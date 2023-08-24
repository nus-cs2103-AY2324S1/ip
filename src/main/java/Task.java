public class Task {
    protected String name;
    protected boolean status;
    public Task(String s) throws InvalidTaskException {
        if (s.isBlank()) {
            throw new InvalidTaskException();
        }
        this.name = s;
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
        i++;
        System.out.println("Added: \n" + this.toString() + "\nPeko!");
        System.out.println("You have: " + i + " tasks now Peko");
    }
    public String toStore() {
        String state = status ? "0" : "1";
        return state + " | " + this.name;
    }
}
