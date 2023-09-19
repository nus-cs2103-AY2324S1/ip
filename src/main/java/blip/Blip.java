package blip;

import blip.ui.BlipUI;
import blip.tasks.TaskList;
import blip.storage.BlipStorage;
import blip.parser.BlipParser;
import blip.exceptions.BlipException;
import blip.commands.Command;


/**
 * Represents the Blip ChatBot.
 */
public class Blip {


    /**
     * User interface for Blip ChatBot.
     */
    private BlipUI ui;

    /**
     * Task list of tasks.
     */
    private TaskList tasks;

    /**
     * Storage for tasks.
     */
    private BlipStorage storage;

    /**
     * Parser for string inputs by user.
     */
    private BlipParser parser;

    /**
     * Constructor of Blip ChatBot.
     *
     * @param filePath The data file path for tasks
     */
    public Blip(String filePath) {
        assert filePath != null : "File path cannot be null";
        this.ui = new BlipUI();
        this.storage = new BlipStorage(filePath);
        this.parser = new BlipParser();
        try {
            this.tasks = storage.loadFile();
        } catch (BlipException e) {
            ui.showLoadingErr();
            this.tasks = new TaskList();
        }
    }


    /**
     * Gets the response from user on the Blip ChatBot GUI
     *
     * @param input The user input on the Blip ChatBot GUI
     * @return String representation of execution of user input
     */
    public String getResponse(String input) {
        assert input != null : "Input cannot be null";
        Command command = parser.parse(input);
        return command.execute(tasks, ui, storage);
    }


    /**
     * Runs the main for Blip ChatBot.
     *
     * @param args CLI Arguments
     */
    public static void main(String[] args) {
        Launcher.main(args);
    }



}
