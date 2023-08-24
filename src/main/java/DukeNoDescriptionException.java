public class DukeNoDescriptionException extends Exception{
    public DukeNoDescriptionException(String msg){
        super("☹ OOPS!!! The description of a " + msg + " cannot be empty."
            +"\n"
            + "____________________________________________");
    }
}
