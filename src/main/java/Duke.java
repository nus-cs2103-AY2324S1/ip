import java.util.Scanner;
public class Duke {
    private static final String LINE = "_".repeat(60);
    private static Task[] tasks;
    private static int noTasks;

    public static void main(String[] args) {
        tasks = new Task[100];
        noTasks = 0;
        Scanner scanner = new Scanner(System.in);
        String task = "";
        String userInput = "";
        boolean loop = true;

        System.out.println(LINE + "\nHello! I'm Dommi\nWhat can I do for you?\n" + LINE);

        while (loop) {
            if (!scanner.hasNextLine()) {
                break;
            }
            userInput = scanner.nextLine();  // Read user input
            String instruction = userInput.split(" ", 2)[0];
            if (userInput.contains(" ")) {
                task = userInput.split(" ", 2)[1];
            }

            System.out.println(LINE);

            switch (instruction) {
                case "bye":
                    exit();
                    loop = false;
                    break;
                case "list":
                    displayList();
                    break;
                case "mark":
                    int markIndex = Integer.parseInt(task);
                    markTaskDone(tasks[markIndex - 1], true);
                    break;
                case "unmark":
                    int unmarkIndex = Integer.parseInt(task);
                    markTaskDone(tasks[unmarkIndex - 1], false);
                    break;
                case "todo":
                    Todo newTodo = new Todo(task);
                    createNewTask(newTodo);
                    break;
                case "deadline":
                    String deadlineTask = task.split(" /by ")[0];
                    String deadline = task.split(" /by ")[1];
                    Deadline newDeadline = new Deadline(deadlineTask, deadline);
                    createNewTask(newDeadline);
                    break;
                case "event":
                    String eventTask = task.split(" /from ")[0];
                    String from = task.split(" /from ")[1].split(" /to ")[0];
                    String to = task.split(" /from ")[1].split(" /to ")[1];
                    Event newEvent = new Event(eventTask, from, to);
                    createNewTask(newEvent);
                    break;
                default:
                    System.out.println("Error! Invalid Input!");
            }
        }
    }

    private static void createNewTask(Task task) {
        tasks[noTasks] = task;
        noTasks += 1;
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + noTasks + " tasks in the list.");
    }

    private static void displayList() {
        for (int i = 0; i < noTasks; i++)
            System.out.println((i + 1) + "." + tasks[i]);
        System.out.println(LINE);
    }

    private static void markTaskDone(Task task, boolean done) {
        if (done) {
            task.markAsDone();
            System.out.println("Nice! I've marked this task as done:");
        } else {
            task.markAsNotDone();
            System.out.println("OK, I've marked this task as not done yet:");
        }
        System.out.println(task);
        System.out.println(LINE);
    }

    private static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }
}
