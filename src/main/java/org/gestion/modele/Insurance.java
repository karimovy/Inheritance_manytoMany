package org.gestion.modele;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="INSURANCE")
public class Insurance {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="INSURANCE_ID")
	private int insuranceId;
	@Column(name="INSURANCE_NAME")
	private String insuranceName;

	@OneToMany(mappedBy="insurance")
	private List<Client> listClient;

	public Insurance() {
		// default constructor
	}

	/**
	 * Complet
	 * 
	 * @param insuranceId
	 * @param insuranceName
	 * @param listClient
	 */
	public Insurance(int insuranceId, String insuranceName, List<Client> listClient) {
		super();
		this.insuranceId = insuranceId;
		this.insuranceName = insuranceName;
		this.listClient = listClient;

		for (Client client : listClient) {
			client.setInsurance(this);
		}
	}

	/**
	 * Incomplet
	 * @param insuranceId
	 * @param insuranceName
	 * @param listClient
	 */
	public Insurance(int insuranceId, String insuranceName) {
		this.insuranceId = insuranceId;
		this.insuranceName = insuranceName;
	}

	public int getInsuranceId() {
		return insuranceId;
	}

	public void setInsuranceId(int newInsuranceId) {
		insuranceId = newInsuranceId;
	}

	public String getInsuranceName() {
		return insuranceName;
	}

	public void setInsuranceName(String newInsuranceName) {
		insuranceName = newInsuranceName;
	}

	public List<Client> getListClient() {
		if (listClient == null)
			listClient = new ArrayList<>();
		return listClient;
	}

	public Iterator<Client> getIteratorClient() {
		if (listClient == null)
			listClient = new ArrayList<>();
		return listClient.iterator();
	}

	public void setListClient(List<Client> newClient) {
		removeAllClient();
		for (Iterator<Client> iter = newClient.iterator(); iter.hasNext();)
			addClient(iter.next());
	}

	public void addClient(Client newClient) {
		if (newClient == null)
			return;
		if (this.listClient == null)
			this.listClient = new ArrayList<>();
		if (!this.listClient.contains(newClient)) {
			this.listClient.add(newClient);
			newClient.setInsurance(this);
		}
	}

	public void removeClient(Client oldClient) {
		if (oldClient == null)
			return;
		if (this.listClient != null && this.listClient.contains(oldClient)) {
			this.listClient.remove(oldClient);
			oldClient.setInsurance(null);
		}
	}

	public void removeAllClient() {
		if (listClient != null) {
			Client oldClient;
			for (Iterator<Client> iter = getIteratorClient(); iter.hasNext();) {
				oldClient = iter.next();
				iter.remove();
				oldClient.setInsurance((Insurance) null);
			}
		}
	}

	@Override
	public String toString() {
		return "Insurance [insuranceId=" + insuranceId + ", insuranceName=" + insuranceName + ", listClient="
				+ listClient + "]";
	}

}