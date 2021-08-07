/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;

/**
 *
 * @author 
 */
public class clsConexion {
    public PreparedStatement Sql;
    public Connection con=null;
    
    //metodo que controla la conexion
    public void conectar(){
        String db = "jdbc:mysql://localhost:3306/colegio";
        String usuario = "root";
        String password = ""; //la contraseña que tiene la Base de Datos
        try{
            String controlador = "com.mysql.cj.jdbc.Driver";
            Class.forName(controlador);
            con = DriverManager.getConnection(db,usuario,password);
            //JOptionPane.showMessageDialog(null,"Conexion exitosa");
            System.out.println("Conexion exitosa");
        } catch (Exception ex){
            JOptionPane.showMessageDialog(null, "Error al conectar" +ex);
        }
    }
    
    public void cerrar(){
        if(con == null){
            try {
                con.close();
            } catch (Exception ex){
                System.out.println("No se ha cerrado la conexion con la bd");
            }
        }
    }
}
