import java.util.ArrayList;

public class TaskList {
	private final ArrayList<Task> list;

	public TaskList() {
		this.list = new ArrayList<>();
	}

	public void addToList(Task item) {
		Duke.printLine();
		System.out.println("Got it. I've added this task: ");
		this.list.add(item);
//		System.out.println("[" + item.getLetter() + "]" + "[" + item.getStatusIcon() + "] " + item.description);
		System.out.println(item.toString());
		System.out.println("Now you have " + this.list.size() + " tasks in the list.");
		Duke.printLine();
	}

	public Task get(int index) {
		return this.list.get(index);
	}

	public void printList() {
		Duke.printLine();
		System.out.println("Here are the tasks in your list:");
		for (int i = 0; i < list.size(); i++) {
			Task currTask = list.get(i);
//			System.out.println((i + 1) + ". " + "[" + currTask.getLetter() + "][" + currTask.getStatusIcon() + "] " + currTask.description);
			System.out.println((i + 1) + "." + currTask.toString());
		}
		Duke.printLine();
	}

	public int length() {
		return this.list.size();
	}
}