package org.gestion.modele;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
//yes
@Entity
@Table(name="TELEPHONE")
public class Telephone {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="TELEPHONE_ID")
	private int telephoneId;
	@Column(name="TELEPHONE_NUMBER")
	private String telephoneNumber;
	@Column(name="TELEPHONE_TYPE")
	private String telephoneType;

	@ManyToMany(fetch=FetchType.LAZY, 
				cascade= {CascadeType.PERSIST,CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
	@JoinTable(	name="phone_book",
				joinColumns=@JoinColumn(name="PERSON_ID"),
				inverseJoinColumns=@JoinColumn(name="TELEPHONE_ID"))
	private List<Person> listPerson;

	public Telephone() {
		// default constructor
	}
	/**
	 * Complet
	 * @param telephoneId
	 * @param telephoneNumber
	 * @param telephoneType
	 * @param listPerson
	 */
	public Telephone(int telephoneId, String telephoneNumber, String telephoneType, List<Person> listPerson) {
		this.telephoneId = telephoneId;
		this.telephoneNumber = telephoneNumber;
		this.telephoneType = telephoneType;
		this.listPerson = listPerson;
		
		for (Person phoneBook : listPerson) {
			phoneBook.addTelephone(this);
		}
	}
	/**
	 * Pas de listPerson
	 * @param telephoneId
	 * @param telephoneNumber
	 * @param telephoneType
	 * @param listPerson
	 */
	public Telephone(int telephoneId, String telephoneNumber, String telephoneType) {
		super();
		this.telephoneId = telephoneId;
		this.telephoneNumber = telephoneNumber;
		this.telephoneType = telephoneType;
	}

	public int getTelephoneId() {
		return telephoneId;
	}

	public void setTelephoneId(int newTelephoneId) {
		telephoneId = newTelephoneId;
	}

	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(String newTelephoneNumber) {
		telephoneNumber = newTelephoneNumber;
	}

	public String getTelephoneType() {
		return telephoneType;
	}

	public void setTelephoneType(String newTelephoneType) {
		telephoneType = newTelephoneType;
	}

	public List<Person> getListPerson() {
		if (listPerson == null)
			listPerson = new ArrayList<>();
		return listPerson;
	}

	public Iterator<Person> getIteratorPerson() {
		if (listPerson == null)
			listPerson = new ArrayList<>();
		return listPerson.iterator();
	}

	public void setListPerson(List<Person> newListPerson) {
		removeAllPerson();
		for (Iterator<Person> iter = newListPerson.iterator(); iter.hasNext();)
			addPerson((Person) iter.next());
	}

	public void addPerson(Person newPerson) {
		if (newPerson == null)
			return;
		if (this.listPerson == null)
			this.listPerson = new ArrayList<>();
		if (!this.listPerson.contains(newPerson)) {
			this.listPerson.add(newPerson);
			newPerson.addTelephone(this);
		}
	}

	public void removePerson(Person oldPerson) {
		if (oldPerson == null)
			return;
		if (this.listPerson != null && this.listPerson.contains(oldPerson)) {
			this.listPerson.remove(oldPerson);
			oldPerson.addTelephone(this);
		}
	}

	public void removeAllPerson() {
		if (listPerson != null) {
			Person oldPerson;
			for (Iterator<Person> iter = getIteratorPerson(); iter.hasNext();) {
				oldPerson = iter.next();
				iter.remove();
				oldPerson.addTelephone(this);
			}
		}
	}
	
	@Override
	public String toString() {
		return "Telephone [telephoneId=" + telephoneId + ", telephoneNumber=" + telephoneNumber + ", telephoneType="
				+ telephoneType + ", listPerson=" + listPerson.size()+ "]";
	}

}