import exceptions.InvalidInputException;
import task.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {
    private static void parseMark(TaskList list, String[] data, Storage storage) throws InvalidInputException {
        if (data.length == 1) {
            throw new InvalidInputException("Task index must be specified");
        }
        int index = Integer.parseInt(data[1]) - 1;
        list.get(index).setDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(list.get(index));
        storage.editTask("mark", index);
    }
    private static void parseUnmark(TaskList list, String[] data, Storage storage) throws InvalidInputException {
        if (data.length == 1) {
            throw new InvalidInputException("Task index must be specified");
        }
        int index = Integer.parseInt(data[1]) - 1;
        list.get(index).setUndone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(list.get(index));
        storage.editTask("unmark", index);
    }
    private static void parseDelete(TaskList list, String[] data, Storage storage) throws InvalidInputException {
        if (data.length == 1) {
            throw new InvalidInputException("Task index must be specified");
        }
        int index = Integer.parseInt(data[1]) - 1;
        Task deleted = list.get(index);
        list.remove(index);
        System.out.println("Noted. I've removed this task:");
        System.out.println(deleted);
        System.out.println("Now you have " + list.size() + " tasks in the list.");
        storage.editTask("delete", index);
    }
    private static void parseTodo(TaskList list, String[] data, Storage storage) throws InvalidInputException {
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
        System.out.println("Got it. I've added this task:");
        System.out.println(todo);
        System.out.println("Now you have " + list.size() + " tasks in the list.");
        storage.saveTask(todo);
    }
    private static void parseDeadline(TaskList list, String[] data, Storage storage) throws InvalidInputException {
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
        System.out.println("Got it. I've added this task:");
        System.out.println(deadline);
        System.out.println("Now you have " + list.size() + " tasks in the list.");
        storage.saveTask(deadline);
    }

    public static LocalDateTime parseDatetime(String by) {
        return LocalDateTime.parse(by, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
    }

    private static void parseEvent(TaskList list, String[] data, Storage storage) throws InvalidInputException {
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
        System.out.println("Got it. I've added this task:");
        System.out.println(event);
        System.out.println("Now you have " + list.size() + " tasks in the list.");
        storage.saveTask(event);
    }

    public static boolean parseInput(Storage storage, TaskList list, String input) throws InvalidInputException {
        int index;
        String desc;
        String[] data = input.split(" ");
        StringBuilder builder = new StringBuilder();

        switch (data[0]) {
        case "bye":
            return true;
        case "list":
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < list.size(); i++) {
                System.out.print(i + 1);
                System.out.println("." + list.get(i));
            }
            break;
        case "mark":
            Parser.parseMark(list, data, storage);
            break;
        case "unmark":
            Parser.parseUnmark(list, data, storage);
            break;
        case "todo":
            Parser.parseTodo(list, data, storage);
            break;
        case "deadline":
            Parser.parseDeadline(list, data, storage);
            break;
        case "event":
            Parser.parseEvent(list, data, storage);
            break;
        case "delete":
            Parser.parseDelete(list, data, storage);
            break;
        default:
            throw new InvalidInputException("I don't understand. Please check your input again.");
        }
        return false;
    }
}
