public class Task {
    private String name;
    public Boolean done;
    public Task (String name) {
        this.name = name;
        this.done = false;
    }

    public String name() {
        return this.name;
    }

    public String display() {
        if(done) {
            return "[X] " + this.name;
        }
        return "[] " + this.name();
    }
}
