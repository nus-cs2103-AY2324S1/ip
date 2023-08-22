public class Task {
	private String name;
	private Boolean done;

	public Task(String name) {
		this.name = name;
		this.done = false;
	}

	public void markAsDone() {
		this.done = true;
	}

	public void markAsNotDone() {
		this.done = false;
	}

	public String getName() {
		return this.name;
	}
	
	public Boolean getDone() {
		return this.done;
	}
}