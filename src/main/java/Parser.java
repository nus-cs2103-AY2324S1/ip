public class Parser {

    public static void parse(String input, Storage storage) {
        if (input.equals("list")) {
            storage.list();
        } else {
            storage.add(input);
        }
    }
}
