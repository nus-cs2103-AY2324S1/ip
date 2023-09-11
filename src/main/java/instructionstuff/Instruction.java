package instructionstuff;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import duke.DukeException;
import storagestuff.Storage;
import taskstuff.Task;
import taskstuff.TaskList;
import userstuff.MainWindow;



/**
 * An abstract class for all instructions.
 * Serves as parent class for all instructions.
 */
public abstract class Instruction {

    /**
     * Executes the instruction it represents.
     *
     * @param storage The Storage instance to store data in if needed.
     * @param taskList The taskList instance to execute instructions on.
     * @param mainWindow The MainWindow instance to use to display messages.
     * @throws DukeException If an error occurred during execution.
     */
    public abstract void execute(Storage storage, TaskList taskList, MainWindow mainWindow) throws DukeException;


    /**
     * Reduces the given stream to String.
     *
     * @param s The stream to reduce.
     * @return Returns a string which contains all the strings in the stream.
     */
    public static String reduceStreamToString(Stream<String> s) {
        Object[] a = s.toArray();
        return IntStream.range(0, a.length).mapToObj(x -> String.format("%d) %s\n", x + 1, a[x]))
                .reduce((x, y) -> x + y).orElse("");
    }

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
         * @param task The task to add.
         */
        public Add(Task task) {
            this.task = task;
        }

        /**
         * Adds the given task to the given list.
         *
         * @param storage The Storage instance to store data in if needed.
         * @param taskList The taskList instance to add the task in.
         * @param mainWindow The MainWindow instance to use to display messages.
         * @throws DukeException If an error occurred during execution.
         */
        @Override
        public void execute(Storage storage, TaskList taskList, MainWindow mainWindow) throws DukeException {
            taskList.addTask(this.task);
            String s = "Got it. I've added this task:\n " + this.task + "\nNow you have "
                    + taskList.getSize() + " tasks in the list.";

            mainWindow.setMessage(s);
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
         * @param storage The Storage instance to store data in if needed.
         * @param taskList The taskList instance to mark the task in.
         * @param mainWindow The MainWindow instance to use to display messages.
         * @throws DukeException If an error occurred during execution.
         */
        @Override
        public void execute(Storage storage, TaskList taskList, MainWindow mainWindow) throws DukeException {
            taskList.markTask(this.index);
            assert index >= 1 && index <= taskList.getSize();
            String s = "Nice! I've marked this task as done:\n " + taskList.getTask(index);
            mainWindow.setMessage(s);
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
         * @param index The index of task to unmark.
         */
        public Unmark(int index) {
            this.index = index;
        }

        /**
         * Unmarks the task at given index in given taskList.
         *
         * @param storage The Storage instance to store data in if needed.
         * @param taskList The taskList instance to unmark task in.
         * @param mainWindow The MainWindow instance to use to display messages.
         * @throws DukeException If an error occurred during execution.
         */
        @Override
        public void execute(Storage storage, TaskList taskList, MainWindow mainWindow) throws DukeException {
            taskList.unmarkTask(this.index);
            assert index >= 1 && index <= taskList.getSize();
            String s = "Nice! I've marked this task as not done:\n " + taskList.getTask(index);
            mainWindow.setMessage(s);
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
         * @param index The index of task to delete.
         */
        public Delete(int index) {
            this.index = index;
        }

        /**
         * Deletes the task at given index in given taskList.
         *
         * @param storage The Storage instance to store data in if needed.
         * @param taskList The taskList instance to delete task from.
         * @param mainWindow The MainWindow instance to use to display messages.
         * @throws DukeException If an error occurred during execution.
         */
        @Override
        public void execute(Storage storage, TaskList taskList, MainWindow mainWindow) throws DukeException {
            String task = taskList.deleteTask(this.index);
            assert index >= 1 && index <= taskList.getSize() + 1;
            String s = "Noted. I've removed this task from the list\n " + task;
            s = s + "\nNow you have " + taskList.getSize() + " tasks in the list.";
            mainWindow.setMessage(s);
        }
    }


    /**
     * A class which represents the list instruction.
     */
    public static class List extends Instruction {

        /**
         * Lists the tasks in the given taskList.
         *
         * @param storage The Storage instance to store data in if needed.
         * @param taskList The taskList instance list tasks from.
         * @param mainWindow The MainWindow instance to use to display messages.
         * @throws DukeException If an error occurred during execution.
         */
        @Override
        public void execute(Storage storage, TaskList taskList, MainWindow mainWindow) throws DukeException {
            String s = Instruction.reduceStreamToString(taskList.getTasks());
            mainWindow.setMessage(s);
        }
    }

    /**
     * A class which represents the find instruction.
     */
    public static class Find extends Instruction {

        /** The keyword to find. */
        private String keyWord;

        /**
         * Initialises the keyword in this class.
         *
         * @param keyWord The keyword to find in tasks.
         */
        public Find(String keyWord) {
            this.keyWord = keyWord;
        }


        /**
         * Finds the tasks in the given taskList.
         * @param storage Unused here.
         * @param taskList The taskList containing tasks to find.
         * @param mainWindow The MainWindow instance to use to display message.
         */
        @Override
        public void execute(Storage storage, TaskList taskList, MainWindow mainWindow) throws DukeException {
            String s = Instruction.reduceStreamToString(taskList.findTasks(this.keyWord));
            mainWindow.setMessage(s);
        }
    }

    /**
     * A special class to indicate bye instruction.
     */
    public static class Exit extends Instruction {

        /**
         * Stores the tasks in taskList in storage.
         *
         * @param storage The Storage instance to store data in.
         * @param taskList The taskList instance which holds the data to store in storage.
         * @param mainWindow The MainWindow instance to use to display messages.
         * @throws DukeException If an error occurred during storing data.
         */
        @Override
        public void execute(Storage storage, TaskList taskList, MainWindow mainWindow) throws DukeException {
            storage.store(reduceStreamToString(taskList.getTasks()));
            mainWindow.setMessage("Bye. Hope to see you again soon.");
        }
    }
}
