package duke.tasks;

import java.util.ArrayList;

public class TaskList {
	/**
	 * The list of tasks
	 */
	private final ArrayList<Task> list;

	/**
	 * Constructor for TaskList
	 */
	public TaskList(ArrayList<Task> list) {
		this.list = list;
	}

	/**
	 * Constructor for TaskList
	 */
	public void add(Task newTask) {
		this.list.add(newTask);
	}

	/**
	 * Marks the task as done
	 *
	 * @param index the index of the task
	 */
	public void mark(int index) {
		this.list.get(index).mark();
	}

	/**
	 * Unmarks the task as done
	 *
	 * @param index the index of the task
	 */
	public void unmark(int index) {
		this.list.get(index).unmark();
	}

	/**
	 * Prints the list of tasks
	 */
	public void list() {
		for (int i = 0; i < this.list.size(); i++) {
			System.out.println((i + 1) + "." + this.list.get(i).toString());
		}
	}

	/**
	 * Deletes the task
	 *
	 * @param index the index of the task
	 */
	public void delete(int index) {
		this.list.remove(index);
	}

	/**
	 * Returns the size of the list
	 *
	 * @return the size of the list
	 */
	public int length() {
		return this.list.size();
	}

	/**
	 * Returns the task
	 *
	 * @param index the index of the task
	 * @return the task
	 */
	public Task getTask(int index) {
		return this.list.get(index);
	}

	/**
	 * Returns all the tasks
	 *
	 * @return all the tasks
	 */
	public ArrayList<Task> getAllTasks() {
		return this.list;
	}

	/**
	 * Returns the filtered tasks
	 *
	 * @param keyword the keyword to filter
	 * @return the filtered tasks
	 */
	public TaskList filter(String keyword) {
		ArrayList<Task> filteredTasks = new ArrayList<>();
		for (Task task : this.list) {
			if (task.getDescription().contains(keyword)) {
				filteredTasks.add(task);
			}
		}
		return new TaskList(filteredTasks);
	}
}