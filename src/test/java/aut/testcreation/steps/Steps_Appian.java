package aut.testcreation.steps;

import aut.testcreation.pages.Page_Appian;
import framework.engine.bdd.CucumberBaseTestRunner;
import io.cucumber.java8.En;
import io.cucumber.java8.Scenario;

import static framework.engine.utils.ExcelReader.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Steps_Appian extends CucumberBaseTestRunner implements En {
    public Steps_Appian(){

        Before(1,CucumberBaseTestRunner::setUp);
        After(CucumberBaseTestRunner::tearDown);

        Given("El Usuario navega a la página de Login {string}", (String pUrl) -> {
            Page_Appian.NavegarURL(extractDataToExcel(pUrl));
        });

        And("El Usuario clickea el botón I Agree", () -> {
            Page_Appian.clickearbtnIAgree();
            reporte.reportarEvento("El Usuario clickea el botón I Agree", "", true, true);
        });

        And("El Usuario ingresa el nombre de Usuario {string}", (String pUsername) -> {
            Page_Appian.ingresarfldUsername(extractDataToExcel(pUsername));
        });

        And("El Usuario ingresa la Contraseña {string}", (String pPassword) -> {
            Page_Appian.ingresarfldPassword(extractDataToExcel(pPassword));
            reporte.reportarEvento("El Usuario ingresa la Contraseña", "", true, true);
        });

        And("El Usuario clickea el botón Sign In", () -> {
            Page_Appian.clickearbtnSignIn();
            reporte.reportarEvento("El Usuario clickea el botón Sign In", "", true, true);
        });

        When("El Usuario obtiene el valor del Próximo Mantenimiento del primer Vehiculo", () -> {
            String lEsperado = Page_Appian.obtenerValorlblPrimerNextMaintenanceDate();
            setDataToExcel("pEsperado", lEsperado, "");
        });

        And("El Usuario clickea en el primer VIN", () -> {
            Page_Appian.clickearlnkPrimerVIN();
            reporte.reportarEvento("El Usuario clickea en el primer VIN", "", true, false);
        });

        And("El Usuario obtiene el valor del Próximo Mantenimiento en la página de información del Vehiculo", () -> {
            String lActual = Page_Appian.obtenerValorlblNextMaintenanceDateVehicle();
            setDataToExcel("pActual", lActual, "");
        });

        Then("El Usuario compara que ambos Valores sean iguales", () -> {
            String lActual = extractDataToExcel("pActual");
            String lEsperado = extractDataToExcel("pEsperado");
            boolean lEstado = lActual.equals(lEsperado);
            reporte.reportarEvento("El Usuario compara que ambos Valores sean iguales", lEsperado, lEstado, false);
            assertEquals(lEsperado, lActual);
        });

        When("El Usuario obtiene el Nombre del vehiculo", () -> {
            String lEsperado = Page_Appian.crearNombreVehiculo();
            setDataToExcel("pEsperado", lEsperado, "");
            reporte.reportarEvento("El Usuario obtiene el Nombre del vehiculo", "", true, false);
        });

        And("El Usuario obtiene el Nombre del vehiculo en la página de información del Vehiculo", () -> {
            String lActual = Page_Appian.obtenerValorlblTituloNombreVehiculo();
            setDataToExcel("pActual", lActual, "");
            reporte.reportarEvento("El Usuario obtiene el Nombre del vehiculo en la página de información del Vehiculo", "Nombre obtenido desde la página de información", true, false);
        });
    }
}
