import java.util.ArrayList;

public class TaskList {
	private final ArrayList<Task> list;

	public TaskList() {
		this.list = new ArrayList<>();
	}

	public void addToList(Task item) {
		Duke.printLine();
		this.list.add(item);
		System.out.println("added: " + item);
		Duke.printLine();
	}

	public Task get(int index) {
		return this.list.get(index);
	}

	public void printList() {
		Duke.printLine();
		for (int i = 0; i < list.size(); i++) {
			Task currTask = list.get(i);
			System.out.println((i + 1) + ". [" + currTask.getStatusIcon() + "] " + currTask.description);
		}
		Duke.printLine();
	}
}