public class Storage {
    private static Task[] storage = new Task[100];
    private static int length = 0;

    public static void addToStorage(String str) {
        Task newTask = new Task(str);
        Storage.storage[Storage.length] = newTask;
        Storage.length++;
    }

    public static Task[] getStorage() {
        return Storage.storage;
    }

    public static int getLength() {
        return Storage.length;
    }

    public static void markAsDone(int index) {
        Storage.storage[index].markAsDone();
    }

    public static void unmark(int index) {
        Storage.storage[index].unmark();
    }
}
