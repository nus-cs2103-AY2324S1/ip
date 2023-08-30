public class Task {

    private String description;
    private boolean status;

    private String indent = "    ";
    public Task(String description) {
        this.description = description;
        this.status = false;
    }
    public Task(String description, boolean status) {
        this.description = description;
        this.status = status;
    }



    public String changeStatus(String userInput) {
        if (!this.status && userInput.equals("mark")) {
            this.status = !this.status;
            return "Nice! Task completed successfully!" + "\n" + indent + this.toString();
        } else if (!this.status && userInput.equals("unmark")) {
            return "Task already unmarked! Please try again..." + "\n" + indent + this.toString();
        } else if (this.status && userInput.equals("unmark")) {
            this.status = !this.status;
            return "Sure! Task status unchecked!" + "\n" + indent + this.toString();
        } else {
            return "Task already unchecked. Please try again..." + "\n" + indent + this.toString();
        }
    }

    public int getStatus() {
        return this.status ? 1 : 0;
    }
    public String getDescription() {
        return this.description;
    }

    public void storeDescription(String description) {
        this.description = description;
    }

    public String storeToDisk() {
        return this.getStatus() + "|" + this.description;
    }

    @Override
    public String toString() {
        if (this.getStatus() == 1) {
            return "[" + "X" + "] " + this.description;
        } else {
            return "[" + " " + "]" + this.description;
        }
    }
}
