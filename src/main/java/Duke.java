import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        boolean running = true;
        Scanner scanner = new Scanner(System.in);
        TaskStorage taskStorage = new TaskStorage();
        ArrayList<Task> tasks;

        // Load tasks from TaskStorage
        try {
            tasks = taskStorage.loadExistingTasks();
        } catch (DukeException e) {
            System.out.printf("[!] %s\n", e.getMessage());
            return;
        }

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Pong");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        while (running) {
            System.out.print("You:  ");
            String userInput = scanner.nextLine();

            try {
                Parser parser = Parser.from(userInput);
                running = Duke.handleInput(parser, tasks);
                taskStorage.storeTasks(tasks);
            } catch (DukeException e) {
                System.out.printf("[!] %s\n", e.getMessage());
            }
        }

        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    private static boolean handleInput(Parser parser, ArrayList<Task> tasks) throws DukeException {
        switch (parser.getCommand()) {
        case "bye":
            return false;
        case "list":
            System.out.println("Pong: ");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.printf("    %d. %s\n", i+1, tasks.get(i));
            }
            break;
        case "mark":
            int index = parser.getArgAsInt() - 1;
            tasks.get(index).markDone();
            System.out.println("Pong: I've marked this task as done.");
            System.out.printf("    %s\n", tasks.get(index));
            break;
        case "unmark":
            index = parser.getArgAsInt() - 1;
            tasks.get(index).unmarkDone();
            System.out.println("Pong: I've marked this task as not done.");
            System.out.printf("    %s\n", tasks.get(index));
            break;
        case "delete":
            index = parser.getArgAsInt() - 1;
            Task task = tasks.remove(index - 1);
            System.out.printf("Pong: [Deleted] %s\n", task);
            break;
        case "todo":
            String todoName = parser.getArg();
            if (todoName == null || todoName.equals("")) {
                throw new DukeException("Todo name cannot be empty");
            }
            tasks.add(new Todo(todoName));
            System.out.printf("Pong: [Added] %s\n", tasks.get(tasks.size() - 1));
            break;
        case "deadline":
            String deadlineName = parser.getArg();
            if (deadlineName == null || deadlineName.equals("")) {
                throw new DukeException("Deadline name cannot be empty");
            }

            LocalDateTime deadline;
            deadline = parser.getOptArgAsDateTime("by");

            if (deadline == null) {
                throw new DukeException("Use /by to specify deadline date");
            }

            tasks.add(new Deadline(deadlineName, deadline));
            System.out.printf("Pong: [Added] %s\n", tasks.get(tasks.size() - 1));
            break;
        case "event":
            String eventName = parser.getArg();
            if (eventName == null || eventName.equals("")) {
                throw new DukeException("Deadline name cannot be empty");
            }

            LocalDateTime from, to;
            from = parser.getOptArgAsDateTime("from");
            to = parser.getOptArgAsDateTime("to");

            if (from == null || to == null) {
                throw new DukeException("Use /from and /to to specify event duration");
            }

            tasks.add(new Event(eventName, from, to));
            System.out.printf("Pong: [Added] %s\n", tasks.get(tasks.size() - 1));
            break;
        }

        return true;
    }
}
