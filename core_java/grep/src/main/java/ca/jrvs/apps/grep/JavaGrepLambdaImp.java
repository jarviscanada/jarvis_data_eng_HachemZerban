package ca.jrvs.apps.grep;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;
import java.util.regex.Pattern;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JavaGrepLambdaImp extends JavaGrepImp implements JavaGrepLambda {


  public static void main(String[] args) {
    if (args.length != 3) {
      throw new IllegalArgumentException("Usage: JavaGrep regex rootPath outFile");

    }

    BasicConfigurator.configure();
    JavaGrepLambdaImp javaGrepLambdaImp = new JavaGrepLambdaImp();
    javaGrepLambdaImp.setRegex(args[0]);
    javaGrepLambdaImp.setRootPath(args[1]);
    javaGrepLambdaImp.setOutfile(args[2]);

    try {
      javaGrepLambdaImp.process();
    } catch (Exception ex) {

      javaGrepLambdaImp.logger.error("Error : Unable to process", ex);
    }

  }


  /**
   * Top level search workflow
   *
   * @throws IOException
   */
  @Override
  public void process() throws IOException {
    List<String> matchedLines = new ArrayList<>();
    Stream<File> fileStream = lsFileStream(getRootPath());
    fileStream.forEach(file -> {
      try {

        rdlStream(file).forEach(line ->
        {
          if (containsPattern(line)) {
            matchedLines.add(line);
          }

        });

      } catch (IOException e) {
        e.printStackTrace();
      }

    });
    writeToFileStr(matchedLines);
  }

  /**
   * Traverse a given directory and return all files
   *
   * @param rootDir input directory
   * @return Stream of files under the rootDir
   * @throws IOException if listing files in a directory failed
   */

  @Override
  public Stream<File> lsFileStream(String rootDir) throws IOException {
    return Files.walk(Paths.get(rootDir)).map(Path::toFile).filter(File::isFile);
  }

  /**
   * Read a file and return all the lines
   *
   * @param inputfile file to be read
   * @return Stream of lines non-duplicated
   * @throws IOException if reading failed
   */

  @Override
  public Stream<String> rdlStream(File inputfile) throws IOException {
    // to solve the  GC memory overhead problem
    InputStream inputStream = new FileInputStream(inputfile);
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

    return bufferedReader.lines();

    // return Files.lines(Paths.get(inputfile.getPath())).distinct(); this causes Overhead Exception
  }

  /**
   * Write line to a file using streams
   *
   * @param lines matched line
   * @throws IOException if write failed
   */
  @Override
  public void writeToFileStr(List<String> lines) throws IOException {
    Stream<String> strm = lines.stream();
    OutputStream outputStream = new FileOutputStream(getOutfile());
    OutputStreamWriter outwrt = new OutputStreamWriter(outputStream);
    strm.forEach(line -> {
      try {
        outwrt.write(line + "\n");
      } catch (IOException e) {
        e.printStackTrace();
      }
    });
    outwrt.close();
  }

  /**
   * Read a file and return a list of all the lines
   *
   * @param inputFile file to be read
   * @return list of lines
   * @throws IllegalArgumentException
   * @throws IOException
   */

  @Override
  public List<String> readLines(File inputFile) throws IllegalArgumentException, IOException {
    return super.readLines(inputFile);
  }

  /**
   * check if a line contains the regex pattern (passed by user)
   *
   * @param line input string
   * @return ture if there is a match
   */
  @Override
  public boolean containsPattern(String line) {
    return super.containsPattern(line);
  }

  /**
   * Write lines to a file
   *
   * @param lines matched line
   * @throws IOException if write failed
   */
  @Override
  public void writeToFile(List<String> lines) throws IOException {
    super.writeToFile(lines);
  }

  @Override
  public String getRegex() {
    return super.getRegex();
  }

  @Override
  public void setRegex(String regex) {
    super.setRegex(regex);
  }

  @Override
  public String getOutfile() {
    return super.getOutfile();
  }

  @Override
  public void setOutfile(String outfile) {
    super.setOutfile(outfile);
  }

  @Override
  public String getRootPath() {
    return super.getRootPath();
  }

  @Override
  public void setRootPath(String rootPath) {
    super.setRootPath(rootPath);
  }
}
