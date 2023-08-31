package instructionstuff;

import duke.DukeException;

import taskstuff.Task;
import taskstuff.TaskList;

import storagestuff.Storage;

import userstuff.Ui;

/**
 * An abstract class for all instructions.
 * Serves as parent class for all instructions.
 */
public abstract class Instruction {

    /**
     * Executes the instruction it represents.
     */
    public abstract void execute(Storage storage, TaskList taskList, Ui ui) throws DukeException;


    /**
     * A class which represents instructions for adding task to list.
     */
    public static class Add extends Instruction {

        /**
         * The task to add.
         */
        private Task task;

        /**
         * Initialises the class fields.
         *
         * @storage Unused here.
         * @param task The task to add.
         */
        public Add(Task task) {
            this.task = task;
        }

        /**
         * Adds the given task to the given list.
         *
         * @param taskList The taskList to add in.
         */
        @Override
        public void execute(Storage storage, TaskList taskList, Ui ui) throws DukeException {
            taskList.addTask(this.task);
            ui.showMessage("Got it. I've added this task:");
            ui.showMessage(" " + this.task);
            ui.showMessage("Now you have " + taskList.getSize() + " tasks in the list.");
        }

    }

    /**
     * A class to represent the marking of task action.
     *
     */
    public static class Mark extends Instruction {

        /** The index of task to mark. */
        private int index;

        /**
         * Initialises the class fields.
         *
         * @param index The index of task.
         */
        public Mark(int index) {
            this.index = index;
        }

        /**
         * Marks the task at given index in given taskList.
         *
         * @param storage Unused here.
         * @param taskList The taskList to mark in.
         */
        @Override
        public void execute(Storage storage, TaskList taskList, Ui ui) throws DukeException {
            taskList.markTask(this.index);
            ui.showMessage("Nice! I've marked this task as done:");
            ui.showMessage(" " + taskList.getTask(index));
        }
    }


    /**
     * A class to represent the unmarking of task action.
     *
     */
    public static class Unmark extends Instruction {

        /**
         * The index of task to unmark.
         */
        private int index;

        /**
         * Initialises the class fields.
         *
         * @param index    The index of task.
         */
        public Unmark(int index) {
            this.index = index;
        }

        /**
         * Unmarks the task at given index in given taskList.
         *
         * @param storage Unused here.
         * @param taskList The taskList to unmark in.
         */
        @Override
        public void execute(Storage storage, TaskList taskList, Ui ui) throws DukeException {
            taskList.unmarkTask(this.index);
            ui.showMessage("Ok. I have marked this task as not done yet:");
            ui.showMessage(" " + taskList.getTask(index));
        }
    }
    /**
     * A class to represent the deleting of task action.
     *
     */
    public static class Delete extends Instruction {

        /**
         * The index of task to delete.
         */
        private int index;

        /**
         * Initialises the class fields.
         *
         * @param index    The index of task.
         */
        public Delete(int index) {
            this.index = index;
        }

        /**
         * Deletes the task at given index in given taskList.
         *
         * @param storage Unused here.
         * @param taskList The taskList to delete from.
         */
        @Override
        public void execute(Storage storage, TaskList taskList, Ui ui) throws DukeException {
            String task = taskList.deleteTask(this.index);
            ui.showMessage("Noted. I have removed this task from the list:");
            ui.showMessage(" " + task);
            ui.showMessage("Now you have " + taskList.getSize() + " tasks in the list.");
        }
    }


    /**
     * A class which represents the list instruction.
     */
    public static class List extends Instruction {

        /**
         * Lists the tasks in the given taskList.
         * @param storage Unused here.
         * @param taskList The taskList containing tasks to list.
         */
        @Override
        public void execute(Storage storage, TaskList taskList, Ui ui) throws DukeException {
            ui.showMessage("Here are the tasks in your list:");
            String[] s = taskList.getTasks();
            for (int i = 0; i < s.length; i++) {
                String t = (i + 1) + ". " + s[i];
                ui.showMessage(t);
            }
        }
    }

    /**
     * A special class to indicate bye instruction.
     */
    public static class Exit extends Instruction {

        /**
         * Stores the tasks in taskList in storage.
         *
         * @param storage The storage to use to store.
         * @param taskList The taskList containing tasks to store.
         */
        @Override
        public void execute(Storage storage, TaskList taskList, Ui ui) throws DukeException {
            String[] s = taskList.getTasks();
            storage.store(s);
            ui.showBye();
        }
    }
}
