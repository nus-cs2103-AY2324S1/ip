import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;

public class TaskListManager {
    private File file;
    private String taskDataPath;
    private TaskList taskList;

    public TaskListManager(String directoryPath, String fileName, TaskList taskList) {
        this.taskList = taskList;
        this.taskDataPath = directoryPath + "/" + fileName;

        try {
            if (new File(directoryPath).mkdirs()) {
                System.out.println("Directories are created.");
            } else {
                System.out.println("Directories already exist.");
            }

            this.file = new File(taskDataPath);

            if (this.file.createNewFile()) {
                System.out.println("File is created: " + this.file.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void saveTask(ArrayList<Task> tasks) {
        try {
            FileWriter w = new FileWriter(taskDataPath);
            for (Task task : tasks) {
                w.write(task.toString() + "\n");
            }
            w.close();
        } catch (IOException e) {
            System.out.println("Error occurred when saving tasks to file.");
        }
    }

    public void loadTasks() {
        try {
            File file = new File(taskDataPath);
            if (!file.exists()) {
                System.out.println("No such file");
                return;
            }
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                String taskData = fileScanner.nextLine();
                parseTask(taskData);
            }
            fileScanner.close();
        } catch (IOException e) {
            System.out.println("Error occurred when loading tasks from file.");
        } catch (DukeException e) {
            System.out.println("File is corrupted :(");
        }
    }

    private void parseTask(String taskData) throws FileNotFoundException, DukeException {
        try {
            String taskType = taskData.substring(1, 2);
            boolean isDone = taskData.charAt(4) == 'X';
            String taskInfo = taskData.substring(7);
            Task taskToAdd = null;

            switch (taskType) {
                case "A":
                    taskToAdd = new Add(taskInfo);
                    break;
                case "T":
                    taskToAdd = new ToDo(taskInfo);
                    break;
                case "D":
                    taskInfo = taskInfo.substring(0, taskInfo.indexOf("(") - 1);
                    String xtraInfo = taskData.substring(taskData.indexOf("(") + 1, taskData.indexOf(")"));
                    String[] deadLineInfo = xtraInfo.split(": ");
                    String by = deadLineInfo[1];
                    taskToAdd = new DeadLine(taskInfo, by);
                    break;
                case "E":
                    taskInfo = taskInfo.substring(0, taskInfo.indexOf('(') - 1);
                    String addInfo = taskData.substring(taskData.indexOf('(') + 1, taskData.indexOf(')'));
                    String[] eventInfo = addInfo.split(": ");
                    String from = eventInfo[1].substring(0, eventInfo[1].length() - 2).trim();
                    String to = eventInfo[2].trim();
                    taskToAdd = new Event(taskInfo, from, to);
                    break;
                default:
                    throw new DukeException("File is corrupted!");
            }
            if (taskToAdd != null) {
                if (isDone) {
                    taskToAdd.isCompleted();
                }
                taskList.addTask(taskToAdd);
            }
        } catch (Exception e) {
            taskList.addTask(new Task("(CORRUPTED) " + taskData));
        }
    }

}