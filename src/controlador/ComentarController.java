package controlador;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import ejb.CategoriaFacade;
import ejb.NotaFacade;
import modelo.Categoria;
import modelo.Nota;
import modelo.Usuario;

@Named
@ViewScoped
public class ComentarController implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@EJB
	private NotaFacade notaEJB;	
	
	private List<Nota> listaNotas;
	
	@PostConstruct
	public void init() {
		listaNotas = notaEJB.findAll();
	}
	
	public void asignar(Nota nota) {
		
		/*try {
			Usuario u = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
			
			listaNotas = notaEJB.buscar(u.getCodigo().getCodigo(), codigoCategoria, fechaConsulta);
			
			//Solo para trazar en consola
			for (Nota nota: listaNotas) {
				System.out.println(nota.getCategoria().getNombre());
				System.out.println(nota.getEncabezado());
				System.out.println(nota.getCuerpo());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}*/
	}

	public NotaFacade getNotaEJB() {
		return notaEJB;
	}

	public void setNotaEJB(NotaFacade notaEJB) {
		this.notaEJB = notaEJB;
	}

	public List<Nota> getListaNotas() {
		return listaNotas;
	}

	public void setListaNotas(List<Nota> listaNotas) {
		this.listaNotas = listaNotas;
	}
	
	


	
	
	
}