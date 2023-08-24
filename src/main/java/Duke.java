import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Task> tasks = new ArrayList<>();
        int num = 1;
        // intro
        System.out.println("Hello! I'm lippy the wombat\n" + "What can I do for you?");
        // response
        while (true) {
            String input = scanner.nextLine();
            if (Objects.equals(input, "bye")) {
                break;
            }
            if (Objects.equals(input, "list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i ++) {
                    int index = i + 1;
                    String task = String.valueOf(tasks.get(i));
                    System.out.println(index + ". "+ task);
                }
                continue;
            }
            if (input.startsWith("mark ") || input.startsWith("unmark ")) {
                String[] parts = input.split(" ");
                if (parts.length == 2) {
                    int index = Integer.parseInt(parts[1]);
                    try {
                        if (index >= 1 && index <= tasks.size()) {
                            Task thisTask = tasks.get(index - 1);
                            tasks.get(index - 1).toggleDone();
                            if (thisTask.getDone()) {
                                System.out.println("Nice! I've marked this task as done:" + "\n" + thisTask);
                            } else {
                                System.out.println("OK, I've marked this task as not done yet:" + "\n" + thisTask);
                            }
                        } else {
                            System.out.println("Invalid Index");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("NumberFormatException");
                    }
                }
                continue;
            }
            if (input.startsWith("todo ")) {
                Task todo = new Todo(input);
                tasks.add(todo);
                System.out.println("Got it. I've added this task:\n" + todo + "\n" +
                        "Now you have " + tasks.size() + " tasks in the list.");
            }
            if (input.startsWith("deadline ")) {
                String[] arr1 = input.split("/");
                String[] arr2 = arr1[1].split("by ");
                String date = arr2[1];
                Task deadline = new Deadline(arr1[0], date);
                tasks.add(deadline);
                System.out.println("Got it. I've added this task:\n" + deadline + "\n"
                        + "Now you have " + tasks.size() + " tasks in the list.");
            }
            if (input.startsWith("event ")) {
                String[] arr1 = input.split("/from "); // [0]: name, [1]: timeframe
                String[] arr2 = arr1[1].split("/to "); // [0] from:..., [1] to:...
                Task event = new Event(arr1[0], arr2);
                tasks.add(event);
                System.out.println("Got it. I've added this task:\n" + event + "\n" +
                        "Now you have " + tasks.size() + " tasks in the list.");
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
