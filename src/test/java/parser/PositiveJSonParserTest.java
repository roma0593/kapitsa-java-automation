package parser;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import shop.Cart;
import shop.RealItem;
import shop.VirtualItem;

import java.io.File;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class PositiveJSonParserTest {
    private final JsonParser jsonParser = new JsonParser();
    private Cart cart;
    private File cartFile;

    @Parameters({"vItemName", "vItemSize", "vItemPrice", "rItemName", "rItemWeight", "vItemPrice", "cartName"})
    @BeforeClass(groups = {"smoke_test"})
    public void createCartWithItemsTest(String vItemName, double vItemSize, double vItemPrice, String rItemName,
                                  double rItemWeight, double rItemPrice, String cartName){

        VirtualItem virtualItem = new VirtualItem();
        virtualItem.setName(vItemName);
        virtualItem.setSizeOnDisk(vItemSize);
        virtualItem.setPrice(vItemPrice);

        RealItem realItem = new RealItem();
        realItem.setName(rItemName);
        realItem.setWeight(rItemWeight);
        realItem.setPrice(rItemPrice);

        cart = new Cart(cartName);
        cart.addVirtualItem(virtualItem);
        cart.addRealItem(realItem);
    }

    @AfterClass(groups = {"smoke_test"})
    public void deleteJsonFile(){
        cartFile.deleteOnExit();
    }

    @Test(groups = {"smoke_test"})
    public void writeToFileTest(){
        jsonParser.writeToFile(cart);

        cartFile = new File("src/main/resources/" + cart.getCartName() + ".json");

        assertTrue(cartFile.exists(), String.format("File %s doesn't exist", cart.getCartName() + ".json"));
    }

    @Parameters({"jsonFilePath", "jsonCartName", "jsonCartPrice"})
    @Test(groups = {"smoke_test"})
    public void readFromFileTest(String jsonFilePath, String jsonCartName, double jsonCartPrice){
        cartFile = new File(jsonFilePath);
        Cart readFromJsonCart = jsonParser.readFromFile(cartFile);

        assertEquals(jsonCartName, readFromJsonCart.getCartName(),
                "Expected and actual cart name mismatch");
        assertEquals(jsonCartPrice, readFromJsonCart.getTotalPrice(),
                "Expected and actual total price mismatch");
    }

    @Test
    public void writeToFileCartWithEmptyFields(){
        VirtualItem emptyVirtualItem = new VirtualItem();
        RealItem emptyRealItem = new RealItem();

        cart.addVirtualItem(emptyVirtualItem);
        cart.addRealItem(emptyRealItem);

        jsonParser.writeToFile(cart);

        cartFile = new File("src/main/resources/" + cart.getCartName() + ".json");

        assertTrue(cartFile.exists(), String.format("File %s doesn't exist", cart.getCartName() + ".json"));
    }
}
