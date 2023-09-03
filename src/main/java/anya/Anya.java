package anya;

import java.io.IOException;

import anya.parser.Parser;
import anya.storage.Storage;
import anya.storage.Storage.InvalidStorageFilePathException;
import anya.storage.Storage.StorageOperationException;
import anya.task.TaskList;
import anya.ui.Ui;

/**
 * Represents a chatbot named anya.
 */
public class Anya {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private Parser parser;

    public Anya(String storageFilePath) {
        try {
            this.ui = new Ui();
            this.storage = new Storage(storageFilePath);
            tasks = storage.load();
            this.parser = new Parser(storage, tasks);
        } catch (InvalidStorageFilePathException | StorageOperationException e) {
            ui.showInitError();
            throw new RuntimeException(e);
        }
    }

    public void run() throws IOException {
        ui.showGreetingMessage();
        parser.parse();
        ui.showExitMessage();
    }

    public static void main(String[] args) throws IOException {
        new Anya("data/tasks.txt").run();
    }
}
