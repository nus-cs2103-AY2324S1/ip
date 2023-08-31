package duke.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * A file reader helper that extends read and write to automatically create files if not present.
 */
public class FileIO {
  private String filepath;
  private String filename;

  /**
   * Returns a FileIO object
   *
   * @param filepath the path to the directory of the file
   * @param filename the name of the file and extension
   */
  public FileIO(String filepath, String filename) {
    this.filepath = filepath;
    this.filename = filename;
  }

  /**
   * Returns a file's content as a string
   *
   * @return the file's content
   * @throws FileNotFoundException if the file is not found
   */
  public String read() throws FileNotFoundException {
    File f = new File(filepath + filename);
    Scanner in = new Scanner(f);
    StringBuilder sb = new StringBuilder();
    while (in.hasNext()) {
      sb.append(in.nextLine().trim());
      sb.append('\n');
    }
    return sb.toString().trim();
  }

  /**
   * Writes s to the file
   *
   * @param s the string s to write to the file
   * @throws IOException if the file cannot be created
   */
  public void write(String s) throws IOException {
    File f = new File(filepath);
    if (!f.exists()) {
      f.mkdirs();
    }

    f = new File(filepath + filename);
    if (!f.isFile()) {
      f.createNewFile();
    }

    FileWriter out = new FileWriter(f);
    out.write(s);
    out.close();
  }

  /**
   * Returns the full file path and name
   *
   * @return the full file path
   */
  public String getFilename() {
    return String.format("%s%s", filepath, filename);
  }
}
