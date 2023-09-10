public class Task {
    protected String name;
    protected boolean isMarked;

    public Task(String name, boolean isMarked) {
        this.name = name;
        this.isMarked = isMarked;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public boolean isMarked() {
        return isMarked;
    }

    public void setMarked(boolean marked) {
        isMarked = marked;
    }

    @Override
    public String toString() {
        if (isMarked) {
            return "[X] " + name;
        } else {
            return "[ ] " + name;
        }
    }
}
