package nl.workshop2.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

//import org.hibernate.annotations.OnDelete;
//import org.hibernate.annotations.OnDeleteAction;

/**
 *
 * @author Vosjes
 */
@Entity
public class Account {
    
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
	@Column(unique=true, nullable=false)
	private String username;
    
	@Column(nullable=false)
	private String wachtwoord;
    
	@Column(nullable=false)
	private String accounttype;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="klant_id", unique=true)
//    @OnDelete(action=OnDeleteAction.CASCADE) // Heeft momenteel geen effect?
    private Klant klant;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getWachtwoord() {
        return wachtwoord;
    }

    public void setWachtwoord(String wachtwoord) {
        this.wachtwoord = wachtwoord;
    }

    public String getAccounttype() {
        return accounttype;
    }

    public void setAccounttype(String accounttype) {
        this.accounttype = accounttype;
    }
    
    public Klant getKlant() {
        return klant;
    }

    public void setKlant(Klant klant) {
        this.klant = klant;
    }

    @Override
    public String toString() {
        return "Account{" + "id=" + id + ", username=" + username + ", wachtwoord="
                + wachtwoord + ", accounttype=" + accounttype + ", klant=" + klant + '}';
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accounttype == null) ? 0 : accounttype.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((klant == null) ? 0 : klant.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		result = prime * result + ((wachtwoord == null) ? 0 : wachtwoord.hashCode());
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
		Account other = (Account) obj;
		if (accounttype == null) {
			if (other.accounttype != null)
				return false;
		} else if (!accounttype.equals(other.accounttype))
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
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		if (wachtwoord == null) {
			if (other.wachtwoord != null)
				return false;
		} else if (!wachtwoord.equals(other.wachtwoord))
			return false;
		return true;
	}
}
