package controlador;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import ejb.CategoriaFacade;
import ejb.NotaFacade;
import modelo.Categoria;
import modelo.Nota;
import modelo.Usuario;

@Named
@ViewScoped
public class NotaController implements Serializable{
	private static final long serialVersionUID = 1L;

	@EJB
	private NotaFacade notaEJB;	
	@EJB
	private CategoriaFacade categoriaEJB;
	
	
	@Inject
	private Nota nota;	
	@Inject
	private Categoria categoria;
	
	
	private List<Categoria> categorias;
	
	
	@PostConstruct
	public void init() {
		categorias = categoriaEJB.findAll();
	}

	public void registrar() {
		try {			
			FacesContext context=FacesContext.getCurrentInstance();
			Usuario u =(Usuario) context.getExternalContext().getSessionMap().get("usuario");
			
			nota.setCategoria(categoria);
			nota.setPersona(u.getCodigo());
			notaEJB.create(nota); //Incluye los datos encabezado y y cuerpo
			
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Aviso","Se registró"));	
		
		}catch(Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"Aviso","Error"));
		}
		
	}

	public NotaFacade getNotaEJB() {
		return notaEJB;
	}

	public void setNotaEJB(NotaFacade notaEJB) {
		this.notaEJB = notaEJB;
	}

	public CategoriaFacade getCategoriaEJB() {
		return categoriaEJB;
	}

	public void setCategoriaEJB(CategoriaFacade categoriaEJB) {
		this.categoriaEJB = categoriaEJB;
	}

	public Nota getNota() {
		return nota;
	}

	public void setNota(Nota nota) {
		this.nota = nota;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}
	


	
	
}