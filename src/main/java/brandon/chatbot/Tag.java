package brandon.chatbot;

public class Tag {
    private String title;

    public Tag(String title) {
        this.title = title;
    }

    public void setTag(String title) {
        this.title = title;
    }

    public String toString() {
        return this.title;
    }
}
