package framework.engine.selenium;

import aut.testcreation.pages.Page_Google;
import framework.engine.utils.ConfigReport;
import framework.engine.utils.LoadProperties;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

import java.awt.*;
import java.io.IOException;
import java.util.Properties;

import static framework.engine.utils.Constants.BROWSER;
import static framework.engine.utils.LoadProperties.extraerParametro;

public class SeleniumTestBase {

    private DriverFactory driverFactory;
    static WebDriver driver;
    static Properties properties;
    public static ConfigReport reporte;
    public static Page_Google pageGoogle;

    /**
     * Se ejecuta una vez antes de todos los test. Carga las propiedades.
     */
    @BeforeAll
    public static void LoadProperties(){
        properties = LoadProperties.loadProperties();
    }

    /**
     * Se ejecuta antes de cada test. Inicializa el driver y crea una nueva instancia de las Pages que se utilizarán.
     * @throws IOException
     * @throws InvalidFormatException
     */
    @BeforeEach
    void setUp() throws IOException, InvalidFormatException {
        driverFactory = new DriverFactory();
        driver = driverFactory.inicializarDriver(BROWSER);
        reporte = new ConfigReport();
        pageGoogle = new Page_Google(DriverFactory.getDriver());
    }

    /**
     * Se ejecuta después de cada test. Cierra el navegador quitando el driver y finaliza el reporte de Word.
     * @throws IOException
     */
    @AfterEach
    void close() throws IOException {
        finalizar();
    }

    /**
     * Recibe el nombre del método del test actual y lo guarda en el archivo runtime.properties.
     * @param pTestName
     * @throws IOException
     */
    public static void getTestName(String pTestName) throws IOException {
        LoadProperties.getEscenario(pTestName);
    }

    /**
     * Obtiene el nombre del método del test actual y lo retorna.
     * @return El nombre del método actual o undefined.
     */
    public static String getMethodName(){
        if (Thread.currentThread().getStackTrace().length>2) {
            return Thread.currentThread().getStackTrace()[2].getMethodName();
        } else {
            return "undefined";
        }
    }

    public static void finalizar() throws IOException {
        driver.quit();
        reporte.finalizar(extraerParametro("Escenario"));
    }
}
