package item.real;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import shop.RealItem;

import static org.testng.Assert.assertEquals;

public class PositiveRealItemTest {
    private static RealItem realItem;

    @BeforeClass
    public static void createRItemObject(){
        realItem = new RealItem();
    }

    @Parameters({"realItemName"})
    @Test
    public void setRealItemNameTest(String realItemName){
        realItem.setName(realItemName);

        assertEquals(realItemName, realItem.getName(), "Expected and actual name mismatch");
    }

    @Parameters({"realItemWeight"})
    @Test
    public void setRealItemWeightTest(double realItemWeight){
        realItem.setWeight(realItemWeight);

        assertEquals(realItemWeight, realItem.getWeight(), "Expected and actual weight mismatch");
    }

    @Parameters({"realItemPrice"})
    @Test
    public void setRealItemPriceTest(double realItemPrice){
        realItem.setPrice(realItemPrice);

        assertEquals(realItemPrice, realItem.getPrice(), "Expected and actual price mismatch");
    }
}
