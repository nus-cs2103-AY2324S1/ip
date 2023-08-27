import exceptions.UnknownCommandException;

public class Parser {

//    private static String processInput(String userInput) {
//        String[] inputArr = userInput.trim().split(" ");
//        String restOfInput;
//
//        if (inputArr.length == 0) {
//            throw new UnknownCommandException();
//        }
//
//        String command = inputArr[0];
//        switch(command) {
//            case "list":
//                return Duke.list.toString();
//            case "mark":
//                restOfInput = userInput.trim().substring(4).trim();
//                return mark(restOfInput);
//            case "unmark":
//                restOfInput = userInput.trim().substring(6).trim();
//                return unmark(restOfInput);
//            case "delete":
//                restOfInput = userInput.trim().substring(6).trim();
//                return deleteTask(restOfInput);
//            case "todo":
//                restOfInput = userInput.trim().substring(4).trim();
//                return createTodo(restOfInput);
//            case "deadline":
//                restOfInput = userInput.trim().substring(8).trim();
//                return createDeadline(restOfInput);
//            case "event":
//                restOfInput = userInput.trim().substring(5).trim();
//                return createEvent(restOfInput);
//            default:
//                throw new UnknownCommandException();
//        }
//    }

}
