package org.gestion.modele;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="QUALIFICATION")
public class Qualification {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="QUALIFICATION_ID")
	private int qualificationId;
	@Column(name="QUALIFICATION_NAME")
	private String qualificationName;
	@Column(name="INSTITUTE_NAME")
	private String instituteName;
	@Column(name="PROCURMENT_DATE")
	private Date procurmentDate;

	@ManyToOne(	fetch=FetchType.LAZY,
				cascade= {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name="PERSON_ID")
	private Doctor doctor;

	public Qualification() {
		// default constructor
	}

	/**
	 * Complet
	 * 
	 * @param qualificationId
	 * @param qualificationName
	 * @param instituteName
	 * @param procurmentDate
	 * @param doctor
	 */
	public Qualification(int qualificationId, String qualificationName, String instituteName, Date procurmentDate,
			Doctor doctor) {
		this.qualificationId = qualificationId;
		this.qualificationName = qualificationName;
		this.instituteName = instituteName;
		this.procurmentDate = procurmentDate;
		this.doctor = doctor;

		doctor.getListQualification().add(this);
	}

	/**
	 * Sans le doctor
	 * 
	 * @param qualificationId
	 * @param qualificationName
	 * @param instituteName
	 * @param procurmentDate
	 * @param doctor
	 */
	public Qualification(int qualificationId, String qualificationName, String instituteName, Date procurmentDate) {
		this.qualificationId = qualificationId;
		this.qualificationName = qualificationName;
		this.instituteName = instituteName;
		this.procurmentDate = procurmentDate;
	}

	public int getQualificationId() {
		return qualificationId;
	}

	public void setQualificationId(int newQualificationId) {
		qualificationId = newQualificationId;
	}

	public String getQualificationName() {
		return qualificationName;
	}

	public void setQualificationName(String newQualificationName) {
		qualificationName = newQualificationName;
	}

	public String getInstituteName() {
		return instituteName;
	}

	public void setInstituteName(String newInstituteName) {
		instituteName = newInstituteName;
	}

	public Date getProcurmentDate() {
		return procurmentDate;
	}

	public void setProcurmentDate(java.util.Date newProcurmentDate) {
		procurmentDate = newProcurmentDate;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor newDoctor) {
		if (this.doctor == null || !this.doctor.equals(newDoctor)) {
			if (this.doctor != null) {
				Doctor oldDoctor = this.doctor;
				this.doctor = null;
				oldDoctor.removeQualification(this);
			}
			if (newDoctor != null) {
				this.doctor = newDoctor;
				this.doctor.addQualification(this);
			}
		}
	}

	@Override
	public String toString() {
		return "Qualification [qualificationId=" + qualificationId + ", qualificationName=" + qualificationName
				+ ", instituteName=" + instituteName + ", procurmentDate=" + procurmentDate + ", doctor=" + doctor
				+ "]";
	}

}