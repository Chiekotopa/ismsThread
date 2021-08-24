/**
 * 
 */

package iSMSThread.jdbc;

import java.sql.ResultSet;

import iSMSThread.jpa.entities.Sms;

/**
 * @author gorba
 *
 */
public class Requete {
	
	JDBC jdbc = new JDBC("system", "administrator", "jdbc:mysql://localhost:5785/ismsdb");
	
	// selection des tous les sms dans la BD
	public ResultSet listeSMS() {
		
		String sql = "SELECT * FROM Sms;";
		return jdbc.executeQuery(sql);
	}
	
	// recupere la valeur du numero du conge d'un reparateur
	public ResultSet listeSMSAEnvoyer(boolean etat) {
		
		String sql = "SELECT idSms, sender, destinataire, contenu FROM Sms WHERE etat = " + etat + ";";
		return jdbc.executeQuery(sql);
	}
	
	// modifie la valeur de l'etat d'envoi du SMS
	public boolean envoiReussi(Sms sms, boolean etat) {
		
		String sql = "UPDATE Sms SET etat = " + etat + " WHERE idSMS = " + sms.getIdSMS() + ";";
		if (jdbc.executeUpdate(sql) > 0) return true;
		else
			return false;
	}
	
}
