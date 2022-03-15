package ca.jrvs.apps.twitter.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Coordinates;
import ca.jrvs.apps.twitter.model.Tweet;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class TwitterDaoIntTest {
  private TwitterDao dao;



  @Before
  public void setUp() throws Exception {
    String consumerKey = System.getenv("consumerKey");
    String consumerSecret = System.getenv("consumerSecret");
    String accessToken = System.getenv("accessToken");
    String tokenSecret = System.getenv("tokenSecret");


    // set up dependecy
    HttpHelper httpHelper = new TwitterHttpHelper(consumerKey, consumerSecret, accessToken, tokenSecret);
    // pass dependency
    this.dao = new TwitterDao(httpHelper);

  }


  @Test
  public void create() throws  Exception {
    String hashTag = "#abc";
    String text = "@gupnv2 " + hashTag + " " + System.currentTimeMillis();
    Double lat = 1d ;
    Double lon = -1d;
    Tweet postTweet = buildTweet(text, lat, lon);
    Tweet tweet = dao.create(postTweet);

    assertEquals(text, tweet.getText());
    assertNotNull(text, tweet.getCoordinates());
    assertEquals(2, tweet.getCoordinates().getCoordinates().size());
    assertEquals(lon, tweet.getCoordinates().getCoordinates().get(0));
    assertEquals(lat, tweet.getCoordinates().getCoordinates().get(1));
    assertTrue(hashTag.contains(tweet.getEntities().getHashtags().get(0).getText()));
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

  @Test
  public void findById() {
    String id = "1498706110253047814";
    Tweet tweet = dao.findById(id);
    assertEquals(id,tweet.getIdStr());

  }

  @Test
  public void deleteById() {

    String id = "1498706110253047814";
    Tweet tweet = dao.deleteById(id);
    assertEquals(id,tweet.getIdStr());
  }
}