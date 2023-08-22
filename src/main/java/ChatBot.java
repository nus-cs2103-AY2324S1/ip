import java.util.Scanner;

public class ChatBot {
    private final TaskList taskList ;

    public ChatBot() {
        this.taskList = new TaskList();
    }

    public void markTaskByBot(int taskIndex) {
        taskList.markTaskAsDone(taskIndex - 1);
        System.out.println("____________________________________________________________\n" +
                " Nice! I've marked this task as done:\n" +
                taskList.getTaskDetails(taskIndex - 1) +
                "\n____________________________________________________________");
    }

    public void unmarkTaskByBot(int taskIndex) {
        taskList.markTaskAsNotDone(taskIndex - 1);
        System.out.println("____________________________________________________________\n" +
                " OK, I've marked this task as not done yet:\n" +
                taskList.getTaskDetails(taskIndex - 1) +
                "\n____________________________________________________________");

    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        String helloMessage = "____________________________________________________________\n" +
                " Hello! I'm Najib\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n";

        String byeMessage = "____________________________________________________________\n" +
                " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________";

        System.out.println(helloMessage);
        String input;
        String printMessage;

        while (true) {
            input = scanner.nextLine();
            String[] parts = input.split(" ", 2);
            String command = parts[0];
            int taskIndex;

            if (command.equals("mark") && parts.length > 1) {
                taskIndex = Integer.parseInt(parts[1]);
                this.markTaskByBot(taskIndex);
            } else if (command.equals("unmark") && parts.length > 1) {
                taskIndex = Integer.parseInt(parts[1]);
                this.unmarkTaskByBot(taskIndex);
            } else if (input.equals("bye")) {
                System.out.println(byeMessage);
                break;  // Exit the loop when user types "bye"
            } else if (input.equals("list")) {
                taskList.displayTasks();
            } else {
                taskList.addTask(input);
                printMessage = "____________________________________________________________\n" +
                        " added: " + input +
                        "\n____________________________________________________________";
                System.out.println(printMessage);
            }
        }

        scanner.close();
    }
}
