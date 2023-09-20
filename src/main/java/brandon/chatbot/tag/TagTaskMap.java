package brandon.chatbot.tag;

import java.util.HashMap;

import brandon.chatbot.tasks.Task;
import brandon.chatbot.tasks.TaskList;

/**
 * Represents the map that links Tag objects to the Task object that it is relevant to.
 */
public class TagTaskMap {
    private static HashMap<Tag, TaskList> tagTaskMap;

    /**
     * Constructs and initializes the HashMap between Tag and TaskList objects.
     */
    public TagTaskMap() {
        this.tagTaskMap = new HashMap<>();
    }

    /**
     * Adds a task and a relevant tag to the tagTaskMap HashMap.
     * If a task already exists in the map, then add the tag to the ArrayList of Tags
     * which is the value of the Task in the map.
     *
     * @param tag of the task to be added to the map.
     * @param task is the key to be added to the map unless the key already exists.
     */
    public void add(Tag tag, Task task) {
        if (tagTaskMap.containsKey(tag)) {
            TaskList tasks = tagTaskMap.get(tag);
            tasks.addTask(task);
            tagTaskMap.put(tag, tasks);
            return;
        }

        tagTaskMap.put(tag, new TaskList().addTask(task));
    }

    public TaskList getTaskList(Tag t) {
        return tagTaskMap.get(t) == null ? new TaskList() : tagTaskMap.get(t);
    }

    public TaskList findTasksByTag(Tag t, String title) {
        return tagTaskMap.get(t).findTask(title);
    }
}
