package cloud.main;



/**
 * Represents a TODO.
 */
public class Todo {
	private String description;
	private boolean isComplete;

	public Todo(String _description) {
		this(_description, false);
	}

    public Todo(String _description, boolean _isComplete) {
		this.description = _description;
		this.isComplete = _isComplete;
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
