package duke;

import java.util.ArrayList;

public class TaskList {
	/**
	 * The list of tasks
	 */
	private final ArrayList<Task> list;

	/**
	 * Constructor for TaskList
	 */
	public TaskList() {
		this.list = new ArrayList<>();
	}

	/**
	 * Add item to list and update storage
	 *
	 * @param item    the item to be added
	 * @param storage the storage object
	 */
	public void addToList(Task item, Storage storage) {
		System.out.println("Got it. I've added this task:");
		this.list.add(item);
		System.out.println(item.toString());
		System.out.println("Now you have " + this.list.size() + " tasks in the list.");
		storage.writeToFile(this);
	}

	/**
	 * Add item to list
	 *
	 * @param item the item to be added
	 */
	public void addToList(Task item) {
		this.list.add(item);
	}

	/**
	 * Returns the task at index
	 *
	 * @param index the index of the task
	 * @return Task the task at index
	 */
	public Task get(int index) {
		return this.list.get(index);
	}

	/**
	 * Prints the list of tasks
	 */
	public void printList() {
		System.out.println("Here are the tasks in your list:");
		for (int i = 0; i < this.list.size(); i++) {
			Task currTask = this.list.get(i);
			System.out.println((i + 1) + "." + currTask.toString());
		}
	}

	/**
	 * Returns the length of the list
	 *
	 * @return int the length of the list
	 */
	public int length() {
		return this.list.size();
	}

	/**
	 * Deletes the task at index
	 *
	 * @param index the index of the task
	 */
	public void delete(int index) {
		Task currTask = this.list.get(index);
		System.out.println("Noted. I've removed this task:");
		System.out.println(currTask.toString());
		this.list.remove(index);
		System.out.println("Now you have " + this.list.size() + " tasks in the list.");
	}
}