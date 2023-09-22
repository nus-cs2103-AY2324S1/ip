package leon;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;

/**
 * The {@code Storage} class. Deals with writing to, and reading from, text files.
 */
public class Storage {
    private final TaskList tasks;

    public Storage(leon.TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Greets the user and creates the requisite folder and text file, if required.
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public void launchOnStart() {
        File f = new File("./data");
        f.mkdirs();
        try {
            File file = new File("./data/tasks.txt");
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("Due to technical issues, I'm only available in guest mode.%n"
                + "I sincerely apologise for the inconvenience caused.");
        }
    }

    /**
     * Reads previously saved tasks from the specified {@code filePath}, parses them and
     * directly adds them to the list.
     *
     * @param filePath Relative path of the text file containing details on the tasks.
     * @throws FileNotFoundException    When the system is unable to find the specified file.
     * @throws IllegalArgumentException When the system is unable to parse the saved tasks,
     *                                  possibly due to the file being corrupted.
     */
    public void readTasksFromDisk(String filePath) throws FileNotFoundException, IllegalArgumentException {
        File f = new File(filePath);
        Scanner sc = new Scanner(f);
        while (sc.hasNext()) {
            String[] args = sc.nextLine().split("/", -1);
            readArguments(args);
        }
    }

    /**
     * Helper function to parse the string arguments from the text file.
     *
     * @param args Array of string arguments that represent details of a task.
     */
    public void readArguments(String[] args) {
        int numOfArgs = args.length;
        leon.TaskList.TaskType type;
        try {
            type = TaskList.TaskType.valueOf(args[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            return;
        }
        String details = args[1];
        boolean isCompleted = args[2].equals("Y");
        if (numOfArgs == 3) {
            addTaskToList(type, details, isCompleted);
        } else if (numOfArgs == 4) {
            LocalDateTime dueDateTime = LocalDateTime.parse(args[3]);
            addTaskToList(type, details, isCompleted, dueDateTime);
        } else {
            LocalDateTime startDateTime = LocalDateTime.parse(args[3]);
            LocalDateTime endDateTime = LocalDateTime.parse(args[4]);
            addTaskToList(type, details, isCompleted, startDateTime, endDateTime);
        }
    }

    /**
     * Helper function to add the stored tasks in the {@code TaskList}.
     *
     * @param taskType       Type of task.
     * @param details        Details of task.
     * @param isCompleted    Completion status of task.
     * @param localDateTimes Variable number of LocalDateTimes.
     */
    public void addTaskToList(TaskList.TaskType taskType, String details, boolean isCompleted,
                              LocalDateTime... localDateTimes) {
        switch (taskType) {
        case TASK:
            Task t = new Task(details, isCompleted);
            tasks.add(t);
            break;
        case TODO:
            ToDo todo = new ToDo(details, isCompleted);
            tasks.add(todo);
            break;
        case DEADLINE:
            Deadline d = new Deadline(details, isCompleted, localDateTimes[0]);
            tasks.add(d);
            break;
        case EVENT:
            Event e = new Event(details, isCompleted, localDateTimes[0], localDateTimes[1]);
            tasks.add(e);
            break;
        default:
            break;
        }
    }

    /**
     * Saves the tasks in the {@code TaskList} in the text file specified by the {@code filePath}.
     *
     * @param filePath Relative path of the text file to be written to.
     * @param tasks    {@code TaskList} containing the current tasks.
     * @throws IOException When the {@code FileWriter} is unable to write to the file.
     */
    public void saveTasksToDisk(String filePath, leon.TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        BufferedWriter bw = new BufferedWriter(fw);
        try {
            for (int i = 0; i < tasks.getNumOfTasks(); i++) {
                Task t = tasks.get(i);
                bw.write(t.toFileSaveFormat());
                bw.newLine();
            }
            bw.flush();
            bw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}
