import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskList {
    static File taskList;
    static int taskCount = 0;

    public TaskList(File file) {
        taskList = file;
        taskList.deleteOnExit();
    }
    public TaskList() {
    }
    static ArrayList<Task> tasks = new ArrayList<>();



    public void printFileContents() {
        try {
            Scanner s = new Scanner(taskList);
            while (s.hasNext()) {
                System.out.println(s.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

    }

    public void writeToFile() {
        try {
            FileWriter fw = new FileWriter(taskList.getPath());
            fw.write(displayList());
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public static String displayList() {
        StringBuilder res = null;
        try {
            if (taskCount == 0) {
                throw new DukeException("Error: There are no items in the list!");
            }
            res = new StringBuilder(Ui.line);
            for (int i = 0; i < taskCount; i++) {
                Task task = tasks.get(i);
                int index = i + 1;
                res.append(index).append(task.getTask()).append("\n");
            }
            res.append(Ui.line);
        } catch (DukeException emptyList) {
            res = new StringBuilder(Ui.line + emptyList.getMessage() + "\n" + Ui.line);
        }
        return res.toString();
    }

    public void delete(String input) {
        int taskIndex = Integer.parseInt(input.substring(7)) - 1;
        try {
            if (taskIndex > taskCount || taskIndex < 0) {
                throw new DukeException("Error: Invalid Task Index!");
            } else {
                int remainingTasks = taskCount - 1;
                String response = Ui.line + "Got it! I've removed this task:" + "\n" + tasks.get(taskIndex).toString() + "\n"
                        + "You now have " + remainingTasks + " task(s) in the list" + "\n" + Ui.line;
                tasks.remove(taskIndex);
                if (taskCount > 0) {
                    taskCount--;
                }
                System.out.println(response);
                writeToFile();
            }
        } catch (DukeException exception) {
            System.out.println(Ui.line + exception.getMessage() + "\n" + Ui.line);
        }
    }

    public void mark(String input) {
        int taskIndex = Integer.parseInt(input.substring(5)) - 1;
        try {
            if (taskIndex > taskCount || taskIndex < 0) {
                throw new DukeException("Error: Invalid Task Index!");
            } else if (tasks.get(taskIndex).isMarked()) {
                throw new DukeException("Error: Task is already completed!");
            } else {
                tasks.get(taskIndex).mark();
                writeToFile();
            }
        } catch (DukeException exception) {
            System.out.println(Ui.line + exception.getMessage() + "\n" + Ui.line);
        }
    }

    public void unMark(String input) {
        int taskIndex = Integer.parseInt(input.substring(7)) - 1;
        try {
            if (taskIndex > taskCount || taskIndex < 0) {
                throw new DukeException("Error: Invalid Task Index!");
            } else if (!tasks.get(taskIndex).isMarked()) {
                throw new DukeException("Error: Task is already marked as incomplete!");
            } else {
                tasks.get(taskIndex).unMark();
                writeToFile();
            }
        } catch (DukeException exception) {
            System.out.println(Ui.line + exception.getMessage() + "\n" + Ui.line);
        }
    }

    public static void addToList(Task task, ArrayList<Task> tasks, int taskId) {
        int taskCount = taskId + 1;
        String response = Ui.line + "Got it! I've added this task:" + "\n" + task.toString() + "\n"
                + "You now have " + taskCount + " task(s) in the list" + "\n" + Ui.line;
        tasks.add(taskId, task);
        if (taskCount < tasks.size()) {
            taskCount++;
            taskId++;
        }
        System.out.println(response);
    }
}
