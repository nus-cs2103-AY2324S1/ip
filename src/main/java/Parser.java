/**
 * deals with making sense of the user commands
 */

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class Parser {

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

    public static LocalDateTime stringToDateTime(String str) throws DateTimeParseException {
        //check if dateTime has correct format: ie. YYYY-MM-DD 00:00
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.now();
        dateTime = LocalDateTime.parse(str, formatter);
        return dateTime;
    }

    public void parseInput(String input, Storage storage, TaskList list, Ui ui)
            throws DukeException, IOException {
        String[] inputArr = input.split(" ");
        String command = inputArr[0];
        if (command.equals("list")) {
            list.listTasks();
        } else if (command.equals("delete")) {
            if (inputArr.length == 1) {
                ui.showNoTaskID();
            } else {
                String strIndex = inputArr[1];
                if (isNumber(strIndex)) {
                    int index = Integer.parseInt(strIndex) - 1; //because index starts from 1
                    list.deleteTask(index, storage, ui);
                } else {
                    //case where a number was not provided
                    ui.showInvalidTaskID();
                }
            }
        } else if (command.equals("mark")) {
            if (inputArr.length == 1) {
                ui.showNoTaskID();
            } else {
                String strIndex = inputArr[1];
                if (isNumber(strIndex)) {
                    int index = Integer.parseInt(strIndex) - 1; //because index starts from 1
                    if (list.isValidTaskID(index)) {
                        list.markTask(index, storage);
                    }
                } else {
                    ui.showInvalidTaskID();
                }
            }
        } else if (command.equals("unmark")) {
            if (inputArr.length == 1) {
                ui.showNoTaskID();
            } else {
                String strIndex = inputArr[1];
                if (isNumber(strIndex)) {
                    int index = Integer.parseInt(strIndex) - 1; //because index starts from 1
                    if (list.isValidTaskID(index)) {
                        list.unmarkTask(index, storage);
                    }
                } else {
                    ui.showInvalidTaskID();
                }
            }
        } else if (command.equals("todo")) {
            parseToDo(input, storage, list, ui);
        } else if (command.equals("deadline")) {
            parseDeadline(input, storage, list, ui);
        } else if (command.equals("event")) {
            parseEvent(input, storage, list, ui);
        } else {
            throw new InvalidCommandException();
        }
    }

    public void parseToDo(String input, Storage storage, TaskList list, Ui ui) throws DukeException, IOException {
        String[] inputArr = input.split(" ", 2);
        if (inputArr.length == 1) {
            throw new NoDescException();
        } else {
            if (inputArr[1].isBlank()) {
                throw new NoDescException();
            } else {
                //0 for unmarked, any other number for marked
                ToDo toDo = new ToDo(0, inputArr[1]);
                list.addTask(toDo, storage, ui);
            }
        }
    }

    public void parseDeadline(String input, Storage storage, TaskList list, Ui ui) throws DukeException,
            IOException {
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
                        list.addTask(deadline, storage, ui);
                    } catch (DateTimeParseException e) {
                        ui.showInvalidDateFormat();
                    }
                }
            }
        }
    }

    public void parseEvent(String input, Storage storage, TaskList list, Ui ui) throws DukeException,
            IOException {
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
                ui.showInvalidEventFormat();
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
                                if (startDateTime.isAfter(endDateTime)) {
                                    ui.showInvalidStartEnd();
                                } else {
                                    Event event = new Event(0, task, startDateTime, endDateTime);
                                    list.addTask(event, storage, ui);
                                }
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