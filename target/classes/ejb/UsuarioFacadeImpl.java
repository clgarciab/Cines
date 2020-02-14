package ejb;

import java.util.List;

import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

import modelo.Usuario;

@Stateless
public class UsuarioFacadeImpl extends AbstractFacadeJPAImpl<Usuario> implements UsuarioFacade {

	@PersistenceContext(unitName = "Persistencia")
	private EntityManager em;

	public UsuarioFacadeImpl() {
		super(Usuario.class);
	}

	@Override
	protected EntityManager getEm() {
		return em;
	}

	@PreDestroy
	public void destruct() {
		getEm().close();
	}
	
	public Usuario iniciarSesion(Usuario u) {
		Usuario usuario = null;
		String consulta;
		
		try {
			consulta = "SELECT u FROM Usuario u WHERE u.usuario = :usuario AND u.clave = :clave";
			Query query = em.createQuery(consulta);
			query.setParameter("usuario", u.getUsuario());
			query.setParameter("clave", u.getClave());
			
			@SuppressWarnings("unchecked")
			List<Usuario> lista = query.getResultList();
			if(!lista.isEmpty()) {
				usuario=lista.get(0);
			}
			
		} catch (Exception e) {
			e.getStackTrace();
		}
		
		return usuario;
	}

	public List<Usuario> findAll() {
		// System.out.println(getEm().createQuery("select m from Categoria m", Menu.class).getResultList());
		// return getEm().createQuery("select m from Categoria m", Menu.class).getResultList();
		CriteriaQuery<Usuario> cq = getEm().getCriteriaBuilder().createQuery(Usuario.class);
		cq.select(cq.from(Usuario.class));
		return getEm().createQuery(cq).getResultList();

	}

}
