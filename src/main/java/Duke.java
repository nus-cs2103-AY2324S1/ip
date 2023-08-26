import duke.ChatManager;
import duke.exception.DukeException;
import duke.message.ErrorMessage;
import duke.Storage;
import duke.task.TaskList;

import java.io.IOException;
import java.util.Scanner;

public class Duke {
    private TaskList tasks;
    private final Storage storage;
    private final ChatManager chatManager;
    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.loadFile());
        } catch (IOException e) {
            System.out.println("new file created");
            this.tasks = new TaskList();
        }
        this.chatManager = new ChatManager(this.tasks);
    }
    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
    private void run() {
        Scanner sc = new Scanner(System.in);
        while (this.chatManager.getIsActive()) {
            String userInput = sc.nextLine();
            try {
                this.chatManager.handleInput(userInput);
            } catch (DukeException e) {
                new ErrorMessage(e.getMessage()).send();
            }
        }
        try {
            this.storage.writeToFile(this.tasks);
        } catch (IOException e) {
            System.out.println("Unable to save data");
        }
    }
}
