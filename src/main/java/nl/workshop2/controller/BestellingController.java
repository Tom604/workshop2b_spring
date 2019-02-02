package nl.workshop2.controller;

import java.util.ArrayList;
import nl.workshop2.dao.DAOFactory;
import nl.workshop2.dao.GenericDao;
import nl.workshop2.dao.mysqldao.BestellingDaoImpl;
import nl.workshop2.domain.Bestelling;

/**
 *
 * @author Vosjes
 */
public class BestellingController {
    
	private final GenericDao<Bestelling> BESTELLINGDAO = DAOFactory.getDAOFactory().getBestellingDao();
	
    public Bestelling insertBestelling(Bestelling bestelling) {
        BestellingDaoImpl bestellingDaoImpl = new BestellingDaoImpl(); 
        return bestellingDaoImpl.insert(bestelling);
    }
    
    public Bestelling selectBestelling(Long id) {
        return BESTELLINGDAO.select(id);
    }
    
    public ArrayList<Bestelling> selectBestellingen() {
        return BESTELLINGDAO.selectMultiple();
    }
    
    public ArrayList<Bestelling> selectBestellingen(Long klantId) {
    	BestellingDaoImpl bestellingDaoImpl = new BestellingDaoImpl();
        return bestellingDaoImpl.selectMultiple(klantId);
    }
    
    public void updateBestelling(Bestelling bestelling) {
    	BESTELLINGDAO.update(bestelling);
    }
    
    public void deleteBestelling(Long id) {
    	BESTELLINGDAO.delete(id);
    }
}
