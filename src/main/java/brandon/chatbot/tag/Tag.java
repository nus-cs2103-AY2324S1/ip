package brandon.chatbot.tag;

import java.util.Objects;

public class Tag {
    private final int hashCode;
    private String title;

    public Tag(String title) {
        this.title = title;
        this.hashCode = title.hashCode();
    }

    public String toString() {
        return this.title;
    }
    @Override
    public int hashCode() {
        return this.hashCode;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Tag otherTag = (Tag) obj;
        return title.equals(otherTag.title);
    }
}
