import java.io.FileNotFoundException;

public class Parser {
    public static Task parseTaskInput(String input) throws DukeException {
        String[] words = input.split(" ");
        TaskType taskType = TaskType.valueOf(words[0].toUpperCase());
        String taskDescription = input.replaceFirst(words[0], "").trim();

        switch (taskType) {
            case ADD:
                if (taskDescription.isEmpty()) {
                    throw new DukeException("What should I add in? Pleas add in a description :)");
                }
                return new Add(taskDescription);
            case TODO:
                if (taskDescription.isEmpty()) {
                    throw new DukeException("Task Description cannot be EMPTY. Please add in a description :)");
                }
                return new ToDo(taskDescription);
            case DEADLINE:
                String[] deadlineParts = taskDescription.split("/by", 2);
                String description = deadlineParts[0].trim();
                if (description.isEmpty()) {
                    throw new DukeException("Please provide a task description.");
                }
                if (deadlineParts.length < 2 || deadlineParts[0].trim().isEmpty()) {
                    throw new DukeException("When is " + deadlineParts[0] + " due? use /by: (date)");
                }
                return new DeadLine(deadlineParts[0].trim(), deadlineParts[1].trim());
            case EVENT:
                String[] eventParts = taskDescription.split("/from", 2);
                String desc = eventParts[0].trim();
                if (desc.isEmpty() || desc.equals("/from") || desc.equals("/to") ) {
                    throw new DukeException("Please provide a task description.");
                } else if (eventParts.length < 2) {
                    throw new DukeException("When is " + desc + "? use /from: (date) /to: (date)");
                } else {
                    String[] toParts = eventParts[1].split("/to", 2);
                    if (toParts.length < 2 || toParts[1].trim().isEmpty()) {
                        throw new DukeException("When is " + eventParts[0] + "? use /from: (date) /to: (date)");
                    } else {
                        return new Event(desc, toParts[0].trim(), toParts[1].trim());
                    }
                }
        }
        return null;
    }

    public static Task parseTask(String taskData) throws FileNotFoundException, DukeException {
        try {
            String taskType = taskData.substring(1, 2);
            boolean isDone = taskData.charAt(4) == 'X';
            String taskInfo = taskData.substring(7);
            Task taskToAdd = null;

            switch (taskType) {
                case "A":
                    taskToAdd = new Add(taskInfo);
                    break;
                case "T":
                    taskToAdd = new ToDo(taskInfo);
                    break;
                case "D":
                    taskInfo = taskInfo.substring(0, taskInfo.indexOf("(") - 1);
                    String xtraInfo = taskData.substring(taskData.indexOf("(") + 1, taskData.indexOf(")"));
                    String[] deadLineInfo = xtraInfo.split(": ");
                    String by = deadLineInfo[1];
                    taskToAdd = new DeadLine(taskInfo, by);
                    break;
                case "E":
                    taskInfo = taskInfo.substring(0, taskInfo.indexOf('(') - 1);
                    String addInfo = taskData.substring(taskData.indexOf('(') + 1, taskData.indexOf(')'));
                    String[] eventInfo = addInfo.split(": ");
                    String from = eventInfo[1].substring(0, eventInfo[1].length() - 2).trim();
                    String to = eventInfo[2].trim();
                    taskToAdd = new Event(taskInfo, from, to);
                    break;
                default:
                    throw new DukeException("File is corrupted!");
            }
            if (taskToAdd != null) {
                if (isDone) {
                    taskToAdd.isCompleted();
                }
                return taskToAdd;
            }
        } catch (Exception e) {
            return new Task("(CORRUPTED) " + taskData);
        }
        return null;
    }
}
