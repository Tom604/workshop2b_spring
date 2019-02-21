package nl.workshop2.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import nl.workshop2.dao.GenericDao;
import nl.workshop2.dao.mysqldao.AdresDaoImpl;
import nl.workshop2.domain.Adres;

/**
 *
 * @author Vosjes
 */
@Component
public class AdresController {
    
	private final GenericDao<Adres> ADRESDAO; //= DAOFactory.getDAOFactory().getAdresDao();
	
	@Autowired
	public AdresController(GenericDao<Adres> adresDao) {
		this.ADRESDAO = adresDao;
	}
	
	/*
	 * Hier een constructor maken die de dao ontvangt in een parameter. Dataveld in klasse: "private final".
	 */
	
    public void insertAdres(Adres adres) {
    	AdresDaoImpl adresDaoImpl = (AdresDaoImpl)ADRESDAO;
    	adresDaoImpl.insert(adres);
    }
    
    public Adres selectAdres(Long id) {
        return ADRESDAO.select(id);
    }
    
    public ArrayList<Adres> selectAdressen() {
        return ADRESDAO.selectMultiple();
    }
    
    public ArrayList<Adres> selectAdressen(Long klantId) {
    	AdresDaoImpl adresDaoImpl = (AdresDaoImpl)ADRESDAO;
        return adresDaoImpl.selectMultiple(klantId);
    }
    
    public void updateAdres(Adres adres) {
    	ADRESDAO.update(adres);
    }
    
    public void deleteAdres(Long id) {
    	ADRESDAO.delete(id);
    }
}
