import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class Storage {
   private String file;

   public Storage(String file) {
       this.file = file;
   }

   public void saveTasksToFile(TaskList taskList) {
       try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (UserInput task : taskList.toDos) {
                writer.write(task.toSaveFormat());
                writer.newLine();
            }
       }
       catch(IOException e) {
           e.printStackTrace();
       }
   }

   public TaskList loadTasksFromFile() {
       TaskList tasksList = new TaskList();
       try {
           List<String> lines = Files.readAllLines(Paths.get(file));
           for (String line : lines) {
               UserInput userInput = UserInput.parseFomSaveFormat(line);
                tasksList.toDos.add(userInput);
           }
       } catch (IOException e) {
           e.printStackTrace();
       }
       return tasksList;
   }
}
