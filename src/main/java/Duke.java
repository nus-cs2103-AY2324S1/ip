import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Duke {
    private ArrayList<Task> list;
    private int size = 0;

    public Duke() {
        this.list = new ArrayList<>();
    }

    private void start() {
        System.out.println("____________________________________________________________");
        String logo = "  __ _  ___ ___  _   _  _   _  \n" +
                " / _/ \\| o \\_ _|/ \\ | \\| | / \\ \n" +
                "( (( o )   /| || o || \\\\ || o |\n" +
                " \\__\\_/|_|\\\\|_||_n_||_|\\_||_n_|";
        System.out.println("Hello I'm Cortana, Microsoft killed me so now I'm here\n" + logo);
        System.out.println("____________________________________________________________");
    }

    private void exit() {
        System.out.println("Bye");
        System.out.println("____________________________________________________________");
    }

    private CommandType parseText(String line) throws DukeException {
        String command = line.split(" ")[0];
        switch(command) {
        case "list":
            return CommandType.LIST;
        case "mark":
            return CommandType.MARK;
        case "unmark":
            return CommandType.UNMARK;
        case "todo":
            return CommandType.TODO;
        case "deadline":
            return CommandType.DEADLINE;
        case "event":
            return CommandType.EVENT;
        case "delete":
            return CommandType.DELETE;
        case "bye":
            return CommandType.BYE;
        default:
            throw new DukeException("!!!: Sorry I do not understand what you mean");
        }
    }

    private void run() {
        start();
        performCommands();
    }

    private void echo() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (Objects.equals(line, "bye")) break;
            System.out.println(line);
            System.out.println("____________________________________________________________");
        }
    }

    private void list() {
        if (size == 0) System.out.println("No items are in the list");
        for (int i = 0; i < size; i++) {
            String item = String.format("%d. %s", i + 1, list.get(i));
            System.out.println(item);
        }
    }

    private void markDone(String options) throws DukeException {
        try {
            Integer.parseInt(options);
        } catch(NumberFormatException e) {
            throw new DukeException("!!!: Please provide a number for the task to be marked done");
        }
        int index = Integer.parseInt(options) - 1;
        if (index < 0 || index >= size) {
            throw new DukeException(String.format("!!!: Please provide a valid number, " +
                    "there are currently %d items in the list", size));
        }
        list.get(index).markDone();
        System.out.println("This task is marked as done");
        System.out.println(list.get(index));
    }

    private void unmarkDone(String options) throws DukeException {
        try {
            Integer.parseInt(options);
        } catch(NumberFormatException e) {
            throw new DukeException("!!!: Please provide a number for the task to be marked not done");
        }
        int index = Integer.parseInt(options) - 1;
        if (index < 0 || index >= size) {
            throw new DukeException(String.format("!!!: Please provide a valid number, " +
                    "there are currently %d items in the list", size));
        }
        list.get(index).unmarkDone();
        System.out.println("This task is marked as not done");
        System.out.println(list.get(index));
    }

    private void addTodoToList(String task) throws DukeException {
        if (task.isBlank()) throw new DukeException("!!!: Please provide a task name");
        list.add(new Todo(task));
        size++;
        System.out.println("This task is added to the list");
        System.out.println(list.get(size - 1));
        System.out.printf("You now have %d tasks in your list%n", size);
    }

    private void addDeadlineToList(String line) throws DukeException {
        if (!line.contains(" /by ")) throw new DukeException("!!!: Please provide a by date using \"/by by_date\"");
        String task = line.substring(0, line.indexOf("/by") - 1);
        if (task.isBlank()) throw new DukeException("!!!: Please provide a task name");
        String dueDate = line.substring(line.indexOf("/by") + 4);
        if (dueDate.isBlank()) throw new DukeException("!!!: Please provide a due date");
        list.add(new Deadline(task, dueDate));
        size++;
        System.out.println("This task is added to the list");
        System.out.println(list.get(size - 1));
        System.out.printf("You now have %d tasks in your list%n", size);
    }

    private void addEventToList(String line) throws DukeException {
        if (!line.contains(" /from ")) throw new DukeException("!!!: Please provide a from date using \"/from from_date\"");
        if (!line.contains(" /to ")) throw new DukeException("!!!: Please provide a to date using \"/to to_date\"");
        String task = line.substring(0, line.indexOf("/from") - 1);
        if (task.isBlank()) throw new DukeException("!!!: Please provide a task name");
        String dateFrom = line.substring(line.indexOf("/from") + 6, line.indexOf("/to") - 1);
        if (dateFrom.isBlank()) throw new DukeException("!!!: Please provide a from date");
        String dateTo = line.substring(line.indexOf("/to") + 4);
        if (dateTo.isBlank()) throw new DukeException("!!!: Please provide a to date");
        list.add(new Event(task, dateFrom, dateTo));
        size++;
        System.out.println("This task is added to the list");
        System.out.println(list.get(size - 1));
        System.out.printf("You now have %d tasks in your list%n", size);
    }

    private void deleteFromList(String options) throws DukeException {
        try {
            Integer.parseInt(options);
        } catch(NumberFormatException e) {
            throw new DukeException("!!!: Please provide a number for the task to be deleted");
        }
        int index = Integer.parseInt(options) - 1;
        if (index < 0 || index >= size) {
            throw new DukeException(String.format("!!!: Please provide a valid number, " +
                    "there are currently %d items in the list", size));
        }
        Task removedTask = list.get(index);
        list.remove(index);
        size--;
        System.out.println("This task is deleted");
        System.out.println(removedTask);
        System.out.printf("You now have %d tasks in your list%n", size);
    }

    private void performCommands() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            try {
                CommandType command = parseText(line);
                String options = !line.contains(" ") ? "" : line.substring(line.indexOf(' ') + 1);
                switch (command) {
                case LIST:
                    list();
                    break;
                case MARK:
                    markDone(options);
                    break;
                case UNMARK:
                    unmarkDone(options);
                    break;
                case TODO:
                    addTodoToList(options);
                    break;
                case DEADLINE:
                    addDeadlineToList(options);
                    break;
                case EVENT:
                    addEventToList(options);
                    break;
                case DELETE:
                    deleteFromList(options);
                    break;
                case BYE:
                    exit();
                    return;
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
            System.out.println("____________________________________________________________");
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
