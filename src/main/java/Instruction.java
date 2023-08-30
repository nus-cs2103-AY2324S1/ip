/**
 * An abstract class for all instructions.
 * Serves as parent class for all instructions.
 */
public abstract class Instruction {

    /**
     * Executes the instruction it represents.
     */
    public abstract void execute(Storage storage, TaskList taskList) throws DukeException;


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
        public void execute(Storage storage, TaskList taskList) throws DukeException {
            taskList.addTask(this.task);
            System.out.println("\tGot it. I've added this task:");
            System.out.println("\t " + this.task);
            System.out.println("\tNow you have " + taskList.getSize() + " tasks in the list.");
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
        public void execute(Storage storage, TaskList taskList) throws DukeException {
            taskList.markTask(this.index);
            System.out.println("\tNice! I've marked this task as done:");
            System.out.println("\t  " + taskList.getTask(index));
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
        public void execute(Storage storage, TaskList taskList) throws DukeException {
            taskList.unmarkTask(this.index);
            System.out.println("\tOk. I have marked this task as not done yet:");
            System.out.println("\t  " + taskList.getTask(index));
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
        public void execute(Storage storage, TaskList taskList) throws DukeException {
            String task = taskList.deleteTask(this.index);
            System.out.println("\tNoted. I have removed this task from the list:");
            System.out.println("\t  " + task);
            System.out.println("\tNow you have " + taskList.getSize() + " tasks in the list.");
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
        public void execute(Storage storage, TaskList taskList) throws DukeException {
            System.out.println("\tHere are the tasks in your list:");
            System.out.print(taskList.listTasks());
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
        public void execute(Storage storage, TaskList taskList) throws DukeException {
            String[] s = new String[taskList.getSize()];
            for (int i = 1; i <= taskList.getSize(); i++) {
                s[i - 1] = taskList.getTask(i);

            }
            storage.store(s);
        }
    }
}
