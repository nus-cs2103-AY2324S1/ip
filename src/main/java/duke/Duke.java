package duke;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    static final String FILEPATH = "./data/duke.txt";
    static boolean allowNext = true;
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String path) {
        this.ui = new Ui();
        this.storage = new Storage(path);
        try {
            tasks = new TaskList(storage.loadTaskList());
        } catch (DukeException e) {
            ui.printError(e);
            tasks = new TaskList();
        } catch (FileNotFoundException e) {
            tasks = new TaskList();
        }
    }

    public void run() throws DukeException, IOException {
        ui.printGreeting();
        storage.createDataFile();

        while (allowNext) {
            try {
                String input = ui.getInput();
                String command = Parser.parseCommand(input);
                String info;

                switch (command.toUpperCase()) {
                case "BYE":
                    handleExit();
                    storage.update(tasks);
                    break;
                case "LIST":
                    handleList();
                    break;
                case "MARK":
                    info = Parser.parseInfo(input);
                    handleMarking(info);
                    storage.update(tasks);
                    break;
                case "UNMARK":
                    info = Parser.parseInfo(input);
                    handleUnmarking(info);
                    storage.update(tasks);
                    break;
                case "DELETE":
                    info = Parser.parseInfo(input);
                    tasks.handleDelete(info);
                    storage.update(tasks);
                    break;
                case "TODO":
                    info = Parser.parseInfo(input);
                    tasks.addTodo(info);
                    storage.update(tasks);
                    break;
                case "EVENT":
                    info = Parser.parseInfo(input);
                    tasks.addEvent(info);
                    storage.update(tasks);
                    break;
                case "DEADLINE":
                    info = Parser.parseInfo(input);
                    tasks.addDeadline(info);
                    storage.update(tasks);
                    break;
                default:
                    throw new DukeInvalidCommandException(command);
                }
            } catch (Exception e) {
                ui.printError(e);
            }
        }
    }

    public void handleExit() {
        ui.printExit();
        allowNext = false;
    }

    public void handleList() {
        ui.printList(tasks);
    }

    public void handleMarking(String info) throws DukeException {
        int index;

        try {
            index = Integer.parseInt(info);
            Task t = tasks.getTask(index - 1);
            t.markAsDone();
            ui.printMark(t);
        } catch (NumberFormatException nfe) {
            throw new DukeInvalidTaskNumberException(info);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeInvalidTaskNumberException(info);
        }
    }
    public void handleUnmarking(String info) throws DukeException {
        int index;

        try {
            index = Integer.parseInt(info);
            Task t = tasks.getTask(index - 1);
            t.markAsUndone();
            ui.printUnmark(t);
        } catch (NumberFormatException nfe) {
            throw new DukeInvalidTaskNumberException(info);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeInvalidTaskNumberException(info);
        }
    }

    public static void main(String[] args) throws DukeException, IOException {
        new Duke(FILEPATH).run();
    }
}
