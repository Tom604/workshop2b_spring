package nl.workshop2.view;

import java.util.ArrayList;
import nl.workshop2.controller.AccountController;
import nl.workshop2.domain.Account;
import nl.workshop2.domain.Klant;

/**
 *
 * @author Vosjes
 */
public class AccountMenuView extends MenuView {
    
    @Override
    public void showMenu() {
        
        String selection = "";
        
        do {
            setViewName("Accounts\t");
            printHeader();
            
            System.out.println("1. Account toevoegen\n2. Account zoeken\n3. Account " +
                "aanpassen\n4. Account verwijderen\n\n0. Terug naar Hoofdpagina\n");
            selection = getSelection();
            switch (selection) {
                case "0":   break;
                case "1":   setAccounttype(); break;
                case "2":   showSelectAccountMenu(); break;
                case "3":   showUpdateAccountMenu(); break;
                case "4":   showDeleteAccountMenu(); break;
                default:    System.out.println(MAINERROR);
            }
        } while (selection.equals("0") == false);
    }

    @Override
    public void setViewName(String viewName) {
        this.viewName = viewName;
    }
    
    private void printAttributes(Account account) {
            System.out.printf("%-16s%-16s\n", account.getUsername(), account.getAccounttype());
    }
    
    private void setAccounttype() {

        Account account = new Account();
        
        System.out.println("Selecteer accounttype:");
        System.out.println("1. Administrator\n2. Medewerker\n");
        switch (getSelection()) {
            case "1":   account.setAccounttype("admin");
                        showInsertAccountMenu(account);
                        break;
            case "2":   account.setAccounttype("medewerker");
                        showInsertAccountMenu(account);
                        break;
            default:    System.out.println(MAINERROR);
        }
    }
    
    void setAccountForKlant(Klant klant) {
        Account account = new Account();
        account.setKlant(klant);
        account.setAccounttype("klant");
        showInsertAccountMenu(account);
    }
    
    private void showInsertAccountMenu(Account account) {
        
        setViewName("Account toevoegen");
        printHeader();
        
        System.out.println("Voeg een nieuwe account toe aan de database.\n");
        System.out.print("Voer de username voor het account in (en druk dan op enter): ");
        account.setUsername(SCANNER.next());
        account.setWachtwoord(getInputWithValidation("wachtwoord"));
            
        System.out.println("\nHet opgegeven account:");
        printAttributes(account);
        System.out.println("\nIs dit correct?");
        System.out.println("1. Ja, opslaan.\n0. Nee, opnieuw invoeren.\n");
        switch (getSelection()) {
            case "0":   break;
            case "1":   AccountController accountController = new AccountController();
                        accountController.insertAccount(account);
                        System.out.println("Account toegevoegd.");
                        break;
            default:    System.out.println(MAINERROR);
        }
    }
    
    private Account showSelectAccountMenu() {
        
        setViewName("Account zoeken\t");
        printHeader();
        
        int id = printList();
        System.out.println("\n0. Terug");
        
        AccountController accountController = new AccountController();
        Account account = new Account();
        
        System.out.println("\nSelecteer account.\n");
        Long selection = Long.parseLong(getSelection());
        if (selection == 0) {
        }
        else if (selection < 0 || selection > id) {
            System.out.println(MAINERROR);
        }
        else {
            account = accountController.selectAccount(selection);
            System.out.println("De geselecteerde account:");
            printAttributes(account);
        }
            
        return account;
    }
    
    private int printList() {

        AccountController accountController = new AccountController();
        ArrayList<Account> accounts = accountController.selectAccounts();
        
        System.out.printf("%-3s%-16s%-16s\n", "", "Username", "Accounttype");
        for (Account e: accounts) {
            System.out.print(e.getId() + ". ");
            printAttributes(e);
        }
        return accounts.size();
    }
    
    private void showUpdateAccountMenu() {
        
        Account account = showSelectAccountMenu();
        setViewName("Account aanpassen");
        printHeader();
        
        System.out.println("Wat wilt u aanpassen?");
        System.out.println("1. Username\n2. Wachtwoord\n\n" +
                "0. Niets, terug naar Accountspagina\n");
        switch (getSelection()) {
            case "0":   break;
            case "1":   System.out.print("Nieuwe username: ");
                        account.setUsername(SCANNER.next());
                        showUpdatedAccountMenu(account);
                        break;
            case "2":   System.out.print("Bevestig uw wachtwoord: ");
                        AccountController accountController = new AccountController();
                        if (accountController.validatePassword(SCANNER.next())) {
                            account.setWachtwoord(getInputWithValidation("wachtwoord"));
                            showUpdatedAccountMenu(account);
                        }
                        else {
                            System.out.println(MAINERROR);
                        }
                        break;
            default:    System.out.println(MAINERROR);
        }
    }

    private void showUpdatedAccountMenu(Account account) {
        
        System.out.println("\nDe aangepaste account:");
        printAttributes(account);
        System.out.println("\nWilt u de aangepaste account opslaan?");
        System.out.println("1. Ja\n0. Nee\n");
        switch (getSelection()) {
            case "0":   break;
            case "1":   AccountController accountController = new AccountController();
                        accountController.updateAccount(account);
                        System.out.println("Account opgeslagen.");
                        break;
            default:    System.out.println(MAINERROR);
        }
    }
    
    private void showDeleteAccountMenu() {
        
        Account account = showSelectAccountMenu();
        Long id = account.getId();
        
        setViewName("Account verwijderen");
        printHeader();
        
        System.out.println("Wilt u deze account verwijderen?");
        System.out.println("1. Ja\n0. Nee\n");
        switch (getSelection()) {
            case "0":   break;
            case "1":   AccountController accountController = new AccountController();
                        accountController.deleteAccount(id);
                        System.out.println("Account verwijderd.");
                        break;
            default:    System.out.println(MAINERROR);
        }
    }
}
