import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Bot {
    List<Task> tasks = new ArrayList<Task>();

    public static enum Command {
        LIST,
        MARK,
        UNMARK,
        DELETE,
        TODO,
        DEADLINE,
        EVENT,
        BYE
    }

    public void printWelcomeMessage() {
        String name = "LINUS";
        MessagePrint.print(
                "Hello! I'm " + name + "\n"
                        + "What can I do for you?");
    }

    public void printExitMessage() {
        MessagePrint.print("Bye. Hope to see you again soon!");
    }

    public void list() {
        String listOfItems = "Here are the tasks in your list:\n";
        for (int i = 0; i < tasks.size(); ++i) {
            listOfItems += (i + 1) + "."
                    + tasks.get(i).toString() + "\n";
        }
        MessagePrint.print(listOfItems);
    }

    public void add(Task task) {
        tasks.add(task);
        int numOfTasks = tasks.size();
        MessagePrint.print("Got it. I've added this task:\n"
                + "  " + task + "\n"
                + "Now you have " + numOfTasks + " task" + (numOfTasks > 1 ? "s" : "") + " in the list.");
    }

    public void delete(int index) {
        Task task = tasks.get(index);
        tasks.remove(index);
        int numOfTasks = tasks.size();
        MessagePrint.print("Noted. I've removed this task:\n"
                + "  " + task + "\n"
                + "Now you have " + numOfTasks + " task" + (numOfTasks > 1 ? "s" : "") + " in the list.");
    }

    public void chat() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            try {
                String[] items = null;
                String description = "";
                int index = 0;
                String input = sc.nextLine();
                String[] inputSplit = input.split(" ", 2);
                String command = inputSplit[0];
                String data = inputSplit.length == 2 ? inputSplit[1] : "";

                switch (Command.valueOf(command.toUpperCase())) {
                case BYE:
                    break;
                case LIST:
                    this.list();
                    continue;
                case MARK:
                    index = Integer.parseInt(data);
                    if (index <= 0 || index > tasks.size()) {
                        throw new LinusException("Cannot mark task. Please provide a valid index.");
                    }

                    tasks.get(index - 1).mark();
                    continue;
                case UNMARK:
                    index = Integer.parseInt(data);
                    if (index <= 0 || index > tasks.size()) {
                        throw new LinusException("Cannot unmark task. Please provide a valid index.");
                    }

                    tasks.get(index - 1).unmark();
                    continue;
                case DELETE:
                    index = Integer.parseInt(data);
                    if (index <= 0 || index > tasks.size()) {
                        throw new LinusException("Cannot delete task. Please provide a valid index.");
                    }

                    this.delete(index - 1);
                    continue;
                case TODO:
                    if (data == "") {
                        throw new LinusException("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                    description = data;
                    this.add(new ToDo(description));
                    continue;
                case DEADLINE:
                    if (data == "") {
                        throw new LinusException("☹ OOPS!!! The description of a deadline cannot be empty.");
                    }

                    items = data.split(" /by ");
                    if (items.length != 2) {
                        throw new LinusException("☹ OOPS!!! Please specify the deadline in the correct format: deadline <description> /by <date>");
                    }

                    description = items[0];
                    String by = items[1];

                    this.add(new Deadline(description, by));
                    continue;
                case EVENT:
                    if (data == "") {
                        throw new LinusException("☹ OOPS!!! The description of a todo cannot be empty.");
                    }

                    items = data.split(" /from | /to ");
                    if (items.length != 3) {
                        throw new LinusException("☹ OOPS!!! Please specify the event in the correct format: event <description> /from <date> /to <date>");
                    }
                    description = items[0];
                    String from = items[1];
                    String to = items[2];

                    this.add(new Event(description, from, to));
                    continue;
                }
                break;
            } catch (IllegalArgumentException e) {
                MessagePrint.print("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            } catch (LinusException e) {
                MessagePrint.print(e.getMessage());
            }
        }
    }

    public void start() {
        this.printWelcomeMessage();
        this.chat();
        this.printExitMessage();
    }
}