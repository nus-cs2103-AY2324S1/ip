package duke;

import java.util.Scanner;
//import java.io.FileWriter;
//import java.io.File;
//import java.io.IOException;
//import java.io.FileReader;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;

/**
 * The Duke class is a Personal Assistant Chatbot that
 * helps a person to keep track of various things
 */
public class Duke {
    public Storage storage;
    public TaskList taskList;
    public Ui ui;

    /**
     * Initializes a new instance of the Duke class.
     * This constructor sets up the initial state of Duke.
     */
    public Duke(String fileDir) {
        ui = new Ui();
        storage = new Storage(fileDir);
        String[] actions = storage.loadActions();
        boolean[] isDone = storage.loadIsDone();
        String[] types = storage.loadTypes();
        int counter = storage.load("./data/duke.txt", actions, types, isDone);

        if (actions != null && isDone != null && types != null && counter >= 0) {
            taskList = new TaskList(actions, isDone, types, counter);
        } else {
            ui.showLoadingError();
            String[] actions2 = new String[100];
            boolean[] isDone2 = new boolean[100];
            String[] types2 = new String[100];
            taskList = new TaskList(actions2, isDone2, types2, 0);

        }
    }

    /**
     * The run method starts Duke's interaction with user.
     * run() serves as an entry point for duke's interaction.
     */
    public void run() {
        ui.showWelcome();
        boolean exit = false;
        Scanner scanner = new Scanner(System.in);

        while (exit != true) {
            String input = scanner.nextLine();
            exit = Parser.inputType(input, taskList, storage);
        }
        Storage.save("./data/duke.txt", TaskList.actions, TaskList.type, TaskList.isDone, TaskList.dueString, TaskList.startTime, TaskList.endTime, TaskList.counter);

    }

    public static void main(String[] args) {
        new Duke("./data/duke.txt").run();

    }
}