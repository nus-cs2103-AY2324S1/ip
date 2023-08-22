import java.util.Objects;
import java.util.Scanner;
public class Duke {
    private static int index = 0;
    private static Task[] tasks = new Task[100];

    public static void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < index; i++) {
            System.out.println((i + 1) + "." + tasks[i].toString());
        }
    }

    public static void addTask(Task task) {
        tasks[index++] = task;
        System.out.println("Got it, I've added this task:\n\t"  +
                task.toString() + "\n" +
                "Now you have " + index + " tasks in the list.");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String LOGO = "   /\\_/\\  \n" +
                "  ( o.o ) \n" +
                "   > ^ <\n";
        String GREETING = "Hello(@.@), I'm NiHao \nWhat can I do for you?";
        String EXIT = "Bye(T.T), Hope to see you again soon!";


        System.out.println(LOGO + GREETING);

        while (true) {
            String input = sc.nextLine();
            String command = input.split(" ")[0];
            if (Objects.equals(input, "exit")) {
                break;
            } else if (Objects.equals(command, "list")) {
                listTasks();
            } else if (Objects.equals(command, "mark")) {
                int i = Integer.parseInt(input.split(" ")[1]);
                tasks[i - 1].mark();
                System.out.println("Nice! I've marked this task as done: \n" +
                        "\t" + tasks[i - 1].toString());
            } else if (Objects.equals(command, "unmark")) {
                int i = Integer.parseInt(input.split(" ")[1]);
                tasks[i - 1].unmark();
                System.out.println("OK, I've marked this task as not done yet: \n" +
                        "\t" + tasks[i - 1].toString());
            } else if (Objects.equals(command, "todo")) {
                String des = input.split(" ", 2)[1];
                addTask(new Todo(des));
            } else if (Objects.equals(command, "deadline")) {
                String details = input.split(" ", 2)[1];
                String des = details.split(" /by ", 2)[0];
                String by = details.split(" /by ", 2)[1];
                addTask(new Deadline(des, by));
            } else if (Objects.equals(command, "event")) {
                String details = input.split(" ", 2)[1];
                String des = details.split(" /from ", 2)[0];
                String time = details.split(" /from ", 2)[1];
                String start = time.split(" /to ", 2)[0];
                String end = time.split(" /to ", 2)[1];
                addTask(new Event(des, start, end));
            }

            else {
                System.out.println("Unexpected Input, Try again!!");
                continue;
            }
        }
        System.out.println(EXIT);
    }
}
