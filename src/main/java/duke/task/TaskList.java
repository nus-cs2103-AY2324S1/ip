package duke.task;
import duke.DukeException;
import duke.task.Task;

import java.util.ArrayList;

/**
 * Represents a List of Task.
 */
public class TaskList {
	private ArrayList<Task> taskList;

	/**
	 * @param taskList Represents the list of Task.
	 */
	public TaskList(ArrayList<Task> taskList) {
		this.taskList = taskList;
	}

	/**
	 * Initialises a new arrayList to store Task.
	 */
	public TaskList() {
		this.taskList = new ArrayList<Task>(100);
	}

	/**
	 * Add task to list.
	 * @param t Task to be added.
	 */
	public void add(Task t) {
		taskList.add(t);
	}

	/**
	 * List all task in taskList in numbered format.
	 */
	public void list() {
		String lst = "";
		StringBuilder br = new StringBuilder();
		for (int i = 0; i < taskList.size(); i++) {
			// int idx = i + 1;
			br.append(i + 1).append(". ");
			br.append((taskList.get(i)).toString()).append("\n");
		}
		String echo = String.format("____________________________________________________________\n"
				+ "Here are the task in your list:\n"
				+ "%s"
				+ "____________________________________________________________", br.toString());
		System.out.println(echo);
	}

	/**
	 * Marks task.
	 * @param toMark Whether to mark Task as done or not.
	 * @param idx Index of task to be marked, starting from 0.
	 * @throws DukeException If unable to mark task at specified idx.
	 */
	public void toMark(Boolean toMark, int idx) throws DukeException {
		if (idx + 1 > taskList.size()) {
			throw new DukeException("Trying to mark or unmark something beyond the list");
		}

		Task t = taskList.get(idx);
		if (toMark) {
			t.markAsDone();
			String echo = String.format("____________________________________________________________\n" +
					"Nice! I've marked this task as done:\n" +
					t.toString() + "\n" +
					"____________________________________________________________");
			System.out.println(echo);
		} else {
			taskList.get(idx).unMark();
			String echo = String.format("____________________________________________________________\n" +
					"Nice! I've marked this task as not done yet:\n" +
					t.toString() + "\n" +
					"____________________________________________________________");
			System.out.println(echo);
		}
	}

	/**
	 * Adds task and prints String represntation of what was done.
	 * @param task Represents the Task to be added to taskList.
	 */
	public void addTask(Task task) {
		this.taskList.add(task);
		Task t = taskList.get(taskList.size() - 1);
		String echo = String.format("____________________________________________________________\n" +
				"Got it. I've added this task:\n" +
				"%s\n" +
				"Now you have %s tasks in the list\n" +
				"____________________________________________________________", t.toString(), taskList.size());
		System.out.println(echo);
	}

	/**
	 * Deletes task from the taskList.
	 * @param pos Position of Task in the taskList to be deleted.
	 */
	public void delete(int pos) {
		Task t = taskList.get(pos - 1);
		String remaining = Integer.toString(taskList.size() - 1);
		taskList.remove(pos);
		String echo = String.format("____________________________________________________________\n" +
				"Noted. I've removed this task:\n" +
				"%s\n" +
				"Now you have %s tasks in the list.\n" +
				"____________________________________________________________", t.toString(), remaining);
		System.out.println(echo);
	}
}
