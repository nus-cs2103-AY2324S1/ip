package Tasks;

/**
 * A child class to Task, this in particular is for todo tasks.
 */
public class Todo extends Task{
    public Todo(String name) {
        super(name);
        this.type = "T";
    }
//    @Override
//    public String toString() {
//        return super.toString() + ;
//    }
}
