package command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

import dukeexceptions.DukeDateTimeParseException;
import dukeexceptions.DukeException;
import dukeexceptions.DukeFromEarlierThanToException;
import dukeexceptions.DukeNullPointerException;
import dukeexceptions.DukeNumberFormatException;
import dukeexceptions.DukeUnknownCommandException;
import parser.Parser;
import task.ListOfTask;

/**
 * This class test the Commands class and thus test the Parser class and ListOfTask class.
 */
public class CommandsTest {

    @Test
    public void commandsExecuteToDo() {
        String[] cmd = {"todo CS2103T A-JUnit", "todo "};
        String cmd2 = "CS2103T A-JUnit";
        String cmd3 = "added: [T][ ] ";
        for (int i = 0; i < cmd.length; i++) {
            Parser cm = new Parser(cmd[i]);
            ByteArrayOutputStream outContent = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent));
            try {
                Commands c = cm.parse();
                assertEquals(cmd3 + cmd2, c.execute(new task.ListOfTask()));
            } catch (DukeException e) {
                assertEquals("Please add the task name\r\n", e.getMessage() + "\r\n");
            }
        }
    }


    @Test
    public void commandsExecuteDeadline() {
        String[] cmd = {"deadline CS2103T A-JUnit /by 18-09-2023 0000",
            "deadline gyrdefsf /by 8411",
            "deadline gyrdefsf /by ", "deadline /b"};
        String cmd2 = "CS2103T A-JUnit";
        String cmd3 = "added: [D][ ] ";
        String cmd4 = " (by: 18-09-2023 0000)";
        for (String str : cmd) {
            Parser cm = new Parser(str);
            final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent));
            try {
                Commands c = cm.parse();
                assertEquals(cmd3 + cmd2 + cmd4, c.execute(new task.ListOfTask()));
            } catch (DukeDateTimeParseException e) {
                assertEquals("The format for dates&time is 'dd-MM-yyyy hhmm'", e.getMessage());
            } catch (DukeNullPointerException e) {
                assertEquals("The format for the command is: deadline task /by date&time", e.getMessage());
            } catch (DukeException e) {
                assertEquals("Please add the task name", e.getMessage());
            }
        }
    }

    @Test
    public void commandsExecuteEvent() {
        String[] cmd = {"event CS2103T A-JUnit /from 18-09-2023 0000 /to 18-09-2024 0000",
            "event CS2103T A-JUnit /from 18-09-2023 0000 /to 18-09-2023 0000",
            "event CS2103T A-JUnit /from 18-09-2023 0000 /to 18-09-2024 9999",
            "event CS2103T A-JUnit /from 18-09-2023 0000 /to 18-09-2024 ",
            "event CS2103T A-JUnit /from 18-09-2023 /to 18-09-2024 0000",
            "event CS2103T A-JUnit /from 18-09-2023 0000 /to",
            "event CS2103T A-JUnit /from 18-09-2023 0000 /t 18-09-2024 0000",
            "event CS2103T A-JUnit /from 18-09-2023 0000 / 18-09-2024 0000",
            "event CS2103T A-JUnit /from 18-09-2023 0000 18-09-2024 0000",
            "event CS2103T A-JUnit /from 18-09-2023 0000",
            "event CS2103T A-JUnit /from 18-09-2023 000",
            "event CS2103T A-JUnit /from 18-09-2023 9999",
            "event CS2103T A-JUnit /from 18-09 1010",
            "event CS2103T A-JUnit /from",
            "event CS2103T A-JUnit /fro",
            "event CS2103T A-JUnit /from /to ",
            "event CS2103T A-JUnit"};
        String cmd2 = "CS2103T A-JUnit";
        String cmd3 = "added: [E][ ] ";
        String cmd4 = " (from: 18-09-2023 0000 to: 18-09-2024 0000)";
        for (String str : cmd) {
            Parser cm = new Parser(str);
            final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent));
            try {
                Commands c = cm.parse();
                assertEquals(cmd3 + cmd2 + cmd4, c.execute(new task.ListOfTask()));
            } catch (DukeFromEarlierThanToException e) {
                assertEquals("From must be earlier than To", e.getMessage());
            } catch (DukeDateTimeParseException e) {
                assertEquals("The format for dates&time is 'dd-MM-yyyy hhmm'", e.getMessage());
            } catch (DukeNullPointerException e) {
                assertEquals("The format for the command is: "
                        + "event task /from startDayDateTime /to endDayDateTime",
                        e.getMessage());
            } catch (DukeException e) {
                assertEquals("Please add the task name", e.getMessage());
            }
        }
    }

    @Test
    public void commandsExecuteMark() {
        String[] cmd = {"todo CS2103T A-JUnit",
            "deadline CS2103T A-JUnit /by 18-09-2023 0000",
            "event CS2103T A-JUnit /from 18-09-2023 0000 /to 18-09-2024 0000"};
        String[] cmdi = {"[T][X] CS2103T A-JUnit",
            "[D][X] CS2103T A-JUnit (by: 18-09-2023 0000)",
            "[E][X] CS2103T A-JUnit (from: 18-09-2023 0000 to: 18-09-2024 0000)"};
        ListOfTask taskList = new ListOfTask();
        for (String str : cmd) {
            Parser cm = new Parser(str);
            try {
                Commands c = cm.parse();
                c.execute(taskList);
            } catch (DukeException e) {
                System.out.println("check the test cases again");
            }
        }
        int[] indexes = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        for (int i = 0; i < indexes.length; i++) {
            try {
                final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
                System.setOut(new PrintStream(outContent));
                String str = taskList.markOrUnmark(indexes[i] + 1, true, true);
                assertEquals(cmdi[i], str);
            } catch (DukeNumberFormatException e) {
                assertEquals("Place a number after the command", e.getMessage());
            } catch (DukeException e) {
                assertEquals("Please select from index 1 to " + taskList.size(), e.getMessage());
            }
        }

        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String s = "mark a";
        Parser p = new Parser(s);
        try {
            Commands c = p.parse();
        } catch (DukeException e) {
            assertEquals("Place a number after the command", e.getMessage());
        }
    }

    @Test
    public void commandsExecuteUnMark() {
        String[] cmd = {"todo CS2103T A-JUnit",
            "mark 1",
            "deadline CS2103T A-JUnit /by 18-09-2023 0000",
            "mark 2",
            "event CS2103T A-JUnit /from 18-09-2023 0000 /to 18-09-2024 0000",
            "mark 3"};
        String[] cmdi = {"[T][ ] CS2103T A-JUnit",
            "[D][ ] CS2103T A-JUnit (by: 18-09-2023 0000)",
            "[E][ ] CS2103T A-JUnit (from: 18-09-2023 0000 to: 18-09-2024 0000)"};
        ListOfTask taskList = new ListOfTask();
        for (String str : cmd) {
            Parser cm = new Parser(str);
            try {
                Commands c = cm.parse();
                c.execute(taskList);
            } catch (DukeException e) {
                System.out.println("check the test cases again");
            }
        }
        int[] indexes = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        for (int i = 0; i < indexes.length; i++) {
            try {
                final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
                System.setOut(new PrintStream(outContent));
                String str = taskList.markOrUnmark(indexes[i] + 1, false, true);
                assertEquals(cmdi[i], str);
            } catch (DukeNumberFormatException e) {
                assertEquals("Place a number after the command", e.getMessage());
            } catch (DukeException e) {
                assertEquals("Please select from index 1 to " + taskList.size(), e.getMessage());
            }
        }


        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String s = "unmark a";
        Parser p = new Parser(s);
        try {
            Commands c = p.parse();
            assertEquals("Place a number after the command", outContent.toString());
        } catch (DukeException e) {
            assertEquals("Place a number after the command", e.getMessage());
        }
    }

    @Test
    public void commandsExecuteDelete() {
        String[] cmd = {"todo CS2103T A-JUnit",
            "deadline CS2103T A-JUnit /by 18-09-2023 0000",
            "event CS2103T A-JUnit /from 18-09-2023 0000 /to 18-09-2024 0000"};
        String[] cmdi = {"[T][ ] CS2103T A-JUnit has been deleted",
            "[D][ ] CS2103T A-JUnit (by: 18-09-2023 0000) has been deleted",
            "[E][ ] CS2103T A-JUnit (from: 18-09-2023 0000 to: 18-09-2024 0000) has been deleted"};
        ListOfTask taskList = new ListOfTask();
        for (String str : cmd) {
            Parser cm = new Parser(str);
            try {
                Commands c = cm.parse();
                c.execute(taskList);
            } catch (DukeException e) {
                System.out.println("check the test cases again");
            }
        }
        for (int i = 0; i < 3; i++) {
            try {
                final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
                System.setOut(new PrintStream(outContent));
                assertEquals(cmdi[i], taskList.delete(1));
            } catch (DukeNumberFormatException e) {
                assertEquals("Place a number after the command", e.getMessage());
            } catch (DukeException e) {
                assertEquals("Please select from index 1 to " + taskList.size(), e.getMessage());
            }
        }

        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String s = "mark a";
        Parser p = new Parser(s);
        try {
            Commands c = p.parse();
        } catch (DukeException e) {
            assertEquals("Place a number after the command", e.getMessage());
        }
    }

    @Test
    public void commandsExecuteList() {
        String[] cmd = {"todo CS2103T A-JUnit",
            "deadline CS2103T A-JUnit /by 18-09-2023 0000",
            "event CS2103T A-JUnit /from 18-09-2023 0000 /to 18-09-2024 0000"};
        String cmdi = "1.[T][ ] CS2103T A-JUnit\n"
            + "2.[D][ ] CS2103T A-JUnit (by: 18-09-2023 0000)\n"
            + "3.[E][ ] CS2103T A-JUnit (from: 18-09-2023 0000 to: 18-09-2024 0000)\n";
        ListOfTask taskList = new ListOfTask();
        for (String str : cmd) {
            Parser cm = new Parser(str);
            try {
                Commands c = cm.parse();
                c.execute(taskList);
            } catch (DukeException e) {
                System.out.println("check the test cases again");
            }
        }
        String[] cmdj = {"l", "list ", "list"};
        for (String str : cmdj) {
            final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent));
            Parser p = new Parser(str);
            try {
                Commands c = p.parse();
                assertEquals(cmdi, c.execute(taskList));
            } catch (DukeException e) {
                assertEquals("Unknown command", e.getMessage());
            }
        }
    }

    @Test
    public void commandsExecuteFind() {
        String[] cmd = {"todo CS2103T A-JUnit",
            "deadline CS2103T B-JUnit /by 18-09-2023 0000",
            "event CS2103T C-JUnit /from 18-09-2023 0000 /to 18-09-2024 0000"};
        String[] cmdi = {"3.[E][ ] CS2103T C-JUnit (from: 18-09-2023 0000 to: 18-09-2024 0000)\n",
            "2.[D][ ] CS2103T B-JUnit (by: 18-09-2023 0000)\n",
            "1.[T][ ] CS2103T A-JUnit\n",
            "Whoopys uWu, sorry I couldnyt fynd any taysk that contyain that strying. XD uWu\n"};
        ListOfTask taskList = new ListOfTask();
        for (String str : cmd) {
            Parser cm = new Parser(str);
            try {
                Commands c = cm.parse();
                c.execute(taskList);
            } catch (DukeException e) {
                System.out.println("check the test cases again");
            }
        }

        String[] cmdj = {"f", "find", "find ", "find C-JUnit", "find B", "find A", "find d"};
        for (int i = 0; i < cmdj.length; i++) {
            try {
                final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
                System.setOut(new PrintStream(outContent));
                Parser p = new Parser(cmdj[i]);
                Commands c = p.parse();
                assertEquals(cmdi[i - 3], c.execute(taskList));
            } catch (DukeUnknownCommandException e) {
                assertEquals("Unknown command", e.getMessage());
            } catch (DukeException e) {
                assertEquals("Please add the task name", e.getMessage());
            }
        }

    }
}
