package duke.util;

import duke.exception.InvalidDateException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.time.LocalDate;

public class Storage {
    protected static String filePath;
    public static ArrayList<Task> listOfTasks;

    public Storage(String filePath) {
        Storage.filePath = filePath;
        listOfTasks = new ArrayList<>(100);
    }

    /**
     * Save a Task into Hard Disk after it has been successfully inputted by user.
     * @param task the Task that is to be saved.
     * @param isAppend a Boolean to determine if we should add a new line in the saved text file.
     */
    public static void saveTask(Task task, boolean isAppend) throws IOException {

        FileOutputStream outputStream = new FileOutputStream(new File(filePath), isAppend);
        //Use a BufferedWriter
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
        String[] saved = new String[5]; // Cannot be more than 5 separate parts. 5th part is only for Event

        //saved[0]
        if (task instanceof Deadline) {
            saved[0] = "D";
            saved[3] = ((Deadline) task).getDueDate().toString();
        } else if (task instanceof Event) {
            saved[0] = "E";
            saved[3] = ((Event) task).getStartTime().toString();
            saved[4] = ((Event) task).getEndTime().toString();
        } else {
            //duke.task.Todo duke.task
            saved[0] = "T";
        }

        //saved[1] and saved[2]
        saved[1] = task.isDone ? "1" : "0";
        saved[2] = task.getDescription();

        if (isAppend) {
            bufferedWriter.newLine();
        }
        bufferedWriter.write(String.join(" | ", saved));
        bufferedWriter.close();
    }

    /**
     * Loads tasks saved previously from Hard Disk.
     * @throws IOException throws an IO Exception if the file is corrupted or invalid.
     */
    public void loadTasks() throws IOException, InvalidDateException {
        // Use FileInputStream and BufferedReader, opposite of saveTask()
        // try-catch to check if file exists or if file is correct format
        try {
            Path directory = Path.of("./data");
            if (!Files.exists(directory)) {
                System.out.println("System Message: Directory 'data' does not exist. Creating one..." +
                        "You can view it under root directory after exiting the program this time.");
                Files.createDirectories(directory); // Create the directory if it doesn't exist
            } else {
                System.out.println("System Message: Directory 'data' exists!");
            }

            Path file = Path.of("./data/duke.txt");
            if (!Files.exists(file)) {
                System.out.println("System Message: File 'duke.txt' does not exist. Creating one..." +
                        "You can view it under 'data' directory after exiting the program this time.");
                Files.createFile(file); // Create the file if it doesn't exist
            } else {
                System.out.println("System Message: File 'duke.txt' exists! Loading past data...");
            }

            FileInputStream inputStream = new FileInputStream(new File(filePath));
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((inputStream)));
            String currentLine;

            try {
                //Recall delimiter "|" and get details of the tasks and add tasks
                while ((currentLine = bufferedReader.readLine()) != null) {
                    if (TaskList.isValidTaskLine(currentLine)) {
                        // Parse the line and create tasks
                        String[] content = currentLine.split(" \\| "); // System.out.printf("Content: %s", content);
                        String taskDescription = content[2]; // System.out.printf("Event details: %s\n", currentLine);
                        Task taskFromHardDisk;

                        // Now check which type of task it belongs to. Create the task and add task to taskList.
                        switch(content[0]) {
                        case "E":
                            if (!TaskList.isValidDate(content[3]) || !TaskList.isValidDate(content[4])) {
                                System.out.printf("Skipping line with invalid date: %s\n", currentLine);
                            } else {
                                taskFromHardDisk = new Event(taskDescription, LocalDate.parse(content[3]), LocalDate.parse(content[4]));
                                checkCompletionStatus(taskFromHardDisk, content[1]);
                                listOfTasks.add(taskFromHardDisk);
                                //Potential error for content[3]
                            }
                            break;
                        case "D":
                            if (!TaskList.isValidDate(content[3])) {
                                System.out.printf("Skipping line with invalid date: %s\n", currentLine);
                            } else {
                                taskFromHardDisk = new Deadline(taskDescription, LocalDate.parse(content[3]));
                                checkCompletionStatus(taskFromHardDisk, content[1]);
                                listOfTasks.add(taskFromHardDisk);
                                //Potential error for content[3]
                            }
                            break;
                        default:
                            taskFromHardDisk = new Todo(taskDescription);
                            checkCompletionStatus(taskFromHardDisk, content[1]);
                            listOfTasks.add(taskFromHardDisk);
                            break;
                        }

                    } else {
                        System.out.printf("Skipping corrupted line: %s\n", currentLine);
                    }
                }
            } catch (IOException e) {
                // Handle duke.exception while reading the file
                System.out.printf("Error while reading file: %s", e.getMessage());
            }
            bufferedReader.close();
        } catch (IOException e) {
            // Handle duke.exception while creating directory or file
            System.out.printf("Error while creating directory: %s", e.getMessage());
        }
    }

    /**
     * Checks whether a Task has already been done.
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
     * Clears lines of task in Hard Disk.
     * @throws IOException throws IO Exception if file format is invalid or corrupted.
     */
    protected void clearAllData() throws IOException {
        FileOutputStream outputStream = new FileOutputStream(filePath);
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter((outputStream)));
        bufferedWriter.close();
    }

    /**
     * Updates all lines of task status in Hard Disk.
     * @throws IOException throws IO Exception if file format is invalid or corrupted.
     */
    protected void updateData() throws IOException {
        for (int i = 0; i < listOfTasks.size(); i++) {
            saveTask(listOfTasks.get(i), i != 0);
        }
    }
}
