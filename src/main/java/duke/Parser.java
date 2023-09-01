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
     * Returns true is the user wants to end the conversation.
     */
    public boolean isExit() {
        return this.isExit;
    }

    /**
     * Reads the command given and executes them.
     * Throws exceptions when fail to comprehend the command.
     *
     * @param s The command line.
     * @throws DateTimeParseException If the input format of the dates is invalid.
     */
    public void parse(String s) throws DateTimeParseException {
        String[] stringList = s.split(" ", 2);
        String first = stringList[0];
        String second = null;
        try {
            second = stringList[1];
        } catch (IndexOutOfBoundsException e) {
            // do nothing
        }
        switch (first) {
        case "bye":
            break;
        case "list":
            printList();
            break;
        case "mark":
            markDone(second);
            break;
        case "unmark":
            markUndone(second);
            break;
        case "todo":
            addTodo(second);
            break;
        case "deadline":
            addDeadline(second);
            break;
        case "event":
            addEvent(second);
            break;
        case "delete":
            delete(second);
            break;
        case "find":
            find(second);
            break;
        default:
            throw new InvalidInputException("OOPS! I do not know what " + first
                    + " means. Please try again :)");
        }
        if (first.equals("bye")) {
            ending();
        }
    }

    /**
     * Reads the inputs passed from a file and adds the tasks into the TaskArray.
     *
     * @param s The input line.
     */
    public void parseFromFile(String s) {
        String[] chars = s.split(" / ");
        String type = chars[0];
        boolean isDone = chars[1].equals("1");
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("HH:mm");
        Task t;
        switch (type) {
        case "[T]":
            t = new Todo(chars[2]);
            if (isDone) {
                t.markDone();
            }
            tasks.add(t);
            break;
        case "[D]":
            if (chars.length == 4) {
                t = new Deadline(chars[2], LocalDate.parse(chars[3]));
            } else {
                t = new Deadline(chars[2], LocalDate.parse(chars[3]),
                        LocalTime.parse(chars[4], dateFormat));
            }
            if (isDone) {
                t.markDone();
            }
            tasks.add(t);
            break;
        case "[E]":
            if (chars.length == 7) {
                t = new Event(chars[2], LocalDate.parse(chars[3]), LocalTime.parse(chars[4], dateFormat),
                        LocalDate.parse(chars[5]), LocalTime.parse(chars[6]));
            } else if (chars.length == 5) {
                t = new Event(chars[2], LocalDate.parse(chars[3]), LocalDate.parse(chars[4]));
            } else {
                if (chars[5].length() > 5) {
                    t = new Event(chars[2], LocalDate.parse(chars[3]), LocalTime.parse(chars[4], dateFormat), //chars
                            LocalDate.parse(chars[5]));
                } else {
                    t = new Event(chars[2], LocalDate.parse(chars[3]), LocalDate.parse(chars[4]),
                            LocalTime.parse(chars[5], dateFormat));
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
     * Prints out the tasks in the TaskArray.
     */
    private void printList() {
        if (tasks.isEmpty()) {
            this.ui.print("list is empty :(");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                this.ui.print(i + 1 + " " + tasks.get(i).toString());
            }
        }
    }

    private void ending() {
        this.isExit = true;
        this.ui.farewell();
    }

    /**
     * Adds a todo task into the list.
     *
     * @param x Details of the task.
     */
    private void addTodo(String x) {
        if (x == null || x.trim().isEmpty()) {
            throw new LackDescriptionException("todo");
        }
        Todo t = new Todo(x);
        tasks.add(t);
        addedTask(x);
    }

    /**
     * Adds a deadline task into the list.
     *
     * @param x Details of the task.
     */
    private void addDeadline(String x) {
        if (x == null || x.trim().isEmpty() || x.trim().startsWith("/by")) {
            throw new LackDescriptionException("deadline");
        }

        String[] s = x.split(" /by ");
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
        addedTask(description);
    }

    /**
     * Adds an event task into the list.
     *
     * @param x Details of the task.
     */
    private void addEvent(String x) {
        if (x == null || x.trim().isEmpty() || x.trim().startsWith("/from") || x.trim().startsWith("/to")) {
            throw new LackDescriptionException("event");
        }

        String[] s = x.split(" /from ");
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
        addedTask(description);
    }

    private void addedTask(String x) {
        this.ui.print("Added to list: " + x);
        this.ui.print("Now you have " + tasks.size()
                + (tasks.size() == 1 ? " task " : " tasks ") + "in the list");
    }

    /**
     * Marks the task as done.
     *
     * @param x Index of the target task.
     */
    private void markDone(String x) {
        if (x == null || x.trim().isEmpty()) {
            throw new InvalidMarkingException("Missing index");
        }

        int j;
        try {
            j = Integer.parseInt(x);
        } catch (NumberFormatException e) {
            throw new InvalidMarkingException("Please provide a valid index");
        }

        if (j-1 > tasks.size()-1 || j-1<0) {
            throw new InvalidMarkingException("There is no corresponding task in the list");
        }

        Task t = tasks.get(j-1);
        t.markDone();

        this.ui.print("Task marked as done.");
    }

    /**
     * Marks the task as undone.
     *
     * @param x Index of the target task.
     */
    private void markUndone(String x) {
        if (x == null || x.trim().isEmpty()) {
            throw new InvalidMarkingException("Missing index");
        }

        int j;
        try {
            j = Integer.parseInt(x);
        } catch (NumberFormatException e) {
            throw new InvalidMarkingException("Please provide a valid index");
        }

        if (j-1 > tasks.size()-1 || j-1<0) {
            throw new InvalidMarkingException("There is no corresponding task in the list");
        }

        Task t = tasks.get(j-1);
        t.markUndone();

        this.ui.print("Task marked as undone.");
    }

    /**
     * Deletes a task from the list.
     *
     * @param x Index of the target task.
     */
    private void delete(String x) {
        if (x == null || x.trim().isEmpty()) {
            throw new InvalidMarkingException("Missing index");
        }

        int j;
        try {
            j = Integer.parseInt(x);
        } catch (NumberFormatException e) {
            throw new InvalidMarkingException("Please provide a valid index");
        }

        if (j - 1 > tasks.size() - 1 || j - 1 < 0) {
            throw new InvalidMarkingException("There is no corresponding task in the list");
        }

        Task t = tasks.get(j - 1);
        tasks.remove(j - 1);

        this.ui.print("I've removed this task:");
        this.ui.print(t);
        this.ui.print("Now you have " + tasks.size() + (tasks.size() > 1 ? " tasks" : " task")
                + " in the list");
    }

    private void find(String x) {
        if (x == null || x.trim().isEmpty()) {
            throw new InvalidFindingException("Missing keyword");
        }

        int counter = 1;
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            if (t.toString().contains(x)) {
                this.ui.print(counter + " " + t);
                counter += 1;
            }
        }

        if (counter == 1) {
            this.ui.print("No matching found");
        } else {
            this.ui.print("You have " + (counter-1) + " matching tasks in your list");
        }
    }
}
