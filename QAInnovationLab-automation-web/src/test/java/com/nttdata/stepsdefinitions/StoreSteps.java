package com.nttdata.stepsdefinitions;


import com.nttdata.steps.StorePage;
import org.openqa.selenium.WebDriver;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import org.junit.jupiter.api.Assertions;

import static com.nttdata.core.DriverManager.getDriver;
import static com.nttdata.core.DriverManager.screenShot;

public class StoreSteps {

    private WebDriver driver;
    private StorePage storePage;

    // Constructor sin argumentos
    public StoreSteps() {
        // Si es necesario inicializar algo, lo puedes hacer aquí
    }
    public StoreSteps(WebDriver driver) {
        this.driver = driver;
        this.storePage = new StorePage(driver);
    }

    @Dado("estoy en la página de la tienda")
    public void estoy_en_la_pagina_de_la_tienda() {
        driver = getDriver();
        driver.get("https://qalab.bensg.com/store/pe/iniciar-sesion?back=my-account");
        screenShot();
    }

    @Y("me logueo con mi usuario: {string} y contraseña: {string}")
    public void me_logueo_con_mi_usuario_y_clave(String user, String password) {
        StorePage storePage = new StorePage(driver);
        storePage.typeUser(user);
        storePage.typePassword(password);
        storePage.login();
        screenShot();
    }

    @Cuando("navego a la categoria {string} y subcategoria {string}")
    public void navego_a_la_categoria_y_subcategoria(String category, String subcategory){
        StorePage storePage = new StorePage(driver);
        storePage.selectCategory(category);
        storePage.selectSubcategory(subcategory);
        screenShot();
    }

    @Y("agrego {int} unidades del primer producto al carrito")
    public void agrego_unidades_del_primer_producto_al_carrito(int quantity) {
        StorePage storePage = new StorePage(driver);
        storePage.addProductToCart( quantity);
        screenShot();
    }

    @Entonces("valido en el popup la confirmación del producto agregado")
    public void valido_en_el_popup_la_confirmacion_del_producto_agregado() {
        StorePage storePage = new StorePage(driver);
        String confirmationMessage = storePage.getPopupConfirmationMessage();
        Assertions.assertEquals("Producto añadido correctamente a su carrito de compra", confirmationMessage);
        screenShot();
    }

    @Y("valido en el popup que el monto total sea calculado correctamente")
    public void valido_en_el_popup_que_el_monto_total_sea_calculado_correctamente() {
        StorePage storePage = new StorePage(driver);
        double totalAmount = storePage.getPopupTotalAmount();
        Assertions.assertTrue(totalAmount == 38.24, "El monto total es correcto");
        screenShot();
    }

    @Cuando("finalizo la compra")
    public void finalizo_la_compra() {
        StorePage storePage = new StorePage(driver);
        storePage.completePurchase();
        screenShot();
    }

    @Entonces("valido el titulo de la pagina del carrito")
    public void valido_el_titulo_de_la_pagina_del_carrito() {
        StorePage storePage = new StorePage(driver);
        String confirmationMessage = storePage.getConfirmation();
        Assertions.assertEquals("CARRITO", confirmationMessage);
        screenShot();
    }

    @Y("vuelvo a validar el calculo de precios en el carrito")
    public void vuelvo_a_validar_el_calculo_de_precios_en_el_carrito() {
        StorePage storePage = new StorePage(driver);
        double totalAmount = storePage.getCartTotalAmount();
        Assertions.assertTrue(totalAmount == 38.24, "El monto total es correcto");
        screenShot();
    }

    @Entonces("valido que debería aparecer un mensaje de error de autenticación")
    public void valido_que_deberia_aparecer_un_mensaje_de_error_de_autenticacion() {
        StorePage storePage = new StorePage(driver);
        String errorMessage = storePage.getErrorMessage();
        Assertions.assertEquals("Error de autenticación.", errorMessage);
        screenShot();
    }

    @Entonces("valido que debería aparecer un mensaje de error de categoría no encontrada")
    public void valido_que_deberia_aparecer_un_mensaje_de_error_de_categoria_no_encontrada() {
        String errorMessage = storePage.getErrorMessage();
        Assertions.assertEquals("Categoría no encontrada", errorMessage);
        screenShot();
    }

}
