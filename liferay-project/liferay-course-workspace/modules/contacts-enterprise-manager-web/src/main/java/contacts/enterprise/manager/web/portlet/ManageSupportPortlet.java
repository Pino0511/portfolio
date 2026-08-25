package contacts.enterprise.manager.web.portlet;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import javax.portlet.Portlet;
import org.osgi.service.component.annotations.Component;

@Component(
    immediate = true,
    property = {
        "com.liferay.portlet.display-category=category.sample",
        "com.liferay.portlet.header-portlet-css=/css/main.css",
        "com.liferay.portlet.instanceable=false", // Meglio false per i pannelli di admin
        "javax.portlet.display-name=Gestione Supporto Aziendale",
        "javax.portlet.init-param.template-path=/",
        "javax.portlet.init-param.view-template=/manage_support/view.jsp",
        "javax.portlet.name=ManageSupportPortlet",
        "javax.portlet.resource-bundle=content.Language",
        "javax.portlet.security-role-ref=power-user,user" 
    },
    service = Portlet.class
)
public class ManageSupportPortlet extends MVCPortlet {
}