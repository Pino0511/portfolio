package contacts.enterprise.manager.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import contacts.enterprise.manager.model.SupportMessage;
import contacts.enterprise.manager.service.base.SupportMessageLocalServiceBaseImpl;
import org.osgi.service.component.annotations.Component;

import java.util.Date;

@Component(
	property = "model.class.name=contacts.enterprise.manager.model.SupportMessage",
	service = AopService.class
)
public class SupportMessageLocalServiceImpl extends SupportMessageLocalServiceBaseImpl {

	public SupportMessage addSupportMessage(
		long groupId, long companyId, long userId, String userName,
		String senderName, String senderEmail, String subject, String body) throws PortalException {

		long messageId = counterLocalService.increment(SupportMessage.class.getName());

		SupportMessage message = supportMessagePersistence.create(messageId);

		message.setGroupId(groupId);
		message.setCompanyId(companyId);
		message.setUserId(userId);
		message.setUserName(userName);
		message.setCreateDate(new Date());
		message.setModifiedDate(new Date());

		message.setSenderName(senderName);
		message.setSenderEmail(senderEmail);
		message.setSubject(subject);
		message.setBody(body);

		message.setIsReplied(false);
		message.setReplyText("");

		return supportMessagePersistence.update(message);
	}
	
	/**
     * Recupera tutti i messaggi inviati da uno specifico utente.
     */
    public java.util.List<contacts.enterprise.manager.model.SupportMessage> getSupportMessagesByUserId(long userId) {
        try {
            return supportMessagePersistence.findByUserId(userId);
        } catch (Exception e) {
            e.printStackTrace();
            return new java.util.ArrayList<>();
        }
    }
}