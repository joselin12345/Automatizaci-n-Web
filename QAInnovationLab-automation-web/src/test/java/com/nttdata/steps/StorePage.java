package com.nttdata.steps;

import com.nttdata.page.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class StorePage {

    private WebDriver driver;


    public StorePage(WebDriver driver) {
        this.driver = driver;
    }

    public void typeUser(String user){
        WebElement userInputElement = driver.findElement(LoginPage.userInput);
        userInputElement.sendKeys(user);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(444));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPage.loginButton));


    }
    public void typePassword(String password){
        this.driver.findElement(LoginPage.passInput).sendKeys(password);
    }

    public void login(){
        this.driver.findElement(LoginPage.loginButton).click();
    }

    public void selectCategory(String category) {
        this.driver.findElement(LoginPage.category).click();
    }

    public void selectSubcategory(String subcategory) {
        this.driver.findElement(LoginPage.subcategory).click();
    }

    public void addProductToCart( int quantity) {
        this.driver.findElement(LoginPage.product).click();
        this.driver.findElement(LoginPage.agrego).click();

    }

    public String getPopupConfirmationMessage() {
        this.driver.findElement(LoginPage.guardar).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement labelElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='myModalLabel']")));
        return labelElement.getText().replaceAll("[\\p{C}]", "").trim();

    }

    public double getPopupTotalAmount() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement labelElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"blockcart-modal\"]/div/div/div[2]/div/div[2]/div/p[4]/span[2]")));
        String text = labelElement.getText();
        String numericText = text.replace("S/", "").trim();
        return Double.parseDouble(numericText);
    }

    public void completePurchase() {
        this.driver.findElement(LoginPage.guardarCompra).click();
    }

    public double getCartTotalAmount() {
        WebElement labelElement = driver.findElement(LoginPage.monto);
        String text = labelElement.getText();
        String numericText = text.replace("S/", "").trim();
        return Double.parseDouble(numericText);
    }

    public String getConfirmation() {
        WebElement labelElement = driver.findElement(LoginPage.titulo);
        String text = labelElement.getText();
        return text;
    }

    public String getErrorMessage() {
        WebElement labelElement = driver.findElement(LoginPage.mensjaeError);
        String text = labelElement.getText();
        return text;
    }
}
