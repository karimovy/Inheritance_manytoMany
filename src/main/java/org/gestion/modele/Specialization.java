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

@Entity
@Table(name="SPECIALIAZTION")
public class Specialization {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="SPECIALIZATION_ID")
	private int specializationId;
	@Column(name="SPECIALIZATION_NAME")
	private String specializationName;
	@Column(name="SPECIALIZATION_DESCRIPTION")
	private String specializationDescription;
	
	@ManyToMany(fetch=FetchType.LAZY, 
			cascade= {CascadeType.PERSIST,CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
	@JoinTable(	name="DOCTORSPECIALIZATION",
				joinColumns=@JoinColumn(name="PERSON_ID"),
				inverseJoinColumns=@JoinColumn(name="SPECIALIZATION_ID"))
	private List<Doctor> listDoctor;
	
	public Specialization() {
		// default constructor
	}
	/**
	 * Complet
	 * @param specializationId
	 * @param specializationName
	 * @param specializationDescription
	 * @param listDoctor
	 */
	public Specialization(int specializationId, String specializationName, String specializationDescription,
			List<Doctor> listDoctor) {
		super();
		this.specializationId = specializationId;
		this.specializationName = specializationName;
		this.specializationDescription = specializationDescription;
		}
	/**
	 * Sans la liste des doctor
	 * @param specializationId
	 * @param specializationName
	 * @param specializationDescription
	 */
	public Specialization(int specializationId, String specializationName, String specializationDescription) {
		super();
		this.specializationId = specializationId;
		this.specializationName = specializationName;
		this.specializationDescription = specializationDescription;
	}

	public int getSpecializationId() {
		return specializationId;
	}

	public void setSpecializationId(int newSpecializationId) {
		specializationId = newSpecializationId;
	}

	public String getSpecializationName() {
		return specializationName;
	}

	public void setSpecializationName(String newSpecializationName) {
		specializationName = newSpecializationName;
	}

	public String getSpecializationDescription() {
		return specializationDescription;
	}

	public void setSpecializationDescription(String newSpecializationDescription) {
		specializationDescription = newSpecializationDescription;
	}

	public List<Doctor> getListDoctor() {
		if (listDoctor == null)
			listDoctor = new ArrayList<>();
		return listDoctor;
	}

	public Iterator<Doctor> getIteratorDoctor() {
		if (listDoctor == null)
			listDoctor = new ArrayList<>();
		return listDoctor.iterator();
	}

	public void setListDoctor(List<Doctor> newListDoctor) {
		removeAllDoctor();
		for (Iterator<Doctor> iter = newListDoctor.iterator(); iter.hasNext();)
			addDoctor(iter.next());
	}

	public void addDoctor(Doctor newDoctor) {
	      if (newDoctor == null)
	         return;
	      
	      if (this.listDoctor == null)
	         this.listDoctor = new ArrayList<>();
	      
	      if (!this.listDoctor.contains(newDoctor))
	      {
   	  
	    	 this.listDoctor.add(newDoctor);
	         newDoctor.addSpecialization(this);
	         
	      }
	   }
	
	public void removeDoctor(Doctor oldDoctor) {
		if (oldDoctor== null)
			return;
		if (this.listDoctor != null && this.listDoctor.contains(oldDoctor)) {
			this.listDoctor.remove(oldDoctor);
			oldDoctor.addSpecialization(null);
		}
	}

	public void removeAllDoctor() {
		if (listDoctor != null) {
			Doctor oldDoctor;
			for (Iterator<Doctor> iter = getIteratorDoctor(); iter.hasNext();) {
				oldDoctor = iter.next();
				iter.remove();
				oldDoctor.addSpecialization(null);
			}
		}
	}
	
	
	@Override
	public String toString() {
		return "Specialization [specializationId=" + specializationId + ", specializationName=" + specializationName
				+ ", specializationDescription=" + specializationDescription + ", listDoctor="  + "]";
	}

}