package services.tagging;

import java.util.HashMap;

/** Represents a tag. */
public class Tag {
    private static HashMap<String, Tag> allTags = new HashMap<>();
    private String tagName;

    /**
     * Constructor for Tag.
     *
     * @param tagName the name of the tag.
     */
    public Tag(String tagName) {
        this.tagName = tagName;
        allTags.put(tagName, this);
    }

    public static Tag getTag(String tagName) {
        if (allTags.get(tagName) == null) {
            return new Tag(tagName);
        } else {
            return allTags.get(tagName);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            // same object
            return true;
        } else if (obj instanceof Tag) {
            // null instanceof obj will always return false, so there is no need for a null check.
            Tag otherTag = (Tag) obj;
            return this.tagName.equals(otherTag.tagName);
        }
        return false;
    }

    @Override
    public String toString() {
        return "#" + tagName;
    }
}
