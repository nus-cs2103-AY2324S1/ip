import java.util.Scanner;

public class Duke {
    private static final String HORIZONTAL_LINE = "____________________________________________________________";
    private Scanner sc = new Scanner(System.in);
    private Task[] tasks = new Task[100];
    private int countTasks = 0;

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

    public void printWithLines(String s) {
        System.out.println(HORIZONTAL_LINE + "\n" + s + "\n" + HORIZONTAL_LINE);
    }

    public void printTask(String s) {
        printWithLines("Got it. I've added this task:" + "\n"
                + s + "\n"
                + "Now you have " + this.countTasks + " tasks in the list.");
    }

    public void addToDo(String description) {
        tasks[countTasks] = new ToDo(description);
        countTasks++;
        printTask(tasks[countTasks - 1].toString());
    }

    public void addDeadline(String description, String by) {
        tasks[countTasks] = new Deadline(description, by);
        countTasks++;
        printTask(tasks[countTasks - 1].toString());
    }

    public void addEvent(String description, String from, String to) {
        tasks[countTasks] = new Event(description, from, to);
        countTasks++;
        printTask(tasks[countTasks - 1].toString());
    }

    public void printList() {
        System.out.println(HORIZONTAL_LINE);
        for (int i = 0; i < countTasks; i++) {
            System.out.println((i + 1) + ". " + tasks[i]);
        }
        System.out.println(HORIZONTAL_LINE);
    }

    public void run() {
        printWithLines("Hello, I'm Je-O" + "\n"
                + "What can I do for you?");
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                printWithLines("Bye. Hope to see you again soon!");
                break;
            } else if (input.equals("list")) {
                printList();
            } else if (input.startsWith("mark ")) {
                int index = Integer.parseInt(input.replace("mark ", ""));
                tasks[index - 1].markAsDone();
                printWithLines("Nice! I've marked this task as done:" + "\n"
                        + tasks[index - 1]);
            } else if (input.startsWith("unmark ")) {
                int index = Integer.parseInt(input.replace("unmark ", ""));
                tasks[index - 1].unmarkAsDone();
                printWithLines("OK, I've marked this task as not done yet:" + "\n"
                        + tasks[index - 1]);
            } else if (input.startsWith("todo ")) {
                addToDo(input.replace("todo ", ""));
            } else if (input.startsWith("deadline ")) {
                String[] split = input.replace("deadline ", "").split(" /by ");
                addDeadline(split[0], split[1]);
            } else if (input.startsWith("event ")) {
                String[] split = input.replace("event ", "").split(" /from | /to ");
                addEvent(split[0], split[1], split[2]);
            }
        }
    }
}
