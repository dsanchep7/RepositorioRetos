package steps;

import drivers.GoogleChromeDriver;
import org.junit.Assert;
import org.openqa.selenium.By;
import pages.EbayPage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class EbaySteps {

    EbayPage ebayPage = new EbayPage();

    public void abrirPagina(){
        GoogleChromeDriver.chromeWebDriver("https://co.ebay.com/");
    }

    public void buscarElementoEnEbay(String producto){
        GoogleChromeDriver.driver.findElement(ebayPage.getTxtBuscador()).sendKeys(producto);
        GoogleChromeDriver.driver.findElement(ebayPage.getBtnBuscador()).click();
        ebayPage.setBtnElementoBusqueda(producto);
        GoogleChromeDriver.driver.findElement(ebayPage.getBtnElementoBusqueda()).click();
    }

    public void buscarElementoEnEbays(String productos){
        try {
            escribirEnTexto(ebayPage.getTxtBuscador(), productos);
            Thread.sleep(1000);
            clicEnElemento(ebayPage.getBtnBuscador());
            Thread.sleep(1000);
            ebayPage.setBtnElementoBusqueda(productos);
            Thread.sleep(1000);
            clicEnElemento(ebayPage.getBtnElementoBusqueda());
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void validarElementoEnPantalla(String producto){
        ebayPage.setTxtElementoBusqueda(producto);
        Assert.assertEquals(producto.replace("  "," "),GoogleChromeDriver.driver.findElement(ebayPage.getTxtElementoBusqueda()).getText());
        GoogleChromeDriver.driver.quit();
    }

    public void escribirEnTexto(By elemento, String texto) {
        GoogleChromeDriver.driver.findElement(elemento).clear();
        GoogleChromeDriver.driver.findElement(elemento).sendKeys(texto);
    }

    public void clicEnElemento(By elemento) {
        GoogleChromeDriver.driver.findElement(elemento).click();
    }

    public void validarElementoNoExistenteEnPantalla(String producto){
        ebayPage.setTxtElementoBusqueda(producto);
        Assert.assertNotEquals(producto,GoogleChromeDriver.driver.findElement(ebayPage.getTxtElementoBusqueda()).getText());
        GoogleChromeDriver.driver.quit();
    }

}
