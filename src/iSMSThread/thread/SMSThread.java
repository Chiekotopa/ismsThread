
package iSMSThread.thread;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import iSMSThread.jdbc.Requete;
import iSMSThread.jpa.entities.Sms;
import iSMSThread.utils.SMPPSender;

public class SMSThread extends Thread {

	private static Requete req = new Requete();

	private List<Sms> smsAEnvoyer = new ArrayList<Sms>();

	public SMSThread(String nom) {
		super(nom);
	}

	public List<Sms> getSmsAEnvoyer() {

		return smsAEnvoyer;
	}

	public void setSmsAEnvoyer(List<Sms> smsAEnvoyer) {

		this.smsAEnvoyer = smsAEnvoyer;
	}

	public void run() {

		System.out.println("\n THREAD D'ENVOI DES SMS" + "\n----------------------\n");

		while (true) {

			this.setSmsAEnvoyer(this.listeSMS());

			for (int i = 0; i < smsAEnvoyer.size(); i++) {

				Sms sms = smsAEnvoyer.get(i);

				try {

					this.envoyerSMS(sms);
					if (sms.isEtat())
						req.envoiReussi(sms, true);

					System.out.println("\n" + sms.toString() + "  Etat d'envoi = " + sms.isEtat());

				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			try {

				System.out.println("en sommeil");
				sleep(30000);
				System.out.println("en activite");

				this.setSmsAEnvoyer(null);

			} catch (NullPointerException | InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	public List<Sms> listeSMS() {

		List<Sms> smsAEnvoyer = new ArrayList<Sms>();

		ResultSet result = req.listeSMSAEnvoyer(false);

		try {

			while (result.next()) {

				int idSms = result.getInt(1);
				String senderID = result.getString("sender");
				String destinataire = result.getString("destinataire");
				String contenu = result.getString("contenu");

				Sms sms = new Sms();
				sms.setIdSMS(idSms);
				sms.setSender(senderID);
				sms.setDestinataire(destinataire);
				sms.setContenu(contenu);

				smsAEnvoyer.add(sms);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return smsAEnvoyer;
	}

	public void envoyerSMS(Sms sms) {

		String sender = sms.getSender();
		String destiniation = sms.getDestinataire();
		String message = sms.getContenu();
		Boolean envoye = false;

		String params[] = { sender, destiniation, message };

		envoye = SMPPSender.main(params);
		sms.setEtat(envoye);

	}

}
