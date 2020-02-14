package modelo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity
@Table(name="usuario")
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@OneToOne(cascade=CascadeType.PERSIST) //Añadido personalizado, si no existe persona no crea usuario
	@JoinColumn(name="codigo", nullable=false)
	private Persona codigo;
	
	private String clave;

	private Short estado;

	private String tipo;

	@Column(name = "usuario", unique = true)
	
	private String usuario;

	//bi-directional one-to-one association to Persona
	/*@OneToOne
	@JoinColumn(name="codigo")
	private Persona persona;*/

	public Usuario() {
	}

	public Persona getCodigo() {
		return codigo;
	}

	public void setCodigo(Persona codigo) {
		this.codigo = codigo;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public Short getEstado() {
		return estado;
	}

	public void setEstado(Short estado) {
		this.estado = estado;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}	

}