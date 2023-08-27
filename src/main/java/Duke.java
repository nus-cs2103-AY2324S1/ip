import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

    static void addTask(String task, TaskType taskType) throws GlubException {
        if (task.equals("")) {
            throw new GlubException(String.format("OOPS!! The description of a %s cannot be empty.\n", taskType));
        }
        switch (taskType) {
        case TODO:
            taskList.add(new ToDo(task));
            break;
        case DEADLINE:
            String[] deadlinePortions = task.split("/");
            String deadlineDesc = deadlinePortions[0];
            try {
                String deadline = deadlinePortions[1].split(" ", 2)[1];
                taskList.add(new Deadline(deadlineDesc, deadline));
            } catch (ArrayIndexOutOfBoundsException ex) {
                throw new GlubException("OOPS!! Please provide a deadline for your deadline task.\n");
            }
            break;
        case EVENT:
            String[] eventPortions = task.split("/");
            String eventDesc = eventPortions[0];
            try {
                String start = eventPortions[1].split(" ", 2)[1];
                String end = eventPortions[2].split(" ", 2)[1];
                taskList.add(new Event(eventDesc, start, end));
            } catch (ArrayIndexOutOfBoundsException ex) {
                throw new GlubException("OOPS!! Ensure that your event has a start and end!\n");
            }
            break;
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
                    String todo = inputScanner.nextLine();
                    addTask(todo, TaskType.TODO);
                    break;
                case "deadline":
                    String deadline = inputScanner.nextLine();
                    addTask(deadline, TaskType.DEADLINE);
                    break;
                case "event":
                    String event = inputScanner.nextLine();
                    addTask(event, TaskType.EVENT);
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
