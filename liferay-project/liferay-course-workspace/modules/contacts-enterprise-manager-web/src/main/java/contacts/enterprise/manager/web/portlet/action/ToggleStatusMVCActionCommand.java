package contacts.enterprise.manager.web.portlet.action;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;

import contacts.enterprise.manager.model.Contact;
import contacts.enterprise.manager.service.ContactLocalService;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
    immediate = true,
    property = {
        "javax.portlet.name=ContactsEnterpriseManager",
        "mvc.command.name=toggleStatus"
    },
    service = MVCActionCommand.class
)
public class ToggleStatusMVCActionCommand implements MVCActionCommand {

    private static final Log _log = LogFactoryUtil.getLog(ToggleStatusMVCActionCommand.class);

    @Override
    public boolean processAction(ActionRequest actionRequest, ActionResponse actionResponse) {
        try {
            long contactId = ParamUtil.getLong(actionRequest, "contactId");
            Contact contact = _contactLocalService.getContact(contactId);
            
            boolean newStatus = !contact.getActive();
            contact.setActive(newStatus);
            
            _contactLocalService.updateContactEntry(contact);
            
            SessionMessages.add(actionRequest, "contactUpdated");
            _log.info("Stato aggiornato. ID: " + contactId + " - Nuovo stato: " + (newStatus ? "Attivo" : "Disattivo"));
            return true;
        } catch (Exception e) {
            _log.error("Errore durante l'aggiornamento dello stato", e);
            SessionErrors.add(actionRequest, "errorSavingContact");
            return false;
        }
    }

    @Reference
    private ContactLocalService _contactLocalService;
}