package duke.task;

import duke.exception.TaskListOutOfBoundsException;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
	private List<Task> tasks;

	public TaskList() {
		tasks = new ArrayList<>();
	}

	public Task getTask(int i) { // 1-indexed
		try {
			return tasks.get(i - 1);
		} catch (IndexOutOfBoundsException e) {
			throw new TaskListOutOfBoundsException(i);
		}
	}

	public void addTask(Task t) {
		tasks.add(t);
	}

	public String getNumberOfTasks() {
		return String.format(
				"Now you have %d task%s in the list.", tasks.size(), tasks.size() == 1 ? "" : "s");
	}

	public void deleteTask(int i) {
		try {
			tasks.remove(i - 1);
		} catch (IndexOutOfBoundsException e) {
			throw new TaskListOutOfBoundsException(i);
		}
	}

	public int size() {
		return tasks.size();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		tasks.forEach(
				(task) -> {
					sb.append(task.toCommand());
					sb.append('\n');
				});
		return sb.toString().trim();
	}

	public void populate(List<Task> tasks) {
		this.tasks = tasks;
	}
}
