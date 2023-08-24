/**
 * Abstract class for tasks
 * @author Jason Ray
 */
public class Task {	
	private String name;
	private Boolean done;
	private Type type;
	public static String LINE_BREAK = "----------------------------------------------------------";

	public Task(String name, Type type) {
		this.name = name;
		this.done = false;
		this.type = type;
	}

	public void markAsDone() {
		this.done = true;
		System.out.println(LINE_BREAK);
		System.out.println("You have marked this task as done");
		System.out.println("\t[X] " + getName());
		System.out.println(LINE_BREAK);
		System.out.println("\n");
	}

	public void markAsNotDone() {
		this.done = false;
		System.out.println(LINE_BREAK);
		System.out.println("You have marked this task as not done");
		System.out.println("\t[ ] " + getName());
		System.out.println(LINE_BREAK);
		System.out.println("\n");
	}

	public String getName() {
		return this.name;
	}
	
	public Boolean getDone() {
		return this.done;
	}

	public String getType() {
		return this.type.name();
	}

}