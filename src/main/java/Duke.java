import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        List<Task> tasks = new ArrayList<>();

        String greeting = "Hello! I'm Toothless.\n" +
                "What can I do for you today?\n" +
                "---------------------------------";
        String farewell = "Goodbye. Hope to see you soon!\n" +
                "---------------------------------";

        String addTask = "A new task has been added!\n ";

        System.out.println(greeting);

        while (true) {

            String nextInput = scanner.nextLine().trim();
            String output = "";

            if (nextInput.equals("bye")) {
                break;

            } else if (nextInput.equals("list")) {
                System.out.println("Here's your list of tasks!\n");
                for (int i = 0; i < tasks.size(); i++) {
                    Task task = tasks.get(i);
                    output = (i + 1) + ": " + task.getTask();
                    System.out.println(output);
                }

            } else if (nextInput.contains("todo")) {
                String title = nextInput.split("todo ")[1];
                ToDo todo = new ToDo(title);
                tasks.add(todo);
                System.out.println(addTask + todo.getTask());

            } else if (nextInput.contains("deadline")) {
                String title = nextInput.split("deadline ")[1].split(" /by ")[0];
                String by = nextInput.split("deadline ")[1].split(" /by ")[1];
                Deadline deadline = new Deadline(title, by);
                tasks.add(deadline);
                System.out.println(addTask + deadline.getTask());

            } else if (nextInput.contains("event")) {
                String title = nextInput.split("event ")[1].split(" /from ")[0];
                String from = nextInput.split("event ")[1]
                        .split(" /from ")[1].split(" /to ")[0];
                String to = nextInput.split("event ")[1]
                        .split(" /from ")[1].split(" /to ")[1];
                Event event = new Event(title, from, to);
                tasks.add(event);
                System.out.println(addTask + event.getTask());

            } else if (nextInput.contains("unmark")) {
                int taskNum = Integer.valueOf(nextInput.split(" ")[1]);
                Task task = tasks.get(taskNum - 1);
                output = task.markAsUndone();
                System.out.println(output);

            } else if (nextInput.contains("mark")) {
                int taskNum = Integer.valueOf(nextInput.split(" ")[1]);
                Task task = tasks.get(taskNum - 1);
                output = task.markAsDone();
                System.out.println(output);

            } else {
                System.out.println(nextInput);
            }

            System.out.println("---------------------------------");
        }

        System.out.println(farewell);
    }
}
