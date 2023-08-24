import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        greet();

        String input = sc.nextLine();
        while (!input.equals("bye")) {
            try {
                processCommand(input);
            } catch (LukeException e) {
                Util.displayMessage(e.getMessage());
            }
            input = sc.nextLine();
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
        String byeMsg = "Bye. Hope to see you again soon!";

        Util.displayMessage(byeMsg);
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
