package com.mimi.commands;

import com.mimi.main.ReadWriteData;
import com.mimi.main.Storage;
import com.mimi.main.Ui;
import com.mimi.tasks.Todo;

/**
 * Representation of the todo Command.
 */
public class TodoCommand extends Command {
    private String taskTodo;
    private Storage storage;
    private ReadWriteData readWriteData;

    /**
     * Creates an instance of the Todo Command.
     * @param taskTodo String representation of the task to be done.
     * @param storage an instance of Storage.
     * @param readWriteData an instance of ReadWriteData.
     */
    public TodoCommand(String taskTodo, Storage storage, ReadWriteData readWriteData) {
        this.taskTodo = taskTodo;
        this.storage = storage;
        this.readWriteData = readWriteData;
    }

    /**
     * Executes the given command.
     */
    @Override
    public void execute() {
        Todo todo = new Todo(taskTodo);

        storage.add(todo);
        readWriteData.write(todo);

    }

    /**
     * Calls the ui to give the appropriate response based on the type of command.
     * @param ui Ui class instance
     */
    @Override
    public void uiResponse(Ui ui) {
        ui.separator();
    }
}
