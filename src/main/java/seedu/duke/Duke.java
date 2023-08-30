package seedu.duke;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
            parser = new Parser(storage, tasks, ui);
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.printIntro();

        Scanner sc = new Scanner(System.in);
        // Get user command
        String cmd = sc.nextLine();

        // Parse the cmd as long as it is not "bye"
        while (!cmd.equals("bye")) {
            parser.parse(cmd);
            cmd = sc.nextLine();
        }

        ui.printExit();
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}