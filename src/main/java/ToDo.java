/**
 * ToDo class is a task that contains a name only without any dates
 */
public class ToDo extends Task{
    public ToDo(String name) {
        super(name);
    }
    /**
     * This method gives the string representation of a ToDo task when it is in a list
     *
     * @return The String representation of a todo Task
     */
    @Override
    public String showTaskinList() {
        return "[T]" + super.showTaskinList();
    }

    @Override
    public String printList() {
        return "T | " + super.printList();
    }


}
