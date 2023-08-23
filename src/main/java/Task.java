public class Task {
    protected String name;
    private boolean status;
    public Task(String s) {
        name = s;
        status = false;
    }

    @Override
    public String toString() {
        String out = status ? "[X] " : "[ ] ";
        return out + name;
    }
    public void setMark() {
        status = true;
    }
    public void setUnmark() {
        status = false;
    }
}
