public class Task {
    protected String description;
    protected boolean isDone;
    protected Type category;

    public Task(String description, String category) throws IllegalArgumentException {
        this.isDone = false;
        if (category.equals("todo")) {
            this.category = Type.T;
            String[] s =description.split(" ", 1);
            try {
                this.description = s[0] + s[1];
            } catch(Exception e) {
                System.out.println("OOPS!!! The description of a todo cannot be empty.");
                System.out.println("This is not in the correct format");
                throw new IllegalArgumentException("Please correct the format");
            }
        } else if (category.equals("deadline")) {
            this.category = Type.D;
            String[] s =description.split("/", 2);
            try {
                this.description = s[0] + "("+ s[1] + ")";
            } catch(Exception e) {
                System.out.println("OOPS!!! The description of a todo cannot be empty.");
                System.out.println("This is not in the correct format");
                throw new IllegalArgumentException("Please correct the format");
            }

        } else if (category.equals("event")){
            String[] s = description.split("/(from|to)", 3);
            this.category = Type.E;
            try {
                this.description = s[0] + "(From : " + s[1] + " To : " + s[2] + ")";
            } catch(Exception e) {
                System.out.println("OOPS!!! The description of a todo cannot be empty.");
                System.out.println("This is not in the correct format");
                throw new IllegalArgumentException("Please correct the format");
            }
        } else {
            throw new IllegalArgumentException("OOPS!!! I'm sorry, but I don't know what that means :-(");
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
