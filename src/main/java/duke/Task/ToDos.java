package duke.Task;

import duke.DukeException.DukeException;

public class ToDos extends Task {

    /**
     * Create the todo task.
     * @param name Description of the task.
     */
    public ToDos (String name) {
        super(name);
    }

    /**
     * Convert the task to string.
     * @return String that represent the task.
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
     * Check whether the input is a todo.
     * @param input Task to be checked.
     * @return Boolean that represent whether the task is a todo.
     * @throws DukeException Exception where the todo is not valid.
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