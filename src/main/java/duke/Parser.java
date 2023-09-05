package duke;

import duke.exceptions.DukeException;
import duke.exceptions.InvalidCommandException;
import duke.exceptions.InvalidDeadlineException;
import duke.exceptions.InvalidEventException;
import duke.exceptions.InvalidStartEndException;
import duke.exceptions.InvalidTaskIdException;
import duke.exceptions.NoDescException;
import duke.exceptions.NoEndException;
import duke.exceptions.NoStartException;
import duke.exceptions.NoTaskIdException;

import duke.tasks.Event;
import duke.tasks.ToDo;
import duke.tasks.Deadline;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * deals with making sense of the user commands
 */
public class Parser {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Parser(Storage storage, TaskList tasks, Ui ui) {
        this.storage = storage;
        this.tasks = tasks;
        this.ui = ui;
    }

    //credit: https://www.freecodecamp.org/news/java-string-to-int-how-to-convert-a-string-to-an-integer/
    private static boolean isNumber(String s) {
        return s != null && s.matches("[0-9.]+");
    }

    public boolean isGoodbye(String input) {
        if (input.equals("bye")) {
            return true;
        }
        return false;
    }

    public static LocalDateTime convertToDateTime(String str) {
        //check if dateTime has correct format: ie. YYYY-MM-DD 00:00
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
        return dateTime;
    }

    public void parseInput(String input) {
        String[] inputs = input.split(" ");
        String command = inputs[0];
        try {
            if (command.equals("list")) {
                tasks.listTasks();
            } else if (command.equals("delete")) {
                parseDelete(input);
            } else if (command.equals("mark")) {
                parseMark(input);
            } else if (command.equals("unmark")) {
                parseUnMark(input);
            } else if (command.equals("todo")) {
                parseToDo(input);
            } else if (command.equals("deadline")) {
                parseDeadline(input);
            } else if (command.equals("event")) {
                parseEvent(input);
            } else {
                throw new InvalidCommandException();
            }
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
    }

    public void parseDelete(String input) throws NoTaskIdException, InvalidTaskIdException {
        String[] inputs = input.split(" ");
        if (inputs.length == 1) {
            throw new NoTaskIdException();
        } else {
            String strIndex = inputs[1];
            if (isNumber(strIndex)) {
                int index = Integer.parseInt(strIndex) - 1; //because index starts from 1
                tasks.deleteTask(index);
            } else {
                //case where a number was not provided
                throw new InvalidTaskIdException();
            }
        }
    }

    public void parseMark(String input) throws NoTaskIdException, InvalidTaskIdException {
        String[] inputs = input.split(" ", 2);
        if (inputs.length == 1) {
            throw new NoTaskIdException();
        } else {
            String strIndex = inputs[1];
            if (isNumber(strIndex)) {
                int index = Integer.parseInt(strIndex) - 1;
                if (tasks.isValidTaskId(index)) {
                    tasks.markTask(index);
                }
            } else {
                throw new InvalidTaskIdException();
            }
        }
    }

    public void parseUnMark(String input) throws NoTaskIdException, InvalidTaskIdException {
        String[] inputs = input.split(" ", 2);
        if (inputs.length == 1) {
            throw new NoTaskIdException();
        } else {
            String strIndex = inputs[1];
            if (isNumber(strIndex)) {
                int index = Integer.parseInt(strIndex) - 1; //because index starts from 1
                if (tasks.isValidTaskId(index)) {
                    tasks.unMarkTask(index);
                }
            } else {
                throw new InvalidTaskIdException();
            }
        }
    }

    public void parseToDo(String input) throws NoDescException {
        String[] inputs = input.split(" ", 2);
        if (inputs.length == 1) {
            throw new NoDescException();
        } else {
            if (inputs[1].isBlank()) {
                throw new NoDescException();
            } else {
                //0 for unmarked, any other number for marked
                ToDo toDo = new ToDo(0, inputs[1]);
                tasks.addTask(toDo);
            }
        }
    }

    public void parseDeadline(String input) throws NoDescException, InvalidDeadlineException {
        String[] inputs = input.split(" ", 2);
        if (inputs.length == 1) {
            throw new NoDescException();
        } else {
            String afterCommand = inputs[1];
            String[] details = afterCommand.split(" /by ", 2);

            if (details.length < 2) {
                throw new InvalidDeadlineException();
            } else {
                String desc = details[0];
                String date = details[1];

                if (desc.isEmpty()) {
                    throw new NoDescException();
                } else {
                    try {
                        LocalDateTime dateTime = convertToDateTime(date);
                        Deadline deadline = new Deadline(0, desc, dateTime);
                        tasks.addTask(deadline);
                    } catch (DateTimeParseException e) {
                        ui.showInvalidDateFormat();
                    }
                }
            }
        }
    }

    public void parseEvent(String input) throws NoDescException, NoStartException, NoEndException,
            InvalidStartEndException, InvalidEventException {
        String[] inputs = input.split(" ", 2);
        if (inputs.length == 1) {
            throw new NoDescException();
        } else if (inputs[1].isBlank()) {
            throw new NoDescException();
        } else {
            String afterCommand = inputs[1];
            String[] details = afterCommand.split(" /from ", 2);

            if (details[0].isBlank()) {
                throw new NoDescException();
            } else if (details.length == 1) {
                throw new InvalidEventException();
            } else {
                String task = details[0];
                String start = details[1].split(" /to ", 2)[0];

                if (start.isBlank()) {
                    throw new NoStartException();
                } else {
                    String[] endDetails = afterCommand.split(" /to ", 2);

                    if (endDetails.length == 1) { //no end date added
                        throw new NoEndException();
                    } else {
                        String end = endDetails[1];

                        if (end.isBlank()) {
                            throw new NoEndException();
                        } else {
                            try {
                                LocalDateTime startDateTime = convertToDateTime(start);
                                LocalDateTime endDateTime = convertToDateTime(end);
                                Event event = new Event(0, task, startDateTime, endDateTime);
                                tasks.addTask(event);
                            } catch (DateTimeParseException e) {
                                ui.showInvalidDateFormat();
                            }
                        }
                    }
                }
            }
        }
    }
}