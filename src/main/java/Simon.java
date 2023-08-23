import java.util.ArrayList;
import java.util.Scanner;

public class Simon {

    private static ArrayList<Task> tasks = new ArrayList<>();
    private static final String SPACE = "____________________________________________________________";
    private static final String NSPACE = "\n____________________________________________________________";
    private static final String SPACEN = "____________________________________________________________\n";

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        // Start Program
        System.out.println(SPACEN + SimonAscii.toStr());
        System.out.println("Hello! I'm Simon\nWhat can I do for you?\n" + SPACE);

        while (true) {
            String inData = scan.nextLine();
            String command = inData.split(" ")[0];

            try {
                switch (command) {
                    case "list":
                        listTasks();
                        break;
                    case "todo":
                    case "deadline":
                    case "event":
                        addTask(inData, command);
                        break;
                    case "unmark":
                        markTask(inData, false);
                        break;
                    case "mark":
                        markTask(inData, true);
                        break;
                    case "delete":
                        deleteTask(inData);
                        break;
                    case "bye":
                        System.out.println("Bye. Hope to see you again soon!" + NSPACE);
                        return;
                    default:
                        throw new SimonException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (SimonException se) {
                System.out.println(se.getMessage() + NSPACE);
            }
        }
    }

    private static void listTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            String status = tasks.get(i).isDone ? "[X] " : "[ ] ";
            System.out.println((i + 1) + ". " + status + tasks.get(i));
        }
        System.out.println(SPACE);
    }

    private static void addTask(String inData, String taskType) throws SimonException {
        Task task;
        switch (taskType) {
            case "todo":
                String description = inData.split("todo ").length > 1 ? inData.split("todo ")[1] : "";
                if (description.trim().isEmpty()) {
                    throw new SimonException("☹ OOPS!!! The description of a todo cannot be empty.");
                }
                task = new ToDo(description);
                break;

            case "deadline":
                String[] deadlineParts = inData.split("deadline ");
                if (deadlineParts.length <= 1 || !inData.contains(" /by ")) {
                    throw new SimonException("☹ OOPS!!! The format for deadline is incorrect. Expected format: 'deadline [task description] /by [deadline]'.");
                }
                String name = deadlineParts[1].split(" /by ")[0];
                String endDate = deadlineParts[1].split(" /by ")[1];
                task = new Deadline(name, endDate);
                break;

            case "event":
                String[] eventParts = inData.split("event ");
                if (eventParts.length <= 1 || !inData.contains(" /from ") || !inData.contains(" /to ")) {
                    throw new SimonException("☹ OOPS!!! The format for event is incorrect. Expected format: 'event [event description] /from [start date] /to [end date]'.");
                }
                String eventName = eventParts[1].split(" /from ")[0];
                String startDate = eventParts[1].split(" /from ")[1].split(" /to ")[0];
                String endDateEvent = eventParts[1].split(" /from ")[1].split(" /to ")[1];
                task = new Event(eventName, startDate, endDateEvent);
                break;

            default:
                throw new SimonException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }


        tasks.add(task);
        System.out.println(SPACEN + "Got it. I've added this task:\n" +
                task + String.format("\nNow you have %d %s in the list.",
                tasks.size(), tasks.size() > 1 ? "tasks" : "task") + NSPACE);
    }

    private static void markTask(String inData, boolean markAsDone) throws SimonException {
        String[] split = inData.split(" ");

        int index;
        try {
            index = Integer.parseInt(split[1]) - 1;
        } catch (NumberFormatException e) {
            throw new SimonException("Please provide a valid task number.");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new SimonException("Please provide a task number.");
        }

        if (tasks.isEmpty()) {
            throw new SimonException("There are no tasks to mark.");
        }
        if (index < 0 || index >= tasks.size()) {
            throw new SimonException("Invalid task number. Choose a number between 1 and " + tasks.size() + ".");
        }

        if (markAsDone) {
            tasks.get(index).markAsDone();
            System.out.println("Nice! I've marked this task as done:\n[X] " + tasks.get(index) + NSPACE);
        } else {
            tasks.get(index).markAsUndone();
            System.out.println("OK, I've marked this task as not done yet:\n[ ] " + tasks.get(index) + NSPACE);
        }
    }

    private static void deleteTask(String inData) throws SimonException {
        String[] split = inData.split(" ");

        int index = 0;
        try {
            index = Integer.parseInt(split[1]) - 1;
        } catch (NumberFormatException e) {
            System.out.println("Please provide a valid task number." + NSPACE);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Please provide a task number." + NSPACE);
        }

        if (tasks.isEmpty()) {
            throw new SimonException("There are no tasks to delete." + NSPACE);
        }
        if (index < 0 || index >= tasks.size()) {
            throw new SimonException("Invalid task number. Choose a number between 1 and " + tasks.size() + "." + NSPACE);
        }

        tasks.remove(index);
        System.out.println("Noted. I've removed this task:\n" + tasks.get(index) + String.format("\nNow you have %d %s in the list.",
                tasks.size(), tasks.size() - 1 > 1 ? "tasks" : "task") + NSPACE);
    }

}
