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

package contacts.enterprise.manager.service.persistence;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import contacts.enterprise.manager.model.SupportMessage;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the support message service. This utility wraps <code>contacts.enterprise.manager.service.persistence.impl.SupportMessagePersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SupportMessagePersistence
 * @generated
 */
public class SupportMessageUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(SupportMessage supportMessage) {
		getPersistence().clearCache(supportMessage);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#fetchByPrimaryKeys(Set)
	 */
	public static Map<Serializable, SupportMessage> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<SupportMessage> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<SupportMessage> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<SupportMessage> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<SupportMessage> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static SupportMessage update(SupportMessage supportMessage) {
		return getPersistence().update(supportMessage);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static SupportMessage update(
		SupportMessage supportMessage, ServiceContext serviceContext) {

		return getPersistence().update(supportMessage, serviceContext);
	}

	/**
	 * Returns all the support messages where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching support messages
	 */
	public static List<SupportMessage> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	 * Returns a range of all the support messages where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SupportMessageModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of support messages
	 * @param end the upper bound of the range of support messages (not inclusive)
	 * @return the range of matching support messages
	 */
	public static List<SupportMessage> findByUuid(
		String uuid, int start, int end) {

		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	 * Returns an ordered range of all the support messages where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SupportMessageModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of support messages
	 * @param end the upper bound of the range of support messages (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching support messages
	 */
	public static List<SupportMessage> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<SupportMessage> orderByComparator) {

		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the support messages where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SupportMessageModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of support messages
	 * @param end the upper bound of the range of support messages (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching support messages
	 */
	public static List<SupportMessage> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<SupportMessage> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUuid(
			uuid, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first support message in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support message
	 * @throws NoSuchSupportMessageException if a matching support message could not be found
	 */
	public static SupportMessage findByUuid_First(
			String uuid, OrderByComparator<SupportMessage> orderByComparator)
		throws contacts.enterprise.manager.exception.
			NoSuchSupportMessageException {

		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the first support message in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support message, or <code>null</code> if a matching support message could not be found
	 */
	public static SupportMessage fetchByUuid_First(
		String uuid, OrderByComparator<SupportMessage> orderByComparator) {

		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the last support message in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support message
	 * @throws NoSuchSupportMessageException if a matching support message could not be found
	 */
	public static SupportMessage findByUuid_Last(
			String uuid, OrderByComparator<SupportMessage> orderByComparator)
		throws contacts.enterprise.manager.exception.
			NoSuchSupportMessageException {

		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the last support message in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support message, or <code>null</code> if a matching support message could not be found
	 */
	public static SupportMessage fetchByUuid_Last(
		String uuid, OrderByComparator<SupportMessage> orderByComparator) {

		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the support messages before and after the current support message in the ordered set where uuid = &#63;.
	 *
	 * @param messageId the primary key of the current support message
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next support message
	 * @throws NoSuchSupportMessageException if a support message with the primary key could not be found
	 */
	public static SupportMessage[] findByUuid_PrevAndNext(
			long messageId, String uuid,
			OrderByComparator<SupportMessage> orderByComparator)
		throws contacts.enterprise.manager.exception.
			NoSuchSupportMessageException {

		return getPersistence().findByUuid_PrevAndNext(
			messageId, uuid, orderByComparator);
	}

	/**
	 * Removes all the support messages where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	 * Returns the number of support messages where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching support messages
	 */
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	 * Returns the support message where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchSupportMessageException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching support message
	 * @throws NoSuchSupportMessageException if a matching support message could not be found
	 */
	public static SupportMessage findByUUID_G(String uuid, long groupId)
		throws contacts.enterprise.manager.exception.
			NoSuchSupportMessageException {

		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the support message where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching support message, or <code>null</code> if a matching support message could not be found
	 */
	public static SupportMessage fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the support message where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching support message, or <code>null</code> if a matching support message could not be found
	 */
	public static SupportMessage fetchByUUID_G(
		String uuid, long groupId, boolean useFinderCache) {

		return getPersistence().fetchByUUID_G(uuid, groupId, useFinderCache);
	}

	/**
	 * Removes the support message where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the support message that was removed
	 */
	public static SupportMessage removeByUUID_G(String uuid, long groupId)
		throws contacts.enterprise.manager.exception.
			NoSuchSupportMessageException {

		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the number of support messages where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching support messages
	 */
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	 * Returns all the support messages where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching support messages
	 */
	public static List<SupportMessage> findByUuid_C(
		String uuid, long companyId) {

		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of all the support messages where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SupportMessageModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of support messages
	 * @param end the upper bound of the range of support messages (not inclusive)
	 * @return the range of matching support messages
	 */
	public static List<SupportMessage> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	 * Returns an ordered range of all the support messages where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SupportMessageModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of support messages
	 * @param end the upper bound of the range of support messages (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching support messages
	 */
	public static List<SupportMessage> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<SupportMessage> orderByComparator) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the support messages where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SupportMessageModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of support messages
	 * @param end the upper bound of the range of support messages (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching support messages
	 */
	public static List<SupportMessage> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<SupportMessage> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first support message in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support message
	 * @throws NoSuchSupportMessageException if a matching support message could not be found
	 */
	public static SupportMessage findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<SupportMessage> orderByComparator)
		throws contacts.enterprise.manager.exception.
			NoSuchSupportMessageException {

		return getPersistence().findByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the first support message in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support message, or <code>null</code> if a matching support message could not be found
	 */
	public static SupportMessage fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<SupportMessage> orderByComparator) {

		return getPersistence().fetchByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last support message in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support message
	 * @throws NoSuchSupportMessageException if a matching support message could not be found
	 */
	public static SupportMessage findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<SupportMessage> orderByComparator)
		throws contacts.enterprise.manager.exception.
			NoSuchSupportMessageException {

		return getPersistence().findByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last support message in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support message, or <code>null</code> if a matching support message could not be found
	 */
	public static SupportMessage fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<SupportMessage> orderByComparator) {

		return getPersistence().fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the support messages before and after the current support message in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param messageId the primary key of the current support message
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next support message
	 * @throws NoSuchSupportMessageException if a support message with the primary key could not be found
	 */
	public static SupportMessage[] findByUuid_C_PrevAndNext(
			long messageId, String uuid, long companyId,
			OrderByComparator<SupportMessage> orderByComparator)
		throws contacts.enterprise.manager.exception.
			NoSuchSupportMessageException {

		return getPersistence().findByUuid_C_PrevAndNext(
			messageId, uuid, companyId, orderByComparator);
	}

	/**
	 * Removes all the support messages where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	 * Returns the number of support messages where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching support messages
	 */
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	 * Returns all the support messages where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching support messages
	 */
	public static List<SupportMessage> findByUserId(long userId) {
		return getPersistence().findByUserId(userId);
	}

	/**
	 * Returns a range of all the support messages where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SupportMessageModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of support messages
	 * @param end the upper bound of the range of support messages (not inclusive)
	 * @return the range of matching support messages
	 */
	public static List<SupportMessage> findByUserId(
		long userId, int start, int end) {

		return getPersistence().findByUserId(userId, start, end);
	}

	/**
	 * Returns an ordered range of all the support messages where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SupportMessageModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of support messages
	 * @param end the upper bound of the range of support messages (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching support messages
	 */
	public static List<SupportMessage> findByUserId(
		long userId, int start, int end,
		OrderByComparator<SupportMessage> orderByComparator) {

		return getPersistence().findByUserId(
			userId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the support messages where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SupportMessageModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of support messages
	 * @param end the upper bound of the range of support messages (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching support messages
	 */
	public static List<SupportMessage> findByUserId(
		long userId, int start, int end,
		OrderByComparator<SupportMessage> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUserId(
			userId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first support message in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support message
	 * @throws NoSuchSupportMessageException if a matching support message could not be found
	 */
	public static SupportMessage findByUserId_First(
			long userId, OrderByComparator<SupportMessage> orderByComparator)
		throws contacts.enterprise.manager.exception.
			NoSuchSupportMessageException {

		return getPersistence().findByUserId_First(userId, orderByComparator);
	}

	/**
	 * Returns the first support message in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support message, or <code>null</code> if a matching support message could not be found
	 */
	public static SupportMessage fetchByUserId_First(
		long userId, OrderByComparator<SupportMessage> orderByComparator) {

		return getPersistence().fetchByUserId_First(userId, orderByComparator);
	}

	/**
	 * Returns the last support message in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support message
	 * @throws NoSuchSupportMessageException if a matching support message could not be found
	 */
	public static SupportMessage findByUserId_Last(
			long userId, OrderByComparator<SupportMessage> orderByComparator)
		throws contacts.enterprise.manager.exception.
			NoSuchSupportMessageException {

		return getPersistence().findByUserId_Last(userId, orderByComparator);
	}

	/**
	 * Returns the last support message in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support message, or <code>null</code> if a matching support message could not be found
	 */
	public static SupportMessage fetchByUserId_Last(
		long userId, OrderByComparator<SupportMessage> orderByComparator) {

		return getPersistence().fetchByUserId_Last(userId, orderByComparator);
	}

	/**
	 * Returns the support messages before and after the current support message in the ordered set where userId = &#63;.
	 *
	 * @param messageId the primary key of the current support message
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next support message
	 * @throws NoSuchSupportMessageException if a support message with the primary key could not be found
	 */
	public static SupportMessage[] findByUserId_PrevAndNext(
			long messageId, long userId,
			OrderByComparator<SupportMessage> orderByComparator)
		throws contacts.enterprise.manager.exception.
			NoSuchSupportMessageException {

		return getPersistence().findByUserId_PrevAndNext(
			messageId, userId, orderByComparator);
	}

	/**
	 * Removes all the support messages where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 */
	public static void removeByUserId(long userId) {
		getPersistence().removeByUserId(userId);
	}

	/**
	 * Returns the number of support messages where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching support messages
	 */
	public static int countByUserId(long userId) {
		return getPersistence().countByUserId(userId);
	}

	/**
	 * Returns all the support messages where isReplied = &#63;.
	 *
	 * @param isReplied the is replied
	 * @return the matching support messages
	 */
	public static List<SupportMessage> findByIsReplied(boolean isReplied) {
		return getPersistence().findByIsReplied(isReplied);
	}

	/**
	 * Returns a range of all the support messages where isReplied = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SupportMessageModelImpl</code>.
	 * </p>
	 *
	 * @param isReplied the is replied
	 * @param start the lower bound of the range of support messages
	 * @param end the upper bound of the range of support messages (not inclusive)
	 * @return the range of matching support messages
	 */
	public static List<SupportMessage> findByIsReplied(
		boolean isReplied, int start, int end) {

		return getPersistence().findByIsReplied(isReplied, start, end);
	}

	/**
	 * Returns an ordered range of all the support messages where isReplied = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SupportMessageModelImpl</code>.
	 * </p>
	 *
	 * @param isReplied the is replied
	 * @param start the lower bound of the range of support messages
	 * @param end the upper bound of the range of support messages (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching support messages
	 */
	public static List<SupportMessage> findByIsReplied(
		boolean isReplied, int start, int end,
		OrderByComparator<SupportMessage> orderByComparator) {

		return getPersistence().findByIsReplied(
			isReplied, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the support messages where isReplied = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SupportMessageModelImpl</code>.
	 * </p>
	 *
	 * @param isReplied the is replied
	 * @param start the lower bound of the range of support messages
	 * @param end the upper bound of the range of support messages (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching support messages
	 */
	public static List<SupportMessage> findByIsReplied(
		boolean isReplied, int start, int end,
		OrderByComparator<SupportMessage> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByIsReplied(
			isReplied, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first support message in the ordered set where isReplied = &#63;.
	 *
	 * @param isReplied the is replied
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support message
	 * @throws NoSuchSupportMessageException if a matching support message could not be found
	 */
	public static SupportMessage findByIsReplied_First(
			boolean isReplied,
			OrderByComparator<SupportMessage> orderByComparator)
		throws contacts.enterprise.manager.exception.
			NoSuchSupportMessageException {

		return getPersistence().findByIsReplied_First(
			isReplied, orderByComparator);
	}

	/**
	 * Returns the first support message in the ordered set where isReplied = &#63;.
	 *
	 * @param isReplied the is replied
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support message, or <code>null</code> if a matching support message could not be found
	 */
	public static SupportMessage fetchByIsReplied_First(
		boolean isReplied,
		OrderByComparator<SupportMessage> orderByComparator) {

		return getPersistence().fetchByIsReplied_First(
			isReplied, orderByComparator);
	}

	/**
	 * Returns the last support message in the ordered set where isReplied = &#63;.
	 *
	 * @param isReplied the is replied
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support message
	 * @throws NoSuchSupportMessageException if a matching support message could not be found
	 */
	public static SupportMessage findByIsReplied_Last(
			boolean isReplied,
			OrderByComparator<SupportMessage> orderByComparator)
		throws contacts.enterprise.manager.exception.
			NoSuchSupportMessageException {

		return getPersistence().findByIsReplied_Last(
			isReplied, orderByComparator);
	}

	/**
	 * Returns the last support message in the ordered set where isReplied = &#63;.
	 *
	 * @param isReplied the is replied
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support message, or <code>null</code> if a matching support message could not be found
	 */
	public static SupportMessage fetchByIsReplied_Last(
		boolean isReplied,
		OrderByComparator<SupportMessage> orderByComparator) {

		return getPersistence().fetchByIsReplied_Last(
			isReplied, orderByComparator);
	}

	/**
	 * Returns the support messages before and after the current support message in the ordered set where isReplied = &#63;.
	 *
	 * @param messageId the primary key of the current support message
	 * @param isReplied the is replied
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next support message
	 * @throws NoSuchSupportMessageException if a support message with the primary key could not be found
	 */
	public static SupportMessage[] findByIsReplied_PrevAndNext(
			long messageId, boolean isReplied,
			OrderByComparator<SupportMessage> orderByComparator)
		throws contacts.enterprise.manager.exception.
			NoSuchSupportMessageException {

		return getPersistence().findByIsReplied_PrevAndNext(
			messageId, isReplied, orderByComparator);
	}

	/**
	 * Removes all the support messages where isReplied = &#63; from the database.
	 *
	 * @param isReplied the is replied
	 */
	public static void removeByIsReplied(boolean isReplied) {
		getPersistence().removeByIsReplied(isReplied);
	}

	/**
	 * Returns the number of support messages where isReplied = &#63;.
	 *
	 * @param isReplied the is replied
	 * @return the number of matching support messages
	 */
	public static int countByIsReplied(boolean isReplied) {
		return getPersistence().countByIsReplied(isReplied);
	}

	/**
	 * Caches the support message in the entity cache if it is enabled.
	 *
	 * @param supportMessage the support message
	 */
	public static void cacheResult(SupportMessage supportMessage) {
		getPersistence().cacheResult(supportMessage);
	}

	/**
	 * Caches the support messages in the entity cache if it is enabled.
	 *
	 * @param supportMessages the support messages
	 */
	public static void cacheResult(List<SupportMessage> supportMessages) {
		getPersistence().cacheResult(supportMessages);
	}

	/**
	 * Creates a new support message with the primary key. Does not add the support message to the database.
	 *
	 * @param messageId the primary key for the new support message
	 * @return the new support message
	 */
	public static SupportMessage create(long messageId) {
		return getPersistence().create(messageId);
	}

	/**
	 * Removes the support message with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param messageId the primary key of the support message
	 * @return the support message that was removed
	 * @throws NoSuchSupportMessageException if a support message with the primary key could not be found
	 */
	public static SupportMessage remove(long messageId)
		throws contacts.enterprise.manager.exception.
			NoSuchSupportMessageException {

		return getPersistence().remove(messageId);
	}

	public static SupportMessage updateImpl(SupportMessage supportMessage) {
		return getPersistence().updateImpl(supportMessage);
	}

	/**
	 * Returns the support message with the primary key or throws a <code>NoSuchSupportMessageException</code> if it could not be found.
	 *
	 * @param messageId the primary key of the support message
	 * @return the support message
	 * @throws NoSuchSupportMessageException if a support message with the primary key could not be found
	 */
	public static SupportMessage findByPrimaryKey(long messageId)
		throws contacts.enterprise.manager.exception.
			NoSuchSupportMessageException {

		return getPersistence().findByPrimaryKey(messageId);
	}

	/**
	 * Returns the support message with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param messageId the primary key of the support message
	 * @return the support message, or <code>null</code> if a support message with the primary key could not be found
	 */
	public static SupportMessage fetchByPrimaryKey(long messageId) {
		return getPersistence().fetchByPrimaryKey(messageId);
	}

	/**
	 * Returns all the support messages.
	 *
	 * @return the support messages
	 */
	public static List<SupportMessage> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the support messages.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SupportMessageModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of support messages
	 * @param end the upper bound of the range of support messages (not inclusive)
	 * @return the range of support messages
	 */
	public static List<SupportMessage> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the support messages.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SupportMessageModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of support messages
	 * @param end the upper bound of the range of support messages (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of support messages
	 */
	public static List<SupportMessage> findAll(
		int start, int end,
		OrderByComparator<SupportMessage> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the support messages.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SupportMessageModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of support messages
	 * @param end the upper bound of the range of support messages (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of support messages
	 */
	public static List<SupportMessage> findAll(
		int start, int end, OrderByComparator<SupportMessage> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the support messages from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of support messages.
	 *
	 * @return the number of support messages
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static SupportMessagePersistence getPersistence() {
		return _persistence;
	}

	private static volatile SupportMessagePersistence _persistence;

}