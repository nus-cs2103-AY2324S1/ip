package duke.command;

import duke.*;

import java.io.IOException;

public class TodoCommand extends Command {
    private String description;

    public TodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        if (description.isEmpty()) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }

        Todo newTodo = new Todo(description);
        tasks.add(newTodo);

        try {
            storage.saveTasks(tasks); // Save the updated tasks to file
        } catch (IOException e) {
            ui.showSavingError(e.getMessage());
        }

        ui.showAddedTask(newTodo, tasks.size());
    }
}
