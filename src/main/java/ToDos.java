import java.io.Serializable;

/**
 * This class is used to create Todos object that holds the description of your todo
 */
public class ToDos extends Task implements Serializable {
    /**
     * This constructor creates an instance of a Todo Object that has its description
     * @param text This param gives the description of the Todo object
     */
    public ToDos(String text){
        super(text);
    }
    /**
     * This is a toString method that has been Override to better suit the display of this class
     * @return The string form and information about this Todo
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
