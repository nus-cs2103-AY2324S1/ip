import java.util.Scanner;

public class Duke {
    private final static String CHATBOT_NAME = "Fluke";
    private final static String LOGO =
            "    ________      __      \n" +
            "   / ____/ /_  __/ /_____ \n" +
            "  / /_  / / / / / //_/ _ \\\n" +
            " / __/ / / /_/ / ,< /  __/\n" +
            "/_/   /_/\\__,_/_/|_|\\___/ \n" +
            "                          ";
    private static Task[] listOfTasks = new Task[100];
    private static int nextTaskIndex = 0;

    public static void main(String[] args) {
        // introduce Fluke
        System.out.println(LOGO);
        addHorizontalLine();
        greet();
        addHorizontalLine();
        // initialise scanner to check for user input
        Scanner scanner = new Scanner(System.in);
        boolean waitingForInput = true;
        while (waitingForInput) {
            // check for user commands
            String nextCommand = scanner.nextLine();
            if (nextCommand.equals("bye")) {
                waitingForInput = false;
                sayBye();
            } else if (nextCommand.equals("list")) {
                list();
            } else if (nextCommand.startsWith("mark")) {
                int number = Integer.parseInt(nextCommand.substring(5));
                markTaskAsDone(number);
            } else if (nextCommand.startsWith("unmark")) {
                int number = Integer.parseInt(nextCommand.substring(7));
                markTaskAsUndone(number);
            } else {
                addTask(nextCommand);
            }
            addHorizontalLine();
        }
    }

    private static void greet() {
        System.out.println(
                "Hello! I'm " + CHATBOT_NAME + ", everything I do is down to luck!" + "\n" +
                "Feeling lucky today?"
        );
    }

    private static void sayBye() {
        System.out.println("Bye. Good luck!");
    }

    private static void addHorizontalLine() {
        System.out.println("____________________________________________________________");
    }

    private static void addTask(String str) {
        listOfTasks[nextTaskIndex] = new Task(str);
        nextTaskIndex += 1;
        System.out.println("added: " + str);
    }

    private static void list() {
        for (int i = 0; i < 100; i++) {
            if (listOfTasks[i] == null) {
                break;
            } else {
                Task task = listOfTasks[i];
                int number = i + 1;
                System.out.println(number + ". " + task);
            }
        }
    }

    private static void markTaskAsDone(int taskNumber) {
        int index = taskNumber - 1;
        // check if task exists
        if (listOfTasks.length > index && listOfTasks[index] != null) {
            listOfTasks[index].markAsDone();
            System.out.println("I have marked this task as done, I hope it's the right one:");
            System.out.println(listOfTasks[index]);
        } else {
            System.out.println("I'm pretty sure that task doesn't exist...");
        }
    }

    private static void markTaskAsUndone(int taskNumber) {
        int index = taskNumber - 1;
        // check if task exists
        if (listOfTasks.length > index && listOfTasks[index] != null) {
            listOfTasks[index].markAsUndone();
            System.out.println("I have marked this task as not done yet, I hope it's the right one:");
            System.out.println(listOfTasks[index]);
        } else {
            System.out.println("I'm pretty sure that task doesn't exist...");
        }
    }
}
