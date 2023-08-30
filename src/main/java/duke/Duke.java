package duke;

import duke.storage.Storage;

/**
 * Duke is entry point of our program
 */
public class Duke {
    public static final String LISTPATH = "./data/list.txt";

    private Storage storage;

    public Duke(String filepath) {
        this.storage = new Storage();
    }


    /**
     * Start duke program
     */
    public void run() {
        Greeting.greet();
        Commands.run(this.storage);
    }


    public static void main(String[] args) {

        new Duke(LISTPATH).run();
    }
}
