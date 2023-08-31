package duke;

import duke.task.Task;
import duke.task.Todo;
import duke.task.Deadline;
import duke.task.Event;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Storage {

    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void saveTasksToFile(TaskList task_List) {
        clearTasksInFile(filePath);
        try (FileWriter writer = new FileWriter(filePath)) {
            List<Task> taskList = task_List.getTask_List();
            for (Task task : taskList) {
                writer.write(task.toFileString() + "\n");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void reloadTasksToFile(List<Task> task_List) {
        clearTasksInFile(filePath);
        try (FileWriter writer = new FileWriter(filePath)) {
            for (Task task : task_List) {
                writer.write(task.toFileString() + "\n");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Task> loadTaskFromFile() throws MYBotExceptions {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String inputLines;
            List<Task> task_List = new ArrayList<>();

            while((inputLines = reader.readLine()) != null) {
                String [] taskSplit = inputLines.split("\\|");
                String taskType = taskSplit[0];
                String status = taskSplit[1];
                String taskDescription = taskSplit[2];

                if (taskType.equals("T")) {
                    task_List.add(new Todo(taskDescription, status));
                } else if (taskType.equals("D")) {
                    String by = taskSplit[3];
                    task_List.add(new Deadline(taskDescription, by, status));
                } else if (taskType.equals("E")) {
                    String from = taskSplit[3];
                    String to =  taskSplit[4];
                    task_List.add(new Event(taskDescription, from, to, status));
                }
            }
            reloadTasksToFile(task_List);
            return task_List;
        } catch (IOException e) {
        } catch (ArrayIndexOutOfBoundsException e) {
        } catch (Exception e) {
            System.out.println("The file to store your tasks entered cannot be accessed.");
        }
        return null;
    }

    public void clearTasksInFile(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
