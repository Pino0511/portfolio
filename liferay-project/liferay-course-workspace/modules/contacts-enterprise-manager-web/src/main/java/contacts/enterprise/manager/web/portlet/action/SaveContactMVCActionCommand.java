package contacts.enterprise.manager.web.portlet.action;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

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
        "mvc.command.name=saveContact"
    },
    service = MVCActionCommand.class
)
public class SaveContactMVCActionCommand implements MVCActionCommand {

    private static final Log _log = LogFactoryUtil.getLog(SaveContactMVCActionCommand.class);

    @Override
    public boolean processAction(ActionRequest actionRequest, ActionResponse actionResponse) {
        try {
            long contactId = ParamUtil.getLong(actionRequest, "contactId");
            String firstName = ParamUtil.getString(actionRequest, "firstName");
            String lastName = ParamUtil.getString(actionRequest, "lastName");
            String email = ParamUtil.getString(actionRequest, "email");
            String phoneNumber = ParamUtil.getString(actionRequest, "phoneNumber");
            String company = ParamUtil.getString(actionRequest, "company");
            String jobTitle = ParamUtil.getString(actionRequest, "jobTitle");
            String notes = ParamUtil.getString(actionRequest, "notes");
            boolean active = ParamUtil.getBoolean(actionRequest, "active");

            ServiceContext serviceContext = ServiceContextFactory.getInstance(actionRequest);
            ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
            
            if (contactId > 0) {
                Contact contact = _contactLocalService.getContact(contactId);
                contact.setFirstName(firstName);
                contact.setLastName(lastName);
                contact.setEmail(email);
                contact.setPhoneNumber(phoneNumber);
                contact.setCompany(company);
                contact.setJobTitle(jobTitle);
                contact.setNotes(notes);
                contact.setActive(active);
                _contactLocalService.updateContactEntry(contact);
                
                SessionMessages.add(actionRequest, "contactUpdated");
                _log.info("Contatto aggiornato con successo: " + email);
            } else {
                long userId = themeDisplay.getUserId();
                long groupId = themeDisplay.getScopeGroupId();
                _contactLocalService.addContact(
                        userId, groupId, firstName, lastName, 
                        email, phoneNumber, company, jobTitle, 
                        notes, active, serviceContext);
                        
                SessionMessages.add(actionRequest, "contactAdded");
                _log.info("Nuovo contatto creato con successo: " + email);
            }
            return true;
        } catch (Exception e) {
            _log.error("Errore durante il salvataggio del contatto", e);
            SessionErrors.add(actionRequest, "errorSavingContact");
            return false;
        }
    }

    @Reference
    private ContactLocalService _contactLocalService;
}