public class Task {

    String list;

    boolean completed;

    public Task(String list) {
        this.list = list;
    }

    public String setMarked() throws DukeException{
        if(this.completed) {
            throw new DukeException("This task has already been marked as done!\n");
        }
        this.completed = true;
        return "";
    }

    public String setUnmarked() throws DukeException{
        if(!this.completed) {
            throw new DukeException("This task has already been marked as not done!");
        }
        this.completed = false;
        return "";
    }

    @Override
    public String toString() {
        return (this.completed ? "[X] " : "[ ] ") + this.list;
    }
}
