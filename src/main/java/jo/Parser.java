package jo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import jo.command.AddCommand;
import jo.command.CheckCommand;
import jo.command.Command;
import jo.command.DeleteCommand;
import jo.command.ExitCommand;
import jo.command.ListCommand;
import jo.command.MarkCommand;
import jo.task.Deadline;
import jo.task.Event;
import jo.task.Task;

public class Parser {

    protected enum STRING_COMMAND {
        todo {
            public Command perform(String input) {
                return new AddCommand(new Task(input, false));
            }
        },
        deadline {
            public Command perform(String input) throws JoException {
                if (!input.contains("/by")) {
                    throw new JoException("Please specify a deadline.");
                } else {
                    String[] description = input.split("/by", 2);
                    String taskName = description[0].trim();
                    try {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        LocalDate deadline = LocalDate.parse(description[1].trim(), formatter);
                        return new AddCommand(new Deadline(taskName, false, deadline));
                    } catch (DateTimeParseException e) {
                        throw new JoException("Invalid date format. Please use yyyy-MM-dd.");
                    }
                }
            }
        },
        event {
            public Command perform(String input) throws JoException {
                if (!input.contains("/from") || !input.contains("/to")) {
                    throw new JoException("Please specify a start AND end date.");
                } else {
                    String[] description = input.split("/from", 2);
                    String[] dates = description[1].split("/to", 2);
                    String taskName = description[0].trim();
                    try {
                        LocalDate start = LocalDate.parse(dates[0].trim());
                        LocalDate end = LocalDate.parse(dates[1].trim());
                        return new AddCommand(new Event(taskName, false, start, end));
                    } catch (DateTimeParseException e) {
                        throw new JoException("Invalid date format. Please use yyyy-MM-dd with a valid date.");
                    }
                }
            }
        },

        check {
            public Command perform(String input) throws JoException {
                try {
                    LocalDate deadline = LocalDate.parse(input);
                    return new CheckCommand(deadline);
                } catch (DateTimeParseException e) {
                    throw new JoException("Invalid date format. Please use yyyy-MM-dd with a valid date.");
                }
            }
        },
        find {
            public Command perform(String input) throws JoException {
                return new SearchCommand(input);
            }
        };

        public abstract Command perform(String s) throws JoException;
    }

    protected enum INT_COMMAND {
        mark {
            @Override
            public Command perform(int taskIndex) {
                return new MarkCommand(taskIndex, true);
            }
        },
        unmark {
            @Override
            public Command perform(int taskIndex) {
                return new MarkCommand(taskIndex, false);
            }
        },
        delete {
            @Override
            public Command perform(int taskIndex) {
                return new DeleteCommand(taskIndex);
            }
        };

        public abstract Command perform(int taskIndex);
    }

    public static <E extends Enum<E>> boolean isInEnum(String input, Class<E> enumClass) {
        for (E enumValue : enumClass.getEnumConstants()) {
            if (enumValue.name().equals(input)) {
                return true;
            }
        }
        return false;
    }

    public static Command parse(String input) throws JoException {
        if (input.equalsIgnoreCase("bye")) {
            return new ExitCommand();
        } else if (input.trim().isEmpty()) {
            throw new JoException("The command cannot be empty.");
        } else if (input.equals("list")) {
            return new ListCommand();
        } else if (isInEnum(input.trim(), STRINGCOMMAND.class)) {
            throw new JoException(String.format("The description of a %s cannot be empty.", input));
        } else if (isInEnum(input, INTCOMMAND.class)) {
            throw new JoException(String.format("Please specify a valid task number to %s.", input));
        } else {
            String instruction = input.split(" ", 2)[0].trim();
            if (isInEnum(instruction, STRINGCOMMAND.class)) {
                for (STRINGCOMMAND t : STRINGCOMMAND.values()) {
                    if (t.name().equals(instruction)) {
                        return t.perform(input.split(" ", 2)[1].trim());
                    }
                }
            } else if (isInEnum(instruction, INTCOMMAND.class)) {
                for (INTCOMMAND c : INTCOMMAND.values()) {
                    if (c.name().equals(instruction)) {
                        int taskIndex = Character.getNumericValue(input.charAt(input.length() - 1)) - 1;
                        return c.perform(taskIndex);
                    }
                }
            } else {
                throw new JoException("I'm sorry, but I don't know what that means :-(");
            }
        }
        return new ExitCommand();
    }
}
