import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<>();

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printHorizontalLine();
        System.out.println("Hello! I'm Zac");
        System.out.println("What can I do for you?");
        printHorizontalLine();

        boolean isBotRunning = true;
        while (isBotRunning) {
            String[] userIn = scanner.nextLine().split(" ");
            String command = userIn[0];
            switch (command) {
                case "bye":
                    isBotRunning = false;
                    sayBye();
                    break;
                case "list":
                    int numTask = 1;
                    for (Task task : taskList) {
                        System.out.printf("%d.%s\n", numTask, task);
                        numTask += 1;
                    }
                    printHorizontalLine();
                    break;
                case "mark": {
                    int taskId = Integer.parseInt(userIn[1]) - 1;
                    if (taskId >= taskList.size()) {
                        printHorizontalLine();
                        System.out.println("Task not found!");
                        printHorizontalLine();
                        break;
                    }
                    Task doneTask = taskList.get(taskId);
                    doneTask.mark();
                    printHorizontalLine();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(doneTask);
                    printHorizontalLine();
                    break;
                }
                case "unmark": {
                    int taskId = Integer.parseInt(userIn[1]) - 1;
                    if (taskId >= taskList.size()) {
                        printHorizontalLine();
                        System.out.println("Task not found!");
                        printHorizontalLine();
                        break;
                    }
                    Task unmarkTask = taskList.get(taskId);
                    unmarkTask.unMark();
                    printHorizontalLine();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(unmarkTask);
                    printHorizontalLine();
                    break;
                }
                default:
                    printHorizontalLine();
                    taskList.add(new Task(command));
                    System.out.printf("added: %s%n", command);
                    printHorizontalLine();
                    break;
            }
        }

    }

    public static void printHorizontalLine() {
        System.out.println("____________________________________________________________");
    }

    public static void sayBye() {
        printHorizontalLine();
        System.out.println("Bye. Hope to see you again soon!");
        printHorizontalLine();
    }
}
