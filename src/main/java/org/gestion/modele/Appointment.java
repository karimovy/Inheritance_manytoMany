package org.gestion.modele;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

 
@Entity
@Table(name = "APPOINTMENT")
public class Appointment implements Serializable{
	@Id
	@GeneratedValue
	@Column(name = "APPOINTMENT_ID")
    private long id;

    @ManyToOne(cascade = CascadeType.ALL, targetEntity= Doctor.class)
	private Doctor doctor;
    
    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Client.class)
    private Client client; 
     
    // additional fields
    private boolean activated;
    private Date registeredDate;
   
    public Appointment() {
	}
    
	public Appointment(Doctor doctor, Client client) {
		this.doctor = doctor;
		this.client = client;
		this.id= Long.parseLong(String.valueOf(doctor.getPersonId()).concat(String.valueOf(client.getPersonId())));
	}


    public long getId() {
        return id;
    }
 
    public void setId(long id) {
        this.id = id;
    }
 
 
    public Doctor getDoctor() {
        return doctor;
    }
 
    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
 

    public boolean isActivated() {
        return activated;
    }
 
    public void setActivated(boolean activated) {
        this.activated = activated;
    }
 
    @Column(name = "REGISTERED_DATE")
    @Temporal(TemporalType.DATE)
    public Date getRegisteredDate() {
        return registeredDate;
    }
 
    public void setRegisteredDate(Date registeredDate) {
        this.registeredDate = registeredDate;
    }

	public Doctor getManager() {
		return doctor;
	}

	public void setManager(Doctor doctor) {
		this.doctor = doctor;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
    
    
    
}