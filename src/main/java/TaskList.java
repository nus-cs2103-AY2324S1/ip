import java.util.ArrayList;

public class TaskList {
	private final ArrayList<Task> list;

	public TaskList() {
		this.list = new ArrayList<>();
	}

	public void addToList(Task item) {
		System.out.println("Got it. I've added this task:");
		this.list.add(item);
//		System.out.println("[" + item.getLetter() + "]" + "[" + item.getStatusIcon() + "] " + item.description);
		System.out.println(item.toString());
		System.out.println("Now you have " + this.list.size() + " tasks in the list.");
	}

	public Task get(int index) {
		return this.list.get(index);
	}

	public void printList() {
		System.out.println("Here are the tasks in your list:");
		for (int i = 0; i < this.list.size(); i++) {
			Task currTask = this.list.get(i);
//			System.out.println((i + 1) + ". " + "[" + currTask.getLetter() + "][" + currTask.getStatusIcon() + "] " + currTask.description);
			System.out.println((i + 1) + "." + currTask.toString());
		}
	}

	public int length() {
		return this.list.size();
	}

	public void delete(int index) {
		Task currTask = this.list.get(index);
		System.out.println("Noted. I've removed this task:");
		System.out.println(currTask.toString());
		this.list.remove(index);
		System.out.println("Now you have " + this.list.size() + " tasks in the list.");
	}
}