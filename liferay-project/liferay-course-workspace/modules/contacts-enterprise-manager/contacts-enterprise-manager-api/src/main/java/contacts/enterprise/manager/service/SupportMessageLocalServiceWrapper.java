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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link SupportMessageLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see SupportMessageLocalService
 * @generated
 */
public class SupportMessageLocalServiceWrapper
	implements ServiceWrapper<SupportMessageLocalService>,
			   SupportMessageLocalService {

	public SupportMessageLocalServiceWrapper() {
		this(null);
	}

	public SupportMessageLocalServiceWrapper(
		SupportMessageLocalService supportMessageLocalService) {

		_supportMessageLocalService = supportMessageLocalService;
	}

	@Override
	public contacts.enterprise.manager.model.SupportMessage addSupportMessage(
			long groupId, long companyId, long userId, String userName,
			String senderName, String senderEmail, String subject, String body)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _supportMessageLocalService.addSupportMessage(
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
	@Override
	public contacts.enterprise.manager.model.SupportMessage addSupportMessage(
		contacts.enterprise.manager.model.SupportMessage supportMessage) {

		return _supportMessageLocalService.addSupportMessage(supportMessage);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _supportMessageLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Creates a new support message with the primary key. Does not add the support message to the database.
	 *
	 * @param messageId the primary key for the new support message
	 * @return the new support message
	 */
	@Override
	public contacts.enterprise.manager.model.SupportMessage
		createSupportMessage(long messageId) {

		return _supportMessageLocalService.createSupportMessage(messageId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _supportMessageLocalService.deletePersistedModel(persistedModel);
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
	@Override
	public contacts.enterprise.manager.model.SupportMessage
			deleteSupportMessage(long messageId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _supportMessageLocalService.deleteSupportMessage(messageId);
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
	@Override
	public contacts.enterprise.manager.model.SupportMessage
		deleteSupportMessage(
			contacts.enterprise.manager.model.SupportMessage supportMessage) {

		return _supportMessageLocalService.deleteSupportMessage(supportMessage);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _supportMessageLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _supportMessageLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _supportMessageLocalService.dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _supportMessageLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _supportMessageLocalService.dynamicQuery(
			dynamicQuery, start, end);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _supportMessageLocalService.dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _supportMessageLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return _supportMessageLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public contacts.enterprise.manager.model.SupportMessage fetchSupportMessage(
		long messageId) {

		return _supportMessageLocalService.fetchSupportMessage(messageId);
	}

	/**
	 * Returns the support message matching the UUID and group.
	 *
	 * @param uuid the support message's UUID
	 * @param groupId the primary key of the group
	 * @return the matching support message, or <code>null</code> if a matching support message could not be found
	 */
	@Override
	public contacts.enterprise.manager.model.SupportMessage
		fetchSupportMessageByUuidAndGroupId(String uuid, long groupId) {

		return _supportMessageLocalService.fetchSupportMessageByUuidAndGroupId(
			uuid, groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _supportMessageLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery
		getExportActionableDynamicQuery(
			com.liferay.exportimport.kernel.lar.PortletDataContext
				portletDataContext) {

		return _supportMessageLocalService.getExportActionableDynamicQuery(
			portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _supportMessageLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _supportMessageLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _supportMessageLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the support message with the primary key.
	 *
	 * @param messageId the primary key of the support message
	 * @return the support message
	 * @throws PortalException if a support message with the primary key could not be found
	 */
	@Override
	public contacts.enterprise.manager.model.SupportMessage getSupportMessage(
			long messageId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _supportMessageLocalService.getSupportMessage(messageId);
	}

	/**
	 * Returns the support message matching the UUID and group.
	 *
	 * @param uuid the support message's UUID
	 * @param groupId the primary key of the group
	 * @return the matching support message
	 * @throws PortalException if a matching support message could not be found
	 */
	@Override
	public contacts.enterprise.manager.model.SupportMessage
			getSupportMessageByUuidAndGroupId(String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _supportMessageLocalService.getSupportMessageByUuidAndGroupId(
			uuid, groupId);
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
	@Override
	public java.util.List<contacts.enterprise.manager.model.SupportMessage>
		getSupportMessages(int start, int end) {

		return _supportMessageLocalService.getSupportMessages(start, end);
	}

	/**
	 * Recupera tutti i messaggi inviati da uno specifico utente.
	 */
	@Override
	public java.util.List<contacts.enterprise.manager.model.SupportMessage>
		getSupportMessagesByUserId(long userId) {

		return _supportMessageLocalService.getSupportMessagesByUserId(userId);
	}

	/**
	 * Returns all the support messages matching the UUID and company.
	 *
	 * @param uuid the UUID of the support messages
	 * @param companyId the primary key of the company
	 * @return the matching support messages, or an empty list if no matches were found
	 */
	@Override
	public java.util.List<contacts.enterprise.manager.model.SupportMessage>
		getSupportMessagesByUuidAndCompanyId(String uuid, long companyId) {

		return _supportMessageLocalService.getSupportMessagesByUuidAndCompanyId(
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
	@Override
	public java.util.List<contacts.enterprise.manager.model.SupportMessage>
		getSupportMessagesByUuidAndCompanyId(
			String uuid, long companyId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<contacts.enterprise.manager.model.SupportMessage>
					orderByComparator) {

		return _supportMessageLocalService.getSupportMessagesByUuidAndCompanyId(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the number of support messages.
	 *
	 * @return the number of support messages
	 */
	@Override
	public int getSupportMessagesCount() {
		return _supportMessageLocalService.getSupportMessagesCount();
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
	@Override
	public contacts.enterprise.manager.model.SupportMessage
		updateSupportMessage(
			contacts.enterprise.manager.model.SupportMessage supportMessage) {

		return _supportMessageLocalService.updateSupportMessage(supportMessage);
	}

	@Override
	public SupportMessageLocalService getWrappedService() {
		return _supportMessageLocalService;
	}

	@Override
	public void setWrappedService(
		SupportMessageLocalService supportMessageLocalService) {

		_supportMessageLocalService = supportMessageLocalService;
	}

	private SupportMessageLocalService _supportMessageLocalService;

}