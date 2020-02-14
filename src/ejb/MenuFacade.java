package ejb;

import java.util.List;

import javax.ejb.Local;

import modelo.Menu;

@Local
public interface MenuFacade extends AbstractFacadeJPA<Menu> {
	public List<Menu> findAll();
}
