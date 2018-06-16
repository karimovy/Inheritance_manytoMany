package org.gestion.modele;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
//yes
@Entity
@Table(name="Address")
public class Address implements Serializable{
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ADDRESS_ID", length=15)
	private int addressId;
	@Column(name="CIVIC_NUMBER", length=15)
	private String civicNumber;
	@Column(name="APP")
	private String app;
	@Column(name="STREET")
	private String street;
	@Column(name="CITY")
	private String city;
	@Column(name="PROVINCE")
	private String province;
	@Column(name="COUNTRY")
	private String country;
	@Column(name="POSTAL_CODE", length=7)
	private String postalCode;
	
	@OneToMany(	mappedBy="address",
				cascade= {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	private List<Person> listPerson;

	public Address() {
		// default constructor
	}
	/**
	 * Compet
	 * @param addressId
	 * @param civicNumber
	 * @param app
	 * @param street
	 * @param city
	 * @param province
	 * @param country
	 * @param postalCode
	 * @param listPerson
	 */
	public Address(int addressId, String civicNumber,String app, String street, String city, String province, String country,
			String postalCode, List<Person> listPerson) {
		super();
		this.addressId = addressId;
		this.civicNumber = civicNumber;
		this.app = app;
		this.street = street;
		this.city = city;
		this.province = province;
		this.country = country;
		this.postalCode = postalCode;
		this.listPerson = listPerson;
		
		for (Person person : listPerson) {
			person.setAddress(this);
		}
		
	}

	/**
	 * Pas de ListPerson
	 * @param addressId
	 * @param civicNumber
	 * @param app
	 * @param street
	 * @param city
	 * @param province
	 * @param country
	 * @param postalCode
	 * @param listPerson
	 */
	public Address(int addressId, String civicNumber,String app, String street, String city, String province, String country,
			String postalCode) {
		super();
		this.addressId = addressId;
		this.civicNumber = civicNumber;
		this.app = app;
		this.street = street;
		this.city = city;
		this.province = province;
		this.country = country;
		this.postalCode = postalCode;
		
	}
	
	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int newAddressId) {
		addressId = newAddressId;
	}

	public String getCivicNumber() {
		return civicNumber;
	}

	public void setCivicNumber(String newCivicNumber) {
		civicNumber = newCivicNumber;
	}

	public String getApp() {
		return app;
	}

	public void setApp(String app) {
		this.app = app;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String newStreet) {
		street = newStreet;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String newCity) {
		city = newCity;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String newProvince) {
		province = newProvince;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String newCountry) {
		country = newCountry;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String newPostalCode) {
		postalCode = newPostalCode;
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
	
	//should be removed
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
			newPerson.setAddress(this);
		}
	}

	public void removePerson(Person oldPerson) {
		if (oldPerson == null)
			return;
		if (this.listPerson != null && this.listPerson.contains(oldPerson)) {
				this.listPerson.remove(oldPerson);
				oldPerson.setAddress(null);
			}
	}

	public void removeAllPerson() {
		if (listPerson != null) {
			Person oldPerson;
			for (Iterator<Person> iter = getIteratorPerson(); iter.hasNext();) {
				oldPerson = iter.next();
				iter.remove();
				oldPerson.setAddress(null);
			}
		}
	}
	@Override
	public String toString() {
		return "Address [addressId=" + addressId + ", civicNumber=" + civicNumber + ", app=" + app + ", street="
				+ street + ", city=" + city + ", province=" + province + ", country=" + country + ", postalCode="
				+ postalCode + "]";
	}

}