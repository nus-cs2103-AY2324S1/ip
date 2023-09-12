package peko.tasks;

import peko.commands.Commands;
import peko.commands.Find;
import peko.exceptions.InvalidTaskException;
import peko.memory.SaveHandler;
import peko.memory.StorageHandler;

import java.io.FileNotFoundException;

/**
 * The TaskHandler class is responsible for processing user commands related to task management
 * and generating appropriate responses or actions.
 */
public class TaskHandler {

    private static final String lineBreak = "------------------------------------------"; //42
    private Commands command;
    private String description;

    /**
     * Constructs a TaskHandler object with the specified user command and description.
     *
     * @param command     The user command.
     * @param description The description or additional input associated with the command.
     */
    public TaskHandler(Commands command, String description) {
        this.command = command;
        this.description = description;
    }

    /**
     * Echos back the user's input or displays a message if the input is blank.
     *
     * @return The echoed user input or a message.
     */
    private String echo() {
        String out;
        if (description.isBlank()) {
            out = "You didn't say anything peko?";
        } else {
            out = description;
        }

        System.out.println(out);
        System.out.println(lineBreak);
        return out;
    }

    /**
     * Reads and retrieves a list of tasks as a formatted string.
     *
     * @return A formatted string containing the list of tasks.
     */
    private String readArray() {
        return StorageHandler.readArray() + lineBreak;
    }

    /**
     * Adds a new task with the provided description to the task list.
     *
     * @return A message confirming the addition of the task.
     * @throws InvalidTaskException If the task description is invalid or empty.
     */
    private String addToArray() throws InvalidTaskException {
        Task t = new Task(description);
        String out = StorageHandler.addToArray(t);
        System.out.println(lineBreak);
        return out;
    }

    /**
     * Marks a task as done based on the provided task index.
     *
     * @param s The index of the task to be marked as done (as a string).
     * @return A message confirming the task has been marked as done.
     * @throws NumberFormatException If the provided index is not a valid number.
     */
    private String mark(String s) throws NumberFormatException{
        int i = Integer.parseInt(s);
        String out = StorageHandler.setMarkArray(i);
        System.out.println(lineBreak);
        return out;
    }

    /**
     * Unmarks a task as done based on the provided task index.
     *
     * @param s The index of the task to be unmarked (as a string).
     * @return A message confirming the task has been unmarked.
     * @throws NumberFormatException If the provided index is not a valid number.
     */
    private String unmark(String s) throws NumberFormatException{
        int i = Integer.parseInt(s);
        String out = StorageHandler.setUnmarkArray(i);
        System.out.println(lineBreak);
        return out;
    }

    /**
     * Adds a new task of the specified type with the provided description to the task list.
     *
     * @param s The description of the task.
     * @return A message confirming the addition of the task.
     * @throws InvalidTaskException If the task description is invalid or empty.
     */
    private String todo(String s) throws InvalidTaskException{
        Task t = new ToDos(description);
        String out = StorageHandler.addToArray(t);
        System.out.println(lineBreak);
        return out;
    }

    /**
     * Adds a new deadline task with the provided description to the task list.
     *
     * @param s The description of the task.
     * @return A message confirming the addition of the deadline task.
     * @throws InvalidTaskException If the task description is invalid or empty.
     */
    private String deadline(String s) throws InvalidTaskException {
        Task t = new Deadline(description);
        String out = StorageHandler.addToArray(t);
        System.out.println(lineBreak);
        return out;
    }

    /**
     * Adds a new event task with the provided description to the task list.
     *
     * @param s The description of the task.
     * @return A message confirming the addition of the event task.
     * @throws InvalidTaskException If the task description is invalid or empty.
     */
    private String Event(String s) throws InvalidTaskException {
        Task t = new Event(description);
        String out = StorageHandler.addToArray(t);
        System.out.println(lineBreak);
        return out;
    }

    /**
     * Finds and displays tasks that match the provided search query.
     *
     * @param s The search query used to filter tasks.
     * @return A formatted string containing the matching tasks.
     */
    private String Find(String s) {
        Find find = new Find(s);
        String out = find.display();
        System.out.println(lineBreak);
        return out;
    }

    /**
     * Deletes a task from the task list based on the provided task index.
     *
     * @param s The index of the task to be deleted (as a string).
     * @return A message confirming the deletion of the task.
     * @throws NumberFormatException If the provided index is not a valid number.
     */
    public String delete(String s) {
        int i = Integer.parseInt(s);
        StorageHandler.setDelete(i);
        String out = "I have deleted the task Peko";
        return out;
    }

    /**
     * Processes the user command, performs corresponding actions, and returns a response.
     *
     * @return A response message based on the executed command.
     */
    public String getResponse() {

        String out = "Gomen Peko I don't understand";
        ;
        try {

            switch (command) {
                case ECHO:
                    out = echo();
                    break;
                case LIST:
                    out = readArray();
                    break;
                case WRITE:
                    out = addToArray();
                    break;
                case MARK:
                    out = mark(description);
                    break;
                case UNMARK:
                    out = unmark(description);
                    break;
                case TODO:
                    out = todo(description);
                    break;
                case DEADLINE:
                    out = deadline(description);
                    break;
                case EVENT:
                    out = Event(description);
                    break;
                case FIND:
                    out = Find(description);
                    break;
                case DELETE:
                    out = delete(description);
                    break;
                case COPYPASTA:
                    try {
                        return StorageHandler.degen();
                    } catch (FileNotFoundException e) {
                        return "Hentai!";

                    } finally {
                        return "Something Went Wrong Peko....";
                    }
                case OTSUPEKO:
                    SaveHandler.saveTo();
                    return "";
                default:
                    System.out.println(out);
                    return out;
            }
        } catch (InvalidTaskException e) {
            System.out.println(e);
            return e.toString();
        } catch (NumberFormatException e) {
            out = ("That's not a number Bakatare!");

            return out;
        }
        System.out.println(out);
        return out;
    }

    /**
     * Executes the user command, performs corresponding actions, and returns whether the program should continue running.
     *
     * @return True if the program should continue running; false if it should exit.
     */
    public boolean run() {
        try {

            switch (command) {
            case ECHO:
                echo();
                return true;
            case LIST:
                readArray();
                return true;
            case WRITE:
                addToArray();
                return true;
            case MARK:
                mark(description);
                return true;
            case UNMARK:
                unmark(description);
                return true;
            case TODO:
                todo(description);
                return true;
            case DEADLINE:
                deadline(description);
                return true;
            case EVENT:
                Event(description);
                return true;
            case FIND:
                Find(description);
                return true;
            case DELETE:
                delete(description);
                return true;
            case COPYPASTA:
                try {
                    StorageHandler.degen();
                } catch (FileNotFoundException e) {
                    System.out.println("Hentai!");
                } finally {
                    return false;
                }
            case OTSUPEKO:
                SaveHandler.saveTo();
                return false;
            default:
                return true;

            }
        } catch (InvalidTaskException e) {
            System.out.println(e);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("That's not a number Bakatare!");

            return true;
        }
    }
}
