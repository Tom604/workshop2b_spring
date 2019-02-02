package nl.workshop2.view;

import java.util.ArrayList;
import nl.workshop2.controller.KlantController;
import nl.workshop2.domain.Klant;

/**
 *
 * @author Vosjes
 */
public class KlantMenuView extends MenuView {

    @Override
    public void showMenu() {
        
        String selection = "";
        
        do {
            setViewName("Klanten\t\t");
            printHeader();
            
            System.out.println("1. Klantgegevens toevoegen\n2. Klantgegevens zoeken\n3. Klantgegevens " +
                "aanpassen\n4. Klantgegevens verwijderen\n\n0. Terug naar Hoofdpagina\n");
            selection = getSelection();
            switch (selection) {
                case "0":   break;
                case "1":   showInsertKlantMenu(); break;
                case "2":   showSelectKlantMenu(); break;
                case "3":   showUpdateKlantMenu(); break;
                case "4":   showDeleteKlantMenu(); break;
                default:    System.out.println(MAINERROR);
            }
        } while (selection.equals("0") == false);
    }

    @Override
    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    private void printAttributes(Klant klant) {
        
        if (klant.getTussenvoegsel() == null) {
            System.out.printf("%-16s\n", klant.getVoornaam() + " " +
                    klant.getAchternaam());
        }
        else {
            System.out.printf("%-20s\n", klant.getVoornaam() + " " +
                    klant.getTussenvoegsel() + " " + klant.getAchternaam());
        }
    }
    
    private void showInsertKlantMenu() {
        
        setViewName("Klant toevoegen\t");
        printHeader();
        
        Klant klant = new Klant();
        
        System.out.println("Voeg een nieuwe klant toe aan de database.\n");
        System.out.print("Voer de voornaam van de klant in (en druk dan op enter): ");
        klant.setVoornaam(SCANNER.next());
        System.out.print("Voer de achternaam van de klant in (en druk dan op enter): ");
        klant.setAchternaam(SCANNER.next()); SCANNER.nextLine();
        System.out.print("Voer het tussenvoegsel van de klant in (en druk dan op enter): ");
        klant.setTussenvoegsel(SCANNER.nextLine());
            
        System.out.println("\nDe opgegeven klant:");
        printAttributes(klant);
        System.out.println("\nIs dit correct?");
        System.out.println("1. Ja, opslaan.\n0. Nee, stoppen (niets opslaan).\n");
        switch (getSelection()) {
            case "0":   break;
            case "1":   KlantController klantController = new KlantController();
                        klant = klantController.insertKlant(klant);
                        System.out.println("Klant toegevoegd.");
                        addAccountAndAdresForKlant(klant);
                        break;
            default:    System.out.println(MAINERROR);
        }
    }
    
    private void addAccountAndAdresForKlant(Klant klant) {
        AccountMenuView accountMenuView = new AccountMenuView();
        accountMenuView.setAccountForKlant(klant);
        AdresMenuView adresMenuView = new AdresMenuView();
        adresMenuView.showInsertAdresMenu(klant);
    }
    
    Klant showSelectKlantMenu() {
        
        setViewName("Klant zoeken\t");
        printHeader();
        
        Long id = printList();
        System.out.println("\n0. Terug");
        
        KlantController klantController = new KlantController();
        Klant klant = new Klant();
        
        System.out.println("\nSelecteer klant.\n");
        Long selection = Long.parseLong(getSelection());
        if (selection == 0) {
        }
        else if (selection < 0 || selection > id) {
            System.out.println(MAINERROR);
        }
        else {
            klant = klantController.selectKlant(selection);
            if (klant.getAchternaam() != null) {
                System.out.println("De geselecteerde klant:");
                printAttributes(klant);
            }
            else {
                System.out.println(MAINERROR);
            }
        }
        return klant;
    }
    
    Klant showSelectKlantMenu(String achternaam) {
        
        setViewName("Klant zoeken\t");
        printHeader();
        
        int id = printList(achternaam);
        System.out.println("\n0. Terug");
        
        KlantController klantController = new KlantController();
        Klant klant = new Klant();
        
        System.out.println("\nSelecteer klant.\n");
        Long selection = Long.parseLong(getSelection());
        if (selection == 0) {
        }
        else if (selection < 0 || selection > id) {
            System.out.println(MAINERROR);
        }
        else {
            klant = klantController.selectKlant(selection);
            System.out.println("De geselecteerde klant:");
            printAttributes(klant);
        }
        return klant;
    }
    
    private Long printList() {

        KlantController klantController = new KlantController();
        ArrayList<Klant> klanten = klantController.selectKlanten();
        
        System.out.printf("%3s%-16s\n", "", "Naam");
        Long x = 0L;
        for (Klant e: klanten) {
            System.out.print(e.getId() + ". ");
            printAttributes(e);
            x = e.getId();
        }
        return x;
    }
    
    int printList(String achternaam) {
        
        KlantController klantController = new KlantController();
        ArrayList<Klant> klanten = klantController.selectKlanten(achternaam);
        
        System.out.printf("%3s%-16s\n", "", "Naam");
        for (Klant e: klanten) {
            System.out.print(e.getId() + ". ");
            printAttributes(e);
        }
        return klanten.size();
    }

    private void showUpdateKlantMenu() {
        
        Klant klant = showSelectKlantMenu();
        setViewName("Klant aanpassen\t");
        printHeader();
        
        System.out.println("Wat wilt u aanpassen?");
        System.out.println("1. Voornaam\n2. Achternaam\n3. Tussenvoegsel\n\n" +
                "0. Niets, terug naar Klantenpagina\n");
        switch (getSelection()) {
            case "0":   break;
            case "1":   System.out.print("Nieuwe voornaam: ");
                        klant.setVoornaam(SCANNER.next());
                        showUpdatedKlantMenu(klant);
                        break;
            case "2":   System.out.print("Nieuwe achternaam: ");
                        klant.setAchternaam(SCANNER.next());
                        showUpdatedKlantMenu(klant);
                        break;
            case "3":   System.out.print("Nieuwe tussenvoegsel: ");
                        klant.setTussenvoegsel(SCANNER.next());
                        showUpdatedKlantMenu(klant);
                        break;
            default:    System.out.println(MAINERROR);
        }
    }

    private void showUpdatedKlantMenu(Klant klant) {
        
        System.out.println("\nDe aangepaste klant:");
        printAttributes(klant);
        
        System.out.println("\nWilt u de aangepaste klant opslaan?");
        System.out.println("1. Ja\n0. Nee\n");
        switch (getSelection()) {
            case "0":   break;
            case "1":   KlantController klantController = new KlantController();
                        klantController.updateKlant(klant);
                        System.out.println("Klant opgeslagen.");
                        break;
            default:    System.out.println(MAINERROR);
        }
    }
    
    private void showDeleteKlantMenu() {
        
        Klant klant = showSelectKlantMenu();
        Long id = klant.getId();
        
        setViewName("Klant verwijderen");
        printHeader();
        
        System.out.println("Wilt u deze klant verwijderen?");
        System.out.println("1. Ja\n0. Nee\n");
        switch (getSelection()) {
            case "0":   break;
            case "1":   KlantController klantController = new KlantController();
                        klantController.deleteKlant(id);
                        System.out.println("Klant verwijderd.");
                        break;
            default:    System.out.println(MAINERROR);
        }
    }
}
