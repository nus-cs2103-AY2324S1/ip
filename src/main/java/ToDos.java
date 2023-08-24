public class ToDos extends Task {
	public ToDos(String description) {
		super(description);
	}


	@Override
	public String toString() {
		return "[T]" + super.toString();
	}

	public String getIcon() {
		return "[D]" + super.getTask();
	}
}
