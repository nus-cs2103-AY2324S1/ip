package duke;

import java.util.ArrayList;

/**
 * Todo in the task list.
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    /**
     * Prints out new Todo in the task list.
     *
     * @param userOutput User command.
     * @param inputList  List of tasks.
     * @throws EmptyException If command is invalid.
     */
    public static void setTodo(String userOutput, TaskList inputList) throws EmptyException {
        try {
            Todo newTodo = new Todo(userOutput.split("todo")[1].strip());
            inputList.add(newTodo);
            System.out.println("Got it. I've added this task:");
            System.out.println(newTodo);
            System.out.println("Now you have " + inputList.size() + " tasks in the list.");
        } catch (Exception e) {
            throw new EmptyException("todo");
        }
    }

    /**
     * Updates the storage with new Todo.
     *
     * @param text  Todo from the storage.
     * @param tasks Task list stored in the storage.
     */
    public static void newTodo(String text, ArrayList<Task> tasks) {
        Todo updatedTodo = new Todo(text);
        tasks.add(updatedTodo);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
