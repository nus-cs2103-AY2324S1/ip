package didier;

import didier.command.Command;
import didier.exception.DidierException;

public class Didier {

    private Storage storage;
    private TaskList taskList;
    private UI ui;

    public Didier(String directoryPath, String fileName) {
        ui = new UI();
        storage = new Storage(directoryPath, fileName);
        taskList = storage.getTasks();
    }

    public void run() {
        this.ui.botGreet();
        boolean isExit = false;
        while (!isExit) {
            // Carry out the action determined by the didier.command
            try {
                String commandString = this.ui.readCommand();
                Command command = Parser.parse(commandString);
                command.execute(this.taskList, this.ui, this.storage);
                isExit = command.isExit();
            } catch (DidierException exception) {
                this.ui.botPrintError(exception);
            } finally {
                if (!isExit) {
                    this.ui.botEndCommand(taskList.getSize());
                }
            }
        }
        this.ui.botGoodBye();
    }


    public static void main(String[] args) {
        Didier didier = new Didier("data/", "didier.txt");
        didier.run();
    }



}
