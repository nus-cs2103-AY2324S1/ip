package peko;

import java.io.FileNotFoundException;

public class TaskHandler {

    private static final String lineBreak = "------------------------------------------"; //42
    private Commands command;
    private String description;

    public TaskHandler(Commands command, String description) {
        this.command = command;
        this.description = description;
    }

    private void echo() {
        if (description.isBlank()) {
            System.out.println("You didn't say anything peko?");
        } else {
            System.out.println(description);
        }

        System.out.println(lineBreak);
    }
    private void readArray() {
        StorageHandler.readArray();
        System.out.println(lineBreak);
    }
    private void addToArray() throws InvalidTaskException {
        Task t = new Task(description);
        StorageHandler.addToArray(t);
        System.out.println(lineBreak);
    }
    private void mark(String s) throws NumberFormatException{
        int i = Integer.parseInt(s);
        StorageHandler.setMarkArray(i);
        System.out.println(lineBreak);
    }
    private void unmark(String s) {
        try {
            int i = Integer.parseInt(s);
            StorageHandler.setUnmarkArray(i);
        } catch (NumberFormatException e) {
            System.out.println("That's not a number Bakatare!");
        }
        System.out.println(lineBreak);
    }
    private void todo(String s) throws InvalidTaskException{
        Task t = new ToDos(description);
        StorageHandler.addToArray(t);
        System.out.println(lineBreak);
    }
    private void deadline(String s) throws InvalidTaskException {
        Task t = new Deadline(description);
        StorageHandler.addToArray(t);
        System.out.println(lineBreak);
    }
    private void Event(String s) throws InvalidTaskException {
        Task t = new Event(description);
        StorageHandler.addToArray(t);
        System.out.println(lineBreak);
    }
    private void Find(String s) {
        Find find = new Find(s);
        find.display();
        System.out.println(lineBreak);
    }
    public void delete(String s) {
        int i = Integer.parseInt(s);
        StorageHandler.setDelete(i);
    }

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
