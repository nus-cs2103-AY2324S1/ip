package com.mimi.main;

import java.io.File;
import java.io.IOException;

import com.mimi.commands.Command;
import com.mimi.ui.Ui;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * The main class of the Mimi program.
 * @author Yuheng
 */
public class Mimi extends Application implements DataCallback {

    private Storage storage;
    private Ui ui;
    private ReadWriteData readWriteData;
    private Stage window;

    /**
     * Default constructor.
     */
    public Mimi() {

    }

    /**
     * Runs the program.
     */
    public void run() throws MimiException {
        this.readWriteData.initialise();

        this.ui.initialise();

        this.storage.remind();
    }


    /**
     * Receives data from the Ui and processes it.
     * @param input the string that the user enters.
     */
    @Override
    public void onDataReceived(String input) {
        Parser parser = new Parser(input, this.storage, this.readWriteData);

        Command c = parser.parse();

        assert c != null : "Command is null";

        c.execute();
        c.uiResponse(this.ui);


        if (c.isExit()) {
            closeWindow();
        }
    }

    private void closeWindow() {
        try {
            PauseTransition delay = new PauseTransition(Duration.seconds(3));
            delay.setOnFinished(event -> this.window.close());
            delay.play();
        } catch (IllegalStateException e) {
            ui.unableToClose();
        }
    }



    /**
     * Starts the program.
     *
     * @param stage the primary stage for this application, onto which the application scene can be set.
     */
    @Override
    public void start(Stage stage) {
        String directory = "./data/";
        String dataPath = "./data/Mimi.txt";
        this.window = stage;

        this.ui = new Ui(stage, this);
        this.storage = new Storage(this.ui);

        File directoryFile = new File(directory);
        File dataFile = new File(dataPath);

        try {
            //checks if the directory and the file are created. If not, create it.
            if (directoryFile.mkdir()) { }

            if (dataFile.createNewFile()) { }

            this.readWriteData = new ReadWriteData(dataFile, this.storage, this.ui);
        } catch (IOException e) {
            this.ui.showLoadingError();
        }

        try {
            run();
        } catch (MimiException e) {
            ui.showLoadingError();
        }
    }
}
