package ca.jrvs.apps.twitter.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.isNotNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.model.Coordinates;
import ca.jrvs.apps.twitter.model.Tweet;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TwitterDaoUnitTest {
 private static String tweetJsonStr = "{\n"
      + "   \"created_at\":\"Mon Feb 18 21:24:39 +0000 2019\",\n"
      + "   \"id\":1097607853932564480,\n"
      + "   \"id_str\":\"1097607853932564480\",\n"
      + "   \"text\":\"test with loc223\",\n"
      + "   \"entities\":{\n"
      + "        \"hashtags\":[],\n"
      + "        \"user_mentions\":[]\n"
      + "   },\n"
      + "   \"coordinates\":null,\n"
      + "   \"retweet_count\":0,\n"
      + "   \"favorite_count\":0,\n"
      + "   \"favorite\":false,\n"
      + "   \"retweeted\":false\n"
      + "}";

  @Mock
  HttpHelper mockHelper;

  @InjectMocks
  TwitterDao dao;
  @Test
  public void create() throws Exception{
    String hashTag = "#abcMockito";
    String text = "@someone sometext " + hashTag + " " + System.currentTimeMillis();
    Double lat = 1d;
    Double lon = -1d;
    //exception is expected here
    when(mockHelper.httpPost(isNotNull())).thenThrow(new RuntimeException("mock"));
    try {
      dao.create(buildTweet(text, lon, lat));
      fail();
    } catch (RuntimeException e) {
      assertTrue(true);
    }

    when(mockHelper.httpPost(isNotNull())).thenReturn(null);
    TwitterDao spyDao = Mockito.spy(dao);
    Tweet expectedTweet = toObjectFromJson(tweetJsonStr, Tweet.class);
    doReturn(expectedTweet).when(spyDao).parseResponseBody(any(), anyInt());
    Tweet tweet = spyDao.create(buildTweet(text, lon, lat));
    assertNotNull(tweet);
    assertNotNull(tweet.getText());
  }
  @Test
  public void findbyId() throws IOException {
    when(mockHelper.httpGet(isNotNull())).thenThrow(new RuntimeException(" mock findid"));
    try {
      dao.findById("1498714154986840066");
      fail();
    } catch (RuntimeException e) {
      assertTrue(true);
    }
    when(mockHelper.httpGet(isNotNull())).thenReturn(null);
    TwitterDao spyDao = Mockito.spy(dao);
    Tweet expectedTweet = toObjectFromJson(tweetJsonStr, Tweet.class);
    doReturn(expectedTweet).when(spyDao).parseResponseBody(any(),anyInt());
    Tweet tweet = spyDao.findById("1498714154986840066");
    assertNotNull(tweet);
    assertNotNull(tweet.getText());
  }

  @Test
  public void deletebyId() throws IOException {

    when(mockHelper.httpGet(isNotNull())).thenThrow(new RuntimeException(" mock fdeletebyid"));
    try {
      dao.findById("1498714154986840006");
      fail();
    } catch (RuntimeException e) {
      assertTrue(true);
    }
    when(mockHelper.httpGet(isNotNull())).thenReturn(null);
    TwitterDao spyDao = Mockito.spy(dao);
    Tweet expectedTweet = toObjectFromJson(tweetJsonStr, Tweet.class);
    doReturn(expectedTweet).when(spyDao).parseResponseBody(any(),anyInt());
    Tweet tweet = spyDao.deleteById("1498714154986840066");
    assertNotNull(tweet);
    assertNotNull(tweet.getText());
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
  public static <T> T toObjectFromJson(String json, Class clazz) throws IOException {
    ObjectMapper m = new ObjectMapper();
    return (T) m.readValue(json, clazz);
  }
}