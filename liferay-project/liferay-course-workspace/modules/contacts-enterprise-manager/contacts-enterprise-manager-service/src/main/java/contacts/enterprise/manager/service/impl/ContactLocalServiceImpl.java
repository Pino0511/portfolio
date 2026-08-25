package contacts.enterprise.manager.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.Junction;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import java.util.List;
import java.util.Date;

import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.model.User;
import contacts.enterprise.manager.model.Contact;
import contacts.enterprise.manager.service.base.ContactLocalServiceBaseImpl;

import org.osgi.service.component.annotations.Component;

@Component(
    property = "model.class.name=contacts.enterprise.manager.model.Contact",
    service = AopService.class
)
public class ContactLocalServiceImpl extends ContactLocalServiceBaseImpl {

    // Metodo per le validazioni
    private void validate(String firstName, String lastName, String email, String phoneNumber, long groupId, long contactId) throws PortalException {
        if (Validator.isNull(firstName)) {
            throw new PortalException("error-first-name-required");
        }
        if (Validator.isNull(lastName)) {
            throw new PortalException("error-last-name-required");
        }
        if (!Validator.isEmailAddress(email)) {
            throw new PortalException("error-email-invalid");
        }
        
        if (Validator.isNotNull(phoneNumber) && !phoneNumber.matches("^[0-9+\\s]+$")) {
            throw new PortalException("error-phone-invalid");
        }

        Contact existingContact = contactPersistence.fetchByG_E(groupId, email);
        if (existingContact != null && existingContact.getContactId() != contactId) {
            throw new PortalException("error-email-duplicate");
        }
    }

    public Contact addContact(
            long userId, long groupId, String firstName, String lastName,
            String email, String phoneNumber, String company,
            String jobTitle, String notes, boolean active,
            ServiceContext serviceContext) throws PortalException {

        validate(firstName, lastName, email, phoneNumber, groupId, 0);

        long contactId = counterLocalService.increment(Contact.class.getName());
        Contact contact = contactPersistence.create(contactId);

        contact.setGroupId(groupId);
        contact.setCompanyId(serviceContext.getCompanyId());
        contact.setUserId(userId);
        
        Date now = new Date();
        contact.setCreateDate(now);
        contact.setModifiedDate(now);

        contact.setFirstName(firstName);
        contact.setLastName(lastName);
        contact.setEmail(email);
        contact.setPhoneNumber(phoneNumber);
        contact.setCompany(company);
        contact.setJobTitle(jobTitle);
        contact.setNotes(notes);
        contact.setActive(active);
        
        User userCreator = UserLocalServiceUtil.getUser(userId);
        contact.setUserName(userCreator.getFullName());
        return contactPersistence.update(contact);
    }
    
    public Contact updateContactEntry(Contact contact) throws PortalException {
        validate(contact.getFirstName(), contact.getLastName(), contact.getEmail(), contact.getPhoneNumber(), contact.getGroupId(), contact.getContactId());
        
        contact.setModifiedDate(new Date());
        return super.updateContact(contact); 
    }
    
    // Ricerca Base
    public List<Contact> searchGroupContacts(long groupId, String keywords, int start, int end) {
        DynamicQuery dq = getSearchDynamicQuery(groupId, keywords);
        return contactPersistence.findWithDynamicQuery(dq, start, end);
    }
    
    public int searchGroupContactsCount(long groupId, String keywords) {
        DynamicQuery dq = getSearchDynamicQuery(groupId, keywords);
        return (int) contactPersistence.countWithDynamicQuery(dq);
    }

    // Ricerca Avanzata con Filtri Multipli e Ordinamento
    public List<Contact> searchAdvanced(
            long groupId, String firstName, String lastName, String company, 
            String jobTitle, int start, int end, OrderByComparator<Contact> orderByComparator) {

        DynamicQuery dq = getAdvancedSearchQuery(groupId, firstName, lastName, company, jobTitle);
        return contactPersistence.findWithDynamicQuery(dq, start, end, orderByComparator);
    }

    public int searchAdvancedCount(long groupId, String firstName, String lastName, String company, String jobTitle) {
        DynamicQuery dq = getAdvancedSearchQuery(groupId, firstName, lastName, company, jobTitle);
        return (int) contactPersistence.countWithDynamicQuery(dq);
    }

    private DynamicQuery getAdvancedSearchQuery(long groupId, String firstName, String lastName, String company, String jobTitle) {
        DynamicQuery dq = dynamicQuery();
        dq.add(PropertyFactoryUtil.forName("groupId").eq(groupId));

        if (Validator.isNotNull(firstName)) {
            dq.add(RestrictionsFactoryUtil.ilike("firstName", "%" + firstName + "%"));
        }
        if (Validator.isNotNull(lastName)) {
            dq.add(RestrictionsFactoryUtil.ilike("lastName", "%" + lastName + "%"));
        }
        if (Validator.isNotNull(company)) {
            dq.add(RestrictionsFactoryUtil.ilike("company", "%" + company + "%"));
        }
        if (Validator.isNotNull(jobTitle)) {
            dq.add(RestrictionsFactoryUtil.ilike("jobTitle", "%" + jobTitle + "%"));
        }

        return dq;
    }
    
    private DynamicQuery getSearchDynamicQuery(long groupId, String keywords) {
        DynamicQuery dq = dynamicQuery();
        dq.add(PropertyFactoryUtil.forName("groupId").eq(groupId));
        if (keywords != null && !keywords.trim().isEmpty()) {
            Junction junction = RestrictionsFactoryUtil.disjunction();
            String likeKeyword = "%" + keywords.toLowerCase() + "%";
            junction.add(RestrictionsFactoryUtil.ilike("firstName", likeKeyword));
            junction.add(RestrictionsFactoryUtil.ilike("lastName", likeKeyword));
            junction.add(RestrictionsFactoryUtil.ilike("email", likeKeyword));
            junction.add(RestrictionsFactoryUtil.ilike("company", likeKeyword));
            dq.add(junction);
        }
        return dq;
    }
}