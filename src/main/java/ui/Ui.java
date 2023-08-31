package ui;

import data.TaskList;
import exceptions.EmptyTaskException;
import exceptions.InvalidCommand;
import tasks.Task;

import static java.lang.Integer.parseInt;

public class Ui {
    public static final String LINE = "＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿";
    public static final String INDENT = "    ";

    public boolean running = true;


    // print a string (including multiple lines) with indent and lines top and bottom
    public static void formatPrint(String input) {
        System.out.println(INDENT + LINE);
        String[] lines = input.split("\n");
        for (String line : lines) {
            System.out.println(INDENT + line);
        }
        System.out.println(INDENT + LINE);
    }
    public void greet() {
        formatPrint("Hello! I'm HASTE\nWhat can I do for you?");
        // read file and update task list
    }
    public void bye() {
        formatPrint("Bye. Hope to see you again!");
        // end prog
        this.running = false;
    }

    public void mark(String cmd) {
        int id = parseInt(cmd.split(" ")[1]) - 1;
        Task currTask = TaskList.taskList.get(id);
        currTask.markDone();
        formatPrint("Nice! I've marked this task as done:\n" + currTask);
    }

    public void unmark(String cmd) {
        int id = parseInt(cmd.split(" ")[1]) - 1;
        Task currTask = TaskList.taskList.get(id);
        currTask.markUndone();
        formatPrint("Okay, I've marked this task as not done:\n" + currTask);
    }
    public void delete(String cmd) {
        int id = parseInt(cmd.split(" ")[1]) - 1;

        // to do : fix order (delete task to be in front?)
        String taskDesc = TaskList.taskList.get(id).toString();
        TaskList.deleteTask(id);
        formatPrint("Noted. I've removed this task\n" + taskDesc + "\nNow you have " + TaskList.getNumOfTasks() + " tasks in the list");

    }
    public void add(Task newTask) throws InvalidCommand, EmptyTaskException {
        TaskList.addTask(newTask);
        String taskDesc = TaskList.getTask(TaskList.getNumOfTasks() - 1).toString();
        Ui.formatPrint("Got it. I've added this task:\n" + taskDesc + "\nNow you have " + TaskList.getNumOfTasks() + " tasks in the list.");
    }

    public void printList() {
        System.out.println(INDENT + LINE);
        for (Task x : TaskList.taskList) {
            System.out.println(INDENT + (TaskList.taskList.indexOf(x) + 1) + ". " + x);
        }
        System.out.println(INDENT + LINE);
    }

}
