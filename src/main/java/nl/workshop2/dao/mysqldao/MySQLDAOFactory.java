package nl.workshop2.dao.mysqldao;

import nl.workshop2.dao.DAOFactory;
import nl.workshop2.dao.GenericDao;
//import nl.workshop2.domain.Account;
//import nl.workshop2.domain.Adres;
import nl.workshop2.domain.Artikel;
import nl.workshop2.domain.Bestelling;
import nl.workshop2.domain.Bestelregel;
import nl.workshop2.domain.Klant;

/**
 *
 * @author Vosjes
 */
public class MySQLDAOFactory extends DAOFactory {
    
    /*
    Creatiemethodes voor alle DAO's in de factory
    */
    
//    @Override
//    public GenericDao<Account> getAccountDao() {
//        return new AccountDaoImpl();
//    }
    
//    @Override
//    public GenericDao<Adres> getAdresDao() {
//        return new AdresDaoImpl();
//    }
    
    @Override
    public GenericDao<Artikel> getArtikelDao() {
        return new ArtikelDaoImpl();
    }
    
    @Override
    public GenericDao<Bestelling> getBestellingDao() {
        return new BestellingDaoImpl();
    }
    
    @Override
    public GenericDao<Bestelregel> getBestelregelDao() {
        return new BestelregelDaoImpl();
    }
    
    @Override
    public GenericDao<Klant> getKlantDao() {
        return new KlantDaoImpl();
    }
}
