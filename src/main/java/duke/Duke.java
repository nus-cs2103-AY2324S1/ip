package duke;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.data.TaskList;
import duke.data.exception.DukeException;
import duke.data.task.Task;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.Ui;
import javafx.fxml.FXML;


public class Duke {
    private Storage storage;
    private TaskList tasklist;
    private Ui ui;
    private Parser parser;

    public Duke() {
        ui = new Ui();
        storage = new Storage("data/duke.txt");
        parser = new Parser();
        try {
            tasklist = new TaskList(storage.loadTasks());
        } catch (DukeException e) {
            tasklist = new TaskList();
        }
    }

    public String getResponse(String input) {
        this.ui.greet();

        String keyword = parser.getInstruction(input);
        try {
            switch (keyword) {
            case "bye": {
                return this.ui.exitBot();
            }
            case "list": {
                return this.ui.printList(tasklist.getTaskList());
            }
            case "unmark": {
                Task task = this.tasklist.unmarkTask(parser.getTaskNumber(input));
                this.storage.rewriteFile(this.tasklist.getTaskList());
                return this.ui.printUnmarkedMessage(task);
            }
            case "mark": {
                Task task = this.tasklist.markTask(parser.getTaskNumber(input));
                this.storage.rewriteFile(this.tasklist.getTaskList());
                return this.ui.printMarkedMessage(task);
            }
            case "delete": {
                Task task = this.tasklist.deleteTask(parser.getTaskNumber(input));
                this.storage.rewriteFile(this.tasklist.getTaskList());
                return this.ui.printDeletedMessage(task, this.tasklist.getTaskList().size());
            }
            case "todo": {
                Task task = this.tasklist.addToDo(parser.getTodoDescription(input));
                this.storage.appendToFile(task.saveString());
                return this.ui.printAddedToListMessage(task, this.tasklist.getTaskList().size());
            }
            case "deadline": {
                Task task = this.tasklist.addDeadline(parser.getDeadlineDescription(input), parser.getBy(input));
                this.storage.appendToFile(task.saveString());
                return this.ui.printAddedToListMessage(task, this.tasklist.getTaskList().size());
            }
            case "event": {
                Task task = this.tasklist.addEvent(parser.getEventDescription(input), parser.getFrom(input),
                        parser.getTo(input));
                this.storage.appendToFile(task.saveString());
                return this.ui.printAddedToListMessage(task, this.tasklist.getTaskList().size());
            }
            case "find": {
                String description = parser.getDescription(input);
                ArrayList<Task> res = tasklist.searchTasks(description);
                return ui.printSearchList(res);
            }
            default: {
                return ui.printBotErrorMsg();
            }
            }
        } catch (DukeException e) {
            return ui.printBotMessage(e.toString());
        } catch (IOException e) {
            return "Something went wrong: " + e.getMessage();
        }
    }
}
