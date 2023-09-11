import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

// deals with loading tasks from the file and saving tasks in the file
public class Storage {
    private String filepath;
    public Storage(String filepath) {
        this.filepath = filepath;
    }

    public void saveTasksToDisk(TaskList taskList) throws IOException {
        File file = new File(this.filepath);
        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    System.out.println("File created at: " + this.filepath);
                } else {
                    System.err.println("Failed to create the file.");
                }
            } catch (IOException e) {
                System.err.println("An error occurred while creating the file: " + e.getMessage());
            }
        }

        String data = "";
        for (int i = 0; i < taskList.getSize(); i++) {
            Task task = taskList.getTask(i);
            data += task.saveTask();
        }

        try {
            FileWriter fw = new FileWriter(file);
            fw.write(data);
            fw.close();
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    public ArrayList<Task> loadTasksFromDisk() throws FileNotFoundException {
        ArrayList<Task> taskList = new ArrayList<Task>();

        File file = new File(this.filepath);
        if (file.exists()) {
            try {
                Scanner sc = new Scanner(file);
                while (sc.hasNext()) {
                    String storedTaskDetails = sc.nextLine();
                    String[] taskDetails = storedTaskDetails.split("\\s+\\|\\s+");
                    String taskType = taskDetails[0];
                    Task task;
                    if (taskType.equals("T")) {
                        task = new ToDo(taskDetails[2]);
                        task.setDone(taskDetails[1] == "1");
                        taskList.add(task);
                    } else if (taskType.equals("D")) {
                        String byInput = taskDetails[3]; // ISO-8601 e.g. 2023-09-06T14:30
                        LocalDateTime by = LocalDateTime.parse(byInput);
                        // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                        // LocalDateTime by = LocalDateTime.parse(byInput, formatter);
                        task = new Deadline(taskDetails[2], by);
                        task.setDone(taskDetails[1] == "1");
                        taskList.add(task);
                    } else if (taskType.equals("E")) {
                        LocalDateTime from = LocalDateTime.parse(taskDetails[3]);
                        LocalDateTime to = LocalDateTime.parse(taskDetails[4]);
                        task = new Event(taskDetails[2], from, to);
                        task.setDone(taskDetails[1] == "1");
                        taskList.add(task);
                    }
                }
            } catch (IOException e) {
                System.err.println("An error occurred while reading the file: " + e.getMessage());
            }
        } else {
            try {
                file.createNewFile();
                System.out.println("File created at: " + this.filepath);
            } catch (IOException e) {
                System.out.println("File creating error");
                System.out.println(e.getMessage());
            }
        }
        System.out.printf("You have %d saved tasks.\n", taskList.size());
        // TaskList tasks = new TaskList(taskList);
        return taskList;
    }


    /* to be fixed
    public Task processTaskDetails(String storedTaskDetails) {
        String[] taskDetails = storedTaskDetails.split("\\s+\\|\\s+");

        Task storedTask;

        if (taskDetails[0].equals("T")) {
            storedTask = new ToDo(taskDetails[2]);
            storedTask.setDone(taskDetails[1] == "1");
        } else if (taskDetails[0].equals("D")) {
            String byInput = taskDetails[3]; // ISO-8601 e.g. 2023-09-06T14:30
            LocalDateTime by = LocalDateTime.parse(byInput);
            // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            // LocalDateTime by = LocalDateTime.parse(byInput, formatter);
            storedTask = new Deadline(taskDetails[2], by);
            storedTask.setDone(taskDetails[1] == "1");
        } else if (taskDetails[0].equals("E")) {
            storedTask = new Event(taskDetails[2], taskDetails[3], taskDetails[4]);
            storedTask.setDone(taskDetails[1] == "1");
        }
        return storedTask;
    }

    */

}
