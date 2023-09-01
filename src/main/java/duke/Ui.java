package duke;

public class Ui {
    final static String LINE = "____________________________________________________________";

    public static void greet() {
        String logo = "        ┏┓\n"
                + "        ┃┃\n"
                + "        ┃┗━┳┓╋┏┳━━┓\n"
                + "        ┃┏┓┃┃╋┃┃┃━┫\n"
                + "        ┗━━┻━┓┏┻━━┛\n"
                + "        ╋╋╋┏━┛┃\n"
                + "        ╋╋╋┗━━┛\n";
        System.out.println("\nHello! I'm \n" + logo);
        System.out.println("How can I help you? \n" + LINE);
    }

    public static void bye() {
        System.out.println("Bye (actually hehe). Hope to see you again!\n" + LINE);
    }
}
