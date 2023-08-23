public class ToDo extends Task {
	public ToDo(String description) {
		super(description);
		this.letter = "T";
	}
	
	@Override
	public String toString() {
		return "[" + this.letter + "]" + "[" + this.getStatusIcon() + "] " + this.description;
	}
}
