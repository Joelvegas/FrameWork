package aut.testcreation.testcases;

import aut.testcreation.pages.Page_Google;
import framework.engine.selenium.SeleniumTestBase;
import framework.engine.utils.ExcelReader;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.jupiter.api.*;

import java.awt.*;
import java.io.IOException;

import static framework.engine.utils.ExcelReader.*;
import static org.junit.jupiter.api.Assertions.*;


@TestMethodOrder(MethodOrderer.MethodName.class)
public class SCR_AUT_001_RealizarBusquedas extends SeleniumTestBase {

    @Test
    void TC_001_04_VerificarBusqueda() throws IOException, InvalidFormatException, AWTException, InterruptedException {
        getTestName(getMethodName());
        String lExpected = extractDataToExcel("pEsperado");
        Page_Google.navegarAlHome();
        Page_Google.ingresarfldBuscar(extractDataToExcel("pBusqueda"));
        Page_Google.clickearbtnBuscarConGoogle();

        setDataToExcel("pBusqueda", "Chile", "TC_001_05_buscarMexico");

        boolean lEstado = Page_Google.obtenerTituloUrl().equals(lExpected);
        reporte.reportarEvento("", lExpected, lEstado, false);
        assertEquals(lExpected, Page_Google.obtenerTituloUrl());
    }

    @Test
    void TC_001_05_buscarMexico() throws IOException, InvalidFormatException, AWTException, InterruptedException {
        getTestName(getMethodName());
        int pEstado = 0;
        String lExpected = extractDataToExcel("pEsperado");
        Page_Google.navegarAlHome();
        Page_Google.ingresarfldBuscar(extractDataToExcel("pBusqueda"));
        Page_Google.clickearbtnBuscarConGoogle();

        boolean lEstado = Page_Google.obtenerTituloUrl().equals(lExpected);
        reporte.reportarEvento("", lExpected, lEstado, false);
        assertEquals(lExpected, Page_Google.obtenerTituloUrl());
    }

    @Test
    void TC_001_06_testConIframe() throws InterruptedException, IOException, InvalidFormatException, AWTException {
        getTestName(getMethodName());
        Page_Google.NavegarURL(extractDataToExcel("pUrl"));
        Page_Google.clickearbtnEnviarA();
        Page_Google.cambiarApupIngresarCP();
        Page_Google.ingresarfldCP(extractDataToExcel("pCP"));
        Page_Google.clickearbtnUsar();
        Page_Google.esperaEnSegundos();
        Page_Google.clickearbtnIngresa();
        Page_Google.esperaEnSegundos();
    }

    @Test
    void TC_001_07_navegarMenuFlotante() throws IOException, InterruptedException, InvalidFormatException, AWTException {
        getTestName(getMethodName());
        Page_Google.NavegarURL(extractDataToExcel("pUrl"));
        /*Page_Google.posarseEnbtnProductosDeInversion();
        Page_Google.esperaEnSegundos();
        Page_Google.clickearlnkEducacionFinanciera();
        Page_Google.esperaEnSegundos();*/
        Page_Google.clickearlnkPodcast();
        Page_Google.esperaEnSegundos();
    }
}
