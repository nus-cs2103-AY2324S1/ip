package rayshawn.chatbot.storage;

import java.util.ArrayList;
import java.util.List;

import rayshawn.chatbot.tasks.Deadline;
import rayshawn.chatbot.tasks.Event;
import rayshawn.chatbot.tasks.Task;
import rayshawn.chatbot.tasks.TaskList;

public class TaskListEncoder {
    public static List<String> encodeTaskList(TaskList toSave) {
        final List<String> encodedTasks = new ArrayList<>();
        toSave.getAllTasks().forEach(task -> encodedTasks.add(encodedTaskToString(task)));
        return encodedTasks;
    }

    private static String encodedTaskToString(Task task) {
        final StringBuilder encodedTaskBuilder = new StringBuilder();
        encodedTaskBuilder.append(task.getType() + " | ");

        encodedTaskBuilder.append((task.checkDone() ? "X" : " ") + " | ");

        encodedTaskBuilder.append(task.getDescription());

        if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            encodedTaskBuilder.append(" | " + deadline.getDate());
        }

        if (task instanceof Event) {
            Event event = (Event) task;
            encodedTaskBuilder.append(" | " + event.getDate() + " | " + event.getStart() + " | " + event.getEnd());
        }

        return encodedTaskBuilder.toString();
    }
}
