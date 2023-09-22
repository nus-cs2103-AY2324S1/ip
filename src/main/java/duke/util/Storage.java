package duke.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;

import duke.exception.InvalidDateException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Represents a Storage to store the list of tasks of a user into a file in hard disk.
 *
 * <p>CS2103T AY23/24 Semester 1
 * Individual Project
 * SeeWhyAre Bot
 * 31 Aug 2023
 *
 * @author Freddy Chen You Ren
 */
public class Storage {
    public static ArrayList<Task> listOfTasks;
    protected static String filePath;

    /**
     * Instantiates a storage with the specified file path.
     *
     * @param filePath The file path to create a file to store user data.
     */
    public Storage(String filePath) {
        Storage.filePath = filePath;
        listOfTasks = new ArrayList<>(100);
    }

    /**
     * Saves a task into hard disk after it has been successfully inputted by user.
     *
     * @param task The task that is to be saved.
     * @param isAppend A boolean to determine if we should add a new line in the saved text file.
     * @throws IOException If there are errors while saving the file.
     */
    public static void saveTask(Task task, boolean isAppend) throws IOException {
        FileOutputStream outputStream = new FileOutputStream(filePath, isAppend);
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
        String[] saved = new String[5]; // Cannot be more than 5 separate parts. 5th part is only for Event.

        if (task instanceof Deadline) {
            saved[0] = "D";
            saved[3] = ((Deadline) task).getDueDate().toString();
        } else if (task instanceof Event) {
            saved[0] = "E";
            saved[3] = ((Event) task).getStartTime().toString();
            saved[4] = ((Event) task).getEndTime().toString();
        } else {
            // default: Todo task.
            saved[0] = "T";
        }

        saved[1] = task.isDone ? "1" : "0";
        saved[2] = task.getDescription();

        bufferedWriter.write(String.join(" | ", saved));

        if (isAppend) {
            bufferedWriter.newLine();
        }

        bufferedWriter.close();
    }

    /**
     * Checks if the directory of storage data exists in the local computer running the programme.
     * If there is no such directory, alert the user and create one for the user.
     */
    public void checkDirectory() {
        try {
            Path directory = Path.of("./data");
            if (!Files.exists(directory)) {
                System.out.println("System Message: Directory 'data' does not exist. Creating one..."
                        + "You can view it under root directory after exiting the program this time.");
                Files.createDirectories(directory);
            } else {
                System.out.println("System Message: Directory 'data' exists!");
            }
        } catch (IOException e) {
            System.out.printf("Error while creating directory: %s", e.getMessage());
        }
    }

    /**
     * Checks if the file of storage data exists in the local computer running the programme.
     * If there is no such file, alert the user and create one for the user.
     */
    public void checkFile() {
        try {
            Path file = Path.of("./data/duke.txt");
            Path fileForTesting = Path.of("./data/taskListTest.txt");
            if (!Files.exists(file)) {
                System.out.println("System Message: File 'duke.txt' does not exist. Creating one..."
                        + "You can view it under 'data' directory after exiting the program this time.");
                Files.createFile(file);
            } else {
                System.out.println("System Message: File 'duke.txt' exists! Loading past data...");
            }

            if (!Files.exists(fileForTesting)) {
                System.out.println("System Message: File 'taskListTest.txt' does not exist. Creating one..."
                        + "You can view it under 'data' directory after exiting the program this time.");
                Files.createFile(fileForTesting);
            } else {
                System.out.println("System Message: File 'taskListTest.txt' exists!");
            }
        } catch (IOException e) {
            System.out.printf("Error while creating directory: %s", e.getMessage());
        }
    }

    /**
     * Loads tasks saved previously from hard disk.
     *
     * @throws IOException If the file is corrupted or invalid.
     * @throws InvalidDateException If the date format or content in the file is corrupted or invalid.
     */
    public void loadTasks() throws IOException, InvalidDateException {
        checkDirectory();
        checkFile();
        FileInputStream inputStream = new FileInputStream(filePath);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((inputStream)));
        String currentLine;
        try {
            while ((currentLine = bufferedReader.readLine()) != null) {
                if (!TaskList.isValidTaskLine(currentLine)) {
                    System.out.printf("Skipping corrupted line: %s\n", currentLine);
                    continue;
                }
                // Parse the line. Check which type of task it belongs to. Create task and put it in list of tasks.
                String[] content = currentLine.split(" \\| ");
                String taskDescription = content[2];
                switch(content[0]) {
                case "E":
                    loadEventTasks(content, taskDescription, currentLine);
                    break;
                case "D":
                    loadDeadlineTasks(content, taskDescription, currentLine);
                    break;
                default:
                    loadTodoTasks(content, taskDescription);
                    break;
                }
            }
        } catch (IOException e) {
            System.out.printf("Error while reading file: %s", e.getMessage());
        }
        bufferedReader.close();
    }

    private void loadEventTasks(String[] content, String taskDescription, String currentLine) {
        if (!TaskList.isValidDate(content[3]) || !TaskList.isValidDate(content[4])) {
            System.out.printf("Skipping line with invalid date: %s\n", currentLine);
        } else {
            Event taskFromHardDisk = new Event(taskDescription, LocalDate.parse(content[3]),
                    LocalDate.parse(content[4]));
            checkCompletionStatus(taskFromHardDisk, content[1]);
            listOfTasks.add(taskFromHardDisk);
        }
    }

    private void loadDeadlineTasks(String[] content, String taskDescription, String currentLine) {
        if (!TaskList.isValidDate(content[3])) {
            System.out.printf("Skipping line with invalid date: %s\n", currentLine);
        } else {
            Deadline taskFromHardDisk = new Deadline(taskDescription, LocalDate.parse(content[3]));
            checkCompletionStatus(taskFromHardDisk, content[1]);
            listOfTasks.add(taskFromHardDisk);
        }
    }

    private void loadTodoTasks(String[] content, String taskDescription) {
        Todo taskFromHardDisk = new Todo(taskDescription);
        checkCompletionStatus(taskFromHardDisk, content[1]);
        listOfTasks.add(taskFromHardDisk);
    }

    /**
     * Checks whether a task has already been done.
     *
     * @param task The task whose completion status is to be checked.
     * @param completionStatus The completion status read from memory. 0 means not done, 1 means done.
     */
    protected void checkCompletionStatus(Task task, String completionStatus) {
        if (completionStatus.equals("1")) {
            task.markAsDone();
        } else {
            task.markAsNotDone();
        }
    }

    /**
     * Clears lines of task in hard disk.
     *
     * @throws IOException If file format is invalid or corrupted.
     */
    protected void clearAllData() throws IOException {
        FileOutputStream outputStream = new FileOutputStream(filePath);
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter((outputStream)));
        bufferedWriter.close();
    }

    /**
     * Updates all lines of task status in hard disk.
     *
     * @throws IOException If file format is invalid or corrupted.
     */
    protected void updateData() throws IOException {
        for (int i = 0; i < listOfTasks.size(); i++) {
            saveTask(listOfTasks.get(i), i != 0);
        }
    }
}
