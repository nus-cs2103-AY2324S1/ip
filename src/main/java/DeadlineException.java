public class DeadlineException extends Exception{
    public DeadlineException() {
        super();
    }

    @Override
    public String toString(){
        return "☹ This is not a valid Deadline input";
    }
}
