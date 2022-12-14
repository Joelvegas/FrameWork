package aut.testcreation.pages;

import framework.engine.selenium.SeleniumWrapper;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.awt.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class Page_Appian extends SeleniumWrapper {

    public Page_Appian(WebDriver driver) {
        super(driver);
    }

    //Localizadores
    static By lstTabla = By.tagName("table");
    static By lstBodyTabla = By.tagName("tbody");
    static By lblTituloPagVehiculos = By.tagName("h4");
    static By btnIAgree = By.xpath("//input[@type='button']");
    //static By btnIAgree = By.tagName("tbody");
    static By fldUsername = By.id("un");
    static By fldPassword = By.id("pw");
    static By btnSignIn = By.xpath("//input[@type='submit']");
    //static By btnSignIn = By.tagName("dasdasdasd");
    static By lstFila = By.xpath("//tbody/descendant::tr");
    static By lnkPrimerVIN = By.xpath("(//p/a)[1]");
    //static By lnkPrimerVIN = By.xpath("bjkhgkgjkgjk");
    static By fldSearch = By.xpath("//input[@placeholder='Search AS Vehicles']");
    static By btnSearch = By.xpath("//button[text()='Search']");
    static By btnFleetReport = By.xpath("//li[@title='Fleet Report']");

    static By lblTituloNombreVehiculo = By.tagName("h1");
    static By lblNextMaintenanceDateVehicle = By.xpath("(//div[@role='presentation']/descendant::div/p)[12]");
    static By lstCeldasPrimeraFila = By.xpath("//tbody/descendant::tr");

    //Métodos
    public static void clickearbtnIAgree() throws IOException, InvalidFormatException, AWTException {
        clickear(btnIAgree);
    }

    public static void ingresarfldUsername(String pUsuario) throws IOException, InvalidFormatException, AWTException {
        escribirEnInput(fldUsername, pUsuario);
    }

    public static void ingresarfldPassword(String pContrasenia) throws IOException, InvalidFormatException, AWTException {
        escribirEnInput(fldPassword, pContrasenia);
    }

    public static void clickearbtnSignIn() throws IOException, InvalidFormatException, AWTException {
        clickear(btnSignIn);
    }

    public static void clickearlnkPrimerVIN() throws IOException, InvalidFormatException, AWTException {
        clickear(lnkPrimerVIN);
    }

    public static int numeroColPrueba() throws IOException, InvalidFormatException, AWTException {
        //esperaEnSegundos(10);
        return obtenerNumerosColumnas(lstBodyTabla);
    }

    public static void clickearbtnFleetReport() throws IOException, InvalidFormatException, AWTException {
        clickear(btnFleetReport);
    }

    /**
     * Obtiene y retorna un String con la fecha del proximo mantenimiento del primer elemento de la tabla principal
     * @return String: fecha del proximo mantenimiento
     * @throws InterruptedException
     */
    public static String obtenerValorlblPrimerNextMaintenanceDate() throws InterruptedException, IOException, InvalidFormatException, AWTException {
        return obtenerValorCelda(lstBodyTabla, 0, 4);
    }

    public static String obtenerValorlblNextMaintenanceDateVehicle() throws InterruptedException, IOException, InvalidFormatException, AWTException {
        esperaEnSegundos();
        return convertirFecha(obtenerTexto(lblNextMaintenanceDateVehicle));
    }

    public static String obtenerValorlblTituloPagVehiculos() throws InterruptedException, IOException, InvalidFormatException, AWTException {
        esperarPresenciaElemento(lblTituloPagVehiculos);
        return obtenerTexto(lblTituloPagVehiculos);
    }

    /**
     * Obtiene las fechas de los mantenimientos de un vehiculo y verifica de que estén bien ordenadas, de ser el caso devuelve true, caso contrario, false.
     * @return boolean: respuesta sobre si las fechas están ordenadas de forma ascendente
     * @throws InterruptedException
     * @throws ParseException
     */
    public static boolean sonFechasAscendentes() throws InterruptedException, ParseException, IOException, InvalidFormatException, AWTException {
        esperaEnSegundos();
        int lCont = 0;
        int lTamanioFilas = obtenerNumeroElementos(lstCeldasPrimeraFila);
        Date[] lFechas = new Date[lTamanioFilas];
        SimpleDateFormat lFormato = new SimpleDateFormat("MM/dd/yyyy");
        for (int i = 0; i < lTamanioFilas; i++) {
            lFechas[i] = lFormato.parse(obtenerValorCelda(lstBodyTabla, i, 5));
        }
        Date lFechaAux = lFechas[0];
        for (int i = 0; i < lTamanioFilas; i++) {
            if(lFechas[i].after(lFechaAux) || lFechas[i].equals(lFechaAux)){
                lFechaAux = lFechas[i];
                lCont++;
            }
        }
        return lCont == lTamanioFilas;
    }

    /**
     * Crea y devuelve un String con el nombre del vehiculo formado por el año, marca y modelo del mismo.
     * Ejemplo: 2019 Ford F150
     * @return String: Nombre del vehiculo
     * @throws InterruptedException
     */
    public static String crearNombreVehiculo() throws InterruptedException, IOException, InvalidFormatException, AWTException {
        String lMake = obtenerValorCelda(lstBodyTabla, 0, 1);
        String lModel = obtenerValorCelda(lstBodyTabla, 0, 2);
        String lYear = obtenerValorCelda(lstBodyTabla, 0, 3);
        return lYear + " " + lMake + " " + lModel;
    }

    public static String obtenerValorlblTituloNombreVehiculo() throws InterruptedException, IOException, InvalidFormatException, AWTException {
        esperaEnSegundos();
        return obtenerTexto(lblTituloNombreVehiculo);
    }

    public static int obtenerCantidadVehiculosPorMarca(String pMarca) throws InterruptedException, IOException, InvalidFormatException, AWTException {
        int lCantFilas = obtenerNumerosFilas(lstBodyTabla);
        int lCantVehiculosPorMarca = 0;
        for (int i = 0; i < lCantFilas; i++) {
            if (obtenerValorCelda(lstBodyTabla, i, 1).equals(pMarca))
                lCantVehiculosPorMarca++;
        }
        return lCantVehiculosPorMarca;
    }

    /**
     * Convierte una String de una fecha de tipo "Month dd, yyyy" en un String de una fecha de tipo "MM/dd/yyyy".
     * Ejemplo: December 8, 2020 -> 12/8/2020
     * @param pFecha Fecha sin formatear
     * @return String: fecha formateada
     */
    private static String convertirFecha(String pFecha){
        pFecha = pFecha.trim();
        String [] lFecha = pFecha.split(" ");
        String lDia = lFecha[1];
        String lMes = lFecha[0];
        String lAnio = lFecha[2];
        switch (lMes) {
            case "January":
                lMes = "01";
                break;
            case "February":
                lMes = "02";
                break;
            case "March":
                lMes = "03";
                break;
            case "April":
                lMes = "04";
                break;
            case "May":
                lMes = "05";
                break;
            case "June":
                lMes = "06";
                break;
            case "July":
                lMes = "07";
                break;
            case "August":
                lMes = "08";
                break;
            case "September":
                lMes = "09";
                break;
            case "October":
                lMes = "10";
                break;
            case "November":
                lMes = "11";
                break;
            case "December":
                lMes = "12";
                break;
        }
        return lMes + "/" + lDia.replace(",", "") + "/" + lAnio;
    }

/*  public static String elegirVehiculo(int pFila){
        String lMarcaVehiculo;
        List<WebElement> lFilas = obtenerFilas();
        List<WebElement> lFila = lFilas.get(pFila - 1).findElements(By.tagName("td"));
        lMarcaVehiculo = getTextWE(lFila.get(1)) + getTextWE(lFila.get(2)); // -------------------------- Dos acciones en una
        By lnkVehiculo = By.xpath("//a[text()='"+ getTextWE(lFila.get(0)) +"']");
        if (IsDisplayed(lnkVehiculo, 10)){
            //clickWE(lFila.get(0));
            click(lnkVehiculo);
        }
        return lMarcaVehiculo;
    }

    private static List<WebElement> obtenerFilas() {
        List<WebElement> lFilas = null;
        if (IsDisplayed(lstTabla, 10)) {
            lFilas = encontarElementos(lstFila);
        }
        return lFilas;
    }*/

}
