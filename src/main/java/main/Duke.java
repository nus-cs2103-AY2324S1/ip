package main;

import javafx.fxml.FXML;
import parser.Parser;
import tasks.Task;
import ui.Ui;
import actions.TaskList;
import storage.Storage;
import gui.DialogBox;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Region;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private Ui ui;
    private Storage storage;
    private TaskList tasklist;
    public ArrayList<Task> taskArrayList = new ArrayList<>();
    private Parser parser;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));



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
     * Runs Chadbot which includes created and initialising all directories,
     * files and objects
     */
    public void run() {
        ui.chadGreet();
        storage.makeNewDirectory();
        storage.makeNewFile();
        storage.loadFile(taskArrayList);

        Scanner scanObj = new Scanner(System.in);
        boolean check = true;

        while (check) {
            String input = scanObj.nextLine();
            String[] inputArray = input.split(" ", 2);
            check = parser.inputParse(inputArray);
        }
        scanObj.close();
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

    public void initialiseFile(){
        storage.makeNewDirectory();
        storage.makeNewFile();
        storage.loadFile(taskArrayList);
    }



    public static void main (String[]args) {
        Duke chad = new Duke();
        chad.run();
    }

}
