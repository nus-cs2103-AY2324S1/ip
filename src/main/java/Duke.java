import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Duke {
    private ArrayList<Task> list;
    private int size = 0;
    private UI ui;

    public Duke() {
        ui = new UI();
        this.list = new ArrayList<>();
    }

    private void run() {
        ui.greet();
        performCommands();
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
        Task task = list.get(index);
        task.markDone();
        ui.displayDoneTask(task);
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
        Task task = list.get(index);
        task.unmarkDone();
        ui.displayNotDoneTask(task);
    }

    private void addTodoToList(String taskName) throws DukeException {
        if (taskName.isBlank()) throw new DukeException("!!!: Please provide a task name");
        Task task = new Todo(taskName);
        list.add(task);
        size++;
        ui.displayAddToList(task, size);
    }

    private void addDeadlineToList(String line) throws DukeException {
        if (!line.contains(" /by ")) throw new DukeException("!!!: Please provide a by date using \"/by by_date\"");
        String taskName = line.substring(0, line.indexOf("/by") - 1);
        if (taskName.isBlank()) throw new DukeException("!!!: Please provide a task name");
        String dueDate = line.substring(line.indexOf("/by") + 4);
        if (dueDate.isBlank()) throw new DukeException("!!!: Please provide a due date");
        Task task = new Deadline(taskName, dueDate);
        list.add(task);
        size++;
        ui.displayAddToList(task, size);
    }

    private void addEventToList(String line) throws DukeException {
        if (!line.contains(" /from ")) throw new DukeException("!!!: Please provide a from date using \"/from from_date\"");
        if (!line.contains(" /to ")) throw new DukeException("!!!: Please provide a to date using \"/to to_date\"");
        String taskName = line.substring(0, line.indexOf("/from") - 1);
        if (taskName.isBlank()) throw new DukeException("!!!: Please provide a task name");
        String dateFrom = line.substring(line.indexOf("/from") + 6, line.indexOf("/to") - 1);
        if (dateFrom.isBlank()) throw new DukeException("!!!: Please provide a from date");
        String dateTo = line.substring(line.indexOf("/to") + 4);
        if (dateTo.isBlank()) throw new DukeException("!!!: Please provide a to date");
        Task task = new Event(taskName, dateFrom, dateTo);
        list.add(task);
        size++;
        ui.displayAddToList(task, size);
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
        Task task = list.get(index);
        list.remove(index);
        size--;
        ui.displayRemoveFromList(task, size);
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
                    ui.displayList(list);
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
                    ui.exit();
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
