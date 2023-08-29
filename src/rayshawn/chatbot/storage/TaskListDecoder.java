package rayshawn.chatbot.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import rayshawn.chatbot.exceptions.ChatBotException;
import rayshawn.chatbot.storage.Storage.StorageOperationException;
import rayshawn.chatbot.tasks.Deadline;
import rayshawn.chatbot.tasks.Event;
import rayshawn.chatbot.tasks.Task;
import rayshawn.chatbot.tasks.TaskList;
import rayshawn.chatbot.tasks.ToDo;

public class TaskListDecoder {

    private static final Pattern TODO_FORMAT =
            Pattern.compile("(?<type>[^/]+)" + " \\| (?<description>[^/]+)");
    private static final Pattern DEADLINE_FORMAT =
            Pattern.compile("(?<type>[^/]+)" + " \\| (?<description>[^/]+)" + " \\| (?<date>[^/]+)");
    private static final Pattern EVENT_FORMAT =
            Pattern.compile("(?<type>[^/]+)" +
                    " \\| (?<description>[^/]+)" +
                    " \\| (?<date>[^/]+)" +
                    " \\| (?<start>[^/]+)" +
                    " \\| (?<end>[^/]+)");

    public static TaskList decodeTaskList(List<String> encodedTaskList)
            throws ChatBotException, StorageOperationException {
        final List<Task> decodedTasks = new ArrayList<>();
        for (String encodedTask : encodedTaskList) {
            decodedTasks.add(decodeTaskFromString(encodedTask));
        }
        return new TaskList(decodedTasks);
    }

    private static Task decodeTaskFromString(String encodedTask)
            throws ChatBotException, StorageOperationException {
        final Matcher matcherT = TODO_FORMAT.matcher(encodedTask.trim());
        final Matcher matcherD = DEADLINE_FORMAT.matcher(encodedTask.trim());
        final Matcher matcherE = EVENT_FORMAT.matcher(encodedTask.trim());

        if (!matcherT.matches() && !matcherD.matches() && !matcherE.matches()) {
            throw new StorageOperationException("Encoded task is in incorrect format!");
        }

        String[] temp = encodedTask.split(" \\| ");
        switch (temp[0]) {
        case "T":
            return createToDo(temp[2], temp[1]);
        case "D":
            return createDeadline(temp[2], temp[3], temp[1]);
        case "E":
            return createEvent(temp[2], temp[3], temp[4], temp[5], temp[1]);
        default:
            throw new StorageOperationException("Invalid Task Type!!");
        }
    }

    private static Task createToDo(String description, String done) throws StorageOperationException {
        Task task = new ToDo(description);
        if (done.equals("X")) {
            task.markDone();
        } else if (!done.equals(" ")) {
            throw new StorageOperationException("Invalid Type for Task Done!!");
        }
        return task;
    }

    private static Task createDeadline(String description, String date, String done) throws StorageOperationException {
        Task task = new Deadline(description, date);
        if (done.equals("X")) {
            task.markDone();
        } else if (!done.equals(" ")) {
            throw new StorageOperationException("Invalid Type for Task Done!!");
        }
        return task;
    }

    private static Task createEvent(String description, String date, String start, String end, String done)
            throws StorageOperationException {
        Task task = new Event(description, date, start, end);
        if (done.equals("X")) {
            task.markDone();
        } else if (!done.equals(" ")) {
            throw new StorageOperationException("Invalid Type for Task Done!!");
        }
        return task;
    }
}
