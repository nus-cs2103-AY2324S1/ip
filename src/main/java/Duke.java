public class Duke {
    private UI ui;
    private TaskList list;
    private Storage storage;

    public Duke() {
        this.ui = new UI();
        this.storage = new Storage();
        try {
            this.list = new TaskList(storage.loadFile());
        } catch (DukeException e) {
            ui.displayException(e);
            this.list = new TaskList();
        }
    }

    private void run() {
        ui.greet();
        performCommands();
    }

    private void performCommands() {
        while (ui.hasInput()) {
            String line = ui.readInput();
            try {
                CommandType command = Parser.parseCommand(line);
                switch (command) {
                case LIST:
                    ui.displayList(list);
                    break;
                case MARK:
                    ui.displayDoneTask(list.markDone(Parser.parseOptions(line)));
                    break;
                case UNMARK:
                    ui.displayNotDoneTask(list.unmarkDone(Parser.parseOptions(line)));
                    break;
                case TODO:
                    ui.displayAddToList(list.addTodoToList(Parser.parseOptions(line)), list.getSize());
                    break;
                case DEADLINE:
                    ui.displayAddToList(list.addDeadlineToList(Parser.parseOptions(line)), list.getSize());
                    break;
                case EVENT:
                    ui.displayAddToList(list.addEventToList(Parser.parseOptions(line)), list.getSize());
                    break;
                case DELETE:
                    ui.displayRemoveFromList(list.deleteFromList(Parser.parseOptions(line)), list.getSize());
                    break;
                case BYE:
                    storage.saveCommandsToFile(list);
                    ui.exit();
                    return;
                }
            } catch (DukeException e) {
                ui.displayException(e);
            }
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
