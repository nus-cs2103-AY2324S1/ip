package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The TaskList class manages a list of tasks and provides methods to interact with and manipulate the tasks.
 */
public class TaskList {
    static File taskList;
    static int taskCount = 0;

    /**
     * Constructs a TaskList instance with the specified file containing task data.
     *
     * @param file The file containing task data.
     * @throws FileNotFoundException If the specified file is not found.
     */
    public TaskList(File file) throws FileNotFoundException {
        taskList = file;
        taskList.deleteOnExit();
    }

    /**
     * Constructs a TaskList instance with a default file path for task data.
     */
    public TaskList() {
        taskList = new File("./src/main/data/tasklist.txt");
        taskList.deleteOnExit();
    }
    static ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Prints the contents of the task list.
     */
    public void printFileContents() {
        try {
            Scanner s = new Scanner(taskList);
            while (s.hasNext()) {
                System.out.println(s.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: There are no items in the list!");
        }
    }

    public void printFileContents(String itemToFind) {
        try {
            Scanner s = new Scanner(taskList);
            while (s.hasNext()) {
                String nextLine = s.nextLine();
                if (nextLine.contains(itemToFind)){
                    System.out.println(nextLine);
                } else {
                    continue;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: There are no items in the list!");
        }
    }

    /**
     * Writes the task list contents to the file.
     */
    public void writeToFile() {
        try {
            FileWriter fw = new FileWriter(taskList.getPath());
            fw.write(displayList());
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    /**
     * Displays the task list as a formatted string.
     *
     * @return The formatted string representation of the task list.
     */
    public static String displayList() {
        StringBuilder res;
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

    /**
     * Deletes a task given its index as a string.
     *
     * @param input The string representation of the index of the task to be deleted.
     */
    public void delete(String input) {
        int taskIndex = Integer.parseInt(input.substring(7)) - 1;
        try {
            if (taskIndex > taskCount || taskIndex <= 0) {
                throw new DukeException("Error: Invalid Task Index!");
            } else {
                int remainingTasks = taskCount - 1;
                String response = Ui.line + "Got it! I've removed this task:" +
                        "\n" + tasks.get(taskIndex).toString() +
                        "\n" + "You now have " + remainingTasks +
                        " task(s) in the list" + "\n" + Ui.line;
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

    /**
     * Marks a task as completed given its index as a string.
     *
     * @param input The string representation of the index of the task to be marked completed.
     */
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

    /**
     * Unmarks a task as completed given its index as a string.
     *
     * @param input The string representation of the index of the task to be unmarked as deleted.
     */
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

    public void addToList(Task task, int taskId) {
        int numTasks = taskCount + 1;
        String response = Ui.line + "Got it! I've added this task:" +
                "\n" + task.toString() + "\n"
                + "You now have " + numTasks
                + " task(s) in the list" + "\n" + Ui.line;
        tasks.add(taskId, task);
        if (taskCount < tasks.size()) {
            taskCount++;
        }
        writeToFile();
        System.out.println(response);
    }

    /**
     * handles a todo task.
     *
     * @param input  The string representation of the todo task.
     */
    public void handleTodo(String input) {
        String nameOfTask = input.substring(5);
        ToDo task = new ToDo(nameOfTask);
        addToList(task, taskCount);
    }

    /**
     * handles a deadline task.
     *
     * @param input The string representation of the todo task and deadline.
     */
    public void handleDeadline(String input) {
        String[] parts = input.split("/by ");
        String nameOfTask = parts[0].trim().substring(9);
        try {
            LocalDate deadline = LocalDate.parse(parts[1].trim());
            Deadline task = new Deadline(nameOfTask, deadline);
            addToList(task, taskCount);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid Date Format! Follow: YYYY-MM-DD");
        }
    }

    /**
     * handles a event task.
     *
     * @param input The string representation of the event, start time, and end time.
     */
    public void handleEvent(String input) {
        String[] taskAndTime = input.split("/from ");
        String[] fromAndTo = taskAndTime[1].split("/to ");
        try {
            LocalDate start = LocalDate.parse(fromAndTo[0].trim());
            LocalDate end = LocalDate.parse(fromAndTo[1].trim());
            String nameOfTask = taskAndTime[0].trim().substring(6);
            Event task = new Event(nameOfTask, start, end);
            addToList(task, taskCount);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid Date Format! Follow: YYYY-MM-DD");
        }
    }

    public void handleFind(String input) {
        String itemToFind = input.substring(5);
        printFileContents(itemToFind);
    }
}
