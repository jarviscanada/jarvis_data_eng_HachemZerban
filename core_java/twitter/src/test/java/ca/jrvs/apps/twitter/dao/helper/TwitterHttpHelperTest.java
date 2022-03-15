package ca.jrvs.apps.twitter.dao.helper;

import static org.junit.Assert.*;

import java.net.URI;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class TwitterHttpHelperTest {

  @Test
  public void httpPost() throws  Exception{
    String consumerKey = System.getenv("consumerKey");
    String consumerSecret = System.getenv("consumerSecret");
    String accessToken = System.getenv("accessToken");
    String tokenSecret = System.getenv("tokenSecret");

    HttpHelper httpHelper = new TwitterHttpHelper(consumerKey, consumerSecret, accessToken, tokenSecret);

   // HttpResponse response = httpHelper.httpPost(new URI("https://api.twitter.com/1.1/statuses/update.json?status=test1_from_java_APP"));
    HttpResponse response = httpHelper.httpPost(new URI("https://api.twitter.com/1.1/statuses/update.json?status=%40gupnv2%20%23abc%201646151382211&long=1.0&lat=-1.0"));
    System.out.println(EntityUtils.toString(response.getEntity()));
  }

  @Test
  public void httpGet() throws  Exception {
    String consumerKey = System.getenv("consumerKey");
    String consumerSecret = System.getenv("consumerSecret");
    String accessToken = System.getenv("accessToken");
    String tokenSecret = System.getenv("tokenSecret");

    HttpHelper httpHelper = new TwitterHttpHelper(consumerKey, consumerSecret, accessToken, tokenSecret);

    HttpResponse response = httpHelper.httpGet(new URI("https://api.twitter.com/1.1/statuses/show.json?id=1496533826880872448"));

    System.out.println(EntityUtils.toString(response.getEntity()));

  }
}