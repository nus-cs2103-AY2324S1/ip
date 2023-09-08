package yong;

import yong.command.Command;

import yong.command.ExitCommand;
import yong.exception.DukeException;

import yong.tasklist.TaskList;


/**
 * Chatbot named YONG that responds to user input using CLI
 */
public class Yong {

    private TaskList taskList;
    private Storage storage;

    private boolean isExit = false;

    /**
     * Class for running all the chatbot logic.
     */
    public Yong() {
        this.taskList = new TaskList();
        this.storage = new Storage(taskList);
        storage.readFile();
    }

    //    public static void run() {
    //        UI ui = new UI();
    //        ui.showWelcome();
    //        TaskList taskList = new TaskList();
    //        Storage storage = new Storage(taskList);
    //        storage.readFile();
    //        boolean isExit = false;
    //        while (!isExit) {
    //            try {
    //                String fullCommand = ui.readCommand();
    //                ui.showLine(); // show the divider line ("_______")
    //                Parser parser = new Parser(taskList);
    //                Command c = parser.parse(fullCommand);
    //                c.execute();
    //                storage.saveFile();
    //                isExit = c.isExit();
    //            } catch (DukeException e) {
    //                ui.showError(e.getMessage());
    //            } finally {
    //                ui.showLine();
    //            }
    //        }
    //
    //    }

    /**
     * Returns String response of chatbot
     *
     * @param userInput user String input
     * @return Yong chatbot string response
     */
    public String getResponse(String userInput) {
        try {
            Parser parser = new Parser(taskList);
            Command c = parser.parse(userInput);
            if (c instanceof ExitCommand) {
                isExit = true;
            }
            String outputString = c.execute();
            storage.saveFile();
            return outputString;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    public boolean getExit() {
        return isExit;
    }
}
