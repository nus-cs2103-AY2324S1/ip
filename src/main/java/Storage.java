import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

/**
 * Manages the storage of tasks in a txt file.
 */
public class Storage {
    protected String filePath;
    protected String directoryPath;

    /**
     * Initialises a new instance of the Storage class.
     *
     * @param directoryPath The directory where the storage file is located or should be created.
     * @param filePath The file path of the storage file.
     */
    public Storage(String directoryPath, String filePath) {
        this.directoryPath = directoryPath;
        this.filePath = filePath;
        this.setupTasks();
    }


    /**
     * Updates the storage file with the tasks from the provided TaskList.
     *
     * @param tasks The TaskList containing tasks to be saved to the storage file.
     */
    public void updateStorage(TaskList tasks) {
        try {
            FileWriter writer = new FileWriter(filePath);
            for (int i = 0; i < tasks.getSize(); i++) {
                writer.write(tasks.getTask(i) + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e){
            e.printStackTrace();
            System.out.println(e);
        }
    }

    /**
     * Sets up the tasks by reading from the storage file or creating a new one if it doesn't exist.
     *
     * @return A TaskList containing the tasks read from the storage file, or an empty TaskList if the file is new.
     */
    private TaskList setupTasks() {
        TaskList tasks = new TaskList();
        try {
            File directory = new File(directoryPath);
            if (!directory.exists()) {
                directory.mkdir();
            }
            File taskFile = new File(filePath);
            if (taskFile.createNewFile()) {
                //new file created
            } else {
                tasks = getTasks();
            }

        } catch (IOException e) {
            System.out.println("Error occurred");
            e.printStackTrace();
        } finally {
            return tasks;
        }
    }

    /**
     * Retrieves tasks from the storage file and returns them in a TaskList.
     *
     * @return A TaskList containing tasks read from the storage file.
     */
    public TaskList getTasks() {
        TaskList taskList = new TaskList();
        try {
            Scanner scanner = new Scanner(new File(filePath));

            while (scanner.hasNextLine()) {
                String str = scanner.nextLine();
                char taskType = str.charAt(1);
                boolean isMarked = str.charAt(4) == 'X';
                String description = str.split("] ")[1];
                switch (taskType) {
                case 'T':
                    ToDo taskT = new ToDo(description);
                    if (isMarked) {
                        taskT.markAsDone();
                    }
                    taskList.addTask(taskT);
                    break;
                case 'D':
                    description = description.split(" \\(by: ")[0];
                    String by = str.split(" \\(by: ")[1];
                    LocalDate date = LocalDate.parse(by.split(" ")[0]);
                    LocalTime time = LocalTime.parse(by.split(" ")[1].replaceAll(".$", ""));
                    Deadline taskD = new Deadline(description, date, time);
                    if (isMarked) {
                        taskD.markAsDone();
                    }
                    taskList.addTask(taskD);
                    break;
                case 'E':
                    description = description.split(" \\(from: ")[0];
                    String from = String.join("", str.split("\\(from: ")[1]).split(" to: ")[0];
                    String to = str.split(" to: ")[1];
                    LocalDate fromDate = LocalDate.parse(from.split(" ")[0]);
                    LocalTime fromTime = LocalTime.parse(from.split(" ")[1]);
                    LocalDate toDate = LocalDate.parse(to.split(" ")[0]);
                    LocalTime toTime = LocalTime.parse(to.split(" ")[1].replaceAll(".$", ""));
                    Event taskE = new Event(description, fromDate, fromTime, toDate, toTime);
                    if (isMarked) {
                        taskE.markAsDone();
                    }
                    taskList.addTask(taskE);
                    break;

                }

            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            return taskList;
        }
    }

}
