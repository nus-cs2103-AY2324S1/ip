package jo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import jo.task.Deadline;
import jo.task.Event;
import jo.task.Task;

/**
 * Responsible for loading tasks from and updating tasks to a file.
 * It provides methods to read tasks from a file and write tasks to a file.
 */
public class Storage {
    private File f;
    private String filePath;

    /**
     * Constructs a new `Storage` object with the specified file path.
     * @param filePath The file path where task data is stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        this.f = new File(filePath);
    }

    /**
     * Loads tasks from the specified file and returns them as an ArrayList.
     * @return An ArrayList of tasks loaded from the file.
     * @throws JoException If an error occurs while reading the file or parsing tasks.
     */
    public ArrayList<Task> load() throws JoException {
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String[] task = s.nextLine().split("\\|");
                String taskType = task[0].trim();
                boolean isDone = task[1].trim().equals("1");
                if (taskType.equals("T")) {
                    taskList.add(new Task(task[2].trim(), isDone));
                } else if (taskType.equals("D")) {
                    taskList.add(new Deadline(task[2].trim(), isDone, LocalDate.parse(task[3].trim())));
                } else if (taskType.equals("E")) {
                    taskList.add(
                            new Event(
                                    task[2].trim(),
                                    isDone,
                                    LocalDate.parse(task[3].trim()),
                                    LocalDate.parse(task[4].trim())
                            )
                    );
                }
            }
        } catch (FileNotFoundException e) {
            throw new JoException(e.getMessage());
        }
        return taskList;
    }

    /**
     * Updates the specified `TaskList` by writing its tasks to the file.
     * @param taskList The `TaskList` to update in the file.
     * @throws JoException If an error occurs while writing to the file.
     */
    public void update(TaskList taskList) throws JoException {
        try {
            FileWriter fw = new FileWriter(this.filePath);
            for (Task t : taskList.tList) {
                fw.write(t.toFile() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            throw new JoException(e.getMessage());
        }
    }
}
