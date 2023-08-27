import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SaveFile {
  private String filepath;
  private String saveFileName;

  public SaveFile(String filepath, String saveFileName) {
    this.filepath = filepath;
    this.saveFileName = saveFileName;
  }

  public List<Task> readFromSaveFile() throws FileNotFoundException, DukeException {
    List<Task> tasks = new ArrayList<>();
    File f = new File(filepath + saveFileName);
    Scanner in = new Scanner(f);
    while (in.hasNext()) {
      CommandStructure cs = CommandStructure.parse(in.nextLine().trim());
      tasks.add(Task.createTask(cs.command, cs.name, cs.arguments));
    }
    return tasks;
  }

  public void saveToSaveFile(TaskList taskList) {
    File f = new File(filepath);
    if (!f.exists()) {
      f.mkdirs();
    }
    f = new File(filepath + saveFileName);
    if (!f.isFile()) {
      try {
        f.createNewFile();
      } catch (IOException e) {
        System.out.println("CANNOT CREATE NEW FILE");
      }
    }

    try {
      FileWriter out = new FileWriter(f);
      out.write(taskList.getTasksAsCommands());
      out.close();
    } catch (IOException e) {
    }
  }
}
