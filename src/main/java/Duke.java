public class Duke {
    public static void main(String[] args) {

        Ui ui = new Ui();

        Storage storage = new Storage(ui);

        Parser parser = new Parser(ui);

        TaskList list;

        // Read from file
        try {
            list = new TaskList(storage.readFile());
        } catch (Exception e) {
            ui.showError("Error reading from file.");
            list = new TaskList();
        }

        ui.startMessage();

        Command command = new InvalidCommand();
        while (command.type != Command.CommandType.BYE) {
            command = parser.parseCommand(ui.commandPrompt());
            command.execute(list, ui);
        }

        // Write to file
        try {
            storage.writeFile(list);
        } catch (Exception e) {
            ui.showError("Error writing to file.");
        }
    }
}
