package duke.taskmanagement;
public class ToDo extends Task{

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString(){
        return "[T]" + super.toString();
    }

    public String convertIsDone() {
        return super.isDone ? "1" : "0";
    }

    public String saveToFileString() {
        return "T | " +  convertIsDone() + " | " + description + "\n";
    }
}
