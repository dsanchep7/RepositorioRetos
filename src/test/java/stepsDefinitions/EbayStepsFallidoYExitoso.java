package stepsDefinitions;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import steps.EbaySteps;

import java.util.List;

public class EbayStepsFallidoYExitoso {
    EbaySteps ebaySteps = new EbaySteps();

    @When("^Hallar un producto$")
    public void hallarUnProducto(List<String> Productos) {
        ebaySteps.buscarElementoEnEbay(Productos.get(0));
    }

    @Then("^Habilitar en pantalla$")
    public void habilitarEnPantalla(List<String> Productos) {
        ebaySteps.validarElementoNoExistenteEnPantalla(Productos.get(0));
    }

}
