package org.gestion.modele;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.gestion.modele.Appointment;
import org.gestion.modele.Person;

@Entity
@Table(name = "PERSON")
@DiscriminatorValue("D")
public class Doctor extends Person {

	@Column(name = "PROFESSIONAL_STATEMENT")
	private String professionalStatement;
	@Column(name = "PRACTICING_FROM")
	private Date practicingFrom;

	@OneToMany(mappedBy = "doctor")
	private List<Appointment> listMeeting;

	// Constructors and Getter/Setter methods,
	public Doctor() {
	}

	public Doctor(String personFirstName, String personLastName, String personEmail, String personImage, String professionalStatement,Date practicingFrom) {
		super(personFirstName, personLastName, personEmail, personImage);
		this.professionalStatement = professionalStatement;
		this.practicingFrom = practicingFrom;
	}

	public String getProfessionalStatement() {
		return professionalStatement;
	}

	public void setProfessionalStatement(String newProfessionalStatement) {
		professionalStatement = newProfessionalStatement;
	}

	public Date getPracticingFrom() {
		return practicingFrom;
	}

	// APPOINTMENT
	public List<Appointment> getListMeeting() {
		if (listMeeting == null)
			listMeeting = new ArrayList<>();
		return listMeeting;
	}

	public Iterator<Appointment> getIteratorMeeting() {
		if (listMeeting == null)
			listMeeting = new ArrayList<>();
		return listMeeting.iterator();
	}

	public void setListMeeting(List<Appointment> newListMeeting) {
		removeAllMeeting();
		for (Iterator<Appointment> iter = newListMeeting.iterator(); iter.hasNext();)
			addMeeting(iter.next());
	}

	public void addMeeting(Appointment newMeeting) {
		if (newMeeting == null)
			return;
		if (this.listMeeting == null)
			this.listMeeting = new ArrayList<>();
		if (!this.listMeeting.contains(newMeeting)) {
			this.listMeeting.add(newMeeting);
			newMeeting.setDoctor(this);
		}
	}

	public void removeMeeting(Appointment oldMeeting) {
		if (oldMeeting == null)
			return;
		if (this.listMeeting != null && this.listMeeting.contains(oldMeeting)) {
			this.listMeeting.remove(oldMeeting);
			oldMeeting.setClient(null);
		}
	}

	public void removeAllMeeting() {
		if (listMeeting != null) {
			Appointment oldMeeting;
			for (Iterator<Appointment> iter = getIteratorMeeting(); iter.hasNext();) {
				oldMeeting = iter.next();
				iter.remove();
				oldMeeting.setClient(null);
			}
		}
	}
}