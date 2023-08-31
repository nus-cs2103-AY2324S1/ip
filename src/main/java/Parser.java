public class Parser {
    public static String[] parse(String input) {
        String[] inputSplit = input.split(" ", 2);
        String command = inputSplit[0];
        String data = inputSplit.length == 2 ? inputSplit[1] : "";
        return new String[] {command, data};
    }
}
