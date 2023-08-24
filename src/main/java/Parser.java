public class Parser {
    public static String[] splitSpace (String str) {
        return str.split(" ", 2);
    }

    public static String[] splitEvent (String str) {
        String[] res = new String[3];
        String noCommand = Parser.splitSpace(str)[1];
        String[] temp = noCommand.split("/from ", 2);
        res[0] = temp[0];
        String[] temp2 = temp[1].split("/to ", 2);
        res[1] = temp2[0];
        res[2] = temp2[1];
        return res;
    }

    public static String[] splitDeadline (String str) {
        String noCommand = Parser.splitSpace(str)[1];
        return noCommand.split("/by ", 2);
    }
}
