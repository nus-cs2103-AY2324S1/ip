import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Task> tasks = new ArrayList<>();
        greetings();
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                break;
            }
            if (input.startsWith("mark") || input.startsWith("unmark ")) {
                Tasks.handleMark(input, tasks);
                continue;
            }
            if (input.startsWith("todo")) {
                Tasks.handleTodo(input, tasks);
                continue;
            }
            if (input.startsWith("deadline")) {
                Tasks.handleDeadline(input, tasks);
                continue;
            }
            if (input.startsWith("event")) {
                Tasks.handleEvent(input, tasks);
                continue;
            }
            if (input.startsWith("delete")) {
                Tasks.handleDelete(input, tasks);
                continue;
            }
            if (Objects.equals(input, "list")) {
                Tasks.handleList(input, tasks);
                continue;
            }
            System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void greetings() {
        System.out.println("Hello! I'm lippy the wombat\n" + "What can I do for you?");
    }
}

