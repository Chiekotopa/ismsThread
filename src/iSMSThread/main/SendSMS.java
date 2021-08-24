
package iSMSThread.main;

import iSMSThread.thread.SMSThread;

public class SendSMS {

	public static void main(String[] args) {

		SMSThread smsThread = new SMSThread("Test d'envoi de SMS");
		smsThread.start();

	}

}
