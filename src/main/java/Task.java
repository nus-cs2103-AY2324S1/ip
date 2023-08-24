import java.util.ArrayList;

/**
 * Abstract class for tasks
 * @author Jason Ray
 */
public class Task {	
	private String name;
	private String desc;
	private Boolean done;
	private Type type;

	public Task(String name, Type type, String desc) {
		this.name = name;
		this.done = false;
		this.type = type;
		this.desc = desc;
	}

	public void markAsDone() {
		this.done = true;
		System.out.println("You have marked this task as done");
		System.out.println("\t" + this.toString());
	}

	public void markAsNotDone() {
		this.done = false;
		System.out.println("You have marked this task as not done");
		System.out.println("\t" + this.toString());
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

	public static void deleteTask(Task task, ArrayList<Task> list) {
		System.out.println("You have deleted a task:");
		System.out.println("\t" + task.toString());
		list.remove(task);
		System.out.println("There are now " + list.size() + " tasks in the list");
	}

	@Override
	public String toString() {
		return "[" + this.getType().substring(0, 1) + "][" + (this.getDone() ? "X" : " ") + "] " + this.name + this.desc;
	}
}