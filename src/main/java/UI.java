public class UI {
    public void showWelcomeMsg() {
        String logo = "\n" +
                "         __                                             \n" +
                "        [  |                                            \n" +
                " .---.   | |--.    .---.   .---.   _ .--..--.    .--.   \n" +
                "/ /'`\\]  | .-. |  / /__\\\\ / /__\\\\ [ `.-. .-. |  ( (`\\]  \n" +
                "| \\__.   | | | |  | \\__., | \\__.,  | | | | | |   `'.'.  \n" +
                "'.___.' [___]|__]  '.__.'  '.__.' [___||__||__] [\\__) ) \n" +
                "                                                        \n";
        String hello = "Heyo I'm Cheems! Nice to meet you:)" + "\n" + "Want to get some fries on the pier together?";

        System.out.println(logo);
        System.out.print("Cheems: ");
        System.out.println(hello);
    }

    public void showExitMsg() {
        String bye = "Cheems: Okay bye:( Let's get the fries next time.";
        System.out.println(bye);
    }
}
