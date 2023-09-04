package nexus.components;

import nexus.exceptions.InvalidInputException;
import nexus.task.Deadline;
import nexus.task.Event;
import nexus.task.Task;
import nexus.task.TaskList;
import nexus.task.Todo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {
    private static void parseMark(TaskList list, String[] data, Storage storage, Ui ui) throws InvalidInputException {
        if (data.length == 1) {
            throw new InvalidInputException("Task index must be specified");
        }
        int index = Integer.parseInt(data[1]) - 1;
        list.get(index).setDone();
        ui.printMark(list.get(index));
        storage.editTask("mark", index);
    }
    private static void parseUnmark(TaskList list, String[] data, Storage storage, Ui ui) throws InvalidInputException {
        if (data.length == 1) {
            throw new InvalidInputException("Task index must be specified");
        }
        int index = Integer.parseInt(data[1]) - 1;
        list.get(index).setUndone();

        System.out.println(list.get(index));
        ui.printUnmark(list.get(index));
        storage.editTask("unmark", index);
    }
    private static void parseDelete(TaskList list, String[] data, Storage storage, Ui ui) throws InvalidInputException {
        if (data.length == 1) {
            throw new InvalidInputException("Task index must be specified");
        }
        int index = Integer.parseInt(data[1]) - 1;
        Task deleted = list.get(index);
        list.remove(index);
        ui.printDelete(deleted, list);
        storage.editTask("delete", index);
    }
    private static void parseTodo(TaskList list, String[] data, Storage storage, Ui ui) throws InvalidInputException {
        StringBuilder builder = new StringBuilder();
        if (data.length == 1) {
            throw new InvalidInputException("Todo description cannot be empty");
        }
        for (int i = 1; i < data.length; i++) {
            builder.append(data[i]);
        }
        String desc = builder.toString().trim();
        Todo todo = new Todo(desc);
        list.add(todo);
        ui.printAdd(todo, list);
        storage.saveTask(todo);
    }
    private static void parseDeadline(TaskList list, String[] data, Storage storage, Ui ui) throws InvalidInputException {
        StringBuilder builder = new StringBuilder();
        if (data.length == 1) {
            throw new InvalidInputException("Deadline description cannot be empty");
        }
        int index = 1;
        while (!data[index].equals("/by")) {
            builder.append(data[index]).append(" ");
            index++;
            if (index == data.length) {
                throw new InvalidInputException("Deadline must include /by [DD/MM/YYYY HHMM]");
            }
        }
        String desc = builder.toString().trim();
        builder.setLength(0);
        index++;
        if (index == data.length) {
            throw new InvalidInputException("Deadline must include /by [DD/MM/YYYY HHMM]");
        }
        while (index < data.length) {
            builder.append(data[index]).append(" ");
            index++;
        }
        String by = builder.toString().trim();
        LocalDateTime dateTime = Parser.parseDatetime(by);
        Deadline deadline = new Deadline(desc, dateTime);
        list.add(deadline);
        ui.printAdd(deadline, list);
        storage.saveTask(deadline);
    }

    public static LocalDateTime parseDatetime(String by) {
        return LocalDateTime.parse(by, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
    }

    private static void parseEvent(TaskList list, String[] data, Storage storage, Ui ui) throws InvalidInputException {
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
        ui.printAdd(event, list);
        storage.saveTask(event);
    }

    public static void parseFind(TaskList list, String[] data, Ui ui) throws InvalidInputException {
        data[0] = "";
        String keyword = String.join(" ", data).trim();
        System.out.println(keyword);
        ui.printFind(keyword, list);
    }

    public static boolean parseInput(Ui ui, Storage storage, TaskList list, String input) throws InvalidInputException {
        String[] data = input.split(" ");

        switch (data[0]) {
        case "bye":
            return true;
        case "list":
            ui.printList(list);
            break;
        case "find":
            Parser.parseFind(list, data, ui);
            break;
        case "mark":
            Parser.parseMark(list, data, storage, ui);
            break;
        case "unmark":
            Parser.parseUnmark(list, data, storage, ui);
            break;
        case "todo":
            Parser.parseTodo(list, data, storage, ui);
            break;
        case "deadline":
            Parser.parseDeadline(list, data, storage, ui);
            break;
        case "event":
            Parser.parseEvent(list, data, storage, ui);
            break;
        case "delete":
            Parser.parseDelete(list, data, storage, ui);
            break;
        default:
            throw new InvalidInputException("I don't understand. Please check your input again.");
        }
        return false;
    }
}
