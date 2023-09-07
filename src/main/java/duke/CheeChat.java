package duke;

import duke.command.Command;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;


public class CheeChat {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private String filePath = "data/duke.txt";

    public CheeChat() {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    protected String getResponse(String input) {
        String output = "";
        try {
            Command c = Parser.parse(input);
            output += c.execute(tasks, ui, storage);
        } catch (Exception e) {
            output = e.getMessage();
        }
        return output;
    }
}

//
//
//    public static void main(String[] args) {
//        new CheeChat().run();
//    }


//    public void run() {
//        ui.showWelcome();
//        boolean isExit = false;
//        while (!isExit) {
//            try {
//                String fullCommand = ui.readCommand();
//                ui.showLine(); // show the divider line ("_______")
//                Command c = Parser.parse(fullCommand);
//                c.execute(tasks, ui, storage);
//                isExit = c.isExit();
//            } catch (Exception e) {
//                ui.showError(e.getMessage());
//            } finally {
//                ui.showLine();
//            }
//        }
//    }
//}


