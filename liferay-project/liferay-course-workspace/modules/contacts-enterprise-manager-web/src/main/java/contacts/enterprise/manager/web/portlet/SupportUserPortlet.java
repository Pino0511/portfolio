package contacts.enterprise.manager.web.portlet;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import javax.portlet.Portlet;
import org.osgi.service.component.annotations.Component;

@Component(
    immediate = true,
    property = {
        "com.liferay.portlet.display-category=category.sample",
        "com.liferay.portlet.header-portlet-css=/css/main.css",
        "com.liferay.portlet.instanceable=true",
        "javax.portlet.display-name=Supporto Utenti",
        "javax.portlet.init-param.template-path=/",
        "javax.portlet.init-param.view-template=/support_user/view.jsp",
        "javax.portlet.name=SupportUserPortlet",
        "javax.portlet.resource-bundle=content.Language",
        "javax.portlet.security-role-ref=power-user,user,guest"
    },
    service = Portlet.class
)
public class SupportUserPortlet extends MVCPortlet {
}