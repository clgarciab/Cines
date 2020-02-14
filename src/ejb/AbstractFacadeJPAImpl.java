package ejb;


import javax.persistence.EntityManager;


public abstract class AbstractFacadeJPAImpl<T> implements AbstractFacadeJPA<T> {
	private Class<T> entityClass;

	public AbstractFacadeJPAImpl(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	protected abstract EntityManager getEm(); // podemos permitir el acceso directo en las clases hijas o crear un consultar para getEm();
	
	@Override
	public void create(T entity) {
			getEm().persist(entity);
	}

	@Override
	public void update(T entity) {
		getEm().merge(entity);
	}

	@Override
	public void remove(T entity) {
		getEm().remove(getEm().merge(entity));

	}

	@Override
	public T find(Object id) {
		return getEm().find(entityClass, id);
	}

}

