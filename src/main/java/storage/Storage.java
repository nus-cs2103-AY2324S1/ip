package storage;

import exceptions.JamesBondException;
import parser.Parser;
import tasks.Task;
import tasks.TaskList;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


public class Storage {
   private String file;

   public Storage(String file) {
       this.file = file;
   }

    public void saveTasksToFile(TaskList taskList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            // Append tasks to the end of the file
            for (Task task : taskList.getToDos()) {
                writer.write(task.toSaveFormat());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

   public TaskList loadTasksFromFile() throws JamesBondException {
       TaskList tasksList = new TaskList();
       try {
           List<String> lines = Files.readAllLines(Paths.get(file));
           for (String line : lines) {
               Task task = Parser.parseFomSaveFormat(line);
                tasksList.add(task);
           }
       } catch (IOException e) {
           e.printStackTrace();
       }
       return tasksList;
   }
}
