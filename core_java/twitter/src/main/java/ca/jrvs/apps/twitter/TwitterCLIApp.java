package ca.jrvs.apps.twitter;

import ca.jrvs.apps.twitter.controller.Controller;
import ca.jrvs.apps.twitter.controller.TwitterController;
import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.Service;
import ca.jrvs.apps.twitter.service.TwitterService;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.luxsuen.jsonutil.util.JsonUtil;
import java.util.Locale;
import org.kopitubruk.util.json.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TwitterCLIApp {

  public static String USAGE = "USAGE: TwitterCLI App post|show|delete [options]";

  private Controller controller ;

  @Autowired
  public TwitterCLIApp(Controller controller){
    this.controller = controller ;
  }
  public static void main(String[] args) {

    String consumerKey = System.getenv("consumerKey");
    String consumerSecret = System.getenv("consumerSecret");
    String accessToken = System.getenv("accessToken");
    String tokenSecret = System.getenv("tokenSecret");
    // create components and chain dependencies
    HttpHelper httpHelper = new TwitterHttpHelper(consumerKey, consumerSecret, accessToken ,tokenSecret);
    CrdDao dao = new TwitterDao(httpHelper);
    Service service = new TwitterService(dao);
    Controller controller = new TwitterController(service);
    TwitterCLIApp app = new TwitterCLIApp(controller);

    // start app
    app.run(args);


  }
  public void run (String[] args) {
    if (args.length == 0) {
      throw new IllegalArgumentException(USAGE);
    }
    switch (args[0].toLowerCase()) {
      case "post":



    }


  }
  public void printTweet(Tweet tweet) {
    try {
      System.out.println(JSONUtil.toJSON(tweet));
    } catch (RuntimeException e) {
      throw new RuntimeException("Unable to convert Tweet object to string", e);
    }
  }

}
