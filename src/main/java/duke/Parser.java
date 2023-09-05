package duke;

import duke.exceptions.*;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;
import duke.tasks.Deadline;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

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

    public static LocalDateTime stringToDateTime(String str) {
        //check if dateTime has correct format: ie. YYYY-MM-DD 00:00
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
        return dateTime;
    }

    public void parseInput(String input) {
        String[] inputArr = input.split(" ");
        String command = inputArr[0];
        try {
            if (command.equals("list")) {
                tasks.listTasks();
            } else if (command.equals("delete")) {
                parseDelete(input);
            } else if (command.equals("mark")) {
                parseMark(input);
            } else if (command.equals("unmark")) {
                parseUnmark(input);
            } else if (command.equals("todo")) {
                parseToDo(input);
            } else if (command.equals("deadline")) {
                parseDeadline(input);
            } else if (command.equals("event")) {
                parseEvent(input);
            } else if (command.equals("find")) {
                findTask(input);
            } else {
                throw new InvalidCommandException();
            }
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
    }

    public void parseDelete(String input) throws NoTaskIDException, InvalidTaskIDException {
        String[] inputArr = input.split(" ");
        if (inputArr.length == 1) {
            throw new NoTaskIDException();
        } else {
            String strIndex = inputArr[1];
            if (isNumber(strIndex)) {
                int index = Integer.parseInt(strIndex) - 1; //because index starts from 1
                tasks.deleteTask(index);
            } else {
                //case where a number was not provided
                throw new InvalidTaskIDException();
            }
        }
    }

    public void parseMark(String input) throws NoTaskIDException, InvalidTaskIDException {
        String[] inputArr = input.split(" ", 2);
        if (inputArr.length == 1) {
            throw new NoTaskIDException();
        } else {
            String strIndex = inputArr[1];
            if (isNumber(strIndex)) {
                int index = Integer.parseInt(strIndex) - 1; //because index starts from 1
                if (tasks.isValidTaskID(index)) {
                    tasks.markTask(index);
                }
            } else {
                throw new InvalidTaskIDException();
            }
        }
    }

    public void parseUnmark(String input) throws NoTaskIDException, InvalidTaskIDException {
        String[] inputArr = input.split(" ", 2);
        if (inputArr.length == 1) {
            throw new NoTaskIDException();
        } else {
            String strIndex = inputArr[1];
            if (isNumber(strIndex)) {
                int index = Integer.parseInt(strIndex) - 1; //because index starts from 1
                if (tasks.isValidTaskID(index)) {
                    tasks.unmarkTask(index);
                }
            } else {
                throw new InvalidTaskIDException();
            }
        }
    }

    public void parseToDo(String input) throws NoDescException {
        String[] inputArr = input.split(" ", 2);
        if (inputArr.length == 1) {
            throw new NoDescException();
        } else {
            if (inputArr[1].isBlank()) {
                throw new NoDescException();
            } else {
                //0 for unmarked, any other number for marked
                ToDo toDo = new ToDo(0, inputArr[1]);
                tasks.addTask(toDo);
            }
        }
    }

    public void parseDeadline(String input) throws NoDescException, InvalidDeadlineException {
        String[] inputArr = input.split(" ", 2);
        if (inputArr.length == 1) {
            throw new NoDescException();
        } else {
            String afterCommand = inputArr[1];
            String[] arr = afterCommand.split(" /by ", 2);
            if (arr.length < 2) {
                throw new InvalidDeadlineException();
            } else {
                String desc = arr[0];
                String date = arr[1];
                if (desc.isEmpty()) {
                    throw new NoDescException();
                } else {
                    try {
                        LocalDateTime dateTime = stringToDateTime(date);
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
        String[] inputArr = input.split(" ", 2);
        if (inputArr.length == 1) {
            throw new NoDescException();
        } else if (inputArr[1].isBlank()) {
            throw new NoDescException();
        } else {
            String afterCommand = inputArr[1];
            String[] arr = afterCommand.split(" /from ", 2);
            if (arr[0].isBlank()) {
                throw new NoDescException();
            } else if (arr.length == 1) {
                throw new InvalidEventException();
            } else {
                String task = arr[0];
                String start = arr[1].split(" /to ", 2)[0];
                if (start.isBlank()) {
                    throw new NoStartException();
                } else {
                    String[] arrWithEnd = afterCommand.split(" /to ", 2);
                    if (arrWithEnd.length == 1) { //no end date added
                        throw new NoEndException();
                    } else {
                        String end = arrWithEnd[1];
                        if (end.isBlank()) {
                            throw new NoEndException();
                        } else {
                            try {
                                LocalDateTime startDateTime = stringToDateTime(start);
                                LocalDateTime endDateTime = stringToDateTime(end);
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

    public void findTask(String input) throws InvalidFindTaskException {
        String[] inputs = input.split(" ");
        if (inputs.length != 2) {
            throw new InvalidFindTaskException();
        } else {
            String keyword = inputs[1];
            ArrayList<Task> matches = tasks.findMatches(keyword);
            ui.showMatches(tasks.listTasks(matches));
        }
    }
}