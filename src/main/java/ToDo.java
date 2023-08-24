public class ToDo extends Task{

    public ToDo(String description) {
        super(description, "T");
        this.addedTaskDescription();
    }
    @Override
    public String getDetails(){
        return "";
    }
}
