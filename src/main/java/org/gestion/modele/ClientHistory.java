package org.gestion.modele;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
//yes
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CLIENTHISTORY")
public class ClientHistory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CLIENT_HISTORY_ID")
	private int clientHistoryId;
	@Column(name = "IS_ANONYMOUS")
	private boolean isAnonymous;
	@Column(name = "WAIT_TIME_RATING")
	private int waitTimeRating;
	@Column(name = "OVERALL_RATING")
	private int overallRating;
	@Column(name = "REVIEW")
	private String review;
	@Column(name = "DATE_REVIEW")
	private Date dateReview;
	@Column(name = "CLIENT_HISTORY_IMAGE")
	private String clientHistoryImage;
	@Column(name = "HISTORY_TYPE")
	private String historyType;

	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH })
	@JoinColumn(name = "PERSON_ID")
	private Client client;

	public ClientHistory() {
		// default constructor
	}

	/**
	 * Complet
	 * 
	 * @param clientHistoryId
	 * @param isAnonymous
	 * @param waitTimeRating
	 * @param overallRating
	 * @param review
	 * @param dateReview
	 * @param clientHistoryImage
	 * @param historyType
	 * @param client
	 */
	public ClientHistory(int clientHistoryId, boolean isAnonymous, int waitTimeRating, int overallRating, String review,
			Date dateReview, String clientHistoryImage, String historyType, Client client) {
		super();
		this.clientHistoryId = clientHistoryId;
		this.isAnonymous = isAnonymous;
		this.waitTimeRating = waitTimeRating;
		this.overallRating = overallRating;
		this.review = review;
		this.dateReview = dateReview;
		this.clientHistoryImage = clientHistoryImage;
		this.historyType = historyType;
		this.client = client;

		client.getListClientHistory().add(this);
	}

	/**
	 * Incomplet
	 * 
	 * @param clientHistoryId
	 * @param isAnonymous
	 * @param waitTimeRating
	 * @param overallRating
	 * @param review
	 * @param dateReview
	 * @param clientHistoryImage
	 * @param historyType
	 */
	public ClientHistory(int clientHistoryId, boolean isAnonymous, int waitTimeRating, int overallRating, String review,
			Date dateReview, String clientHistoryImage, String historyType) {
		super();
		this.clientHistoryId = clientHistoryId;
		this.isAnonymous = isAnonymous;
		this.waitTimeRating = waitTimeRating;
		this.overallRating = overallRating;
		this.review = review;
		this.dateReview = dateReview;
		this.clientHistoryImage = clientHistoryImage;
		this.historyType = historyType;
	}

	public int getClientHistoryId() {
		return clientHistoryId;
	}

	public void setClientHistoryId(int newClientHistoryId) {
		clientHistoryId = newClientHistoryId;
	}

	public boolean getIsAnonymous() {
		return isAnonymous;
	}

	public void setIsAnonymous(boolean newIsAnonymous) {
		isAnonymous = newIsAnonymous;
	}

	public int getWaitTimeRating() {
		return waitTimeRating;
	}

	public void setWaitTimeRating(int newWaitTimeRating) {
		waitTimeRating = newWaitTimeRating;
	}

	public int getOverallRating() {
		return overallRating;
	}

	public void setOverallRating(int newOverallRating) {
		overallRating = newOverallRating;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String newReview) {
		review = newReview;
	}

	public Date getDateReview() {
		return dateReview;
	}

	public void setDateReview(Date newDateReview) {
		dateReview = newDateReview;
	}

	public String getClientHistoryImage() {
		return clientHistoryImage;
	}

	public void setClientHistoryImage(String newClientHistoryImage) {
		clientHistoryImage = newClientHistoryImage;
	}

	public String getHistoryType() {
		return historyType;
	}

	public void setHistoryType(String newHistoryType) {
		historyType = newHistoryType;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client newClient) {
		if (this.client == null || !this.client.equals(newClient)) {
			if (this.client != null) {
				Client oldClient = this.client;
				this.client = null;
				oldClient.removeClientHistory(this);
			}
			if (newClient != null) {
				this.client = newClient;
				this.client.addClientHistory(this);
			}
		}
	}

	@Override
	public String toString() {
		return "ClientHistory [clientHistoryId=" + clientHistoryId + ", isAnonymous=" + isAnonymous
				+ ", waitTimeRating=" + waitTimeRating + ", overallRating=" + overallRating + ", review=" + review
				+ ", dateReview=" + dateReview + ", clientHistoryImage=" + clientHistoryImage + ", historyType="
				+ historyType + ", client=" + client + "]";
	}

}