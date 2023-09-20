package duke.task;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
public class Tag {
    private String tagName;
    private static HashMap<String, Tag> tagHashMap = new HashMap<>();
    private static HashMap<Tag, ArrayList<Task>> tagTaskArrayHashMap = new HashMap<>();
    private static Tag emptyTag= new EmptyTag();

    protected Tag(String tagName) {
        this.tagName = tagName;
    }

    public String getTagName() {
        return tagName;
    }
    public String getHashedTagName() {
        return "#" + tagName;
    }

    public String getParsedTagName() {
        return tagName;
    }

    public static boolean existTag(String tagName) {
        return tagHashMap.containsKey(tagName);
    }

    public static Tag generateTag(String tagName) {
        if(tagName == null || tagName.equals("") || tagName.equals("null")){
            return emptyTag;
        }
        if(!tagHashMap.containsKey(tagName)) {
            Tag newTag = new Tag(tagName);
            tagHashMap.put(tagName, newTag);
        }

        return tagHashMap.get(tagName);
    }

    public static String removeTag(String tagName) {

        if(!tagHashMap.containsKey(tagName)) {
            return "Unable to Find Tag";
        }
        Tag tag = tagHashMap.get(tagName);
        ArrayList<Task> taskArrayList =tagTaskArrayHashMap.get(tagName);
        tagHashMap.remove(tagName);

        if (taskArrayList != null) {
            for( Task task : taskArrayList) {
                task.removeTag();
            }
        }

        return "Done Removing Tag";
    }


}
