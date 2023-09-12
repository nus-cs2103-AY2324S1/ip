package anto;

import java.util.ArrayList;

/**
 * Anto class represents a command line chatbot named Anto.
 *
 * @author Toh Pin Ren
 */
public class Anto {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Creates an Anto chatbot with default filePath.
     */
    public Anto() {
        String filePath = System.getProperty("user.dir") + "/data/anto.txt";

        ui = new Ui();
        storage = new Storage(ui, filePath);
        try {
            tasks = new TaskList(storage.loadSave(), storage);
            ui.setTaskList(tasks);
        } catch (AntoException e) {
            ui.printError(e);
            tasks = new TaskList(new ArrayList<>(), storage);
            ui.setTaskList(tasks);
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

    /**
     * Returns response based on input given by user.
     *
     * @param input User input.
     * @return String with response from Chatbot.
     */
    public String getResponse(String input) {
        assert this.parser != null;
        return this.parser.readInput(input);
    }

    /**
     * Checks if Anto task list has no tasks.
     *
     * @return Whether task list has no tasks.
     */
    public Boolean hasNoTasks() {
        assert this.tasks != null;
        return this.tasks.hasNoTasks();
    }

    /**
     * Returns Anto's TaskList.
     *
     * @return TaskList for Anto.
     */
    public TaskList getTaskList() {
        return this.tasks;
    }

    /**
     * Returns Anto's Ui.
     *
     * @return Ui for Anto.
     */
    public Ui getUi() {
        return this.ui;
    }
}
