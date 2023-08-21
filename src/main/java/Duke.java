import java.util.Scanner;
public class Duke {

    private static Task[] tasks = new Task[100];
    private static int position = 0;

    public static void start() {
        String intro = "Hi! This is your AI assistant LoyBoy!\n";
        String question = "What can I do for you today?";
        System.out.println(intro + question);
        Scanner scanner = new Scanner(System.in);


        while (true) {
            String command = scanner.nextLine();
            if (command.equalsIgnoreCase("bye")) {
                String outro = "I wish you a pleasant day ahead, goodbye!\n";
                System.out.println(outro);
                break;
            } else if (command.equalsIgnoreCase("list")) {
                listTask();
            } else if (command.length() > 5) {
                if (command.substring(0,4).equalsIgnoreCase("mark")) {
                    tasks[Integer.valueOf(command.substring(command.length() - 1)) - 1].markTask();
                } else if (command.substring(0,6).equalsIgnoreCase("unmark")) {
                    tasks[Integer.valueOf(command.substring(command.length() - 1)) - 1].unmarkTask();
                } else {
                    addTask(command);
                }
            } else {
                addTask(command);
            }

        }
        scanner.close();
    }

    private static void addTask(String description) {
        String[] parts = description.split(" ", 2);
        if (parts.length < 2) {
            System.out.println("You inputted an invalid command! Please try again :)");
        } else {
            String taskType = parts[0].toLowerCase();
            String taskDetails = parts[1].toLowerCase();
            Task task = parseTask(taskType, taskDetails);
            if (task != null) {
                tasks[position] = task;
                position++;
                System.out.println("You added '" + tasks[position - 1] + "' to the list!"
                        + "\nNow you have " + position + " task(s) in the list!");
            } else {
                System.out.println("You inputted an invalid command! Please try again :)");
            }

        }
    }

    private static Task parseTask(String taskType, String taskDetails) {
        if (taskType.equalsIgnoreCase("todo")) {
            return new ToDoTask(taskDetails);
        } else if (taskType.equalsIgnoreCase("deadline")) {
            return DeadlineTask.parseDeadline(taskDetails);
        } else if (taskType.equalsIgnoreCase("event")) {
            return EventTask.parseEvent(taskDetails);
        } else {
            return null;
        }
    }
    private static void listTask() {
        if (position == 0) {
            System.out.println("List is empty!");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int item = 0; tasks[item] != null; item++)  {
                System.out.println(item + 1 + ". " + tasks[item]);
            }
        }

    }



    public static void main(String[] args) {
        Duke.start();


    }
}

