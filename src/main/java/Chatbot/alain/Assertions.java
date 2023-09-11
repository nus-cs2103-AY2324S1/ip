package chatbot.alain;

/**
 * A utility class that provides assertion methods for various tasks
 * to ensure their correct addition or removal from the TaskList.
 *
 * <p>This class is used for debugging purposes during development.</p>
 */
public class Assertions {

    /**
     * Asserts that a new To-do task is present in the given task list.
     *
     * @param list The list of tasks to check.
     * @param task The To-do task to verify its presence.
     * @throws AssertionError If the To-do task is not found in the list.
     */
    public static void assertNewTodo(TaskList list, Task task) {
        assert list.contains(task)
                : "Newly added To-do task should be in the list";
    }

    /**
     * Asserts that a new Deadline task is present in the given task list.
     *
     * @param list The list of tasks to check.
     * @param task The Deadline task to verify its presence.
     * @throws AssertionError If the Deadline task is not found in the list.
     */
    public static void assertNewDeadline(TaskList list, Task task) {
        assert list.contains(task)
                : "Newly added Deadline task should be in the list";
    }

    /**
     * Asserts that a new Event task is present in the given task list.
     *
     * @param list The list of tasks to check.
     * @param task The Event task to verify its presence.
     * @throws AssertionError If the Event task is not found in the list.
     */
    public static void assertNewEvent(TaskList list, Task task) {
        assert list.contains(task)
                : "Newly added Event task should be in the list";
    }

    /**
     * Asserts that a task has been removed from the given task list.
     *
     * @param list The list of tasks to check.
     * @param task The task that should have been removed.
     * @throws AssertionError If the task is still found in the list after it should have been removed.
     */
    public static void assertDelete(TaskList list, Task task) {
        assert !list.contains(task) : "List should not contain the removed task";
    }
}

