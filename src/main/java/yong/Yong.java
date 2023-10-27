package yong;

import yong.command.Command;

import yong.command.ExitCommand;
import yong.exception.DukeException;

import yong.tasklist.TaskList;

import java.util.Scanner;


/**
 * Chatbot named YONG that responds to user input using CLI.
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
        assert (this.taskList instanceof TaskList);
    }


    /**
     * Returns String response of chatbot.
     *
     * @param userInput user String input.
     * @return Yong chatbot string response.
     */
    public String getResponse(String userInput) {
        try {
            Parser parser = new Parser(taskList);
            Command c = parser.parse(userInput);
            if (c instanceof ExitCommand) {
                isExit = true;
            }
            String outputString = c.execute();
            assert (!outputString.isEmpty());
            storage.saveFile();
            return outputString;
        } catch (DukeException e) {
            System.out.println(e);
            return e.getMessage();
        }
    }

    public boolean getExit() {
        return isExit;
    }

    /**
     * Run function to run Yong chatbot according to CLI inputs.
     */
    public static void run() {
        Scanner scanner = new Scanner(System.in);
        TaskList taskList = new TaskList();
        Storage storage = new Storage(taskList);
        storage.readFile();
        boolean isExit = false;
        while (!isExit) {
            try {
                Parser parser = new Parser(taskList);
                Command c = parser.parse(scanner.nextLine());
                c.execute();
                isExit = c.isExit();
            } catch (DukeException e) {
                throw e;
            }
        }
        storage.saveFile();
    }
}
