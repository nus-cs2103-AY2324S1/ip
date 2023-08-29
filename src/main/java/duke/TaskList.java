package duke;

import java.util.ArrayList;

public class TaskList {
	private final ArrayList<Task> list;

	public TaskList() {
		this.list = new ArrayList<>();
	}

	public void addToList(Task item, Storage storage) {
		System.out.println("Got it. I've added this task:");
		this.list.add(item);
		System.out.println(item.toString());
		System.out.println("Now you have " + this.list.size() + " tasks in the list.");
		storage.writeToFile(this);
	}

	public void addToList(Task item) {
		this.list.add(item);
	}

	public Task get(int index) {
		return this.list.get(index);
	}

	public void printList() {
		System.out.println("Here are the tasks in your list:");
		for (int i = 0; i < this.list.size(); i++) {
			Task currTask = this.list.get(i);
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