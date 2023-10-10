package cheese.storage;

import java.util.ArrayList; 
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDate;

import cheese.task.Task;
import cheese.parser.Parser;
import cheese.tasklist.TaskList;

/**
 * Storage class that handles the loading and saving of tasks
 *
 */

public class Storage {
  /** 
   * File object that represents the file to be loaded from and saved to
   */
  private File file;
  private TaskList taskList;
  private Parser parser;

  public Storage(String filePath) {
    this.file = new File(filePath);
    if (!this.file.exists()) {
      try {
        this.file.createNewFile();
        System.out.println("File created: " + this.file.getName());
      } catch (IOException e) {
        System.out.println("Something went wrong: " + e.getMessage());
      }
    }
    this.taskList = new TaskList();
    this.parser = new Parser();

  }
  
  /**
   * Loads the tasks from the file and returns a TaskList object
   * @return TaskList object
   */
  public TaskList loadTask() {
    try {
      Scanner sc = new Scanner(this.file);
      while (sc.hasNextLine()) {
        String line = sc.nextLine();
        String[] splitLine = line.split("\\|");
        char taskType = splitLine[0].charAt(1);
        boolean isDone = (splitLine[0].charAt(4) == 'X');
        String description = splitLine[1].trim();
        switch (taskType) {
          case 'T':
          Task todo = new Task(taskType, description);
          if (isDone) {
            todo.markAsDone();
          }
          this.taskList.addTask(todo);
          break;
          case 'D':
          if (parser.isBy(description)) {
            String deadlineInfo = parser.matchBy(description).group(1);
            String desc = description.split(" \\(")[0];
            LocalDate deadlineDate = parser.convertDateTime(deadlineInfo);
            // Can be formatted as a LocalDate obj
            if (deadlineDate != null) {

              Task deadline = new Task(taskType, desc, deadlineDate);
              if (isDone) {
                deadline.markAsDone();
              }
              this.taskList.addTask(deadline);
            } else { // Cannot be formatted as a LocalDate obj
              Task deadline = new Task(taskType, desc, deadlineInfo);
              if (isDone) {
                deadline.markAsDone();
              }
              this.taskList.addTask(deadline);
            }
          break;

          }
          case 'E':
          if (parser.isFrom(splitLine[1]) && parser.isTo(splitLine[1])) {
            String eventFrom = parser.matchFrom(splitLine[1]).group(1);
            String eventTo = parser.matchTo(splitLine[1]).group(1);
            String desc = splitLine[1].split(" ")[0];
            Task event = new Task(taskType, desc, eventFrom, eventTo);

            if (isDone) {
              event.markAsDone();
            }
            this.taskList.addTask(event);
            }
          break;
        }
      }
      sc.close();
    } catch (FileNotFoundException e) {
      System.out.println("File not found");
    }
    return this.taskList;
    
  }
  
  /**
   * Saves the tasks in the TaskList object to the file
   * @param cheeseList TaskList object
   */
  public void saveTask(List<Task> cheeseList) {
    try {
      FileWriter fw = new FileWriter(this.file);
      for (int i = 0; i < this.taskList.getSize(); i++) {
        Task task = this.taskList.getTask(i);
        fw.write(task.toString() + "\n");
      }
      fw.close();
    } catch (IOException e) {
      System.out.println("Something went wrong: " + e.getMessage());
    }
  }


}
