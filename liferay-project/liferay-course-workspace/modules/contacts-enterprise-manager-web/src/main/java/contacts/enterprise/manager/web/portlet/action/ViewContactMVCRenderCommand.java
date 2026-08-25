package contacts.enterprise.manager.web.portlet.action;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import contacts.enterprise.manager.model.Contact;
import contacts.enterprise.manager.service.ContactLocalService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

@Component(
    immediate = true,
    property = {
        "javax.portlet.name=ContactsEnterpriseManager", 
        "mvc.command.name=/contact/view"
    },
    service = MVCRenderCommand.class
)
public class ViewContactMVCRenderCommand implements MVCRenderCommand {

    @Reference
    private ContactLocalService contactLocalService;

    @Override
    public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
        try {
            // 1. Prende l'ID dall'URL
            long contactId = ParamUtil.getLong(renderRequest, "contactId");
            System.out.println("=== DEBUG: Sto cercando il contatto con ID: " + contactId + " ===");

            // 2. Cerca il contatto nel database
            Contact currentContact = contactLocalService.getContact(contactId);
            System.out.println("=== DEBUG: Contatto trovato! Nome: " + currentContact.getFirstName() + " ===");

            // 3. Passa il contatto alla JSP
            renderRequest.setAttribute("currentContact", currentContact);

        } catch (Exception e) {
            System.out.println("=== DEBUG: Errore durante la ricerca del contatto ===");
            e.printStackTrace();
        }
        
        return "/view_contact.jsp";
    }
}