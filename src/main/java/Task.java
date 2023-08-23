public class Task {

    private String description;
    private boolean status;

    private String indent = "    ";
    public Task(String description) {
        this.description = description;
        this.status = false;
    }

    public String getStatus() {
        return this.status ? "X" : " ";
    }

    public String getDescription() {
        return "[" + this.getStatus() + "] " + this.description;
    }

    public String changeStatus(String userInput) {
        if (!this.status && userInput.equals("mark")) {
            this.status = !this.status;
            return "Nice! Task completed successfully!" + "\n" + indent + this.getDescription();
        } else if (!this.status && userInput.equals("unmark")) {
            return "Task already unmarked! Please try again..." + "\n" + indent + this.getDescription();
        } else if (this.status && userInput.equals("unmark")) {
            this.status = !this.status;
            return "Sure! Task status unchecked!" + "\n" + indent + this.getDescription();
        } else {
            return "Task already unchecked. Please try again..." + "\n" + indent + this.getDescription();
        }
    }
}
