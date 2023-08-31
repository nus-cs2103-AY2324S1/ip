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

    public String toString(int number) {
        return String.format(
            "%s | %s#%d: %s",
            this.getCompletionString(),
            this.getTypeString(),
            number,
            this.getDescription()
        );
    }

    public String getDescription() {
        return this.description;
    }

    public boolean isComplete() {
        return this.isComplete;
    }

    /**
     * Returns a string representing whether the TODO has been completed.
     */
    public String getCompletionString() {
        return this.isComplete ? "X" : " ";
    }

    public void setComplete(boolean _isComplete) {
        this.isComplete = _isComplete;
    }

    /**
     * Returns a string representing the type of this TODO.
     */
    public String getTypeString() {
        return "T";
    }
}
