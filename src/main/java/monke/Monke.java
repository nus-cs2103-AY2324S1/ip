package monke;

import monke.commands.Command;
import monke.commands.ListCommand;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Monke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Monke(String filepath) {
        this.ui = new Ui();
        this.storage = new Storage(filepath);
        try {
            tasks = new TaskList(storage.load());
        } catch (MonkeException e) {
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.printHorizontalLine();
        ui.showWelcome();
        boolean isExit = false;
//        Scanner sc = new Scanner(System.in);

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
//                String fullCommand = sc.nextLine();
                ui.printHorizontalLine();
                Command c = Parser.parse(fullCommand);
                c.execute(ui, storage, tasks);
                isExit = c.isExit();
            } catch (MonkeException e) {
                ui.print(e.getMessage());
            } finally {
                ui.printHorizontalLine();
            }
        }
//        sc.close();

    }

    public static void main(String[] args) {
        new Monke("./data/tasks.txt").run();
    }
}
