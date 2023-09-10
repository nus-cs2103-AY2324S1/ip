public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        String status = "[" + getStatusIcon() + "] ";
        return " " + "[T]" + status + this.description;
    }


}
