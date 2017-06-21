/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Dao;

import Conexion.ConexionBd;
import Conexion.InterfaceCrud;
import Modelo.Bean.BeanLogin;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.naming.NamingException;

/**
 *
 * @author lmedi
 */
@ManagedBean
@RequestScoped
public class DaoLogin extends ConexionBd implements InterfaceCrud {

    private Connection cnx;
    private Statement puente = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private BeanLogin bl;
    private Object Obj;
    public boolean encontrado = false;
    public boolean listo = false;

    private String idUsuario;
    private String Usuario;
    private String Contrasenia;

    public BeanLogin getBl() {
        return bl;
    }

    public void setBl(BeanLogin bl) {
        this.bl = bl;
    }

    public DaoLogin(BeanLogin Blog) {
        super();
        try {
            cnx = ObtenerConexion();
            puente = cnx.createStatement();

            idUsuario = Blog.getIdUsuario();
            Usuario = Blog.getUsuario();
            Contrasenia = Blog.getContrasenia();
            bl = new BeanLogin();
        } catch (Exception ex) {
            Logger.getLogger(DaoUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<BeanLogin> Listar() throws SQLException {
        ps = cnx.prepareStatement("SELECT * FROM usuario");
        rs = ps.executeQuery();
        List<BeanLogin> Lbl = new ArrayList();
        while (rs.next()) {
            bl = new BeanLogin();
            bl.setUsuario(rs.getString("Usuario"));
            bl.setContrasenia(rs.getString("Contrasenia"));
            Lbl.add(bl);
        }
        return Lbl;
    }

    public BeanLogin Validar(Object obj) throws NamingException {
        bl = (BeanLogin)obj;
        try {
            cnx = ObtenerConexion();
            puente = cnx.createStatement();
            rs = puente.executeQuery("SELECT * FROM usuario WHERE Usuario = '" + bl.getUsuario() + "' AND Contrasenia = '" + bl.getContrasenia() + "'");

            if (rs.next()) {
                encontrado = true;
            }

            CerrarConexion(cnx);

        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DaoLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bl;
    }

    @Override
    public Boolean Registrar() {
        try {
            String sql = "INSERT INTO usuario (Usuario, Contrasenia) VALUES ('" + Usuario + "', '" + Contrasenia + "')";
            puente.executeUpdate(sql);
            listo = true;
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(DaoLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listo;
    }

    @Override
    public Boolean Eliminar() {
        try {
            String sql = "DELETE FROM usuario WHERE IdUsuario = '" + idUsuario + "'; ";
            puente.executeUpdate(sql);
            listo = true;
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(DaoLogin.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listo;
    }

    @Override
    public Boolean Actualizar() {
        try {
            cnx = ObtenerConexion();
            puente.executeUpdate("UPDATE usuario SET Usuario = '" + Usuario + "', Contrasenia = '" + Contrasenia + "' where idUsuario = '" + idUsuario + "' ");
            listo = true;

        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(DaoLogin.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listo;
    }
}
