import java.util.Scanner;

public class ChatBot {
    private final TaskList taskList ;

    public ChatBot() {
        this.taskList = new TaskList();
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

            if (input.equals("bye")) {
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
