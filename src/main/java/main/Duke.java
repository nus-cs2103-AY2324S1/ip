package main;

import parser.Parser;
import tasks.Task;
import ui.Ui;
import actions.TaskList;
import storage.Storage;


import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private Ui ui;
    private Storage storage;
    private TaskList tasklist;
    public ArrayList<Task> taskArrayList = new ArrayList<>();
    private Parser parser;


    public Duke() { // had to change to public for launcher
        this.ui = new Ui();
        this.storage = new Storage();
        this.tasklist = new TaskList(taskArrayList);
        this.parser = new Parser(this, taskArrayList);
    }


    public static class DukeException extends Exception {
        public DukeException(String message) {
            super(message);
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {

        String[] inputArray = input.split(" ", 2);
        String output = parser.displayInputParse(inputArray);

        return "Chad:\n" + output;
    }

    /**
     * Intialises the file by loading the txt file
     */
    public void initialiseFile(){
        storage.makeNewDirectory();
        storage.makeNewFile();
        storage.loadFile(taskArrayList);
    }


    public static void main (String[]args) {

    }

}
