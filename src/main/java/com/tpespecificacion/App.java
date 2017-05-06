package com.tpespecificacion;

import org.apache.commons.lang3.StringEscapeUtils;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {


        System.out.println( "Hello World!" );
        //Para testear que anda Maven,
        // lo que esta abajo necesita de librerias
        // y se deberian bajar solas
        String p="<p>My paragraph.</p>";
        System.out.println(StringEscapeUtils.escapeHtml4(p));
    }
}
