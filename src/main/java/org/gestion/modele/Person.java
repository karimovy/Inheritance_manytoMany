package org.gestion.modele;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PERSON")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "discriminator", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue(value = "P")
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PERSON_ID")
	protected int personId;
	@Column(name = "PERSON_FIRST_NAME")
	protected String personFirstName;
	@Column(name = "PERSON_LAST_NAME")
	protected String personLastName;
	@Column(name = "PERSON_EMAIL")
	protected String personEmail;
	@Column(name = "PERSON_IMAGE")
	protected String personImage;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE,
			CascadeType.REFRESH })
	@JoinTable(	name = "phone_book", 
				joinColumns = @JoinColumn(name = "PERSON_ID"), 
				inverseJoinColumns = @JoinColumn(name = "TELEPHONE_ID"))
	protected List<Telephone> listTelephone;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ADDRESS_ID") // ADDRESS_ID is in table Person
	protected Address address;

	// Constructors and Getter/Setter methods,
	public Person() {
	}

	/**
	 * @param personId
	 * @param personFirstName
	 * @param personLastName
	 * @param personEmail
	 * @param personImage
	 */
	public Person(String personFirstName, String personLastName, String personEmail, String personImage) {
		this.personFirstName = personFirstName;
		this.personLastName = personLastName;
		this.personEmail = personEmail;
		this.personImage = personImage;
	}

	public int getPersonId() {
		return personId;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
	}

	public String getPersonFirstName() {
		return personFirstName;
	}

	public void setPersonFirstName(String personFirstName) {
		this.personFirstName = personFirstName;
	}

	public String getPersonLastName() {
		return personLastName;
	}

	public void setPersonLastName(String personLastName) {
		this.personLastName = personLastName;
	}

	public String getPersonEmail() {
		return personEmail;
	}

	public void setPersonEmail(String personEmail) {
		this.personEmail = personEmail;
	}

	public String getPersonImage() {
		return personImage;
	}

	public void setPersonImage(String personImage) {
		this.personImage = personImage;
	}

	// Telephone

	public List<Telephone> getListTelephone() {
		if (listTelephone == null)
			listTelephone = new ArrayList<>();
		return listTelephone;
	}

	public Iterator<Telephone> getIteratorTelephone() {
		if (listTelephone == null)
			listTelephone = new ArrayList<>();
		return listTelephone.iterator();
	}

	public void setTelephone(List<Telephone> newTelephone) {
		removeAllTelephone();
		for (Iterator<Telephone> iter = newTelephone.iterator(); iter.hasNext();)
			addTelephone(iter.next());
	}

	public void addTelephone(Telephone newTelephone) {
		if (newTelephone == null)
			return;
		if (this.listTelephone == null)
			this.listTelephone = new ArrayList<>();
		if (!this.listTelephone.contains(newTelephone)) {
			this.listTelephone.add(newTelephone);
			newTelephone.addPerson(this);

		}
	}

	public void removeTelephone(Telephone oldTelephone) {
		if (oldTelephone == null)
			return;
		if (this.listTelephone != null && this.listTelephone.contains(oldTelephone)) {
			this.listTelephone.remove(oldTelephone);
			oldTelephone.addPerson(this);
		}
	}

	public void removeAllTelephone() {
		if (listTelephone != null) {
			Telephone oldTelephone;
			for (Iterator<Telephone> iter = getIteratorTelephone(); iter.hasNext();) {
				oldTelephone = iter.next();
				iter.remove();
				oldTelephone.addPerson(this);
			}
		}
	}
	// End Telephone

	// Address
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address newAddress) {
		if (this.address == null || !this.address.equals(newAddress)) {
			if (this.address != null) {
				Address oldAddress = this.address;
				this.address = null;
				oldAddress.removePerson(this);
				oldAddress.removePerson(this);
			}
			if (newAddress != null) {
				this.address = newAddress;
				this.address.addPerson(this);
			}
		}
	}
	// End Address

}