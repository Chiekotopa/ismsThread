
package iSMSThread.jpa.entities;

/**
 *
 * @author Néhémie
 */

public class Sms {

	private Integer idSMS;

	private String sender;

	private String destinataire;

	private String contenu;

	private double cout;

	private boolean etat;

	private Client clientidClient;

	public Sms() {
	}

	public Sms(Integer idSMS) {
		this.idSMS = idSMS;
	}

	public Sms(Integer idSMS, String destinataire) {
		this.idSMS = idSMS;
		this.destinataire = destinataire;
	}

	public Integer getIdSMS() {

		return idSMS;
	}

	public void setIdSMS(Integer idSMS) {

		this.idSMS = idSMS;
	}

	public String getSender() {

		return sender;
	}

	public void setSender(String sender) {

		this.sender = sender;
	}

	public String getDestinataire() {

		return destinataire;
	}

	public void setDestinataire(String destinataire) {

		this.destinataire = destinataire;
	}

	public String getContenu() {

		return contenu;
	}

	public void setContenu(String contenu) {

		this.contenu = contenu;
	}

	public double getCout() {

		return cout;
	}

	public void setCout(double cout) {

		this.cout = cout;
	}

	public boolean isEtat() {

		return etat;
	}

	public void setEtat(boolean etat) {

		this.etat = etat;
	}

	public Client getClientidClient() {

		return clientidClient;
	}

	public void setClientidClient(Client clientidClient) {

		this.clientidClient = clientidClient;
	}

	@Override
	public String toString() {

		return "Sms [idSMS=" + idSMS + ", sender=" + sender + ", destinataire=" + destinataire + ", contenu=" + contenu
				+ ", cout=" + cout + ", etat=" + etat + ", clientidClient=" + clientidClient + "]";
	}

}
