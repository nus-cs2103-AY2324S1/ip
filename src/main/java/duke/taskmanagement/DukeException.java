package duke.taskmanagement;
public class DukeException {
    protected String typeOfTask;

    public DukeException(String str){
        this.typeOfTask = str;
    }

    public String toString() {
        return " ☹ OOPS!!! The description of a " + typeOfTask + " cannot be empty.";
    }

    public String errorMessage(String str) {
        return " ☹ OOPS!!!" + str;
    }

    public String nothing(){
        return "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    }
}
