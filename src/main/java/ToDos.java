public class ToDos extends Task {
    public ToDos(String description) throws DukeException {
        super(description);
        if (description == null || description.trim().isEmpty()) {
            throw new DukeException("OOPS! The description of ToDo cannot be empty!");
        }
    }
    @Override
    public String getType(){
        return "T";
    }
}
