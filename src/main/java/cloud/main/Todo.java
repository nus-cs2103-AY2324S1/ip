package cloud.main;



/**
 * Represents a TODO.
 */
public class Todo {
	private String description;
	private boolean isDone;

	public Todo(String _description) {
		this(_description, false);
	}

    public Todo(String _description, boolean _isDone) {
		this.description = _description;
		this.isDone = _isDone;
    }

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String _description) {
		this.description = _description;
	}

	public boolean isDone() {
		return this.isDone;
	}

	public void setDone(boolean _isDone) {
		this.isDone = _isDone;
	}
}
