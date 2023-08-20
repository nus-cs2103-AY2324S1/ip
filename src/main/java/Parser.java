public class Parser {

    public static void parse(String input, Storage storage) {
        if (input.isBlank()) {
            // do nothing
        } else if (input.startsWith("mark ")) {
            storage.markTaskDone(Integer.valueOf(input.substring(5, 6)));
        } else if (input.startsWith("unmark ")) {
            storage.markTaskNotDone(Integer.valueOf(input.substring(7, 8)));
        } else if (input.equals("list")) {
            storage.list();
        } else {
            storage.add(input);
        }
    }
}
