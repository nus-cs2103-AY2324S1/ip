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

    @Override
    public String writeString() {
        if (this.getMarkStatus()) {
            return "E,0," + this.getName() + "," + this.start + "," + this.end + "\n";
        } else  {
            return "E,1," + this.getName() + "," + this.start + "," + this.end + "\n";
        }
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
     * @throws DukeException
     */
    public static boolean isEvent(String input) throws DukeException {
        if(input.split( " ")[0].equals("event")) {
            if (input.split(" ").length == 1) {
                throw new DukeException("OOPS! The description of event cannot be empty");
            } else if (input.indexOf("/from ") == -1){
                throw new DukeException("OOPS! The description of event does not contain /from");
            } else if (input.indexOf("/to ") == -1){
                throw new DukeException("OOPS! The description of event does not contain /to");
            } else {
                return true;
            }
        } else {
            return false;
        }
    }
}