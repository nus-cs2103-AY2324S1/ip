public class Duke {
    public static void main(String[] args) {
        String line = "____________________________________________________________\n";
        String logo = "  _  ._ _   _  \n"
                + " (_) | (/_ (_) \n";
        String greet = line + logo
                + "Woof! I'm Oreo! How may I help you?\n"
                + line;
        String exit = "I will be sad to see you go! bye!\n" + line;

        System.out.println(greet + exit);
    }
}
