public abstract class Tasks {

    boolean status;
    static int count = 0;
    abstract String toFileString();
    public Tasks() {
        this.status = false;
    }
    public void markDone() {
        this.status = true;
    }
    public void markNotDone() {
        this.status = false;
    }


}
