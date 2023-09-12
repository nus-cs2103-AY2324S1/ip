package peko.tasks;

import peko.commands.Commands;
import peko.commands.Find;
import peko.exceptions.InvalidTaskException;
import peko.memory.SaveHandler;
import peko.memory.StorageHandler;

import java.io.FileNotFoundException;

public class TaskHandler {

    private static final String lineBreak = "------------------------------------------"; //42
    private Commands command;
    private String description;

    public TaskHandler(Commands command, String description) {
        this.command = command;
        this.description = description;
    }

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
    private String readArray() {
        return StorageHandler.readArray() + lineBreak;
    }
    private String addToArray() throws InvalidTaskException {
        Task t = new Task(description);
        String out = StorageHandler.addToArray(t);
        System.out.println(lineBreak);
        return out;
    }
    private String mark(String s) throws NumberFormatException{
        int i = Integer.parseInt(s);
        String out = StorageHandler.setMarkArray(i);
        System.out.println(lineBreak);
        return out;
    }
    private String unmark(String s) throws NumberFormatException{
        int i = Integer.parseInt(s);
        String out = StorageHandler.setUnmarkArray(i);
        System.out.println(lineBreak);
        return out;
    }
    private String todo(String s) throws InvalidTaskException{
        Task t = new ToDos(description);
        String out = StorageHandler.addToArray(t);
        System.out.println(lineBreak);
        return out;
    }
    private String deadline(String s) throws InvalidTaskException {
        Task t = new Deadline(description);
        String out = StorageHandler.addToArray(t);
        System.out.println(lineBreak);
        return out;
    }
    private String Event(String s) throws InvalidTaskException {
        Task t = new Event(description);
        String out = StorageHandler.addToArray(t);
        System.out.println(lineBreak);
        return out;
    }
    private String Find(String s) {
        Find find = new Find(s);
        String out = find.display();
        System.out.println(lineBreak);
        return out;
    }
    public String delete(String s) {
        int i = Integer.parseInt(s);
        StorageHandler.setDelete(i);
        String out = "I have deleted the task Peko";
        return out;
    }

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

    public boolean run() {
        System.out.println("running");
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
