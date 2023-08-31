package duke.task;
import java.util.ArrayList;
import duke.DukeException;
public class ToDo extends Task {
    public String description;
    public ToDo(String description) {
        super(description);
        this.description = description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    public String toStringFile() {
        return "T | " + super.toStringFile();
    }
    @Override
    public String getType() { return "TODO"; }

    public static ToDo addTodo(String description, ArrayList<Task> list) throws DukeException{
        if (description.isBlank()) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        ToDo newTask = new ToDo(description);
        list.add(newTask);
        return newTask;

    }

    public static void addSavedTodo(String description, ArrayList<Task> list, String isMarked) {
        ToDo newTask = new ToDo(description);
        newTask.markFromRead(isMarked);
        list.add(newTask);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ToDo) {
            ToDo toDo = (ToDo) obj;
            return this.description.equals(toDo.description);
        }
        return false;

    }


}
