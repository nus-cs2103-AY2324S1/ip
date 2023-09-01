package jarvis;

public class Task {
    private String title;
    private boolean isCompleted;

    public Task(String title, boolean isCompleted) {
        this.title = title;
        this.isCompleted = isCompleted;
    }

    public String getTitle() {
        return title;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public String getStatusIcon() {
        return (isCompleted ? "1 | " : "0 | ");
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
        return getStatusIcon() + getTitle();
    }
}
