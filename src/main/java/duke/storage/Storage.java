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
        return loadTasks(f);
    }

    private ArrayList<Task> loadTasks(File f) throws DukeException {
        try {
            return processFile(f);
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }

    private ArrayList<Task> processFile(File f) throws IOException {
        readFile(f);
        Scanner s = new Scanner(f);
        ArrayList<Task> taskArr = new ArrayList<>();

        while (s.hasNext()) {
            String str = s.nextLine();
            String[] task = str.split(" \\| ");
            String taskId = task[0]; // T or D or E
            String taskDescription = task[2];
            boolean isMark = task[1].equals("1");
            switch (taskId) {
            case "T":
                processTodo(taskArr, taskDescription, isMark);
                break;
            case "D":
                processDeadline(taskArr, task, taskDescription, isMark);
                break;
            case "E":
                processEvent(taskArr, task, taskDescription, isMark);
                break;
            default:
            }
        }
        return taskArr;
    }

    private static void processEvent(ArrayList<Task> taskArr, String[] task, String taskDescription, boolean isMark) {
        String[] time = task[3].split(" to ");
        Event eventTask = new Event(taskDescription,
                LocalDateTime.parse(time[0], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                LocalDateTime.parse(time[1], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        if (isMark) {
            eventTask.markAsDone();
        }
        taskArr.add(eventTask);
    }

    private static void processDeadline(ArrayList<Task> taskArr, String[] task, String taskDescription, boolean isMark) {
        Deadline deadline;
        try {
            DateTimeFormatter altInputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime byDateTime = LocalDateTime.parse(task[3], altInputFormatter);
            deadline = new Deadline(taskDescription, byDateTime);
            if (isMark) {
                deadline.markAsDone();
            }
            taskArr.add(deadline);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid Date Time: " + e.getMessage());
        }
    }

    private static void processTodo(ArrayList<Task> taskArr, String taskDescription, boolean isMark) {
        Todo todo = new Todo(taskDescription);
        if (isMark) {
            todo.markAsDone();
        }
        taskArr.add(todo);
    }

    /**
     * Updates the contents of the file with the tasks from the provided TaskList.
     *
     * @param taskList The TaskList containing tasks to be updated in the file.
     */
    public void updateFileContents(TaskList taskList) {
        try {
            FileWriter fw = initialiseFileWriter();
            processFileContent(taskList, fw);
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    private static void processFileContent(TaskList taskList, FileWriter fw) throws IOException {
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            processIndividualTask(fw, task);
        }
    }

    private static void processIndividualTask(FileWriter fw, Task task) throws IOException {
        if (task instanceof Todo) {
            loadTodoTask(fw, task);
        } else if (task instanceof Deadline) {
            loadDeadlineTask(fw, task);
        } else if (task instanceof Event) {
            loadEventTask(fw, task);
        }
    }

    private static void loadEventTask(FileWriter fw, Task task) throws IOException {
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

    private static void loadDeadlineTask(FileWriter fw, Task task) throws IOException {
        String originalTask = task.writeFileString();
        String taskType = originalTask.substring(1, 2); // Extract "D"
        String taskStatus = originalTask.substring(4, 5); // Extract "X"
        String description = originalTask.substring(7, originalTask.indexOf(" (by:"));
        String date = originalTask.substring(originalTask.indexOf("(by: ") + 5,
                originalTask.indexOf(")"));

        String convertedTask = taskType + " | " + (taskStatus.equals("X") ? "1" : "0") + " | "
                + description + " | " + date;
        fw.write(convertedTask + "\n");
    }

    private static void loadTodoTask(FileWriter fw, Task task) throws IOException {
        String taskType = task.toString().substring(1, 2); // Extract "T"
        String taskStatus = task.toString().substring(4, 5);
        String description = task.toString().substring(7);
        String convertedTask = taskType + " | " + (taskStatus.equals(" ") ? "0" : "1") + " | "
                + description;
        fw.write(convertedTask + "\n");
    }

    private FileWriter initialiseFileWriter() throws IOException {
        FileWriter file = new FileWriter(filePath);
        file.write("");
        FileWriter fw = new FileWriter(filePath);
        return fw;
    }

}
