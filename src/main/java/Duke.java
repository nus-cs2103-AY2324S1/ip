import java.io.FileNotFoundException;
import java.util.Scanner;

public class Duke {
    private static final String SAVED_TASKS_FILEPATH = "./data/savedTasks.txt";
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        greet();
        try {
            Task.readSavedTasks(SAVED_TASKS_FILEPATH);
        } catch (LukeException e) {
            Util.displayMessage(e.getMessage());
        }

        String input = sc.nextLine();
        while (!input.equals("bye")) {
            try {
                processCommand(input);
            } catch (LukeException e) {
                Util.displayMessage(e.getMessage());
            }
            input = sc.nextLine();
        }

        try {
            Task.saveTasksToFile(SAVED_TASKS_FILEPATH);
        } catch (LukeException e) {
            Util.displayMessage(e.getMessage());
        }
        bye();
    }

    private static void processCommand(String command) throws LukeException {
        Task task;
        switch(command.split(" ")[0]) {
            case "list":
                Task.listTasks(command);
                break;
            case "mark":
                task = Task.markUnmarkTask(command);
                Util.displayMessage("Nice! I've marked this task as done: \n" + task);
                break;
            case "unmark":
                task = Task.markUnmarkTask(command);
                Util.displayMessage("OK, I've marked this task as not done yet: \n" + task);
                break;
            case "delete":
                task = Task.deleteTask(command);
                Util.displayMessage("Noted. I've removed this task: \n" + task);
                break;
            case "todo":
                // Fallthrough
            case "deadline":
                // Fallthrough
            case "event":
                task = Task.addTask(command);
                Util.displayMessage("added : " + task);
                break;
            default:
                throw new LukeException("I'm sorry, but I don't know what that means :-(");
        }
    }

    private static void bye() {
        Util.displayMessage("Bye. Hope to see you again soon!");
    }

    private static void greet() {
        String logo = " _           _        \n"
                    + "| |    _   _| | _____ \n"
                    + "| |   | | | | |/ / _ \\\n"
                    + "| |__ | |_| |   <  __/\n"
                    + "|____| \\__,_|_|\\_\\___|\n";
        String greetingMsg = "Hello! I'm Luke \n"
                        + "What can I do for you?\n";

        System.out.println("Hello from\n" + logo);
        Util.displayMessage(greetingMsg);
    }

}
