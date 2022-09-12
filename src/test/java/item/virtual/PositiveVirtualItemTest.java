package item.virtual;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import shop.VirtualItem;

import static org.testng.Assert.assertEquals;

public class PositiveVirtualItemTest {
    private static VirtualItem vItem;

    @BeforeClass
    public static void createVItemObject(){
        vItem = new VirtualItem();
    }

    @Parameters({"virtualItemName"})
    @Test
    public static void setNameForVirtualItemTes(String virtualItemName){
        vItem.setName(virtualItemName);

        assertEquals(virtualItemName, vItem.getName(), "Expected and actual name mismatch");
    }

    @Parameters({"virtualItemSize"})
    @Test
    public static void setSizeForVirtualItemTes(double virtualItemSize){
        vItem.setSizeOnDisk(virtualItemSize);

        assertEquals(virtualItemSize, vItem.getSizeOnDisk(), "Expected and actual size mismatch");
    }

    @Parameters({"virtualItemPrice"})
    @Test
    public static void setPriceForVirtualItemTes(double virtualItemPrice){
        vItem.setPrice(virtualItemPrice);

        assertEquals(virtualItemPrice, vItem.getPrice(), "Expected and actual price mismatch");
    }
}
