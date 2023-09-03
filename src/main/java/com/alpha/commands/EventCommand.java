package com.alpha.commands;

import com.alpha.exceptions.InvalidTaskException.InvalidEventException;
import com.alpha.storage.Storage;
import com.alpha.tasks.Event;
import com.alpha.tasks.Task;
import com.alpha.tasks.TaskList;
import com.alpha.ui.Ui;
import com.alpha.utils.Parser;

/**
 * The type Event command.
 */
public class EventCommand extends Command {

    /**
     * Instantiates a new Event command.
     *
     * @param args The arguments of the Command.
     */
    public EventCommand(String args) {
        super(args);
    }

    /**
     * Executes the commands.
     *
     * @param taskList   Task list of the application.
     * @param ui      Ui of the application.
     * @param storage Storage functionality of the application.
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            Task task = new Event(Parser.getEventName(getArgs()), Parser.getEventStart(getArgs()),
                    Parser.getEventEnd(getArgs()));
            taskList.addTask(task);
            return ui.addTask(task, taskList);
        } catch (InvalidEventException e) {
            return e.getMessage();
        }
    }
}
