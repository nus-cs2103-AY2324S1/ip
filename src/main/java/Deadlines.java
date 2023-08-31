/**
 * class for deadline
 */
public class Deadlines extends Task{
    /**
     * The deadline of the task
     */
    private String ddl;

    /**
     * The constructor
     * @param name the name of the deadline task
     * @param ddl the deadline
     */
    public Deadlines (String name, String ddl) {
        super(name);
        this.ddl = ddl;
    }

    @Override
    public String writeString() {
        if (this.getMarkStatus()) {
            return "D,0" + this.getName() + "," + this.ddl + "\n";
        } else  {
            return "D,1," + this.getName() + "," + this.ddl + "\n";
        }
    }

    /**
     * Convert to string
     * @return a string
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: "+ ddl +")";
    }

    /**
     * To check whether the input is a Deadlines
     * @param input the task
     * @return Boolean
     * @throws DukeException
     */
    public static boolean isDeadline(String input) throws DukeException {
        if(input.split( " ")[0].equals("deadline")) {
            if (input.split(" ").length == 1) {
                throw new DukeException("OOPS! The description of deadline cannot be empty");
            } else if (input.indexOf("/by ") == -1){
                throw new DukeException("OOPS! The description of deadline does not contain /by");
            } else {
                return true;
            }
        } else {
            return false;
        }
    }
}