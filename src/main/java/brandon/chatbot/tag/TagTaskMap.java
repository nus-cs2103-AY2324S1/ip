package brandon.chatbot.tag;

import brandon.chatbot.tasks.Task;
import brandon.chatbot.tasks.TaskList;

import java.util.HashMap;

public class TagTaskMap {
    public static HashMap<Tag, TaskList> tagTaskMap;

    public TagTaskMap() {
        this.tagTaskMap = new HashMap<>();
    }

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
        return tagTaskMap.get(t);
    }

    public TaskList findTasksByTag(Tag t, String title) {
        return tagTaskMap.get(t).findTask(title);
    }

//    public void printMap() {
//        for (Tag t : tagTaskMap.keySet()) {
//            System.out.println(t);
//        }
//        System.out.println(tagTaskMap.keySet().size());
//    }
}
