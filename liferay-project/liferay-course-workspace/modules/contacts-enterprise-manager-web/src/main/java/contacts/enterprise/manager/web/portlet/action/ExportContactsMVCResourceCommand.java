package contacts.enterprise.manager.web.portlet.action;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.servlet.HttpHeaders;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import contacts.enterprise.manager.model.Contact;
import contacts.enterprise.manager.service.ContactLocalService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import java.io.PrintWriter;
import java.util.List;

@Component(
    property = {
        "javax.portlet.name=ContactsEnterpriseManager",
        "mvc.command.name=/contact/export"
    },
    service = MVCResourceCommand.class
)
public class ExportContactsMVCResourceCommand implements MVCResourceCommand {

    @Reference
    private ContactLocalService contactLocalService;

    @Override
    public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) {
        try {
            ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
            long groupId = themeDisplay.getScopeGroupId();

            List<Contact> contacts = contactLocalService.searchGroupContacts(groupId, "", 0, 10000);

            resourceResponse.setContentType("text/csv");
            resourceResponse.addProperty(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"rubrica_contatti.csv\"");

            PrintWriter writer = resourceResponse.getWriter();
            // Intestazione colonne Excel
            writer.println("Nome,Cognome,Azienda,Ruolo,Email,Telefono,Stato");

            // Riempiamo le righe
            for (Contact c : contacts) {
                String stato = c.getActive() ? "Attivo" : "Inattivo";
                writer.println(c.getFirstName() + "," + c.getLastName() + "," + c.getCompany() + "," + 
                               c.getJobTitle() + "," + c.getEmail() + "," + c.getPhoneNumber() + "," + stato);
            }
            
            writer.flush();
            writer.close();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}