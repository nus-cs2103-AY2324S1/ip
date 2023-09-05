package duke;

import java.io.IOException;

import duke.task.TaskList;

/**
 * Represents the Duke chatbot.
 */
public class Duke {
    private final UI ui;
    private TaskList list;
    private final Storage storage;

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

    public String getResponse(String line) {
        try {
            CommandType command = Parser.parseCommand(line);
            switch (command) {
            case LIST:
                return ui.displayList(list);
            case MARK:
                return ui.displayDoneTask(list.markDone(Parser.parseOptions(line)));
            case UNMARK:
                return ui.displayNotDoneTask(list.unmarkDone(Parser.parseOptions(line)));
            case TODO:
                return ui.displayAddToList(list.addTodoToList(Parser.parseOptions(line)), list.getSize());
            case DEADLINE:
                return ui.displayAddToList(list.addDeadlineToList(Parser.parseOptions(line)), list.getSize());
            case EVENT:
                return ui.displayAddToList(list.addEventToList(Parser.parseOptions(line)), list.getSize());
            case DELETE:
                return ui.displayRemoveFromList(list.deleteFromList(Parser.parseOptions(line)), list.getSize());
            case DEADLINEBY:
                return ui.displayTasks(list.getDeadlineDateTasks(Parser.parseOptions(line)));
            case EVENTFROM:
                return ui.displayTasks(list.getEventStartDateTasks(Parser.parseOptions(line)));
            case EVENTTO:
                return ui.displayTasks(list.getEventEndDateTasks(Parser.parseOptions(line)));
            case FIND:
                return ui.displayTasks(list.findTasks(Parser.parseOptions(line)));
            default:
                return "";
            }
        } catch (DukeException e) {
            return ui.displayException(e);
        }
    }

    /**
     * Saves task list to file.
     */
    public void save() throws IOException {
        storage.saveTasksToFile(list);
    }
}
