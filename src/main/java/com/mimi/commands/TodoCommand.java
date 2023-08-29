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
    @Override
    public void execute() {
        Todo todo = new Todo(taskTodo);

        storage.add(todo);
        readWriteData.write(todo);

    }

    @Override
    public void uiResponse(Ui ui) {
        ui.separator();
    }
}
