public class Main {


  public static void main(String[] args) {

    String ts = "lueererlu.jpg";
    String ip = "99.788.987.46";
    String line = "\n";

    RegexExc regex = new RegexExcImp();
    boolean result = regex.matchJpeg(ts);
    boolean resultip = regex.matchIp(ip);
    boolean resultline = regex.isEmptyLine(line);
    System.out.println(resultline);

  }

}
