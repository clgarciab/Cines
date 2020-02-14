package controlador;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import modelo.Usuario;

@Named
@ViewScoped
public class PlantillaController implements Serializable {

	private static final long serialVersionUID = 1L;

	
	public void verificarSesion() {
		try {

			FacesContext context = FacesContext.getCurrentInstance();
			Usuario usuario = (Usuario) context.getExternalContext().getSessionMap().get("usuario");
			
			if(usuario==null) {
				context.getExternalContext().redirect(context.getExternalContext().getApplicationContextPath()+"/permisos.xhtml");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
