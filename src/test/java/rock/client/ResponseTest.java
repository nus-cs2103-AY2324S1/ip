package rock.client;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ResponseTest {
    static final String TEST_STR = "TEST";
    @Test
    public void testValidResponseMsg() {
        final Response testResponse = Response.createValidResponse(TEST_STR);
        assertEquals(TEST_STR, testResponse.getMessage());
    }
    @Test
    public void testValidResponseIsError() {
        final Response testResponse = Response.createValidResponse(TEST_STR);
        assertEquals(false, testResponse.isError());
    }
    @Test
    public void testErrorResponseMsg() {
        final Response testResponse = Response.createErrorResponse(TEST_STR);
        assertEquals(TEST_STR, testResponse.getMessage());
    }
    @Test
    public void testErrorResponseIsError() {
        final Response testResponse = Response.createErrorResponse(TEST_STR);
        assertEquals(true, testResponse.isError());
    }
}

