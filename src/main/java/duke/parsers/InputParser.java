package duke.parsers;

import duke.exceptions.DukeException;
import duke.filehandler.Storage;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class InputParser {
    private ArrayList<Task> tasks;

    public InputParser(ArrayList<Task> existingTasks) {
        tasks = existingTasks;
    }

    /**
     * Checks user inputs, if invalid throws DukeException
     *
     * @param str  User input split by each word
     * @param task type of task - todo,event,deadline,mark/unmark
     * @throws DukeException
     */
    public static void inputChecker(String[] str, String task) throws DukeException {

        if (str.length < 2) {
            throw new DukeException("☹ OOPS!!! The description of a " + task + " cannot be empty.");
        }
    }

    /**
     * receives string of date and time, returns Date object
     *
     * @param str
     * @return Date
     * @throws DukeException
     */
    public static Date dateParser(String str) throws DukeException {
        if (str.length() < 15) {
            throw new DukeException("Invalid date, must be of the form dd/mm/yyyy hhmm");
        }
        String newStr = str.substring(0, 13) + ":" + str.substring(13);
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        try {
            Date d1 = df.parse(newStr);
            return d1;
        } catch (Exception e) {
            throw new DukeException("Invalid date");
        }
    }

    /**
     * parses user input
     *
     * @param input
     * @param toStore
     * @return truw if user has not exited, false if user has exited chatbot
     */
    public String parse(String input, boolean toStore) {
        String[] splitStr = input.trim().split("\\s+");
        String reply = "";

        if (input.equals("bye")) {
            reply = "exit app";
        }
        //list out each task from duke.ui.Duke.tasks ArrayList
        else if (input.equals("list")) {
            reply += "  Here are the tasks in your list:\n";
            for (int i = 0; i < Task.getSize(); i++) {
                int index = i + 1;
                reply += "  " + index + "." + tasks.get(i).toString() + "\n";
            }
        }
        //create Todo object
        else if (splitStr[0].equals("todo")) {
            try {
                inputChecker(splitStr, "todo");
                Todo t = new Todo(input.substring(5));
                tasks.add(t);
                reply += t.addedMessage();
                if (toStore) {
                    Storage.saveTasks(tasks);
                }
            } catch (DukeException e) {
                reply += e.getMessage();
            }
        }
        //create deadline object, splitting the due date by "/" and stripping off the by:
        else if (splitStr[0].equals("deadline")) {
            try {
                inputChecker(splitStr, "deadline");
                String[] deadlineArr = input.split("/by ");
                Date deadline = dateParser(deadlineArr[1]);

                Deadline d = new Deadline(deadlineArr[0].substring(9), deadline);
                tasks.add(d);
                reply += d.addedMessage();
                if (toStore) {
                    Storage.saveTasks(tasks);
                }
            } catch (DukeException e) {
                reply += e.getMessage();
            }
        }
        //create event object, splitting the due date by "/" and stripping off the to: and from:
        else if (splitStr[0].equals("event")) {
            try {
                inputChecker(splitStr, "event");
                int startIndex = input.indexOf("/from ");
                int endIndex = input.indexOf("/to");

                Date from = dateParser(input.substring(startIndex + 6, endIndex - 1));
                Date to = dateParser(input.substring(endIndex + 4));

                Event e = new Event(input.substring(6, startIndex), from, to);
                tasks.add(e);
                reply += e.addedMessage();
                if (toStore) {
                    Storage.saveTasks(tasks);
                }
            } catch (DukeException e) {
                reply += e.getMessage();
            }
        }

        //find a certain task
        else if (splitStr[0].equals("find")) {
            try {
                inputChecker(splitStr, "find");
                String toFind = input.substring(5);
                ArrayList<Task> foundTasks = new ArrayList<>();

                for (Task task : tasks) {
                    if (task.getDescription().contains(toFind)) {
                        foundTasks.add(task);
                    }
                }
                if (foundTasks.size() > 0) {
                    reply += "Here are the matching tasks in your list:\n";
                    for (int i = 0; i < foundTasks.size(); i++) {
                        int index = i + 1;
                        reply += "  " + index + "." + foundTasks.get(i).toString() + "\n";
                    }
                } else {
                    reply += "No tasks called " + toFind + " found\n";
                }
            } catch (DukeException e) {
                reply += e.getMessage();
            }
        }

        //mark or unmark an existing task
        else if (splitStr[0].equals("mark") || splitStr[0].equals("unmark")) {
            try {
                inputChecker(splitStr, "mark/unmark");
                int index = Integer.parseInt(splitStr[1]);
                Task item = tasks.get(index - 1);
                reply += item.setAction(splitStr[0]);
                if (toStore) {
                    Storage.saveTasks(tasks);
                }
            } catch (DukeException e) {
                reply += e.getMessage();
            }
        }

        //delete task from duke.ui.Duke.tasks ArrayList
        else if (splitStr[0].equals("delete")) {
            try {
                inputChecker(splitStr, "delete");
                int index = Integer.parseInt(splitStr[1]);
                Task item = tasks.remove(index - 1);
                reply += item.delete();
                if (toStore) {
                    Storage.saveTasks(tasks);
                }
            } catch (DukeException e) {
                reply += e.getMessage();
            }
        }

        //tag a task by its number in the list
        else if (splitStr[0].equals("tag")) {
            try {
                inputChecker(splitStr, "tag");
                Task item = tasks.get(Integer.parseInt(splitStr[1]) - 1);
                String[] getTag = input.split("#");
                inputChecker(getTag, "tag");
                reply += item.setTag(getTag[1]);
            } catch (DukeException e) {
                reply += e.getMessage();
            }
        }

        //unknown command
        else {
            reply += "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n";
        }

        return reply;
    }
}
