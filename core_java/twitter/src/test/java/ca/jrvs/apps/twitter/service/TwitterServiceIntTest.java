package ca.jrvs.apps.twitter.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Coordinates;
import ca.jrvs.apps.twitter.model.Tweet;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class TwitterServiceIntTest {
  private TwitterDao dao ;
  private TwitterService service ;


  @Before
  public void setup(){
    String consumerKey = System.getenv("consumerKey");
    String consumerSecret = System.getenv("consumerSecret");
    String accessToken = System.getenv("accessToken");
    String tokenSecret = System.getenv("tokenSecret");


    // set up dependecy
    HttpHelper httpHelper = new TwitterHttpHelper(consumerKey, consumerSecret, accessToken, tokenSecret);
    // pass dependency
    this.dao = new TwitterDao(httpHelper);
    this.service = new TwitterService(dao);


  }

  @Test
  public void postTweet() {
    String hashTag = "#abc";
    String text = "@gupnv2 " + hashTag + " " + System.currentTimeMillis();
    Double lat = 1d ;
    Double lon = -1d;
    Tweet postTweet = buildTweet(text, lat, lon);
    Tweet tweet = service.postTweet(postTweet);
    assertEquals(text, tweet.getText());
  }

  @Test
  public void showTweet() {
    String id = "1499065005618118670";
    Tweet tweet = service.showTweet(id);

    assertEquals(id,tweet.getIdStr());
  }

  @Test
  public void deleteTweets() {
    String [] ids = {"1499070531752103945"};

    List <Tweet> deletedTweet = service.deleteTweets(ids);


    for(int i =0 ; i< ids.length;i++){
      assertEquals(ids[i],deletedTweet.get(i).getIdStr());
    }



  }
  public static Tweet buildTweet(String text, Double lat, Double lon) {
    Tweet tweet = new Tweet();
    Coordinates coord = new Coordinates();
    tweet.setText(text);

    List<Double> coordList = new ArrayList<>();
    coordList.add(lon);
    coordList.add(lat);
    coord.setCoordinates(coordList);
    tweet.setCoordinates(coord);
    return tweet;
  }

}