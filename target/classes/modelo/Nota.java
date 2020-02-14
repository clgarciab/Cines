package modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;


/**
 * The persistent class for the nota database table.
 * 
 */
@Entity
@Table(name="nota")
@NamedQuery(name="Nota.findAll", query="SELECT n FROM Nota n")
public class Nota implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int codigo;
	/**
	 * Las 2 ManyToOne son generadas manualmente
	 */
	@ManyToOne
	@JoinColumn(name="codigo_categoria",nullable=false)
	private Categoria categoria;

	@ManyToOne
	@JoinColumn(name="codigo_persona")
	private Persona persona;

	private String comentarioAdmin;

	private String cuerpo;

	private String encabezado;

	//Inserta la fecha actual automáticamente
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha", insertable = false)
	private Date fecha;

	private String valor;

	public Nota() {
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public String getComentarioAdmin() {
		return comentarioAdmin;
	}

	public void setComentarioAdmin(String comentarioAdmin) {
		this.comentarioAdmin = comentarioAdmin;
	}

	public String getCuerpo() {
		return cuerpo;
	}

	public void setCuerpo(String cuerpo) {
		this.cuerpo = cuerpo;
	}

	public String getEncabezado() {
		return encabezado;
	}

	public void setEncabezado(String encabezado) {
		this.encabezado = encabezado;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	
}