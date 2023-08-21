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
            String scannerIn = scanner.nextLine();
            String[] userIn = scannerIn.split(" ");
            String command = userIn[0];
            printHorizontalLine();
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
                    break;
                case "mark": {
                    int taskId = Integer.parseInt(userIn[1]) - 1;
                    if (taskId >= taskList.size()) {
                        System.out.println("Task not found!");
                        break;
                    }
                    Task doneTask = taskList.get(taskId);
                    doneTask.mark();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(doneTask);
                    break;
                }
                case "unmark": {
                    int taskId = Integer.parseInt(userIn[1]) - 1;
                    if (taskId >= taskList.size()) {
                        System.out.println("Task not found!");
                        break;
                    }
                    Task unmarkTask = taskList.get(taskId);
                    unmarkTask.unMark();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(unmarkTask);
                    break;
                }
                case "todo":
                case "deadline":
                case "event":
                    Task newTask = null;
                    switch (command) {
                        case "deadline":
                            newTask = Deadline.initializeFromInput(scannerIn);
                            break;
                        case "todo":
                            newTask = Todo.initializeFromInput(scannerIn);
                            break;
                        case "event":
                            newTask = Event.initializeFromInput(scannerIn);
                            break;
                    }
                    taskList.add(newTask);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(newTask);
                    System.out.printf("Now you have %d tasks in the list.\n", taskList.size());
                    break;
            }
            printHorizontalLine();

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
