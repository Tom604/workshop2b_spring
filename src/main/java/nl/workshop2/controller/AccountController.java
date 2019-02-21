package nl.workshop2.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
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
public class AccountController {
    
	private final GenericDao<Account> ACCOUNTDAO; //= DAOFactory.getDAOFactory().getAccountDao();
	
	@Autowired
	public AccountController(GenericDao<Account> accountDao) {
		this.ACCOUNTDAO = accountDao;
	}
	
    public void insertAccount(Account account) {
        AccountDaoImpl accountDaoImpl = (AccountDaoImpl)ACCOUNTDAO;
        accountDaoImpl.insert(account);
    }
    
    public Account selectAccount(Long id) {
        return ACCOUNTDAO.select(id);
    }
    
    public ArrayList<Account> selectAccounts() {
        return ACCOUNTDAO.selectMultiple();
    }
    
    public void updateAccount(Account account) {
        ACCOUNTDAO.update(account);
    }
    
    public void deleteAccount(Long id) {
        ACCOUNTDAO.delete(id);
    }
    
    public boolean validatePassword(String wachtwoord) {
        
        boolean validation = false;
        
        AccountDaoImpl accountDaoImpl = (AccountDaoImpl)ACCOUNTDAO;
        Account account = accountDaoImpl.select(LoginController.loginnaam);
        if (PasswordStorage.verifyPassword(wachtwoord, account.getWachtwoord())) {
            validation = true;
        }
        return validation;
    }
}
