package puke;

import java.io.File;
import java.io.IOException;

import javafx.application.Application;
import puke.command.Command;
import puke.command.ErrorCommand;
import puke.gui.Main;
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
            boolean created = new File("ListData.txt").createNewFile();
            assert created;
            tasks = new TaskList();
        }
    }
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
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
