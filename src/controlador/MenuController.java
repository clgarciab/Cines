package controlador;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.omg.CosNaming._BindingIteratorImplBase;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

import ejb.MenuFacade;
import modelo.Menu;
import modelo.Usuario;

@Named
@SessionScoped
public class MenuController implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	private MenuFacade EJBMenu;

	private List<Menu> todos_menus;
	private MenuModel model;

	@PostConstruct
	public void init() {
		model = new DefaultMenuModel(); // Crea un modelo de menu según Primefaces

		this.listarMenus(); // carga los menus de la tabla a una lista
		this.establecerPermisos(); // Carga el menú correspondiente según el usuario
	}

	public void listarMenus() {
		try {
			todos_menus = EJBMenu.findAll();
			System.out.println("TODOS LOS MENÚS: ");
			for (Menu menu : todos_menus) {
				System.out.println(menu.getNombre());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void establecerPermisos() {

		// Recorre de la sesión el usuario
		Usuario u = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");

		// ra cada uno de todos los posibles menús que existen
		for (Menu m : todos_menus) {

			// Si es de tipo SUBMENU, corresponde con el usuario, NO ES SUBMENU y está
			// activo
			// Añade SUBEMNU
			if (m.getTipo().equals("S") && m.getTipoUsuario().equals(u.getTipo()) && m.getMenu() == null
					&& m.getEstado() == 1) {

				DefaultSubMenu submenu = new DefaultSubMenu(m.getNombre());
				model.addElement(submenu);

				for (Menu i : todos_menus) {
					// Si es de tipo ITEM, corresponde con el usuario, ES SUBMENU y está activo
					if (i.getTipo().equals("I") && i.getTipoUsuario().equals(u.getTipo()) && i.getMenu() != null
							&& i.getEstado() == 1) {
						System.out.println("SUBMENU: " + i.getNombre());
						DefaultMenuItem item = new DefaultMenuItem(i.getNombre());
						item.setUrl(i.getUrl());
						submenu.addElement(item);
					}
				}

				// Si es de tipo ITEM, corresponde con el usuario, NO ES SUBMENU y está activo
				// Añade ITEM
			} else if (m.getTipo().equals("I") && m.getTipoUsuario().equals(u.getTipo()) && m.getMenu() == null
					&& m.getEstado() == 1) {
				DefaultMenuItem item = new DefaultMenuItem(m.getNombre());
				item.setUrl(m.getUrl());
				model.addElement(item);
			}

		}
		
/*
		for (Menu m : todos_menus) {
			if (m.getTipo().equals("S") && m.getTipoUsuario().equals(u.getTipo())) {
				DefaultSubMenu firstSubmenu = new DefaultSubMenu(m.getNombre());
				for (Menu i : todos_menus) {
					Menu submenu = i.getMenu();
					if (submenu != null) {
						if (submenu.getCodigo() == m.getCodigo()) {
							DefaultMenuItem item = new DefaultMenuItem(i.getNombre());
							item.setUrl(i.getUrl());
							firstSubmenu.addElement(item);
						}
					}
				}
				model.addElement(firstSubmenu);
			} else {
				if (m.getMenu() == null && m.getTipoUsuario().equals(u.getTipo())) {
					DefaultMenuItem item = new DefaultMenuItem(m.getNombre());
					item.setUrl(m.getUrl());
					model.addElement(item);
				}

			}
		}
		*/

	}

	
	public void cerrarSesion() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
	}

	public String mostrarUsuarioLogueado() {
		Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
		return us.getUsuario();
	}

	public MenuModel getModel() {
		return model;
	}

	public void setModel(MenuModel model) {
		this.model = model;
	}

	public MenuFacade getEJBMenu() {
		return EJBMenu;
	}

	public void setEJBMenu(MenuFacade eJBMenu) {
		EJBMenu = eJBMenu;
	}

	public List<Menu> getLista() {
		return todos_menus;
	}

	public void setLista(List<Menu> lista) {
		this.todos_menus = lista;
	}
}