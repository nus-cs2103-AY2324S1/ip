package echobot.note;
public class Note {
    private String title;
    private String content;

    public Note(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String toFileString() {
        return title + "/" + content;
    }

    public static Note fromFileString(String fileString) {
        String[] parts = fileString.split("/");
        if (parts.length == 2) {
            String title = parts[0];
            String content = parts[1];
            return new Note(title, content);
        } else {
            return null;
        }
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

}
