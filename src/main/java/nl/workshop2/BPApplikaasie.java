package nl.workshop2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import nl.workshop2.utility.BPEntityManager;

//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.Persistence;

//import java.sql.SQLException;

//import nl.workshop2.utility.HikariCPDataSource;
import nl.workshop2.view.LoginMenuView;
import nl.workshop2.view.StringDrawingView;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

/**
 *
 * @author Vosjes
 */
public class BPApplikaasie {
    
//    private static final Logger log = LoggerFactory.getLogger(BPApplikaasie.class);
    
    public static void main(String[] args) {
        
    	ApplicationContext context = new AnnotationConfigApplicationContext(BPApplikaasieConfig.class);
    	
    	// Draw begin String (verplaatsen naar LoginMenuView?)
        StringDrawingView stringDrawingView = new StringDrawingView();
        stringDrawingView.drawBeginString();
        
        //Start bpapplikaasie
        LoginMenuView loginMenuView = new LoginMenuView();
        loginMenuView.showStartMenu();
        
        // Draw end String (verplaatsen naar LoginMenuView?)
        stringDrawingView.drawEndString();
    	
//        BPEntityManager.close();
        
        BPEntityManager em = context.getBean(BPEntityManager.class);
        em.close();
        
    	// Hier EntityManager setup alleen voor aanmaken db en testen of dit goed gebeurt.
    	
    	/*
    	EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("workshop2a_hibernate");
    	EntityManager em = emFactory.createEntityManager();
    	em.close();
		emFactory.close();
    	*/
    	
    	
    	// Hikari CP momenteel buiten gebruik
    	
    	/*
        //Connect to database and check if connection is good (otherwise close app and log)
        try {
            HikariCPDataSource.getConnection().close();
            log.debug("Database connection tested, connection pool created");
        } catch (SQLException ex) {
            ex.printStackTrace();
            log.warn("Exception catched when connecting to database");
            System.exit(1);
        }
        */
    }
}
