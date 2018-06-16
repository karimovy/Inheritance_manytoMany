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

import org.gestion.modele.Meeting;
import org.gestion.modele.Person;


@Entity
@Table(name="PERSON")
@DiscriminatorValue("D")
public class Doctor extends Person{

    @Column(name="Budget")
    private double budget;

    @OneToMany(mappedBy = "doctor")
	private List<Meeting> listMeeting;
    
	// Constructors and Getter/Setter methods,
	public Doctor() {
	}

    public Doctor(String personFirstName, String personLastName, String personEmail, String personImage, double budget) {
        super(personFirstName, personLastName, personEmail, personImage);
        this.budget=budget;
    }

	public double getBudget() {
        return budget;
    }


    public void setBudget(double budget) {
        this.budget = budget;
    }

 // APPOINTMENT
 		public List<Meeting> getListMeeting() {
 			if (listMeeting == null)
 				listMeeting = new ArrayList<>();
 			return listMeeting;
 		}

 		public Iterator<Meeting> getIteratorMeeting() {
 			if (listMeeting == null)
 				listMeeting = new ArrayList<>();
 			return listMeeting.iterator();
 		}

 		public void setListMeeting(List<Meeting> newListMeeting) {
 			removeAllMeeting();
 			for (Iterator<Meeting> iter = newListMeeting.iterator(); iter.hasNext();)
 				addMeeting(iter.next());
 		}

 		public void addMeeting(Meeting newMeeting) {
 			if (newMeeting == null)
 				return;
 			if (this.listMeeting == null)
 				this.listMeeting = new ArrayList<>();
 			if (!this.listMeeting.contains(newMeeting)) {
 				this.listMeeting.add(newMeeting);
 				newMeeting.setDoctor(this);
 			}
 		}

 		public void removeMeeting(Meeting oldMeeting) {
 			if (oldMeeting == null)
 				return;
 			if (this.listMeeting != null && this.listMeeting.contains(oldMeeting)) {
 				this.listMeeting.remove(oldMeeting);
 				oldMeeting.setClient(null);
 			}
 		}

 		public void removeAllMeeting() {
 			if (listMeeting != null) {
 				Meeting oldMeeting;
 				for (Iterator<Meeting> iter = getIteratorMeeting(); iter.hasNext();) {
 					oldMeeting = iter.next();
 					iter.remove();
 					oldMeeting.setClient(null);
 				}
 			}
 		}
}