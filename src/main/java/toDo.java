public class toDo {
    public String name;
    public boolean done;

    public toDo(String name) {
        this.name = name;
        this.done = false;
    }

    public void mark() {
        this.done = true;
    }

    public void unmark() {
        this.done = false;
    }

    public String getStatus() {
        return this.done ? "[X]" : "[ ]";
    }

}
