import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String HORIZONTAL_LINE = "____________________________________________________________";
    private Scanner sc = new Scanner(System.in);
    private ArrayList<Task> tasks = new ArrayList<>();
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

    public void printDeleteTask(String s) {
        printWithLines("Noted. I've removed this task:" + "\n"
                + s + "\n"
                + "Now you have " + this.countTasks + " tasks in the list.");
    }

    public void addToDo(String input) throws DukeException {
        if (input.indexOf(" ") == -1) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        String description = input.substring(input.indexOf(" ") + 1);
        tasks.add(new ToDo(description));
        countTasks++;
        printTask(tasks.get(countTasks - 1).toString());
    }

    public void addDeadline(String input) throws DukeException {
        if (input.indexOf(" ") == -1) {
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        }
        String[] split = input.substring(input.indexOf(" ") + 1).split(" /by ");
        tasks.add(new Deadline(split[0], split[1]));
        countTasks++;
        printTask(tasks.get(countTasks - 1).toString());
    }

    public void addEvent(String input) throws DukeException {
        if (input.indexOf(" ") == -1) {
            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
        }
        String[] split = input.substring(input.indexOf(" ") + 1).split(" /from | /to ");
        tasks.add(new Event(split[0], split[1], split[2]));
        countTasks++;
        printTask(tasks.get(countTasks - 1).toString());
    }

    public void printList() {
        System.out.println(HORIZONTAL_LINE);
        for (int i = 0; i < countTasks; i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
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
                tasks.get(index - 1).markAsDone();
                printWithLines("Nice! I've marked this task as done:" + "\n"
                        + tasks.get(index - 1));
            } else if (input.startsWith("unmark ")) {
                int index = Integer.parseInt(input.replace("unmark ", ""));
                tasks.get(index - 1).unmarkAsDone();
                printWithLines("OK, I've marked this task as not done yet:" + "\n"
                        + tasks.get(index - 1));
            } else if (input.startsWith("todo")) {
                try {
                    addToDo(input);
                } catch (DukeException e) {
                    printWithLines(e.toString());
                }
            } else if (input.startsWith("deadline")) {
                try {
                    addDeadline(input);
                } catch (DukeException e) {
                    printWithLines(e.toString());
                }
            } else if (input.startsWith("event")) {
                try {
                    addEvent(input);
                } catch (DukeException e) {
                    printWithLines(e.toString());
                }
            } else if (input.startsWith("delete ")) {
                int index = Integer.parseInt(input.replace("delete ", ""));
                countTasks--;
                printDeleteTask(tasks.get(index - 1).toString());
                tasks.remove(index - 1);
            } else {
                printWithLines("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
    }
}
