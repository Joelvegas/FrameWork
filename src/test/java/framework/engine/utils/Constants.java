package framework.engine.utils;

import lombok.extern.apachecommons.CommonsLog;

import java.util.Properties;

@CommonsLog
public class Constants {

    private Constants() {
    }

    static Properties properties = LoadProperties.loadProperties();
    public static final String BASE_URL_AUT = properties.getProperty("url.aut");
    public static final String IS_DEBUG = properties.getProperty("debug");
    public static final String BROWSER = properties.getProperty("browser");
    public static final String RUNTIME_FILENAME = properties.getProperty("runtime.filename");
    public static final String DATATABLES_PATH = properties.getProperty("datatables.path");
    public static final String DESCRIPTIONS_PATH = properties.getProperty("descriptions.path");
    public static final String ESPERA_HASTA = properties.getProperty("esperaHasta");
    public static final String TIEMPO_ESPERA = properties.getProperty("tiempoEspera");
    public static final String ROOT_PATH = properties.getProperty("pathArchivo");
    public static String EXCEPTION = null;
}
