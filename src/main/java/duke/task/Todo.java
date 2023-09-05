package duke.task;

/**
 * Todo Class initiates the todo event and stores its description
 */
public class Todo extends Task {
    /**
     * @param name name of the Todo list
     * @throws DukeException when there is no description after the todo String
     */
    public Todo(String name) throws DukeException {
        super(" " + name);
        super.setType("Todo");
        super.setOgName(name);
        if (name.isEmpty()) {
            throw new DukeException(" No Description given!");
        }
    }

    /**
     * @return String format of the Todo class
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
