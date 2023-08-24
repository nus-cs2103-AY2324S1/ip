public class Task {
    private String description;
    private boolean isDone = false;

    public Task(String description) {
        this.description = description;
    }



    @Override

    public String toString() {
        String s = String.format("%s",this.description);
        return s;
    }
}
