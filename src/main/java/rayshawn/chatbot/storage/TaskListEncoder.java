package rayshawn.chatbot.storage;

import java.util.ArrayList;
import java.util.List;

import rayshawn.chatbot.tasks.Deadline;
import rayshawn.chatbot.tasks.Event;
import rayshawn.chatbot.tasks.Task;
import rayshawn.chatbot.tasks.TaskList;

/**
 * Encodes the task list into a data file for storage.
 */
public class TaskListEncoder {
    // Adapted from
    // https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/storage/AddressBookEncoder.java

    /**
     * Encodes all the tasks into a list of decodable and readable string presentation for storage.
     *
     * @param toSave list of data to be saved
     * @return list of string to be placed into storage
     */
    public static List<String> encodeTaskList(TaskList toSave) {
        final List<String> encodedTasks = new ArrayList<>();
        toSave.getAllTasks().forEach(task -> encodedTasks.add(encodedTaskToString(task)));
        return encodedTasks;
    }

    private static String encodedTaskToString(Task task) {
        final StringBuilder encodedTaskBuilder = new StringBuilder(encodedTaskInit(task));


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

    private static String encodedTaskInit(Task task) {
        final StringBuilder str = new StringBuilder();
        str.append(task.getType() + " | ");

        str.append((task.isDone() ? "X" : " ") + " | ");

        str.append(task.getDescription());
        return str.toString();
    }
}
