package jarvis;

public class Task {
    private String title;
    private boolean isCompleted;

    public Task(String title) {
        this.title = title;
        isCompleted = false;
    }

    public String getTitle() {
        return title;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public String getStatusIcon() {
        return (isCompleted ? "[X]" : "[ ]");
    }

   public String getCatIcon() {
        return null;
   }

    public void markCompleted() {
        isCompleted = true;
    }

    public void unmarkCompleted() {
        isCompleted = false;
    }

    public String toString() {
        return getStatusIcon() + " " + getTitle();
    }
}
