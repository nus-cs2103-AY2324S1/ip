package main;

import javafx.application.Application;
import javafx.stage.Stage;

import components.Storage;
import components.Ui;
import components.DukeException;
import components.Parser;
import components.GuiManager;
import tasks.TaskList;

public class Duke extends Application {
    private final Storage storage;
    private TaskList list;
    private final Ui ui;
    private final Parser parser = new Parser();

    private static final String PARENT_DIR = "./data";
    private static final String FILEPATH = "./data/store.txt";

    public Duke() {
        ui = new Ui();
        storage = new Storage(PARENT_DIR, FILEPATH);
        initialize();
    }

    private void initialize() {
        try {
            storage.loadOrCreateFile();
            list = storage.readData(storage);
        } catch (DukeException e) {
            ui.showError(e);
        }
    }

    @Override
    public void start(Stage stage) {
        GuiManager guiManager = new GuiManager(stage, ui, parser, list, storage);
        guiManager.initializeGui();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
