package org.gestion.modele;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name = "PERSON")
@DiscriminatorValue("C")
public class Client extends Person implements Serializable {

	@OneToMany(	fetch = FetchType.LAZY, 
				mappedBy = "client", 
				cascade = CascadeType.ALL)
	private List<Appointment> listAppointment;
	
	@ManyToOne(	fetch=FetchType.LAZY,targetEntity=Insurance.class,
			cascade= {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	private Insurance insurance;
	
	@OneToMany(mappedBy = "client")
	private List<ClientHistory> listClientHistory;

	// Constructors and Getter/Setter methods,
	public Client() {
	}

	public Client(String personFirstName, String personLastName, String personEmail, String personImage) {
		this.personFirstName = personFirstName;
		this.personLastName = personLastName;
		this.personEmail = personEmail;
		this.personImage = personImage;
	}

	// Insurance
	public Insurance getInsurance() {
		return insurance;
	}

	public void setInsurance(Insurance newInsurance) {
		if (this.insurance == null || !this.insurance.equals(newInsurance)) {
			if (this.insurance != null) {
				Insurance oldInsurance = this.insurance;
				this.insurance = null;
				oldInsurance.removeClient(this);
			}
			if (newInsurance != null) {
				this.insurance = newInsurance;
				this.insurance.addClient(this);
			}
		}
	}
	// End insurance
	
	// APPOINTMENT
	public List<Appointment> getListAppointment() {
		if (listAppointment == null)
			listAppointment = new ArrayList<>();
		return listAppointment;
	}

	public Iterator<Appointment> getIteratorAppointment() {
		if (listAppointment == null)
			listAppointment = new ArrayList<>();
		return listAppointment.iterator();
	}

	public void setListAppointment(List<Appointment> newListAppointment) {
		removeAllAppointment();
		for (Iterator<Appointment> iter = newListAppointment.iterator(); iter.hasNext();)
			addAppointment(iter.next());
	}

	public void addAppointment(Appointment newAppointment) {
		if (newAppointment == null)
			return;
		if (this.listAppointment == null)
			this.listAppointment = new ArrayList<>();
		if (!this.listAppointment.contains(newAppointment)) {
			this.listAppointment.add(newAppointment);
			newAppointment.setClient(this);
		}
	}

	public void removeAppointment(Appointment oldAppointment) {
		if (oldAppointment == null)
			return;
		if (this.listAppointment != null && this.listAppointment.contains(oldAppointment)) {
			this.listAppointment.remove(oldAppointment);
			oldAppointment.setClient(null);
		}
	}

	public void removeAllAppointment() {
		if (listAppointment != null) {
			Appointment oldAppointment;
			for (Iterator<Appointment> iter = getIteratorAppointment(); iter.hasNext();) {
				oldAppointment = iter.next();
				iter.remove();
				oldAppointment.setClient(null);
			}
		}
	}
	
	// End Appointement
	
	// Client History
	public List<ClientHistory> getListClientHistory() {
		if (listClientHistory == null)
			listClientHistory = new ArrayList<>();
		return listClientHistory;
	}

	public Iterator<ClientHistory> getIteratorClientHistory() {
		if (listClientHistory == null)
			listClientHistory = new ArrayList<>();
		return listClientHistory.iterator();
	}

	public void setListClientHistory(List<ClientHistory> newListClientHistory) {
		removeAllClientHistory();
		for (Iterator<ClientHistory> iter = newListClientHistory.iterator(); iter.hasNext();)
			addClientHistory(iter.next());
	}

	public void addClientHistory(ClientHistory newClientHistory) {
		if (newClientHistory == null)
			return;
		if (this.listClientHistory == null)
			this.listClientHistory = new ArrayList<>();
		if (!this.listClientHistory.contains(newClientHistory)) {
			this.listClientHistory.add(newClientHistory);
			newClientHistory.setClient(this);
		}
	}

	public void removeClientHistory(ClientHistory oldClientHistory) {
		if (oldClientHistory == null)
			return;
		if (this.listClientHistory != null && this.listClientHistory.contains(oldClientHistory)) {
				this.listClientHistory.remove(oldClientHistory);
				oldClientHistory.setClient( null);
			}
	}

	public void removeAllClientHistory() {
		if (listClientHistory != null) {
			ClientHistory oldClientHistory;
			for (Iterator<ClientHistory> iter = getIteratorClientHistory(); iter.hasNext();) {
				oldClientHistory = iter.next();
				iter.remove();
				oldClientHistory.setClient(null);
			}
		}
	}
	// end client History
}