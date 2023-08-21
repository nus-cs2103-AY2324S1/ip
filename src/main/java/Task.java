public class Task {
    protected String description;
    protected boolean isDone;
    protected Type category;

    public Task(String description, String category) {
        this.isDone = false;
        if (category.equals("todo")) {
            this.category = Type.T;
            this.description = description;
        } else if (category.equals("deadline")) {
            this.category = Type.D;
            String[] s =description.split("/", 2);
            this.description = s[0] + "("+ s[1] + ")";
        } else {
            String[] s = description.split("/(from|to)", 3);
            this.category = Type.E;
            this.description = s[0] + "(From : " + s[1] + " To : " + s[2] + ")";
        }
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getStatus() {
        return "[" + this.category +"]" + "["+ this.getStatusIcon() + "] " + this.description;
    }

    public void setDescription(String desc) {
        this.description = desc;
    }

    public String mark() {
        this.isDone = true;
        return "["+ this.getStatusIcon() + "] " + this.description;
    }

}
