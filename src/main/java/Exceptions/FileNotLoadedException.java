package Exceptions;

public class FileNotLoadedException extends DukeException {
    public FileNotLoadedException() {
        super("No file data. Creating data file and starting new task list...");
    }
}
