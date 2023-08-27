public class Reply {
    private String indent = "     ";
    private String section = indent + "________________________________________\n";
    private static Reply reply = null;

    private Reply() {
        //Print intro
        System.out.println(section
                + indent + "Hello! I'm Evan\n"
                + indent + "What can I do for you?\n"
                + section);
    }

    public static Reply init() {
        if (reply == null) {
            reply = new Reply();
        }
        return reply;
    }

    public void printDialog(String dialog) {
        System.out.println(section + indent + dialog + "\n" + section);
    }
}
