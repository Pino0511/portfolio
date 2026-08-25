/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package contacts.enterprise.manager.service;

import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.OrderByComparator;

import contacts.enterprise.manager.model.SupportMessage;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for SupportMessage. This utility wraps
 * <code>contacts.enterprise.manager.service.impl.SupportMessageLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see SupportMessageLocalService
 * @generated
 */
public class SupportMessageLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>contacts.enterprise.manager.service.impl.SupportMessageLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static SupportMessage addSupportMessage(
			long groupId, long companyId, long userId, String userName,
			String senderName, String senderEmail, String subject, String body)
		throws PortalException {

		return getService().addSupportMessage(
			groupId, companyId, userId, userName, senderName, senderEmail,
			subject, body);
	}

	/**
	 * Adds the support message to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SupportMessageLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param supportMessage the support message
	 * @return the support message that was added
	 */
	public static SupportMessage addSupportMessage(
		SupportMessage supportMessage) {

		return getService().addSupportMessage(supportMessage);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel createPersistedModel(
			Serializable primaryKeyObj)
		throws PortalException {

		return getService().createPersistedModel(primaryKeyObj);
	}

	/**
	 * Creates a new support message with the primary key. Does not add the support message to the database.
	 *
	 * @param messageId the primary key for the new support message
	 * @return the new support message
	 */
	public static SupportMessage createSupportMessage(long messageId) {
		return getService().createSupportMessage(messageId);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel deletePersistedModel(
			PersistedModel persistedModel)
		throws PortalException {

		return getService().deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the support message with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SupportMessageLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param messageId the primary key of the support message
	 * @return the support message that was removed
	 * @throws PortalException if a support message with the primary key could not be found
	 */
	public static SupportMessage deleteSupportMessage(long messageId)
		throws PortalException {

		return getService().deleteSupportMessage(messageId);
	}

	/**
	 * Deletes the support message from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SupportMessageLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param supportMessage the support message
	 * @return the support message that was removed
	 */
	public static SupportMessage deleteSupportMessage(
		SupportMessage supportMessage) {

		return getService().deleteSupportMessage(supportMessage);
	}

	public static <T> T dslQuery(DSLQuery dslQuery) {
		return getService().dslQuery(dslQuery);
	}

	public static int dslQueryCount(DSLQuery dslQuery) {
		return getService().dslQueryCount(dslQuery);
	}

	public static DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	public static <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>contacts.enterprise.manager.model.impl.SupportMessageModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	public static <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>contacts.enterprise.manager.model.impl.SupportMessageModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	public static <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator) {

		return getService().dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static SupportMessage fetchSupportMessage(long messageId) {
		return getService().fetchSupportMessage(messageId);
	}

	/**
	 * Returns the support message matching the UUID and group.
	 *
	 * @param uuid the support message's UUID
	 * @param groupId the primary key of the group
	 * @return the matching support message, or <code>null</code> if a matching support message could not be found
	 */
	public static SupportMessage fetchSupportMessageByUuidAndGroupId(
		String uuid, long groupId) {

		return getService().fetchSupportMessageByUuidAndGroupId(uuid, groupId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery
		getExportActionableDynamicQuery(
			com.liferay.exportimport.kernel.lar.PortletDataContext
				portletDataContext) {

		return getService().getExportActionableDynamicQuery(portletDataContext);
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the support message with the primary key.
	 *
	 * @param messageId the primary key of the support message
	 * @return the support message
	 * @throws PortalException if a support message with the primary key could not be found
	 */
	public static SupportMessage getSupportMessage(long messageId)
		throws PortalException {

		return getService().getSupportMessage(messageId);
	}

	/**
	 * Returns the support message matching the UUID and group.
	 *
	 * @param uuid the support message's UUID
	 * @param groupId the primary key of the group
	 * @return the matching support message
	 * @throws PortalException if a matching support message could not be found
	 */
	public static SupportMessage getSupportMessageByUuidAndGroupId(
			String uuid, long groupId)
		throws PortalException {

		return getService().getSupportMessageByUuidAndGroupId(uuid, groupId);
	}

	/**
	 * Returns a range of all the support messages.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>contacts.enterprise.manager.model.impl.SupportMessageModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of support messages
	 * @param end the upper bound of the range of support messages (not inclusive)
	 * @return the range of support messages
	 */
	public static List<SupportMessage> getSupportMessages(int start, int end) {
		return getService().getSupportMessages(start, end);
	}

	/**
	 * Recupera tutti i messaggi inviati da uno specifico utente.
	 */
	public static List<SupportMessage> getSupportMessagesByUserId(long userId) {
		return getService().getSupportMessagesByUserId(userId);
	}

	/**
	 * Returns all the support messages matching the UUID and company.
	 *
	 * @param uuid the UUID of the support messages
	 * @param companyId the primary key of the company
	 * @return the matching support messages, or an empty list if no matches were found
	 */
	public static List<SupportMessage> getSupportMessagesByUuidAndCompanyId(
		String uuid, long companyId) {

		return getService().getSupportMessagesByUuidAndCompanyId(
			uuid, companyId);
	}

	/**
	 * Returns a range of support messages matching the UUID and company.
	 *
	 * @param uuid the UUID of the support messages
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of support messages
	 * @param end the upper bound of the range of support messages (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching support messages, or an empty list if no matches were found
	 */
	public static List<SupportMessage> getSupportMessagesByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		OrderByComparator<SupportMessage> orderByComparator) {

		return getService().getSupportMessagesByUuidAndCompanyId(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the number of support messages.
	 *
	 * @return the number of support messages
	 */
	public static int getSupportMessagesCount() {
		return getService().getSupportMessagesCount();
	}

	/**
	 * Updates the support message in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SupportMessageLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param supportMessage the support message
	 * @return the support message that was updated
	 */
	public static SupportMessage updateSupportMessage(
		SupportMessage supportMessage) {

		return getService().updateSupportMessage(supportMessage);
	}

	public static SupportMessageLocalService getService() {
		return _service;
	}

	private static volatile SupportMessageLocalService _service;

}