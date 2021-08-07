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
public class clsAcudiente {
    
    private String docAcu;
    private String nomAcu;
    private String apeAcu;
    private String dirAcu;
    private String telAcu;
    private String emaAcu;
    
    private String docEst;    
    
    public ResultSet datos;
    public ResultSet datosCombo;
    public ResultSet datosNomApe;
    public ResultSet datosTable;
    clsConexion objCon = new clsConexion();
    
    //Set y get

    /**
     * @return the docAcu
     */
    public String getDocAcu() {
        return docAcu;
    }

    /**
     * @param docAcu the docAcu to set
     */
    public void setDocAcu(String docAcu) {
        this.docAcu = docAcu;
    }

    /**
     * @return the nomAcu
     */
    public String getNomAcu() {
        return nomAcu;
    }

    /**
     * @param nomAcu the nomAcu to set
     */
    public void setNomAcu(String nomAcu) {
        this.nomAcu = nomAcu;
    }

    /**
     * @return the apeAcu
     */
    public String getApeAcu() {
        return apeAcu;
    }

    /**
     * @param apeAcu the apeAcu to set
     */
    public void setApeAcu(String apeAcu) {
        this.apeAcu = apeAcu;
    }

    /**
     * @return the dirAcu
     */
    public String getDirAcu() {
        return dirAcu;
    }

    /**
     * @param dirAcu the dirAcu to set
     */
    public void setDirAcu(String dirAcu) {
        this.dirAcu = dirAcu;
    }

    /**
     * @return the telAcu
     */
    public String getTelAcu() {
        return telAcu;
    }

    /**
     * @param telAcu the telAcu to set
     */
    public void setTelAcu(String telAcu) {
        this.telAcu = telAcu;
    }

    /**
     * @return the emaAcu
     */
    public String getEmaAcu() {
        return emaAcu;
    }

    /**
     * @param emaAcu the emaAcu to set
     */
    public void setEmaAcu(String emaAcu) {
        this.emaAcu = emaAcu;
    }
    
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

    
    //Metodos
    
    public void guardar(){
        try{
            objCon.conectar();
            objCon.Sql = objCon.con.prepareStatement("insert into acudiente values(?,?,?,?,?,?)");
            objCon.Sql.setString(1, getDocAcu());
            objCon.Sql.setString(2, getNomAcu());
            objCon.Sql.setString(3, getApeAcu());
            objCon.Sql.setString(4, getDirAcu());
            objCon.Sql.setString(5, getTelAcu());
            objCon.Sql.setString(6, getEmaAcu());
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
            objCon.Sql = objCon.con.prepareStatement("SELECT * FROM acudiente  WHERE docAcu=?");
            objCon.Sql.setString(1, getDocAcu());
            objCon.Sql.executeQuery();
            datos = objCon.Sql.getResultSet();
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al buscar");
        }
    }
    
    public void eliminar() {
        try {
            objCon.conectar();
            objCon.Sql = objCon.con.prepareStatement("delete from acudiente  where docAcu =?");
            objCon.Sql.setString(1, getDocAcu());
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
            objCon.Sql = objCon.con.prepareStatement("UPDATE acudiente  SET nomAcu=?, apeAcu=?, dirAcu=?, telAcu=?, emaAcu=? WHERE docAcu=?");
            objCon.Sql.setString(1, getNomAcu());
            objCon.Sql.setString(2, getApeAcu());
            objCon.Sql.setString(3, getDirAcu());
            objCon.Sql.setString(4, getTelAcu());
            objCon.Sql.setString(5, getEmaAcu());
            objCon.Sql.setString(6, getDocAcu());
            objCon.Sql.executeUpdate();
            objCon.cerrar();
            JOptionPane.showMessageDialog(null, "Registro actualizado con éxito");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al actualizar:");
        }
    }
    ///////////
    
    public void comboDocEst() {
        try {
            objCon.conectar();
            objCon.Sql = objCon.con.prepareStatement("SELECT * FROM estudiante ");
            objCon.Sql.executeQuery();
            datosCombo = objCon.Sql.getResultSet();    
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al buscar");
        }
    }
    
    public void busNomApe(){
        try {
            objCon.conectar();
            objCon.Sql = objCon.con.prepareStatement("SELECT concat(nomEst,' ',apeEst) FROM estudiante where docEst=?");
            objCon.Sql.setString(1, getDocEst());
            objCon.Sql.executeQuery();
            datosNomApe = objCon.Sql.getResultSet(); 
        } catch (Exception e) {
        }
    }

   public void agregar(){
       try {
           objCon.conectar();;
           objCon.Sql=objCon.con.prepareStatement("insert into acudienteXEstudiante (docAcu,docEstAcu) values(?,?)");
           objCon.Sql.setString(1, getDocAcu());
           objCon.Sql.setString(2, getDocEst());
           objCon.Sql.executeUpdate();
           objCon.cerrar();
           JOptionPane.showMessageDialog(null, "Registro guardado con éxito");
       } catch (Exception e) {
           System.out.println("Error al guardar");
       }
   } 
    
   public void camposTabla(){
       try {
           objCon.conectar();
           objCon.Sql = objCon.con.prepareStatement("SELECT ae.docEstAcu, es.nomEst, es.apeEst, es.telEst "
                   + "from acudiente ac, estudiante es, acudienteXEstudiante ae "
                   + "where ae.docAcu=? and ae.docAcu=ac.docAcu and ae.docEstAcu=es.docEst;");
           objCon.Sql.setString(1, getDocAcu());
           objCon.Sql.executeQuery();
           datosTable = objCon.Sql.getResultSet(); 
       } catch (Exception e) {
       }
   }
   
}
