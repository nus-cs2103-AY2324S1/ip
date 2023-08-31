import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

import tasks.Task;

public class Duke {

    public static final String VERSION = "OwO Bot ─ a CS2103T iP ─ Week 3 Update";

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke() {
        storage = new Storage();
        ui = new Ui();
    }

    public void run() {

        this.tasks = new TaskList(storage.load());

        ui.printWelcomeMessage(VERSION);

        runCommandLoop();

        storage.save(tasks.getTaskList());

        ui.printExitMessage();

    }
    public static void main(String[] args) {
        new Duke().run();
    }


    public void runCommandLoop() {
        ui.runLoop(tasks);

    }
}
