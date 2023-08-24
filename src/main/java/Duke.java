public class Duke {
    public static void main(String[] args) {
        hello_world();
        goodbye_world();
    }

    public static void hello_world(){
        System.out.println("Hello! I'm");
        printName();
        System.out.println("What can I do for you?");
        print_divider_line();
    }

    public static void goodbye_world(){
        System.out.println("Bye. Hope to see you again soon!");
        print_thank_you();
        print_divider_line();
    }

    public static void print_divider_line() {
        System.out.println("═".repeat(80));
    }
    public static void printName() {
        String name = "\n"+
                "     ██╗██╗███╗   ██╗ ██████╗     ███████╗██╗  ██╗███████╗███╗   ██╗ ██████╗ \n" +
                "     ██║██║████╗  ██║██╔════╝     ██╔════╝██║  ██║██╔════╝████╗  ██║██╔════╝ \n" +
                "     ██║██║██╔██╗ ██║██║  ███╗    ███████╗███████║█████╗  ██╔██╗ ██║██║  ███╗\n" +
                "██   ██║██║██║╚██╗██║██║   ██║    ╚════██║██╔══██║██╔══╝  ██║╚██╗██║██║   ██║\n" +
                "╚█████╔╝██║██║ ╚████║╚██████╔╝    ███████║██║  ██║███████╗██║ ╚████║╚██████╔╝\n" +
                " ╚════╝ ╚═╝╚═╝  ╚═══╝ ╚═════╝     ╚══════╝╚═╝  ╚═╝╚══════╝╚═╝  ╚═══╝ ╚═════╝ \n" ;
        System.out.print(name);
    }

    public static void print_thank_you() {
        String thankYou = "\n" +
                "████████╗██╗  ██╗ █████╗ ███╗   ██╗██╗  ██╗    ██╗   ██╗ ██████╗ ██╗   ██╗\n" +
                "╚══██╔══╝██║  ██║██╔══██╗████╗  ██║██║ ██╔╝    ╚██╗ ██╔╝██╔═══██╗██║   ██║\n" +
                "   ██║   ███████║███████║██╔██╗ ██║█████╔╝      ╚████╔╝ ██║   ██║██║   ██║\n" +
                "   ██║   ██╔══██║██╔══██║██║╚██╗██║██╔═██╗       ╚██╔╝  ██║   ██║██║   ██║\n" +
                "   ██║   ██║  ██║██║  ██║██║ ╚████║██║  ██╗       ██║   ╚██████╔╝╚██████╔╝\n" +
                "   ╚═╝   ╚═╝  ╚═╝╚═╝  ╚═╝╚═╝  ╚═══╝╚═╝  ╚═╝       ╚═╝    ╚═════╝  ╚═════╝\n";
        System.out.print(thankYou);
    }
}