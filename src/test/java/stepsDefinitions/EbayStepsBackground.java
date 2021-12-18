package stepsDefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import steps.EbaySteps;

import java.util.List;

public class EbayStepsBackground {
    EbaySteps ebaySteps = new EbaySteps();

    /*
    @Given("^que me encuentro en la pagina de Ebay$")
    public void queMeEncuentroEnLaPaginaDeEbay() {ebaySteps.abrirPagina();}
*/
    @When("^Encontrar un producto$")
    public void encontrarUnProducto(List<String> Productos) {
    ebaySteps.buscarElementoEnEbay(Productos.get(0));
    }

    @Then("^voy a visualizar (.*) en pantalla$")
    public void voyAVisualizarEnPantalla(List<String>Productos) {
    ebaySteps.validarElementoEnPantalla(Productos.get(0));
    }
}
