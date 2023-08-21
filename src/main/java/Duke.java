import java.util.Scanner;

public class Duke {
    private static final String NAME = "404";
    private static final String INDENT = "     ";
    private static final int STORE_SIZE = 100;
    private static Task[] tasks = new Task[STORE_SIZE];
    private static int task_pointer = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        printLine();
        String greeting = String.format("%sHello! I'm %s%n%sWhat can I do for you?",
                                        INDENT, NAME, INDENT);
        System.out.println(greeting);
        printLine();
        System.out.println();
        String text = sc.nextLine();
        String[] split = text.split(" ");
        String command = split[0];
        while (!command.equals("bye")) {
            printLine();
            switch(command) {
                case "list":
                    listTask();
                    break;

                case "mark":
                    markAndUnmarkTask(text, true);
                    break;

                case "unmark":
                    markAndUnmarkTask(text, false);
                    break;

                case "todo":
                    String todoTask = text.substring(text.indexOf(" ") + 1);
                    Task todo = new Todo(todoTask);
                    addTask(todo);
                    break;

                case "deadline":
                    String deadlineTask = text.substring(text.indexOf(" ") + 1,
                                                         text.indexOf("/by") - 1);
                    String by = text.substring(text.indexOf("/by") + 4);
                    Task deadline = new Deadline(deadlineTask, by);
                    addTask(deadline);
                    break;

                case "event":
                    String eventTask = text.substring(text.indexOf(" ") + 1,
                            text.indexOf("/from") - 1);
                    String from = text.substring(text.indexOf("/from") + 6,
                            text.indexOf("/to") - 1);
                    String to = text.substring(text.indexOf("/to") + 4);
                    Task event = new Event(eventTask, from, to);
                    addTask(event);
                    break;
            }
            printLine();
            System.out.println();
            text = sc.nextLine();
            split = text.split(" ");
            command = split[0];
        }
        printLine();
        System.out.printf("%sBye. Hope to see you again soon!%n", INDENT);
        printLine();
        sc.close();
    }

    private static void printLine() {
        String line =  "    ____________________________________________________________";
        System.out.println(line);
    }

    private static void addTask(Task task) {
        tasks[task_pointer] = task;
        task_pointer++;
        System.out.printf("%sGot it. I've added this task:%n" +
                          "%s  %s%n" +
                          "%sNow you have %d tasks in the list.%n",
                          INDENT, INDENT, task, INDENT, task_pointer);
    }

    private static void listTask() {
        if (task_pointer == 0) {
            System.out.printf("%sSorry, there is nothing to list!%n", INDENT);
            return;
        } else {
            System.out.printf("%sHere are the tasks in your list:%n", INDENT);
        }

        for (int i = 0; i < task_pointer; i++) {
            if (tasks[i] == null) {
                break;
            } else {
                String out = String.format("%s%d.%s", INDENT, i + 1, tasks[i]);
                System.out.println(out);
            }
        }
    }

    private static void markAndUnmarkTask(String text, boolean mark) {
        String message;
        String task;
        int index = Integer.valueOf(text.substring(text.indexOf(' ') + 1)) - 1;
        if (mark) {
            message = "Nice! I've marked this task as done:";
            task = tasks[index].markAsDone();
        } else {
            message = "OK, I've marked this task as not done yet:";
            task = tasks[index].markAsNotDone();
        }
        System.out.printf("%s%s%n%s  %s%n", INDENT, message, INDENT, task);
    }
}
