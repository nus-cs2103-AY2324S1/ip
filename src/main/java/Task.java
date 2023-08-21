public class Task {
    protected String name;
    public Boolean done;
    public Task (String name) {
        this.name = name;
        this.done = false;
    }
    public String display() {
        if(done) {
            return "[X] " + this.name;
        }
        return "[] " + this.name;
    }
}
