package com.coherent.training.selenium.kapitsa.parser;

import com.coherent.training.selenium.kapitsa.shop.Cart;

import java.io.File;

public interface Parser {

    void writeToFile(Cart cart);
    Cart readFromFile(File file);
}
