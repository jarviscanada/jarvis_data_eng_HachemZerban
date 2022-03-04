package ca.jrvs.apps.twitter.controller;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNotNull;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.when;

import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.TwitterService;
import java.util.ArrayList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TwitterControllerUnitTest {
 @Mock
  TwitterService service ;
 @InjectMocks
 TwitterController controller ;

  @Test
  public void postTweet() {
   String hashTag = "#abc";
   String text = "@gupnv2 " + hashTag + " " + System.currentTimeMillis();
    String [] args ={"post",text,"2:3"};
   when(service.postTweet(isNotNull())).thenThrow(new RuntimeException("mock"));
   try {
    controller.postTweet(args);
    fail();
   } catch (RuntimeException e) {
    assertTrue(true);
   }
   when(service.postTweet(any())).thenReturn(new Tweet());
   assertNotNull(controller.postTweet(args));

  }

  @Test
  public void showTweet() {
   String id = "1499420278841085958";
   String [] args ={"show",id};
   when(service.showTweet(isNotNull())).thenThrow(new RuntimeException("mock"));
   try {
    controller.showTweet(args);
   } catch (RuntimeException e) {
    assertTrue(true);
   }
   when(service.showTweet(any())).thenReturn(new Tweet());
   assertNotNull(controller.showTweet(args));

  }

  @Test
  public void deleteTweet() {
   String [] args = {"delete","1498714154986840066,1498706355154214912"};
   when(service.deleteTweets(isNotNull())).thenThrow(new RuntimeException("mock"));
   try {
    controller.deleteTweet(args);

   }catch (RuntimeException e) {
    when(service.deleteTweets(any())).thenReturn(new ArrayList<Tweet>());
    assertNotNull(controller.deleteTweet(args));

   }


  }
}