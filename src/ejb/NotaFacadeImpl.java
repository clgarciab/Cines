package ejb;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;

import modelo.Nota;

@Stateless
public class NotaFacadeImpl extends AbstractFacadeJPAImpl<Nota> implements NotaFacade {

	@PersistenceContext(unitName = "Persistencia")
	private EntityManager em;

	public NotaFacadeImpl() {
		super(Nota.class);
	}

	@Override
	protected EntityManager getEm() {
		return em;
	}

	@PreDestroy
	public void destruct() {
		getEm().close();
	}

	public List<Nota> findAll() {
		// System.out.println(getEm().createQuery("select m from Nota m",
		// Menu.class).getResultList());
		// return getEm().createQuery("select m from Nota m",
		// Menu.class).getResultList();
		CriteriaQuery<Nota> cq = getEm().getCriteriaBuilder().createQuery(Nota.class);
		cq.select(cq.from(Nota.class));
		return getEm().createQuery(cq).getResultList();
	}

	@Override
	public List<Nota> buscar(int codigoPersona, int codigoCategoria, Date fechaConsulta) {

		List<Nota> lista = null;

		try {
			
			System.out.println(codigoPersona);
			System.out.println(codigoCategoria);
			System.out.println(fechaConsulta);

			String jpql = "SELECT n FROM Nota n " + "WHERE n.persona.codigo=:codigoPersona "
					+ "AND n.categoria.codigo=:codigoCategoria " + "AND n.fecha BETWEEN :fecha and :fecha2";

			TypedQuery<Nota> query = em.createQuery(jpql, Nota.class);
			query.setParameter("codigoPersona", codigoPersona);
			query.setParameter("codigoCategoria", codigoCategoria);
			query.setParameter("fecha", fechaConsulta, TemporalType.DATE);

			Calendar cal = Calendar.getInstance();
			cal.setTime(fechaConsulta);
			cal.add(Calendar.DATE, 1);
			query.setParameter("fecha2", cal, TemporalType.DATE);

			lista = query.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return lista;

	}

	

}
