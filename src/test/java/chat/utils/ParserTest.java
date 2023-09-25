package chat.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import chat.exceptions.ChatException;

public class ParserTest {

    @Test
    public void mapTest() {
        try {
            assertEquals(Parser.map("delete"), Enum.DELETE);
        } catch (ChatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    public void filePathTest1() {
        try {
            String[] test = Parser.parseFilePath("duke/data/duke.txt");
            String[] actual = {"duke", "data", "duke.txt"};
            for (int i = 0; i < test.length; i++) {
                assertEquals(test[i], actual[i]);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    public void filePathTest2() {
        try {
            String[] test = Parser.parseFilePath("/data/duke.txt");
            String[] actual = {"", "data", "duke.txt"};
            for (int i = 0; i < test.length; i++) {
                assertEquals(test[i], actual[i]);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
