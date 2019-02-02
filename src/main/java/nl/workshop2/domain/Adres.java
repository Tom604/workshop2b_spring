package nl.workshop2.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

//import org.hibernate.annotations.OnDelete;
//import org.hibernate.annotations.OnDeleteAction;

/**
 *
 * @author Vosjes
 */
@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames = {"klant_id", "adrestype"})})
public class Adres {
    
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
	
	@Column(nullable=false)
    private String straatnaam;
    
	@Column(nullable=false)
    private int huisnummer;
    
    private String toevoeging;
    
    @Column(nullable=false)
    private String postcode;
    
    @Column(nullable=false)
    private String woonplaats;
    
    @Column(nullable=false)
    private String adrestype;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="klant_id", nullable=false)
//    @OnDelete(action=OnDeleteAction.CASCADE) // Heeft momenteel geen effect?
    private Klant klant;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStraatnaam() {
        return straatnaam;
    }

    public void setStraatnaam(String straatnaam) {
        this.straatnaam = straatnaam;
    }

    public int getHuisnummer() {
        return huisnummer;
    }

    public void setHuisnummer(int huisnummer) {
        this.huisnummer = huisnummer;
    }

    public String getToevoeging() {
        return toevoeging;
    }

    public void setToevoeging(String toevoeging) {
        this.toevoeging = toevoeging;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getWoonplaats() {
        return woonplaats;
    }

    public void setWoonplaats(String woonplaats) {
        this.woonplaats = woonplaats;
    }

    public String getAdrestype() {
        return adrestype;
    }

    public void setAdrestype(String adrestype) {
        this.adrestype = adrestype;
    }

    public Klant getKlant() {
        return klant;
    }

    public void setKlant(Klant klant) {
        this.klant = klant;
    }

    @Override
    public String toString() {
        return "Adres{" + "id=" + id + ", straatnaam=" + straatnaam + ", huisnummer="
                + huisnummer + ", toevoeging=" + toevoeging + ", postcode=" + postcode
                + ", woonplaats=" + woonplaats + ", adrestype=" + adrestype + ", klant=" + klant + '}';
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adrestype == null) ? 0 : adrestype.hashCode());
		result = prime * result + huisnummer;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((klant == null) ? 0 : klant.hashCode());
		result = prime * result + ((postcode == null) ? 0 : postcode.hashCode());
		result = prime * result + ((straatnaam == null) ? 0 : straatnaam.hashCode());
		result = prime * result + ((toevoeging == null) ? 0 : toevoeging.hashCode());
		result = prime * result + ((woonplaats == null) ? 0 : woonplaats.hashCode());
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
		Adres other = (Adres) obj;
		if (adrestype == null) {
			if (other.adrestype != null)
				return false;
		} else if (!adrestype.equals(other.adrestype))
			return false;
		if (huisnummer != other.huisnummer)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (klant == null) {
			if (other.klant != null)
				return false;
		} else if (!klant.equals(other.klant))
			return false;
		if (postcode == null) {
			if (other.postcode != null)
				return false;
		} else if (!postcode.equals(other.postcode))
			return false;
		if (straatnaam == null) {
			if (other.straatnaam != null)
				return false;
		} else if (!straatnaam.equals(other.straatnaam))
			return false;
		if (toevoeging == null) {
			if (other.toevoeging != null)
				return false;
		} else if (!toevoeging.equals(other.toevoeging))
			return false;
		if (woonplaats == null) {
			if (other.woonplaats != null)
				return false;
		} else if (!woonplaats.equals(other.woonplaats))
			return false;
		return true;
	}
}
