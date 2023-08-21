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
                        inputArrayString += "     " + num + ". " + currentTask.statusString() + " " + currentTask.getName() + "\n";
                    } else {
                        break;
                    }
                }
                System.out.println("    ____________________________________________________________\n" +
                        "     Here are the tasks in your list:\n" +
                        inputArrayString +
                        "    ____________________________________________________________\n");

            } else if (userInput.startsWith("mark")) {
                String[] parts = userInput.split("\\s+");
                int taskIndex = Integer.parseInt(parts[1]) - 1;
                Task currentTask = taskArray[taskIndex];
                currentTask.markDone();
                System.out.println("    ____________________________________________________________\n" +
                        "     Nice! I've marked this task as done:\n" +
                        "       " + currentTask.toString() + "\n" +
                        "    ____________________________________________________________\n");
            } else if (userInput.startsWith("unmark")) {
                String[] parts = userInput.split("\\s+");
                int taskIndex = Integer.parseInt(parts[1]) - 1;
                Task currentTask = taskArray[taskIndex];
                currentTask.unmarkDone();
                System.out.println("    ____________________________________________________________\n" +
                        "     OK, I've marked this task as not done yet:\n" +
                        "       " + currentTask.toString() + "\n" +
                        "    ____________________________________________________________\n");
            }
            else {
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
