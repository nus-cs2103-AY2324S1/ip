import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Task[] tasklist = new Task[100];
        AtomicInteger count = new AtomicInteger(0);

        System.out.println("____________________________________________________________\n" +
                " Hello! I'm Charlie\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n");

        while (true) {
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                exitBot();
                break;
            } else if (input.equals("list")) {
                printlist(tasklist, count.get());

            } else if (input.startsWith("mark")) {
                markResponse(input, tasklist);

            } else if (input.startsWith("unmark")) {
                unmarkResponse(input, tasklist);

            } else if (input.startsWith("todo")) {
                addTodo(input, tasklist, count);

            } else if (input.startsWith("deadline")) {
                addDeadline(input, tasklist, count);

            } else if (input.startsWith("event")) {
                addEvent(input, tasklist, count);
            }


        }

        scanner.close();


    }
    private static void printlist(Task[] arr, int count) {
        System.out.println("____________________________________________________________\n");
        for (int i =0; i < count; i++) {
            System.out.printf("%d.) %s%n", i + 1, arr[i].toString());
        }
        System.out.println("____________________________________________________________\n");
    }

    private static void markResponse(String input, Task[] tasklist) {
        int spaceIndex = input.indexOf(" ");
        int result = Integer.parseInt(input.substring(spaceIndex + 1)) - 1;
        tasklist[result].mark();
        System.out.println("____________________________________________________________\n" +
                "Nice! I've marked this task as done: \n" +
                tasklist[result].toString() +
                "\n____________________________________________________________\n");
    }

    private static void unmarkResponse(String input, Task[] tasklist) {
        int spaceIndex = input.indexOf(" ");
        int result = Integer.parseInt(input.substring(spaceIndex + 1)) - 1;
        tasklist[result].unmark();
        System.out.println("____________________________________________________________\n" +
                "OK, I've marked this task as not done yet: \n" +
                tasklist[result].toString() +
                "\n____________________________________________________________\n");
    }
    private static void addTodo(String input, Task[] tasklist, AtomicInteger count) {
        int spaceIndex = input.indexOf(" ");
        String task = input.substring(spaceIndex + 1);
        Task newTask = new ToDo(task);
        tasklist[count.getAndIncrement()] = newTask;
        System.out.println("____________________________________________________________\n" +
                "Got it. I've added this task:\n" +
                newTask.toString() +
                "\n Now you have " + count + " tasks in the list." +
                "\n____________________________________________________________\n");
    }

    private static void addDeadline(String input, Task[] tasklist, AtomicInteger count) {
        int spaceIndex = input.indexOf(" ");
        int dateIndex = input.indexOf("/by");
        String task = input.substring(spaceIndex + 1, dateIndex);
        String deadline = input.substring(dateIndex + 4);
        Task newTask = new Deadline(task, deadline);
        tasklist[count.getAndIncrement()] = newTask;
        System.out.println("____________________________________________________________\n" +
                "Got it. I've added this task:\n" +
                newTask.toString() +
                "\n Now you have " + count + " tasks in the list." +
                "\n____________________________________________________________\n");
    }

    private static void addEvent(String input, Task[] tasklist, AtomicInteger count) {
        int spaceIndex = input.indexOf(" ");
        int startIndex = input.indexOf("/from");
        int endIndex = input.indexOf("/to");
        String task = input.substring(spaceIndex + 1, startIndex);
        String start = input.substring(startIndex + 6, endIndex);
        String end = input.substring(endIndex + 4);
        Task newTask = new Event(task, start, end);
        tasklist[count.getAndIncrement()] = newTask;
        System.out.println("____________________________________________________________\n" +
                "Got it. I've added this task:\n" +
                newTask.toString() +
                "\n Now you have " + count + " tasks in the list." +
                "\n____________________________________________________________\n");
    }

    private static void exitBot() {
        System.out.println("____________________________________________________________\n" +
                " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n");
    }
}

