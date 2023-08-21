public class Duke {
    private static final String divider = "____________________________________________________________\n";
    public static void main(String[] args) {
        String logo = ".-. .-')              .-. .-')   \n" +
                "\\  ( OO )             \\  ( OO )  \n" +
                " ;-----.\\  .-'),-----. ;-----.\\  \n" +
                " | .-.  | ( OO'  .-.  '| .-.  |  \n" +
                " | '-' /_)/   |  | |  || '-' /_) \n" +
                " | .-. `. \\_) |  |\\|  || .-. `.  \n" +
                " | |  \\  |  \\ |  | |  || |  \\  | \n" +
                " | '--'  /   `'  '-'  '| '--'  / \n" +
                " `------'      `-----' `------'  ";
        String welcomeMessage = "Hi, I'm Bob. How can I help you?";
        String goodbyeMessage = "Goodbye! Bob signing out!";

        System.out.println(divider + logo + "\n" + welcomeMessage);
        System.out.println(divider + goodbyeMessage + "\n" + divider);
    }
}
