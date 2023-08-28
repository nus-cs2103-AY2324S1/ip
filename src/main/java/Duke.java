import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String LINE_SEPARATOR = "____________________________________________________________";
    private static final String GREETING_MESSAGE = LINE_SEPARATOR + "\n"
            + " Hello! I'm IRIS\n"
            + " What can I do for you?\n"
            + LINE_SEPARATOR;
    private static final String GOODBYE_MESSAGE = LINE_SEPARATOR + "\n"
            + "Bye. Hope to see you again soon!\n"
            + LINE_SEPARATOR;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;
        ArrayList<Task> taskList = new ArrayList<>();

        System.out.println(GREETING_MESSAGE);

        input = scanner.nextLine();

        while (!input.equals("bye")) {
            System.out.println(LINE_SEPARATOR);
            handleInput(input, taskList);
            System.out.println(LINE_SEPARATOR);
            input = scanner.nextLine();
        }

        scanner.close();
        System.out.println(GOODBYE_MESSAGE);
    }

    private static void handleInput(String input, ArrayList<Task> taskList) {
        String[] inputParts = input.split(" ", 2);
        String command = inputParts[0];

        switch (command) {
        case "list":
            listTasks(taskList);
            break;
        case "mark":
            markTask(inputParts, taskList, true);
            break;
        case "unmark":
            markTask(inputParts, taskList, false);
            break;
        case "delete":
            deleteTask(inputParts, taskList);
            break;
        default:
            addTask(inputParts, taskList);
            break;
        }
    }

    private static void listTasks(ArrayList<Task> taskList) {
        int count = 1;
        for (Task task : taskList) {
            if (task == null) {
                break;
            } else {
                System.out.println(count++ + ". " + task);
            }
        }
    }

    private static void markTask(String[] inputParts, ArrayList<Task> taskList, boolean markAsDone) {
        int index = Integer.parseInt(inputParts[1]) - 1;
        Task task = taskList.get(index);
        if (markAsDone) {
            task.markDone();
            System.out.println("Nice! I've marked this task as done:\n" + task);
        } else {
            task.markUndone();
            System.out.println("OK, I've marked this task as not done yet:\n" + task);
        }
    }

    private static void deleteTask(String[] inputParts, ArrayList<Task> taskList) {
        int index = Integer.parseInt(inputParts[1]) - 1;
        Task removedTask = taskList.remove(index);
        System.out.println("Noted. I've removed this task:\n" + removedTask);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    private static void addTask(String[] inputParts, ArrayList<Task> taskList) {
        try {
            Task newTask;
            if (inputParts.length < 2) {
                throw new DukeException("☹ OOPS!!! The description of a command cannot be empty.");
            }

            String description = inputParts[1];
            if (inputParts[0].equals("todo")) {
                newTask = new Todo(description);
            } else if (inputParts[0].equals("deadline")) {
                String[] commandParts = description.split("/by", 2);
                newTask = new Deadline(commandParts[0], commandParts[1]);
            } else if (inputParts[0].equals("event")) {
                String[] commandParts = description.split("/from", 2);
                String[] eventParts = commandParts[1].split("/to");
                newTask = new Event(commandParts[0], eventParts[0], eventParts[1]);
            } else {
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }

            taskList.add(newTask);
            System.out.println("Got it. I've added this task:\n" + newTask);
            System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        } catch (DukeException e) {
            System.out.println(e);
        }
    }
}