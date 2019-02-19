package Controllers;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;

public class Writer {

  /**
   * Writes all action into log.txt, in order to keep all the data for future uses.
   *
   * @param action a String in the format of expressing the action
   * @throws IOException the exception handler
   */
  public static void writeAction(String action) throws IOException {

    try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("log.txt", true)))) {
      out.println(action);
    } catch (IOException e) {
      System.err.println(e);
    }
  }
}
