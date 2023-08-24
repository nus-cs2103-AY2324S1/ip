import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Arrays;

public class Duke {
    private static Line line = new Line();
    public static void main(String[] args) {
        printGreetings();
        Scanner s = new Scanner(System.in);
        Tasks tasks = new Tasks();

        while (true) {
            String text = s.nextLine();
            if (text.equals("")) {
                System.out.println(line);
                System.out.println("    Please enter something :-)");
                System.out.println(line);
                continue;
            }

            if (text.equals("list")) {
                tasks.listTasks();
                continue;
            }

            if (text.startsWith("mark")) {
                String[] words = text.split(" ");
                try {
                    int number = Integer.parseInt(words[1]);
                    Task task = tasks.getTask(number);
                    if (task != null) {
                        task.mark(true);
                    }
                } catch (Exception ex) {
                    // ignore
                }
                continue;
            }

            if (text.startsWith("unmark")) {
                String[] words = text.split(" ");
                try {
                    int number = Integer.parseInt(words[1]);
                    Task task = tasks.getTask(number);
                    if (task != null) {
                        task.mark(false);
                    }
                } catch (Exception ex) {
                    // ignore
                }
                continue;
            }

            System.out.println(line);
            System.out.println("    added: " + text);
            tasks.addTask(new Task(text));
            System.out.println(line);

            if (text.equals("bye")) {
                break;
            }

        }
        printExit();
    }

    private static void printGreetings() {
        String logo = "     ____        _        \n"
                + "    |  _ \\ _   _| | _____ \n"
                + "    | | | | | | | |/ / _ \\\n"
                + "    | |_| | |_| |   <  __/\n"
                + "    |____/ \\__,_|_|\\_\\___|\n";
        System.out.println(line);
        System.out.println("    Hello from\n" + logo);
        System.out.println("    What can I do for you?");
        System.out.println(line);
    }

    private static void printExit() {
        System.out.println(line);
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println(line);
    }
}
