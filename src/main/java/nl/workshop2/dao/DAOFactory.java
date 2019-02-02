package nl.workshop2.dao;

import nl.workshop2.dao.mysqldao.MySQLDAOFactory;
import nl.workshop2.domain.Account;
import nl.workshop2.domain.Adres;
import nl.workshop2.domain.Artikel;
import nl.workshop2.domain.Bestelling;
import nl.workshop2.domain.Bestelregel;
import nl.workshop2.domain.Klant;

/**
 *
 * @author Vosjes
 */
public abstract class DAOFactory {
    
    /*
    Hier bevinden zich de abstracte methodes die door de factories geïmplementeerd
    dienen te worden, alsook de statische methode om de betreffende factory te maken.
    */
    
    public abstract GenericDao<Account> getAccountDao();
    public abstract GenericDao<Adres> getAdresDao();
    public abstract GenericDao<Artikel> getArtikelDao();
    public abstract GenericDao<Bestelling> getBestellingDao();
    public abstract GenericDao<Bestelregel> getBestelregelDao();
    public abstract GenericDao<Klant> getKlantDao();
    
    public static DAOFactory getDAOFactory() {
    	return new MySQLDAOFactory();
    }
}
