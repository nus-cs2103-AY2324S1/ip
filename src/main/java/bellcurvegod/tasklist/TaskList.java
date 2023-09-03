package bellcurvegod.tasklist;

import bellcurvegod.exception.EmptyDescriptionException;
import bellcurvegod.exception.InvalidCommandException;
import bellcurvegod.task.*;
import bellcurvegod.ui.Ui;
import bellcurvegod.storage.Storage;

import java.util.ArrayList;

public class TaskList {
    protected static ArrayList<Task> tasks = new ArrayList<>();
    protected static int numOfTasks = 0;

    public static ArrayList<Task> getTaskList() {
        return tasks;
    }

    public static int getNumOfTasks() {
        return numOfTasks;
    }

    public static void incrementNumOfTasks() {
        numOfTasks++;
    }

    /**
     * Adds a task to the storage.
     * @param input description of the task to be added
     */
    public static void addTask(String input) throws InvalidCommandException, EmptyDescriptionException {
        String cmd = input.split(" ")[0];
        Task newTask = null;

        if (!(cmd.equals("todo") || cmd.equals("deadline") || cmd.equals("event"))) {
            throw new InvalidCommandException(
                    Ui.getLine() + "\n" +
                            "You have entered an invalid bellcurvegod.command word!\n" +
                            "To add a new Task, use \"todo\", \" deadline\", or \"event\".\n" +
                            Ui.getLine());
        }

        if (cmd.equals("deadline")) {
            newTask = Deadline.generateDeadlineFromInput(input);
        } else if (cmd.equals("event")) {
            newTask = Event.generateEventFromInput(input);
        } else {
            newTask = Todo.generateTodoFromInput(input);
        }

        tasks.add(newTask);
        numOfTasks++;
        Ui.showLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(newTask);
        System.out.println("Now you have " + numOfTasks + " tasks in the list.");
        Ui.showLine();
    }

    /**
     * Deletes the task from the list.
     * @param task task to be deleted.
     */
    public static void delete(Task task) {
        tasks.remove(task);
        numOfTasks--;
        Ui.showLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + numOfTasks + " tasks in the list.");
        Ui.showLine();
    }
}
