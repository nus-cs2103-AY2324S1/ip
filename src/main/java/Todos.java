/**
 * Emcapsulates a Todo task
 * @author Donovan Chan Jia Jun
 */
public class Todos extends Task{
    public Todos(String name) {
        super(name);
    }

    /**
     * Retrieves the string representation of the Todo Object.
     *
     * @return String Represents the Todo
     */
    @Override
    public String toString() {
        return String.format("[T]%s %s", super.getMarking(), super.name);
    }
}
