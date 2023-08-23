public class Duke {
    public static void main(String[] args) {
        String logo = " ___  _ _                  ___  _          _    ___        _    ___ \n"
                + "|  _|| | | ___  _ _  _ _  |  _>| |_  ___ _| |_ | . > ___ _| |_ |_  |\n"
                + "| |  \\   // . \\| | || '_> | <__| . |<_> | | |  | . \\/ . \\ | |    | |\n"
                + "| |_  |_| \\___/`___||_|   `___/|_|_|<___| |_|  |___/\\___/ |_|   _| |\n"
                + "|___|                                                          |___|\n";

        String greet = " Hello! I'm\n" + logo + "What can I do for you?\n";
        System.out.println(greet);

        String sendOff = "Bye. Hope to see you again soon!";
        System.out.println(sendOff);
    }
}