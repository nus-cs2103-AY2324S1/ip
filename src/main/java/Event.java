import java.util.ArrayList;

/**
 * Class to declare a Event task
 * 
 * @author Jason Ray
 */
public class Event extends Task {
	private String start;
	private String end;

	public Event(String name, String start, String end, Type type) {
		super(name, type);
		this.start = start;
		this.end = end;
	}
	
	public static void printTaskAdded(String taskName, String start, String end, ArrayList<Task> list) {
		System.out.println(LINE_BREAK);
		System.out.println("You have added a task:");
		System.out.println("\t[E][ ] " + taskName + " (from: "+ start + " to: " + end + ")");
		list.add(new Event(taskName, start, end, Type.EVENT));
		System.out.println("There are now " + list.size() + " tasks in the list");
		System.out.println(LINE_BREAK);
		System.out.println("\n");
	}

	public String getStart() {
		return this.start;
	}

	public String getEnd() {
		return this.end;
	}
}