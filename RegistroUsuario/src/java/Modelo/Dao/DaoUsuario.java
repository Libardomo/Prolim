/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Dao;

import Conexion.ConexionBd;
import Conexion.InterfaceCrud;
import Modelo.Bean.BeanUsuario;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author lmedi
 */
@ManagedBean
@RequestScoped
public class DaoUsuario extends ConexionBd implements InterfaceCrud {

    private Connection cnx;
    private Statement puente = null;
    private ResultSet rs = null;
    public boolean listo = false;

    public String idUsuario;
    public String idCliente;
    public String Nombre;
    public String Apellido;
    public String Direccion;
    public String Telefono;
    public String Correo;

    public DaoUsuario(BeanUsuario Bruser) {

        super();
        try {

            cnx = ObtenerConexion();
            puente = cnx.createStatement();

            idCliente = Bruser.getIdCliente();
            Nombre = Bruser.getNombre();
            Apellido = Bruser.getApellido();
            Correo = Bruser.getCorreo();
            Direccion = Bruser.getDireccion();
            Telefono = Bruser.getTelefono();
            idUsuario = Bruser.getIdUsuario();

        } catch (Exception ex) {
            Logger.getLogger(DaoUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public Boolean Registrar() {
        try {

            // INSERT INTO cliente (IdUsuario) SELECT MAX(IdUsuario) FROM usuario
            String sql = "INSERT INTO cliente(Nombre, Apellido, Correo, Direccion, Telefono, IdUsuario) VALUES ('" + Nombre + "', '" + Apellido + "', '" + Correo + "', '" + Direccion + "', '" + Telefono + "', '" + idUsuario + "')";
            puente.executeUpdate(sql);
            listo = true;

        } catch (Exception ex) {
            Logger.getLogger(DaoUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listo;
    }

//    public Boolean Registrarid() {
//        try {
//            String sql = "INSERT INTO cliente (IdUsuario) SELECT MAX(IdUsuario) FROM usuario";
//            puente.executeUpdate(sql);
//            listo = true;
//        } catch (Exception ex) {
//            Logger.getLogger(DaoUsuario.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return listo;
//    }

    @Override
    public Boolean Eliminar() {
        try {
            String sql = "DELETE FROM cliente WHERE IdUsuario = '" + idUsuario + "'; ";
            puente.executeUpdate(sql);
            listo = true;
        } catch (Exception ex) {
            Logger.getLogger(DaoUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listo;
    }

    @Override
    public Boolean Actualizar() {
        try {

            puente.executeUpdate("UPDATE cliente SET Nombre = '" + Nombre + "', Apellido = '" + Apellido + "', Correo = '" + Correo + "', Direccion = '" + Direccion + "', Telefono = '" + Telefono + "' WHERE idCliente = '" + idCliente + "'");
            listo = true;

        } catch (Exception ex) {
            Logger.getLogger(DaoUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listo;
    }
}
