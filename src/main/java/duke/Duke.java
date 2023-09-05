package duke;

import duke.task.TaskList;

/**
 * Represents the Duke chatbot.
 */
public class Duke {
    private UI ui;
    private TaskList list;
    private Storage storage;

    /**
     * Constructor for Duke.
     */
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
                case DEADLINEBY:
                    ui.displayTasks(list.getDeadlineDateTasks(Parser.parseOptions(line)));
                    break;
                case EVENTFROM:
                    ui.displayTasks(list.getEventStartDateTasks(Parser.parseOptions(line)));
                    break;
                case EVENTTO:
                    ui.displayTasks(list.getEventEndDateTasks(Parser.parseOptions(line)));
                    break;
                case FIND:
                    ui.displayTasks(list.findTasks(Parser.parseOptions(line)));
                    break;
                case BYE:
                    storage.saveTasksToFile(list);
                    ui.exit();
                    return;
                default:
                    break;
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
