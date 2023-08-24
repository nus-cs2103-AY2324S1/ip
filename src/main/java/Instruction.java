/**
 * An abstract class for all instructions.
 * Serves as parent class for all instructions.
 */
public abstract class Instruction {

    /**
     * Executes the instruction it represents.
     */
    public abstract void execute();


    /**
     * A class which represents instructions for adding task to list.
     */
    public static class Add extends Instruction {

        /**
         * The task to add.
         */
        private Task task;

        /**
         * The list to add the task in.
         */
        private TaskList taskList;

        /**
         * Initialises the class fields.
         *
         * @param task The task to add.
         * @param taskList The list to add in.
         */
        public Add(Task task, TaskList taskList) {
            this.task = task;
            this.taskList = taskList;
        }

        /**
         * Adds the given task to the given list.
         *
         */
        @Override
        public void execute() {
            this.taskList.addTask(this.task);
        }

    }

    /**
     * A class to represt the marking of task action.
     *
     */
    public static class Mark extends Instruction {

        /** The index of task to mark. */
        private int index;

        /** The tasklist to mark in. */
        private TaskList taskList;

        /**
         * Initialises the class fields.
         *
         * @param index The index of task.
         * @param taskList The taskList to mark in.
         */
        public Mark(int index, TaskList taskList) {
            this.index = index;
            this.taskList = taskList;
        }

        /**
         * Marks the task at given index in given taskList.
         *
         */
        @Override
        public void execute() {
            this.taskList.markTask(this.index);
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
         * The tasklist to unmark in.
         */
        private TaskList taskList;

        /**
         * Initialises the class fields.
         *
         * @param index    The index of task.
         * @param taskList The taskList to mark in.
         */
        public Unmark(int index, TaskList taskList) {
            this.index = index;
            this.taskList = taskList;
        }

        /**
         * unmarks the task at given index in given taskList.
         */
        @Override
        public void execute() {
            this.taskList.unmarkTask(this.index);
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
         * The tasklist to delete from.
         */
        private TaskList taskList;

        /**
         * Initialises the class fields.
         *
         * @param index    The index of task.
         * @param taskList The taskList to delete from.
         */
        public Delete(int index, TaskList taskList) {
            this.index = index;
            this.taskList = taskList;
        }

        /**
         * deletes the task at given index in given taskList.
         */
        @Override
        public void execute() {
            this.taskList.deleteTask(this.index);
        }
    }


    /**
     * A class which represents the list instruction.
     */
    public static class List extends Instruction {

        /**
         * The taskList to list.
         */
        private TaskList taskList;

        /**
         * Initialises the class fields.
         *
         * @param taskList The taskList to list.
         */
        public List(TaskList taskList) {
            this.taskList = taskList;
        }

        /**
         * Lists the tasks in the given taskList.
         */
        @Override
        public void execute() {
            this.taskList.listTasks();
        }
    }

    /**
     * A special class to indicate bye instruction.
     */
    public static class Exit extends Instruction {

        /**
         * Execute does nothing.
         */
        @Override
        public void execute() {

        }
    }
}
