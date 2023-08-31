package cloud.main.todo;



/**
 * Represents a TODO.
 */
public class Todo {
    private boolean isComplete = false;

    private String description;

    public Todo(String _description) {
        this.description = _description;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean isComplete() {
        return this.isComplete;
    }

    public String getCompleteSymbol() {
        return this.isComplete ? "X" : " ";
    }

    public void setComplete(boolean _isComplete) {
        this.isComplete = _isComplete;
    }
}
