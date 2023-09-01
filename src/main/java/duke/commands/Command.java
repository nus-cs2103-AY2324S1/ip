package duke.commands;

import duke.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;
import duke.tasks.Task;
import duke.tasks.TaskList;

import java.io.IOException;

public abstract class Command {

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public abstract CommandType getType();

    public static class ExitCommand extends Command {
        @Override
        public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
            try {
                storage.saveTasksToFile(tasks);
                ui.showGoodbye();
            } catch (IOException e) {
                throw new DukeException("Error saving duke.tasks to file: " + e.getMessage());
            }
        }

        @Override
        public CommandType getType() {
            return CommandType.BYE;
        }
    }

    public static class ListCommand extends Command {
        @Override
        public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
            tasks.list();
        }

        @Override
        public CommandType getType() {
            return CommandType.LIST;
        }
    }

    public static class MarkCommand extends Command {
        private int taskNumber;

        public MarkCommand(int taskNumber) {
            this.taskNumber = taskNumber;
        }

        @Override
        public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
            int index = taskNumber - 1;
            tasks.markTaskAsDone(index);
        }

        @Override
        public CommandType getType() {
            return CommandType.MARK;
        }
    }

    public static class UnmarkCommand extends Command {
        private int taskNumber;

        public UnmarkCommand(int taskNumber) {
            this.taskNumber = taskNumber;
        }

        @Override
        public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
            int index = taskNumber - 1;
            tasks.unmarkTask(index);
        }

        @Override
        public CommandType getType() {
            return CommandType.UNMARK;
        }
    }

    public static class DeleteCommand extends Command {
        private int taskNumber;

        public DeleteCommand(int taskNumber) {
            this.taskNumber = taskNumber;
        }

        @Override
        public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
            int index = taskNumber - 1;
            tasks.deleteTask(index);
        }

        @Override
        public CommandType getType() {
            return CommandType.DELETE;
        }
    }

    public static class AddCommand extends Command {
        private Task task;
        private CommandType commandType;

        public AddCommand(Task task, CommandType commandType) {
            this.task = task;
            this.commandType = commandType;
        }

        @Override
        public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
            tasks.addTask(task);
        }

        @Override
        public CommandType getType() {
            return this.commandType;

        }
    }

    public static class InvalidCommand extends Command {
        private String message;

        public InvalidCommand(String message) {
            this.message = message;
        }

        @Override
        public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
            throw new DukeException(message);
        }

        @Override
        public CommandType getType() {
            return CommandType.INVALID;
        }
    }
}
