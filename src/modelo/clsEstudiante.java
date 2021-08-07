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
public class clsEstudiante {
    private String  docEst;
    private String  nomEst;
    private String  apeEst;
    private String  dirEst;
    private String  telEst;    
    public ResultSet datos;
    clsConexion objCon = new clsConexion();
    
//Set y get

    /**
     * @return the docEst
     */
    public String getDocEst() {
        return docEst;
    }

    /**
     * @param docEst the docEst to set
     */
    public void setDocEst(String docEst) {
        this.docEst = docEst;
    }

    /**
     * @return the nomEst
     */
    public String getNomEst() {
        return nomEst;
    }

    /**
     * @param nomEst the nomEst to set
     */
    public void setNomEst(String nomEst) {
        this.nomEst = nomEst;
    }

    /**
     * @return the apeEst
     */
    public String getApeEst() {
        return apeEst;
    }

    /**
     * @param apeEst the apeEst to set
     */
    public void setApeEst(String apeEst) {
        this.apeEst = apeEst;
    }

    /**
     * @return the dirEst
     */
    public String getDirEst() {
        return dirEst;
    }

    /**
     * @param dirEst the dirEst to set
     */
    public void setDirEst(String dirEst) {
        this.dirEst = dirEst;
    }

    /**
     * @return the telEst
     */
    public String getTelEst() {
        return telEst;
    }

    /**
     * @param telEst the telEst to set
     */
    public void setTelEst(String telEst) {
        this.telEst = telEst;
    }
    
    // metodos
    
    public void guardar(){
        try{
            objCon.conectar();
            objCon.Sql = objCon.con.prepareStatement("insert into estudiante values(?,?,?,?,?)");
            objCon.Sql.setString(1, getDocEst());
            objCon.Sql.setString(2, getNomEst());
            objCon.Sql.setString(3, getApeEst());
            objCon.Sql.setString(4, getDirEst());
            objCon.Sql.setString(5, getTelEst());
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
            objCon.Sql = objCon.con.prepareStatement("SELECT * FROM estudiante  WHERE docEst=?");
            objCon.Sql.setString(1, getDocEst());
            objCon.Sql.executeQuery();
            datos = objCon.Sql.getResultSet();
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al buscar");
        }
    }
    
    public void eliminar() {
        try {
            objCon.conectar();
            objCon.Sql = objCon.con.prepareStatement("delete from estudiante  where docEst =?");
            objCon.Sql.setString(1, getDocEst());
            objCon.Sql.executeUpdate();
            objCon.cerrar();
            JOptionPane.showMessageDialog(null, "Registro eliminado con éxito");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al eliminar:");
        }
    }

    public void actualizar() {
        try {
            objCon.conectar();
            objCon.Sql = objCon.con.prepareStatement("UPDATE estudiante  SET nomEst =?, apeEst=?, dirEst =?, telEst=? WHERE docEst =?");
            objCon.Sql.setString(1, getNomEst());
            objCon.Sql.setString(2, getApeEst());
            objCon.Sql.setString(3, getDirEst());
            objCon.Sql.setString(4, getTelEst());
            objCon.Sql.setString(5, getDocEst());
            objCon.Sql.executeUpdate();
            objCon.cerrar();
            JOptionPane.showMessageDialog(null, "Registro actualizado con éxito");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al actualizar:");
        }
    }

    
    
}
    

