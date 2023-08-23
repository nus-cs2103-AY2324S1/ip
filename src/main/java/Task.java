class Task {
    protected boolean isDone = false;
    protected String name = "";

    public Task(String name) {
        this.name = name;
    }
    public String getStatusIcon() {
        return isDone ? "[X]" : "[ ]";
    }

    public void markTask() {
        this.isDone = true;
        String message = "____________________________________________________________\n" +
                " Nice! I've marked this task as done:\n" +
                "   " + this.getStatusIcon() + " " + this.name + "\n" +
                "____________________________________________________________";
        System.out.println(message);
    }

    public void unMarkTask() {
        this.isDone = false;
        String message = "____________________________________________________________\n" +
                " Nice! I've marked this task as done:\n" +
                "   " + this.getStatusIcon() + " " + this.name + "\n" +
                "____________________________________________________________";
        System.out.println(message);
    }
}