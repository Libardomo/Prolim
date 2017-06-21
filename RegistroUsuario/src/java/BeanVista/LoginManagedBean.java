/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeanVista;

import Conexion.ConexionBd;
import Controlador.ControladorLogin;
import Modelo.Bean.BeanLogin;
import Modelo.Dao.DaoLogin;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;

/**
 *
 * @author lmedi
 */
@ManagedBean
@RequestScoped
public class LoginManagedBean {

    /**
     * Creates a new instance of LoginManagedBean
     */
    private BeanLogin BLogin;
    private DaoLogin DLogin;
    private FacesMessage mensaje;
    public String respuesta;

    public BeanLogin getBLogin() {
        return BLogin;
    }

    public void setBLogin(BeanLogin BLogin) {
        this.BLogin = BLogin;
    }

    public DaoLogin getDLogin() {
        return DLogin;
    }

    public void setDLogin(DaoLogin DLogin) {
        this.DLogin = DLogin;
    }

    public LoginManagedBean() {
        BLogin = new BeanLogin();
        DLogin = new DaoLogin(BLogin);
    }

    public String listarUsu() {
        return "";
    }

    public String validarUsuario() throws NamingException {
        try {
            ControladorLogin control = new ControladorLogin();
            BeanLogin ValidarUsu = control.validarUsuPass(this.BLogin);
            if (ValidarUsu != null) {
                HttpSession sesion = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
                sesion.setAttribute("usuarioLogin", ValidarUsu);
                if (ValidarUsu.getUsuario().equals("admin")) {
                    return "rolAdmin";
                } else {
                    return "rolUser";
                }
            } else {
                mensaje = new FacesMessage("Datos incorrectos");
                mensaje.setSeverity(FacesMessage.SEVERITY_FATAL);
                FacesContext.getCurrentInstance().addMessage(null, mensaje);
                return "";
            }

        } catch (SQLException e) {
            return "";
        }
    }

    public String modificarUsu() {
        try {
            DaoLogin dlog = new DaoLogin(BLogin);
            if (dlog.Actualizar()) {
                mensaje = new FacesMessage("Los datos se actualizaron correcgtamente");
                FacesContext.getCurrentInstance().addMessage(null, mensaje);
                return "";
            } else {
                mensaje = new FacesMessage("No se pudo actualizar.");
                FacesContext.getCurrentInstance().addMessage(null, mensaje);
                return "";
            }
        } catch (Exception ex) {
            Logger.getLogger(LoginManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        } 
    }
}
//        HttpSession sesion = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
//        sesion.setAttribute("usuarioLogin", this.BLogin);
//        try {
//            if (DLogin.Validar(this.BLogin) != null) {
//                String usuario = BLogin.getUsuario();
//                String Contrasenia = BLogin.getContrasenia();
//                if (BLogin.getUsuario().equals(usuario) && BLogin.getContrasenia().equals(Contrasenia)) {
//                    if (BLogin.getUsuario().equals("admin") && BLogin.getContrasenia().equals("admin")) {
//                        respuesta = "rolAdmin";
//                    } else {
//                        respuesta = "rolUser";
//                    }
//                } else {
//                    respuesta = "Inicio";
//                }
//            } else {
//                mensaje = new FacesMessage("Usuario y/o contraseña incorrectos");
//                FacesContext.getCurrentInstance().addMessage(null, mensaje);
//                return "";
//            }
//        } catch (Exception ex) {
//            Logger.getLogger(LoginManagedBean.class.getName()).log(Level.SEVERE, null, ex);
//        }
