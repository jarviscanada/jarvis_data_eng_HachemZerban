package ca.jrvs.apps.twitter.dao.helper;

import java.io.IOException;
import java.net.URI;
import javax.swing.text.html.parser.Entity;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

@Component
public class TwitterHttpHelper implements HttpHelper {

  /**
   * Dependecies are specified as private member variables
   */
  private OAuthConsumer consumer;
  private HttpClient httpClient;

  /**
   * Constructor
   * Setup dependencies using secrets
   *
   * @param  consumerKey
   * @param consumerSecret
   * @param accessToken
   * @param tokenSecret
   */
public TwitterHttpHelper(String consumerKey, String consumerSecret, String accessToken,
    String tokenSecret) {
  consumer = new CommonsHttpOAuthConsumer(consumerKey,
      consumerSecret);
  consumer.setTokenWithSecret(accessToken, tokenSecret);

  httpClient = HttpClientBuilder.create().build();

}
public  TwitterHttpHelper() {
  String consumerKey = System.getenv("consumerKey");
  String consumerSecret = System.getenv("consumerSecret");
  String accessToken = System.getenv("accessToken");
  String tokenSecret = System.getenv("tokenSecret");
  consumer = new CommonsHttpOAuthConsumer(consumerKey, consumerSecret);
  consumer.setTokenWithSecret(accessToken, tokenSecret);
  httpClient = HttpClientBuilder.create().build();

}




  /**
   * Execute a HTTP Post call
   *
   * @param uri
   * @return
   */
  @Override
  public HttpResponse httpPost(URI uri) {
    try {
      return executeHttpRequest(HttpMethod.POST,uri,null);
    }  catch (IOException | OAuthException e) {
      throw  new RuntimeException("Failed to execute", e);
    }
  }

  /**
   * Execute a HTTP Get call
   *
   * @param uri
   * @return
   */
  @Override
  public HttpResponse httpGet(URI uri) {
    try {
      return executeHttpRequest(HttpMethod.GET,uri,null);
    }  catch (IOException | OAuthException e) {
      throw  new RuntimeException("Failed to execute", e);
    }
  }

  /*
  other solution
   public HttpResponse httpGet(URI uri) {
    // create HTTP GET request
    HttpGet request = new HttpGet(uri);
    // sign the request
    consumer.sign(request);

    HttpClient httpClient = HttpClientBuilder.create().build();

    return httpClient.execute(request);
  }

   */

  private HttpResponse executeHttpRequest (HttpMethod method, URI uri, StringEntity stringEntity)
      throws OAuthException, IOException {
    if(method == HttpMethod.GET) {
      HttpGet request = new HttpGet(uri);
      consumer.sign(request);
      return httpClient.execute(request);

    }else if (method == HttpMethod.POST) {
      HttpPost request = new HttpPost(uri);
      if (stringEntity != null) {
        request.setEntity(stringEntity);

      }
      consumer.sign(request);
      return  httpClient.execute(request);

    }
    else {
      throw new IllegalArgumentException("Unknow HTTP method: " + method.name());
    }


  }

  public static void main(String[] args) throws  Exception {
    String consumerKey = System.getenv("consumerKey");
    String consumerSecret = System.getenv("consumerSecret");
    String accessToken = System.getenv("accessToken");
    String tokenSecret = System.getenv("tokenSecret");

    HttpHelper httpHelper = new TwitterHttpHelper(consumerKey, consumerSecret, accessToken, tokenSecret);

    HttpResponse response = httpHelper.httpPost(new URI("https://api.twitter.com/1.1/statuses/update.json?status=test1_from_java_APP"));

    System.out.println(EntityUtils.toString(response.getEntity()));






  }
}
