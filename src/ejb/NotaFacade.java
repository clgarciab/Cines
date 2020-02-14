package ejb;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import modelo.Nota;

@Local
public interface NotaFacade extends AbstractFacadeJPA<Nota> {
	public List<Nota> findAll();
	public List<Nota> buscar(int codigoPersona, int codigoCategoria, Date fechaConsulta);
}
