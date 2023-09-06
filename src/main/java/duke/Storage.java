package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.TaskType;
import duke.task.ToDo;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a storage that loads tasks from the specified file and saves tasks to the same file.
 */
public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the specified file path. Create the folder if necessary.
     * @throws FileNotFoundException
     */
    public ArrayList<Task> loadFile() throws FileNotFoundException {
        File folder = new File("data");
        if (!folder.exists()) {
            folder.mkdir();
        }
        File f = new File(folder, "duke.txt");
        Scanner s = new Scanner(f);
        ArrayList<Task> tasks = new ArrayList<>();
        while (s.hasNext()) {
            tasks.add(addTask(s.nextLine()));
        }
        return tasks;
    }

    private Task addTask(String description) {
        TaskType taskType = TaskType.valueOf(description.substring(0, description.indexOf(" ")).toUpperCase());
        boolean isDone = description.charAt(description.indexOf("|") + 2) == '1';
        String taskDescription = description.substring(description.indexOf("|") + 6);
        switch (taskType) {
        case TODO:
            return new ToDo(taskDescription, isDone);
        case DEADLINE:
            String by = taskDescription.substring(taskDescription.indexOf("|") + 2);
            taskDescription = taskDescription.substring(0, taskDescription.indexOf("|") - 1);
            return new Deadline(taskDescription, by, isDone);
        default:
            int secondDividerIndex = taskDescription.indexOf("|", taskDescription.indexOf("|") + 2);
            String from = taskDescription.substring(taskDescription.indexOf("|") + 2, secondDividerIndex - 1);
            String to = taskDescription.substring(secondDividerIndex + 2);
            taskDescription = taskDescription.substring(0, taskDescription.indexOf("|") - 1);
            return new Event(taskDescription, from, to, isDone);
        }
    }

    //TODO: update the file after each task creation/modification

    /**
     * Saves the specified task list to the specified file path. Create the file if necessary.
     * @throws IOException
     */
    public void save(TaskList taskList) throws IOException {
        String txt = taskList.getTasksTxt();
        if (txt.isEmpty()) {
            return;
        }
        new File(filePath).createNewFile();
        FileWriter fw = new FileWriter(filePath);
        fw.write(taskList.getTasksTxt());
        fw.close();
    }
}
