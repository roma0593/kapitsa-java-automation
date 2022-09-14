package com.coherent.training.selenium.kapitsa.cart;

import org.testng.annotations.*;
import com.coherent.training.selenium.kapitsa.shop.Cart;
import com.coherent.training.selenium.kapitsa.shop.RealItem;
import com.coherent.training.selenium.kapitsa.shop.VirtualItem;

import static org.testng.Assert.assertEquals;

public class PositiveCartTest {
    private Cart cart;
    private static RealItem realItem;
    private static VirtualItem virtualItem;
    private static double totalCartPrice;
    private static final double TAX = 1.2;
    private static final String REAL_ITEM_NAME = "Apple";
    private static final double REAL_ITEM_WEIGHT = 0.3;
    private static final double REAL_ITEM_PRICE = 2.6;
    private static final String VIRTUAL_ITEM_NAME = "FIFA22";
    private static final double VIRTUAL_ITEM_WEIGHT = 6.5;
    private static final double VIRTUAL_ITEM_PRICE = 30.0;


    @BeforeClass
    public static void createItems(){

        realItem = new RealItem();
        realItem.setName(REAL_ITEM_NAME);
        realItem.setWeight(REAL_ITEM_WEIGHT);
        realItem.setPrice(REAL_ITEM_PRICE);

        virtualItem = new VirtualItem();
        virtualItem.setName(VIRTUAL_ITEM_NAME);
        virtualItem.setSizeOnDisk(VIRTUAL_ITEM_WEIGHT);
        virtualItem.setPrice(VIRTUAL_ITEM_PRICE);
    }

    @Parameters({"cartName"})
    @BeforeMethod
    public void createCart(String cartName){
        cart = new Cart(cartName);
    }

    @Test
    public void cartEmptinessTest(){
        assertEquals(0.00, cart.getTotalPrice(), "Real and total com.coherent.training.selenium.kapitsa.cart price mismatch");
    }

    @Test
    public void addRealItemTest(){
        cart.addRealItem(realItem);
        totalCartPrice = cart.getTotalPrice();
        double realItemPriceInCart = realItem.getPrice() * TAX;

        assertEquals(totalCartPrice, realItemPriceInCart, "Real and total com.coherent.training.selenium.kapitsa.cart price mismatch");
    }

    @Test
    public void addVirtualItemTest(){
        cart.addVirtualItem(virtualItem);
        totalCartPrice = cart.getTotalPrice();
        double virtualItemPriceInCart = virtualItem.getPrice() * TAX;

        assertEquals(totalCartPrice, virtualItemPriceInCart,
                "Real and total com.coherent.training.selenium.kapitsa.cart price mismatch");
    }
}
