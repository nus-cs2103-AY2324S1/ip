package chatty.utils;

import java.io.IOException;

import chatty.command.Command;
import chatty.exception.ChattyException;
import chatty.task.TaskList;

/**
 * Main class that will run according to the command given by the user
 */
public class Duke {

    private Ui ui;
    private TaskList taskList;
    private Storage storage;

    /**
     * Empty constructor when GUI is run
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage("data/tasks.txt");
        try {
            this.taskList = new TaskList();
            this.storage.loadTask(this.taskList);
        } catch (IOException e) {
            e.getMessage();
            this.taskList = new TaskList();
        } catch (ChattyException e) {
            e.getMessage();
        }
    }

    /**
     * Constructor that will load the existing file if there is
     * @param filePath the path to the existing file
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            taskList = new TaskList();
            storage.loadTask(taskList);
        } catch (IOException e) {
            e.getMessage();
            taskList = new TaskList();
        } catch (ChattyException e) {
            e.getMessage();
        }
    }

    /**
     * The method that will start the chatbot when called
     */
    public void startChatting() {
        ui.showGreet();
        boolean isExit = false;

        while (!isExit) {
            try {
                String originalInput = ui.getInput();
                assert originalInput != null && !originalInput.isEmpty() : "Input should not be null or empty.";
                Command command = Parser.parse(originalInput);
                command.execute(taskList, ui, storage);
                isExit = command.isExit();
            } catch (ChattyException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Get the chatbot response to be printed on the dialogue box when GUI is called
     * @param input the input from the user
     * @return The response given by the chatbot
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            String msg = command.execute(this.taskList, this.ui, this.storage);
            assert msg != null : "Command execution returned a null message.";
            return "Chatty: \n" + msg;
        } catch (ChattyException e) {
            return e.getMessage();
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").startChatting();
    }
}
