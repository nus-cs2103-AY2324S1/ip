import java.util.Scanner;

import static java.lang.Integer.parseInt;
public class Haste {
    public static final String LINE = "＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿";
    public static final String INDENT = "    ";

    public static void main(String[] args) {
        Ui ui = new Ui();
        ui.greet();
        Scanner sc = new Scanner(System.in);
        while (ui.running) {
            String cmd = sc.nextLine();
            //System.out.println(cmd);
            Parser.handleCommand(cmd, ui);

        }

    }


}
