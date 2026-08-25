package contacts.enterprise.manager.web.portlet.action;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.util.ParamUtil;

import contacts.enterprise.manager.model.Contact;
import contacts.enterprise.manager.service.ContactLocalService;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
    immediate = true,
    property = {
        "javax.portlet.name=ContactsEnterpriseManager",
        "mvc.command.name=/contact/edit"
    },
    service = MVCRenderCommand.class
)
public class EditContactMVCRenderCommand implements MVCRenderCommand {

    @Override
    public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
        long contactId = ParamUtil.getLong(renderRequest, "contactId");

        if (contactId > 0) {
            try {
                Contact contact = _contactLocalService.getContact(contactId);
                renderRequest.setAttribute("editContact", contact);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return "/edit_contact.jsp";
    }

    @Reference
    private ContactLocalService _contactLocalService;
}