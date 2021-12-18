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
