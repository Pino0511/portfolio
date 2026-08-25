package contacts.enterprise.manager.web.portlet;

import contacts.enterprise.manager.model.Contact;
import contacts.enterprise.manager.service.ContactLocalService;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;
import java.util.List;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
    immediate = true,
    property = {
        "com.liferay.portlet.display-category=category.sample",
        "com.liferay.portlet.header-portlet-css=/css/main.css",
        "com.liferay.portlet.instanceable=true",
        "javax.portlet.display-name=Rubrica Contatti",
        "javax.portlet.init-param.template-path=/",
        "javax.portlet.init-param.view-template=/view.jsp",
        "javax.portlet.name=ContactsEnterpriseManager",
        "javax.portlet.resource-bundle=content.Language",
        "javax.portlet.security-role-ref=power-user,user"
    },
    service = Portlet.class
)
public class ContactsEnterpriseManagerWebPortlet extends MVCPortlet {

    @Override
    public void render(RenderRequest renderRequest, RenderResponse renderResponse)
            throws IOException, PortletException {
        
        ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
        long groupId = themeDisplay.getScopeGroupId(); 
        
        String keywords = ParamUtil.getString(renderRequest, "keywords");
        
        int cur = ParamUtil.getInteger(renderRequest, "cur", 1);
        int delta = ParamUtil.getInteger(renderRequest, "delta", 20);
        int start = (cur - 1) * delta;
        int end = start + delta;

        int contactsCount = _contactLocalService.searchGroupContactsCount(groupId, keywords);
        List<Contact> contactsList = _contactLocalService.searchGroupContacts(groupId, keywords, start, end);
        
        renderRequest.setAttribute("contactsList", contactsList);
        renderRequest.setAttribute("contactsCount", contactsCount);
        renderRequest.setAttribute("keywords", keywords);

        super.render(renderRequest, renderResponse);
    }

    @Reference
    private ContactLocalService _contactLocalService;
}