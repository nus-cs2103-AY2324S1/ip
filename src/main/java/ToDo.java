public class ToDo extends Task{

    private String TaskIcon = "[T]";

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String getTaskAsString() {
        String message = String.format("%s[%s] %s", this.TaskIcon,this.getStatusIcon(), this.getDescription());
        return message;
    }
}
