package brandon.chatbot.storage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import brandon.chatbot.common.DukeException;
import brandon.chatbot.parser.Parser;
import brandon.chatbot.tag.Tag;
import brandon.chatbot.tasks.Deadline;
import brandon.chatbot.tasks.Event;
import brandon.chatbot.tasks.Task;
import brandon.chatbot.tasks.TaskList;
import brandon.chatbot.tasks.Todo;

/**
 * Represents a storage through which the program stores its output.
 */
public class Storage {
    private static final Pattern DATA_FORMAT = Pattern.compile(
            "\\/&type:(?<type>.*)"
                    + "\\/&done:(?<done>.*)"
                    + "\\/&title:(?<title>.*)"
                    + "\\/&tags:(?<tags>.*)"
                    + "\\/&others:(?<others>.*)");
    private static final Pattern DEADLINE_FORMAT = Pattern.compile("/by:(?<deadline>.*)");
    private static final Pattern TIME_FORMAT = Pattern.compile("/from:(?<from>.*)/to:(?<to>.*)");
    private Path path;

    public Storage(Path path) {
        this.path = path;
    }

    /**
     * Saves tasks in the TaskList to the local storage.
     *
     * @param tasks the tasks stored in the TaskList object.
     */
    public void save(TaskList tasks) throws Exception {
        try {
            if (!Files.exists(path)) {
                Files.createFile(path);
            }
            String data = encodeTasks(tasks);
            Files.write(path, data.getBytes());
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Encodes the tasks in the task list.
     *
     * @param tasks is TaskList object that contains a list of tasks.
     * @return encoded string of the list of tasks.
     */
    public String encodeTasks(TaskList tasks) {
        String data = "";
        for (Task task : tasks.getList()) {
            data += "/&type: " + task.getType() + " ";
            data += "/&done: " + task.getDone() + " ";
            data += "/&title: " + task.getTitle() + " ";
            data += "/&tags: " + task.getTags() + " ";
            data += "/&others: " + task.getOtherData() + "\n";
        }
        return data;
    }

    /**
     * Reads the task list saved in the hard disk and loads tasks to the current list.
     *
     * @return TaskList of tasks saved from the previous run of ekud chatbot.
     */
    public TaskList load() {
        try (BufferedReader reader = new BufferedReader(new FileReader(path.toFile()))) {
            TaskList newList = new TaskList();
            String line;
            while ((line = reader.readLine()) != null) {
                Task taskToAdd = decode(line);
                newList.addTask(taskToAdd);
            }
            return newList;
        } catch (Exception e) {
            return new TaskList();
        }
    }

    private Task decode(String line) throws DukeException {
        Matcher matcher = DATA_FORMAT.matcher(line);
        if (!matcher.matches()) {
            return new Task(null, null);
        }

        String type = matcher.group("type");
        String done = matcher.group("done");
        String title = matcher.group("title");
        String tags = matcher.group("tags");
        ArrayList<Tag> parsedTags = new Parser().parseTags(tags);
        String others = matcher.group("others");

        switch (type.strip()) {
        case "todo":
            return createToDo(title, done, parsedTags);
        case "deadline":
            Matcher deadlineMatcher = DEADLINE_FORMAT.matcher(others.strip());
            if (!deadlineMatcher.matches()) {
                break;
            }
            String deadline = deadlineMatcher.group("deadline");
            return createDeadline(title, done, deadline, parsedTags);
        case "event":
            Matcher eventMatcher = TIME_FORMAT.matcher(others.strip());
            if (!eventMatcher.matches()) {
                break;
            }
            String from = eventMatcher.group("from");
            String to = eventMatcher.group("to");
            return createEvent(title, done, from, to, parsedTags);
        default:
            return new Task(null, null);
        }

        return new Task(null, null);
    }

    private Todo createToDo(
            String title,
            String done,
            ArrayList<Tag> parsedTags
    ) throws DukeException {
        Todo newTodo = new Todo(title.strip(), Optional.ofNullable(parsedTags));
        if (done.strip().equals("done")) {
            newTodo.setDone(true);
        }

        return newTodo;
    }

    private Deadline createDeadline(
            String title,
            String done,
            String deadline,
            ArrayList<Tag> parsedTags
    ) throws DukeException {
        Deadline newDeadline = new Deadline(title.strip(), deadline.strip(), Optional.ofNullable(parsedTags));
        if (done.strip().equals("done")) {
            newDeadline.setDone(true);
        }

        return newDeadline;
    }

    private Event createEvent(
            String title,
            String done,
            String from,
            String to,
            ArrayList<Tag> parsedTags
    ) throws DukeException {
        Event newEvent = new Event(title.strip(), from.strip(), to.strip(), Optional.ofNullable(parsedTags));
        if (done.strip().equals("done")) {
            newEvent.setDone(true);
        }

        return newEvent;
    }
}
