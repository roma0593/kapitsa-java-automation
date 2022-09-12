package parser;

import com.google.gson.JsonSyntaxException;
import listeners.ExceptionListener;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import shop.Cart;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.expectThrows;

@Listeners(ExceptionListener.class)
public class NegativeJSonParserTest {
    private static final JsonParser jsonParser = new JsonParser();
    private static Cart cart;
    private static File emptyFile;
    private static File nonJsonFile;
    private File nullFile;
    private static final String EMPTY_FILE_PATH = "src/main/resources/new_cart.json";
    private static final String NON_JSON_FILE_PATH = "src/main/resources/cart.html";
    private static final String NON_JSON_FILE_CONTENT = "<html><head><title>New Page</title></head><body><p>This is Body</p></body></html>";

    @BeforeClass
    public static void createFiles() throws IOException {
        emptyFile = new File(EMPTY_FILE_PATH);
        emptyFile.createNewFile();

        nonJsonFile = new File(NON_JSON_FILE_PATH);
        nonJsonFile.createNewFile();

        FileWriter fileWriter = new FileWriter(nonJsonFile.getPath());
        fileWriter.write(NON_JSON_FILE_CONTENT);
        fileWriter.close();
    }

    @BeforeClass
    public static void removeFiles() {
        emptyFile.deleteOnExit();
        nonJsonFile.deleteOnExit();
    }

    @Parameters({"absentFilePath"})
    @Test
    public void readFromAbsentFileTest(String absentFilePath) {
        File absentFile = new File(absentFilePath);

        String expectedMessage = String.format("File %s.json not found!", absentFile.getPath());

        Exception exception = expectThrows(NoSuchFileException.class, () -> {
            jsonParser.readFromFile(absentFile);
        });

        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test(expectedExceptions = {NullPointerException.class})
    public void readFromEmptyJsonFileTest() {
        jsonParser.readFromFile(emptyFile);
    }

    @Test(expectedExceptions = {JsonSyntaxException.class})
    public void readFromInvalidFileTypeTest() {
        jsonParser.readFromFile(nonJsonFile);
    }

    @Test(expectedExceptions = {NullPointerException.class})
    public void readFromNullFileTest() {
        jsonParser.readFromFile(nullFile);
    }

    @Test(expectedExceptions = {NullPointerException.class})
    public void writeToFileWithNullCart() {
        jsonParser.writeToFile(cart);
    }
}
