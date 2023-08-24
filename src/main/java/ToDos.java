public class ToDos extends Task{

    /**
     * The constructor
     * @param name the name of the ToDos
     */
    public ToDos (String name) {
        super(name);
    }

    /**
     * To convert the ToDos to the string
     * @return a string
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * To check whether the input is a Todo
     * @param input the task
     * @return Boolean
     * @throws TodoEmptyNameException
     */
    public static boolean isTodo(String input) throws TodoEmptyNameException {
        if(input.split( " ")[0].equals("todo")) {
            if (input.split(" ").length == 1) {
                throw new TodoEmptyNameException();
            } else {
                return true;
            }
        } else {
            return false;
        }
    }
}