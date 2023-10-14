package shiba.parsers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

public class SpaceSeparatedValuesParserTest {
    @Test
    public void convert_normal() {
        assertEquals("shiba labrador corgi", SpaceSeparatedValuesParser.convert("shiba", "labrador", "corgi"));
    }

    @Test
    public void convert_spaces() {
        assertEquals("shiba\\ inu labrador\\ retriever pembroke\\ welsh\\ corgi",
                SpaceSeparatedValuesParser.convert("shiba inu", "labrador retriever", "pembroke welsh corgi"));
    }

    @Test
    public void convert_manySpaces() {
        assertEquals("\\ \\ shiba\\ \\ \\ inu\\ \\  \\ labrador\\ \\ retriever\\  "
                        + "\\ \\ \\ pembroke\\ \\ \\ \\ welsh\\ \\ \\ \\ corgi\\ \\ \\ ",
                SpaceSeparatedValuesParser.convert("  shiba   inu  ", " labrador  retriever ",
                        "   pembroke    welsh    corgi   "));
    }

    @Test
    public void convert_backslash() {
        assertEquals("shiba\\inu labrador\\retriever pembroke\\welsh\\corgi",
                SpaceSeparatedValuesParser.convert("shiba\\inu", "labrador\\retriever", "pembroke\\welsh\\corgi"));
    }

    @Test
    public void convert_empty() {
        assertEquals("", SpaceSeparatedValuesParser.convert());
    }

    @Test
    public void parse_normal() {
        List<String> params = SpaceSeparatedValuesParser.parse("shiba labrador corgi");
        assertEquals(3, params.size());
        assertEquals("shiba", params.get(0));
        assertEquals("labrador", params.get(1));
        assertEquals("corgi", params.get(2));
    }

    @Test
    public void parse_spaces() {
        List<String> params = SpaceSeparatedValuesParser.parse("shiba\\ inu labrador\\ retriever "
                + "pembroke\\ welsh\\ corgi");
        assertEquals(3, params.size());
        assertEquals("shiba inu", params.get(0));
        assertEquals("labrador retriever", params.get(1));
        assertEquals("pembroke welsh corgi", params.get(2));
    }

    @Test
    public void parse_manySpaces() {
        List<String> params = SpaceSeparatedValuesParser.parse("\\ \\ shiba\\ \\ \\ inu\\ \\  "
                + "\\ labrador\\ \\ retriever\\  \\ \\ \\ pembroke\\ \\ \\ \\ welsh\\ \\ \\ \\ corgi\\ \\ \\ ");
        assertEquals(3, params.size());
        assertEquals("  shiba   inu  ", params.get(0));
        assertEquals(" labrador  retriever ", params.get(1));
        assertEquals("   pembroke    welsh    corgi   ", params.get(2));
    }

    @Test
    public void parse_backslash() {
        List<String> params = SpaceSeparatedValuesParser.parse("shiba\\inu labrador\\retriever pembroke\\welsh\\corgi");
        assertEquals(3, params.size());
        assertEquals("shiba\\inu", params.get(0));
        assertEquals("labrador\\retriever", params.get(1));
        assertEquals("pembroke\\welsh\\corgi", params.get(2));
    }

    @Test
    public void parse_backslashSpace() {
        List<String> params = SpaceSeparatedValuesParser.parse("shiba\\\\ inu "
                + "labrador\\ \\retriever pembroke\\\\ welsh\\ \\corgi");
        assertEquals(3, params.size());
        assertEquals("shiba\\ inu", params.get(0));
        assertEquals("labrador \\retriever", params.get(1));
        assertEquals("pembroke\\ welsh \\corgi", params.get(2));
    }

    @Test
    public void parse_empty() {
        List<String> params = SpaceSeparatedValuesParser.parse("");
        assertEquals(0, params.size());
    }
}
