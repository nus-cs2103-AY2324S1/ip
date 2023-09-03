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
                running = Duke.handleInput(userInput, tasks);
                taskStorage.storeTasks(tasks);
            } catch (DukeException e) {
                System.out.printf("[!] %s\n", e.getMessage());
            }
        }

        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    private static boolean handleInput(String userInput, ArrayList<Task> tasks) throws DukeException {
        if (userInput.equals("bye")) {
            return false;
        } else if (userInput.equals("list")) {
            System.out.println("Pong: ");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.printf("    %d. %s\n", i+1, tasks.get(i));
            }
        } else if (userInput.startsWith("mark")) {
            String userIndex = userInput.substring(5);
            int index = Integer.parseInt(userIndex) - 1;
            tasks.get(index).markDone();
            System.out.println("Pong: I've marked this task as done.");
            System.out.printf("    %s\n", tasks.get(index));
        } else if (userInput.startsWith("unmark")) {
            String userIndex = userInput.substring(7);
            int index = Integer.parseInt(userIndex) - 1;
            tasks.get(index).unmarkDone();
            System.out.println("Pong: I've marked this task as not done.");
            System.out.printf("    %s\n", tasks.get(index));
        } else if (userInput.startsWith("todo")) {
            try {
                tasks.add(new Todo(userInput.substring(5)));
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException("Todo name cannot be empty.");
            }
            System.out.printf("Pong: [Added] %s\n", tasks.get(tasks.size() - 1));
        } else if (userInput.startsWith("deadline")) {
            String[] tokens;
            try {
                tokens = userInput.substring(9).split(" /by ");
                tasks.add(new Deadline(tokens[0], tokens[1]));
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException("Deadline name cannot be empty.");
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("Use /by to specify deadline date.");
            }
            System.out.printf("Pong: [Added] %s\n", tasks.get(tasks.size() - 1));
        } else if (userInput.startsWith("event")) {
            String[] tokens;
            try {
                tokens = userInput.substring(6).split(" /from ");
                String[] duration = tokens[1].split(" /to ");
                tasks.add(new Event(tokens[0], duration[0], duration[1]));
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException("Event name cannot be empty.");
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("Use /from and /to to specify event duration.");
            }
            System.out.printf("Pong: [Added] %s\n", tasks.get(tasks.size() - 1));
        } else if (userInput.startsWith("delete")) {
            int deleteIndex = Integer.parseInt(userInput.substring(7));
            Task task = tasks.remove(deleteIndex - 1);
            System.out.printf("Pong: [Deleted] %s\n", task);
        }

        return true;
    }
}
