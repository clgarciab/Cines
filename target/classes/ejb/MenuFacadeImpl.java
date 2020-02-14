package ejb;

import java.util.List;

import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

import modelo.Menu;

@Stateless
public class MenuFacadeImpl extends AbstractFacadeJPAImpl<Menu> implements MenuFacade {

	@PersistenceContext(unitName = "Persistencia")
	private EntityManager em;

	public MenuFacadeImpl() {
		super(Menu.class);
	}

	@Override
	protected EntityManager getEm() {
		return em;
	}

	@PreDestroy
	public void destruct() {
		getEm().close();
	}

	public List<Menu> findAll() {
		// System.out.println(getEm().createQuery("select m from Categoria m", Menu.class).getResultList());
		// return getEm().createQuery("select m from Categoria m", Menu.class).getResultList();
		CriteriaQuery<Menu> cq = getEm().getCriteriaBuilder().createQuery(Menu.class);
		cq.select(cq.from(Menu.class));
		return getEm().createQuery(cq).getResultList();

	}

}
