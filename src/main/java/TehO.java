import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class TehO {
    //static int taskCounter = 0; //remove all
    static ArrayList<Task> taskList = new ArrayList<Task>();

    public static void main(String[] args) throws InvalidCommandException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I'm TehO \nWhat can I do for you?");

        while (true) {
            String userCommand = sc.nextLine();
            if (userCommand.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (userCommand.equals("list")) {
                listTask(taskList);
            } else if (userCommand.startsWith("mark")) {
                markTask(userCommand);
            } else if (userCommand.startsWith("unmark")) {
                unmarkTask(userCommand);
            } else if (userCommand.startsWith("todo")) { //todo borrow book
                addToDo(userCommand);
            } else if (userCommand.startsWith("deadline")) { //deadline return book /by Sunday
                addDeadline(userCommand);
            } else if (userCommand.startsWith("event")) {
                addEvent(userCommand);
            } else if (userCommand.startsWith("delete")) {
                delete(userCommand);
            } else {
                try {
                    throw new InvalidCommandException();
                } catch (InvalidCommandException e) {
                    System.out.println(e.toString());
                }
            }
        }
        sc.close();
    }

    public static void markTask(String userCommand) {
        //note that split returns a String[]
        //parseInt returns the integer value which is represented by the argument
        int taskNumber = parseInt(userCommand.split(" ")[1]) - 1; //counting from 0
        Task task = taskList.get(taskNumber);
        task.markAsDone(taskNumber);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task.toString());
    }
    public static void unmarkTask(String userCommand) {
        int taskNumber = parseInt(userCommand.split(" ")[1]) - 1; //counting from 0
        Task task = taskList.get(taskNumber);
        task.markAsNotDone(taskNumber);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task.toString());
    }

    public static void addToDo(String userCommand) {
        try {
            if (userCommand.length() < 6) {
                throw new EmptyToDoDescriptionException();
            }
            System.out.println("Got it. I've added this task:");
            String command = userCommand.substring(5); //"todo " 5 index
            Task task = new ToDo(command);
            taskList.add(task);
            System.out.println(task.toString());
            System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        } catch (EmptyToDoDescriptionException e) {
            System.out.println(e.toString());
        }
    }

    public static void addDeadline(String userCommand) {
        try {
            if (userCommand.length() < 10) {
                throw new EmptyDeadlineDescriptionException();
            }
            System.out.println("Got it. I've added this task:");
            String commandWithDate = userCommand.substring(9); //"todo " 9 index
            String cDeadline = commandWithDate.split("/by")[0];
            Task task = new Deadline(cDeadline);
            String byDate = commandWithDate.split("/by")[1];
            taskList.add(task);
            System.out.println(task.toString() + "(by:" + byDate + ")");
            System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        } catch (EmptyDeadlineDescriptionException e) {
            System.out.println(e.toString());
        }
    }

    public static void addEvent(String userCommand) {
        try {
            if (userCommand.length() < 6) {
                throw new EmptyEventDescriptionException();
            }
            System.out.println("Got it. I've added this task:");
            String commandWithDate = userCommand.substring(6); //"todo " 6 index
            String cEvent = commandWithDate.split("/from")[0];
            String dates = commandWithDate.split("/from")[1];
            Task task = new Event(cEvent);
            String fromDate = dates.split("/to")[0];
            String toDate = dates.split("/to")[1];
            taskList.add(task);
            System.out.println(task.toString() + "(from:" + fromDate
                    + " to:" + toDate + ")");
            System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        } catch (EmptyEventDescriptionException e) {
            System.out.println(e.toString());
        }
    }

    public static void listTask(ArrayList<Task> taskList) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            System.out.println((i + 1) + ". " + task.toString());
        }
    }

    public static void delete(String userCommand) {
        int taskNumber = parseInt(userCommand.split(" ")[1]) - 1; //counting from 0
        Task task = taskList.get(taskNumber);
        taskList.remove(taskNumber);
        System.out.println("Noted. I've removed this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }
}
