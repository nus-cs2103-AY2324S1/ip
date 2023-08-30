package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    private Ui ui;
    private FileStorage fileStorage;
    private TaskList fullList;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");

    public Parser(String filepath) {
        this.ui = new Ui();
        this.fileStorage = new FileStorage(filepath);
        this.fullList = new TaskList();
    }

    public Parser(FileStorage fileStorage, TaskList fullList, Ui ui) {
        this.ui = ui;
        this.fileStorage = fileStorage;
        this.fullList = fullList;
    }
    public enum CommandType {
        TODO, DEADLINE, EVENT, MARK,
        UNMARK, DELETE, LIST, BYE, UNKNOWN, EMPTY
    }

    public CommandType parseCommand(String input) {
        if (input.equals("bye")) {
            return CommandType.BYE;
        } else if (input.equals("mark")) {
            return CommandType.MARK;
        } else if (input.equals("unmark")) {
            return CommandType.UNMARK;
        } else if (input.equals("todo")) {
            return CommandType.TODO;
        } else if (input.equals("deadline")) {
            return CommandType.DEADLINE;
        } else if (input.equals("event")) {
            return CommandType.EVENT;
        } else if (input.equals("delete")) {
            return CommandType.DELETE;
        } else if (input.equals("list")) {
            return CommandType.LIST;
        } else if (input.equals("")) {
            return CommandType.EMPTY;
        } else {
            return CommandType.UNKNOWN;
        }
    }

    public LocalDateTime setDate(String date) {
        try {
            return LocalDateTime.parse(date, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date input. Reverting to current date and time");
            return LocalDateTime.now();
        }
    }

    public boolean createTaskAction(String input) {
        String[] userInputParts = input.split(" ", 2);
        String command_user = userInputParts[0];
        CommandType command = parseCommand(command_user);

        switch (command) {
        case EMPTY:
            ui.showEmptyMessage();
            return true;

        case UNKNOWN:
            ui.showUnknownMessage();
            return true;

        case BYE:
            fileStorage.saveTasks(fullList);
            ui.showByeMessage();
            return false;

        case LIST:
            if (userInputParts.length > 1) {
                ui.showInvalidMessage();
                return true;
            }
            ui.showList(fullList);
            return true;

        case MARK:
            String[] split_index = input.split(" ");
            if (split_index.length <= 1 || split_index.length > 2) {
                ui.showInvalidMessage();
                return true;
            }
            int index = Integer.parseInt(split_index[1]) - 1;
            fullList.markItem(index);
            return true;

        case UNMARK:
            String[] split_index_unmark = input.split(" ");
            if (split_index_unmark.length <= 1 || split_index_unmark.length > 2) {
                ui.showInvalidMessage();
                return true;
            }
            int index_unmark = Integer.parseInt(split_index_unmark[1]) - 1;
            fullList.unMarkItem(index_unmark);
            return true;

        case TODO:
            if (userInputParts.length <= 1) {
                ui.showInvalidMessage();
                return true;
            }
            ToDos toDo = new ToDos(userInputParts[1].trim());
            fullList.addToList(toDo);
            return true;

        case DEADLINE:
            String[] details = userInputParts[1].split("/by");
            String description = details[0].trim();

            if (details.length <= 1) {
                ui.showInvalidMessage();
                return true;
            }

            LocalDateTime by = setDate(details[1].trim());
            Deadline deadline = new Deadline(description, by);
            fullList.addToList(deadline);
            return true;

        case EVENT:
            String[] det = userInputParts[1].split("/from");
            if (det.length <=1) {
                ui.showInvalidMessage();
                return true;
            }
            String[] dateParts = det[1].trim().split("/to");
            if (dateParts.length <=1) {
                ui.showInvalidMessage();
                return true;
            }

            String descr = det[0].trim();
            LocalDateTime from = setDate(dateParts[0].trim());
            LocalDateTime to = setDate(dateParts[1].trim());

            Event event = new Event(descr, from, to);
            if (event.isStartDateBefore(from, to)) {
                fullList.addToList(event);
                return true;
            } else {
                ui.showInvalidDate();
                return true;
            }

        case DELETE:
            String[] split_i = input.split(" ");
            if (split_i.length <= 1 || split_i.length > 2) {
                ui.showInvalidMessage();
                return true;
            }
            int ind = Integer.parseInt(split_i[1]) - 1;
            fullList.deleteFromList(ind);
            return true;

        default:
            ui.showInvalidMessage();
            return true;
        }
    }
}
