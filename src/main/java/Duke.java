import java.util.Objects;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws DukeException {
        // Send welcome message
        System.out.println(
                "    ____________________________________________________________\n" +
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
            try {
                String userInput = scan.nextLine().trim();
                if (Objects.equals(userInput, "bye")) {
                    System.out.println(
                            "    ____________________________________________________________\n" +
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
                            inputArrayString += "     " + num + ". " + currentTask.toString() + "\n";
                        } else {
                            break;
                        }
                    }
                    System.out.println(
                            "    ____________________________________________________________\n" +
                                    "     Here are the tasks in your list:\n" +
                                    inputArrayString +
                                    "    ____________________________________________________________\n");

                } else if (userInput.startsWith("mark")) {
                    String[] parts = userInput.split("\\s+");
                    int taskIndex = Integer.parseInt(parts[1]) - 1;
                    Task currentTask = taskArray[taskIndex];
                    currentTask.markDone();
                    System.out.println(
                            "    ____________________________________________________________\n" +
                                    "     Nice! I've marked this task as done:\n" +
                                    "       " + currentTask.toString() + "\n" +
                                    "    ____________________________________________________________\n");

                } else if (userInput.startsWith("unmark")) {
                    String[] parts = userInput.split("\\s+");
                    int taskIndex = Integer.parseInt(parts[1]) - 1;
                    Task currentTask = taskArray[taskIndex];
                    currentTask.unmarkDone();
                    System.out.println(
                            "    ____________________________________________________________\n" +
                                    "     OK, I've marked this task as not done yet:\n" +
                                    "       " + currentTask.toString() + "\n" +
                                    "    ____________________________________________________________\n");
                } else if (userInput.startsWith("todo")) {
                    String taskName = userInput.substring("todo".length());
                    taskArray[inputNum] = new ToDo(taskName.trim());
                    System.out.println(
                            "    ____________________________________________________________\n" +
                                    "     Got it. I've added this task:\n" +
                                    "       " + taskArray[inputNum].toString() + "\n" +
                                    "     Now you have " + (inputNum + 1) + " task(s) in the list.\n" +
                                    "    ____________________________________________________________\n");
                    inputNum++;

                } else if (userInput.startsWith("deadline")) {
                    String description = userInput.substring("deadline".length());
                    String[] parts = description.split(" /by ");
                    String taskName = parts[0];
                    String by = parts[1];
                    taskArray[inputNum] = new Deadline(taskName, by);
                    System.out.println(
                            "    ____________________________________________________________\n" +
                                    "     Got it. I've added this task:\n" +
                                    "       " + taskArray[inputNum].toString() + "\n" +
                                    "     Now you have " + (inputNum + 1) + " task(s) in the list.\n" +
                                    "    ____________________________________________________________\n");
                    inputNum++;

                } else if (userInput.startsWith("event ")) {
                    String description = userInput.substring("event ".length());
                    String[] partsA = description.split(" /from ");
                    String taskName = partsA[0];
                    String[] partsB = partsA[1].split(" /to ");
                    String start = partsB[0];
                    String end = partsB[1];
                    taskArray[inputNum] = new Event(taskName, start, end);
                    System.out.println(
                            "    ____________________________________________________________\n" +
                                    "     Got it. I've added this task:\n" +
                                    "       " + taskArray[inputNum].toString() + "\n" +
                                    "     Now you have " + (inputNum + 1) + " task(s) in the list.\n" +
                                    "    ____________________________________________________________\n");
                    inputNum++;

                } else {
                    throw new InvalidInputException("Invalid Input");
                }
            } catch (InvalidInputException | EmptyToDoException e) {
                System.out.println(e);
            }
        }
        scan.close();
    }
}
