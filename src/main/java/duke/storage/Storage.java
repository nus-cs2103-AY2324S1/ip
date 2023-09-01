package duke.storage;

import duke.tasks.TaskList;
import duke.tasks.DeadlineTask;
import duke.tasks.EventTask;
import duke.tasks.ToDoTask;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Storage {
    private TaskList taskList;
    private String path;
    public Storage(TaskList taskList, String path) {
        this.taskList = taskList;
        this.path = path;
        initialize();
    }

    //For testing purposes only
    public Storage(TaskList taskList) {
        this.taskList = taskList;
        this.path = "";
    }

    private void initialize() {
        File savedData = new File(path);
        if (savedData.exists()) {
            loadTasksFromFile(savedData, taskList);
        }
    }

    public void loadTasksFromFile(File file, TaskList taskList) {
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String taskData = scanner.nextLine();
                String[] dataSegments = taskData.split("[|]");
                switch (dataSegments.length) {
                    case 3:
                        if (dataSegments[0].equals("T")) {
                            String description = dataSegments[2];
                            Boolean done = false;
                            if (dataSegments[1].equals("1")) {
                                done = true;
                            } else if (!dataSegments[1].equals("0")) {
                                System.out.println("Task formatting error: " + taskData + " not loaded");
                                break;
                            }
                            ToDoTask newToDo = new ToDoTask(description, done);
                            taskList.addTask(newToDo);
                        } else {
                            System.out.println("Task formatting error: " + taskData + " not loaded");
                        }
                        break;
                    case 4:
                        if (dataSegments[0].equals("D")) {
                            String description = dataSegments[2];
                            Boolean done = false;
                            LocalDateTime by = LocalDateTime.parse(dataSegments[3]);
                            if (dataSegments[1].equals("1")) {
                                done = true;
                            } else if (!dataSegments[1].equals("0")) {
                                System.out.println("Task formatting error: " + taskData + " not loaded");
                                break;
                            }
                            DeadlineTask newDeadline = new DeadlineTask(description, by, done);
                            taskList.addTask(newDeadline);
                        } else {
                            System.out.println("Task formatting error: " + taskData + " not loaded");
                        }
                        break;
                    case 5:
                        if (dataSegments[0].equals("E")) {
                            String description = dataSegments[2];
                            Boolean done = false;
                            LocalDateTime from = LocalDateTime.parse(dataSegments[3]);
                            LocalDateTime to = LocalDateTime.parse(dataSegments[4]);
                            if (dataSegments[1].equals("1")) {
                                done = true;
                            } else if (!dataSegments[1].equals("0")) {
                                System.out.println("Task formatting error: " + taskData + " not loaded");
                                break;
                            }
                            EventTask newEvent = new EventTask(description, from, to, done);
                            taskList.addTask(newEvent);
                        } else {
                            System.out.println("Task formatting error: " + taskData + " not loaded");
                        }
                        break;
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error scanning file!");
        }
    }

    /* Tasks are saved in the following format:
        {TaskType: T/D/E} | {Done: 0/1} | Description | from/by date | to date
     */
    public void saveData() {
        File dataFolder = new File("./data");
        if (!dataFolder.exists()) {
            if (dataFolder.mkdir()) {
                // System.out.println("Data folder created successfully!");
            } else {
                System.out.println("Error creating data folder... Tasks not saved.");
            }
        }

        File savedData = new File(path);
        try {
            FileWriter writer = new FileWriter(savedData, false);
            savedData.createNewFile();
            writer.write(taskList.getTaskListData());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving data... Tasks not saved.");
        }
    }

    //For testing purposes
    public TaskList getTaskList() {
        return taskList;
    }
}
