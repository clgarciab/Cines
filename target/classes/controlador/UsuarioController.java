package controlador;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.CascadeType;
import javax.persistence.OneToOne;

import ejb.UsuarioFacade;
import modelo.Persona;
import modelo.Usuario;

@Named
@ViewScoped
public class UsuarioController implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private UsuarioFacade usuarioEJB;

	// CON CDI
	@Inject
	private Usuario usuario;
	@Inject
	private Persona persona;

	public void registrar() {
		try {
			/*
			 * IllegarStateException - Si no tenemos lo de abajo, nos dará un error debido a
			 * que se encontrado un objeto (Persona) que no tiene Hay que persistir en
			 * cascada en el modelo Usuario @OneToOne(cascade=CascadeType.PERSIST)
			 */
			this.usuario.setCodigo(persona);
			usuarioEJB.create(usuario);

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se registró correctamente"));
			
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "No se registró"));
		}
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

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

}
