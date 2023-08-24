public class Crusader {
    /** Logo generated from https://patorjk.com/software/taag */
    private static final String logo =
              "   _____                          _\n"
            + "  / ____|                        | |\n"
            + " | |     _ __ _   _ ___  __ _  __| | ___ _ __\n"
            + " | |    | '__| | | / __|/ _` |/ _` |/ _ \\ '__|\n"
            + " | |____| |  | |_| \\__ \\ (_| | (_| |  __/ |\n"
            + "  \\_____|_|   \\__,_|___/\\__,_|\\__,_|\\___|_|\n";

    private static void showLogo() {
        System.out.println(logo);
    }

    /**
     * Generates a horizontal line to divide parts of the conversation.
     */
    private static void addDivider() {
        System.out.println("____________________________________________________________\n");
    }
    public static void main(String[] args) {
        addDivider();
        showLogo();
        addDivider();
    }
}
