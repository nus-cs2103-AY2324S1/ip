package chatty.utils;

import chatty.command.Command;
import chatty.exception.ChattyException;
import chatty.task.TaskList;

import java.io.IOException;

public class Duke {

    private Ui ui;
    private TaskList taskList;
    private Storage storage;

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

    public void startChatting() {
        ui.showGreet();
        boolean isExit = false;

        while (!isExit) {
            try {
                String originalInput = ui.getInput();
                Command command = Parser.parse(originalInput);
                command.execute(taskList, ui, storage);
                isExit = command.isExit();
            } catch (ChattyException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            String msg = command.execute(this.taskList, this.ui, this.storage);
            return "Chatty: \n" + msg;
        } catch (ChattyException e) {
            return e.getMessage();
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").startChatting();
    }
}
