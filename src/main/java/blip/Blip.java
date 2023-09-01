package blip;

import java.util.Scanner;
import blip.ui.*;
import blip.tasks.*;
import blip.storage.*;
import blip.parser.*;
import blip.exceptions.*;
import blip.commands.*;

/**
 * Represents the Blip ChatBot.
 */
public class Blip {
    /**
     * File path for tasks.
     */
    private static String FILE_PATH = "./data/blip.txt";

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
     * @param filePath The data file path for tasks
     */
    public Blip(String filePath) {
        this.ui = new BlipUI();
        this.storage = new BlipStorage(filePath);
        this.parser = new BlipParser();
        try {
            tasks = storage.loadFile();
        } catch (BlipException e) {
            ui.showLoadingErr();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the Blip ChatBot.
     */
    public void run() {
        ui.showIntro();
        Scanner scanIn = new Scanner(System.in);

        while (true) {
            String userInput;
            userInput = scanIn.nextLine();
            Command command = parser.parse(userInput);
            if (command instanceof ByeCommand) {
                command.execute(tasks, ui, storage);
                break;
            }
            command.execute(tasks, ui, storage);
        }

    }

    /**
     * Main for Blip ChatBot where it runs Blip.
     * @param args CLI Arguments
     */
    public static void main(String[] args) {
        new Blip(FILE_PATH).run();
    }



}
