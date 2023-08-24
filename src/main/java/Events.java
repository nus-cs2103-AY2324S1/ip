/**
 * class for events
 */
public class Events extends Task{
    /**
     * For the start
     */
    private String start;
    /**
     * For the end
     */
    private String end;

    /**
     * The constructor
     * @param name the name of the event task
     * @param start The starting time
     * @param end The ending time
     */
    public Events (String name, String start, String end) {
        super(name);
        this.start = start;
        this.end = end;
    }

    /**
     * To convert the task to string
     * @return a string
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + start + "to: " + end + ")";
    }

    /**
     * To check whether the input is a Deadlines
     * @param input the task
     * @return Boolean
     * @throws TodoEmptyNameException
     */
    public static boolean isEvent(String input) throws EventEmptyNameException, EventFromNotFoundException, EventToNotFoundException {
        if(input.split( " ")[0].equals("event")) {
            if (input.split(" ").length == 1) {
                throw new EventEmptyNameException();
            } else if (input.indexOf("/from ") == -1){
                throw new EventFromNotFoundException();
            } else if (input.indexOf("/to ") == -1){
                throw new EventToNotFoundException();
            } else {
                return true;
            }
        } else {
            return false;
        }
    }
}