import java.util.Objects;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        // Send welcome message
        System.out.println("    ____________________________________________________________\n" +
                "     Hello! I'm BbabBBB\n" +
                "     What can I do for you?\n" +
                "    ____________________________________________________________\n");

        // Implement function to read user input via keyboard
        Scanner scan = new Scanner(System.in);
        Task[] taskArray = new Task[100];
        boolean[] statusArray = new boolean[100];

        int inputNum = 0;

        // Bot exits when user inputs "bye" and echoes when user inputs anything else
        while (true) {
            String userInput = scan.nextLine();
            if (Objects.equals(userInput, "bye")) {
                System.out.println("    ____________________________________________________________\n" +
                        "     Bye. Hope to see you again soon!\n" +
                        "    ____________________________________________________________\n");
                break;

            } else if (Objects.equals(userInput, "list")) {
                String inputArrayString = "";
                for (int i = 0; i < taskArray.length; i++) {
                    Task currentTask = taskArray[i];
                    if (taskArray[i] != null) {
                        String taskName = taskArray[i].getName();
                        boolean taskStatus = taskArray[i].getStatus();
                        int num = i + 1;
                        inputArrayString += num + currentTask.statusString() + currentTask.getName();
                    } else {
                        break;
                    }
                }
                System.out.println("    ____________________________________________________________\n" +
                        inputArrayString +
                        "    ____________________________________________________________\n");

            } else {
                Task task = new Task(userInput);
                taskArray[inputNum] = task;
                System.out.println("    ____________________________________________________________\n" +
                        "    added: " + userInput + "\n" +
                        "    ____________________________________________________________\n");
                inputNum++;
            }
        }
        scan.close();
    }
}
