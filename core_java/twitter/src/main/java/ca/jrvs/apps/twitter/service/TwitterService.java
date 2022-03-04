package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.model.Tweet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class TwitterService implements  Service {


  private final CrdDao dao ;
  @Autowired
  public TwitterService(CrdDao dao ){
    this.dao = dao ;

  }


  /**
   * Validate and post a user input Tweet
   *
   * @param tweet tweet to be created
   * @return created tweet
   * @throws IllegalArgumentException if text exceed max number of allowed characters or lat/long
   *                                  out of range
   */
  @Override
  public Tweet postTweet(Tweet tweet) {
    validatePostTweet(tweet);
    return (Tweet) dao.create(tweet);
  }
  public void validatePostTweet(Tweet tweet){

    if(tweet.getText().length()>140 || checkLatLon(tweet)) {
      throw  new IllegalArgumentException("Wrong Text or Latitude and longtitude out of  range ") ;
    }
  }
  public boolean checkLatLon(Tweet tweet){
    Double longtitude =   tweet.getCoordinates().getCoordinates().get(0) ;
    Double latitude = tweet.getCoordinates().getCoordinates().get(1) ;
if (latitude <-90  || latitude>90 || longtitude<-180 || longtitude>180) {
  return true;
}
  else
    return false;
}

public void validateId(String id) {
    try {
      Long l = Long.valueOf(id) ;
    }
    catch (NumberFormatException e) {
      throw new NumberFormatException("wrong Id fromat  please enter only numbers");
    }
    if ((id.length() != 19)  ) {
      throw  new IllegalArgumentException("Wrong Id format . id should not exceed or be less than 14 digist .") ;

    }
}



  @Override
  public Tweet showTweet(String id) {
    validateId(id);

    return (Tweet) dao.findById(id);
  }
  /**
   * Search a tweet by ID
   *
   * @param id     tweet id
   * @param fields set fields not in the list to null
   * @return Tweet object which is returned by the Twitter API
   * @throws IllegalArgumentException if id or fields param is invalid
   */
  public Tweet showTweet(String id, String[] fields) {
    validateId(id);
    Tweet fulltweet = showTweet(id) ;
  Tweet tweet = new Tweet();

    for (String field : fields) {
      if (field.equals("created_at"))
        tweet.setCreatedAt(fulltweet.getCreatedAt());
      else if (field.equals("id"))
        tweet.setId(fulltweet.getId());
      else if (field.equals("id_str"))
        tweet.setIdStr(fulltweet.getIdStr());
      else if (field.equals("text"))
        tweet.setText(fulltweet.getText());
      else if (field.equals("entities"))
        tweet.setEntities(fulltweet.getEntities());
      else if (field.equals("coordinates"))
        tweet.setCoordinates(fulltweet.getCoordinates());
      else if (field.equals("retweet_count"))
        tweet.setRetweetCount(fulltweet.getRetweetCount());
      else if (field.equals("favorite_count"))
        tweet.setFavoriteCount(fulltweet.getFavoriteCount());
      else if (field.equals("favorited"))
        tweet.setFavorited(fulltweet.getFavorited());
      else if (field.equals("retweeted"))
        tweet.setRetweeted(fulltweet.getRetweeted());
      else
        throw new IllegalArgumentException("Invalid field: please enter a valid one  ");
    }
    return (Tweet) tweet ;
  }

  /**
   * Delete Tweet(s) by id(s).
   *
   * @param ids tweet IDs which will be deleted
   * @return A list of Tweets
   * @throws IllegalArgumentException if one of the IDs is invalid.
   */
  @Override
  public List<Tweet> deleteTweets(String[] ids) {
   List<Tweet> tweetList = new ArrayList<Tweet>();

    for (String id: ids) {
      tweetList.add((Tweet) showTweet(id));
      dao.deleteById(id);
    }

    return tweetList;
  }



}
