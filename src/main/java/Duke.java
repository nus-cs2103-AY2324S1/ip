import java.util.Scanner;

public class Duke {
    private Scanner scanner;
    private String[] tasks;
    private int counter;

    private static String logo =
            "██████╗░██╗░░░░░░█████╗░░█████╗░██████╗░\n" +
                    "██╔══██╗██║░░░░░██╔══██╗██╔══██╗██╔══██╗\n" +
                    "██████╦╝██║░░░░░██║░░██║██║░░██║██████╔╝\n" +
                    "██╔══██╗██║░░░░░██║░░██║██║░░██║██╔═══╝░\n" +
                    "██████╦╝███████╗╚█████╔╝╚█████╔╝██║░░░░░\n" +
                    "╚═════╝░╚══════╝░╚════╝░░╚════╝░╚═╝░░░░░";

    public Duke() {
        scanner = new Scanner(System.in);
        tasks = new String[100];
        counter = 0;
    }

    public void start() {
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm BloopBot");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");

        boolean isEcho = true;


        while (isEcho) {
            String strInput = scanner.nextLine();
            System.out.println("____________________________________________________________");
            if (strInput.equalsIgnoreCase("bye")) {
                isEcho = false;
                System.out.println(" Bye. Hope to see you again soon!");
            } else if (strInput.equalsIgnoreCase("list")) {
                displayTasks();
            } else {
                addTask(strInput);
                System.out.println(" added: " + strInput);
            }
            System.out.println("____________________________________________________________");
        }

    }

    private void addTask(String task) {
        tasks[counter] = task;
        counter++;
    }

    private void displayTasks() {
        if (counter == 0) {
            System.out.println("____________________________________________________________");
            System.out.println(" No tasks in list.");
            System.out.println("____________________________________________________________");
        } else {
            for (int i = 0; i < counter; i++) {
                System.out.println(" " + (i + 1) + ". " + tasks[i]);
            }
        }
    }

    public static void main(String[] args) {
        Duke bloopBot = new Duke();
        bloopBot.start();
    }
}