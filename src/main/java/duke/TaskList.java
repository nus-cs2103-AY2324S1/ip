package duke;

import java.io.IOException;
import java.util.ArrayList;

public class TaskList {
    protected static final String horizontalLine = "    ____________________________________________________________";

    protected static ArrayList<Task> taskArray;
    protected static Storage storage;
    protected static int numTask;
    protected static Parser parser;

    public TaskList(ArrayList<Task> loadTask, Storage store) {
        taskArray = loadTask;
        numTask = taskArray.size();
        storage = store;
        parser = new Parser();
    }

    public void unmark(String i) {
        int taskId = Integer.parseInt(i.substring(7)) - 1;
        taskArray.get(taskId).markAsUndone();
        // update the duke.txt
        try {
            storage.saveTask(taskArray);
        } catch (IOException e) {
            System.out.println("      Uhm.. something is not working right..");
        }
    }
    public void mark(String i) {
        int taskId = Integer.parseInt(i.substring(5)) - 1;
        taskArray.get(taskId).markAsDone();
        // update the duke.txt
        try {
            storage.saveTask(taskArray);
        } catch (IOException e) {
            System.out.println("      Uhm.. something is not working right..");
        }
    }
    public static void deleteTask(String i) {
        int deleteTask = Integer.parseInt(i.substring(7)) - 1;
        Task removed = taskArray.get(deleteTask);
        taskArray.remove(deleteTask);
        numTask--;
        System.out.println("     Noted. I've removed this task:\n"
                + "     " + removed.printDesc() + "\n"
                + "     Now you have " + numTask +" tasks in the list.");
    }
    public static void listTask(String i) {
        System.out.println("     Here are the tasks in your list:\n");
        for (int a = 0; a < numTask; a++) {
            System.out.println("     " + (a + 1) + ". " + taskArray.get(a).printDesc());
        }
    }
    public static void todoTask(String i) {
        String[] taskDetails = i.split(" ", 2);
        try {
            taskArray.add(new Todo(taskDetails[1]));
            taskArray.get(numTask).printMessage(numTask);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(horizontalLine + "\n"
                    + "     OOPS!!! The description of todo cannot be empty :(.\n"
                    + horizontalLine);
            numTask--;
        }
        numTask++;
    }
    public static void deadlineTask(String i) {
        String[] taskDetails = i.split(" ", 2);
        try {
            String[] deadlineDetails = parser.deadlineDetails(taskDetails[1]);
            taskArray.add(new Deadline(deadlineDetails[0], deadlineDetails[1]));
            taskArray.get(numTask).printMessage(numTask);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(horizontalLine + "\n"
                    + "     OOPS!!! The description of deadline is incomplete.\n"
                    + horizontalLine);
            numTask--;
        }
        numTask++;
    }
    public static void eventTask(String i) {
        String[] taskDetails = i.split(" ", 2);
        try {
            String[] eventDetails = parser.eventDetails(taskDetails[1]);
            taskArray.add(new Event(eventDetails[0], eventDetails[1].substring(5),
                    eventDetails[2].substring(3)));
            taskArray.get(numTask).printMessage(numTask);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(horizontalLine + "\n"
                    + "     OOPS!!! The description of event is incomplete :(.\n"
                    + horizontalLine);
            numTask--;
        }
        numTask++;
    }
    protected ArrayList<Task> getTaskArray() {
        return taskArray;
    }
}
