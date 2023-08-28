import java.util.ArrayList;

public class ToDo extends Task {
    String description;
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    public String toStringFile() {
        return "T | " + super.toStringFile();
    }

    public static void addTodo(String description, ArrayList<Task> list) throws DukeException{
        if (description.isBlank()) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        ToDo newTask = new ToDo(description);
        list.add(newTask);
        System.out.println(line);
        System.out.println("Okay! I added a new TODO:\n\t" + newTask.toString());
        System.out.println("Now you have " + list.size() + " tasks in the list.");
        System.out.println(line);

    }

    public static void addSavedTodo(String description, ArrayList<Task> list, String isMarked) {
        ToDo newTask = new ToDo(description);
        newTask.markFromRead(isMarked);
        list.add(newTask);
    }

}
