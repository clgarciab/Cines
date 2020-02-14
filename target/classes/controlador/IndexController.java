package controlador;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ejb.UsuarioFacade;
import modelo.Usuario;

@Named
@ViewScoped
public class IndexController implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private UsuarioFacade usuarioEJB;

	// CON CDI
	@Inject
	private Usuario usuario;
	
	
	public String iniciarSesion() {
		String redireccion = null;
		Usuario u = null;
		
		try {
			u=usuarioEJB.iniciarSesion(usuario);
			
			if(u!=null) {
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", u);
				redireccion = "/protegido/principal";
				System.out.println("login ok");
			}else {
				System.out.println("error login");
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Aviso","Usuario incorrecto."));
			}
			
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"Aviso","Error."));
		}
		
		return redireccion;
	}
	
	
	public String iniciarSesion1() {
		String redireccion = null;
		Usuario u = null;
		
		try {
			u=usuarioEJB.iniciarSesion(usuario);
			
			if(u!=null) {
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", u);
				redireccion = "/protegido/principal1";
			}else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Aviso","Usuario incorrecto."));
			}
			
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"Aviso","Error."));
		}
		
		return redireccion;
	}

	

	public UsuarioFacade getUsuarioEJB() {
		return usuarioEJB;
	}

	public void setUsuarioEJB(UsuarioFacade usuarioEJB) {
		this.usuarioEJB = usuarioEJB;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
