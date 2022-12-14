package aut.testcreation.pages;

import framework.engine.selenium.SeleniumWrapper;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.awt.*;
import java.io.IOException;

import static framework.engine.utils.Constants.BASE_URL_AUT;

public class Page_Google extends SeleniumWrapper {

    public Page_Google(WebDriver driver) {
        super(driver);
    }

    //locators
    static By fldBuscar = By.name("q");
    static By btnBuscarConGoogle = By.name("btnK");
    static By btnVoyATenerSuerte = By.name("btnI");
    static By btnEnviarA = By.xpath("(//a[@data-js='cp'])[1]");
    static By pupIngresarCP = By.xpath("(//iframe)[3]");
    static By fldCP = By.xpath("//input[@data-testid='zip-code-textfield']");

    static By btnUsar = By.xpath("//button[@data-testid='button-use-zipcode']");
    static  By btnIngresa = By.xpath("//a[@data-link-id='login']");
    static By btnProductosDeInversion = By.xpath("//a[@data-overlaytext='Productos de Inversión']");
    static By lnkEducacionFinanciera = By.xpath("//a[@title='Educación Financiera']");
    static By lnkPodcast = By.xpath("//a[contains(text(), 'Podcast')]");

    //methods
    public static void ingresarfldBuscar(String pBusqueda) throws InterruptedException, IOException, InvalidFormatException, AWTException {
        escribirEnInput(fldBuscar, pBusqueda);
    }

    public static void clickearbtnBuscarConGoogle() throws InterruptedException, IOException, InvalidFormatException, AWTException {
        clickear(btnBuscarConGoogle);
    }

    public static void clickearbtnVoyATenerSuerte() throws InterruptedException, IOException, InvalidFormatException, AWTException {
        clickear(btnVoyATenerSuerte);
    }

    public static void navegarAlHome() throws InterruptedException {
        NavegarURL(BASE_URL_AUT);
    }

    public static void clickearbtnEnviarA() throws InterruptedException, IOException, InvalidFormatException, AWTException {
        clickear(btnEnviarA);
    }

    public static void cambiarApupIngresarCP() throws IOException, InvalidFormatException, AWTException {
        cambiarAIframe(pupIngresarCP);
    }

    public static void ingresarfldCP(String pCP) throws InterruptedException, IOException, InvalidFormatException, AWTException {
        escribirEnInput(fldCP, pCP);
    }

    public static void clickearbtnUsar() throws InterruptedException, IOException, InvalidFormatException, AWTException {
        clickear(btnUsar);
    }

    public static void clickearbtnIngresa() throws InterruptedException, IOException, InvalidFormatException, AWTException {
        clickear(btnIngresa);
    }

    public static void posarseEnbtnProductosDeInversion() throws IOException, InvalidFormatException, AWTException {
        sobre(btnProductosDeInversion);
    }

    public static void clickearlnkEducacionFinanciera() throws InterruptedException, IOException, InvalidFormatException, AWTException {
        clickear(lnkEducacionFinanciera);
    }

    public static void clickearlnkPodcast() throws InterruptedException, IOException, InvalidFormatException, AWTException {
        clickear(lnkPodcast);
    }
}
