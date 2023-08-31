package minion.commands;

import minion.storage.Storage;
import minion.data.TaskList;
import minion.data.task.ToDo;
import minion.ui.Ui;

import java.io.IOException;

/**
 * Represents a todo command.
 */
public class ToDoCommand extends Command {
    public static final String COMMAND_WORD = "todo";
    private final ToDo toDo;

    /**
     * Constructs a todo command.
     * @param toDo Todo of the command.
     */
    public ToDoCommand(ToDo toDo) {
        this.toDo = toDo;
    }

    /**
     * Executes the todo command.
     * @param tasks Task list.
     * @param ui Ui of chatbot.
     * @param storage Storage of chatbot.
     * @throws IOException if there is IO error.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        tasks.add(toDo);
        ui.print(
            "Got it. I've added this task:",
            "\t" + toDo.toString(),
            "Now you have " + tasks.size() +  " tasks in the list."
        );
        storage.writeToFile(tasks);
    }

    /**
     * Checks whether this object equals the other object.
     * @param o other object.
     * @return whether this object equals the other object.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof ToDoCommand)) {
            return false;
        }
        ToDoCommand c = (ToDoCommand) o;
        return this.toDo.equals(c.toDo);
    }
}
