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
}