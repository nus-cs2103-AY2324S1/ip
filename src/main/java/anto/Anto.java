package anto;

/**
 * Anto class represents a command line chatbot named Anto.
 *
 * @author Toh Pin Ren
 */
public class Anto {

    private Storage storage;
    private TaskList tasks;
    Ui ui;
    private Parser parser;

    /**
     * Creates an Anto chat bot with default filePath.
     */
    public Anto() {
        String filePath = "data/anto.txt";
        ui = new Ui();
        storage = new Storage(ui, filePath);
        try {
            tasks = new TaskList(storage.loadSave(), storage);
            ui.setTaskList(tasks);
        } catch (AntoException e) {
            ui.printError(e);
        }
        parser = new Parser(ui, tasks);
    }

    /**
     * Creates an Anto chatbot with the specified filePath.
     *
     * @param filePath The relative file path to the location of anto.txt file.
     */
    public Anto(String filePath) {
        ui = new Ui();
        storage = new Storage(ui, filePath);
        try {
            tasks = new TaskList(storage.loadSave(), storage);
            ui.setTaskList(tasks);
        } catch (AntoException e) {
            ui.printError(e);
        }
    }

//    /**
//     * Runs the Anto chatbot and continuously reads inputs.
//     */
//    public void run() {
//        parser = new Parser(ui, tasks);
//    }

    /**
     * Main method to run program.
     *
     * @param args String input.
     */
    public static void main(String[] args) {
//        new Anto("data/anto.txt").run();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {
        return this.parser.readInput(input);
    }
}
