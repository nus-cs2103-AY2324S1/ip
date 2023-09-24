package nexus.components;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import nexus.exceptions.InvalidInputException;
import nexus.task.Deadline;
import nexus.task.Event;
import nexus.task.Task;
import nexus.task.TaskList;
import nexus.task.Todo;

/**
 * Handles all the user input.
 */
public class Parser {
    private static String parseMark(TaskList list, String[] data, Storage storage, Ui ui)
            throws InvalidInputException {
        if (data.length == 1) {
            throw new InvalidInputException("Task index must be specified");
        }
        assert data.length == 2 : "Mark task has more than 1 argument.";
        int index = Integer.parseInt(data[1]) - 1;
        try {
            list.get(index).setDone();
            storage.editTask("mark", index);
            return ui.showMark(list.get(index));
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidInputException("Task does not exist");
        }
    }
    private static String parseUnmark(TaskList list, String[] data, Storage storage, Ui ui)
            throws InvalidInputException {
        if (data.length == 1) {
            throw new InvalidInputException("Task index must be specified");
        }
        assert data.length == 2 : "Unmark task has more than 1 argument.";
        int index = Integer.parseInt(data[1]) - 1;
        try {
            list.get(index).setUndone();
            storage.editTask("unmark", index);
            return ui.printUnmark(list.get(index));
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidInputException("Task does not exist");
        }
    }
    private static String parseDelete(TaskList list, String[] data, Storage storage, Ui ui)
            throws InvalidInputException {
        if (data.length == 1) {
            throw new InvalidInputException("Task index must be specified");
        }
        assert data.length == 2 : "Delete task has more than 1 argument.";
        int index = Integer.parseInt(data[1]) - 1;

        try {
            Task deleted = list.get(index);
            list.remove(index);
            storage.editTask("delete", index);
            return ui.showDelete(deleted, list);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidInputException("Task does not exist");
        }

    }
    private static String parseTodo(TaskList list, String[] data, Storage storage, Ui ui)
            throws InvalidInputException {
        StringBuilder builder = new StringBuilder();
        if (data.length == 1) {
            throw new InvalidInputException("Todo description cannot be empty");
        }
        for (int i = 1; i < data.length; i++) {
            builder.append(data[i]).append(" ");
        }
        String desc = builder.toString().trim();
        Todo todo = new Todo(desc);
        list.add(todo);
        storage.saveTask(todo);
        return ui.showAdd(todo, list);
    }
    private static String parseDeadline(TaskList list, String[] data, Storage storage, Ui ui)
            throws InvalidInputException {
        StringBuilder builder = new StringBuilder();
        if (data.length == 1) {
            throw new InvalidInputException("Deadline description cannot be empty");
        }
        int index = 1;
        while (!data[index].equals("/by")) {
            builder.append(data[index]).append(" ");
            index++;
            if (index == data.length) {
                throw new InvalidInputException("Deadline must include /by [D/M/YYYY HHMM]");
            }
        }
        String desc = builder.toString().trim();
        builder.setLength(0);
        index++;
        if (index == data.length) {
            throw new InvalidInputException("Deadline must include /by [D/M/YYYY HHMM]");
        }
        while (index < data.length) {
            builder.append(data[index]).append(" ");
            index++;
        }
        String endDateTimeString = builder.toString().trim();
        LocalDateTime dateTime = Parser.parseDatetime(endDateTimeString);
        Deadline deadline = new Deadline(desc, dateTime);
        list.add(deadline);
        storage.saveTask(deadline);
        return ui.showAdd(deadline, list);
    }

    private static String parseEvent(TaskList list, String[] data, Storage storage, Ui ui)
            throws InvalidInputException {
        StringBuilder builder = new StringBuilder();
        if (data.length == 1) {
            throw new InvalidInputException("Event description cannot be empty");
        }
        int index = 1;
        while (!data[index].equals("/from")) {
            builder.append(data[index]).append(" ");
            index++;
            if (index == data.length) {
                throw new InvalidInputException("Event must include /from [D/M/YYYY HHMM]");
            }
        }
        if (builder.length() == 0) {
            throw new InvalidInputException("Event description cannot be empty");
        }
        String desc = builder.toString().trim();
        builder.setLength(0);
        index++;
        while (!data[index].equals("/to")) {
            builder.append(data[index]).append(" ");
            index++;
            if (index == data.length) {
                throw new InvalidInputException("Event must include /to [D/M/YYYY HHMM]");
            }
        }
        String start = builder.toString().trim();
        builder.setLength(0);
        index++;
        if (index == data.length) {
            throw new InvalidInputException("Event must include /to [D/M/YYYY HHMM]");
        }
        while (index < data.length) {
            builder.append(data[index]).append(" ");
            index++;
        }
        String end = builder.toString().trim();
        try {
            Event event = new Event(desc, Parser.parseDatetime(start), Parser.parseDatetime(end));
            list.add(event);
            storage.saveTask(event);
            return ui.showAdd(event, list);
        } catch (DateTimeParseException e){
            throw new InvalidInputException("Datetime format must be [D/M/YYYY HHMM]");
        }

    }

    /**
     * Handle find command.
     *
     * @param list TaskList.
     * @param data String[].
     * @param ui Ui.
     * @return The tasks found.
     */
    public static String parseFind(TaskList list, String[] data, Ui ui) {
        data[0] = "";
        String keyword = String.join(" ", data).trim();
        System.out.println(keyword);
        return ui.showFind(keyword, list);
    }

    /**
     * Handle list command.
     *
     * @param list TaskList.
     * @param ui Ui.
     * @return All tasks stored on hard drive.
     */
    public static String parseList(TaskList list, Ui ui) {
        return ui.showList(list);
    }

    /**
     * Handles the user command.
     *
     * @param ui Ui.
     * @param storage Storage.
     * @param list TaskList.
     * @param input User input string.
     * @return Response string.
     * @throws InvalidInputException Invalid input.
     */
    public static String parseInput(Ui ui, Storage storage, TaskList list, String input)
            throws InvalidInputException {
        String[] data = input.split(" ");
        assert data.length > 0 : "Input is empty.";

        switch (data[0]) {
        case "list":
            return Parser.parseList(list, ui);
        case "find":
        case "f":
            return Parser.parseFind(list, data, ui);
        case "mark":
        case "m":
            return Parser.parseMark(list, data, storage, ui);
        case "unmark":
        case "um":
            return Parser.parseUnmark(list, data, storage, ui);
        case "todo":
        case "t":
            return Parser.parseTodo(list, data, storage, ui);
        case "deadline":
        case "d":
            return Parser.parseDeadline(list, data, storage, ui);
        case "event":
        case "e":
            return Parser.parseEvent(list, data, storage, ui);
        case "delete":
        case "del":
            return Parser.parseDelete(list, data, storage, ui);
        default:
            throw new InvalidInputException("I don't understand. Please check your input again.");
        }
    }

    public static LocalDateTime parseDatetime(String endDateTimeString) {
        return LocalDateTime.parse(endDateTimeString, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
    }
}
