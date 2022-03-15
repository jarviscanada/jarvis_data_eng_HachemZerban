package ca.jrvs.apps.twitter.service;

import static net.bytebuddy.matcher.ElementMatchers.any;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.isNotNull;
import static org.mockito.Mockito.when;

import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.model.Coordinates;
import ca.jrvs.apps.twitter.model.Tweet;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TwitterServiceUnitTest {
  @Mock
  CrdDao dao;

  @InjectMocks
  TwitterService service;
  @Test
  public void postTweet() {
    Tweet tweet = buildTweet("test",2d,-2d);
    when(dao.create(isNotNull())).thenThrow(new RuntimeException("mock"));
    try {
      service.postTweet(tweet);
      fail();
    } catch (RuntimeException e) {
      assertTrue(true);
    }

    when(dao.create(any())).thenReturn(new Tweet());
    assertNotNull(service.postTweet(tweet));
  }

  @Test
  public void showTweet() {
   Tweet tweet = buildTweet("test",2d,-2d);
   when(dao.findById(isNotNull())).thenThrow(new RuntimeException("mock"));
   try {
     service.showTweet(tweet.getIdStr());
     fail();
   } catch (RuntimeException e) {
    assertTrue(true);
   }
    when(dao.findById(any())).thenReturn(new Tweet());
    assertNotNull(service.showTweet(tweet.getIdStr()));

  }

  @Test
  public void deleteTweets() {

    String [] ids = {"1499070531752103945"};
    when(dao.deleteById(isNotNull())).thenThrow(new RuntimeException("mock"));
    try {
      service.deleteTweets(ids);
      fail();
    } catch (RuntimeException e) {
      assertTrue(true);
    }


    when(dao.deleteById(any())).thenReturn(new Tweet());

    List <Tweet> deletedTweet = service.deleteTweets(ids);
    for (Tweet tweet: deletedTweet) {
      assertNotNull(tweet);
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