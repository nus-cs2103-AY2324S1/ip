package duke;

import Exceptions.DukeArgumentException;

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
    public TaskList() {
        taskArray = new ArrayList<>();
        parser = new Parser();
    }

    public void unmark(String i) throws DukeArgumentException {
        int taskId = Integer.parseInt(i.substring(7)) - 1;
        if (taskId < 0 || taskId >= numTask) {
            throw new DukeArgumentException("      I think you keyed in the wrong task number..");
        }
        taskArray.get(taskId).markAsUndone();
        // update the duke.txt
        try {
            storage.saveTask(taskArray);
        } catch (IOException e) {
            System.out.println("      Uhm.. something is not working right..");
        }
    }
    public void mark(String i) throws DukeArgumentException {
        int taskId = Integer.parseInt(i.substring(5)) - 1;
        if (taskId < 0 || taskId >= numTask) {
            throw new DukeArgumentException("      I think you keyed in the wrong task number..");
        }
        taskArray.get(taskId).markAsDone();
        // update the duke.txt
        try {
            storage.saveTask(taskArray);
        } catch (IOException e) {
            System.out.println("      Uhm.. something is not working right..");
        }
    }
    public static void deleteTask(String i) throws DukeArgumentException {
        int deleteTask = Integer.parseInt(i.substring(7)) - 1;
        if (deleteTask < 0 || deleteTask >= numTask) {
            throw new DukeArgumentException("      I think you keyed in the wrong task number..");
        }
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
    public static void todoTask(String i) throws DukeArgumentException {
        String[] taskDetails = parser.commandSplit(i);
        if (taskDetails.length != 2) {
            throw new DukeArgumentException("     OOPS!!! The description of todo cannot be empty :(");
        }
        taskArray.add(new Todo(taskDetails[1]));
        taskArray.get(numTask).printMessage(numTask);
        numTask++;
    }
    public static void deadlineTask(String i) throws DukeArgumentException {
        String[] taskDetails = parser.commandSplit(i);
        if (taskDetails.length != 2) {
            throw new DukeArgumentException("     OOPS!!! The description of deadline cannot be empty :(");
        }
        String[] deadlineDetails = parser.deadlineDetails(taskDetails[1]);
        if (deadlineDetails.length != 2) {
            throw new DukeArgumentException("     OOPS!!! Where is the deadline time?");
        }
        taskArray.add(new Deadline(deadlineDetails[0], deadlineDetails[1]));
        taskArray.get(numTask).printMessage(numTask);
        numTask++;
    }
    public static void eventTask(String i) throws DukeArgumentException {
        String[] taskDetails = parser.commandSplit(i);
        if (taskDetails.length != 2) {
            throw new DukeArgumentException("     OOPS!!! The description of event cannot be empty :(");
        }
        String[] eventDetails = parser.eventDetails(taskDetails[1]);
        if (eventDetails.length != 3) {
            throw new DukeArgumentException("     OOPS!!! The details for the event is missing!");
        }
        taskArray.add(new Event(eventDetails[0], eventDetails[1].substring(5),
                eventDetails[2].substring(3)));
        taskArray.get(numTask).printMessage(numTask);
        numTask++;
    }
    protected ArrayList<Task> getTaskArray() {
        return taskArray;
    }
}
