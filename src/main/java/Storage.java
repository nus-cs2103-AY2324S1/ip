import java.util.ArrayList;

public class Storage {
    private static ArrayList<Task> storage = new ArrayList<>();

    public static void addToStorage(Task newTask) {
        Storage.storage.add(newTask);
    }

    public static ArrayList<Task> getStorage() {
        return Storage.storage;
    }

    public static int getLength() {
        return Storage.storage.size();
    }

    public static void markAsDone(int index) {
        Storage.storage.get(index).markAsDone();
    }

    public static void unmark(int index) {
        Storage.storage.get(index).unmark();
    }

    public static Task delete(int index) {
        return Storage.storage.remove(index);
    }
}
