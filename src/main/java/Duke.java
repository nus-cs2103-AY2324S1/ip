/**
 * Duke is a simple task management program that allows users to add, delete, mark, and list tasks.
 */
public class Duke {
    private static ToDoList toDoList;

    /**
     * Greets the user with a welcome message.
     */
    public static void greet() {
        Ui.greet();
    }

    /**
     * Displays a farewell message when exiting the program.
     */
    public static void exit() {
        Ui.exit();
    }

    /**
     * Adds a task to the ToDoList and displays a confirmation message.
     *
     * @param task The task to be added.
     */
    public static void add(Task task) {
        toDoList.add(task);
        Ui.add(task, toDoList.size());
    }

    /**
     * Deletes a task at the specified index from the ToDoList and displays a confirmation message.
     *
     * @param index The index of the task to be deleted.
     * @throws IndexOutOfBoundsException If the index is out of range.
     */
    public static void delete(int index) {
        final Task task = toDoList.get(index);
        toDoList.delete(index);
        Ui.delete(task, toDoList.size());
    }

    /**
     * Lists all tasks in the ToDoList and displays them.
     */
    public static void list() {
        System.out.println(toDoList);
    }

    /**
     * Marks a task at the specified index as done and displays a confirmation message.
     *
     * @param index The index of the task to be marked as done.
     * @throws IndexOutOfBoundsException If the index is out of range.
     */
    public static void mark(int index) {
        toDoList.mark(index);
        Ui.mark(toDoList.get(index));
    }

    /**
     * Marks a task at the specified index as not done yet and displays a confirmation message.
     *
     * @param index The index of the task to be marked as not done yet.
     * @throws IndexOutOfBoundsException If the index is out of range.
     */
    public static void unmark(int index) {
        toDoList.unmark(index);
        Ui.unmark(toDoList.get(index));
    }

    /**
     * Main method to run the Duke program.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        final String DATA_DIRECTORY = "../../../data";
        String projectRoot = System.getProperty("user.dir");
        String dataFilePath = projectRoot + "/" + DATA_DIRECTORY + "/tasks.ser";
        toDoList = new ToDoList(dataFilePath);

        Parser.start();
    }
}
