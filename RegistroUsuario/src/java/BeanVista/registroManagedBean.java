/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeanVista;

import Modelo.Bean.BeanUsuario;
import java.sql.Connection;
import Conexion.ConexionBd;
import Modelo.Bean.BeanLogin;
import Modelo.Dao.DaoLogin;
import Modelo.Dao.DaoUsuario;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author lmedi
 */
@ManagedBean
@RequestScoped
public class registroManagedBean {

    /**
     * Creates a new instance of registroUsuario
     */
    private BeanUsuario BUsu;
    private BeanLogin BLogin;

    public BeanUsuario getBUsu() {
        return BUsu;
    }

    public void setBUsu(BeanUsuario BUsu) {
        this.BUsu = BUsu;
    }

    public BeanLogin getBLogin() {
        return BLogin;
    }

    public void setBLogin(BeanLogin BLogin) {
        this.BLogin = BLogin;
    }

    public registroManagedBean() {
        BLogin = new BeanLogin();
        BUsu = new BeanUsuario();
    }

    public String registroUsuarioBd() {
        try {
            DaoUsuario DUsu = new DaoUsuario(BUsu);
            DaoLogin DLogin = new DaoLogin(BLogin);
            if (DUsu.Registrar() && DLogin.Registrar()) {
                FacesMessage mensaje = new FacesMessage("Datos ingresados Correctamente");
                FacesContext.getCurrentInstance().addMessage(null, mensaje);
                return "";
            } else {
                FacesMessage mensaje = new FacesMessage("Los datos no se pudieron ingresar.");
                mensaje.setSeverity(FacesMessage.SEVERITY_INFO);
                FacesContext.getCurrentInstance().addMessage(null, mensaje);
                return "";
            }
        } catch (Exception e) {
            return "";
        }
    }

}
