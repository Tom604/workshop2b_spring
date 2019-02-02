package nl.workshop2.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Vosjes
 */
@Entity
public class Bestelling {
    
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
	
	@Column(nullable=false)
    private BigDecimal totaalprijs;
    
	@Column(name="datum_tijd", nullable=false)
    private LocalDateTime datumTijd;
    
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="klant_id", nullable=false)
    private Klant klant;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getTotaalprijs() {
        return totaalprijs;
    }

    public void setTotaalprijs(BigDecimal totaalprijs) {
        this.totaalprijs = totaalprijs;
    }

    public LocalDateTime getDatumTijd() {
        return datumTijd;
    }

    public void setDatumTijd(LocalDateTime datumTijd) {
        this.datumTijd = datumTijd;
    }
    
    public Klant getKlant() {
        return klant;
    }

    public void setKlant(Klant klant) {
        this.klant = klant;
    }

    @Override
    public String toString() {
        return "Bestelling{" + "id=" + id + ", totaalprijs=" + totaalprijs
                + ", datumTijd=" + datumTijd + ", klant=" + klant + '}';
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((datumTijd == null) ? 0 : datumTijd.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((klant == null) ? 0 : klant.hashCode());
		result = prime * result + ((totaalprijs == null) ? 0 : totaalprijs.hashCode());
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
		Bestelling other = (Bestelling) obj;
		if (datumTijd == null) {
			if (other.datumTijd != null)
				return false;
		} else if (!datumTijd.equals(other.datumTijd))
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
		if (totaalprijs == null) {
			if (other.totaalprijs != null)
				return false;
		} else if (!totaalprijs.equals(other.totaalprijs))
			return false;
		return true;
	}
}
