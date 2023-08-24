import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Task[] tasklist = new Task[100];
        AtomicInteger count = new AtomicInteger(0);
        String dottedLine = "____________________________________________________________\n";

        System.out.println(dottedLine +
                "Hello! I'm Charlie\n" +
                "What can I do for you?\n" +
                dottedLine);

        while (true) {
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                exitBot();
                break;
            } else if (input.equals("list")) {
                printlist(tasklist, count.get());

            } else if (input.startsWith("mark")) {
                try {
                    markResponse(input, tasklist, count);
                } catch (DukeException e) {
                    System.out.println(dottedLine +
                            e.getMessage() +
                            "\n" + dottedLine);
                }


            } else if (input.startsWith("unmark")) {
                try {
                    unmarkResponse(input, tasklist, count);
                } catch (DukeException e) {
                    System.out.println(dottedLine +
                            e.getMessage() +
                            "\n" + dottedLine);
                }


            } else if (input.startsWith("todo")) {
                try {
                    addTodo(input, tasklist, count);
                } catch (DukeException e) {
                    System.out.println(dottedLine +
                            e.getMessage() +
                            "\n" + dottedLine);
                }


            } else if (input.startsWith("deadline")) {
                try {
                    addDeadline(input, tasklist, count);
                } catch (DukeException e) {
                    System.out.println(dottedLine +
                            e.getMessage() +
                            "\n" + dottedLine);
                }


            } else if (input.startsWith("event")) {
                try {
                    addEvent(input, tasklist, count);
                } catch (DukeException e) {
                    System.out.println(dottedLine +
                            e.getMessage() +
                            "\n" + dottedLine);
                }

            } else {
                try {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                } catch(DukeException e) {
                    System.out.println(dottedLine +
                            e.getMessage() +
                            "\n" + dottedLine);
                }

            }


        }

        scanner.close();


    }
    private static void printlist(Task[] arr, int count) {
        System.out.println("____________________________________________________________\n" +
                "Here are the tasks in your list:");
        for (int i =0; i < count; i++) {
            System.out.printf("%d. %s%n", i + 1, arr[i].toString());
        }
        System.out.println("____________________________________________________________\n");
    }

    private static void markResponse(String input, Task[] tasklist, AtomicInteger count) throws DukeException {
        int spaceIndex = input.indexOf(" ");
        int result = Integer.parseInt(input.substring(spaceIndex + 1)) - 1;
        if (result < 0 || (result + 1) > count.get()) {
            throw new DukeException("☹ OOPS!!! The task number is invalid.");
        }
        tasklist[result].mark();
        System.out.println("____________________________________________________________\n" +
                "Nice! I've marked this task as done:\n" +
                tasklist[result].toString() +
                "\n____________________________________________________________\n");
    }

    private static void unmarkResponse(String input, Task[] tasklist, AtomicInteger count) throws DukeException {
        int spaceIndex = input.indexOf(" ");
        int result = Integer.parseInt(input.substring(spaceIndex + 1)) - 1;
        if (result < 0 || (result + 1) > count.get()) {
            throw new DukeException("☹ OOPS!!! The task number is invalid.");
        }
        tasklist[result].unmark();
        System.out.println("____________________________________________________________\n" +
                "OK, I've marked this task as not done yet:\n" +
                tasklist[result].toString() +
                "\n____________________________________________________________\n");
    }
    private static void addTodo(String input, Task[] tasklist, AtomicInteger count) throws DukeException {
        int spaceIndex = input.indexOf(" ");

        if (spaceIndex == -1 || spaceIndex + 1 >= input.length()) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }

        String task = input.substring(spaceIndex + 1);
        Task newTask = new ToDo(task);
        tasklist[count.getAndIncrement()] = newTask;
        System.out.println("____________________________________________________________\n" +
                "Got it. I've added this task:\n" +
                newTask.toString() +
                "\nNow you have " + count + " tasks in the list." +
                "\n____________________________________________________________\n");
    }

    private static void addDeadline(String input, Task[] tasklist, AtomicInteger count) throws DukeException{
        int spaceIndex = input.indexOf(" ");
        if (spaceIndex == -1 || spaceIndex + 1 >= input.length()) {
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        }

        int dateIndex = input.indexOf("/by");
        if (dateIndex == -1 || dateIndex + 1 >= input.length()) {
            throw new DukeException("☹ OOPS!!! You forgot to include the deadline.");
        }

        String task = input.substring(spaceIndex + 1, dateIndex);
        String deadline = input.substring(dateIndex + 4);
        Task newTask = new Deadline(task, deadline);
        tasklist[count.getAndIncrement()] = newTask;
        System.out.println("____________________________________________________________\n" +
                "Got it. I've added this task:\n" +
                newTask.toString() +
                "\nNow you have " + count + " tasks in the list." +
                "\n____________________________________________________________\n");
    }

    private static void addEvent(String input, Task[] tasklist, AtomicInteger count) throws DukeException{
        int spaceIndex = input.indexOf(" ");
        if (spaceIndex == -1 || spaceIndex + 1 >= input.length()) {
            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
        }
        int startIndex = input.indexOf("/from");
        int endIndex = input.indexOf("/to");

        if (startIndex == -1 || endIndex == -1 || startIndex > endIndex) {
            throw new DukeException("☹ OOPS!!! Invalid event format.");
        }
        if (startIndex + 5 >= endIndex) {
            throw new DukeException("☹ OOPS!!! Missing event start date/time.");
        }
        if (endIndex + 3 >= input.length()) {
            throw new DukeException("☹ OOPS!!! Missing event end date/time.");
        }

        String task = input.substring(spaceIndex + 1, startIndex);
        String start = input.substring(startIndex + 6, endIndex);
        String end = input.substring(endIndex + 4);
        Task newTask = new Event(task, start, end);
        tasklist[count.getAndIncrement()] = newTask;
        System.out.println("____________________________________________________________\n" +
                "Got it. I've added this task:\n" +
                newTask.toString() +
                "\nNow you have " + count + " tasks in the list." +
                "\n____________________________________________________________\n");
    }

    private static void exitBot() {
        System.out.println("____________________________________________________________\n" +
                "Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n");
    }
}

