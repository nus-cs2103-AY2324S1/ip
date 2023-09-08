package sana;

/**
 * Represents a chatBot named Sana.
 */
public class Sana {

    private Storage storage;
    private TaskList tasks;

    /**
     * Constructs a Sana object.
     *
     * @param filePath specifies the path where the file to store tasks is saved.
     */
    public Sana(String filePath) {
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    public Sana() {

    }

    String getResponse(String input) {
        try {
            Command parsedCommand = Parser.parse(input);
            return parsedCommand.execute(tasks, storage);
        } catch (SanaException e) {
            return e.getMessage();
        }

    }

}


