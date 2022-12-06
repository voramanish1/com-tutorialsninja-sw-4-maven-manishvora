package com.tutorialsninja.testsuite;

import com.tutorialsninja.pages.LaptopsAndNotebooks;
import com.tutorialsninja.testbase.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LaptopsAndNotebooksTest extends BaseTest {
    LaptopsAndNotebooks laptopAndNotebooks = new LaptopsAndNotebooks();

    @Test
    public void verifyProductsPriceDisplayHighToLowSuccessfully() {
        laptopAndNotebooks.clickOnLaptop();
        laptopAndNotebooks.clickOnShowAllLaptopsAndNoteBooks();
        laptopAndNotebooks.selectSortByPriceHighToLow();
    }


    @Test
    public void verifyThatUserPlaceOrderSuccessfully() throws InterruptedException {
        laptopAndNotebooks.selectCurrencyTab();
        laptopAndNotebooks.changeCurrencyToSterling();
        laptopAndNotebooks.clickOnLaptop();
        laptopAndNotebooks.clickOnShowAllLaptopsAndNoteBooks();
        laptopAndNotebooks.selectSortByPriceHighToLow();
        laptopAndNotebooks.selectProductMacBook();
        Assert.assertEquals(laptopAndNotebooks.verifyTextMacBook(), "MacBook", "MacBook Product not display");
        laptopAndNotebooks.clickOnAddToCart();
        Assert.assertTrue(laptopAndNotebooks.shoppingCartMessage().contains("Success: You have added MacBook to your shopping cart!"), "Product not added to cart");
        laptopAndNotebooks.clickOnShoppingCart();
        Assert.assertTrue(laptopAndNotebooks.verifyShoppingCartText().contains("Shopping Cart"));
        Assert.assertEquals(laptopAndNotebooks.verifyProductNameMacBook(), "MacBook", "Product name not matched");
        WebElement qty = driver.findElement(By.cssSelector("input[value='1']"));
        qty.clear();
        laptopAndNotebooks.enterQuantity("2");
        laptopAndNotebooks.clickOnUpdateTab();
        Assert.assertTrue(laptopAndNotebooks.modifyShoppingCartMessage().contains("Success: You have modified your shopping cart!"), "Cart not modified");
        Assert.assertEquals(laptopAndNotebooks.verifyTotal(), "£737.45", "Total not matched");
        laptopAndNotebooks.clickOnCheckout();
        Assert.assertEquals(laptopAndNotebooks.verifyCheckoutText(), "Checkout", "not check out");
        Thread.sleep(1000);
        Assert.assertEquals(laptopAndNotebooks.verifyNewCustomer(),"New Customer","correct customer");
        laptopAndNotebooks.clickOnGuestCheckOut();
        laptopAndNotebooks.clickOnContinueButton();
        laptopAndNotebooks.enterFirstName("Abc");
        laptopAndNotebooks.enterLastName("Xyz");
        laptopAndNotebooks.enterEmail("test123@gmail.com");
        laptopAndNotebooks.enterTelephone("0987654321");
        laptopAndNotebooks.enterAddress1("10 Downing Street");
        laptopAndNotebooks.enterCity("London");
        laptopAndNotebooks.enterPostcode("EC1B 2JL");
        laptopAndNotebooks.selectCountry();
        laptopAndNotebooks.selectRegion();
        laptopAndNotebooks.clickOnContinue();
        laptopAndNotebooks.addComments("Thank You");
        laptopAndNotebooks.clickonTermsAndConditions();
        laptopAndNotebooks.clickOnContinueTab();
        Assert.assertEquals(laptopAndNotebooks.paymentWarningMessage(),"Warning: Payment method required!\n×");
    }
}
