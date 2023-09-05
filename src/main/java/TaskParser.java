public class TaskParser {
    public static Task parse(String line) {
        String[] parts = line.split(" \\| ");

        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];
        Task task = null;

        if (type.equals("T")) {
            task = new ToDo(description);
        } else if (type.equals("D")) {
            task = new Deadline(description, parts[3]);
        } else if (type.equals("E")) {
            task = new Event(description, parts[3], parts[4]);
        }

        if (isDone) {
            task.markDone();
        }

        return task;
    }


    // Serialize a Task object to a string for saving to the file
    public static String serialize(Task task) {
        String doneStatus = task.isDone ? "1" : "0";

        if (task instanceof ToDo) {
            return "T | " + doneStatus + " | " + task.description;
        } else if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            return "D | " + doneStatus + " | " + task.description + " | " + DateTimeParser.dateTimeToString(deadline.by);
        } else if (task instanceof Event) {
            Event event = (Event) task;
            return "E | " + doneStatus + " | " + task.description + " | " + DateTimeParser.dateTimeToString(event.from)
                    + " | " + DateTimeParser.dateTimeToString(event.to);
        } else {
            return "Wrong formatting";
        }
    }
}
