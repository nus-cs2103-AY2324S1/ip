package duke;

import duke.exception.DukeDatabaseNotFoundException;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TaskList taskList;

    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        try {
            this.taskList = new TaskList(storage.loadData());
        } catch (DukeDatabaseNotFoundException e) {
            this.taskList = new TaskList();
        }
    }

    private void greet() {
        Ui.showGreetMessage();
    }

    private void exit() {
        this.storage.saveData(this.taskList);
        Ui.showExitMessage();
    }

    private void list() {
        this.taskList.list();
    }

    private void help() {
        Ui.showHelpMessage();
    }

    private void listen() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                String input = sc.nextLine();
                ArrayList<String> parsedInput = Parser.parseUserInput(input);
                Command command = Parser.parseCommand(input);
                switch (command) {
                    case BYE:
                        this.exit();
                        return;
                    case LIST:
                        this.list();
                        break;
                    case HELP:
                        this.help();
                        break;
                    case MARK:
                        this.taskList.markAsDone(parsedInput);
                        break;
                    case UNMARK:
                        this.taskList.markAsUndone(parsedInput);
                        break;
                    case DELETE:
                        this.taskList.delete(parsedInput);
                        break;
                    case TODO:
                        this.taskList.newTodo(parsedInput);
                        break;
                    case DEADLINE:
                        this.taskList.newDeadline(parsedInput);
                        break;
                    case EVENT:
                        this.taskList.newEvent(parsedInput);
                        break;
                }
            } catch (DukeException e) {
                Ui.showError(e);
            }
        }
    }

    private void run() {
        this.greet();
        this.listen();
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
