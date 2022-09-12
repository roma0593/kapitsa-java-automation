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
    private VirtualItem virtualItem;
    private RealItem realItem;
    private Cart readFromJsonCart;

    @Parameters({"vItemName", "vItemSize", "vItemPrice"})
    @BeforeClass(groups = {"smoke_test"})
    public void createVirtualItem(String vItemName, double vItemSize, double vItemPrice){
        virtualItem = new VirtualItem();
        virtualItem.setName(vItemName);
        virtualItem.setSizeOnDisk(vItemSize);
        virtualItem.setPrice(vItemPrice);
    }

    @Parameters({"rItemName", "rItemWeight", "vItemPrice"})
    @BeforeClass(groups = {"smoke_test"})
    public void createRealItem(String rItemName, double rItemWeight, double rItemPrice){
        realItem = new RealItem();
        realItem.setName(rItemName);
        realItem.setWeight(rItemWeight);
        realItem.setPrice(rItemPrice);
    }

    @Parameters({"cartName"})
    @BeforeClass(dependsOnMethods = {"createRealItem", "createVirtualItem"}, groups = {"smoke_test"})
    public void createCart(String cartName){
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

    @Test(dependsOnMethods = {"writeToFileTest"}, groups = {"smoke_test"})
    public void readFromFileTest(){
        readFromJsonCart = jsonParser.readFromFile(cartFile);
    }

    @Parameters({"cartName"})
    @Test(dependsOnMethods = {"readFromFileTest"}, groups = {"smoke_test"})
    public void cartNameFromFileTest(String cartName){
        assertEquals(cartName, readFromJsonCart.getCartName(), "Expected and actual cart name mismatch");
    }

    @Test(dependsOnMethods = {"readFromFileTest"}, groups = {"smoke_test"})
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
