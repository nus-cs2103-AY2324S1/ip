package duke;

import java.util.List;
import java.util.Scanner;

import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * The backbone of the program.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for Duke.
     */
    public Duke() {
        this.storage = new Storage();
        this.tasks = new TaskList();
        this.ui = new Ui();
    }

    /**
     * Constructor for Duke with specified filePath.
     * @param filePath
     */
    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        this.tasks = new TaskList();
        this.ui = new Ui();
    }

    /**
     * Runs the program.
     */
    public void run() {
        try {
            tasks = storage.read();
        } catch (DukeException e) {
            ui.printError(e.toString());
        }

        Scanner input = new Scanner(System.in);
        ui.hello();
        String response = "";

        while (input.hasNextLine()) {
            response = input.nextLine();
            List<String> queryList = Parser.convertToList(response);
            if (queryList.get(0).equals("bye")) {
                break;
            }
            try {
                switch (queryList.get(0)) {
                case "list":
                    ui.list(tasks);
                    break;
                case "date":
                    if (queryList.size() < 2) {
                        throw new DukeException("Please specify date with the following format: YYYY-MM-DD");
                    }
                    ui.date(tasks, queryList.get(1));
                    break;
                case "mark":
                    try {
                        int index = Integer.parseInt(queryList.get(1)) - 1;
                        ui.mark(tasks, index);
                    } catch (NumberFormatException e) {
                        throw new DukeException(e);
                    }
                    break;
                case "unmark":
                    try {
                        int index = Integer.parseInt(queryList.get(1)) - 1;
                        ui.unmark(tasks, index);
                    } catch (NumberFormatException e) {
                        throw new DukeException(e);
                    }
                    break;
                case "delete":
                    try {
                        int index = Integer.parseInt(queryList.get(1)) - 1;
                        ui.deleteTask(tasks, index);
                    } catch (NumberFormatException e) {
                        throw new DukeException(e);
                    }
                    break;
                case "deadline":
                    ui.addTask(tasks, Parser.parseUserDeadline(queryList));
                    break;
                case "event":
                    ui.addTask(tasks, Parser.parseUserEvent(queryList));
                    break;
                case "todo":
                    ui.addTask(tasks, Parser.parseUserToDo(queryList));
                    break;
                case "find":
                    String keyword = Parser.parseUserFind(queryList);
                    ui.find(tasks, keyword);
                    break;
                default:
                    throw new DukeException("I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                ui.printError(e.toString());
            }
        }

        try {
            storage.write(tasks);
        } catch (DukeException e) {
            ui.printError(e.toString());
        }

        ui.bye();
        input.close();
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
