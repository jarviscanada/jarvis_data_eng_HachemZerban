package ca.jrvs.apps.twitter.controller;

import ca.jrvs.apps.twitter.model.Coordinates;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.Service;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
@org.springframework.stereotype.Controller
public class TwitterController  implements Controller{

  private  static final  String COORD_SEP = ":";
  private  static  final String COMMA = ",";

  private Service service ;

  @Autowired
  public TwitterController(Service service) {
    this.service= service;

  }


  /**
   * Parse user argument and post a tweet by calling service classes
   *
   * @param args
   * @return a posted tweet
   * @throws IllegalArgumentException if args are invalid
   */
  @Override
  public Tweet postTweet(String[] args) {
    if (args.length<3)
     throw new IllegalArgumentException("Invalid arguents!");

    String text = args[1];
    String coordinates = args[2];
    String [] coordArray = coordinates.split(COORD_SEP) ;
    if(coordArray.length != 2 || StringUtils.isEmpty(text)) {
      throw new IllegalArgumentException(
          "Invalid Location format \n USAGE : Twitter CLIApp post \"tweet_text\" \"latitude:longtitde\""
      );
    }
      Double lat = null ;
      Double lon = null ;
      try {
        lat = Double.parseDouble(coordArray[0]);
        lon = Double.parseDouble(coordArray[1]);

      }
      catch (Exception e ){
        throw  new IllegalArgumentException("Invalid location format \n USAGE : Twitter CLIApp post post \"tweet_text\" \"latitude:longtitde\""
       ,e );
      }

   Tweet postTweet = buildTweet(text, lon, lat);
      return  service.postTweet(postTweet);
  }


  public Tweet buildTweet (String text, double longitute, double lat){
    Tweet tweet = new Tweet();
     Coordinates coordinates = new Coordinates();
     List <Double> coord = new ArrayList<Double>();
     coord.add(longitute);
     coord.add(lat);

     coordinates.setCoordinates(coord);
    tweet.setText(text);
    tweet.setCoordinates(coordinates);
    return tweet ;

  }


  /**
   * Parse user argument and search a tweet by calling service classes
   *
   * @param args
   * @return a tweet
   * @throws IllegalArgumentException if args are invalid
   */
  @Override
  public Tweet showTweet(String[] args) {
    if (args.length <2){
      throw new IllegalArgumentException(
          "Invalid arugements format \n USAGE : Twitter CLIApp show \"tweed_id\" "
      );
    }
    String id = args[1];
    return service.showTweet(id);
  }

  /**
   * Parse user argument and delete tweets by calling service classes
   *
   * @param args
   * @return a list of deleted tweets
   * @throws IllegalArgumentException if args are invalid
   */
  @Override
  public List<Tweet> deleteTweet(String[] args) {

    if (args.length <2){
      throw new IllegalArgumentException(
          "Invalid arugements format \n USAGE : Twitter CLIApp delete [id1,id2,..] "
      );
    }
    String ids = args[1];
    String [] arrayids = ids.split(COMMA);
    if(arrayids.length<1 ){
      throw new IllegalArgumentException(
          "Invalid arugements format \n USAGE : Twitter CLIApp delete  [id1,id2,..] "
      );
    }

    return service.deleteTweets(arrayids);

  }
}
