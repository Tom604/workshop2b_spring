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

/**
 *
 * @author Vosjes
 */
@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames = {"bestelling_id", "artikel_id"})})
public class Bestelregel {
    
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
	
	@Column(nullable=true)
    private int aantal;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="bestelling_id", nullable=false)
    private Bestelling bestelling;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="artikel_id", nullable=false)
    private Artikel artikel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAantal() {
        return aantal;
    }

    public void setAantal(int aantal) {
        this.aantal = aantal;
    }
    
    public Bestelling getBestelling() {
        return bestelling;
    }

    public void setBestelling(Bestelling bestelling) {
        this.bestelling = bestelling;
    }

    public Artikel getArtikel() {
        return artikel;
    }

    public void setArtikel(Artikel artikel) {
        this.artikel = artikel;
    }

    @Override
    public String toString() {
        return "BestelRegel{" + "id=" + id + ", bestelling=" + bestelling
                + ", artikel=" + artikel + ", aantal=" + aantal + '}';
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + aantal;
		result = prime * result + ((artikel == null) ? 0 : artikel.hashCode());
		result = prime * result + ((bestelling == null) ? 0 : bestelling.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Bestelregel other = (Bestelregel) obj;
		if (aantal != other.aantal)
			return false;
		if (artikel == null) {
			if (other.artikel != null)
				return false;
		} else if (!artikel.equals(other.artikel))
			return false;
		if (bestelling == null) {
			if (other.bestelling != null)
				return false;
		} else if (!bestelling.equals(other.bestelling))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
