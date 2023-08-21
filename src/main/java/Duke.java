import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;

public class Duke {
    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<>();

        System.out.println("    Hello! \uD83C\uDF1F I'm Arona, your Virtual Assistant.\n    What can I do for you today? \uD83D\uDCAC\n");

        Scanner scanner = new Scanner(System.in);
        String echo = scanner.nextLine().toLowerCase().trim();

        while (!echo.equals("bye")) {
            if (echo.equals("list")) { // Lists all current tasks.

                System.out.println("    " + (tasks.size() == 1 ? "Yay! \uD83C\uDF1F You only have one task:" : "Here are your current tasks \uD83D\uDE0A: "));
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println("    " + (i + 1) + ". " + tasks.get(i));
                }
                System.out.println("");

            } else if (echo.startsWith("mark ")) { // Marks a task.
                int index = Integer.valueOf(echo.split(" ")[1]) - 1;
                Task task = tasks.get(index);
                task.mark();

                System.out.println("    Awesome! \uD83C\uDF89 I've marked this task as done:");
                System.out.println("    " + task + "\n");

            } else if (echo.startsWith("unmark ")) { // Unmarks a task.
                int index = Integer.valueOf(echo.split(" ")[1]) - 1;
                Task task = tasks.get(index);
                task.unmark();

                System.out.println("    Sure thing! \uD83D\uDC4C I've marked this task as not done yet:");
                System.out.println("    " + task + "\n");

            } else if (echo.startsWith("todo ")) { // Adds a todo task.
                String[] strings = echo.split(" ");
                String description = String.join(" ",Arrays.copyOfRange(strings, 1, strings.length));
                Todo todo = new Todo(description);
                tasks.add(todo);

                System.out.println("    Got it! \uD83D\uDE04 I've added this task:");
                System.out.println("      " + todo);
                System.out.println("    Now you have " + tasks.size() + (tasks.size() == 1 ? " task" : " tasks") + " in the list.\n");

            } else if (echo.startsWith("deadline ")) { // Adds a deadline task.
                String[] strings = echo.split(" ");
                int index = -1;

                for (int i = 0; i < strings.length; i++) {
                    if (strings[i].charAt(0) == '/') {
                        index = i;
                        strings[i] = strings[i].substring(1) + ":";
                        break;
                    }
                }

                String description = String.join(" ", Arrays.copyOfRange(strings, 1, index));
                String by = String.join(" ", Arrays.copyOfRange(strings, index, strings.length));
                Deadline deadline = new Deadline(description, by);
                tasks.add(deadline);

                System.out.println("    Got it! \uD83D\uDE04 I've added this task:");
                System.out.println("      " + deadline);
                System.out.println("    Now you have " + tasks.size() + (tasks.size() == 1 ? " task" : " tasks") + " in the list.\n");

            } else if (echo.startsWith("event ")) { // Adds an event task.
                String[] strings = echo.split(" ");
                int indexStart = -1;
                int indexEnd = -1;
                boolean first = true;

                for (int i = 0; i < strings.length; i++) {
                    if (strings[i].charAt(0) == '/') {
                        if (first) {
                            indexStart = i;
                            first = false;
                            strings[i] = strings[i].substring(1) + ":";
                        } else {
                            indexEnd = i;
                            strings[i] = strings[i].substring(1) + ":";
                            break;
                        }
                    }
                }

                String description = String.join(" ", Arrays.copyOfRange(strings, 1, indexStart));
                String from = String.join(" ", Arrays.copyOfRange(strings, indexStart, indexEnd));
                String to = String.join(" ", Arrays.copyOfRange(strings, indexEnd, strings.length));
                Event event = new Event(description, from, to);
                tasks.add(event);

                System.out.println("    Got it! \uD83D\uDE04 I've added this task:");
                System.out.println("      " + event);
                System.out.println("    Now you have " + tasks.size() + (tasks.size() == 1 ? " task" : " tasks") + " in the list.\n");
            }
            echo = scanner.nextLine().toLowerCase().trim(); // Reads next line
        }
        System.out.println("    Goodbye. See you soon! \uD83D\uDC3E"); // Exits the programme.
    }
}

