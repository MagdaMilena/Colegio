/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import controlador.clsConexion;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author 
 */
public class clsMateria {
    private Integer codMat;
    private String nomMat;
    private String graMat;
    
    public ResultSet datos;
    public ResultSet contCod;
    clsConexion objCon = new clsConexion();
    
    //Gett and Sett

    /**
     * @return the codMat
     */
    public Integer getCodMat() {
        return codMat;
    }

    /**
     * @param codMat the codMat to set
     */
    public void setCodMat(Integer codMat) {
        this.codMat = codMat;
    }

    /**
     * @return the nomMat
     */
    public String getNomMat() {
        return nomMat;
    }

    /**
     * @param nomMat the nomMat to set
     */
    public void setNomMat(String nomMat) {
        this.nomMat = nomMat;
    }

    /**
     * @return the graMat
     */
    public String getGraMat() {
        return graMat;
    }

    /**
     * @param graMat the graMat to set
     */
    public void setGraMat(String graMat) {
        this.graMat = graMat;
    }
    
    //Metodos
    
    public void guardar(){
        try{
            objCon.conectar();
            objCon.Sql = objCon.con.prepareStatement("insert into materia (nomMat,graMat) values(?,?)");
            objCon.Sql.setString(1, getNomMat());
            objCon.Sql.setString(2, getGraMat());
            objCon.Sql.executeUpdate();
            objCon.cerrar();
            JOptionPane.showMessageDialog(null, "Registro guardado con éxito");        
        }catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al guardar ");

        }
    }
    
    public void buscar() {
        try {
            objCon.conectar();
            objCon.Sql = objCon.con.prepareStatement("SELECT * FROM materia  WHERE codMat=?");
            objCon.Sql.setInt(1, getCodMat());
            objCon.Sql.executeQuery();
            datos = objCon.Sql.getResultSet();
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al buscar");
        }
    }
    
    public void actualizar() {
        try {
            objCon.conectar();
            objCon.Sql = objCon.con.prepareStatement("UPDATE materia  SET nomMat=?, graMat=? WHERE codMat=?");
            objCon.Sql.setString(1, getNomMat());
            objCon.Sql.setString(2, getGraMat());
            objCon.Sql.setInt(3, getCodMat());
            objCon.Sql.executeUpdate();
            objCon.cerrar();
            JOptionPane.showMessageDialog(null, "Registro actualizado con éxito");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al actualizar:");
        }
    }
    
    public void contar(){
        try {
            objCon.conectar();
            objCon.Sql=objCon.con.prepareStatement("select count(codMat)+1 from materia");
            objCon.Sql.executeQuery();
            contCod=objCon.Sql.getResultSet();
        } catch (Exception e) {
            System.out.println(""+e);
        }
    }
    
    
}
