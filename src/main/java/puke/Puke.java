package puke;

import java.io.File;
import java.io.IOException;

import puke.command.Command;
import puke.command.ErrorCommand;
import puke.managers.DataHandler;
import puke.managers.Parser;
import puke.managers.PukeException;
import puke.managers.TaskList;

/**
 * A chatbot that uses overly complicated sentences.
 */
public class Puke {
    /**
     * List of tasks stored by the chatbot
     */
    private TaskList tasks;

    /**
     * Constructor for the chatbot
     * @throws IOException when an error occurs with the file reader.
     */
    public Puke() throws IOException {
        try {
            tasks = new TaskList(DataHandler.loadDatabase());
        } catch (PukeException e) {
            new File("ListData.txt").createNewFile();
            tasks = new TaskList();
        }
    }

    /*
     * Runs the program
     *
    public void run() {
        ui.startup();
        boolean isExit = false;
        while (!isExit) {
            String command = ui.command();
            String input = ui.input();
            ui.line();
            Command next;
            try {
                next = Parser.parse(command, input);
            } catch (PukeException e) {
                next = new ErrorCommand();
            }
            next.execute(tasks);
            isExit = next.isExit();
        }
    }

    public static void main(String[] args) throws IOException {
        new Puke().run();
    }
*/
    public String getResponse(String input) {
        Command next;
        String[] inputComponents = input.split(" ", 2);
        String command = inputComponents[0];
        String rest;
        try {
            rest = inputComponents[1];
        } catch (Exception e) {
            rest = "";
        }
        try {
            next = Parser.parse(command, rest);
        } catch (Exception e) {
            next = new ErrorCommand();
        }
        return next.execute(tasks);
    }
}
