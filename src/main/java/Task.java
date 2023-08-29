abstract class Task {
    private String name;
    private boolean completed;
    public static String doneCheckbox = "[X] ";
    public static String undoneCheckbox = "[ ] ";

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
    }

    public void setUncompleted() {
        this.completed = false;
    }

    public String getCheckbox() {
        if (this.completed) {
            return Task.doneCheckbox;
        } else {
            return Task.undoneCheckbox;
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

    public String removed() {
        String s1 = "Noted. I've removed this task:";
        return(s1 + "\n" + this.toString());
    }

    public void execute(TaskList lst, Ui ui, Storage storage) {};
}
