import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

/**
 * Duke is a chat bot that allows users to add, delete, mark, view tasks.
 */
public class Duke {

    /** State of bot Duke. */
    private boolean active = true;

    /** List of tasks stored. */
    private List<Task> list = new ArrayList<>();

    /** Duke greets the user. */
    private void greet() {
        System.out.println("Hello! I'm Aikent\n" + "What can I do for you?");
    }

    /** Duke says goodbye to users. */
    private void exit() {
        active = false;
        System.out.println("Bye. Hope to see you again soon!");
    }

    /** Prints out the list of tasks stored. */
    private void list() {
        System.out.println("Here are the tasks in your list:");
        for (int index = 0; index < this.list.size(); index++) {
            Task item = this.list.get(index);
            System.out.println((index + 1) + ". " + item.toString());
        }
    }

    /** Marks the task input by users .
     *
     * @param index Index of task to be marked.
     * */
    private void mark(int index) {
        try {
            this.list.get(index).markAsDone();
            System.out.println(this.list.get(index).toString());
        } catch (IndexOutOfBoundsException error) {
            throw new IllegalArgumentException("OOPS!!! I could not find any task in that position.");
        }
    }

    /**
     * Unmarks the task input by user.
     *
     * @param index Index of task to be unmarked.
     */
    private void unmark(int index) {
        try {
            this.list.get(index).markAsUndone();
            System.out.println(this.list.get(index).toString());
        } catch (IndexOutOfBoundsException error) {
            throw new IllegalArgumentException("OOPS!!! I could not find any task in that position.");
        }
    }

    /** Displays how many task are stored in the list currently. */
    private void printListSize() {
        System.out.println("Now you have " + this.list.size() + " tasks in the list.");
    }

    /**
     * Display what Duke will response to a given command by the user.
     *
     * @param msg The input command by the user.
     */
    private void respond(String msg) {
        String[] input = msg.split(" ", 2);
        String command = input[0];
        String specifications = input.length > 1 ? input[1] : "";
        switch (command) {
            case "bye":
                exit();
                break;
            case "list":
                list();
                break;
            case "mark":
                if (specifications.isEmpty()) {
                    throw new IllegalArgumentException("Please indicate task number.");
                }
                int index = Integer.parseInt(specifications) - 1;
                mark(index);
                break;
            case "unmark":
                if (specifications.isEmpty()) {
                    throw new IllegalArgumentException("Please indicate task number.");
                }
                int i = Integer.parseInt(specifications) - 1;
                unmark(i);
                break;
            case "todo":
                if (specifications.isEmpty()) {
                    throw new IllegalArgumentException("OOPS!!! The description of a todo cannot be empty.");
                }
                Task toDoTask = new ToDo(specifications);
                this.list.add(toDoTask);
                toDoTask.notice();
                this.printListSize();
                break;
            case "deadline":
                try {
                    String[] splits = specifications.split("/by", 2);
                    String description = splits[0];
                    String date = splits[1];
                    Task deadlineTask = new Deadline(description, date);
                    this.list.add(deadlineTask);
                    deadlineTask.notice();
                    this.printListSize();
                    break;
                } catch (ArrayIndexOutOfBoundsException error) {
                    throw new IllegalArgumentException("OOPS!!! The description of a deadline must have <task> /by <time>.");
                }
            case "event":
                try {
                    String[] split = specifications.split("/from", 2);
                    String event = split[0];
                    String[] timings = split[1].split("/to", 2);
                    String start = timings[0];
                    String end = timings[1];
                    Task eventTask = new Event(event, start, end);
                    this.list.add(eventTask);
                    eventTask.notice();
                    this.printListSize();
                    break;
                } catch (ArrayIndexOutOfBoundsException error) {
                    throw new IllegalArgumentException("OOPS!!! The description of an event must have <task> /from <start> /to <end>.");
                }
            case "delete":
                if (specifications.isEmpty()) {
                    throw new IllegalArgumentException("Please indicate task number.");
                }
                try {
                    Integer number = Integer.parseInt(specifications);
                    Task taskToRemove = this.list.get(number - 1);
                    this.list.remove(number - 1);
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(taskToRemove.toString());
                    this.printListSize();
                    break;
                } catch (IndexOutOfBoundsException error) {
                    throw new IllegalArgumentException("OOPS!!! I could not find any task in that position.");
                }
            default:
                throw new IllegalArgumentException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    public static void main(String[] args) {
        Duke bot = new Duke();
        Scanner sc = new Scanner(System.in);

        bot.greet();

        while (bot.active) {
            String input = sc.nextLine();
            try {
                bot.respond(input);
            } catch (Exception error) {
                System.out.println(error.getMessage());
            }
        }

        sc.close();
    }
}
