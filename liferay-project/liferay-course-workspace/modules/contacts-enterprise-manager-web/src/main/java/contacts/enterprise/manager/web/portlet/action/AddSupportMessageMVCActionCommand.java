package contacts.enterprise.manager.web.portlet.action;

import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import contacts.enterprise.manager.service.SupportMessageLocalService;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
    immediate = true,
    property = {
        "javax.portlet.name=SupportUserPortlet", // Collega l'azione alla portlet giusta
        "mvc.command.name=/support/add_message"  // Collega l'azione all'URL del form JSP
    },
    service = MVCActionCommand.class
)
public class AddSupportMessageMVCActionCommand extends BaseMVCActionCommand {

    @Reference
    private SupportMessageLocalService supportMessageLocalService;

    @Override
    protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {

        // 1. Recupero informazioni di sistema (ID utente, ecc.)
        ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

        long groupId = themeDisplay.getScopeGroupId();
        long companyId = themeDisplay.getCompanyId();
        long userId = themeDisplay.getUserId(); // Se è un guest, Liferay imposta questo a 0 in automatico
        String userName = themeDisplay.isSignedIn() ? themeDisplay.getUser().getFullName() : "Guest";

        // 2. Recupero i dati inseriti nel form (o passati di nascosto)
        String senderName = ParamUtil.getString(actionRequest, "senderName");
        String senderEmail = ParamUtil.getString(actionRequest, "senderEmail");
        String subject = ParamUtil.getString(actionRequest, "subject");
        String body = ParamUtil.getString(actionRequest, "body");

        try {
            // 3. Chiamo il metodo del Service Builder per salvare tutto nel database
            supportMessageLocalService.addSupportMessage(
                groupId, companyId, userId, userName,
                senderName, senderEmail, subject, body
            );

            // --- INIZIO INVIO EMAIL DI CONFERMA ALL'UTENTE ---
            try {
                com.liferay.mail.kernel.model.MailMessage mailMessage = new com.liferay.mail.kernel.model.MailMessage();
                mailMessage.setFrom(new javax.mail.internet.InternetAddress("support@tuaazienda.com", "Supporto Aziendale"));
                mailMessage.setTo(new javax.mail.internet.InternetAddress(senderEmail));
                mailMessage.setSubject("Presa in carico richiesta: " + subject);
                mailMessage.setBody("Ciao " + senderName + ",\n\n" +
                                   "Ti confermiamo di aver ricevuto il tuo messaggio:\n" +
                                   "\"" + body + "\"\n\n" +
                                   "Il nostro team ti risponderà il prima possibile.\n\n" +
                                   "Saluti,\nIl Team di Supporto");
                
                com.liferay.mail.kernel.service.MailServiceUtil.sendEmail(mailMessage);
                System.out.println("=== DEBUG: Email di conferma inviata a " + senderEmail + " ===");
            } catch (Exception mailEx) {
                System.out.println("=== DEBUG: Errore email di conferma ===");
                mailEx.printStackTrace();
            }
            // --- FINE INVIO EMAIL ---

            // 4. Mando un segnale di successo alla pagina
            SessionMessages.add(actionRequest, "messageAdded");
            System.out.println("=== DEBUG: Messaggio salvato nel DB! Mittente: " + senderEmail + " ===");

        } catch (Exception e) {
            // 5. Se c'è un problema, avviso la pagina dell'errore
            SessionErrors.add(actionRequest, "errorAddingMessage");
            System.out.println("=== DEBUG: Errore durante il salvataggio del messaggio ===");
            e.printStackTrace();
        }
    }
}