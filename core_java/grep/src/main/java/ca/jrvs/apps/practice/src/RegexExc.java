public interface RegexExc {

  /**
   * return true if filename exteion is jpg or jpeg ( case insensitive)
   *
   * @param filename
   * @return
   */
  boolean matchJpeg(String filename);

  /**
   * return true if ip is valid to simplfy the problem , IP address range is from 0.0.0. to
   * 999.999.999.999
   *
   * @param ip
   * @return
   */
  boolean matchIp(String ip);

  /**
   * return true if line is empty (e.g. rempty whiter space , tabs , etc ... )
   *
   * @param line
   * @return
   */
  boolean isEmptyLine(String line);


}
