package com.PubliciBot.Services;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * Created by Hugo on 25/06/2017.
 */
public class Utils {



    public static String getProperty(String nombreProperty)
    {
        return System.getProperty(nombreProperty);
    }

    public static String generarNombreArchivoImagen()
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return dateFormat.format(new Date()) + ".png";
    }
}
