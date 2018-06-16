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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


@Entity
@Table(name = "PERSON")
@DiscriminatorValue("D")
public class Doctor extends Person {

	@Column(name = "PROFESSIONAL_STATEMENT")
	private String professionalStatement;
	@Column(name = "PRACTICING_FROM")
	private Date practicingFrom;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE,
			CascadeType.REFRESH })
	@JoinTable(name = "DOCTORSPECIALIZATION", joinColumns = @JoinColumn(name = "PERSON_ID"), inverseJoinColumns = @JoinColumn(name = "SPECIALIZATION_ID"))
	private List<Specialization> listSpecialization;

	@OneToMany(mappedBy = "doctor")
	private List<Appointment> listAppointement;

	@OneToMany(mappedBy = "doctor")
	private List<Qualification> listQualification;

	// Constructors and Getter/Setter methods,
	public Doctor() {
	}

	public Doctor(String personFirstName, String personLastName, String personEmail, String personImage,
			String professionalStatement, Date practicingFrom) {
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
	public List<Appointment> getListAppointement() {
		if (listAppointement == null)
			listAppointement = new ArrayList<>();
		return listAppointement;
	}

	public Iterator<Appointment> getIteratorAppointment() {
		if (listAppointement == null)
			listAppointement = new ArrayList<>();
		return listAppointement.iterator();
	}

	public void setListAppointement(List<Appointment> newListAppointment) {
		removeAllAppointment();
		for (Iterator<Appointment> iter = newListAppointment.iterator(); iter.hasNext();)
			addAppointment(iter.next());
	}

	public void addAppointment(Appointment newAppointment) {
		if (newAppointment == null)
			return;
		if (this.listAppointement == null)
			this.listAppointement = new ArrayList<>();
		if (!this.listAppointement.contains(newAppointment)) {
			this.listAppointement.add(newAppointment);
			newAppointment.setDoctor(this);
		}
	}

	public void removeAppointment(Appointment oldAppointment) {
		if (oldAppointment == null)
			return;
		if (this.listAppointement != null && this.listAppointement.contains(oldAppointment)) {
			this.listAppointement.remove(oldAppointment);
			oldAppointment.setClient(null);
		}
	}

	public void removeAllAppointment() {
		if (listAppointement != null) {
			Appointment oldAppointment;
			for (Iterator<Appointment> iter = getIteratorAppointment(); iter.hasNext();) {
				oldAppointment = iter.next();
				iter.remove();
				oldAppointment.setClient(null);
			}
		}
	}

	// QUALIFICATION
	public List<Qualification> getListQualification() {
		if (listQualification == null)
			listQualification = new ArrayList<>();
		return listQualification;
	}

	public Iterator<Qualification> getIteratorQualification() {
		if (listQualification == null)
			listQualification = new ArrayList<>();
		return listQualification.iterator();
	}

	public void setListQualification(List<Qualification> newListQualification) {
		removeAllQualification();
		for (Iterator<Qualification> iter = newListQualification.iterator(); iter.hasNext();)
			addQualification(iter.next());
	}

	public void addQualification(Qualification newQualification) {
		if (newQualification == null)
			return;
		if (this.listQualification == null)
			this.listQualification = new ArrayList<>();
		if (!this.listQualification.contains(newQualification)) {
			this.listQualification.add(newQualification);
			newQualification.setDoctor(this);
		}
	}

	public void removeQualification(Qualification oldQualification) {
		if (oldQualification == null)
			return;
		if (this.listQualification != null && this.listQualification.contains(oldQualification)) {
			this.listQualification.remove(oldQualification);
			oldQualification.setDoctor(null);
		}
	}

	public void removeAllQualification() {
		if (listQualification != null) {
			Qualification oldQualification;
			for (Iterator<Qualification> iter = getIteratorQualification(); iter.hasNext();) {
				oldQualification = iter.next();
				iter.remove();
				oldQualification.setDoctor(null);
			}
		}
	}

	// Specialization
	public List<Specialization> getListSpecialization() {
			if (listSpecialization == null)
				listSpecialization = new ArrayList<>();
			return listSpecialization;
		}

	public Iterator<Specialization> getIteratorSpecialization() {
		if (listSpecialization == null)
			listSpecialization = new ArrayList<>();
		return listSpecialization.iterator();
	}

	public void setListSpecialization(List<Specialization> newListSpecialization) {
		removeAllSpecialization();
		for (Iterator<Specialization> iter = newListSpecialization.iterator(); iter.hasNext();)
			addSpecialization(iter.next());
	}

	public void addSpecialization(Specialization newSpecialization) {
		if (newSpecialization == null)
			return;

		if (this.listSpecialization == null)
			this.listSpecialization = new ArrayList<>();

		if (!this.listSpecialization.contains(newSpecialization)) {

			this.listSpecialization.add(newSpecialization);
			newSpecialization.addDoctor(this);

		}
	}

	public void removeSpecialization(Specialization oldSpecialization) {
		if (oldSpecialization == null)
			return;
		if (this.listSpecialization != null && this.listSpecialization.contains(oldSpecialization)) {
			this.listSpecialization.remove(oldSpecialization);
			oldSpecialization.addDoctor((Doctor) null);
		}
	}

	public void removeAllSpecialization() {
		if (listSpecialization != null) {
			Specialization oldSpecialization;
			for (Iterator<Specialization> iter = getIteratorSpecialization(); iter.hasNext();) {
				oldSpecialization = iter.next();
				iter.remove();
				oldSpecialization.addDoctor(null);
			}
		}
	}
}