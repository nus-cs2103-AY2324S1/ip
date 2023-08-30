package com.mimi.commands;

import com.mimi.main.ReadWriteData;
import com.mimi.main.Storage;
import com.mimi.main.Ui;
import com.mimi.tasks.Todo;

public class TodoCommand extends Command {
    String taskTodo;
    Storage storage;
    ReadWriteData readWriteData;
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
