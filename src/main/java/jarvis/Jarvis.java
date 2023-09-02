package jarvis;

import jarvis.commands.Command;

import jarvis.exceptions.JarvisException;
import jarvis.tasks.TaskList;

public class Jarvis {

    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    public Jarvis() {
        taskList = new TaskList();
        ui = new Ui();
        storage = new Storage();
        taskList.setTasks(storage.loadTasks());
    }

    public void start() {
        ui.printIntro();
    }

    public void respond(String userInput){
        try {
            Command command = Parser.parseCommand(userInput);
            command.execute(taskList, ui, storage);
        } catch (JarvisException e) {
            ui.printError(e.getMessage());
        }
    }
}
