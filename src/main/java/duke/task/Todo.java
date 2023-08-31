package duke.task;
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public Todo(String description, String status) {
        super(description);
        if(status.contains("Y")){
            super.taskStatusFromFile(true);
        } else {
            super.taskStatusFromFile(false);
        }
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toFileString(){
        return "T" + super.toFileString() ;
    }
}
