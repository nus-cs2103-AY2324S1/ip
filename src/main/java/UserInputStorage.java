public class UserInputStorage {
    private static Task[] userinputs = new Task[100];
    private static int storagePointer = 0;

    public static void store(Task task) {
        userinputs[storagePointer] = task;
        storagePointer++;
    }

    public static Task getTaskByIndex(int index) {
        return userinputs[index - 1];
    }

    public static String printAllContent() {
        String tobePrinted = "";
        for (int i = 0; i < storagePointer; i++) {
            tobePrinted = tobePrinted + (i + 1) + ". " + userinputs[i].toString() + "\n";
        }
        return tobePrinted;
    }

    public static int getNumOfElement() {
        return storagePointer;
    }
}
