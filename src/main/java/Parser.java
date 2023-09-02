import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {
    public Parser() {

    }

    public static String taskData(Task task) {
        String taskType = task instanceof ToDo ? "T" : task instanceof Deadline ? "D" : "E";
        String status = task.getIsDone() ? "1" : "0";
        String description = task.getDescription();

        StringBuilder data = new StringBuilder();
        data.append(taskType).append(" | ").append(status).append(" | ").append(description);

        // Add additional information for Deadline and Event tasks
        if (task instanceof Deadline) {
            LocalDateTime deadlineDateTime = ((Deadline) task).getBy();
            String formattedDateTime = deadlineDateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
            data.append(" | ").append(formattedDateTime);
        } else if (task instanceof Event) {
            data.append(" | ").append(((Event) task).getFrom()).append(" | ").append(((Event) task).getTo());
        }

        return data.toString();

    }

    public static Task dataToTask(String data) {
        String[] info = data.split(" \\| ");
        String taskType = info[0];
        String status = info[1];
        String description = info[2];

        Task task;
        switch (taskType) {
            case "T":
                task = new ToDo(description);
                break;
            case "D":
                task = new Deadline(description, info[3]);
                break;
            case "E":
                task = new Event(description, info[3], info[4]);
                break;
            default:
                throw new IllegalArgumentException("☹ OOPS!!! Invalid task in data");
        }

        if (status.equals("1")) {
            task.markAsDone();
        }

        return task;
    }

}
