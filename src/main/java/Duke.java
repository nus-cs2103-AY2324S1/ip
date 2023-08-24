import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm lippy the wombat\n" + "What can I do for you?\n");
        Scanner scanner = new Scanner(System.in);
        List<Task> tasks = new ArrayList<>();
        while (true) {
            String input = scanner.nextLine();
            if (Objects.equals(input, "bye")) {
                break;
            }
            if (Objects.equals(input, "list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    int num = i + 1;
                    System.out.println(num + ". " + tasks.get(i));
                }
                continue;
            }
            if (input.startsWith("mark ") || input.startsWith("unmark ")) {
                String[] parts = input.split(" ");
                if (parts.length == 2) {
                    int index = Integer.parseInt(parts[1]);
                    if (index >= 1 && index <= tasks.size()) {
                        Task thisTask = tasks.get(index - 1);
                        tasks.get(index - 1).toggleDone();
                        if (thisTask.getDone()) {
                            System.out.println("Nice! I've marked this task as done: " + "\n" + thisTask);
                        } else {
                            System.out.println("OK, I've marked this task as not done yet: " + "\n" + thisTask);
                        }
                    }
                }
                continue;
            }
            Task task = new Task(input);
            tasks.add(task);
            System.out.println("added: " + input);
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
