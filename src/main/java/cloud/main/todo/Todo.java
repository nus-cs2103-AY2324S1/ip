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

	public void setDescription(String _description) {
		this.description = _description;
	}

	public boolean isComplete() {
		return this.isComplete;
	}

	public void setComplete(boolean _isComplete) {
		this.isComplete = _isComplete;
	}
}
