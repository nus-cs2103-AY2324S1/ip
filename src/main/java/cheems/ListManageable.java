package cheems;

public interface ListManageable {
    public void addTaskToDatabase(String... params);
    public void markAsDone(int i);
    public void markAsNotDone(int i);
    public void delete(int i);
    public void displayData();
    public void find(String params);
    public void loadTaskFromDatabase();
    public void taskListToStorage();
}
