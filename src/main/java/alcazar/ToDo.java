package alcazar;
public class ToDo extends Task {


    public ToDo(String description) {
        super(description);
    }

    /**
     * Used to evaluate String form of a ToDo task
     * @return String form of a ToDo Task
     */


    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
