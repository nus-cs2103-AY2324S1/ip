package duke.taskmanagement;

public class DukeException {
    protected String typeOfTask;

    /**
     * Create new duke exception to handle
     * the exception when running Duke.
     * @param str Indicate the type of task when handling this exception.
     */
    public DukeException(String str){
        this.typeOfTask = str;
    }

    /**
     * Return the String that shows the error
     * message, which is when empty description
     * is entered.
     * @return The intended error message.
     */
    public String toString() {
        return " ☹ OOPS!!! The description of a " + typeOfTask + " cannot be empty.";
    }

    /**
     * Return the String that shows the error
     * message, a more general method to
     * generate error message.
     * @param str
     * @return the error messsage.
     */
    public String errorMessage(String str) {
        return " ☹ OOPS!!!" + str;
    }

    /**
     * Return the String that indicates
     * an invalid input.
     * @return the error message.
     */
    public String nothing(){
        return "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    }
}
