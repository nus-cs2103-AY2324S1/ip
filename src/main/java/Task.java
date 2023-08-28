abstract class Task {
    private String name;
    private boolean completed;

    public Task(String name) {
        this.name = name;
        this.completed = false;
    }

    //getters & setters
    public String getName() {
        return this.name;
    }

    public boolean getCompleted() {
        return this.completed;
    }

    public void setCompleted() {
        this.completed = true;
        System.out.printf("Nice! I've marked this task as done: \n");
        System.out.println(this.toString());
    }

    public void setUncompleted() {
        this.completed = false;
        System.out.println("OK, I've marked this task as not done yet: ");
        System.out.println(this.toString());
    }

    public String getCheckbox() {
        if (this.completed) {
            return Duke.doneCheckbox;
        } else {
            return Duke.undoneCheckbox;
        }
    }
    abstract String newFormat();
    public String getInt() {
        if (this.completed) {
            return "1";
        } else {
            return "0";
        }
    }
    public String confirmation(int size) {
        String s1 = "Got it. I've added this task:\n";
        String s2 = "Now you have " + size + " tasks in the list.";
        return s1 + this.toString() + "\n" + s2;
    }

    public void removed() {
        String s1 = "Noted. I've removed this task:";
        System.out.println(s1 + "\n" + this.toString());
    }
}
