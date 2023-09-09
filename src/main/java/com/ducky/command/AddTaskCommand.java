package com.ducky.command;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import com.ducky.logic.Storage;
import com.ducky.logic.TaskList;
import com.ducky.logic.DuckyException;
import com.ducky.task.DeadlineTask;
import com.ducky.task.EventTask;
import com.ducky.task.TaskType;
import com.ducky.task.TodoTask;
import com.ducky.util.Parser;

/**
 * Represents a command that adds a task to Ducky's task list.
 */
public class AddTaskCommand extends Command {

    private final TaskType type;
    private final String[] args;

    /**
     * Constructs a command that adds the specified type of task with additional arguments.
     * @param type Type of task.
     * @param args Arguments specific to the given type of task.
     */
    public AddTaskCommand(TaskType type, String... args) {
        this.type = type;
        this.args = args;
    }

    /**
     * Adds the task to Ducky's task list, saves the state to file system,
     * then returns status of changes.
     *
     * @param taskList TaskList of Ducky chatbot instance.
     * @param storage  Storage module of Ducky chatbot instance.
     * @return String confirming the operation and the new task.
     * @throws DuckyException If exceptions specific to Ducky are raised.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws DuckyException {
        switch (this.type) {
        case TODO:
            TodoTask newTodo = new TodoTask(this.args[0]);
            taskList.addTask(newTodo);
            storage.save(taskList);
            return String.format("%s\n%s\n%s\n",
                    "Okay! I've added this task:",
                    newTodo,
                    taskList.getListLengthStatus());
        case DEADLINE:
            LocalDate deadline;
            try {
                deadline = Parser.parseDate(this.args[1]);
            } catch (DateTimeParseException e) {
                throw new DuckyInvalidCommandFormatException(
                        "Your deadline should be in yyyy-mm-dd format."
                );
            }
            DeadlineTask newDeadline = new DeadlineTask(this.args[0], deadline);
            taskList.addTask(newDeadline);
            storage.save(taskList);
            return String.format("%s\n%s\n%s\n",
                    "Okay! I've added this task:",
                    newDeadline,
                    taskList.getListLengthStatus());
        case EVENT:
            EventTask newEvent = new EventTask(this.args[0], this.args[1], this.args[2]);
            taskList.addTask(newEvent);
            storage.save(taskList);
            return String.format("%s\n%s\n%s\n",
                    "Okay! I've added this task:",
                    newEvent,
                    taskList.getListLengthStatus());
        default:
            return "Failed to add task.";
        }
    }
}
