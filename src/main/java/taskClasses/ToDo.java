package taskClasses;

import taskClasses.Task;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description, "T");
        this.addedTaskDescription();
    }
    @Override
    public String getDetails(){
        return "";
    }

    @Override
    public String getDBString() {
        return String.format("%s | %s | %s",
                "T",this.isDone() ? "1" : "0",
                this.description);
    }
}
