package parser;

import org.testng.annotations.*;
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
    private RealItem realItem;
    private Cart readFromJsonCart;

    @Parameters({"vItemName", "vItemSize", "vItemPrice", "rItemName", "rItemWeight", "vItemPrice", "cartName"})
    @BeforeClass(groups = {"smoke_test"})
    public void createCartWithItemsTest(String vItemName, double vItemSize, double vItemPrice, String rItemName,
                                  double rItemWeight, double rItemPrice, String cartName){

        VirtualItem virtualItem = new VirtualItem();
        virtualItem.setName(vItemName);
        virtualItem.setSizeOnDisk(vItemSize);
        virtualItem.setPrice(vItemPrice);

        realItem = new RealItem();
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

    @Test(groups = {"smoke_test"}, priority = 1)
    public void writeToFileTest(){
        jsonParser.writeToFile(cart);

        cartFile = new File("src/main/resources/" + cart.getCartName() + ".json");

        assertTrue(cartFile.exists(), String.format("File %s doesn't exist", cart.getCartName() + ".json"));
    }

    @Test(groups = {"smoke_test"}, priority = 2)
    public void readFromFileTest(){
        readFromJsonCart = jsonParser.readFromFile(cartFile);
    }

    @Parameters({"cartName"})
    @Test(groups = {"smoke_test"}, priority = 3)
    public void cartNameFromFileTest(String cartName){
        assertEquals(cartName, readFromJsonCart.getCartName(), "Expected and actual cart name mismatch");
    }

    @Test(groups = {"smoke_test"}, priority = 4)
    public void cartPriceFromFileTest(){
        assertEquals(cart.getTotalPrice(), readFromJsonCart.getTotalPrice(),
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
