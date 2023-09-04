package puke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;

import java.time.LocalDateTime;

public class Puke {
    private TaskList tasks;
    private final Ui ui;
    public Puke() throws IOException {
        this.ui = new Ui();
        try {
            tasks = new TaskList(DataHandler.loadDatabase());
        } catch (PukeException e) {
            new File("ListData.txt").createNewFile();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.startup();
        boolean isExit = false;
        while (!isExit) {
            String command = ui.command();
            String input = ui.input();
            ui.line();
            Command next;
            try {
                next = Parser.parse(command, input);
            } catch (PukeException e) {
                next = new ErrorCommand();
            }
            next.execute(tasks, ui);
            isExit = next.isExit();
        }
    }

    public static void main(String[] args) throws IOException {
        new Puke().run();
    }
}

class Task {
    protected String tag;
    protected boolean done;
    protected String description;
    protected Task(String tag, String description) throws PukeException {
        this.tag = tag;
        this.description = description;
        this.done = false;
        if (tag.isEmpty() || description.isEmpty()) {
            throw new PukeException();
        }
    }

    /**
     * Marks a task as done
     */
    public void mark() {
        this.done = true;
    }

    /**
     * Marks a task as undone
     */
    public void unmark() {
        this.done = false;
    }

    public String getDescription() {
        return description;
    }


    /**
     * Returns a String representation of the task that can be stored and read later when the program is initiated again.
     *
     * @return a String representation.
     */
    public String write() {
        int checked = 0;
        if (this.done) {
            checked = 1;
        }
        return String.format("%s/%d/%s", this.tag, checked , this.description);
    }

    /**
     * Returns a String representation to be displayed in the to do list.
     *
     * @return a String representation.
     */
    @Override
    public String toString() {
        String status = "[ ]";
        if (done) {
            status = "[X]";
        }
        return String.format("%s%s %s", this.tag, status, this.description);
    }
}

class ToDo extends Task {
    private final static String tag = "[T]";

    /**
     * Creates a Task with no set deadline or time period.
     *
     * @param desc The description of the task
     * @throws PukeException If an incorrect format is used.
     */
    public ToDo(String desc) throws PukeException {
        super(tag, desc);
    }
}

class Deadline extends Task {
    private final static String tag = "[D]";
    private final LocalDateTime date;

    /**
     * Creates a Task with a set deadline.
     *
     * @param all All strings from the remainder of the input after being split
     * @throws PukeException If an incorrect format is used.
     */
    public Deadline(String[] all) throws PukeException {
        super(tag, all[0]);
        try {
            this.date = LocalDateTime.parse(all[1].split("by ")[1]);
        } catch (Exception DateTimeParseException) {
            throw new PukeException();
        }
    }

    /**
     * Creates a Deadline Task using input from the ListData.txt file.
     *
     * @param desc The description of the task.
     * @param date The date of the deadline.
     * @return The Deadline task.
     * @throws PukeException If an incorrect format is detected e.g. the file is corrupted.
     */
    public static Deadline construct(String desc, String date) throws PukeException {
        String[] container = new String[2];
        container[0] = desc;
        container[1] = "by " + date;
        return new Deadline(container);
    }

    /**
     * Returns a String representation of the Deadline task that is stored in the ListData.txt file.
     *
     * @return a String representation.
     */
    @Override
    public String write() {
        return super.write() + "/" + this.date;
    }

    /**
     * Returns a String representation of the Deadline task that is used for Displaying in the to do list.
     *
     * @return a String representation
     */
    public String toString() {
        return super.toString() + " (by: " + this.date + ")";
    }
}

class Event extends Task {
    private static final String tag = "[E]";
    private final LocalDateTime from;
    private final LocalDateTime to;

    /**
     * Creates a task with a start and end time
     *
     * @param all All Strings from the remainder of the input line after the command string.
     * @throws PukeException If an incorrect format is used.
     */
    public Event(String[] all) throws PukeException {
        super(tag, all[0]);
        try {
            this.from = LocalDateTime.parse(all[1].split("from ")[1]);
            this.to = LocalDateTime.parse(all[2].split("to ")[1]);
        } catch (Exception DateTimeParseException) {
            System.out.println(all[1].split("from ")[1]);
            throw new PukeException();
        }
    }

    /**
     * Creates an Event task using data stored in the ListData.txt file.
     *
     * @param desc Description of the event
     * @param from Start date and time of the event.
     * @param to Ending date and time of the event.
     * @return The Event task.
     * @throws PukeException If an incorrect format is detected e.g. the file is corrupted.
     */
    public static Event construct(String desc, String from, String to) throws PukeException {
        String[] container = new String[3];
        container[0] = desc;
        container[1] = "from " + from;
        container[2] = "to " + to;
        return new Event(container);
    }

    /**
     * Returns a String representation of the Deadline task that is stored in the ListData.txt file.
     *
     * @return a String representation.
     */
    @Override
    public String write() {
        return super.write() + "/" + this.from + "/" + this.to;
    }

    /**
     * Returns a String representation of the Deadline task that is used for Displaying in the to do list.
     *
     * @return a String representation.
     */
    public String toString() {
        return super.toString() + " (from: " + this.from + " " +
                "to: " + this.to + ")";
    }
}

class PukeException extends Exception {
    public PukeException() {
        super("Unfortunately, the circumstances preceding this has necessitated that I issue and apology for the input that I have received is unrecognised.");
    }
}

class DataHandler {

    /**
     * Interprets a line from the ListData.txt file used to store events.
     *
     * @param input a line from the file
     * @return a corresponding task.
     * @throws PukeException If an invalid task is detected.
     */
    public static Task translate(String input) throws PukeException {
        String[] split = input.split("/");
        Task output;
        if (split[0].equals("[T]")) {
            output = new ToDo(split[2]);
        } else if (split[0].equals("[D]")) {
            output = Deadline.construct(split[2], split[3]);
        } else if (split[0].equals("[E]")) {
            output = Event.construct(split[2], split[3], split[4]);
        } else {
            throw new PukeException();
        }

        if (split[1].equals("0")) {
            output.unmark();
        } else if (split[1].equals("1")) {
            output.mark();
        } else {
            throw new PukeException();
        }

        return output;
    }

    /**
     * Updates the ListData.txt file with the latest list of tasks.
     *
     * @param taskList The task list.
     * @throws IOException If an error occurs with the file writer.
     */
    public static void writeToDatabase(TaskList taskList) throws IOException {
        FileWriter fw = new FileWriter("ListData.txt");
        StringBuilder output = new StringBuilder();
        for (Task item:taskList.getList()) {
            output.append(item.write()).append("\n");
        }
        fw.write(output.toString());
        fw.close();
    }

    /**
     * Loads the events stored in the ListData.txt file when the program is run.
     * If an invalid line is detected, it is skipped.
     *
     * @return The Task List
     * @throws PukeException If the file is not found.
     */
    public static ArrayList<Task> loadDatabase() throws PukeException {
        Scanner sc;
        try {
            sc = new Scanner(new File("ListData.txt"));
        } catch (Exception e) {
            throw new PukeException();
        }
        ArrayList<Task> output = new ArrayList<Task>();
        while (sc.hasNext()) {
            try {
                output.add(DataHandler.translate(sc.nextLine()));
            } catch (Exception e) {
                continue;
            }
        }
        return output;
    }

    /**
     * Clears all stored tasks.
     *
     * @throws IOException If an error occurs with the FileWriter.
     */
    public static void clearAll() throws IOException {
        FileWriter fw = new FileWriter("ListData.txt");
        fw.write("");
        fw.close();
    }
}

class TaskList {
    private final ArrayList<Task> list;

    TaskList() {
        this.list = new ArrayList<Task>(100);
    }

    TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    String printOut() {
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (Task s : list) {
            sb.append(String.format("%d. %s\n", i, s.toString()));
            i++;
        }
        return sb.toString();
    }

    Task get(int index) throws PukeException {
        try {
            return list.get(index);
        } catch (Exception e) {
            throw new PukeException();
        }
    }

    ArrayList<Task> getList() {
        return this.list;
    }

    void add(Task t) {
        this.list.add(t);
    }

    Task delete(int index) throws PukeException {
        Task hold;
        try {
            hold = list.get(index - 1);
            list.remove(index - 1);
            return hold;
        } catch (Exception e) {
            throw new PukeException();
        }
    }

    String find(String key) {
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (Task s : list) {
            if (s.getDescription().contains(key)) {
                sb.append(String.format("%d. %s\n", i, s.toString()));
                i++;
            }
        }
        return sb.toString();
        }

    void mark(int index) throws PukeException {
        try {
            list.get(index - 1).mark();
        } catch (Exception e) {
            throw new PukeException();
        }
    }

    void unmark(int index) throws PukeException {
        try {
            list.get(index - 1).unmark();
        } catch (Exception e) {
            throw new PukeException();
        }
    }

    int size() {
        return list.size();
    }

    void clear() {
        this.list.clear();
    }
}

abstract class Command {
    private final boolean exit;
    protected boolean isValid;

    Command(boolean exit, boolean valid) {
        this.exit = exit;
        this.isValid = valid;
    }
    abstract void execute(TaskList tl, Ui ui);

    boolean isExit() {
        return this.exit;
    }

}

class ExitCommand extends Command {

    ExitCommand(String rest) {
        super(rest.isEmpty(), rest.isEmpty());
    }

    /**
     * Executes the command by printing out the corresponding message.
     * If the command is in the wrong format.
     *
     * @param tl The task list.
     * @param ui The UI.
     */
    void execute(TaskList tl, Ui ui) {
        if (!super.isValid) {
            System.out.println(Ui.ERROR_MESSAGE);
            System.out.println(Ui.SEPARATOR);
        } else {
            System.out.println(ui.exit());
        }
    }

    /**
     * Returns a boolean indicating if the other object is an instance of ExitCommand.
     *
     * @param other Another object.
     * @return a boolean.
     */
    @Override
    public boolean equals(Object other) {
        return (other instanceof ExitCommand);
    }
}

class ListCommand extends Command {

    ListCommand(String rest) {
        super(false, rest.isEmpty());
    }

    /**
     * Executes the command by printing out the corresponding message.
     * If the command is in the wrong format.
     *
     * @param tl The task list.
     * @param ui The UI.
     */
    void execute(TaskList tl, Ui ui) {
        if (!super.isValid) {
            System.out.println(Ui.ERROR_MESSAGE);
            System.out.println(Ui.SEPARATOR);
        } else {
            System.out.println(ui.list());
            System.out.println(tl.printOut());
            System.out.println(Ui.SEPARATOR);
        }
    }

    /**
     * Returns a boolean indicating if the other object is an instance of ListCommand.
     *
     * @param other Another object
     * @return a boolean
     */
    @Override
    public boolean equals(Object other) {
        return (other instanceof ListCommand);
    }
}

class MarkCommand extends Command {
    private final int index;

    MarkCommand(String rest) {
        super(false, true);
        this.index = Integer.parseInt(rest);
    }

    /**
     * Executes the command by printing out the corresponding message.
     * If the command is in the wrong format or an index is out of bounds, prints an error message instead.
     *
     * @param tl The task list.
     * @param ui The UI.
     */
    void execute(TaskList tl, Ui ui) {
        try {
            tl.mark(this.index);
            System.out.println(ui.mark(this.index));
            System.out.println(Ui.SEPARATOR);
            DataHandler.writeToDatabase(tl);
        } catch (Exception PukeException) {
            System.out.println(Ui.ERROR_MESSAGE);
            System.out.println(Ui.SEPARATOR);
        }
    }

    /**
     * Returns a boolean indicating if the other object has the same toString() and is an instance of MarkCommand.
     *
     * @param other Another object
     * @return a boolean.
     */
    @Override
    public boolean equals(Object other) {
        return (other instanceof MarkCommand && other.toString().equals(this.toString()));
    }

    /**
     * Returns a String representation of the command
     *
     * @return a String.
     */
    @Override
    public String toString() {
        return "mark " + this.index;
    }
}

class UnmarkCommand extends Command {
    private int index;

    UnmarkCommand(String rest) {
        super(false, true);
        try {
            this.index = Integer.parseInt(rest.substring(1));
        } catch (Exception e) {
            this.index = -1;
        }
    }

    /**
     * Executes the command by printing out the corresponding message.
     * If the command is in the wrong format or an index is out of bounds, prints an error message instead.
     *
     * @param tl The task list.
     * @param ui The UI.
     */
    void execute(TaskList tl, Ui ui) {
        try {
            tl.unmark(this.index);
            System.out.println(ui.unmark());
            System.out.println(Ui.SEPARATOR);
            DataHandler.writeToDatabase(tl);
        } catch (Exception e) {
            System.out.println(Ui.ERROR_MESSAGE);
            System.out.println(Ui.SEPARATOR);
        }
    }

    /**
     * Returns a boolean indicating if the other object has the same toString() and is an instance of UnmarkCommand.
     *
     * @param other Another object
     * @return a boolean.
     */
    @Override
    public boolean equals(Object other) {
        return (other instanceof UnmarkCommand && other.toString().equals(this.toString()));
    }

    /**
     * Returns a String representation of the command
     *
     * @return a String.
     */
    @Override
    public String toString() {
        return "unmark " + this.index;
    }
}

class TodoCommand extends Command {
    private final String desc;

    TodoCommand(String rest) {
        super(false, !rest.isEmpty());
        this.desc = rest;
    }

    /**
     * Executes the command by printing out the corresponding message.
     * If the command is in the wrong format, prints an error message instead.
     *
     * @param tl The task list.
     * @param ui The UI.
     */
    void execute(TaskList tl, Ui ui) {
        try {
            tl.add(new ToDo(this.desc));
            System.out.println(ui.toDo(tl));
            System.out.println(Ui.SEPARATOR);
            DataHandler.writeToDatabase(tl);
        } catch (Exception PukeException) {
            System.out.println(Ui.ERROR_MESSAGE);
            System.out.println(Ui.SEPARATOR);
        }
    }

    /**
     * Returns a boolean indicating if the other object has the same toString as this command and is an instance of EventCommand.
     *
     * @param other Another object.
     * @return A boolean.
     */
    @Override
    public boolean equals(Object other) {
        return (other instanceof TodoCommand && other.toString().equals(this.toString()));
    }

    /**
     * Returns a String representation of this command.
     *
     * @return a String.
     * @throws RuntimeException If an incorrect format is used
     */
    @Override
    public String toString() {
        try {
            return new ToDo(this.desc).toString();
        } catch (PukeException e) {
            throw new RuntimeException(e);
        }
    }
}

class DeadlineCommand extends Command {
    private final String[] rest;

    DeadlineCommand(String rest) {
        super(false, !rest.isEmpty());
        this.rest = rest.split(" /");
    }

    /**
     * Executes the command by printing out the corresponding message.
     * If the command is in the wrong format, prints an error message instead.
     *
     * @param tl The task list.
     * @param ui The UI.
     */
    void execute(TaskList tl, Ui ui) {
        try {
            tl.add(new Deadline(this.rest));
            System.out.println(ui.deadline(tl));
            System.out.println(Ui.SEPARATOR);
            DataHandler.writeToDatabase(tl);
        } catch (Exception PukeException) {
            System.out.println(Ui.ERROR_MESSAGE);
            System.out.println(Ui.SEPARATOR);
        }
    }

    /**
     * Returns a boolean indicating if the other object has the same toString as this command and is an instance of EventCommand.
     *
     * @param other Another object.
     * @return A boolean.
     */
    @Override
    public boolean equals(Object other) {
        return (other instanceof DeadlineCommand && other.toString().equals(this.toString()));
    }

    /**
     * Returns a String representation of this command.
     *
     * @return a String.
     * @throws RuntimeException If an incorrect format is used
     */
    @Override
    public String toString() {
        try {
            return new Deadline(this.rest).toString();
        } catch (PukeException e) {
            throw new RuntimeException(e);
        }
    }
}

class EventCommand extends Command {
    private final String[] rest;

    EventCommand(String rest) {
        super(false, !rest.isEmpty());
        this.rest = rest.split(" /");
    }

    /**
     * Executes the command by printing out the corresponding message.
     * If the command is in the wrong format, prints an error message instead.
     *
     * @param tl The task list.
     * @param ui The UI.
     */
    void execute(TaskList tl, Ui ui) {
        try {
            tl.add(new Event(this.rest));
            System.out.println(ui.event(tl));
            System.out.println(Ui.SEPARATOR);
            DataHandler.writeToDatabase(tl);
        } catch (Exception PukeException) {
            System.out.println(Ui.ERROR_MESSAGE);
            System.out.println(Ui.SEPARATOR);
        }
    }

    /**
     * Returns a boolean indicating if the other object has the same toString as this command and is an instance of EventCommand.
     *
     * @param other Another object.
     * @return A boolean.
     */
    @Override
    public boolean equals(Object other) {
        return (other instanceof EventCommand && other.toString().equals(this.toString()));
    }

    /**
     * Returns a String representation of this command.
     *
     * @return a String.
     * @throws RuntimeException If an incorrect format is used
     */
    @Override
    public String toString() {
        try {
            return new Event(this.rest).toString();
        } catch (PukeException e) {
            throw new RuntimeException(e);
        }
    }
}

class DeleteCommand extends Command {
    private final int index;

    DeleteCommand(String rest) {
        super(false, true);
        this.index = Integer.parseInt(rest);
    }

    /**
     * Executes the command by printing out the corresponding message.
     * If the command is invalid due to the index being out of bounds, prints an error message instead.
     *
     * @param tl The task list.
     * @param ui The UI.
     */
    void execute(TaskList tl, Ui ui) {
        try {
            Task hold = tl.delete(this.index);
            System.out.println(ui.delete(hold, tl));
            System.out.println(Ui.SEPARATOR);
            DataHandler.writeToDatabase(tl);
        } catch (Exception PukeException) {
            System.out.println(Ui.ERROR_MESSAGE);
            System.out.println(Ui.SEPARATOR);
        }
    }

    /**
     * Returns a boolean indicating if the other object has the same toString() as this one.
     *
     * @param other Another object.
     * @return a boolean.
     */
    @Override
    public boolean equals(Object other) {
        return (other instanceof DeleteCommand && other.toString().equals(this.toString()));
    }

    /**
     * Returns a string representing this command.
     *
     * @return a String
     */
    @Override
    public String toString() {
        return "delete " + this.index;
    }
}

class ClearCommand extends Command {
    ClearCommand(String rest) {
        super(false, rest.isEmpty());
    }

    /**
     * Executes the command by printing out the corresponding message.
     * If the command is invalid, an error message is printed instead.
     *
     * @param tl The task list.
     * @param ui The UI.
     */
    void execute(TaskList tl, Ui ui) {
        if (!super.isValid) {
            System.out.println(Ui.ERROR_MESSAGE);
            System.out.println(Ui.SEPARATOR);
        } else {
            try {
                tl.clear();
                DataHandler.clearAll();
                System.out.println(ui.clear());
                System.out.println(Ui.SEPARATOR);
            } catch (Exception e) {
                tl.clear();
                System.out.println(ui.clear());
                System.out.println(Ui.SEPARATOR);
            }
        }
    }

    /**
     * Returns a boolean indicating if the other object is an instance of ClearCommand.
     * Used in testing.
     *
     * @param other Another object.
     * @return a boolean.
     */
    @Override
    public boolean equals(Object other) {
        return (other instanceof ClearCommand);
    }
}

class ErrorCommand extends Command {
    ErrorCommand() {
        super(false, false);
    }


    /**
     * Executes the command by printing out the corresponding message.
     *
     * @param tl The task list.
     * @param ui The UI.
     */
    void execute(TaskList tl, Ui ui) {
        System.out.println(Ui.ERROR_MESSAGE);
        System.out.println(Ui.SEPARATOR);
    }

    /**
     * Returns the boolean representing whether another Object is an instance of an ErrorCommand.
     * Used in testing.
     *
     * @param other Another object.
     * @return boolean
     */
    @Override
    public boolean equals(Object other) {
        return (other instanceof ErrorCommand);
    }
}

class FindCommand extends Command {
    private final String key;
    FindCommand(String rest) {
        super(false, true);
        this.key = rest;
    }

    void execute(TaskList tl, Ui ui) {
        try {
            System.out.println(ui.find());
            System.out.println(tl.find(this.key));
            System.out.println(Ui.SEPARATOR);
        } catch (Exception PukeException) {
            System.out.println(Ui.ERROR_MESSAGE);
            System.out.println(Ui.SEPARATOR);
        }
    }
    }

class Parser {
    /**
     * Parses the command string as input from the UI and returns its corresponding command
     *
     * @param command First token of input from the UI
     * @param line Remaining input from the UI on the same line
     * @return Corresponding command
     * @throws PukeException If an invalid command or line is parsed
     */
    public static Command parse(String command, String line) throws PukeException {
        try {
            if (command.equals("bye")) {
                return new ExitCommand(line);
            } else if (command.equals("list")) {
                return new ListCommand(line);
            } else if (command.equals("mark")) {
                return new MarkCommand(line.substring(1));
            } else if (command.equals("unmark")) {
                return new UnmarkCommand(line.substring(1));
            } else if (command.equals("todo")) {
                return new TodoCommand(line.substring(1));
            } else if (command.equals("deadline")) {
                return new DeadlineCommand(line.substring(1));
            } else if (command.equals("event")) {
                return new EventCommand(line.substring(1));
            } else if (command.equals("delete")) {
                return new DeleteCommand(line.substring(1));
            } else if (command.equals("clearall")) {
                return new ClearCommand(line);
            } else if (command.equals("find")) {
                return new FindCommand(line.substring(1));
            } else {
                return new ErrorCommand();
            }
        } catch (Exception e) {
            return new ErrorCommand();
        }
    }
}

class Ui {

    public static String ERROR_MESSAGE = "Unfortunately, the circumstances preceding this has necessitated that I issue and apology for the input that I have received is unrecognised.";
    public static String SEPARATOR = "____________________________________________________________";

    private final Scanner sc;

    Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Returns the first token from a line of input.
     *
     * @return Command string.
     */
    public String command() {
        return sc.next();
    }

    /**
     * Returns the remainder of the line of input after the command string has been removed.
     *
     * @return Remainder of line.
     */
    public String input() {
        return sc.nextLine();
    }

    /**
     * Prints the separator line.
     */
    public void line() {
        System.out.println(Ui.SEPARATOR);
    }

    /**
     * Prints the welcome logo and message.
     */
    void startup() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| |_| | | | | |/ / _ \\\n"
                + "| ___/| |_| |    | __/\n"
                + "| |    \\__,_|_|\\_\\___|\n"
                + "|_|";
        System.out.println("Salutations! I hereby would like to inform you that my identity is that of\n" + logo +
                "\nAn exceedingly verbose conversation simulation program.");
        System.out.println(SEPARATOR);
    }

    /**
     * Returns the exit message.
     *
     * @return The exit message.
     */
    String exit() {
        return "It appears that the user has decided to close the program as indicated by the command of which this is the function being issued and therefore,\n" +
                "I shall bid thee farewell and wish thee great fortune in your future endeavors.";
    }

    /**
     * Returns the message for listing tasks in the list.
     *
     * @return The message for listing tasks in the list.
     */
    String list() {
        return "Here is the collection of items, previously designated to be known as Tasks, that you have inputted over a previous unspecified period of time\n" +
                "that may or may not require urgent attention, but will nevertheless necessitate some level of action within an either\n" +
                "indicated or not indicated time period.";
    }

    /**
     * Returns the message for marking a task as done.
     *
     * @param index Index of the task that has been marked.
     * @return The message indicating that the task has been done.
     */
    String mark(int index) {
        return "I have been made aware of your desire to indicate that the task numbered " + index +
                " has been since been achieved as of the time at which you hve stipulated as so.";
    }

    /**
     * Returns the message for marking a task as undone.
     *
     * @return The message indicating that that task has been unmarked.
     */
    String unmark() {
        return "Very well. I have acknowledged your request to unmark the task of specified index as having been completed and\n" +
                "will now proceed to set said task of specified index to be considered as having not yet been completed.";
    }

    /**
     * Returns the message indicating that a new to do task has been added to the list.
     *
     * @param tl The task list.
     * @return The message.
     * @throws PukeException If the task or values used in the list are out of bounds.
     */
    String toDo(TaskList tl) throws PukeException {
        return "Understood. I have hereby created a task known to require doing at a future time but with no such time being specified and inserted it into " +
                "the overall collection of said tasks that require action.\n" +
                "Here is a display of the added deadline task: " + tl.get(tl.size() - 1) + "\n" +
                "You now, in total, have " + tl.size() + " of these tasks recorded within said collection.";
    }

    /**
     * Returns the message indicating that a new Deadline task has been added to the list.
     *
     * @param tl The task list.
     * @return The message.
     * @throws PukeException If the task or values used in the list are out of bounds.
     */
    String deadline(TaskList tl) throws PukeException {
        return "Understood. I have hereby created a task known to require doing at a future time alongside the stipulated time that you have indicated and inserted it into " +
                "the overall collection of these tasks that require action.\n" +
                "Here is a display of the added deadline task: " + tl.get(tl.size() - 1) + "\n" +
                "You now, in total, have " + tl.size() + " of these tasks recorded within said collection.";
    }

    /**
     * Returns the message indicating that a new Event task has been added to the list.
     *
     * @param tl The task list.
     * @return The message.
     * @throws PukeException If the task or values used in the list are out of bounds.
     */
    String event(TaskList tl) throws PukeException {
        return "Understood. I have hereby created a task known to require participation for a set period of time alongside this stipulated duration that you have indicated and inserted it into " +
                "the overall collection of these tasks that require action.\n" +
                "Here is a display of the added deadline task: " + tl.get(tl.size() - 1) + "\n" +
                "You now, in total, have " + tl.size() + " of these tasks recorded within said collection.";
    }

    /**
     * Returns the message indicating that a Task has been removed from the list
     *
     * @param hold The task that was removed.
     * @param tl The task list.
     * @return The message.
     */
    String delete(Task hold, TaskList tl) {
        return "I have acknowledged your request to have the task allocated to the specific index at which you have mentioned removed from the collection of all\n" +
                "such tasks, colloquially known as your To Do list.\n" +
                "The task in question that has been deleted is: " + hold + "\n" +
                "As of this current moment, there are a total of " + tl.size() + " occurrences of tasks in your list.";
    }

    /**
     * Returns the message indicating that all tasks have been cleared from the list.
     *
     * @return The message.
     */
    String clear() {
        return "Well I certainly hope you had meant to do that because I am not going too ask for your confirmation. As per the aforementioned instructions, I shall now" +
                "purge all of the tasks that you have previously recorded and designated as requiring attention.";
    }

    String find() {
        return "As per the instructions provided, I shall initiate a search into your list of items, of which we have previously declared to be known as tasks due too their relatively \n" +
                "urgent need of attention within a specified or unspecified frame of time, for those of which have an alphabetical similarity to the frame of reference that you have provided.";
    }
}