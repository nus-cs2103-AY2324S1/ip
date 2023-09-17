package duke;

public class Taskparser {

	/**
	 * Convert tasks in file to remake the list
	 *
	 * @param taskString Tasks in String from storage file
	 * @return	List of tasks
	 */
    public static Task parseTask(String taskString) {
        String[] parts = taskString.split(" \\| ");
        String type = parts[0];
        boolean isDone = Integer.parseInt(parts[1]) == 1;
        String description = parts[2];
        switch (type) {
        case "T":
            return new Todo(description);
        case "D":
            String deadline = parts[3];
            return new Deadline(description, deadline);
        case "E":
             String from = parts[3];
             String to = parts[4];
             return new Event(description, from, to);
        default:
             throw new IllegalArgumentException("Invalid task type: " + type);
        }
    }

	/**
	 * To convert tasks from the list to strings to be stored in the file
	 *
	 * @param task Tasks from the list to be stored
	 * @return String converted from task list in storage form
	 */
    public static String taskToString(Task task) {
        StringBuilder sb = new StringBuilder();
		char type = task.toString().charAt(1);
		sb.append(type).append(" | ");
		sb.append(task.getStatusToStore()).append(" | ");
		sb.append(task.description);
		if (task instanceof Deadline) {
			Deadline deadlineTask = (Deadline) task;
			sb.append(" | ").append(deadlineTask.by);
		} else if (task instanceof Event) {
			Event eventTask = (Event) task;
			sb.append(" | ").append(eventTask.from).append(" | ").append(eventTask.to);
		}

		return sb.toString();
	}
}