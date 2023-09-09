package com.alpha.commands;

import java.time.LocalDateTime;

import com.alpha.tasks.Event;
import com.alpha.tasks.Task;
import com.alpha.tasks.TaskList;

/**
 * The type Event command.
 */
public class EventCommand extends Command {

    private final Task task;

    /**
     * Instantiates a new Event command.
     *
     * @param name     The name of the task.
     * @param start    The start datetime as a LocalDateTime object.
     * @param end      The end datetime as a LocalDateTime object.
     * @param taskList The task list.
     */
    public EventCommand(String name, LocalDateTime start, LocalDateTime end, TaskList taskList) {
        super(taskList);
        this.task = new Event(name, start, end);
    }

    @Override
    public String execute() {
        getTaskList().addTask(task);
        return "Got it. I've added this task:\n" + task + "\n"
                + "Now you have " + super.getTaskListSize() + " tasks in the list.\n";
    }
}
