package duke.main;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import duke.command.FirstWord;
import duke.exception.DukeEmptyToDoException;
import duke.exception.DukeException;
import duke.exception.DukeInvalidDateFormatException;
import duke.exception.DukeInvalidMarkUnmarkArgumentException;
import duke.exception.DukeUnknownCommandException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * Parses command and assigns to respective tasks.
 */
public class Parser {
    private Scanner sc;
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a Parser object.
     * @param tasks
     * @param storage
     */
    public Parser(TaskList tasks, Storage storage) {
        this.tasks = tasks;
        this.sc = new Scanner(System.in);
        this.storage = storage;
        this.ui = new Ui();
    }

    /**
     * Lists out all tasks.
     */
    private String listOutTasks() {
        String tasksList = "";
        for (int i = 0; i < tasks.size(); i++) {
            tasksList += ui.listOutTasksString(i + 1, tasks.get(i).toString().trim());
        }

        String guiTalk = tasksList;
        return guiTalk;
    }

    /**
     * Exits chatbot.
     */
    private String exit() {
        String guiTalk = ui.exitString();
        return guiTalk;
    }

    /**
     * Marks task as done.
     * @param index
     */
    private String mark(int index) throws DukeInvalidMarkUnmarkArgumentException {
        try {
            Task task = tasks.get(index);
            task.mark();
            String guiTalk = ui.markString(task.toString());
            return guiTalk;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeInvalidMarkUnmarkArgumentException();
        }
    }

    /**
     * Marks task as not done.
     * @param index
     */
    private String unmark(int index) throws DukeInvalidMarkUnmarkArgumentException {
        try {
            Task task = tasks.get(index);
            task.unmark();
            String guiTalk = ui.unmarkString(task.toString());
            return guiTalk;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeInvalidMarkUnmarkArgumentException();
        }
    }

    /**
     * Adds a To Do task.
     * @param reply
     * @throws DukeException
     */
    private String addToDo(String reply) throws DukeException {
        if (reply.length() == "todo".length()) {
            throw new DukeEmptyToDoException();
        }

        String taskContent = reply.replace("todo ", "");
        Task task = new ToDo(taskContent);
        String guiTalk = addTask(task);
        return guiTalk;
    }

    /**
     * Adds a Deadline task.
     * @param reply
     * @throws DukeException
     */
    private String addDeadline(String reply) throws DukeException {
        String taskContent = reply.substring(0, reply.indexOf(" /by")).replace("deadline ", "");
        String due = reply.substring(reply.indexOf("/by") + 4);

        try {
            Task task = new Deadline(taskContent, LocalDate.parse(due));
            String guiTalk = addTask(task);
            return guiTalk;
        } catch (DateTimeParseException e) {
            throw new DukeInvalidDateFormatException();
        }

    }

    /**
     * Adds an Event task.
     * @param reply
     * @throws DukeException
     */
    private String addEvent(String reply) throws DukeException {
        String from = reply.substring(reply.indexOf("/from") + 6, reply.indexOf(" /to"));
        String to = reply.substring(reply.indexOf("/to") + 4);
        String taskContent = reply.substring(0, reply.indexOf(" /from")).replace("event ", "");

        try {
            Task task = new Event(taskContent, LocalDate.parse(from), LocalDate.parse(to));
            String guiTalk = addTask(task);
            return guiTalk;
        } catch (DateTimeParseException e) {
            throw new DukeInvalidDateFormatException();
        }
    }

    /**
     * Deletes a task.
     * @param index
     */
    private String delete(int index) {
        assert tasks.size() > 0 : "You have no task to delete.";
        Task removedTask = tasks.remove(index);
        storage.save();

        String guiTalk = ui.deleteString(removedTask.toString(), tasks.size());
        return guiTalk;
    }

    /**
     * Constructs message for adding task.
     * @param task
     */
    private String addTask(Task task) {
        assert task != null;
        tasks.add(task);
        storage.save();

        String guiTalk = ui.addTaskString(task.toString(), tasks.size());
        return guiTalk;
    }

    private String find(String reply) {
        assert tasks.size() > 0 : "Your task list is empty.";
        String keyword = reply.replace("find ", "");
        String guiTalk = ui.findString(tasks.find(keyword));
        return guiTalk;
    }

    /**
     * Triggers respective actions.
     */
    public String interact(String reply) {
        while (true) {
            // String reply = sc.nextLine();
            FirstWord firstWord;
            try {
                firstWord = FirstWord.valueOf(reply.split(" ")[0].toUpperCase());
            } catch (IllegalArgumentException e) {
                firstWord = FirstWord.INVALID;
            }
            try {
                switch (firstWord) {
                case BYE:
                    return exit();
                case LIST:
                    return listOutTasks();
                case MARK:
                    return mark(Character.getNumericValue(reply.charAt(5) - 1));
                case UNMARK:
                    return unmark(Character.getNumericValue(reply.charAt(7) - 1));
                case TODO:
                    return addToDo(reply);
                case DEADLINE:
                    return addDeadline(reply);
                case EVENT:
                    return addEvent(reply);
                case DELETE:
                    return delete(Character.getNumericValue(reply.charAt(7) - 1));
                case FIND:
                    return find(reply);
                default:
                    throw new DukeUnknownCommandException();
                }
            } catch (DukeException e) {
                // this.line(e.toString());
                return e.toString();
            }
        }
    }
}
