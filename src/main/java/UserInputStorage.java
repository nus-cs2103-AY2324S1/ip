public class UserInputStorage {
    private static String[] userinputs = new String[100];
    private static int storagePointer = 0;

    public static void store(String userInput) {
        userinputs[storagePointer] = userInput;
        storagePointer++;
    }

    public static String printAllContent() {
        String tobePrinted = "";
        for (int i = 0; i < storagePointer; i++) {
            tobePrinted = tobePrinted + (i + 1) + ". " +  userinputs[i] + "\n";
        }

        return tobePrinted;
    }
}
