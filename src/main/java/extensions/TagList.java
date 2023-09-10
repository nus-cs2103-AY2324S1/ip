package extensions;

import java.util.ArrayList;
import java.util.List;

public class TagList {
    private List<Tag> tagList;

    public TagList() {
        tagList = new ArrayList<>();
    }

    public List<Tag> getTagList() {
        return tagList;
    }


    public int getSize() {
        return tagList.size();
    }
    public void addTag(Tag tag) {
        tagList.add(tag);
    }

    public Tag getTag(int index) {
        return tagList.get(index);
    }

    public boolean containTag(String tagNameToFind) {
        for (Tag tag : tagList) {
            if (tag.toString().equals("#" + tagNameToFind)) {
                return true;
            }
        }
        return false;
    }


    public void removeTag(int index) {
        tagList.remove(index);
    }
}
