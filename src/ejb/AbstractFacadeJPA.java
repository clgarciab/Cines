package ejb;


public interface AbstractFacadeJPA<T> {
	
	void create(T entity);

	void update(T entity);

	void remove(T entity);

	T find(Object id);
}
