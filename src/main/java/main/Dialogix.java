package main;

import command.Command;
import exception.DialogixException;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Dialogix {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private boolean isExit = false;
    private static final String filePath = "/data/dialogix.txt";

    Dialogix() {
        storage = new Storage(filePath);
        ui = new Ui();

        try {
            tasks = new TaskList(storage.load());
        } catch (DialogixException e) {
            System.out.println(e.getMessage());
            tasks = new TaskList();
        }
    }

    String getResponse(String input) {
        if (isExit) {
            return "";
        }

        try {
            ui.resetOutput();
            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);
            isExit = c.isExit();
            return ui.getOutput();
        } catch (DialogixException e) {
            return e.getMessage();
        }
    }
}
