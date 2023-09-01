package duke.main;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import duke.command.FirstWord;
import duke.exception.DukeEmptyToDoException;
import duke.exception.DukeException;
import duke.exception.DukeInvalidDateFormatException;
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

    /**
     * Constructs a Parser object.
     * @param tasks
     * @param storage
     */
    public Parser(TaskList tasks, Storage storage) {
        this.tasks = tasks;
        this.sc = new Scanner(System.in);
        this.storage = storage;
    }
    /**
     * Adds horizontal line for replies by chatbot.
     * @param text
     */
    private void line(String text) {
        System.out.println(text);
        System.out.println("------------------------------------------");
    }

    /**
     * Lists out all tasks.
     */
    private void listOutTasks() {
        String tasksList = "";
        for (int i = 0; i < tasks.size(); i++) {
            tasksList += String.format("%d. %s\n", i + 1, tasks.get(i).toString().trim());
        }
        this.line(tasksList);
    }

    /**
     * Exits chatbot.
     */
    private void exit() {

        this.line("  Bye~ Hope to see you again soon! >w<");
    }

    /**
     * Marks task as done.
     * @param index
     */
    private void mark(int index) {
        Task task = tasks.get(index);
        task.mark();
        this.line(String.format("  Nice! I've marked this task as done:\n  %s", task.toString()));
    }

    /**
     * Marks task as not done.
     * @param index
     */
    private void unmark(int index) {
        Task task = tasks.get(index);
        task.unmark();
        this.line(String.format("  Ok, I've marked this task as not done yet:\n  %s", task.toString()));
    }

    /**
     * Adds a To Do task.
     * @param reply
     * @throws DukeException
     */
    private void addToDo(String reply) throws DukeException {
        if (reply.length() == "todo".length()) {
            throw new DukeEmptyToDoException();
        }

        String taskContent = reply.replace("todo ", "");
        Task task = new ToDo(taskContent);
        addTask(task);
    }

    /**
     * Adds a Deadline task.
     * @param reply
     * @throws DukeException
     */
    private void addDeadline(String reply) throws DukeException {

        String taskContent = reply.substring(0, reply.indexOf(" /by")).replace("deadline ", "");
        String due = reply.substring(reply.indexOf("/by") + 4);

        try {
            Task task = new Deadline(taskContent, LocalDate.parse(due));
            addTask(task);
        } catch (DateTimeParseException e) {
            throw new DukeInvalidDateFormatException();
        }

    }

    /**
     * Adds an Event task.
     * @param reply
     * @throws DukeException
     */
    private void addEvent(String reply) throws DukeException {

        String from = reply.substring(reply.indexOf("/from") + 6, reply.indexOf(" /to"));
        String to = reply.substring(reply.indexOf("/to") + 4);
        String taskContent = reply.substring(0, reply.indexOf(" /from")).replace("event ", "");

        try {
            Task task = new Event(taskContent, LocalDate.parse(from), LocalDate.parse(to));
            addTask(task);
        } catch (DateTimeParseException e) {
            throw new DukeInvalidDateFormatException();
        }
    }

    /**
     * Deletes a task.
     * @param index
     */
    private void delete(int index) {
        Task removedTask = tasks.remove(index);
        System.out.println(String.format("  Noted. I've removed this task:"));
        System.out.println(String.format("  %s", removedTask.toString()));
        this.line(String.format("  Now you have %d task(s) in the list.", tasks.size()));
        storage.save();
    }

    /**
     * Constructs message for adding task.
     * @param task
     */
    private void addTask(Task task) {
        System.out.println(String.format("  Got it. I've added this task:"));
        tasks.add(task);
        System.out.println(String.format("  %s", task.toString()));
        this.line(String.format("  Now you have %d task(s) in the list.", tasks.size()));
        storage.save();
    }

    private void find(String reply) {
        System.out.println(String.format("  Here are the matching tasks in your list:"));
        String keyword = reply.replace("find ", "");
        String matchingTasks = tasks.find(keyword);;
        this.line(matchingTasks);
    }

    /**
     * Triggers respective actions.
     */
    public void interact() {
        while (true) {
            String reply = sc.nextLine();
            FirstWord firstWord;
            try {
                firstWord = FirstWord.valueOf(reply.split(" ")[0].toUpperCase());
            } catch (IllegalArgumentException e) {
                firstWord = FirstWord.INVALID;
            }
            try {
                switch (firstWord) {
                case BYE:
                    exit();
                    break;
                case LIST:
                    listOutTasks();
                    break;
                case MARK:
                    mark(Character.getNumericValue(reply.charAt(5) - 1));
                    break;
                case UNMARK:
                    unmark(Character.getNumericValue(reply.charAt(7) - 1));
                    break;
                case TODO:
                    addToDo(reply);
                    break;
                case DEADLINE:
                    addDeadline(reply);
                    break;
                case EVENT:
                    addEvent(reply);
                    break;
                case DELETE:
                    delete(Character.getNumericValue(reply.charAt(7) - 1));
                    break;
                case FIND:
                    find(reply);
                    break;
                default:
                    throw new DukeUnknownCommandException();
                }
            } catch (DukeException e) {
                this.line(e.toString());
            }
        }
    }
}
