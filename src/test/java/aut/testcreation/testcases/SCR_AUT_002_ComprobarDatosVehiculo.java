package aut.testcreation.testcases;

import aut.testcreation.pages.Page_Appian;
import framework.engine.selenium.SeleniumTestBase;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;

import static framework.engine.utils.ExcelReader.*;
import static org.junit.jupiter.api.Assertions.*;

public class SCR_AUT_002_ComprobarDatosVehiculo extends SeleniumTestBase {

    @BeforeEach
    void TC_002_01_antes() throws IOException, InvalidFormatException, AWTException {
        getTestName(getMethodName());
        Page_Appian.NavegarURL(extractDataToExcel("pUrl"));
        Page_Appian.clickearbtnIAgree();
        reporte.reportarEvento("", "", true, true);
        Page_Appian.ingresarfldUsername(extractDataToExcel("pUsername"));
        Page_Appian.ingresarfldPassword(extractDataToExcel("pPassword"));
        reporte.tomarCaptura();
        Page_Appian.clickearbtnSignIn();
        reporte.reportarEvento("", "", true, true);
    }

    @Test
    void TC_002_02_proximoMantenimientoCorrecto() throws InterruptedException, IOException, InvalidFormatException, AWTException {
        getTestName(getMethodName());
        String lEsperado = Page_Appian.obtenerValorlblPrimerNextMaintenanceDate();
        Page_Appian.clickearlnkPrimerVIN();
        reporte.reportarEvento("", "", true, false);
        boolean lEstado = Page_Appian.obtenerValorlblNextMaintenanceDateVehicle().equals(lEsperado);
        reporte.reportarEvento("", lEsperado, lEstado, false);
        assertEquals(lEsperado, Page_Appian.obtenerValorlblNextMaintenanceDateVehicle());
    }

    /*@Test
    void TC_002_03_navegaPaginaInformacion() throws IOException, InterruptedException, InvalidFormatException, AWTException {
        getTestName(getMethodName());
        String lEsperado = extractDataToExcel("pEsperado");
        Page_Appian.clickearlnkPrimerVIN();
        reporte.reportarEvento("click", true, false);
        boolean lEstado = Page_Appian.obtenerValorlblTituloPagVehiculos().equals(lEsperado);
        reporte.reportarEvento(lEsperado, lEstado, false);
        assertEquals(lEsperado, Page_Appian.obtenerValorlblTituloPagVehiculos());
    }

    @Test
    void TC_002_04_fechasOrdenadasFormaAscendente() throws InterruptedException, ParseException, IOException, InvalidFormatException, AWTException {
        getTestName(getMethodName());
        Page_Appian.clickearlnkPrimerVIN();
        reporte.reportarEvento("true", Page_Appian.sonFechasAscendentes(), false);
        assertTrue(Page_Appian.sonFechasAscendentes());
    }*/

    @Test
    void TC_002_05_verificarRedireccionVehiculo() throws InterruptedException, IOException, InvalidFormatException, AWTException {
        getTestName(getMethodName());
        String lNombreVehiculo = Page_Appian.crearNombreVehiculo();
        reporte.reportarEvento("", "", true, false);
        Page_Appian.clickearlnkPrimerVIN();
        reporte.reportarEvento("", "", true, false);
        String lNombreVehiculoRedir = Page_Appian.obtenerValorlblTituloNombreVehiculo();
        boolean lEstado = lNombreVehiculo.equals(lNombreVehiculoRedir);
        reporte.reportarEvento("", lNombreVehiculo, lEstado, false);
        assertEquals(lNombreVehiculo, lNombreVehiculoRedir);
    }

    /*@Test
    void TC_002_06_estadisticaVehiculosPorMarcaCorrecta() throws InterruptedException, IOException, InvalidFormatException, AWTException {
        int lCantVehiculosPorMarca = Page_Appian.obtenerCantidadVehiculosPorMarca("Ford");
        Page_Appian.clickearbtnFleetReport();

        assertEquals(lCantVehiculosPorMarca, 9);
    }*/
}
