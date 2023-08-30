import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

import exception.InvalidCommandException;
import exception.InvalidDeadlineException;
import exception.InvalidEventException;
import exception.InvalidToDoException;
import storage.Storage;
import task.Deadline;
import task.Event;
import task.Task;
import task.TaskList;
import task.ToDo;

/**
 * Chatbot class
 */
public class Duke {
    /**
     * The main method
     * 
     * @param args the input argument
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        Storage storage = new Storage("./data", "storage.txt");
        TaskList tasks = new TaskList(storage.loadTasks());
        // ArrayList<Task> tasks = new ArrayList<Task>();
        String intro = "    ____________________________________________________________\n" +
                    "    Hello! I'm Not A ChatBot\n" + 
                    "    What can I do for you?\n" +
                    "    ____________________________________________________________";
        String end = "    ____________________________________________________________\n" +
                    "    Bye. Hope to see you again soon!\n" +
                    "    ____________________________________________________________";
        System.out.println(intro);
        String message = scanner.nextLine();
        while(!message.equals("bye")) {
            try{
                System.out.println("    ____________________________________________________________");
                if(message.equals("list")) {
                    System.out.println("    Here are the tasks in your list:");
                    System.out.print(tasks);
                } else if(message.split(" ")[0].equals("mark") && message.split(" ").length == 2 && isInt(message.split(" ")[1]) 
                        && Integer.parseInt(message.split(" ")[1]) <= tasks.size() && Integer.parseInt(message.split(" ")[1]) > 0) {
                    tasks.getTask(Integer.parseInt(message.split(" ")[1]) - 1).mark();
                    storage.updateTask(tasks);
                    System.out.println("    Nice! I've marked this task as done:");
                    System.out.println("    " + tasks.getTask(Integer.parseInt(message.split(" ")[1]) - 1));
                } else if(message.split(" ")[0].equals("unmark") && message.split(" ").length == 2 && isInt(message.split(" ")[1]) 
                        && Integer.parseInt(message.split(" ")[1]) <= tasks.size() && Integer.parseInt(message.split(" ")[1]) > 0) {
                    tasks.getTask(Integer.parseInt(message.split(" ")[1]) - 1).unmark();
                    storage.updateTask(tasks);
                    System.out.println("    OK, I've unmarked this task as not done yet:");
                    System.out.println("    " + tasks.getTask(Integer.parseInt(message.split(" ")[1]) - 1));
                } else if(ToDo.isToDo(message)) {
                    Task newTask = new ToDo(message.substring(5));
                    tasks.addTask(newTask);
                    storage.addTask(newTask);
                    System.out.println("    Got it. I've added this task:");
                    System.out.println("    " + tasks.getTask(tasks.size() - 1));
                    System.out.println("    Now you have " + tasks.size() + " tasks in the list.");
                } else if(Deadline.isDeadline(message)) {
                    String name = message.substring(9, message.indexOf("/by "));
                    String deadlineString = message.substring(message.indexOf("/by ") + 4);
                    LocalDate deadlineDate = LocalDate.parse(deadlineString);
                    Task newTask = new Deadline(name, deadlineDate);
                    tasks.addTask(newTask);
                    storage.addTask(newTask);
                    System.out.println("    Got it. I've added this task:");
                    System.out.println("    " + tasks.getTask(tasks.size() - 1));
                    System.out.println("    Now you have " + tasks.size() + " tasks in the list.");
                } else if(Event.isEvent(message)) {
                    String name = message.substring(6, message.indexOf("/from "));
                    int fromIndex = message.indexOf("/from ");
                    int toIndex = message.indexOf(" /to ", fromIndex);
                    String fromString = message.substring(fromIndex + 6, toIndex);
                    String toString = message.substring(toIndex + 5);
                    LocalDate fromDate = LocalDate.parse(fromString);
                    LocalDate toDate = LocalDate.parse(toString);
                    Task newTask = new Event(name, fromDate, toDate);
                    tasks.addTask(newTask);
                    storage.addTask(newTask);
                    System.out.println("    Got it. I've added this task:");
                    System.out.println("    " + tasks.getTask(tasks.size() - 1));
                    System.out.println("    Now you have " + tasks.size() + " tasks in the list.");
                } else if(message.split(" ")[0].equals("delete") && message.split(" ").length == 2 && isInt(message.split(" ")[1]) 
                        && Integer.parseInt(message.split(" ")[1]) <= tasks.size() && Integer.parseInt(message.split(" ")[1]) > 0) {
                    Task removed = tasks.getTask(tasks.size() - 1);
                    tasks.removeTask(Integer.parseInt(message.split(" ")[1]) - 1);
                    storage.updateTask(tasks);
                    System.out.println("    Noted. I've removed this task:");
                    System.out.println("    " + removed);
                    System.out.println("    Now you have " + tasks.size() + " tasks in the list.");
                } else {
                    throw new InvalidCommandException();
                }
            } catch(InvalidToDoException e) {
                System.out.println("    " + e.getMessage());
            } catch(InvalidEventException e) {
                System.out.println("    " + e.getMessage());
            } catch(InvalidDeadlineException e) {
                System.out.println("    " + e.getMessage());
            } catch(InvalidCommandException e) {
                System.out.println("    " + e.getMessage());
            }
            System.out.println("    ____________________________________________________________");
            message = scanner.nextLine();
        }
        System.out.println(end);
        scanner.close();
    }

    /**
     * Merthod to check whether a string is integer
     * 
     * @param str the string that wanted to be checked
     * @return whether the string is integer
     */
    private static boolean isInt(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}