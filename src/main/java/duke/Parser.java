package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Converts user command into a Task
 */
public class Parser {

    /**
     * Converts user command into Task and adds it into the list
     *
     * @param input User command
     * @param list Task is added into list
     */
    public void parse(String input, TaskList list) {
        try {
            if (input.equals("list")) {
                list.printList();
            } else if (input.startsWith("mark ")) {
                int num = input.charAt(5) - '0' - 1;
                if (num >= 0 && num < list.count) {
                    list.getTask(num).markAsDone();
                    System.out.println("Nice! I've marked this task done:");
                    System.out.println(list.getTask(num));
                } else {
                    System.out.println("Invalid");
                }
            } else if (input.startsWith("unmark ")) {
                int num = input.charAt(7) - '0' - 1;
                if (num >= 0 && num < list.count) {
                    list.getTask(num).markAsUndone();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(list.getTask(num));
                } else {
                    System.out.println("Invalid");
                }
            } else if (input.startsWith("todo ")) {
                String des = input.substring(5);
                if (des.isBlank()) {
                    throw new EmptyDescriptionException();
                }
                list.addTask(new Todo(des));
            } else if (input.startsWith("deadline ")) {
                if (input.substring(9).isBlank()) {
                    throw new EmptyDescriptionException();
                }
                String[] split = input.substring(9).split(" /by ");
                String des = split[0];
                LocalDate date = LocalDate.parse(split[1]);
                String by = date.format(DateTimeFormatter.ofPattern("MMM d yyy"));
                list.addTask(new Deadline(des, by));

            } else if (input.startsWith("event ")) {
                if (input.substring(6).isBlank()) {
                    throw new EmptyDescriptionException();
                }
                String[] split = input.substring(6).split(" /from ");
                String des = split[0];
                String[] fromto = split[1].split(" /to ");
                String from = fromto[0];
                String to = fromto[1];
                list.addTask(new Event(des, from, to));
            } else if (input.startsWith("delete ")) {
                int index = Integer.parseInt(input.substring(7)) - 1;
                if (index >= 0 && index < list.count) {
                    list.deleteTask(index);
                } else {
                    System.out.println("Invalid.");
                }
            } else if (input.equals("bye")) {
                System.out.println("BYE");
            } else if (input.startsWith("find ")) {
                String description = input.substring(5);
                list.findTask(description, list);
            } else {
                throw new UnknownCommandException();
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        } catch (DateTimeParseException e) {
            System.out.println("OOps invalid time input");
        }
    }
}
