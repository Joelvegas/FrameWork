package aut.testcreation.steps;

import aut.testcreation.pages.Page_Google;
import framework.engine.bdd.CucumberBaseTestRunner;
import io.cucumber.java8.En;

import static framework.engine.utils.ExcelReader.*;
import static org.junit.jupiter.api.Assertions.*;


public class GoogleSteps extends CucumberBaseTestRunner implements En{

    public GoogleSteps(){

        Before(1,CucumberBaseTestRunner::setUp);
        After(CucumberBaseTestRunner::tearDown);

        Given("que estoy en el Home de Google", Page_Google::navegarAlHome);

        When("busco la palabra {string} en el navegador", (String pBusqueda) -> {
            String lBusqueda = extractDataToExcel(pBusqueda);
            Page_Google.ingresarfldBuscar(lBusqueda);
        });

        When("presiono el boton buscar", () -> {
            Page_Google.clickearbtnBuscarConGoogle(); //<-- Preguntar ----------------------------------------------------
        });

        Then("me lleva a la pagina de resultados {string}", (String pEsperado) -> {
            setDataToExcel("pBusqueda", "gatitos", "SC_001_03_buscarScotiaBank");
            String lEsperado = extractDataToExcel(pEsperado);
            boolean lEstado = Page_Google.obtenerTituloUrl().equals(lEsperado);
            reporte.reportarEvento("me lleva a la pagina de resultados", lEsperado, lEstado, false);
            assertEquals(lEsperado, Page_Google.obtenerTituloUrl());
        });
    }
}
