import java.util.regex.*;

public class RegexExcImp implements RegexExc {

  /**
   * return true if filename exteion is jpg or jpeg ( case insensitive)
   *
   * @param filename
   * @return true
   */
  @Override
  public boolean matchJpeg(String filename) {
    String pattern = ".*(\\.jpg|\\.jpeg)$";
    boolean isMatch = Pattern.matches(pattern, filename);

    return isMatch;


  }

  /**
   * return true if ip is valid to simplfy the problem , IP address range is from 0.0.0.0 to
   * 999.999.999.999
   *
   * @param ip
   * @return
   */
  @Override
  public boolean matchIp(String ip) {
    String pattern = "[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}";
    boolean isMatch = Pattern.matches(pattern, ip);
    return isMatch;
  }

  /**
   * return true if line is empty (e.g. empty whiter space , tabs , etc ... )
   *
   * @param line
   * @return
   */
  @Override
  public boolean isEmptyLine(String line) {
    String pattern = "\\s+|\\t+|\\n+";
    boolean isMatch = Pattern.matches(pattern, line);
    return isMatch;
  }
}
