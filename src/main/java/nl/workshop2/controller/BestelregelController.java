package nl.workshop2.controller;

import java.util.ArrayList;
import nl.workshop2.dao.DAOFactory;
import nl.workshop2.dao.GenericDao;
import nl.workshop2.dao.mysqldao.BestelregelDaoImpl;
import nl.workshop2.domain.Bestelregel;

/**
 *
 * @author Vosjes
 */
public class BestelregelController {
    
	private final GenericDao<Bestelregel> BESTELREGELDAO = DAOFactory.getDAOFactory().getBestelregelDao();
	
    public void insertBestelregel(Bestelregel bestelregel) {
        BestelregelDaoImpl bestelregelDaoImpl = new BestelregelDaoImpl();
        bestelregelDaoImpl.insert(bestelregel);
    }
    
    public Bestelregel selectBestelregel(Long id) {
        return BESTELREGELDAO.select(id);
    }
    
    public ArrayList<Bestelregel> selectBestelregels() {
        return BESTELREGELDAO.selectMultiple();
    }
    
    public ArrayList<Bestelregel> selectBestelregels(Long bestellingId) {
    	BestelregelDaoImpl bestelregelDaoImpl = new BestelregelDaoImpl();
        return bestelregelDaoImpl.selectMultiple(bestellingId);
    }
    
    public void updateBestelregel(Bestelregel bestelregel) {
        BESTELREGELDAO.update(bestelregel);
    }
    
    public void deleteBestelregel(Long id) {
        BESTELREGELDAO.delete(id);
    }
}
