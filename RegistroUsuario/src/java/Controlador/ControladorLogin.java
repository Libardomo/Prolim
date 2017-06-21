/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Bean.BeanLogin;
import Modelo.Dao.DaoLogin;
import java.sql.SQLException;
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
public class ControladorLogin {

    /**
     * Creates a new instance of ControladorLogin
     */   
    private BeanLogin Blog;
    private DaoLogin Dlog;

    public BeanLogin getBlog() {
        return Blog;
    }

    public void setBlog(BeanLogin Blog) {
        this.Blog = Blog;
    }
    
    public BeanLogin getDlog() {
        return Blog;
    }

    public void setDlog(DaoLogin Dlog) {
        this.Dlog = Dlog;
    }
    
    public ControladorLogin() {
        Dlog = new DaoLogin(Blog);
    }
    
    public BeanLogin validarUsuPass(BeanLogin bl) throws SQLException{
        if (bl.getUsuario() != null && bl.getContrasenia() != null) {
            String Contrasenia = bl.getContrasenia();
            try {
                BeanLogin LogUsu = Dlog.Validar(bl);
                if (LogUsu != null && LogUsu.getContrasenia().equals(Contrasenia)) {
                    return bl;
                } else {
                    return null;
                }
            } catch (Exception ex) {
                Logger.getLogger(ControladorLogin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return bl;
    }   
}
