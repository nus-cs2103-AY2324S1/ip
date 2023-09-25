public class Parser {
    public static String[] splitSpace (String str) {
        return str.split(" ", 2);
    }

    public static String[] splitEvent (String str) {
        String[] res = new String[3];
        String noCommand = Parser.splitSpace(str)[1];
        String[] temp = noCommand.split("/from ", 2);
        res[0] = temp[0];
        if (temp.length < 2) {
            res[1] = "";
            res[2] = "";
            return res;
        }
        String[] temp2 = temp[1].split("/to ", 2);
        res[1] = temp2[0];
        if (temp2.length < 2) {
            res[2] = "";
            return res;
        }
        res[2] = temp2[1];

        //removes leading and trailing spaces
        for (int i = 0; i < res.length; i++) {
            res[i] = res[i].trim();
        }

        return res;
    }

    public static String[] splitDeadline (String str) {
        String noCommand = Parser.splitSpace(str)[1];
        return noCommand.split("/by ", 2);
    }
}
