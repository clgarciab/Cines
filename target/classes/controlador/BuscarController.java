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
public class BuscarController implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@EJB
	private CategoriaFacade categoriaEJB;
	@EJB
	private NotaFacade notaEJB;	

	/*@Inject
	private Categoria categoria;
	@Inject
	private Nota nota;	*/
	
	private List<Categoria> listaCategorias;
	private List<Nota> listaNotas;
	
	private int codigoCategoria;
	private Date fechaConsulta;
	
	@PostConstruct
	public void init() {
		listaCategorias = categoriaEJB.findAll();
		listaNotas = new ArrayList<Nota>();
	}
	
	public void buscar() {
		
		try {
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
		}
	}

	public CategoriaFacade getCategoriaEJB() {
		return categoriaEJB;
	}

	public void setCategoriaEJB(CategoriaFacade categoriaEJB) {
		this.categoriaEJB = categoriaEJB;
	}

	public NotaFacade getNotaEJB() {
		return notaEJB;
	}

	public void setNotaEJB(NotaFacade notaEJB) {
		this.notaEJB = notaEJB;
	}

	public List<Categoria> getListaCategorias() {
		return listaCategorias;
	}

	public void setListaCategorias(List<Categoria> listaCategorias) {
		this.listaCategorias = listaCategorias;
	}

	public List<Nota> getListaNotas() {
		return listaNotas;
	}

	public void setListaNotas(List<Nota> listaNotas) {
		this.listaNotas = listaNotas;
	}

	public int getCodigoCategoria() {
		return codigoCategoria;
	}

	public void setCodigoCategoria(int codigoCategoria) {
		this.codigoCategoria = codigoCategoria;
	}

	public Date getFechaConsulta() {
		return fechaConsulta;
	}

	public void setFechaConsulta(Date fechaConsulta) {
		this.fechaConsulta = fechaConsulta;
	}
	

	
	
	
}