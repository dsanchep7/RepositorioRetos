# Mi segundo reto

Mi segundo reto consta de realizar la automatización de ingreso y busqueda de productos en la paginan https://co.ebay.com/, con diferentes escenarios background,fallido y exitoso.

## Empezando

Las pruebas fueron apoyados por el IDE Intelling con el gestor de dependencias gradle sobre la parte front de la pagina apoyado por el patron de diseño POM y la herramienta de software gherkin.


### Implementación de POM
 
Se realiza en los paquetes 
- drivers Clase - ChromeDriver )
- pages (Clase - Pages)
- steps (Clase - Steps)

####   La clase ChromeDriver
  ```
Esta clase se utiliza para levantar el navegador

package drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class GoogleChromeDriver {

    public static WebDriver driver;

    public static void chromeWebDriver(String url){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--disable-infobars");
        driver = new ChromeDriver(options);
        driver.get(url);
    }

}

  ```
####  La clase pages
  ```
Es donde definimos los xpath para la iteración con la pagina que estamos automatizando.

package pages;

import org.openqa.selenium.By;

public class EbayPage {

    By txtBuscador = By.xpath("//input[@placeholder='Buscar artículos']");
    By btnBuscador = By.xpath("//input[@class ='btn btn-prim gh-spr' ]");
    By btnElementoBusqueda;
    By txtElementoBusqueda;

    public By getTxtBuscador() {
        return txtBuscador;
    }

    public By getBtnBuscador() {
        return btnBuscador;
    }

    public By getBtnElementoBusqueda() {
        return btnElementoBusqueda;
    }

    public By getTxtElementoBusqueda() {
        return txtElementoBusqueda;
    }

    public void setBtnElementoBusqueda(String producto) {
        //this.btnElementoBusqueda = By.xpath("//li[@class='s-item s-item__pl-on-bottom' and @data-view='"+producto+"')]");
        this.btnElementoBusqueda = By.xpath("//li[@class='s-item s-item__pl-on-bottom']//div[@class='s-item__wrapper clearfix']//h3[contains(text(),'"+producto+"')]");
    }
    //li[@class='s-item s-item__pl-on-bottom']//a[@class='s-item__link']//h3[contains(text()

    public void setTxtElementoBusqueda(String producto) {
        this.txtElementoBusqueda = By.xpath("//h3[contains(text(),'"+producto+"')]");
    }
}
  ```

####  La clase steps
  ```
Es donde definimos el paso a paso de las acciones que vamos a realizar en el código a través de la creación de metodos para la correcta funcionalidad de la automatización

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

  ```


### Implementación del Cucumber

Se realiza en los paquetes 

-runners (Clase - Runners )
-stepsDefinition (Clase - stepsDefinition)
-Directorio feature (Archivo.feature)


####  EL runner
  ```
Es el que nos permite ejecutar el proyecto, en este caso se crea runner por escenario presentado desde los features (EbayBackgroundRunner, EbayFallidoYExitosoRunner y EbayRunner)


package runners;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src\\test\\resources\\features\\EbayBuscador.feature",
        glue = "stepsDefinitions",
        snippets = SnippetType.CAMELCASE
)

public class EbayBuscadorRunner {

}
  ```

#### StepsDefinition
  ```
Aqui se definen los paso a paso que se van hacer en la pagina cuando se ejecute la automatización que se componen por el given, when y then presentados en cada uno de los escenarios presentados en el feature.


package stepsDefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import steps.EbaySteps;

public class EbayStepsDefinitions {

  EbaySteps ebaySteps = new EbaySteps();

    @Given("^que me encuentro en la pagina de Ebay$")
    public void queMeEncuentroEnLaPaginaDeEbay() {ebaySteps.abrirPagina();}

    @When("^busque el producto (.*)$")
    public void busqueElProductoLenovoIdeaPadGaming3156120HzLaptopparaJuegosAMDRyzen55600H8GBRam512G(String producto) {
        ebaySteps.buscarElementoEnEbay(producto);
    }

    @Then("^podre ver (.*) en pantalla$")
    public void podreVerLenovoIdeaPadGaming3156120HzLaptopparaJuegosAMDRyzen55600H8GBRam512GEnPantalla(String producto) {
        ebaySteps.validarElementoEnPantalla(producto);
    }
}

  ```

#### Los feature
  ```
Es donde se arma la historia de usuario, la cual tiene una estructura inicial y final, para este reto se generaron tres tipos de feature:
 
 ```
      ##### Ejecutado con la lista de productos desde el feature
      Feature: HU-001 Buscador Ebay
      Yo como usuario de Ebay
      Quiero buscar un producto en la plataforma
      Para ver el nombre del producto en pantalla

      Scenario Outline: Buscar producto
      Given que me encuentro en la pagina de Ebay
      When busque el producto <NombreProducto>
      Then podre ver <NombreProducto> en pantalla
      Examples:
      |NombreProducto|
      |Lenovo IdeaPad Gaming 3 15.6 120Hz Laptop para Juegos AMD Ryzen 5-5600H 8GB Ram 512G|
      |Controlador inalámbrico Xbox choque Azul-inalámbrico y conectividad Bluetooth-Nuevo|
      |Samsung Galaxy S9 G960 Verizon TracFone Straight Talk total liberado B Page Plus|
      |Cable para Samsung Galaxy S20 S21 Cable Usb-C A USB-Carga Tipo C Charger C|
      |Tcl 43P615K 43 pulgadas 4K Ultra HD Smart Android Tv|
	  
      ##### Ejecutado con dos escenarios
      Feature: HU-001 Buscador Ebay
      Yo como usuario de Ebay
      Quiero buscar un producto en la plataforma
      Para ver el nombre del producto en pantalla

      Background: Navegar en la pagina
      Given que me encuentro en la pagina de Ebay

      Scenario Outline: Busqueda de un producto
      When Encontrar un producto
      |<Producto>|
      Then voy a visualizar <Producto> en pantalla
      Examples:
      |Producto|
      |Google Pixel 3 XL 64GB Desbloqueado de fábrica pantalla de 6.3" 4G LTE Smartphone Nuevo|

      Scenario Outline: Busqueda de un producto
      When Encontrar un producto
      |<Producto>|
      Then voy a visualizar <Producto> en pantalla
      Examples:
      |Producto|
      |Auriculares Bluetooth para iPhone Samsung Android Inalámbrico Auriculares IPX7 Impermeable|
	  
	  ##### Ejecutado con escenarios fallido y exitoso
      Feature: HU-001 Buscador Ebay
      Yo como usuario de Ebay
      Quiero buscar un producto en la plataforma
      Para ver el nombre del producto en pantalla

      Scenario: Busqueda exitosa de un producto
      Given que me encuentro en la pagina de Ebay
      When Hallar un producto
      |Auriculares Bluetooth para iPhone Samsung Android Inalámbrico Auriculares IPX7 Impermeable|
      Then Habilitar en pantalla
      |Auriculares Bluetooth para iPhone Samsung Android Inalámbrico Auriculares IPX7 Impermeable|

      Scenario: Busqueda fallida de un producto
      Given que me encuentro en la pagina de Ebay
      When Hallar un producto
      |Google Pixel 2 XL 64GB Unlocked|
      Then Habilitar en pantalla
      |Google Pixel 2 XL 64GB Unlocked0|e|

 ```
