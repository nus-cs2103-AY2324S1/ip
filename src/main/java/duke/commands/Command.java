package duke.commands;

import duke.helper.Parser;
import duke.helper.Storage;
import duke.helper.TaskList;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public abstract class Command {

    public Storage storage;
    public Parser parser;
    public TaskList tasks;

    public Command(Storage storage, Parser parser, TaskList tasks) {
        this.storage = storage;
        this.parser = parser;
        this.tasks = tasks;
    }

    /**
     * Handles commands.
     *
     * @return Blus's response to the user after processing the command.
     */
    public abstract String execute();

    /**
     * Stores the current tasks list to file.
     *
     */
    public void store() {
        storage.saveList(tasks.getUserList(), tasks.getUserListPointer());
    }

    public boolean isValidDateFormat(String date) {
        try {
            LocalDate d = LocalDate.parse(date);

        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }
}
