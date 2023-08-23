import java.util.Scanner;

public class Duke {
    private Scanner scanner;
    private TaskList taskList;

    private static String logo =
                    "██████╗░██╗░░░░░░█████╗░░█████╗░██████╗░\n" +
                    "██╔══██╗██║░░░░░██╔══██╗██╔══██╗██╔══██╗\n" +
                    "██████╦╝██║░░░░░██║░░██║██║░░██║██████╔╝\n" +
                    "██╔══██╗██║░░░░░██║░░██║██║░░██║██╔═══╝░\n" +
                    "██████╦╝███████╗╚█████╔╝╚█████╔╝██║░░░░░\n" +
                    "╚═════╝░╚══════╝░╚════╝░░╚════╝░╚═╝░░░░░";

    public Duke() {
        scanner = new Scanner(System.in);
        taskList = new TaskList();
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

            String[] words = strInput.split(" ");
            String firstWord = words[0].toLowerCase();

            if (firstWord.equals("bye")) {
                isEcho = false;
                System.out.println(" Bye. Hope to see you again soon!");
            } else if (firstWord.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                taskList.displayTasks();
            } else if (firstWord.equals("mark")){
                int taskIndex = Integer.parseInt(words[1]) - 1;
                if (taskList.isMarked(taskIndex)) {
                    System.out.println("Task already marked");
                } else {
                    taskList.doneAndDusted(taskIndex);
                }
            } else if (firstWord.equals("unmark")) {
                int taskIndex = Integer.parseInt(words[1]) - 1;
                if (taskList.isMarked(taskIndex)) {
                    taskList.notDoneNotDusted(taskIndex);
                } else {
                    System.out.println("Task already unmarked");
                }
            } else {
                taskList.addTask(strInput);
                System.out.println(" added: " + strInput);
                System.out.println("____________________________________________________________");
            }
        }
    }

    public static void main(String[] args) {
        Duke bloopBot = new Duke();
        bloopBot.start();
    }
}