package ca.jrvs.apps.grep;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

public interface JavaGrepLambda {


  /**
   * Traverse a given directory and return all files
   * @param rootDir input directory
   * @return Stream of files under the rootDir
   * @throws IOException if listing files in a directory failed
   */

  Stream<File> lsFileStream(String rootDir) throws IOException;
  /**
   * Read a file and return all the lines
   * @param inputfile file to be read
   * @return Stream of lines non-duplicated
   * @throws IOException if reading failed
   */
  Stream<String> rdlStream(File inputfile) throws IOException;
  /**
   * Write line to a file using streams
   * @param lines matched line
   * @throws IOException if write failed
   */
  void writeToFileStr(List<String> lines) throws IOException;


}
