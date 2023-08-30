package storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import tasklist.TaskList;
import tasks.Task;

public class DataWriter {
  private static String filePath;

  public DataWriter(String filePath) {
    DataWriter.filePath = filePath;
  }

  public static void addLine(String line) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(DataWriter.filePath, true))) {
      writer.write(line);
      writer.newLine();
    } catch (IOException e) {
      System.err.println("An error occurred while adding the line: " + e.getMessage());
    }
  }

  public void deleteLine(int lineNumber) {
    List<String> lines = new ArrayList<>();
    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
      String currentLine;
      while ((currentLine = reader.readLine()) != null) {
        lines.add(currentLine);
      }
    } catch (IOException e) {
      System.err.println("An error occurred while reading the file: " + e.getMessage());
      return;
    }

    if (lineNumber >= 1 && lineNumber <= lines.size()) {
      lines.remove(lineNumber - 1);

      try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
        for (String line : lines) {
          writer.write(line);
          writer.newLine();
        }
      } catch (IOException e) {
        System.err.println("An error occurred while writing back to the file: " + e.getMessage());
      }
    } else {
      System.out.println("Invalid line number.");
    }
  }

  public static void refresh(TaskList tasks) {
    try {
      // Clean the file by overwriting it with an empty content
      BufferedWriter cleanWriter = new BufferedWriter(new FileWriter(filePath, false));
      cleanWriter.write("");
      cleanWriter.close();

      // Append tasks from the array list to the file
      BufferedWriter appendWriter = new BufferedWriter(new FileWriter(filePath, true));
      for (int i = 0; i < tasks.size(); i++) {
        Task task = tasks.get(i);
        appendWriter.write(task.toString() + System.lineSeparator());
      }
      appendWriter.close();
    } catch (IOException e) {
      System.out.println("An error occurred: " + e.getMessage());
    }
  }
}
