public class ListItem {
    private String text;
    private boolean done;

    public ListItem(String text) {
        this.text = text;
        this.done = false; // defaults to not done yet
    }

    public String getText() {
        return text;
    }

    public boolean getDone() {
        return done;
    }

    public void toggleDone() {
        this.done = !this.done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public String toString() {
        if (done) {
            // if done use tickbox
            return "[✅] " + this.text;
        } else {
            return "[ ] " + this.text;
        }
    }
}
