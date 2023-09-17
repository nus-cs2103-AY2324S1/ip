package duke.notes;

public class Note {

    private String text;

    public Note(String text) {
        this.text = text;
    }

    public String getNote() {
        return text;
    }
    @Override
    public String toString() {
        return "/NOTE" + text;
    }
}
