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
                    case "bye":
                        System.out.println("Bye. Hope to see you again soon!" + NSPACE);
                        return;
                    default:
                        tasks.add(new Task(inData));
                        System.out.println("added: " + inData + NSPACE);
                        break;
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
        if (taskType.equals("todo")) {
            task = new ToDo(inData.split("todo ")[1]);
        } else if (taskType.equals("deadline")) {
            String name = inData.split("deadline ")[1].split(" /by ")[0];
            String endDate = inData.split("deadline ")[1].split(" /by ")[1];
            task = new Deadline(name, endDate);
        } else if (taskType.equals("event")) {
            String name = inData.split("event ")[1].split(" /from ")[0];
            String startDate = inData.split("event ")[1].split(" /from ")[1].split(" /to ")[0];
            String endDate = inData.split("event ")[1].split(" /from ")[1].split(" /to ")[1];
            task = new Event(name, startDate, endDate);
        } else {
            throw new SimonException("Unknown task type.");
        }

        tasks.add(task);
        System.out.println(SPACEN + "Got it. I've added this task:\n" +
                task + String.format("\nNow you have %d %s in the list.",
                tasks.size(), tasks.size() > 1 ? "tasks" : "task") + NSPACE);
    }

    private static void markTask(String inData, boolean markAsDone) throws SimonException {
        String[] split = inData.split(" ");
        int index = Integer.parseInt(split[1]) - 1;

        if (index < 0 || index >= tasks.size()) {
            throw new SimonException("Invalid task number.");
        }

        if (markAsDone) {
            tasks.get(index).markAsDone();
            System.out.println("Nice! I've marked this task as done:\n[X] " + tasks.get(index) + NSPACE);
        } else {
            tasks.get(index).markAsUndone();
            System.out.println("OK, I've marked this task as not done yet:\n[ ] " + tasks.get(index) + NSPACE);
        }
    }
}
