package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gdata.util.common.base.PercentEscaper;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParser;

@org.springframework.stereotype.Repository

public class TwitterDao  implements  CrdDao<Tweet,String>{



  //URI constants
  private static final String API_BASE_URI = "https://api.twitter.com";
  private static final String POST_PATH = "/1.1/statuses/update.json";
  private static final String SHOW_PATH = "/1.1/statuses/show.json";
  private static final String DELETE_PATH = "/1.1/statuses/destroy/";
  //URI symbols
  private static final String QUERY_SYM = "?";
  private static final String AMPERSAND = "&";
  private static final String EQUAL = "=";

  //Response code
  private static final int HTTP_OK = 200;

  private final HttpHelper httpHelper;

  @Autowired
  public TwitterDao(HttpHelper httpHelper) {
    this.httpHelper = httpHelper;
  }

  /**
   * Create an entity(Tweet) to the underlying storage
   *
   * @param entity entity that to be created
   * @return created entity
   */
  @Override
  public Tweet create(Tweet entity) {
    PercentEscaper percentEscaper = new PercentEscaper("",false);
    URI uri = null;
    // Construct URI
    try {
      uri = new URI(API_BASE_URI + POST_PATH + QUERY_SYM + "status" + EQUAL
      + percentEscaper.escape(entity.getText()) + AMPERSAND + "long" + EQUAL + entity.getCoordinates().getCoordinates().get(0).toString()
      + AMPERSAND + "lat" + EQUAL + entity.getCoordinates().getCoordinates().get(1).toString());
    } catch ( URISyntaxException e) {
      e.printStackTrace();
    }

    HttpResponse response = httpHelper.httpPost(uri);
    return  parseResponseBody(response, HTTP_OK);
  }

  /**
   * Find an entity(Tweet) by its id
   *
   * @param s entity id
   * @return Tweet entity
   */
  @Override
  public Tweet findById(String s) {



    PercentEscaper percentEscaper = new PercentEscaper("",false);
    URI uri = null ;

    try {
      uri = new URI(API_BASE_URI + SHOW_PATH + QUERY_SYM + "id" + EQUAL +s);
    } catch (URISyntaxException e) {
      e.printStackTrace();
    }
     HttpResponse  httpResponse = httpHelper.httpGet(uri);
    return parseResponseBody(httpResponse,HTTP_OK);
  }






  @Override
  public Tweet deleteById(String s) {

    PercentEscaper percentEscaper = new PercentEscaper("",false);
    URI uri = null ;

    try {
      uri = new URI(API_BASE_URI + DELETE_PATH + s + ".json");
    } catch (URISyntaxException e) {
      e.printStackTrace();
    }
    HttpResponse  httpResponse = httpHelper.httpPost(uri);
    return parseResponseBody(httpResponse,HTTP_OK);
  }


  protected Tweet parseResponseBody(HttpResponse response, int expectedStatusCode) {
    Tweet tweet;

    //Check response status
    int status = response.getStatusLine().getStatusCode();
    if (status != expectedStatusCode) {
      try {
        System.out.println(EntityUtils.toString(response.getEntity()));
      } catch (IOException e) {
        System.out.println("Response has no entity");
      }
      throw new RuntimeException("Unexpected HTTP status: " + status);
    }

    if (response.getEntity() == null)
      throw new RuntimeException("Empty response body");

    //Convert Response entity to string
    String jsonStr;
    try {
      jsonStr = EntityUtils.toString(response.getEntity());
    } catch (IOException e) {
      throw new RuntimeException("Failed to convert entity to String", e);
    }

    //Convert JSON string to Tweet object
    try {
      tweet = toObjectFromJson(jsonStr, Tweet.class);
    } catch (IOException e) {
      throw new RuntimeException("Unable to convert JSON string to Object", e);
    }

    return tweet;
  }
  public static <T> T toObjectFromJson(String json, Class clazz) throws IOException {
    ObjectMapper m = new ObjectMapper();
    return (T) m.readValue(json, clazz);
  }
}
