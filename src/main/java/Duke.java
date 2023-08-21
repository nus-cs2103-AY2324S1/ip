public class Duke {
    public static String name = "Dook";
    public static void main(String[] args) {
        GreetUser();
        BidFarewell();
    }

    private static void GreetUser() {
        System.out.println(String.format("%s here.\nWhat can I do for you?", name));
        PrintDivider();
    }
    public static void PrintDivider() {
        System.out.println("_______________________________________");
    }
    private static void BidFarewell() {
        System.out.println("Goodbye.");
        PrintDivider();
    }
}
