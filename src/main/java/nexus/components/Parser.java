package nexus.components;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
        int index = Integer.parseInt(data[1]) - 1;
        list.get(index).setDone();
        storage.editTask("mark", index);
        return ui.showMark(list.get(index));
    }
    private static String parseUnmark(TaskList list, String[] data, Storage storage, Ui ui)
            throws InvalidInputException {
        if (data.length == 1) {
            throw new InvalidInputException("Task index must be specified");
        }
        int index = Integer.parseInt(data[1]) - 1;
        list.get(index).setUndone();

        System.out.println(list.get(index));
        storage.editTask("unmark", index);
        return ui.printUnmark(list.get(index));
    }
    private static String parseDelete(TaskList list, String[] data, Storage storage, Ui ui)
            throws InvalidInputException {
        if (data.length == 1) {
            throw new InvalidInputException("Task index must be specified");
        }
        int index = Integer.parseInt(data[1]) - 1;
        Task deleted = list.get(index);
        list.remove(index);
        storage.editTask("delete", index);
        return ui.showDelete(deleted, list);
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
                throw new InvalidInputException("Event must include /from [datetime]");
            }
        }
        String desc = builder.toString().trim();
        builder.setLength(0);
        index++;
        while (!data[index].equals("/to")) {
            builder.append(data[index]).append(" ");
            index++;
            if (index == data.length) {
                throw new InvalidInputException("Event must include /to [datetime]");
            }
        }
        String start = builder.toString().trim();
        builder.setLength(0);
        index++;
        if (index == data.length) {
            throw new InvalidInputException("Event must include /to [datetime]");
        }
        while (index < data.length) {
            builder.append(data[index]).append(" ");
            index++;
        }
        String end = builder.toString().trim();
        Event event = new Event(desc, Parser.parseDatetime(start), Parser.parseDatetime(end));
        list.add(event);
        storage.saveTask(event);
        return ui.showAdd(event, list);
    }

    /**
     * Handle find command.
     *
     * @param list TaskList.
     * @param data String[].
     * @param ui Ui.
     */
    public static String parseFind(TaskList list, String[] data, Ui ui) {
        data[0] = "";
        String keyword = String.join(" ", data).trim();
        System.out.println(keyword);
        return ui.showFind(keyword, list);
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

        switch (data[0]) {
        case "bye":
            return "bye";
        case "list":
            return ui.showList(list);
        case "find":
            return Parser.parseFind(list, data, ui);
        case "mark":
            return Parser.parseMark(list, data, storage, ui);
        case "unmark":
            return Parser.parseUnmark(list, data, storage, ui);
        case "todo":
            return Parser.parseTodo(list, data, storage, ui);
        case "deadline":
            return Parser.parseDeadline(list, data, storage, ui);
        case "event":
            return Parser.parseEvent(list, data, storage, ui);
        case "delete":
            return Parser.parseDelete(list, data, storage, ui);
        default:
            throw new InvalidInputException("I don't understand. Please check your input again.");
        }
    }

    public static LocalDateTime parseDatetime(String endDateTimeString) {
        return LocalDateTime.parse(endDateTimeString, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
    }
}
