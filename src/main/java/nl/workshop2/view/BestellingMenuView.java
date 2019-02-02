package nl.workshop2.view;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import nl.workshop2.controller.BestellingController;
import nl.workshop2.controller.KlantController;
import nl.workshop2.domain.Bestelling;
import nl.workshop2.domain.Klant;

/**
 *
 * @author Vosjes
 */
public class BestellingMenuView extends MenuView {
    
    @Override
    public void showMenu() {
        
        String selection = "";
        
        do {
            setViewName("Bestellingen\t");
            printHeader();
            
            System.out.println("1. Bestelling toevoegen\n2. Bestelling zoeken\n3. Bestelling " +
                "aanpassen\n4. Bestelling verwijderen\n\n0. Terug naar Hoofdpagina\n");
            selection = getSelection();
            switch (selection) {
                case "0":   break;
                case "1":   showInsertBestellingMenu(); break;
                case "2":   showSelectionOptionsMenu(); break;
                case "3":   Bestelling bestelling = showSelectionOptionsMenu();
                            showUpdateBestellingMenu(bestelling); break;
                case "4":   showDeleteBestellingMenu(); break;
                default:    System.out.println(MAINERROR);
            }
        } while (selection.equals("0") == false);
    }

    @Override
    public void setViewName(String viewName) {
        this.viewName = viewName;
    }
    
    private void printAttributes(Bestelling bestelling) {
        
        KlantController klantController = new KlantController();
        bestelling.setKlant(klantController.selectKlant(bestelling.getKlant().getId()));
        
        if (bestelling.getKlant().getTussenvoegsel() == null) {
            System.out.printf("%-8s%-22s%-16s\n", bestelling.getTotaalprijs(),
                    bestelling.getDatumTijd(), bestelling.getKlant().getVoornaam() + " " +
                    bestelling.getKlant().getAchternaam());
        }
        else {
            System.out.printf("%-8s%-22s%-20s\n", bestelling.getTotaalprijs(),
                    bestelling.getDatumTijd(), bestelling.getKlant().getVoornaam() + " " +
                    bestelling.getKlant().getTussenvoegsel() + " " + bestelling.getKlant().getAchternaam());
        }
    }
    
    private void showInsertBestellingMenu() {
        
        Bestelling bestelling = getInitializedBestelling();
        
        setViewName("Bestelling toevoegen");
        printHeader();
        
        System.out.println("Voeg een nieuwe bestelling toe voor deze klant.\n");
        System.out.println("1. Ja\n0. Nee, terug naar Bestellingenpagina\n");
        switch (getSelection()) {
            case "0":   break;
            case "1":   BestellingController bestellingController = new BestellingController();
                        bestelling = bestellingController.insertBestelling(bestelling);
                        System.out.println("Bestelling toegevoegd.");
                        showUpdateBestellingMenu(bestelling);
                        break;
            default:    System.out.println(MAINERROR);
        }
    }
    
    private Bestelling getInitializedBestelling() {
        
        KlantMenuView klantMenuView = new KlantMenuView();
        Klant klant = klantMenuView.showSelectKlantMenu();
        
        Bestelling bestelling = new Bestelling();
        bestelling.setKlant(klant);
        bestelling.setTotaalprijs(new BigDecimal("0")); // Initiële waarde voor totaalprijs
        bestelling.setDatumTijd(LocalDateTime.now());
        return bestelling;
    }
    
    private Bestelling showSelectionOptionsMenu() {
        
        setViewName("Bestelling zoeken");
        printHeader();
        
        Bestelling bestelling = new Bestelling();
        
        System.out.println("Wilt u:");
        System.out.println("1. Bestelling op klantnaam zoeken\n2. Alle bestellingen " + 
                "weergeven\n\n0. Terug naar Bestellingenpagina\n");
        switch (getSelection()) {
            case "0":   break;
            case "1":   bestelling = showSelectBestellingFromKlantMenu();
                        break;
            case "2":   bestelling = showSelectBestellingMenu();
                        break;
            default:    System.out.println(MAINERROR);
        }
        
        return bestelling;
    }
    
    private Bestelling showSelectBestellingFromKlantMenu() {
        
        BestellingController bestellingController = new BestellingController();
        Bestelling bestelling = new Bestelling();
        Klant klant;
        
        // Selecteer klant
        System.out.print("Selecteer bestelling op achternaam klant: ");
        KlantMenuView klantMenuView = new KlantMenuView();
        klant = klantMenuView.showSelectKlantMenu(SCANNER.next());
        System.out.println();
        
        // Print bestellingen voor de klant
        int id = printList(klant.getId());
        System.out.println("\n0. Terug\n");
        
        System.out.println("Selecteer bestelling:\n");
        Long selection = Long.parseLong(getSelection());
        if (selection == 0) {
        }
        else if (selection < 0 || selection > id) {
            System.out.println(MAINERROR);
        }
        else {
            bestelling = bestellingController.selectBestelling(selection);
            System.out.println("De geselecteerde bestelling:");
            printAttributes(bestelling);
            System.out.println();
            BestelregelMenuView bestelregelMenuView = new BestelregelMenuView();
            bestelregelMenuView.printList(selection);
        }
        return bestelling;
    }
    
    Bestelling showSelectBestellingMenu() {
        
        int id = printList();
        System.out.println("\n0. Terug\n");
        
        BestellingController bestellingController = new BestellingController();
        Bestelling bestelling = new Bestelling();
        
        System.out.println("Selecteer bestelling.\n");
        Long selection = Long.parseLong(getSelection());
        if (selection == 0) {
        }
        else if (selection < 0 || selection > id) {
            System.out.println(MAINERROR);
        }
        else {
            bestelling = bestellingController.selectBestelling(selection);
            System.out.println("De geselecteerde bestelling:");
            printAttributes(bestelling);
            System.out.println();
            BestelregelMenuView bestelregelMenuView = new BestelregelMenuView();
            bestelregelMenuView.printList(selection);
//            /*
//            TODO - S: Ook nog artikelnaam printen (print nu alleen artikel id)
//            */
        }
        return bestelling;
    }
    
    private int printList() {

        BestellingController bestellingController = new BestellingController();
        ArrayList<Bestelling> bestellingen = bestellingController.selectBestellingen();
        
        System.out.printf("%-3s%-8s%-22s%-16s\n", "", "Prijs", "Datum en tijd", "Naam");
        for (Bestelling e: bestellingen) {
            System.out.print(e.getId() + ". ");
            printAttributes(e);
        }
        return bestellingen.size();
    }
    
    private int printList(Long klantId) {

        BestellingController bestellingController = new BestellingController();
        ArrayList<Bestelling> bestellingen = bestellingController.selectBestellingen(klantId);
        
        System.out.printf("%-3s%-8s%-22s%-16s\n", "", "Prijs", "Datum en tijd", "Naam");
        for (Bestelling e: bestellingen) {
            System.out.print(e.getId() + ". ");
            printAttributes(e);
        }
        return bestellingen.size();
    }
    
    private void showUpdateBestellingMenu(Bestelling bestelling) {
        BestelregelMenuView bestelregelMenuView = new BestelregelMenuView();
        bestelregelMenuView.showMenuWithBestelling(bestelling);
    }
    
    private void showDeleteBestellingMenu() {
        
        Bestelling bestelling = showSelectionOptionsMenu();
        Long id = bestelling.getId();
        
        setViewName("Bestelling verwijderen");
        printHeader();
        
        System.out.println("Wilt u deze bestelling verwijderen?");
        System.out.println("1. Ja\n0. Nee\n");
        switch (getSelection()) {
            case "0":   break;
            case "1":   BestellingController bestellingController = new BestellingController();
                        bestellingController.deleteBestelling(id);
                        System.out.println("Bestelling verwijderd.");
                        break;
            default:    System.out.println(MAINERROR);
        }
    }
}
