package taskmate.main;

import taskmate.commands.Command;
import taskmate.exceptions.*;
import taskmate.tools.Parser;
import taskmate.tools.Storage;
import taskmate.tools.TaskList;
import taskmate.tools.Ui;

import java.io.IOException;

public class TaskMate {

    static String chatbotName = "TaskMate";
    static String defaultSaveTaskFilePath = "./data/saved_tasks.txt";

    public enum CommandTypes {
        list, bye, todo, deadline, event, mark, unmark, delete, help, find
    }

    private Ui ui;
    private Storage storage;
    private TaskList tasks;
    private Parser parser;

    TaskMate(String filePath) {
        this.ui = new Ui(chatbotName);
        this.storage = new Storage(filePath);
        this.tasks = new TaskList();

        // Load existing tasks from disk
        try {
            String fileContents = storage.readFromFile();
            this.tasks = new TaskList(fileContents);
        } catch (IOException e) {
            ui.printFileNotFoundResponse(storage.getSaveFilePath());
        } catch (NoDataException e) {
            ui.printNoDataResponse();
        }
    }

    TaskMate() {
        this.ui = new Ui(chatbotName);
        this.storage = new Storage(defaultSaveTaskFilePath);
        this.tasks = new TaskList();

        // Load existing tasks from disk
        try {
            String fileContents = storage.readFromFile();
            this.tasks = new TaskList(fileContents);
        } catch (IOException e) {
            ui.printFileNotFoundResponse(storage.getSaveFilePath());
        } catch (NoDataException e) {
            ui.printNoDataResponse();
        }
    }

    public static void main(String[] args) {
        new TaskMate().run();
    }

    public void run() {

        // Greets user
        ui.greetUser();
        ui.promptUser();

        // Reading user input
        String userInput;
        Command command;
        while (true) {
            userInput = ui.nextLine();

            // Parse user input
            try {
                command = Parser.parse(userInput);
                command.execute(tasks, ui, storage);
                if (command.isExit()) {
                    break;
                }
            } catch (InvalidCommandTypeException e) {
                ui.printInvalidCommandTypeExceptionResponse();
                System.out.println(e); // todo: Remove for production code
                continue;
            } catch (InvalidDescriptionException e) {
                ui.printInvalidCommandTypeExceptionResponse(); // todo: Add new message for chatbot
                System.out.println(e); // todo: Remove for production code
                continue;
            } catch (EmptyByException e) {
                ui.printEmptyByExceptionResponse();
                System.out.println(e); // todo: Remove for production code
                continue;
            } catch (InvalidByException e) {
                ui.printInvalidByExceptionResponse();
                System.out.println(e);
                continue;
            } catch (InvalidToException e) {
                ui.printInvalidToExceptionResponse();
                System.out.println(e);
                continue;
            } catch (EmptyToException e) {
                ui.printEmptyToExceptionResponse();
                System.out.println(e);
                continue;
            } catch (InvalidFromException e) {
                ui.printInvalidFromExceptionResponse();
                System.out.println(e);
                continue;
            } catch (EmptyFromException e) {
                ui.printEmptyFromExceptionResponse();
                System.out.println(e);
                continue;
            } catch (NotAnIntegerException e) {
                ui.printNotAnIntegerExceptionResponse();
                System.out.println(e);
                continue;
            }
        }
    }
}