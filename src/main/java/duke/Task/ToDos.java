package duke.Task;

import duke.DukeException.DukeException;

public class ToDos extends Task {

    /**
     * The constructor
     * @param name the name of the Duke.Task.ToDos
     */
    public ToDos (String name) {
        super(name);
    }

    /**
     * To convert the Duke.Task.ToDos to the string
     * @return a string
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String writeString() {
        if (this.getMarkStatus()) {
            return "T,0," + this.getName() + "\n";
        } else  {
            return "T,1," + this.getName() + "\n";
        }
    }

    /**
     * To check whether the input is a Todo
     * @param input the task
     * @return Boolean
     * @throws DukeException
     */
    public static boolean isTodo(String input) throws DukeException {
        if(input.split( " ")[0].equals("todo")) {
            if (input.split(" ").length == 1) {
                throw new DukeException("OOPS! The description of todo cannot be empty");
            } else {
                return true;
            }
        } else {
            return false;
        }
    }
}