public class EventException extends Exception{

    public EventException() {
        super();
    }

    @Override
    public String toString(){
        return "☹ This is not a valid event input";
    }
}
