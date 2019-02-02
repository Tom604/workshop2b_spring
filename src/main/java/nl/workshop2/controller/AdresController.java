package nl.workshop2.controller;

import java.util.ArrayList;
import nl.workshop2.dao.DAOFactory;
import nl.workshop2.dao.GenericDao;
import nl.workshop2.dao.mysqldao.AdresDaoImpl;
import nl.workshop2.domain.Adres;

/**
 *
 * @author Vosjes
 */
public class AdresController {
    
	private final GenericDao<Adres> ADRESDAO = DAOFactory.getDAOFactory().getAdresDao();
	
    public void insertAdres(Adres adres) {
    	AdresDaoImpl adresDaoImpl = new AdresDaoImpl();
    	adresDaoImpl.insert(adres);
    }
    
    public Adres selectAdres(Long id) {
        return ADRESDAO.select(id);
    }
    
    public ArrayList<Adres> selectAdressen() {
        return ADRESDAO.selectMultiple();
    }
    
    public ArrayList<Adres> selectAdressen(Long klantId) {
    	AdresDaoImpl adresDaoImpl = new AdresDaoImpl();
        return adresDaoImpl.selectMultiple(klantId);
    }
    
    public void updateAdres(Adres adres) {
    	ADRESDAO.update(adres);
    }
    
    public void deleteAdres(Long id) {
    	ADRESDAO.delete(id);
    }
}
