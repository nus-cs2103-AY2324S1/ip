import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Duke {

    private boolean isRunning = true;
    private final ArrayList<Task> tasks = new ArrayList<>();
    private int numOfTasks = 0;
    public Duke() {
        String name = "Meg";
        String intro1 = String.format("I'm %s. You called me?" +
                "\n", name);
        String intro2 = "Make it quick, thanks.";
        System.out.println(intro1);
        System.out.println(intro2 + "\n");
        printHorizontalLine();
        Scanner sc = new Scanner(System.in);
        while (this.isRunning) {
            String msg = sc.nextLine();
            readInput(msg);
        }
        exit();
    }

    public void printHorizontalLine() {
        for (int i = 0; i < 20; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    public void exit() {
        System.out.println("Don't let me see you again!");
        printHorizontalLine();
        System.exit(0);
    }

    public void readInput(String message) {
        switch (message) {
            case "bye":
                this.isRunning = false;
                break;
            case "list":
                this.list();
                break;
            case "mark":
                this.markAsComplete();
                break;
            case "unmark":
                this.markAsIncomplete();
                break;
            case "todo":
                this.createToDo();
                break;
            case "deadline":
                this.createDeadline();
                break;
            case "event":
                this.createEvent();
                break;
            default:
                tasks.add(new Task(message));
                numOfTasks++;
                System.out.printf("Don't expect me to %s for you!%n", message);
                printHorizontalLine();
                break;
        }
    }

    public void list() {
        System.out.printf("You have %d tasks.%n", numOfTasks);
        for (int i = 0; i < numOfTasks; i++) {
            System.out.printf("%d. " + tasks.get(i).toString()
                    + "%n", i + 1);
        }
        System.out.println("Don't expect me to remember them for you!");
        printHorizontalLine();
    }

    public void markAsComplete() {
        if (this.numOfTasks == 0) {
            System.out.println("No tasks to mark");
            printHorizontalLine();
            return;
        }
        int taskNumber = this.launchConfirmationScreen("mark as complete");
        if (taskNumber != -1) {
            Task task = tasks.get(taskNumber - 1);
            if (!task.isCompleted) {
                task.setCompleted();
                System.out.printf("Task %d set as complete.%n", taskNumber);
            } else {
                System.out.printf("Task %d is already complete." + "%n" +
                        "Stop wasting my time!", taskNumber);
            }
        } else {
            System.out.println("Request unsuccessful.");
        }
        printHorizontalLine();
    }

    public void markAsIncomplete() {
        if (this.numOfTasks == 0) {
            System.out.println("No tasks to mark");
            printHorizontalLine();
            return;
        }
        int taskNumber = this.launchConfirmationScreen("mark as incomplete");
        if (taskNumber != -1) {
            Task task = tasks.get(taskNumber - 1);
            if (task.isCompleted) {
                task.setIncomplete();
                System.out.printf("Task %d set as incomplete.%n", taskNumber);
            } else {
                System.out.printf("Task %d is already incomplete." + "%n" +
                        "Stop wasting my time!", taskNumber);
            }
        } else {
            System.out.println("Request unsuccessful.");
        }
        printHorizontalLine();
    }

    public int launchConfirmationScreen(String message) {
        Scanner sc = new Scanner(System.in);
        System.out.printf("Please input the task number you wish to %s.%n",
                message);
        printHorizontalLine();
        for (int i = 0; i < numOfTasks; i++) {
            System.out.printf("%d. " + tasks.get(i).toString()
                    + "%n", i + 1);
        }
        try {
            int a = sc.nextInt();
            return (a > numOfTasks ? -1 : a);
        }
        catch (InputMismatchException e) {
            return -1;
        }
    }

    public void createToDo() {
        Scanner sc = new Scanner(System.in);
        System.out.printf("Input task details.%n");
        String message = sc.nextLine();
        tasks.add(new ToDo(message));
        numOfTasks++;
        System.out.printf("Stop talking to me! Go and %s!%n", message);
        printHorizontalLine();
    }

    public void createDeadline() {
        Scanner sc = new Scanner(System.in);
        System.out.printf("Input task details.%n");
        String message = sc.nextLine();
        System.out.printf("Input task deadline.%n");
        String due = sc.nextLine();
        tasks.add(new Deadline(message, due));
        numOfTasks++;
        System.out.printf("Just saying, better %s now.%n" +
                "Not like it's my problem if you don't.%n", message);
        printHorizontalLine();
    }

    public void createEvent() {
        Scanner sc = new Scanner(System.in);
        System.out.printf("Input task details.%n");
        String message = sc.nextLine();
        System.out.printf("Input start time.%n");
        String start = sc.nextLine();
        System.out.printf("Input end time.%n");
        String end = sc.nextLine();
        tasks.add(new Event(message, start, end));
        numOfTasks++;
        System.out.printf("Wow, you have a %s at %s?%n" +
                "Uhh, n-not like I wanna join you!%n", message, start);
        printHorizontalLine();
    }

    public static void main(String[] args) {
        new Duke();
    }
}
