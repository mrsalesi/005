/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cms.tools;

import HMIS.Commettes;
import HMIS.ManagementGauges;
import HMIS.Messenger;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mohammad
 */
public class TaskHMIS extends TimerTask {

    public static int i = 0;

    @Override
    public void run() {
        try {
            System.out.println("Timer ran " + ++i);
//            ManagementGauges.taskDocumentaryReminder();
//            Commettes.taskCommettesReminder();
//            Commettes.taskApprovedReminder();
//            Commettes.taskStepsReminder();
//            Commettes.taskNextSessionsReminder();
//            Messenger.taskMessengerInQueue();

        } catch (Exception ex) {
            Logger.getLogger(TaskHMIS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
