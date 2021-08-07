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
public class clsProfesor {
    private String docPro;
    private String nomPro;
    private String apePro;
    private String dirPro;
    private String telPro;
    private String emaPro;
    private String titPro;
    
    private Integer codMat;
    private String nomMat;
    private String graMat;
    
    public ResultSet datosCombo;
    public ResultSet datos;
    public ResultSet datosCodMat;
    public ResultSet datosTable;
    clsConexion objCon = new clsConexion();
    
    //Set y get

    /**
     * @return the docPro
     */
    public String getDocPro() {
        return docPro;
    }

    /**
     * @param docPro the docPro to set
     */
    public void setDocPro(String docPro) {
        this.docPro = docPro;
    }

    /**
     * @return the nomPro
     */
    public String getNomPro() {
        return nomPro;
    }

    /**
     * @param nomPro the nomPro to set
     */
    public void setNomPro(String nomPro) {
        this.nomPro = nomPro;
    }

    /**
     * @return the apePro
     */
    public String getApePro() {
        return apePro;
    }

    /**
     * @param apePro the apePro to set
     */
    public void setApePro(String apePro) {
        this.apePro = apePro;
    }

    /**
     * @return the dirPro
     */
    public String getDirPro() {
        return dirPro;
    }

    /**
     * @param dirPro the dirPro to set
     */
    public void setDirPro(String dirPro) {
        this.dirPro = dirPro;
    }

    /**
     * @return the telPro
     */
    public String getTelPro() {
        return telPro;
    }

    /**
     * @param telPro the telPro to set
     */
    public void setTelPro(String telPro) {
        this.telPro = telPro;
    }

    /**
     * @return the emaPro
     */
    public String getEmaPro() {
        return emaPro;
    }

    /**
     * @param emaPro the emaPro to set
     */
    public void setEmaPro(String emaPro) {
        this.emaPro = emaPro;
    }

    /**
     * @return the titPro
     */
    public String getTitPro() {
        return titPro;
    }

    /**
     * @param titPro the titPro to set
     */
    public void setTitPro(String titPro) {
        this.titPro = titPro;
    }
    
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
    
    //metodos
    
    public void guardar(){
        try{
            objCon.conectar();
            objCon.Sql = objCon.con.prepareStatement("insert into profesor values(?,?,?,?,?,?,?)");
            objCon.Sql.setString(1, getDocPro());
            objCon.Sql.setString(2, getNomPro());
            objCon.Sql.setString(3, getApePro());
            objCon.Sql.setString(4, getDirPro());
            objCon.Sql.setString(5, getTelPro());
            objCon.Sql.setString(6, getEmaPro());
            objCon.Sql.setString(7, getTitPro());
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
            objCon.Sql = objCon.con.prepareStatement("SELECT * FROM profesor  WHERE docPro=?");
            objCon.Sql.setString(1, getDocPro());
            objCon.Sql.executeQuery();
            datos = objCon.Sql.getResultSet();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al buscar");
        }
    }
    
    public void actualizar() {
        try {
            objCon.conectar();
            objCon.Sql = objCon.con.prepareStatement("UPDATE profesor  SET nomPro=?, apePro=?, dirPro=?, telPro=?, emaPro=?, titPro=? WHERE docPro=?");
            objCon.Sql.setString(1, getNomPro());
            objCon.Sql.setString(2, getApePro());
            objCon.Sql.setString(3, getDirPro());
            objCon.Sql.setString(4, getTelPro());
            objCon.Sql.setString(5, getEmaPro());
            objCon.Sql.setString(6, getTitPro());
            objCon.Sql.setString(7, getDocPro());
            objCon.Sql.executeUpdate();
            objCon.cerrar();
            JOptionPane.showMessageDialog(null, "Registro actualizado con éxito");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al actualizar:");
        }
    }

    ///////////////// Profesor x materia  //////////////
    
    public void comboNomMat() {
        try {
            objCon.conectar();
            objCon.Sql = objCon.con.prepareStatement("SELECT * FROM materia ");
            objCon.Sql.executeQuery();
            datosCombo = objCon.Sql.getResultSet();    
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al buscar");
        }
    }
    
    public void buscarCodMat(){
        try {
            objCon.conectar();
            objCon.Sql = objCon.con.prepareStatement("SELECT codMat, graMat FROM materia where nomMat=?");
            objCon.Sql.setString(1, getNomMat());
            objCon.Sql.executeQuery();
            datosCodMat = objCon.Sql.getResultSet();  
        } catch (Exception e) {
        }
    }

    public void guardarMP(){
        try{
            objCon.conectar();
            objCon.Sql = objCon.con.prepareStatement("insert into materiaXProfesor (codMatMatxPro,docProMatxPro,graMatxPro) values(?,?,?)");
            objCon.Sql.setInt(1, getCodMat());
            objCon.Sql.setString(2, getDocPro());
            objCon.Sql.setString(3, getGraMat());
            objCon.Sql.executeUpdate();
            objCon.cerrar();
            JOptionPane.showMessageDialog(null, "Registro guardado con éxito");        
        }catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al guardar ");
        }
    }
    
    
    public void camposTabla(){
       try {
           objCon.conectar();
           objCon.Sql = objCon.con.prepareStatement("SELECT ma.nomMat, mp.graMatxPro "
                   + "from materia ma, materiaXProfesor mp, profesor pr "
                   + "where mp.docProMatxPro=? and mp.docProMatxPro=pr.docPro and mp.codMatMatxPro=ma.codMat");
           objCon.Sql.setString(1, getDocPro());
           objCon.Sql.executeQuery();
           datosTable = objCon.Sql.getResultSet(); 
       } catch (Exception e) {
       }
   }
    
}
