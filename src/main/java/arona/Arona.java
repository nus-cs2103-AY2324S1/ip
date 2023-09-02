package arona;

import arona.commands.*;
import arona.parser.Parser;
import arona.storage.Storage;
import arona.task.TaskList;
import arona.ui.Ui;

import java.util.Scanner;
public class Arona {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Arona(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage);
    }

    public void run() {
        ui.showWelcomeMessage();

        Scanner scanner = new Scanner(System.in);
        String userInput;

        while (true) {
            userInput = scanner.nextLine().toLowerCase().trim();

            if (userInput.isEmpty()) {
                continue;
            }

            String[] inputTokens = Parser.parseUserInput(userInput);
            String command = Parser.getCommand(inputTokens);

            if (command.equals("bye")) {
                if (inputTokens.length == 1) {
                    break;
                }
            }
            try {
                switch (command) {
                case "list":
                    ListCommand listCommand = new ListCommand(tasks, ui);
                    listCommand.execute();
                    break;
                case "unmark":
                    UnMarkCommand unmarkCommand = new UnMarkCommand(tasks, ui, storage, Parser.getTaskIndex(inputTokens));
                    unmarkCommand.execute();
                    break;
                case "mark":
                    MarkCommand markCommand = new MarkCommand(tasks, ui, storage, Parser.getTaskIndex(inputTokens));
                    markCommand.execute();
                    break;
                case "todo":
                    ToDoCommand toDoCommand = new ToDoCommand(tasks, ui, storage, Parser.getToDoDescription(inputTokens));
                    toDoCommand.execute();
                    break;
                case "deadline":
                    DeadlineCommand deadlineCommand = new DeadlineCommand(tasks, ui, storage, Parser.getDeadlineDescription(inputTokens));
                    deadlineCommand.execute();
                    break;
                case "event":
                    EventCommand eventCommand = new EventCommand(tasks, ui, storage, Parser.getEventDescription(inputTokens));
                    eventCommand.execute();
                    break;
                case "delete":
                    DeleteCommand deleteCommand = new DeleteCommand(tasks, ui, storage, Parser.getTaskIndex(inputTokens));
                    deleteCommand.execute();
                    break;
                default:
                    ui.showInvalidArgumentMessage();
                    break;
                }
            } catch (Exception e) {
                ui.showErrorMessage(e);
            }
        }
        ui.showGoodbyeMessage();
    }

    public static void main(String[] args) {
        new Arona("src/main/java/arona/data/arona.txt").run();
    }
}

