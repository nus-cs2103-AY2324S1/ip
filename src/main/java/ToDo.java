/*
 * Todo class that inherits from Task.
 * 
 * 
 * @author Owen Yeo
 */
public class ToDo extends Task {
    
    ToDo(String label) {
        super(label);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
