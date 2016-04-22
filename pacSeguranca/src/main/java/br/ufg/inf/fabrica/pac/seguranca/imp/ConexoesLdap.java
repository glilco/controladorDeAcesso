/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.fabrica.pac.seguranca.imp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 *
 * @author auf
 */
public class ConexoesLdap {

    private static String PATH_ADMIN;
    private static String KEY_ADMIN;
    private static String USER_ADMIN;
    private static String INITIAL_CTX;
    private static String SERVIDOR_LDAP;
    private static String SEARCHBASE;
    private static String BASE_DN;

    public static void lerParametros() {
        String pathSistema = System.getenv("SIGERA_CONF_PROPERTIES");

        if (pathSistema == null) {        //tratar
            //tratar exceção             
            throw new NotImplementedException();
        }

        File file = new File(pathSistema);
        if (!file.exists()) {
            throw new NotImplementedException();
        }

        InputStream input = null;
        try {
            input = new FileInputStream(file);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ConexoesLdap.class.getName()).log(Level.SEVERE, null, ex);
        }
        Properties prop = new Properties();
        try {
            prop.load(input);
        } catch (IOException ex) {
            Logger.getLogger(ConexoesLdap.class.getName()).log(Level.SEVERE, null, ex);
        }

        ConexoesLdap.setPATH_ADMIN(prop.getProperty("PATH_ADMIN"));
        ConexoesLdap.setKEY_ADMIN(prop.getProperty("KEY_ADMIN"));
        ConexoesLdap.setUSER_ADMIN(prop.getProperty("USER_ADMIN"));
        ConexoesLdap.setINITIAL_CTX(prop.getProperty("INITIAL_CTX"));
        ConexoesLdap.setSERVIDOR_LDAP(prop.getProperty("SERVIDOR_LDAP"));
        ConexoesLdap.setSEARCHBASE(prop.getProperty("SEARCHBASE"));
        ConexoesLdap.setBASE_DN(prop.getProperty("BASE_DN"));

    }

    public static void setPATH_ADMIN(String PATH_ADMIN) {
        ConexoesLdap.PATH_ADMIN = PATH_ADMIN;
    }

    public static void setKEY_ADMIN(String KEY_ADMIN) {
        ConexoesLdap.KEY_ADMIN = KEY_ADMIN;
    }

    public static void setUSER_ADMIN(String USER_ADMIN) {
        ConexoesLdap.USER_ADMIN = USER_ADMIN;
    }

    public static void setINITIAL_CTX(String INITIAL_CTX) {
        ConexoesLdap.INITIAL_CTX = INITIAL_CTX;
    }

    public static void setSERVIDOR_LDAP(String SERVIDOR_LDAP) {
        ConexoesLdap.SERVIDOR_LDAP = SERVIDOR_LDAP;
    }

    public static void setSEARCHBASE(String SEARCHBASE) {
        ConexoesLdap.SEARCHBASE = SEARCHBASE;
    }

    public static void setBASE_DN(String BASE_DN) {
        ConexoesLdap.BASE_DN = BASE_DN;
    }

    public static String getPATH_ADMIN() {
        return PATH_ADMIN;
    }

    public static String getKEY_ADMIN() {
        return KEY_ADMIN;
    }

    public static String getUSER_ADMIN() {
        return USER_ADMIN;
    }

    public static String getINITIAL_CTX() {
        return INITIAL_CTX;
    }

    public static String getSERVIDOR_LDAP() {
        return SERVIDOR_LDAP;
    }

    public static String getSEARCHBASE() {
        return SEARCHBASE;
    }

    public static String getBASE_DN() {
        return BASE_DN;
    }

}
