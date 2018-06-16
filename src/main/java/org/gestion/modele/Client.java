package org.gestion.modele;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "PERSON")
@DiscriminatorValue("C")
public class Client extends Person implements Serializable {

	@OneToMany(	fetch = FetchType.LAZY, 
				mappedBy = "client", 
				cascade = CascadeType.ALL)
	private List<Meeting> listAppointment;

	// Constructors and Getter/Setter methods,
	public Client() {
	}

	public Client(String personFirstName, String personLastName, String personEmail, String personImage) {
		this.personFirstName = personFirstName;
		this.personLastName = personLastName;
		this.personEmail = personEmail;
		this.personImage = personImage;
	}

}