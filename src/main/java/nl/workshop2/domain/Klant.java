package nl.workshop2.domain;

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
public class Klant {
    
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
	
	@Column(nullable=false)
    private String voornaam;
	
	@Column(nullable=false)
    private String achternaam;
	
    private String tussenvoegsel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public String getTussenvoegsel() {
        return tussenvoegsel;
    }

    public void setTussenvoegsel(String tussenvoegsel) {
        this.tussenvoegsel = tussenvoegsel;
    }
    
    @Override
    public String toString() {
        if (tussenvoegsel == null) {
            return "Klant{" + "id=" + id + ", voornaam=" + voornaam + ", achternaam="
                    + achternaam + '}';
        }
        return "Klant{" + "id=" + id + ", voornaam=" + voornaam + ", achternaam="
                + achternaam + ", tussenvoegsel=" + tussenvoegsel + '}';
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((achternaam == null) ? 0 : achternaam.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((tussenvoegsel == null) ? 0 : tussenvoegsel.hashCode());
		result = prime * result + ((voornaam == null) ? 0 : voornaam.hashCode());
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
		Klant other = (Klant) obj;
		if (achternaam == null) {
			if (other.achternaam != null)
				return false;
		} else if (!achternaam.equals(other.achternaam))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (tussenvoegsel == null) {
			if (other.tussenvoegsel != null)
				return false;
		} else if (!tussenvoegsel.equals(other.tussenvoegsel))
			return false;
		if (voornaam == null) {
			if (other.voornaam != null)
				return false;
		} else if (!voornaam.equals(other.voornaam))
			return false;
		return true;
	}
}
