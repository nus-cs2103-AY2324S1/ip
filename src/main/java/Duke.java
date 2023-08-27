import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
public class Duke {

    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void start() {
        String intro = "Hi! This is your AI assistant LoyBoy!\n";
        String question = "What can I do for you today?";
        System.out.println(intro + question);
        Duke.loadTasks();

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
        Duke.saveTasks();
    }

    private static void loadTasks() {
        Storage storage = new Storage();
        try {
            tasks = storage.loadTask();
        } catch (IOException e) {
            System.out.println("Error! Cannot load task from data file");
        }
    }

    private static void saveTasks() {
        Storage storage = new Storage();
        try {
            storage.saveTasks(tasks);
        } catch (IOException e) {
            System.out.println("Error! Cannot save task to data file");
        }
    }

    public static boolean checkMark(String command) throws RuntimeException {
        String[] parts = command.split(" ", 2);
        try {
            if (parts[0].equalsIgnoreCase("mark")) {
                tasks.get(Integer.valueOf(parts[1]) - 1).markTask();
                return true;
            } else if (parts[0].equalsIgnoreCase("unmark")) {
                tasks.get(Integer.valueOf(parts[1]) - 1).unmarkTask();
                return true;
            } else if (parts[0].equalsIgnoreCase("delete")) {
                Task removedTask = tasks.remove(Integer.valueOf(parts[1]) - 1);
                System.out.println("Yes Sir. I've removed the following task\n" + removedTask + "\nNow you" +
                        " have " + tasks.size() + " task(s) in the list!");
                return true;
            }
        } catch (IndexOutOfBoundsException e) {
            throw new RuntimeException("The task does not exist in this list! Pick a number where a task exist!");
        } catch (NumberFormatException e) {
            throw new RuntimeException("Please pick a number instead of using letters!");
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
                tasks.add(task);
                System.out.println("You added '" + tasks.get(tasks.size() - 1) + "' to the list!"
                        + "\nNow you have " + tasks.size() + " task(s) in the list!");
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
        if (tasks.size() == 0) {
            System.out.println("List is empty!");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int item = 0; item < tasks.size(); item++)  {
                System.out.println(item + 1 + ". " + tasks.get(item));
            }
        }

    }

    public static void main(String[] args) {
        Duke.start();
    }
}

