package duke;

import duke.exception.InvalidFindingException;
import duke.exception.LackDescriptionException;
import duke.exception.LackInformationException;
import duke.exception.InvalidInputException;
import duke.exception.InvalidMarkingException;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * An object that deals with making sense of user commands.
 */
public class Parser {
    /** The TaskList that the parser will be adding tasks into. */
    private TaskList tasks;
    /** The Ui that helps the parser to print messages. */
    private Ui ui;
    /** A boolean to indicate if the user wants to end the conversation. */
    private boolean isExit;

    /**
     * Constructs a new Parser that stores tasks into the given TaskArray and prints through the given Ui.
     *
     * @param tasks The TaskList to store tasks.
     * @param ui The Ui to interact with users.
     */
    public Parser(TaskList tasks, Ui ui) {
        this.tasks = tasks;
        this.ui = ui;
        this.isExit = false;
    }

    /**
     * Constructs a new Parser that stores tasks into the given TaskArray.
     *
     * @param tasks The TaskList to store tasks.
     */
    public Parser(TaskList tasks) {
        this.tasks = tasks;
        this.ui = null;
        this.isExit = false;
    }

    /**
     * Reads the command given and executes them.
     * Throws exceptions when fail to comprehend the command.
     *
     * @param command The command line.
     * @return Returns a string as a response from Duke.
     * @throws DateTimeParseException If the input format of the dates is invalid.
     */
    public String parse(String command) throws DateTimeParseException {
        String[] stringList = command.split(" ", 2);
        String firstWord = stringList[0];
        String otherWords = null;
        try {
            otherWords = stringList[1];
        } catch (IndexOutOfBoundsException e) {
            // do nothing
        }
        switch (firstWord) {
        case "bye":
            break;
        case "list":
            return printList();
        case "mark":
            return markDone(otherWords);
        case "unmark":
            return markUndone(otherWords);
        case "todo":
            return addTodo(otherWords);
        case "deadline":
            return addDeadline(otherWords);
        case "event":
            return addEvent(otherWords);
        case "delete":
            return delete(otherWords);
        case "find":
            return findTask(otherWords);
        default:
            throw new InvalidInputException("OOPS! I do not know what " + firstWord
                    + " means. Please try again :)");
        }
        return null;
    }

    /**
     * Reads the inputs passed from a file and adds the tasks into the TaskArray.
     *
     * @param line The input line.
     */
    public void parseFromFile(String line) {
        assert tasks != null : "No existing list";
        String[] strings = line.split(" / ");
        assert strings.length >= 3 : "Wrong format in file";
        String typeOfTask = strings[0];

        assert typeOfTask.equals("[T]") || typeOfTask.equals("[D]") || typeOfTask.equals("[E]") : "Wrong format for task in file";
        assert strings[1].equals("1") || strings[1].equals("0") : "Wrong boolean format in file";

        boolean isDone = strings[1].equals("1");
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("HH:mm");
        Task t;
        switch (typeOfTask) {
        case "[T]":
            t = new Todo(strings[2]);
            if (isDone) {
                t.markDone();
            }
            tasks.add(t);
            break;
        case "[D]":
            if (strings.length == 4) {
                t = new Deadline(strings[2], LocalDate.parse(strings[3]));
            } else {
                t = new Deadline(strings[2], LocalDate.parse(strings[3]),
                        LocalTime.parse(strings[4], dateFormat));
            }
            if (isDone) {
                t.markDone();
            }
            tasks.add(t);
            break;
        case "[E]":
            if (strings.length == 7) {
                t = new Event(strings[2], LocalDate.parse(strings[3]), LocalTime.parse(strings[4], dateFormat),
                        LocalDate.parse(strings[5]), LocalTime.parse(strings[6]));
            } else if (strings.length == 5) {
                t = new Event(strings[2], LocalDate.parse(strings[3]), LocalDate.parse(strings[4]));
            } else {
                if (strings[5].length() > 5) {
                    t = new Event(strings[2], LocalDate.parse(strings[3]), LocalTime.parse(strings[4], dateFormat), //chars
                            LocalDate.parse(strings[5]));
                } else {
                    t = new Event(strings[2], LocalDate.parse(strings[3]), LocalDate.parse(strings[4]),
                            LocalTime.parse(strings[5], dateFormat));
                }
            }
            if (isDone) {
                t.markDone();
            }
            tasks.add(t);
            break;
        }
    }

    /**
     * Returns a string containing the tasks in the TaskList.
     */
    private String printList() {
        assert tasks != null : "No existing list";
        assert ui != null : "No existing ui";
        if (tasks.isEmpty()) {
            return ui.sendMessage("list is empty :(");
        } else {
            String res = "";
            for (int i = 0; i < tasks.size(); i++) {
                res = String.join("",res + "\n", ui.sendMessage(i + 1 + " " + tasks.get(i).convertToString()));
            }
            return res;
        }
    }

    /**
     * Adds a todo task into the list.
     *
     * @param details Details of the task.
     * @return Returns the message from Duke.
     */
    private String addTodo(String details) {
        assert tasks != null : "No existing list";
        if (details == null || details.trim().isEmpty()) {
            throw new LackDescriptionException("todo");
        }
        Todo t = new Todo(details);
        tasks.add(t);
        return confirmAddedTask(details);
    }

    /**
     * Adds a deadline task into the list.
     *
     * @param details Details of the task.
     * @return Returns the message from Duke.
     */
    private String addDeadline(String details) {
        assert tasks != null : "No existing list";
        if (details == null || details.trim().isEmpty() || details.trim().startsWith("/by")) {
            throw new LackDescriptionException("deadline");
        }

        String[] s = details.split(" /by ");
        String description = s[0];
        String deadline;
        try {
            deadline = s[1];
        } catch (IndexOutOfBoundsException e) {
            throw new LackInformationException("\"/by\"");
        }

        String[] dateTime = deadline.split(" ");
        LocalDate date = LocalDate.parse(dateTime[0]);
        LocalTime time;
        Deadline d;
        if (dateTime.length > 1) {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm");
            time = LocalTime.parse(dateTime[1], format);
            d = new Deadline(description, date, time);
        } else {
            d = new Deadline(description, date);
        }

        tasks.add(d);
        return confirmAddedTask(description);
    }

    /**
     * Adds an event task into the list.
     *
     * @param details Details of the task.
     * @return Returns the message from Duke.
     */
    private String addEvent(String details) {
        assert tasks != null : "No existing list";
        if (details == null || details.trim().isEmpty() || details.trim().startsWith("/from") || details.trim().startsWith("/to")) {
            throw new LackDescriptionException("event");
        }

        String[] s = details.split(" /from ");
        String description = s[0];
        String fromto = null;
        try {
            fromto = s[1];
        } catch (IndexOutOfBoundsException e) {
            throw new LackInformationException("\"/from\"");
        }
        if (fromto.trim().startsWith("/to")) {
            throw new LackInformationException("\"/from\"");
        }
        String[] ft = fromto.split(" /to ");
        String from = ft[0];

        LocalDate startDate;
        LocalTime startTime = null;
        String[] starts = from.split(" ");
        startDate = LocalDate.parse(starts[0]);
        if (starts.length > 1) {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm");
            startTime = LocalTime.parse(starts[1], format);
        }

        String to;
        try {
            to = ft[1];
        } catch (IndexOutOfBoundsException e) {
            throw new LackInformationException("\"/to\"");
        }

        LocalDate endDate;
        LocalTime endTime = null;
        String[] ends = to.split(" ");
        endDate = LocalDate.parse(ends[0]);
        if (ends.length > 1) {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm");
            endTime = LocalTime.parse(ends[1], format);
        }

        Event e;

        if (startTime == null && endTime == null) {
            e = new Event(description, startDate, endDate);
        } else if (startTime == null) {
            e = new Event(description, startDate, endDate, endTime);
        } else if (endTime == null) {
            e = new Event(description, startDate, startTime, endDate);
        } else {
            e = new Event(description, startDate, startTime, endDate, endTime);
        }

        tasks.add(e);
        return confirmAddedTask(description);
    }

    private String confirmAddedTask(String x) {
        assert tasks != null : "No existing list";
        assert ui != null : "No existing ui";
        return String.join("", ui.sendMessage("Added to list: " + x) + "\n", ui.sendMessage("Now you have " + tasks.size()));
    }

    /**
     * Marks the task as done.
     *
     * @param details Index of the target task.
     * @return Returns the message from Duke.
     */
    private String markDone(String details) {
        assert tasks != null : "No existing list";
        assert ui != null : "No existing ui";

        if (details == null || details.trim().isEmpty()) {
            throw new InvalidMarkingException("Missing index");
        }

        int j;
        try {
            j = Integer.parseInt(details);
        } catch (NumberFormatException e) {
            throw new InvalidMarkingException("Please provide a valid index");
        }

        if (j-1 > tasks.size()-1 || j-1<0) {
            throw new InvalidMarkingException("There is no corresponding task in the list");
        }

        Task t = tasks.get(j-1);
        t.markDone();

        return ui.sendMessage("Task marked as done.");
    }

    /**
     * Marks the task as undone.
     *
     * @param details Index of the target task.
     * @return Returns the message from Duke.
     */
    private String markUndone(String details) {
        assert tasks != null : "No existing list";
        assert ui != null : "No existing ui";

        if (details == null || details.trim().isEmpty()) {
            throw new InvalidMarkingException("Missing index");
        }

        int j;
        try {
            j = Integer.parseInt(details);
        } catch (NumberFormatException e) {
            throw new InvalidMarkingException("Please provide a valid index");
        }

        if (j-1 > tasks.size()-1 || j-1<0) {
            throw new InvalidMarkingException("There is no corresponding task in the list");
        }

        Task t = tasks.get(j-1);
        t.markUndone();

        return ui.sendMessage("Task marked as undone.");
    }

    /**
     * Deletes a task from the list.
     *
     * @param details Index of the target task.
     * @return Returns the message from Duke.
     */
    private String delete(String details) {
        assert tasks != null : "No existing list";

        if (details == null || details.trim().isEmpty()) {
            throw new InvalidMarkingException("Missing index");
        }

        int j;
        try {
            j = Integer.parseInt(details);
        } catch (NumberFormatException e) {
            throw new InvalidMarkingException("Please provide a valid index");
        }

        if (j - 1 > tasks.size() - 1 || j - 1 < 0) {
            throw new InvalidMarkingException("There is no corresponding task in the list");
        }

        Task t = tasks.get(j - 1);
        tasks.remove(j - 1);

        String res = String.join("", "I've removed this task:\n", t.convertToString());
        res = String.join("", res + "\n", "Now you have " + tasks.size() + (tasks.size() > 1 ? " tasks" : " task")
                + " in the list");

        return res;
    }

    /**
     * Finds the task in the list.
     *
     * @param x Keyword to be searched.
     * @return Matched tasks.
     */
    private String findTask(String x) {
        assert tasks != null : "No existing list";
        assert ui != null : "No existing ui";

        if (x == null || x.trim().isEmpty()) {
            throw new InvalidFindingException("Missing keyword");
        }

        int counter = 1;
        String res = "";
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            if (t.convertToString().contains(x)) {
                res = String.join("", res + "\n", ui.sendMessage(counter + " " + t.convertToString()));
                counter += 1;
            }
        }

        if (counter == 1) {
            return ui.sendMessage("No matching found");
        } else {
            return String.join("", res + "\n", ui.sendMessage("You have " + (counter-1) + " matching tasks in your list"));
        }
    }
}
