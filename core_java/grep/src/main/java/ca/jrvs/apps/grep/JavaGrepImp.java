package ca.jrvs.apps.grep;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public abstract class JavaGrepImp implements JavaGrep {

  final Logger logger = LoggerFactory.getLogger(JavaGrepImp.class);
  /**
   * Traverse a given directory and return all files
   *
   * @param rootDir input directory
   * @return files under the rootDir
   */
  List<File> fileList = new ArrayList<>();
  private String regex;
  private String rootPath;
  private String outFile;
  // Use default logger config
/*
  public static void main(String[] args) {
    if (args.length != 3) {
      throw new IllegalArgumentException("Usage: JavaGrep regex rootPath outFile");

    }
    BasicConfigurator.configure();

    JavaGrepImp javaGrepImp = new JavaGrepImp();
    javaGrepImp.setRegex(args[0]);
    javaGrepImp.setRootPath(args[1]);
    javaGrepImp.setOutfile(args[2]);

    try {
      javaGrepImp.process();
    } catch (Exception ex) {

      javaGrepImp.logger.error("Error : Unable to process", ex);
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
    // List <File> fileList = listFiles(getRootPath());
    for (File file : listFiles(getRootPath())) {
      for (String line : readLines(file)) {
        if (containsPattern(line)) {
          matchedLines.add(line);
        }
      }


    }
    writeToFile(matchedLines);
  }

  @Override
  public List<File> listFiles(String rootDir) {

    File dirFile = new File(rootDir);
    File[] fileArr = dirFile.listFiles();

    for (int i = 0; i < fileArr.length; i++) {
      if (fileArr[i].isFile()) {
        fileList.add(fileArr[i]);

      } else {
        String tempdir = fileArr[i].getPath();
        listFiles(tempdir);

      }


    }

    return fileList;
  }

  /**
   * Read a file and return all the lines
   * <p>
   * Explain FileReader, BufferedReader, and character encoding
   *
   * @param inputFile file to be read
   * @return lines
   * @throws IllegalArgumentException if a given input is not a file
   */
  @Override
  public List<String> readLines(File inputFile) throws IllegalArgumentException, IOException {
    List<String> Linelist = new ArrayList<>();
    FileReader fr = null;
    String currentLine = null;

    try {
      fr = new FileReader(inputFile);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    BufferedReader reader = new BufferedReader(fr);
    while ((currentLine = reader.readLine()) != null) {
      Linelist.add(currentLine);

    }

    return Linelist;
  }

  /**
   * check if a line contains the regex pattern (passed by user)
   *
   * @param line input string
   * @return ture if there is a match
   */
  @Override
  public boolean containsPattern(String line) {

    boolean isMatch = (Pattern.matches(getRegex(), line));
    return isMatch;


  }

  /**
   * Write lines to a file
   * <p>
   * Explore: FileOutputStream, OutputStreamWriter, and BufferedWriter
   *
   * @param lines matched line
   * @throws IOException if write failed
   */
  @Override
  public void writeToFile(List<String> lines) throws IOException {
    OutputStream outputStream = new FileOutputStream(getOutfile());
    OutputStreamWriter outwrt = new OutputStreamWriter(outputStream);
    for (String line : lines) {
      outwrt.write(line + "\n");
      //      outwrt.write("\n");
    }
    outwrt.close();
  }

  @Override
  public String getRegex() {
    return regex;
  }

  @Override
  public void setRegex(String regex) {
    this.regex = regex;
  }

  @Override
  public String getOutfile() {
    return outFile;
  }

  @Override
  public void setOutfile(String outfile) {
    this.outFile = outfile;
  }


  @Override
  public String getRootPath() {
    return rootPath;
  }

  @Override
  public void setRootPath(String rootPath) {
    this.rootPath = rootPath;
  }

}
