package duke;

import java.io.File;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.LocalDate;
import duke.command.*;


public class Richie {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Richie(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (RichieException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String userInput = ui.scanUserInput();
                Command command = Parser.parse(userInput);
                command.execute(this.ui, this.storage, this.tasks);
                if (command instanceof ExitCommand) {
                    isExit = true;
                }

            } catch (RichieException e) {
                ui.showMessage(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Richie("src/data.txt").run();
    }
}

