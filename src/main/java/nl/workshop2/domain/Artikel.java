package nl.workshop2.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Vosjes
 */
@Entity
public class Artikel {
    
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
	
	@Column(unique=true, nullable=false)
    private String naam;
    
	@Column(nullable=false)
    private BigDecimal prijs;
    
	@Column(nullable=true)
	// Was niet standaard true, wellicht vanwege primitieve variabele ipv referentievariabele?
    private int voorraad;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public BigDecimal getPrijs() {
        return prijs;
    }

    public void setPrijs(BigDecimal prijs) {
        this.prijs = prijs;
    }

    public int getVoorraad() {
        return voorraad;
    }

    public void setVoorraad(int voorraad) {
        this.voorraad = voorraad;
    }
    
    @Override
    public String toString() {
        return "Artikel{" + "id=" + id + ", naam=" + naam + ", prijs=" + prijs + ", voorraad=" + voorraad + '}';
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((naam == null) ? 0 : naam.hashCode());
		result = prime * result + ((prijs == null) ? 0 : prijs.hashCode());
		result = prime * result + voorraad;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Artikel other = (Artikel) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (naam == null) {
			if (other.naam != null)
				return false;
		} else if (!naam.equals(other.naam))
			return false;
		if (prijs == null) {
			if (other.prijs != null)
				return false;
		} else if (!prijs.equals(other.prijs))
			return false;
		if (voorraad != other.voorraad)
			return false;
		return true;
	}
}
