import java.util.Scanner;
public class Duke {
    private static final String LINE = "_".repeat(60);
    private static Task[] tasks;
    private static int noTasks;

    public static void main(String[] args) {
        tasks = new Task[100];
        noTasks = 0;
        Scanner scanner = new Scanner(System.in);

        System.out.println(LINE + "\nHello! I'm Dommi\nWhat can I do for you?\n" + LINE);

        while (true) {
            String userInput = scanner.nextLine();  // Read user input
            System.out.println(LINE);

            if (userInput.equals("bye")) {
                exit();
                break;
            } else if (userInput.equals("list"))
                displayList();
            else if (userInput.startsWith("mark")) {
                int index = Integer.parseInt(userInput.substring(5));
                markTaskDone(tasks[index - 1], true);
            } else if (userInput.startsWith("unmark")) {
                int index = Integer.parseInt(userInput.substring(7));
                markTaskDone(tasks[index - 1], false);
            }
            else {
                Task newTask = new Task(userInput);
                createNewTask(newTask);
            }
        }
    }

    private static void createNewTask(Task task) {
        tasks[noTasks] = task;
        noTasks += 1;
        System.out.println("added: " + task.getDescription() + "\n" + LINE);
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
