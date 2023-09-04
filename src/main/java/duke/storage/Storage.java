package duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

/**
 * Manages the reading and updating of tasks from/to a file.
 */
public class Storage {
    protected String filePath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The path to the file used for storage.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    private void readFile(File f) throws IOException {
        if (!f.exists()) {
            f.getParentFile().mkdirs(); // Creates parent directories if they don't exist
            f.createNewFile(); // Creates the file itself
        }
    }

    /**
     * Loads tasks from the file and returns them as an ArrayList.
     *
     * @return An ArrayList of Task objects loaded from the file.
     * @throws DukeException If there's an error while loading tasks.
     */
    public ArrayList<Task> load() throws DukeException {
        File f = new File(this.filePath);
        try {
            readFile(f);
            Scanner s = new Scanner(f);
            ArrayList<Task> taskArr = new ArrayList<>();

            while (s.hasNext()) {
                String str = s.nextLine();
                String[] task = str.split(" \\| ");
                switch (task[0]) {
                case "T":
                    Todo todo = new Todo(task[2]);
                    if (task[1].equals("1")) {
                        todo.markAsDone();
                    }
                    taskArr.add(todo);
                    break;
                case "D":
                    Deadline deadline;
                    try {
                        DateTimeFormatter altInputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                        LocalDateTime byDateTime = LocalDateTime.parse(task[3], altInputFormatter);
                        deadline = new Deadline(task[2], byDateTime);
                        if (task[1].equals("1")) {
                            deadline.markAsDone();
                        }
                        taskArr.add(deadline);
                    } catch (DateTimeParseException e) {
                        System.out.println("Invalid Date Time: " + e.getMessage());
                    }
                    break;
                case "E":
                    String[] time = task[3].split(" to ");
                    Event eventTask = new Event(task[2],
                            LocalDateTime.parse(time[0], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                            LocalDateTime.parse(time[1], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
                    if (task[1].equals("1")) {
                        eventTask.markAsDone();
                    }
                    taskArr.add(eventTask);
                    break;
                default:
                }
            }
            return taskArr;
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }

    /**
     * Updates the contents of the file with the tasks from the provided TaskList.
     *
     * @param taskList The TaskList containing tasks to be updated in the file.
     */
    public void updateFileContents(TaskList taskList) {
        try {
            FileWriter file = new FileWriter(filePath);
            file.write("");
            FileWriter fw = new FileWriter(filePath);
            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.get(i);
                if (task instanceof Todo) {
                    String taskType = task.toString().substring(1, 2); // Extract "T"
                    String taskStatus = task.toString().substring(4, 5);
                    String description = task.toString().substring(7);
                    String convertedTask = taskType + " | " + (taskStatus.equals(" ") ? "0" : "1") + " | "
                            + description;
                    fw.write(convertedTask + "\n");
                } else if (task instanceof Deadline) {
                    String originalTask = task.writeFileString();
                    String taskType = originalTask.substring(1, 2); // Extract "D"
                    String taskStatus = originalTask.substring(4, 5); // Extract "X"
                    String description = originalTask.substring(7, originalTask.indexOf(" (by:"));
                    String date = originalTask.substring(originalTask.indexOf("(by: ") + 5,
                            originalTask.indexOf(")"));

                    String convertedTask = taskType + " | " + (taskStatus.equals("X") ? "1" : "0") + " | "
                            + description + " | " + date;
                    fw.write(convertedTask + "\n");
                } else if (task instanceof Event) {
                    String originalTask = task.writeFileString();
                    String taskType = originalTask.substring(1, 2); // Extract "E"
                    String taskStatus = originalTask.substring(4, 5); // Extract " "
                    String description = originalTask.substring(7, originalTask.indexOf(" (from:"));
                    String startTime = originalTask.substring(originalTask.indexOf("(from: ") + 7,
                            originalTask.indexOf(" to:"));
                    String endTime = originalTask.substring(originalTask.indexOf("to: ") + 4,
                            originalTask.indexOf(")"));
                    String convertedTask = taskType + " | " + (taskStatus.equals(" ") ? "0" : "1") + " | "
                            + description + " | " + startTime + " to " + endTime;
                    fw.write(convertedTask + "\n");
                }
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

}
