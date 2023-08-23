public class ToDoException extends Exception{
    public ToDoException() {
        super();
    }

    @Override
    public String toString(){
        return "☹ This is not a valid Todo input";
    }

}
