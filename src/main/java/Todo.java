/**
 * This is the Todo class, a child class of Task class
 * @author Selwyn
 */
public class Todo extends Task{
    /**
     * Constructor for a Todo task
     *
     * @param detail
     */
    public Todo(String detail) {
        super(detail);
    }

    /**
     * This method displays and prints the Todo completion state and details
     */
    @Override
    public void displayTask() {
        System.out.print("[T] ");
        System.out.print(this.isDone ? "[X] " : "[ ] ");
        System.out.println(this.detail);
    }
}