package duke;

import duke.exception.InvalidFindingException;
import duke.exception.InvalidInputException;
import duke.exception.InvalidMarkingException;
import duke.exception.InvalidRankingException;
import duke.exception.LackDescriptionException;
import duke.exception.LackInformationException;
import duke.stub.TaskListStub;

import org.junit.jupiter.api.Test;

import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


public class ParserTest {
    @Test
    public void parse_listButEmptyList_emptyMessage() {
        Parser parser = new Parser(new TaskListStub(), new Ui()); // The implementation of Ui is simple enough, unnecessary to create a UiStub
        assertEquals("list is empty :(", parser.parse("list"));
    }

    @Test
    public void parse_listNonEmptyList_listPrinted() {
        Parser parser = new Parser(new TaskListStub(false, true), new Ui());
        assertEquals("\n1 [ ] task1\n" + "2 [ ] task2\n" + "3 [ ] task3", parser.parse("list") );
    }

    @Test
    public void parse_markNoIndex_invalidMarkingExceptionThrown() {
        Parser parser = new Parser(new TaskListStub(), new Ui());
        try {
            parser.parse("mark");
            fail();
        } catch (InvalidMarkingException e) {
            assertEquals("Missing index", e.getMessage());
        }
    }

    @Test
    public void parse_unmarkNoIndex_invalidMarkingExceptionThrown() {
        Parser parser = new Parser(new TaskListStub(), new Ui());
        try {
            parser.parse("unmark");
            fail();
        } catch (InvalidMarkingException e) {
            assertEquals("Missing index", e.getMessage());
        }
    }

    @Test
    public void parse_markNonInteger_invalidMarkingExceptionThrown() {
        Parser parser = new Parser(new TaskListStub(), new Ui());
        try {
            parser.parse("mark nonInteger");
            fail();
        } catch (InvalidMarkingException e) {
            assertEquals("Please provide a valid index", e.getMessage());
        }
    }

    @Test
    public void parse_unmarkNonInteger_invalidMarkingExceptionThrown() {
        Parser parser = new Parser(new TaskListStub(), new Ui());
        try {
            parser.parse("unmark nonInteger");
            fail();
        } catch (InvalidMarkingException e) {
            assertEquals("Please provide a valid index", e.getMessage());
        }
    }

    @Test
    public void parse_markInvalidInteger_invalidMarkingExceptionThrown() {
        Parser parser = new Parser(new TaskListStub(), new Ui());
        try {
            parser.parse("mark 4"); //TaskListStub only has three tasks
            fail();
        } catch (InvalidMarkingException e) {
            assertEquals("There is no corresponding task in the list", e.getMessage());
        }

        try {
            parser.parse("mark 0");
            fail();
        } catch (InvalidMarkingException e) {
            assertEquals("There is no corresponding task in the list", e.getMessage());
        }

        try {
            parser.parse("mark -1");
            fail();
        } catch (InvalidMarkingException e) {
            assertEquals("There is no corresponding task in the list", e.getMessage());
        }

    }

    @Test
    public void parse_unmarkInvalidInteger_invalidMarkingExceptionThrown() {
        Parser parser = new Parser(new TaskListStub(), new Ui());
        try {
            parser.parse("unmark 4"); //TaskListStub only has three tasks
            fail();
        } catch (InvalidMarkingException e) {
            assertEquals("There is no corresponding task in the list", e.getMessage());
        }

        try {
            parser.parse("unmark 0");
            fail();
        } catch (InvalidMarkingException e) {
            assertEquals("There is no corresponding task in the list", e.getMessage());
        }

        try {
            parser.parse("unmark -1");
            fail();
        } catch (InvalidMarkingException e) {
            assertEquals("There is no corresponding task in the list", e.getMessage());
        }
    }

    @Test
    public void parse_markValidInteger_taskMarked() {
        Parser parser = new Parser(new TaskListStub(false, true), new Ui());
        assertEquals("Task marked as done.", parser.parse("mark 1"));

        assertEquals("\n1 [/] task1\n" + "2 [ ] task2\n" + "3 [ ] task3", parser.parse("list"));

        assertEquals("Task marked as done.", parser.parse("mark 2"));

        assertEquals("\n1 [/] task1\n" + "2 [/] task2\n" + "3 [ ] task3", parser.parse("list"));

        assertEquals("Task marked as done.", parser.parse("mark 3"));

        assertEquals("\n1 [/] task1\n" + "2 [/] task2\n" + "3 [/] task3", parser.parse("list"));
    }

    @Test
    public void parse_unmarkValidInteger_taskUnmarked() {
        Parser parser = new Parser(new TaskListStub(false, false), new Ui());
        assertEquals("Task marked as undone.", parser.parse("unmark 1"));

        assertEquals("\n1 [ ] task1\n" + "2 [/] task2\n" + "3 [/] task3", parser.parse("list"));

        assertEquals("Task marked as undone.", parser.parse("unmark 2"));

        assertEquals("\n1 [ ] task1\n" + "2 [ ] task2\n" + "3 [/] task3", parser.parse("list"));

        assertEquals("Task marked as undone.", parser.parse("unmark 3"));

        assertEquals("\n1 [ ] task1\n" + "2 [ ] task2\n" + "3 [ ] task3", parser.parse("list"));
    }

    @Test
    public void parse_todoNoDescription_lackDescriptionExceptionThrown() {
        Parser parser = new Parser(new TaskListStub(), new Ui());
        try {
            parser.parse("todo");
            fail();
        } catch (LackDescriptionException e) {
            assertEquals("The description of todo should not be empty", e.getMessage());
        }
        assertEquals("list is empty :(", parser.parse("list"));
    }

    @Test
    public void parse_todoValidInput_addTaskToList() {
        Parser parser = new Parser(new TaskListStub(), new Ui());
        assertEquals("Added to list: read books\n" + "Now you have 1 item(s) in the list", parser.parse("todo read books"));

        assertEquals("\n1 [T] [ ] read books (Priority: 1)", parser.parse("list"));
    }

    @Test
    public void parse_deadlineNoDescription_lackDescriptionExceptionThrown() {
        Parser parser = new Parser(new TaskListStub(), new Ui());
        try {
            parser.parse("deadline");
            fail();
        } catch (LackDescriptionException e) {
            assertEquals("The description of deadline should not be empty", e.getMessage());
        }

        try {
            parser.parse("deadline /by 10");
            fail();
        } catch (LackDescriptionException e) {
            assertEquals("The description of deadline should not be empty", e.getMessage());
        }

        assertEquals("list is empty :(", parser.parse("list"));
    }

    @Test
    public void parse_deadlineNoTimeInformation_lackInformationExceptionThrown() {
        Parser parser = new Parser(new TaskListStub(), new Ui());
        try {
            parser.parse("deadline play games");
            fail();
        } catch (LackInformationException e) {
            assertEquals("Please provide information for: \"/by\"", e.getMessage());
        }

        try {
            parser.parse("deadline play games /by ");
            fail();
        } catch (LackInformationException e) {
            assertEquals("Please provide information for: \"/by\"", e.getMessage());
        }

        assertEquals("list is empty :(" , parser.parse("list"));
    }

    @Test
    public void parse_deadlineInvalidTimeFormat_dateTimeParseExceptionThrown() {
        Parser parser = new Parser(new TaskListStub(), new Ui());
        try {
            parser.parse("deadline play games /by 2023/4/2 14:00");
            fail();
        } catch (DateTimeParseException e) {
            //do nothing
        }

        try {
            parser.parse("deadline play games /by 2023/04/04 14-00");
            fail();
        } catch (DateTimeParseException e) {
            //do nothing
        }

        try {
            parser.parse("deadline play games /by 2023/04/04 ");
            fail();
        } catch (DateTimeParseException e) {
            //do nothing
        }

        assertEquals("list is empty :(", parser.parse("list"));
    }

    @Test
    public void parse_deadlineValidInput_addTaskToList() {
        Parser parser = new Parser(new TaskListStub(), new Ui());
        assertEquals("Added to list: play games\n" + "Now you have 1 item(s) in the list", parser.parse("deadline play games /by 2023-12-31 14:00"));

        assertEquals("\n1 [D] [ ] play games (by: Dec 31 2023 2:00PM) (Priority: 1)", parser.parse("list"));
    }

    @Test
    public void parse_eventNoDescription_lackDescriptionExceptionThrown() {
        Parser parser = new Parser(new TaskListStub(), new Ui());
        try {
            parser.parse("event");
            fail();
        } catch (LackDescriptionException e) {
            assertEquals("The description of event should not be empty", e.getMessage());
        }

        try {
            parser.parse("event /from 10 /to 2");
            fail();
        } catch (LackDescriptionException e) {
            assertEquals("The description of event should not be empty", e.getMessage());
        }

        try {
            parser.parse("event /from 10");
            fail();
        } catch (LackDescriptionException e) {
            assertEquals("The description of event should not be empty", e.getMessage());
        }

        try {
            parser.parse("event /to 2");
            fail();
        } catch (LackDescriptionException e) {
            assertEquals("The description of event should not be empty", e.getMessage());
        }

        assertEquals("list is empty :(", parser.parse("list"));
    }

    @Test
    public void parse_eventNoTimeInformation_lackInformationExceptionThrown() {
        Parser parser = new Parser(new TaskListStub(), new Ui());
        try {
            parser.parse("event party");
            fail();
        } catch (LackInformationException e) {
            assertEquals("Please provide information for: \"/from\"", e.getMessage());
        }

        try {
            parser.parse("event party /from");
            fail();
        } catch (LackInformationException e) {
            assertEquals("Please provide information for: \"/from\"", e.getMessage());
        }

        try {
            parser.parse("event party /to 2023-12-31");
            fail();
        } catch (LackInformationException e) {
            assertEquals("Please provide information for: \"/from\"", e.getMessage());
        }

        try {
            parser.parse("event party /from /to 2023-12-31");
            fail();
        } catch (LackInformationException e) {
            assertEquals("Please provide information for: \"/from\"", e.getMessage());
        }

        try {
            parser.parse("event party /from 2023-11-21");
            fail();
        } catch (LackInformationException e) {
            assertEquals("Please provide information for: \"/to\"", e.getMessage());
        }

        try {
            parser.parse("event party /from 2023-11-21 /to ");
            fail();
        } catch (LackInformationException e) {
            assertEquals("Please provide information for: \"/to\"", e.getMessage());
        }

        assertEquals("list is empty :(", parser.parse("list"));
    }

    @Test
    public void parse_eventInvalidTimeFormat_dateTimeParseExceptionThrown() {
        Parser parser = new Parser(new TaskListStub(), new Ui());
        try {
            parser.parse("event party /from 2023/4/2 14:00 /to 2023-12-31 13:00");
            fail();
        } catch (DateTimeParseException e) {
            //do nothing
        }

        try {
            parser.parse("event party /from 2023-04-02 14:00 /to 2023/12/31 13:00");
            fail();
        } catch (DateTimeParseException e) {
            //do nothing
        }

        try {
            parser.parse("event party /from 2023-04-02 2PM /to 2023-12-31 13:00");
            fail();
        } catch (DateTimeParseException e) {
            //do nothing
        }

        assertEquals("list is empty :(", parser.parse("list"));
    }


    @Test
    public void parse_eventValidInput_addTaskToList() {
        Parser parser = new Parser(new TaskListStub(), new Ui());
        assertEquals("Added to list: party\n" + "Now you have 1 item(s) in the list", parser.parse("event party /from 2023-04-02 14:00 /to 2023-12-31 13:00"));

        assertEquals("\n1 [E] [ ] party (from: Apr 2 2023 2:00PM to: Dec 31 2023 1:00PM) (Priority: 1)", parser.parse("list"));
    }

    @Test
    public void parse_deleteNoIndex_invalidMarkingExceptionThrown() {
        Parser parser = new Parser(new TaskListStub(), new Ui());
        try {
            parser.parse("delete");
            fail();
        } catch (InvalidMarkingException e) {
            assertEquals("Missing index", e.getMessage());
        }
    }

    @Test
    public void parse_deleteNonInteger_invalidMarkingExceptionThrown() {
        Parser parser = new Parser(new TaskListStub(), new Ui());
        try {
            parser.parse("delete nonInteger");
            fail();
        } catch (InvalidMarkingException e) {
            assertEquals("Please provide a valid index", e.getMessage());
        }
    }

    @Test
    public void parse_deleteInvalidInteger_invalidMarkingExceptionThrown() {
        Parser parser = new Parser(new TaskListStub(), new Ui());
        try {
            parser.parse("delete 4"); //TaskListStub only has three tasks
            fail();
        } catch (InvalidMarkingException e) {
            assertEquals("There is no corresponding task in the list", e.getMessage());
        }

        try {
            parser.parse("delete 0");
            fail();
        } catch (InvalidMarkingException e) {
            assertEquals("There is no corresponding task in the list", e.getMessage());
        }

        try {
            parser.parse("delete -1");
            fail();
        } catch (InvalidMarkingException e) {
            assertEquals("There is no corresponding task in the list", e.getMessage());
        }
    }

    @Test
    public void parse_deleteValidInteger_taskDeleted() {
        Parser parser = new Parser(new TaskListStub(false, true), new Ui());
        assertEquals("I've removed this task:\n" + "[ ] task1\n" + "Now you have 2 tasks in the list", parser.parse("delete 1"));

        assertEquals("\n1 [ ] task2\n" + "2 [ ] task3", parser.parse("list"));
    }

    @Test
    public void parse_invalidInstruction_invalidInputExceptionThrown() {
        Parser parser = new Parser(new TaskListStub(), new Ui());
        try {
            parser.parse("check homework");
            fail();
        } catch (InvalidInputException e) {
            assertEquals("OOPS! I do not know what \"check\" means. Please try again :)", e.getMessage());
        }

        try {
            parser.parse("copy homework");
            fail();
        } catch (InvalidInputException e) {
            assertEquals("OOPS! I do not know what \"copy\" means. Please try again :)", e.getMessage());
        }
    }

    @Test
    public void parse_findMissingKeyword_invalidFindingExceptionThrown() {
        Parser parser = new Parser(new TaskListStub(), new Ui());
        try {
            parser.parse("find");
            fail();
        } catch (InvalidFindingException e) {
            assertEquals("Missing keyword", e.getMessage());
        }
    }

    @Test
    public void parse_findNotInList_noMatching() {
        Parser parser = new Parser(new TaskListStub(), new Ui());
        assertEquals("No matching found", parser.parse("find homework"));
    }

    @Test
    public void parse_findInList_returnMatching() {
        Parser parser = new Parser(new TaskListStub(false, true), new Ui());
        assertEquals("\n" + "1 [ ] task1\n" + "2 [ ] task2\n" + "3 [ ] task3\n"
                + "You have 3 matching task(s) in your list", parser.parse("find task"));
    }

    @Test
    public void parse_rankMissingKeyword_invalidRankingExceptionThrown() {
        Parser parser = new Parser(new TaskListStub(), new Ui());
        try {
            parser.parse("rank");
            fail();
        } catch (InvalidRankingException e) {
            assertEquals("Missing target task", e.getMessage());
        }
    }

    @Test
    public void parse_rankInvalidIndexType_invalidRankingExceptionThrown() {
        Parser parser = new Parser(new TaskListStub(), new Ui());
        try {
            parser.parse("rank book");
            fail();
        } catch (InvalidRankingException e) {
            assertEquals("Please provide a valid index for task", e.getMessage());
        }
    }

    @Test
    public void parse_rankInvalidIndex_invalidRankingExceptionThrown() {
        Parser parser = new Parser(new TaskListStub(), new Ui());
        try {
            parser.parse("rank 1");
            fail();
        } catch (InvalidRankingException e) {
            assertEquals("There is no corresponding task in the list", e.getMessage());
        }

        try {
            parser.parse("rank -1 /level 100");
            fail();
        } catch (InvalidRankingException e) {
            assertEquals("There is no corresponding task in the list", e.getMessage());
        }
    }

    @Test
    public void parse_rankInvalidRanking_invalidRankingExceptionThrown() {
        Parser parser = new Parser(new TaskListStub(), new Ui());
        parser.parse("todo read books");

        try {
            parser.parse("rank 1 /level high");
            fail();
        } catch (InvalidRankingException e) {
            assertEquals("Please provide a valid number for ranking", e.getMessage());
        }
        assertEquals("\n1 [T] [ ] read books (Priority: 1)", parser.parse("list"));
    }

    @Test
    public void parse_rankValidRanking_rankingUpdated() {
        Parser parser = new Parser(new TaskListStub(), new Ui());
        parser.parse("todo read books");

        assertEquals("Priority of task has been updated successfully", parser.parse("rank 1 /level 3"));
        assertEquals("\n1 [T] [ ] read books (Priority: 3)", parser.parse("list"));
    }

}
