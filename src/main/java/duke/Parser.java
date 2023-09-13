package duke;

import duke.exception.InvalidFindingException;
import duke.exception.LackDescriptionException;
import duke.exception.LackInformationException;
import duke.exception.InvalidInputException;
import duke.exception.InvalidMarkingException;
import duke.exception.InvalidRankingException;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * An object that deals with making sense of user commands.
 */
public class Parser {
    /** The TaskList that the parser will be adding tasks into. */
    private TaskList tasks;
    /** The Ui that helps the parser to print messages. */
    private Ui ui;

    /**
     * Constructs a new Parser that stores tasks into the given TaskArray and prints through the given Ui.
     *
     * @param tasks The TaskList to store tasks.
     * @param ui The Ui to interact with users.
     */
    public Parser(TaskList tasks, Ui ui) {
        this.tasks = tasks;
        this.ui = ui;
    }

    /**
     * Constructs a new Parser that stores tasks into the given TaskArray.
     *
     * @param tasks The TaskList to store tasks.
     */
    public Parser(TaskList tasks) {
        this.tasks = tasks;
        this.ui = null;
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
            // do nothing, we will handle the case where otherWords == null below
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
        case "rank":
            return rankTask(otherWords);
        default:
            throw new InvalidInputException("OOPS! I do not know what " + firstWord
                    + " means. Please try again :)");
        }

        return null;
    }

    /**
     * Reads the inputs passed from a file and adds the tasks into the TaskList.
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

        Task t;

        switch (typeOfTask) {
        case "[T]":
            t = getTodoFromFile(strings, isDone);
            break;
        case "[D]":
            t = getDeadlineFromFile(strings, isDone);
            break;
        case "[E]":
            t = getEventFromFile(strings, isDone);
            break;
        default:
            throw new InvalidInputException("Invalid format from file");
        }
        tasks.add(t);
    }

    private Task getTodoFromFile(String[] line, boolean isDone) {
        int rank = Integer.parseInt(line[3]);
        Task t = new Todo(line[2], rank);
        if (isDone) {
            t.markDone();
        }
        return t;
    }

    private Task getDeadlineFromFile(String[] line, boolean isDone) {
        int rank;
        Task t;
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm");

        if (line.length == 5) {
            rank = Integer.parseInt(line[4]);
            t = new Deadline(line[2], LocalDate.parse(line[3]), rank);
        } else {
            rank = Integer.parseInt(line[5]);
            t = new Deadline(line[2], LocalDate.parse(line[3]),
                    LocalTime.parse(line[4], timeFormat), rank);
        }

        if (isDone) {
            t.markDone();
        }
        return t;
    }

    private Task getEventFromFile(String[] line, boolean isDone) {
        int rank;
        Task t;
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm");

        if (line.length == 8) {
            rank = Integer.parseInt(line[7]);
            t = new Event(line[2], LocalDate.parse(line[3]), LocalTime.parse(line[4], timeFormat),
                    LocalDate.parse(line[5]), LocalTime.parse(line[6]), rank);
        } else if (line.length == 6) {
            rank = Integer.parseInt(line[5]);
            t = new Event(line[2], LocalDate.parse(line[3]), LocalDate.parse(line[4]), rank);
        } else if (line[5].length() > 5) {
            rank = Integer.parseInt(line[6]);
            t = new Event(line[2], LocalDate.parse(line[3]), LocalTime.parse(line[4], timeFormat),
                    LocalDate.parse(line[5]), rank);
        } else {
            rank = Integer.parseInt(line[6]);
            t = new Event(line[2], LocalDate.parse(line[3]), LocalDate.parse(line[4]),
                    LocalTime.parse(line[5], timeFormat), rank);
        }

        if (isDone) {
            t.markDone();
        }
        return t;
    }

    /**
     * Returns a string containing the tasks in the TaskList.
     */
    private String printList() {
        assert tasks != null : "No existing list";
        assert ui != null : "No existing ui";
        if (tasks.isEmpty()) {
            return ui.sendMessage("list is empty :(");
        }
        String res = "";
        int counter = 1;
        ArrayList<Task> tempTasks = new ArrayList<>();
        while (!tasks.isEmpty()) {
            Task t = tasks.poll();
            res = String.join("",res + "\n", ui.sendMessage(counter + " " + t.convertToString()));
            tempTasks.add(t);
            counter += 1;
        }
        for (Task temp : tempTasks) {
            tasks.add(temp);
        }
        return res;
    }

    /**
     * Adds a todo task into the list.
     *
     * @param details Details of the task.
     * @return Returns the message from Duke.
     */
    private String addTodo(String details) {
        assert tasks != null : "No existing list";
        if (!hasToDoDescription(details)) {
            throw new LackDescriptionException("todo");
        }
        Todo t = new Todo(details, 0);
        tasks.add(t);
        return confirmAddedTask(details);
    }

    private boolean hasToDoDescription(String details) {
        return !(details == null || details.trim().isEmpty());
    }

    /**
     * Adds a deadline task into the list.
     *
     * @param details Details of the task.
     * @return Returns the message from Duke.
     */
    private String addDeadline(String details) {
        assert tasks != null : "No existing list";
        if (!hasDeadlineDescription(details)) {
            throw new LackDescriptionException("deadline");
        }

        String[] s = details.split(" /by ");
        String description = s[0];

        if (!hasByInformation(s)) {
            throw new LackInformationException("\"/by\"");
        }
        String deadline = s[1];

        String[] dateTime = deadline.split(" ");
        LocalDate date = getDeadlineDate(dateTime);
        LocalTime time = getDeadlineTime(dateTime);

        Deadline d = getDeadline(description, date, time);

        tasks.add(d);
        return confirmAddedTask(description);
    }

    private boolean hasDeadlineDescription(String details) {
        return !(details == null || details.trim().isEmpty() || details.trim().startsWith("/by"));
    }

    private boolean hasByInformation(String[] s) {
        return s.length > 1;
    }

    private boolean hasDeadlineTime(String[] dateTime) {
        return dateTime.length > 1;
    }

    private LocalDate getDeadlineDate(String[] s) {
        return LocalDate.parse(s[0]);
    }

    private LocalTime getDeadlineTime(String[] s) {
        if (!hasDeadlineTime(s)) {
            return null;
        }
        DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm");
        return LocalTime.parse(s[1], format);
    }

    private Deadline getDeadline(String description, LocalDate date, LocalTime time) {
        Deadline d;

        if (time == null) {
            d = new Deadline(description, date, 0);
        } else {
            d = new Deadline(description, date, time, 0);
        }
        return d;
    }

    /**
     * Adds an event task into the list.
     *
     * @param details Details of the task.
     * @return Returns the message from Duke.
     */
    private String addEvent(String details) {
        assert tasks != null : "No existing list";
        if (!hasEventDescription(details)) {
            throw new LackDescriptionException("event");
        }

        String[] s = details.split(" /from ");
        String description = s[0];

        if (!hasFromInformation(s)) {
            throw new LackInformationException("\"from\"");
        }
        String fromto = s[1];
        String[] ft = fromto.split(" /to ");

        String from = ft[0];
        String[] startDateTime = from.split(" ");
        LocalDate startDate = getEventDate(startDateTime);
        LocalTime startTime = getEventTime(startDateTime);

        if (!hasToInformation(ft)) {
            throw new LackInformationException("\"/to\"");
        }
        String to = ft[1];
        String[] endDateTime = to.split(" ");
        LocalDate endDate = getEventDate(endDateTime);
        LocalTime endTime = getEventTime(endDateTime);

        Event e = getEvent(description, startDate, startTime, endDate, endTime);
        tasks.add(e);
        return confirmAddedTask(description);
    }

    private boolean hasEventDescription(String details) {
        return !(details == null || details.trim().isEmpty() || details.trim().startsWith("/from")
                || details.trim().startsWith("/to"));
    }

    private boolean hasFromInformation(String[] s) {
        if (s.length <= 1) {
            return false;
        }
        String fromTo = s[1];
        return !fromTo.trim().startsWith("/to");
    }

    private boolean hasEventTime(String[] s) {
        return s.length > 1;
    }

    private LocalDate getEventDate(String[] s) {
        return LocalDate.parse(s[0]);
    }

    private LocalTime getEventTime(String[] s) {
        if (!hasEventTime(s)) {
            return null;
        }
        DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm");
        return LocalTime.parse(s[1], format);
    }

    private boolean hasToInformation(String[] s) {
        return s.length > 1;
    }

    private Event getEvent(String description, LocalDate startDate, LocalTime startTime,
                           LocalDate endDate, LocalTime endTime) {
        Event e;

        if (startTime == null && endTime == null) {
            e = new Event(description, startDate, endDate, 0);
        } else if (startTime == null) {
            e = new Event(description, startDate, endDate, endTime, 0);
        } else if (endTime == null) {
            e = new Event(description, startDate, startTime, endDate, 0);
        } else {
            e = new Event(description, startDate, startTime, endDate, endTime, 0);
        }
        return e;
    }

    /**
     * Returns a confirmation string telling the user that the task has been added.
     *
     * @param x Description of the task.
     */
    private String confirmAddedTask(String x) {
        assert tasks != null : "No existing list";
        assert ui != null : "No existing ui";
        return String.join("", ui.sendMessage("Added to list: " + x) + "\n",
                ui.sendMessage("Now you have " + tasks.size()));
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
        tasks.remove(t);

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
            return String.join("", res + "\n",
                    ui.sendMessage("You have " + (counter-1) + " matching tasks in your list"));
        }
    }

    /**
     * Updates the priority of the task.
     */
    private String rankTask(String details) {
        if (details == null || details.trim().isEmpty()) {
            throw new InvalidRankingException("Missing target task");
        }

        String[] strings = details.split(" /level ");
        int idxTask;
        try {
            this.ui.sendMessage(strings[0]);
            idxTask = Integer.parseInt(strings[0]);
        } catch (NumberFormatException e) {
            throw new InvalidRankingException("Please provide a valid index for task");
        }

        if (idxTask - 1 > tasks.size() - 1 || idxTask - 1 < 0) {
            throw new InvalidRankingException("There is no corresponding task in the list");
        }

        int rank;
        try {
            rank = Integer.parseInt(strings[1]);
        } catch (RuntimeException e) {
            throw new InvalidRankingException("Please provide a valid index for ranking");
        }

        Task t = tasks.get(idxTask - 1);
        t.setPriority(rank);
        tasks.remove(t);
        tasks.add(t);

        return this.ui.sendMessage("Priority of task has been updated successfully");
    }
}
