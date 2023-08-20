public class Task {
    private boolean done;
    private String name;
    public Task(String name) {
        this.done = false;
        this.name = name;
    }

    public boolean getStatus(){
        return done;
    }

    public void taskIsDone() {
        this.done = true;
    }
    public void taskNotDone() {
        this.done = false;
    }

    public String getName() {
        return name;
    }
}
