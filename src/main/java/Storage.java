public class Storage {
    private static String[] storage = new String[100];
    private static int length = 0;

    public static void addToStorage(String str) {
        Storage.storage[Storage.length] = str;
        Storage.length++;
    }

    public static String[] getStorage() {
        return Storage.storage;
    }

    public static int getLength() {
        return Storage.length;
    }
}
