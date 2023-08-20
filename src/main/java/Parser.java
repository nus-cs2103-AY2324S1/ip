public class Parser {

    public static void parse(String input, Storage storage) {
        if (input.isBlank()) {
            return;
        }
        String[] inputArr = input.split(" ");
        String description;
        switch(inputArr[0]) {
            case "mark":
                storage.markTaskDone(Integer.parseInt(inputArr[1]));
                break;
            case "unmark":
                storage.markTaskNotDone(Integer.parseInt(inputArr[1]));
                break;
            case "list":
                storage.list();
                break;
            case "todo":
                description = input.substring(5);
                storage.add(description);
                break;
            case "deadline":
                description = input.substring(8, input.indexOf("/"));
                String by = input.split("/by")[1];
                storage.add(description, by);
                break;
            case "event":
                description = input.substring(6, input.indexOf("/"));
                String from = input.split("/from")[1].split("/to")[0];
                String to = input.split("/to")[1];
                storage.add(description, from, to);
                break;
            default:
                storage.add(input);
                break;
        }
    }
}
