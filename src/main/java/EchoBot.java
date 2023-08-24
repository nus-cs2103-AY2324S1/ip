import java.util.Scanner;
class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void mark() {
        isDone = true;
    }

    public void unmark() {
        isDone = false;
    }

    public String getDescription() {
        return description;
    }
}

public class EchoBot {
    public static int extractTaskNum(String userInput, String command) {
        String taskNumberStr = userInput.substring(command.length()).trim();
        return Integer.parseInt(taskNumberStr);
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String logo = "     ____        _        \n"
                + "    |  _ \\ _   _| | _____ \n"
                + "    | | | | | | | |/ / _ \\\n"
                + "    | |_| | |_| |   <  __/\n"
                + "    |____/ \\__,_|_|\\_\\___|\n";
        String horizontalLine = "   _____________________________________________\n";

        //Array to store the tasks
        Task[] tasks = new Task[100];
        int numOfTask = 0;

        System.out.println(horizontalLine + "    Hello! I'm EchoBot\n" + logo);
        System.out.println("    What can I do for you?\n" + horizontalLine);

        while(true) {
            // Read the user input
            String userInput = scanner.nextLine().trim();

            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println(horizontalLine + "    Bye. Hope to see you again soon!\n" + horizontalLine);
                break;
            } else if (userInput.equalsIgnoreCase("list")) {
                System.out.println(horizontalLine + "    Here are the tasks in your list:");

                for (int i = 0; i < numOfTask; i++) {
                    Task task = tasks[i];
                    System.out.println("    " + (i + 1) + "." + "[" + task.getStatusIcon() + "]" + " " + task.getDescription());
                }

                System.out.println(horizontalLine);
            } else if (userInput.startsWith("mark")) {
                int taskNum = extractTaskNum(userInput, "mark");

                if (taskNum >= 1 && taskNum <= numOfTask) {
                    Task task = tasks[taskNum-1];
                    task.mark();

                    System.out.println(horizontalLine + "    Nice! I've marked this task as done:");
                    System.out.println("      " + "[" + task.getStatusIcon() + "]" + " " + task.getDescription());
                    System.out.println(horizontalLine);
                }
            } else if (userInput.startsWith("unmark")) {
                int taskNum = extractTaskNum(userInput, "unmark");

                if (taskNum >= 1 && taskNum <= numOfTask) {
                    Task task = tasks[taskNum-1];
                    task.unmark();

                    System.out.println(horizontalLine + "    OK, I've marked this task as not done yet:");
                    System.out.println("      " + "[" + task.getStatusIcon() + "]" + " " + task.getDescription());
                    System.out.println(horizontalLine);
            }
        } else {
                //Store the task inputted by user
                tasks[numOfTask] = new Task(userInput);
                numOfTask++;

                System.out.println(horizontalLine + "    added: " + userInput + "\n" + horizontalLine);
            }
        }
    }
}
