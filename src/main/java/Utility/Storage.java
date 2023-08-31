package Utility;

import TaskPackages.TaskList;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import Commands.Command;
import Exceptions.DukeException;

public class Storage {

  private Path path;

  public Storage(String filepath) {
    String homedir = new File(System.getProperty("user.dir")).getParent();
    String[] splitFilepath = Parser.filePathParser(filepath);
    this.path = Paths.get(homedir, splitFilepath[0], splitFilepath[1]);
  }

  public TaskList load() throws DukeException {
    if (Files.exists(path)) {
      TaskList tasklist = new TaskList();
      try {
        List<String> contents = Files.readAllLines(path);
        for (String content : contents) {
          Command c = Parser.fileLineParser(content);
          c.load(tasklist);
        }
        return tasklist;
      } catch (FileNotFoundException e) {
        throw new DukeException(e.getMessage());
      } catch (IOException e) {
        throw new DukeException(e.getMessage());
      } catch (NumberFormatException e) {
        throw new DukeException(e.getMessage());
      } catch (Exception e) {
        throw new DukeException(e.getMessage());
      }
    } else {
      throw new DukeException("File not found");
    }
  }

  public void writeToFile(TaskList tasklist) {
    try {
      if (Files.notExists(path)) {
        Files.createFile(path);
      }
      BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8,
          StandardOpenOption.TRUNCATE_EXISTING);
      while (!tasklist.isEmpty()) {
        String tempString = tasklist.clearList();
        System.err.println(tempString);
        writer.write(tempString + "\n");
        writer.flush();
      }
    } catch (Exception e) {
      System.err.println(e);
    }
  }

}
