import java.util.ArrayList;

public class Duke {
    private final UI ui = new UI();
    private final Actions actionList = new Actions();
    private final Parser parser = new Parser();
    private final Save savior = new Save();

    public Duke() {
        ArrayList<Task> loadedTasks = savior.loadTasks();
        actionList.add(loadedTasks);
    }

    public void initiateChatbot() throws DukeException{
        ui.openingMessage();
        String input = ui.readInput();
        Command command = parser.issueCommand(input);
        while (!command.exit()) {
            try {
                command.executeCommand(ui, actionList);
            } catch (DukeException ohno) {
                ui.lineSandwich(ohno.getMessage());
            }
            input = ui.readInput();
            command = parser.issueCommand(input);
        }
        savior.saveTasks(actionList.list());
    }
    public static void main(String[] args) {
        Duke Whelmed = new Duke();
        try {
            Whelmed.initiateChatbot();
        } catch (DukeException duked) {
            System.out.println("An error occurred: " + duked.getMessage());
        }
    }
}