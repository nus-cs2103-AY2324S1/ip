import java.io.IOException;

public abstract class Command {

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public static class Exit extends Command {
        @Override
        public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
            try {
                storage.saveTasksToFile(tasks);
                ui.showGoodbye();
            } catch (IOException e) {
                throw new DukeException("Error saving tasks to file: " + e.getMessage());
            }
        }
    }

    public static class List extends Command {
        @Override
        public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
            tasks.list();
        }
    }

    public static class Mark extends Command {
        private int taskNumber;

        public Mark(int taskNumber) {
            this.taskNumber = taskNumber;
        }

        @Override
        public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
            int index = taskNumber - 1;
            tasks.markTaskAsDone(index);
        }
    }

    public static class Unmark extends Command {
        private int taskNumber;

        public Unmark(int taskNumber) {
            this.taskNumber = taskNumber;
        }

        @Override
        public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
            int index = taskNumber - 1;
            tasks.unmarkTask(index);
        }
    }

    public static class Delete extends Command {
        private int taskNumber;

        public Delete(int taskNumber) {
            this.taskNumber = taskNumber;
        }

        @Override
        public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
            int index = taskNumber - 1;
            tasks.deleteTask(index);
        }
    }

    public static class Add extends Command {
        private Task task;

        public Add(Task task) {
            this.task = task;
        }

        @Override
        public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
            tasks.addTask(task);
        }
    }

    public static class Invalid extends Command {
        private String message;

        public Invalid(String message) {
            this.message = message;
        }

        @Override
        public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
            throw new DukeException(message);
        }
    }
}
