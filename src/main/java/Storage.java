import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles loading and saving of task data to disk.
 */
public class Storage {
  /**
   * Path to save file.
   */
  private final String filePath;

  /**
   * Constructor for storage class.
   *
   * @param filePath Path to text file to use as save-file.
   * @throws SaveFileException if file cannot be created
   */
  public Storage(String filePath) throws SaveFileException {
    this.filePath = filePath;
    createSaveFile();
  }

  /**
   * Create new empty save file at SAVE_FILE_PATH if it does not already exist.
   *
   * @throws SaveFileException if file cannot be created
   */
  public void createSaveFile() throws SaveFileException {
    File saveFile = new File(filePath);
    File parent = saveFile.getParentFile();
    // check & create parent dir(s)
    if (parent != null && !parent.exists()) {
      parent.mkdirs();
    }
    try {
      // create save file
      saveFile.createNewFile();
    } catch (IOException e) {
      throw new SaveFileException("Error creating save file: " + e.getMessage());
    }
  }

  /**
   * Reads save file contents and returns TaskList.
   *
   * @return TaskList instance.
   * @throws SaveFileException        if there is an error reading the file
   * @throws InvalidTaskDataException if the text data format is invalid
   */
  public ArrayList<Task> load() throws SaveFileException, InvalidTaskDataException {
    try {
      File f = new File(filePath);
      Scanner s = new Scanner(f);
      ArrayList<Task> readTasks = new ArrayList<>();
      while (s.hasNext()) {
        String data = s.nextLine();
        if (data.isBlank()) {
          continue;
        }
        String taskType = data.split(Task.DELIMITER_REGEX)[0];
        switch (taskType) {
          case "T":
            readTasks.add(ToDoTask.fromData(data));
            break;
          case "D":
            readTasks.add(DeadlineTask.fromData(data));
            break;
          case "E":
            readTasks.add(EventTask.fromData(data));
            break;
          default:
            throw new InvalidTaskDataException();
        }
      }
      return readTasks;
    } catch (IOException e) {
      throw new SaveFileException("Error reading save file: " + e.getMessage());
    }
  }

  /**
   * Saves given list of tasks to save file.
   *
   * @param tasks ArrayList of Task instances.
   * @throws SaveFileException If there is an error saving the file.
   */
  public void save(ArrayList<Task> tasks) throws SaveFileException {
    StringBuilder s = new StringBuilder();
    for (int i = 1; i <= tasks.size(); i++) {
      s.append(tasks.get(i - 1).toData());
    }
    try {
      FileWriter fw = new FileWriter(filePath);
      fw.write(s.toString());
      fw.close();
    } catch (IOException e) {
      throw new SaveFileException("Error writing save file: " + e.getMessage());
    }
  }
}

