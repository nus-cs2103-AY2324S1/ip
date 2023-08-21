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
            } else if (command.split(" ", 2).length > 1) {
                try {
                    if (Duke.checkMark(command) == false) {
                        addTask(command);
                    }
                } catch (RuntimeException e) {
                    System.out.println(e.getMessage());
                } catch (DukeException e) {
                    System.out.println("Error! " + e.getMessage());
                }
            } else {
                try {
                    addTask(command);
                } catch (DukeException e) {
                    System.out.println("Error! " + e.getMessage());
                }
            }

        }
        scanner.close();
    }

    public static boolean checkMark(String command) throws RuntimeException {
        String[] parts = command.split(" ", 2);
        if (parts[0].equalsIgnoreCase("mark")) {
            try {
                tasks[Integer.valueOf(parts[1]) - 1].markTask();
            } catch (RuntimeException e) {
                throw new RuntimeException("The task does not exist in this list!");
            }
            return true;
        } else if (parts[0].equalsIgnoreCase("unmark")) {
            try {
                tasks[Integer.valueOf(parts[1]) - 1].unmarkTask();
            } catch (RuntimeException e) {
                throw new RuntimeException("The task does not exist in this list! Pick a number where a task exist!d");
            }
            return true;
        }
        return false;
    }

    private static void addTask(String description) throws DukeException {
        String[] parts = description.split(" ", 2);
        if (parts.length < 2) {
            throw new DukeException("You inputted an invalid command! Please try deadline, todo or event :)");
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
                throw new DukeException("You inputted an invalid command! Please try deadline, todo or event :)");
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

