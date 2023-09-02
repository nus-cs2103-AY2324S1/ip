package Duke;

/**
 * Class to create todo instances.
 * Extends Task.
 */
public class Todo extends Task{
    /**
     * Constructor fof todo
     * @param val
     */
    public Todo (String val){
        super(val);
    }
    public String toString(){
        return "[T]"+super.toString();
    }
}
