import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class Storage {
    protected String filePath;
    protected ArrayList<Task> taskList;

    Storage(String filePath) {
        this.filePath = filePath;
        this.taskList = new ArrayList<>(100);
    }

    /**
     * Save a Task after it has been successfully inputted by user.
     * @param task the Task that is to be saved.
     * @param isAppend a Boolean to determine if we should add a new line in the saved text file.
     */
    protected void saveTask(Task task, boolean isAppend) throws IOException {

        FileOutputStream outputStream = new FileOutputStream(new File(this.filePath), isAppend);
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
            //Todo task
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
    protected void loadTasks() throws IOException, InvalidDateException {
        // Use FileInputStream and BufferedReader, opposite of saveTask()
        // try-catch to check if file exists or if file is correct format
        try {
            Path directory = Path.of("./data");
            if (!Files.exists(directory)) {
                System.out.println("System Message: Directory 'data' does not exist. Creating one...");
                Files.createDirectories(directory); // Create the directory if it doesn't exist
            }
            System.out.println("System Message: Directory 'data' exists!");

            Path file = Path.of("./data/duke.txt");
            if (!Files.exists(file)) {
                System.out.println("System Message: File 'duke.txt' does not exist. Creating one...");
                Files.createFile(file); // Create the file if it doesn't exist
            }
            System.out.println("System Message: File 'duke.txt' exists! Loading past data...");

            FileInputStream inputStream = new FileInputStream(new File(this.filePath));
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((inputStream)));
            String currentLine;

            try {
                //Recall delimiter "|" and get details of the tasks and add tasks
                while ((currentLine = bufferedReader.readLine()) != null) {
                    if (TaskList.isValidTaskLine(currentLine)) {
                        // Parse the line and create tasks
                        String[] content = currentLine.split(" \\| ");
                        //System.out.printf("Content: %s", content);
                        String taskDescription = content[2];
                        //System.out.printf("Event details: %s\n", currentLine);
                        Task taskFromHardDisk;

                        // Now check which type of task it belongs to
                        // Create the task and add task to taskList
                        switch(content[0]) {
                        case "E":
                            if (!TaskList.isValidDate(content[3]) || !TaskList.isValidDate(content[4])) {
                                System.out.printf("Skipping line with invalid date: %s\n", currentLine);
                            } else {
                                taskFromHardDisk = new Event(taskDescription, LocalDate.parse(content[3]), LocalDate.parse(content[4]));
                                //Check if task is done
                                if (content[1].equals("1")) {
                                    taskFromHardDisk.markAsDone();
                                } else {
                                    taskFromHardDisk.markAsNotDone();
                                }
                                taskList.add(taskFromHardDisk);
                                //Potential error for content[3]
                            }
                            break;
                        case "D":
                            if (!TaskList.isValidDate(content[3])) {
                                System.out.printf("Skipping line with invalid date: %s\n", currentLine);
                            } else {
                                taskFromHardDisk = new Deadline(taskDescription, LocalDate.parse(content[3]));
                                //Check if task is done
                                if (content[1].equals("1")) {
                                    taskFromHardDisk.markAsDone();
                                } else {
                                    taskFromHardDisk.markAsNotDone();
                                }
                                taskList.add(taskFromHardDisk);
                                //Potential error for content[3]
                            }
                            break;
                        default:
                            taskFromHardDisk = new Todo(taskDescription);
                            //Check if task is done
                            if (content[1].equals("1")) {
                                taskFromHardDisk.markAsDone();
                            } else {
                                taskFromHardDisk.markAsNotDone();
                            }
                            taskList.add(taskFromHardDisk);
                            break;
                        }

                    } else {
                        System.out.printf("Skipping corrupted line: %s\n", currentLine);
                    }
                }
            } catch (IOException e) {
                // Handle exception while reading the file
                System.out.printf("Error while reading file: %s", e.getMessage());
            }
            bufferedReader.close();
        } catch (IOException e) {
            // Handle exception while creating directory or file
            System.out.printf("Error while creating directory: %s", e.getMessage());
        }
    }

    /**
     * Clears lines of task status in Hard Disk.
     * @throws IOException throws IO Exception if file format is invalid or currupted.
     */
    protected void clearAllData() throws IOException {
        FileOutputStream outputStream = new FileOutputStream(this.filePath);
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter((outputStream)));
        bufferedWriter.close();
    }

    /**
     * Updates all lines of task status in Hard Disk.
     * @throws IOException throws IO Exception if file format is invalid or currupted.
     */
    protected void updateData() throws IOException {
        for (int i = 0; i < this.taskList.size(); i++) {
            if (i != 0) {
                saveTask(this.taskList.get(i), true);
            } else {
                saveTask(this.taskList.get(i), false);
            } //BUG FOR EVENT TASK, End Time get erased, Type kept getting changed to Deadline
        }
    }
}
