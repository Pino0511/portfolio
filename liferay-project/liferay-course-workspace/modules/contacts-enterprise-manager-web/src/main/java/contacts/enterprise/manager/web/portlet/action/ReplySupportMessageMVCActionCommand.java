package contacts.enterprise.manager.web.portlet.action;

import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;
import contacts.enterprise.manager.model.SupportMessage;
import contacts.enterprise.manager.service.SupportMessageLocalService;

import com.liferay.mail.kernel.model.MailMessage;
import com.liferay.mail.kernel.service.MailServiceUtil;
import javax.mail.internet.InternetAddress;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
    immediate = true,
    property = {
        "javax.portlet.name=ManageSupportPortlet", 
        "mvc.command.name=/support/reply_message"
    },
    service = MVCActionCommand.class
)
public class ReplySupportMessageMVCActionCommand extends BaseMVCActionCommand {

    @Reference
    private SupportMessageLocalService supportMessageLocalService;

    @Override
    protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {

        // 1. Recupero i dati dal modulo (l'ID nascosto e il testo della risposta)
        long messageId = ParamUtil.getLong(actionRequest, "messageId");
        String replyText = ParamUtil.getString(actionRequest, "replyText");

        try {
            // 2. Chiedo a Liferay di pescarmi quel preciso messaggio dal Database
            SupportMessage message = supportMessageLocalService.getSupportMessage(messageId);

            // 3. Inserisco la risposta e cambio lo stato (usando il "setIsReplied" corretto di prima!)
            message.setReplyText(replyText);
            message.setIsReplied(true);

            // 4. Salvo l'aggiornamento nel Database
            supportMessageLocalService.updateSupportMessage(message);
            
            // --- INIZIO INVIO EMAIL ---
            try {
                InternetAddress fromAddress = new InternetAddress("support@tuaazienda.com", "Supporto Aziendale");
                InternetAddress toAddress = new InternetAddress(message.getSenderEmail());
                
                String emailSubject = "Risposta al tuo ticket: " + message.getSubject();
                String emailBody = "Ciao " + message.getSenderName() + ",\n\n" +
                                   "Ecco la risposta alla tua richiesta:\n\n" +
                                   replyText + "\n\n" +
                                   "Saluti,\nIl Team di Supporto";

                MailMessage mailMessage = new MailMessage(fromAddress, toAddress, emailSubject, emailBody, false);
                MailServiceUtil.sendEmail(mailMessage);
                
                System.out.println("=== DEBUG: Email inviata con successo a " + message.getSenderEmail() + " ===");
            } catch (Exception mailEx) {
                System.out.println("=== DEBUG: Errore nell'invio dell'email (Server SMTP forse non configurato) ===");
                mailEx.printStackTrace();
            }
            // --- FINE INVIO EMAIL ---

            // 5. Mando un segnale di successo alla pagina
            SessionMessages.add(actionRequest, "replySaved");
            System.out.println("=== DEBUG: Risposta salvata per il messaggio " + messageId + " ===");

            // 6. Dico a Liferay di tornare alla tabella principale
            actionResponse.getRenderParameters().setValue("mvcPath", "/manage_support/view.jsp");

        } catch (Exception e) {
            SessionErrors.add(actionRequest, "errorSavingReply");
            System.out.println("=== DEBUG: Errore nel salvataggio della risposta ===");
            e.printStackTrace();
        }
    }
}