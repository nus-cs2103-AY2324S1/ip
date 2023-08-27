import java.lang.reflect.Array;
import java.util.*;

public class Duke {
    static List<Task> taskList = new ArrayList<>();
    static boolean isListening = true;
    static void greet() {
        String greeting = "_________________________________________________\n"
                + "Hello! I'm Glub!\n"
                + "What can I do for you?\n"
                + "_________________________________________________\n";
        System.out.println(greeting);
    }

    static void addTask(String task, String type) throws GlubException {
        if (task.equals("")) {
            throw new GlubException(String.format("OOPS!! The description of a %s cannot be empty.\n", type));
        }
        if (type.equals("todo")) {
            taskList.add(new ToDo(task));
        } else if (type.equals("deadline")) {
            String[] taskPortions = task.split("/");
            String desc = taskPortions[0];
            try {
                String deadline = taskPortions[1].split(" ", 2)[1];
                taskList.add(new Deadline(desc, deadline));
            } catch (ArrayIndexOutOfBoundsException ex) {
                throw new GlubException("OOPS!! Please provide a deadline for your deadline task.\n");
            }
        } else {
            String[] taskPortions = task.split("/");
            String desc = taskPortions[0];
            try {
                String start = taskPortions[1].split(" ", 2)[1];
                String end = taskPortions[2].split(" ", 2)[1];
                taskList.add(new Event(desc, start, end));
            } catch (ArrayIndexOutOfBoundsException ex) {
                throw new GlubException("OOPS!! Ensure that your event has a start and end!\n");
            }
        }
        String addMsg = "_________________________________________________\n"
                + "Got it. I've added this task:\n"
                + String.format(" \t%s\n", taskList.get(taskList.size()-1))
                + String.format("Now you have %d tasks in the list\n", taskList.size())
                + "_________________________________________________\n";
        System.out.println(addMsg);
    }

    static void deleteTask(int taskNum) throws GlubException {
        try {
            Task deleted = taskList.remove(taskNum - 1);
            String msg = "_________________________________________________\n"
                    + "Noted. I've removed this task:\n"
                    + String.format("\t%s\n", deleted)
                    + String.format("Now you have %d tasks in the list.\n", taskList.size())
                    + "_________________________________________________\n";
            System.out.println(msg);
        } catch (IndexOutOfBoundsException ex) {
            throw new GlubException(String.format("OOPS!! Task %d does not exist!\n", taskNum));
        }
    }

    static void list() {
        System.out.println("Here are the tasks in your list:\n");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.printf(" %d. %s\n", i + 1, taskList.get(i));
        }
    }

    static void mark(int taskNum) {
        Task task = taskList.get(taskNum - 1);
        task.setDone();
        String markMsg = "_________________________________________________\n"
                + "Nice! I've marked this task as done:\n"
                + String.format("\t %s\n", task)
                + "_________________________________________________\n";
        System.out.println(markMsg);
    }

    static void unmark(int taskNum) {
        Task task = taskList.get(taskNum - 1);
        task.setUndone();
        String markMsg = "_________________________________________________\n"
                + "Ok, I've marked this task as not done yet:\n"
                + String.format("\t %s\n", task)
                + "_________________________________________________\n";
        System.out.println(markMsg);
    }
    static void exit() {
        String exitMsg = "_________________________________________________\n"
                + "Bye. Hope to see you again soon!\n"
                + "_________________________________________________\n";
        System.out.println(exitMsg);
        isListening = false;
    }
    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        greet();
        while (isListening) {
            try {
                String command = inputScanner.next();
                switch (command) {
                case "bye":
                    exit();
                    break;
                case "list":
                    list();
                    break;
                case "mark":
                    mark(inputScanner.nextInt());
                    break;
                case "unmark":
                    unmark(inputScanner.nextInt());
                    break;
                case "delete":
                    deleteTask(inputScanner.nextInt());
                    break;
                case "todo":
                case "deadline":
                case "event":
                    String task = inputScanner.nextLine();
                    addTask(task, command);
                    break;
                default:
                    throw new GlubException("OOPS!! I'm sorry, but I don't know what that means :-(\n");
                }
            } catch (GlubException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
