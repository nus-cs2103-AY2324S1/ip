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
    public void reply(int i) {

        System.out.println("Added: \n" + this.toString() + "\nPeko!");
        System.out.println("You have: " + i + " tasks now Peko");
    }
}
