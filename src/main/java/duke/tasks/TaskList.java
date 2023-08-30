package duke.tasks;

import java.util.ArrayList;

public class TaskList {
	/**
	 * The list of tasks
	 */
	private ArrayList<Task> list;

	/**
	 * Constructor for TaskList
	 */
	public TaskList(ArrayList<Task> list) {
		this.list = list;
	}

	public void add(Task newTask) {
		this.list.add(newTask);
	}

	public void mark(int index) {
		this.list.get(index).mark();
	}

	public void unmark(int index) {
		this.list.get(index).unmark();
	}

	public void list() {
		for (int i = 0; i < this.list.size(); i++) {
			System.out.println((i + 1) + "." + this.list.get(i).toString());
		}
	}

	public void delete(int index) {
		this.list.remove(index);
	}

	public int size() {
		return this.list.size();
	}

	public Task getTask(int index) {
		return this.list.get(index);
	}

	public ArrayList<Task> getAllTasks() {
		return this.list;
	}

	public int length() {
		return this.list.size();
	}
}