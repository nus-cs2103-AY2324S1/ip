import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class TehO {
    static int taskCounter = 0;
    static Task[] taskList = new Task[100];

    public static void main(String[] args) throws InvalidCommandException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I'm TehO \nWhat can I do for you?");

        while (true) {
            String userCommand = sc.nextLine();
            //BYE
            if (userCommand.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
                //LIST
            } else if (userCommand.equals("list")) {
                listTask(taskList);
                //MARK
            } else if (userCommand.startsWith("mark")) {
                //note that split returns a String[]
                //parseInt returns the integer value which is represented by the argument
                int taskNumber = parseInt(userCommand.split(" ")[1]) - 1; //counting from 0
                Task task = taskList[taskNumber];
                task.markAsDone(taskNumber);
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(task.toString());
                //UNMARK
            } else if (userCommand.startsWith("unmark")) {
                int taskNumber = parseInt(userCommand.split(" ")[1]) - 1; //counting from 0
                Task task = taskList[taskNumber];
                task.markAsNotDone(taskNumber);
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(task.toString());
                //TODO
            } else if (userCommand.startsWith("todo")) { //todo borrow book
                try {
                    if (userCommand.length() < 6) {
                        throw new EmptyToDoDescriptionException();
                    }
                    System.out.println("Got it. I've added this task:");
                    String command = userCommand.substring(5); //"todo " 5 index
                    Task task = new ToDo(command);
                    addToDo(task);
                    System.out.println("Now you have " + taskCounter + " tasks in the list.");
                } catch (EmptyToDoDescriptionException e) {
                    System.out.println(e.toString());
                }
            //DEADLINE
            } else if (userCommand.startsWith("deadline")) { //deadline return book /by Sunday
                try {
                    if (userCommand.length() < 10) {
                        throw new EmptyDeadlineDescriptionException();
                    }
                    System.out.println("Got it. I've added this task:");
                    String commandWithDate = userCommand.substring(9); //"todo " 9 index
                    String cDeadline = commandWithDate.split("/by")[0];
                    Task task = new Deadline(cDeadline);
                    String byDate = commandWithDate.split("/by")[1];
                    addDeadline(task, byDate);
                    System.out.println("Now you have " + taskCounter +" tasks in the list.");
                } catch (EmptyDeadlineDescriptionException e) {
                    System.out.println(e.toString());
                }
                //EVENT
            } else if (userCommand.startsWith("event")) { //need time
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
                    addEvent(task, fromDate, toDate);
                    System.out.println("Now you have " + taskCounter +" tasks in the list.");
                } catch (EmptyEventDescriptionException e) {
                    System.out.println(e.toString());
                }
            //OTHERS
            } else {
                //Task task = new Task(userCommand);
                //addTask(task);
                try {
                    throw new InvalidCommandException();
                } catch (InvalidCommandException e) {
                    System.out.println(e.toString());
                }
            }
        }
    }


//    public static void addTask(Task newTask) {
//        taskList[taskCounter] = newTask;
//        taskCounter++;
//        System.out.println("added: " + newTask.description);
//    }

    public static void addToDo(Task newTask) {
        taskList[taskCounter] = newTask;
        taskCounter++;
        System.out.println(newTask.toString());
    }

    public static void addDeadline(Task newTask, String byDate) {
        taskList[taskCounter] = newTask;
        taskCounter++;
        System.out.println(newTask.toString() + "(by:" + byDate + ")");
    }

    public static void addEvent(Task newTask, String fromDate, String toDate) {
        taskList[taskCounter] = newTask;
        taskCounter++;
        System.out.println(newTask.toString() + "(from:" + fromDate
        + " to:" + toDate + ")");
    }

    public static void listTask(Task[] taskList) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskCounter; i++) {
            Task task = taskList[i];
            System.out.println((i+1) + ". " + task.toString());
        }
    }
}
