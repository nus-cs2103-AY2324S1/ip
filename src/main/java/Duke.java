public class Duke {
    private static void start() {
        System.out.println("____________________________________________________________\n");
        String logo = "  __ _  ___ ___  _   _  _   _  \n" +
                " / _/ \\| o \\_ _|/ \\ | \\| | / \\ \n" +
                "( (( o )   /| || o || \\\\ || o |\n" +
                " \\__\\_/|_|\\\\|_||_n_||_|\\_||_n_|";
        System.out.println("Hello I'm Cortana, Microsoft killed me so now I'm here\n" + logo);
        System.out.println("____________________________________________________________\n");
    }

    private static void exit() {
        System.out.println("Bye\n");
        System.out.println("____________________________________________________________\n");
    }

    public static void main(String[] args) {
        start();
        exit();
    }
}
