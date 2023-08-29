package duke;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
  private String filePath;
  private File directory;
  private File file;

  public Storage(String filePath) {
    this.filePath = filePath;
  }

  // initialise the storage
  // check whether the file exists


  public ArrayList<Task> load() throws DukeException {
    // load tasks from storage using filePath given
    ArrayList<Task> taskList = new ArrayList<Task>();
    // check whether directory exists from filePath given
    // check the directory until the file name from the file path
    // if directory doesn't exist, create directory
    directory = new File(filePath.substring(0, filePath.lastIndexOf("/")));
    file = new File(filePath);
    if (directory.exists() && file.exists()) {
      // load the data into taskList
      try {
        Scanner s = new Scanner(file);
        while (s.hasNext()) {
          // save to taskList
          taskList.add(Task.parseTask(s.nextLine()));
        }
        s.close();
      } catch (IOException e) {
        throw new DukeException(e.getMessage());
      }
    }
    return taskList;
  }

  public void save(TaskList tasks) throws DukeException {
    if (!directory.exists()) {
      directory.mkdir();
    }
    if (!file.exists()) {
      try {
        file.createNewFile();
      } catch (IOException e) {
        throw new DukeException(e.getMessage());
      }
    }

    try {
      FileWriter fWriter = new FileWriter(filePath);
      for (int i = 0; i < tasks.size(); i++) {
        fWriter.write(tasks.getTaskItemSaveString(i) + "\n");
      }
      fWriter.close();
    } catch (IOException e) {
      throw new DukeException(e.getMessage());
    } 
    

  }

}
