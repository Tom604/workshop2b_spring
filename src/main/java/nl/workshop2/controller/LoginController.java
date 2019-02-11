package nl.workshop2.controller;

import org.springframework.stereotype.Component;

import nl.workshop2.dao.GenericDao;
import nl.workshop2.dao.mysqldao.AccountDaoImpl;
import nl.workshop2.domain.Account;
import nl.workshop2.utility.PasswordStorage;

/**
 *
 * @author Vosjes
 */
@Component
public class LoginController {
    
    public static String loginnaam;
    public static String accounttype;
    
    private final GenericDao<Account> ACCOUNTDAO;
    
    public LoginController(GenericDao<Account> accountDao) {
    	this.ACCOUNTDAO = accountDao;
    }

    public boolean validateLogin(String username, String wachtwoord) {
        
        boolean validation = false;
        
        // Verkrijg data van de database via AccountDAO en valideer
        AccountDaoImpl accountDaoImpl = (AccountDaoImpl)ACCOUNTDAO;
        Account account = accountDaoImpl.select(username);
        if (account.getUsername() != null && PasswordStorage.verifyPassword(wachtwoord, account.getWachtwoord())) {
            validation = true;
            loginnaam = account.getUsername();
            accounttype = account.getAccounttype();
        }
        return validation;
    }
}
