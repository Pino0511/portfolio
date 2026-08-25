package contacts.enterprise.manager.web.portlet.action;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;

import contacts.enterprise.manager.service.ContactLocalService;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
    immediate = true,
    property = {
        "javax.portlet.name=ContactsEnterpriseManager",
        "mvc.command.name=deleteContact"
    },
    service = MVCActionCommand.class
)
public class DeleteContactMVCActionCommand implements MVCActionCommand {

    private static final Log _log = LogFactoryUtil.getLog(DeleteContactMVCActionCommand.class);

    @Override
    public boolean processAction(ActionRequest actionRequest, ActionResponse actionResponse) {
        try {
            long contactId = ParamUtil.getLong(actionRequest, "contactId");
            _contactLocalService.deleteContact(contactId);
            
            SessionMessages.add(actionRequest, "contactDeleted");
            _log.info("Contatto eliminato con successo. ID: " + contactId);
            return true;
        } catch (Exception e) {
            _log.error("Errore durante l'eliminazione del contatto", e);
            SessionErrors.add(actionRequest, "errorDeletingContact");
            return false;
        }
    }

    @Reference
    private ContactLocalService _contactLocalService;
}