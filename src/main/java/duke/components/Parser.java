package duke.components;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.ExitCommand;
import duke.command.FilterCommand;
import duke.command.ModifyCommand;

import java.io.IOException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    public static Command parse(String input) throws DukeException, IOException {
        String[] commandInput = input.split(" ");
        String command = commandInput[0];
        try {
            if (command.equals("bye")) {
                return new ExitCommand();
            } else if (command.equals("list")) {
                return new ModifyCommand("L", 0);
            } else if (command.equals("mark")) {
                String type = "M";
                int index = Integer.valueOf(commandInput[1]);
                return new ModifyCommand(type, index);
            } else if (command.equals("unmark")) {
                String type = "U";
                int index = Integer.valueOf(commandInput[1]);
                return new ModifyCommand(type, index);
            } else if (command.equals("todo")) {
                String type = "T";
                String[] task = getTask(type, input);
                return new AddCommand(type, task[0]);
            } else if (command.equals("deadline")) {
                String type = "D";
                String[] task = getTask(type, input);
                return new AddCommand(type, task[0], parseDateTime(task[1]));
            } else if (command.equals("event")) {
                String type = "E";
                String[] task = getTask(type, input);
                return new AddCommand(type, task[0], parseDateTime(task[1]), parseDateTime(task[2]));
            } else if (command.equals("delete")) {
                String type = "D";
                int index = Integer.valueOf(commandInput[1]);
                return new ModifyCommand(type, index);
            } else if (command.equals("find")) {
                String type = "F";
                String keyword = commandInput[1];
                return new FilterCommand(type, keyword);
            } else {
                throw new DukeException("I'm afraid I do not quite understand. Could you kindly repeat it?");
            }
        } catch (Exception ex) {
            throw new DukeException("Could you repeat that last part? I did not quite catch it.");
        }
    }

    public static String[] getTask(String type, String input) {
        if (type.equals("T")) {
            return new String[] {input.substring(5)};
        } else if (type.equals("D")) {
            String deadline = input.substring(9);
            return deadline.split(" /by ");
        } else if (type.equals("E")) {
            String details = input.substring(6);
            String[] taskStartEnd = details.split(" /from ");
            String[] startEnd = taskStartEnd[1].split(" /to ");
            return new String[] {taskStartEnd[0], startEnd[0], startEnd[1]};
        } else {
            return new String[] {};
        }
    }
    public static LocalDateTime parseDateTime(String dateTimeStr) throws DukeException{
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            return LocalDateTime.parse(dateTimeStr, formatter);
        } catch (DateTimeParseException ex) {
            throw new DukeException("I'm afraid I do not quite understand. Please input the date and time" +
                    "as follows:\nd/M/yyyy HHmm");
        }
    }
}
