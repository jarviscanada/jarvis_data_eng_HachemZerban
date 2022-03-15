package ca.jrvs.apps.twitter.controller;

import static org.junit.Assert.*;

import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Coordinates;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.TwitterService;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class TwitterControllerIntTest {
  private TwitterDao dao ;
  private TwitterService service ;
  private TwitterController controller;

  @Before
  public void setup() {
    String consumerKey = System.getenv("consumerKey");
    String consumerSecret = System.getenv("consumerSecret");
    String accessToken = System.getenv("accessToken");
    String tokenSecret = System.getenv("tokenSecret");

    // set up dependecy
    HttpHelper httpHelper = new TwitterHttpHelper(consumerKey, consumerSecret, accessToken,
        tokenSecret);
    // pass dependency
    this.dao = new TwitterDao(httpHelper);
    this.service = new TwitterService(dao);
    this.controller = new TwitterController(service);
  }

  @Test
  public void postTweet() {
    String hashTag = "#abc";
    String text = "@gupnv2 " + hashTag + " " + System.currentTimeMillis();

   String [] args = {"post ",text,"2:3"};
   Tweet  tweet = controller.postTweet(args);
   assertEquals(text,tweet.getText());


  }

  @Test
  public void showTweet() {
    String id = "1499420278841085958";
    String [] args ={"show",id};
    Tweet tweet = controller.showTweet(args);
    assertEquals(id,tweet.getIdStr());
  }

  @Test
  public void deleteTweet() {
    String [] idsarr= {"1498714154986840066","1498706355154214912"};
    String [] args = {"delete","1498714154986840066,1498706355154214912"};
    List<Tweet> tweetList = controller.deleteTweet(args);
    for (int i=0 ; i<tweetList.size();i++){
      assertEquals(idsarr[i],tweetList.get(i).getIdStr());

    }

  }

}