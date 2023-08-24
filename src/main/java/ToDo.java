import java.util.ArrayList;

/**
 * Class to declare a Todo task
 * 
 * @author Jason Ray
 */
public class ToDo extends Task {
	public ToDo(String name, Type type) {
		super(name, type);
	}

	public static void printTaskAdded(String taskName, ArrayList<Task> list) {
		System.out.println("You have added a task:");
		System.out.println("\t[T][ ] " + taskName);
		list.add(new ToDo(taskName, Type.TODO));
		System.out.println("There are now " + list.size() + " tasks in the list");
	}
}