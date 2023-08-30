package core;

import Commands.Command;
import Parser.CommandParser;
import Parser.DataParser;
import TaskList.TaskList;
import Ui.Ui;
import Storage.Storage;
import Storage.DataReader;

public class Duke {
    private static Storage storage;
    private static TaskList taskList;
    private static Ui ui;
    private static String filepath;

    public Duke(String filepath) {
        Duke.filepath = filepath;
        ui = new Ui();
        storage = new Storage(filepath);
        taskList = new TaskList();
    }
    public static void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {String fullCommand = ui.readCommand();
                Command c = CommandParser.parse(fullCommand);
                c.execute(taskList, ui, storage);
            } catch (Exception e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
//        Duke.greet();
//        Scanner scanner = new Scanner(System.in);
//        boolean running = true;
//        while (running) {
//            String input = scanner.nextLine();
//            if (input.equals("bye")) {
//                Duke.bye();
//                running = false;
//            } else if (input.equals("list")) {
//                memory.print();
//            } else if (isMarkCommand(input)) {
//                int pos = extractValue(input);
//                if (pos > memory.size() || pos <= 0) {
//                    System.out.println("Invalid index. Please enter again.");
//                    continue;
//                }
//                memory.mark(pos);
//            } else if (isUnmarkCommand(input)) {
//                int pos = extractValue(input);
//                if (pos > memory.size() || pos <= 0) {
//                    System.out.println("Invalid index. Please enter again.");
//                    continue;
//                }
//                memory.unmark(pos);
//            } else if (isDeleteCommand(input)) {
//                int pos = extractValue(input);
//                if (pos > memory.size() || pos <= 0) {
//                    System.out.println("Invalid index. Please enter again.");
//                    continue;
//                }
//                memory.delete(pos);
//            } else {
//                memory.add(parser.parseTask(input));
//            }
//        }
    }
}
