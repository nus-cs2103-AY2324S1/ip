public class ToDo extends Task {

    public ToDo(String description, int id) {
        super(description, id);
    }

    @Override
    public String toString() {
        String status = "[" + getStatusIcon() + "] ";
        return String.valueOf(this.id) + ". " + "[T]" + status + this.description;
    }


}
