package nl.workshop2.controller;

import java.util.ArrayList;
import nl.workshop2.dao.DAOFactory;
import nl.workshop2.dao.GenericDao;
import nl.workshop2.dao.mysqldao.ArtikelDaoImpl;
import nl.workshop2.domain.Artikel;

/**
 *
 * @author Vosjes
 */
public class ArtikelController {

	private final GenericDao<Artikel> ARTIKELDAO = DAOFactory.getDAOFactory().getArtikelDao();
	
    public void insertArtikel(Artikel artikel) {
        ArtikelDaoImpl artikelDaoImpl = new ArtikelDaoImpl();
        artikelDaoImpl.insert(artikel);
    }
    
    public Artikel selectArtikel(Long id) {
        return ARTIKELDAO.select(id);
    }
    
    public ArrayList<Artikel> selectArtikelen() {
        return ARTIKELDAO.selectMultiple();
    }
    
    public void updateArtikel(Artikel artikel) {
        ARTIKELDAO.update(artikel);
    }
    
    public void deleteArtikel(Long id) {
        ARTIKELDAO.delete(id);
    }
}
