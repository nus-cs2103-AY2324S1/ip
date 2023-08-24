public class ToDo extends Task{

    public ToDo(String name) throws DukeException {
        super(name);
        if (name.trim().isEmpty()) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
