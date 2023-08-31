package duke;

import duke.command.Command;

import duke.exception.DukeException;

import duke.tasklist.TaskList;



/**
 * Chatbot named YONG that responds to user input using CLI
 */
public class Yong {

    public static void main(String[] args) {
        Yong.run();
    }

    public static void run() {
        UI ui = new UI();
        ui.showWelcome();
        TaskList taskList = new TaskList();
        Storage storage = new Storage(taskList);
        storage.readFile();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Parser parser = new Parser(taskList);
                Command c = parser.parse(fullCommand);
                c.execute();
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
        storage.saveFile();
    }
}
