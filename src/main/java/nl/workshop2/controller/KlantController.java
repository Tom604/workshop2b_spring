package nl.workshop2.controller;

import java.util.ArrayList;
import nl.workshop2.dao.DAOFactory;
import nl.workshop2.dao.GenericDao;
import nl.workshop2.dao.mysqldao.KlantDaoImpl;
import nl.workshop2.domain.Klant;

/**
 *
 * @author Vosjes
 */
public class KlantController {
    
	private final GenericDao<Klant> KLANTDAO =  DAOFactory.getDAOFactory().getKlantDao();
	
    public Klant insertKlant(Klant klant) {
        KlantDaoImpl klantDaoImpl = new KlantDaoImpl();
        return klantDaoImpl.insert(klant);
    }
    
    public Klant selectKlant(Long id) {
        return KLANTDAO.select(id);
    }
    
    public ArrayList<Klant> selectKlanten() {
        return KLANTDAO.selectMultiple();
    }
    
    public ArrayList<Klant> selectKlanten(String achternaam) {
    	KlantDaoImpl klantDaoImpl = new KlantDaoImpl();
        return klantDaoImpl.selectMultiple(achternaam);
    }
    
    public void updateKlant(Klant klant) {
        KLANTDAO.update(klant);
    }
    
    public void deleteKlant(Long id) {
        KLANTDAO.delete(id);
    }
}
