package cloud.main.todo;



/**
 * Represents a deadline TODO, which has an ending timestamp.
 */
public class Deadline extends Todo {
	private String timestampEnd;

    public Deadline(String _description, String end) {
		super(_description);

		this.timestampEnd = end;
    }

	public String getEnd() {
		return this.timestampEnd;
	}

	public void setEnd(String end) {
		this.timestampEnd = end;
	}
}
