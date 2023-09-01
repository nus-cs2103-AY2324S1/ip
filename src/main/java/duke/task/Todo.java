package duke.task;

import java.util.ArrayList;

/**
 * The Todo class, which is a type of Task.
 */
public class Todo extends Task {


    /**
     * The constructor of Todo
     * @param description The description of todo.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Read the Task data and add it into ArrayList.
     * @param list An ArrayList of Tasks.
     * @param data Data to be read and stored in list.
     */
    public static void readData(ArrayList<Task> list, String data) {
        String splitTodo[] = data.split(" \\| ");

        Task todoTask = new Todo(splitTodo[2]);
        list.add(todoTask);
        if (splitTodo[1].equals("1")) {
            list.get(list.size()-1).markDoneNoPrint();
        }
    }

    /**
     * Default System.out when this function is called.
     * @return string information in the Todo instance.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}