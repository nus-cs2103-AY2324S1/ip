
package benben;

import static java.util.Objects.isNull;

/**
 * The type Parser parses the comment to the action that need to be taken
 */
public class Parser {

    /**
     * Parses the command and tells benben what to do next
     *
     * @param bb   the BenBen Object
     * @param next the command
     * @throws BenBenException if the command cannot be recognised
     */
    public static String parse(BenBen bb, String next) throws BenBenException {

        if (next.equals("bye")) {
            return bb.exit();
        }

        if (next.equals("list")) {
            return bb.iterList();
        }

        if (next.startsWith("mark")) {
            return bb.mark(next);
        }

        if (next.startsWith("unmark")) {
            return bb.unmark(next);
        }

        if (next.startsWith("todo")) {
            return bb.todo(next);
        }

        if (next.startsWith("deadline")) {
            return bb.deadline(next);
        }

        if (next.startsWith("event")) {
            return bb.event(next);
        }

        if (next.startsWith("delete")) {
            return bb.remove(next);
        }

        if (next.startsWith("find")) {
            return bb.find(next);
        }

        if (next.startsWith("exit")) {
            return bb.exit();
        }

        if (next.startsWith("sort")) {
            return bb.sort(next);
        }

            throw new BenBenException("BenBen does not understand your instruction:(");
    }

    public static Task parseFromFile(String[] args) throws BenBenException{
        Task newTask = null;
        String type = args[0];
        String status = args[1];
        switch (type) {
            case "T":
                assert args.length == 3;
                newTask = (Task) new Todo(args[2]);
                    break;
            case "D":
                assert args.length == 4;
                newTask = (Task) new Deadline(args[2], args[3]);
                    break;
            case "E":
                assert args.length == 5;
                newTask = (Task) new Event(args[2], args[3], args[4]);
                    break;
            default:
                throw new BenBenException("The file content is corrupted, please report this to admin");
        }

        switch (status) {
            case "1":
                assert isNull(newTask);
                newTask.mark();
                break;
            case "0":
                assert isNull(newTask);
                break;
            default:
                throw new BenBenException("The file content is corrupted, please report this to admin");
        }

        return newTask;
    }

    public static String[] getArrayFromFile(String ln) {
        String[] strSplit = ln.split("\\|");
        assert strSplit.length >= 3;
        for (int i = 0; i < strSplit.length; i++) {
            strSplit[i] = strSplit[i].trim();
        }
        return strSplit;
    }



    public static Task parseTodo(String str) throws BenBenException {
        String[] strSplit = str.split("\\s+");
        StringBuilder des = new StringBuilder("");

        for (int i = 1; i < strSplit.length; i++) {
            des.append(" ").append(strSplit[i]);
        }

        if (des.length() == 0) {
            throw new BenBenException("Please follow the format: todo todo details");
        }

        String desString = des.toString().trim();
        Task t = (Task) new Todo(desString);
        return t;
    }

    public static Task parseDeadline (String str) throws BenBenException {
        String[] strSplit = str.split("\\s+");
        StringBuilder des = new StringBuilder("");
        StringBuilder ddl= new StringBuilder("");
        String field = "des";

        for (int i = 1; i < strSplit.length; i++) {
            if (strSplit[i].equals("/by")) {
                field = "ddl";
                continue;
            }
            switch (field) {
                case "des":
                    des.append(" ").append(strSplit[i]);
                    break;
                case "ddl":
                    ddl.append(" ").append(strSplit[i]);
                    break;
            }
        }

        if (des.length() == 0 || ddl.length() == 0) {
            throw new BenBenException("Please follow the format: deadline description /by ddl");
        }

        String desString = des.toString().trim();
        String ddlString = ddl.toString().trim();
        Task t = (Task) new Deadline(desString, ddlString);
        return t;
    }

    public static Task parseEvent(String str) throws BenBenException {
        String[] strSplit = str.split("\\s+");
        StringBuilder des = new StringBuilder("");
        StringBuilder start = new StringBuilder("");
        StringBuilder end = new StringBuilder("");
        String field = "des";

        for (int i = 1; i < strSplit.length; i++) {
            if (strSplit[i].equals("/from")) {
                field = "start";
                continue;
            }
            if (strSplit[i].equals("/to")) {
                field = "end";
                continue;
            }
            switch (field) {
                case "des":
                    des.append(" ").append(strSplit[i]);
                    break;
                case "start" :
                    start.append(" ").append((strSplit[i]));
                    break;
                case "end" :
                    end.append(" ").append(strSplit[i]);
                    break;
            }
        }

        if (des.length() == 0 || start.length() == 0 || end.length() == 0) {
            throw new BenBenException("Please follow the format: event description /from start /to end");
        }

        String desString = des.toString().trim();
        String startString = start.toString().trim();
        String endString = end.toString().trim();
        Task t = (Task) new Event(desString, startString, endString);
        return t;
    }
}
