import java.util.ArrayList;

/**
 * Class to declare a Deadline task
 * 
 * @author Jason Ray
 */
public class Deadline extends Task {
	private String deadline;

	public Deadline(String name, String deadline, Type type) {
		super(name, type);
		this.deadline = deadline;
	}
	
	public static void printTaskAdded(String taskName, String deadline, ArrayList<Task> list) {
		System.out.println("You have added a task:");
		System.out.println("\t[D][ ] " + taskName + " (by: "+ deadline + ")");
		list.add(new Deadline(taskName, deadline, Type.DEADLINE));
		System.out.println("There are now " + list.size() + " tasks in the list");
	}

	public String getDeadline() {
		return this.deadline;
	}
}