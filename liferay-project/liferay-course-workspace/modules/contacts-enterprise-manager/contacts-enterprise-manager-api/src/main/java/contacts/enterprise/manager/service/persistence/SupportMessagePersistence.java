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

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import contacts.enterprise.manager.exception.NoSuchSupportMessageException;
import contacts.enterprise.manager.model.SupportMessage;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the support message service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SupportMessageUtil
 * @generated
 */
@ProviderType
public interface SupportMessagePersistence
	extends BasePersistence<SupportMessage> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SupportMessageUtil} to access the support message persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the support messages where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching support messages
	 */
	public java.util.List<SupportMessage> findByUuid(String uuid);

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
	public java.util.List<SupportMessage> findByUuid(
		String uuid, int start, int end);

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
	public java.util.List<SupportMessage> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SupportMessage>
			orderByComparator);

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
	public java.util.List<SupportMessage> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SupportMessage>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first support message in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support message
	 * @throws NoSuchSupportMessageException if a matching support message could not be found
	 */
	public SupportMessage findByUuid_First(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<SupportMessage>
				orderByComparator)
		throws NoSuchSupportMessageException;

	/**
	 * Returns the first support message in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support message, or <code>null</code> if a matching support message could not be found
	 */
	public SupportMessage fetchByUuid_First(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<SupportMessage>
			orderByComparator);

	/**
	 * Returns the last support message in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support message
	 * @throws NoSuchSupportMessageException if a matching support message could not be found
	 */
	public SupportMessage findByUuid_Last(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<SupportMessage>
				orderByComparator)
		throws NoSuchSupportMessageException;

	/**
	 * Returns the last support message in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support message, or <code>null</code> if a matching support message could not be found
	 */
	public SupportMessage fetchByUuid_Last(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<SupportMessage>
			orderByComparator);

	/**
	 * Returns the support messages before and after the current support message in the ordered set where uuid = &#63;.
	 *
	 * @param messageId the primary key of the current support message
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next support message
	 * @throws NoSuchSupportMessageException if a support message with the primary key could not be found
	 */
	public SupportMessage[] findByUuid_PrevAndNext(
			long messageId, String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<SupportMessage>
				orderByComparator)
		throws NoSuchSupportMessageException;

	/**
	 * Removes all the support messages where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public void removeByUuid(String uuid);

	/**
	 * Returns the number of support messages where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching support messages
	 */
	public int countByUuid(String uuid);

	/**
	 * Returns the support message where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchSupportMessageException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching support message
	 * @throws NoSuchSupportMessageException if a matching support message could not be found
	 */
	public SupportMessage findByUUID_G(String uuid, long groupId)
		throws NoSuchSupportMessageException;

	/**
	 * Returns the support message where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching support message, or <code>null</code> if a matching support message could not be found
	 */
	public SupportMessage fetchByUUID_G(String uuid, long groupId);

	/**
	 * Returns the support message where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching support message, or <code>null</code> if a matching support message could not be found
	 */
	public SupportMessage fetchByUUID_G(
		String uuid, long groupId, boolean useFinderCache);

	/**
	 * Removes the support message where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the support message that was removed
	 */
	public SupportMessage removeByUUID_G(String uuid, long groupId)
		throws NoSuchSupportMessageException;

	/**
	 * Returns the number of support messages where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching support messages
	 */
	public int countByUUID_G(String uuid, long groupId);

	/**
	 * Returns all the support messages where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching support messages
	 */
	public java.util.List<SupportMessage> findByUuid_C(
		String uuid, long companyId);

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
	public java.util.List<SupportMessage> findByUuid_C(
		String uuid, long companyId, int start, int end);

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
	public java.util.List<SupportMessage> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SupportMessage>
			orderByComparator);

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
	public java.util.List<SupportMessage> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SupportMessage>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first support message in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support message
	 * @throws NoSuchSupportMessageException if a matching support message could not be found
	 */
	public SupportMessage findByUuid_C_First(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<SupportMessage>
				orderByComparator)
		throws NoSuchSupportMessageException;

	/**
	 * Returns the first support message in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support message, or <code>null</code> if a matching support message could not be found
	 */
	public SupportMessage fetchByUuid_C_First(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<SupportMessage>
			orderByComparator);

	/**
	 * Returns the last support message in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support message
	 * @throws NoSuchSupportMessageException if a matching support message could not be found
	 */
	public SupportMessage findByUuid_C_Last(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<SupportMessage>
				orderByComparator)
		throws NoSuchSupportMessageException;

	/**
	 * Returns the last support message in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support message, or <code>null</code> if a matching support message could not be found
	 */
	public SupportMessage fetchByUuid_C_Last(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<SupportMessage>
			orderByComparator);

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
	public SupportMessage[] findByUuid_C_PrevAndNext(
			long messageId, String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<SupportMessage>
				orderByComparator)
		throws NoSuchSupportMessageException;

	/**
	 * Removes all the support messages where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public void removeByUuid_C(String uuid, long companyId);

	/**
	 * Returns the number of support messages where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching support messages
	 */
	public int countByUuid_C(String uuid, long companyId);

	/**
	 * Returns all the support messages where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching support messages
	 */
	public java.util.List<SupportMessage> findByUserId(long userId);

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
	public java.util.List<SupportMessage> findByUserId(
		long userId, int start, int end);

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
	public java.util.List<SupportMessage> findByUserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SupportMessage>
			orderByComparator);

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
	public java.util.List<SupportMessage> findByUserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SupportMessage>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first support message in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support message
	 * @throws NoSuchSupportMessageException if a matching support message could not be found
	 */
	public SupportMessage findByUserId_First(
			long userId,
			com.liferay.portal.kernel.util.OrderByComparator<SupportMessage>
				orderByComparator)
		throws NoSuchSupportMessageException;

	/**
	 * Returns the first support message in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support message, or <code>null</code> if a matching support message could not be found
	 */
	public SupportMessage fetchByUserId_First(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator<SupportMessage>
			orderByComparator);

	/**
	 * Returns the last support message in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support message
	 * @throws NoSuchSupportMessageException if a matching support message could not be found
	 */
	public SupportMessage findByUserId_Last(
			long userId,
			com.liferay.portal.kernel.util.OrderByComparator<SupportMessage>
				orderByComparator)
		throws NoSuchSupportMessageException;

	/**
	 * Returns the last support message in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support message, or <code>null</code> if a matching support message could not be found
	 */
	public SupportMessage fetchByUserId_Last(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator<SupportMessage>
			orderByComparator);

	/**
	 * Returns the support messages before and after the current support message in the ordered set where userId = &#63;.
	 *
	 * @param messageId the primary key of the current support message
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next support message
	 * @throws NoSuchSupportMessageException if a support message with the primary key could not be found
	 */
	public SupportMessage[] findByUserId_PrevAndNext(
			long messageId, long userId,
			com.liferay.portal.kernel.util.OrderByComparator<SupportMessage>
				orderByComparator)
		throws NoSuchSupportMessageException;

	/**
	 * Removes all the support messages where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 */
	public void removeByUserId(long userId);

	/**
	 * Returns the number of support messages where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching support messages
	 */
	public int countByUserId(long userId);

	/**
	 * Returns all the support messages where isReplied = &#63;.
	 *
	 * @param isReplied the is replied
	 * @return the matching support messages
	 */
	public java.util.List<SupportMessage> findByIsReplied(boolean isReplied);

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
	public java.util.List<SupportMessage> findByIsReplied(
		boolean isReplied, int start, int end);

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
	public java.util.List<SupportMessage> findByIsReplied(
		boolean isReplied, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SupportMessage>
			orderByComparator);

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
	public java.util.List<SupportMessage> findByIsReplied(
		boolean isReplied, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SupportMessage>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first support message in the ordered set where isReplied = &#63;.
	 *
	 * @param isReplied the is replied
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support message
	 * @throws NoSuchSupportMessageException if a matching support message could not be found
	 */
	public SupportMessage findByIsReplied_First(
			boolean isReplied,
			com.liferay.portal.kernel.util.OrderByComparator<SupportMessage>
				orderByComparator)
		throws NoSuchSupportMessageException;

	/**
	 * Returns the first support message in the ordered set where isReplied = &#63;.
	 *
	 * @param isReplied the is replied
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support message, or <code>null</code> if a matching support message could not be found
	 */
	public SupportMessage fetchByIsReplied_First(
		boolean isReplied,
		com.liferay.portal.kernel.util.OrderByComparator<SupportMessage>
			orderByComparator);

	/**
	 * Returns the last support message in the ordered set where isReplied = &#63;.
	 *
	 * @param isReplied the is replied
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support message
	 * @throws NoSuchSupportMessageException if a matching support message could not be found
	 */
	public SupportMessage findByIsReplied_Last(
			boolean isReplied,
			com.liferay.portal.kernel.util.OrderByComparator<SupportMessage>
				orderByComparator)
		throws NoSuchSupportMessageException;

	/**
	 * Returns the last support message in the ordered set where isReplied = &#63;.
	 *
	 * @param isReplied the is replied
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support message, or <code>null</code> if a matching support message could not be found
	 */
	public SupportMessage fetchByIsReplied_Last(
		boolean isReplied,
		com.liferay.portal.kernel.util.OrderByComparator<SupportMessage>
			orderByComparator);

	/**
	 * Returns the support messages before and after the current support message in the ordered set where isReplied = &#63;.
	 *
	 * @param messageId the primary key of the current support message
	 * @param isReplied the is replied
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next support message
	 * @throws NoSuchSupportMessageException if a support message with the primary key could not be found
	 */
	public SupportMessage[] findByIsReplied_PrevAndNext(
			long messageId, boolean isReplied,
			com.liferay.portal.kernel.util.OrderByComparator<SupportMessage>
				orderByComparator)
		throws NoSuchSupportMessageException;

	/**
	 * Removes all the support messages where isReplied = &#63; from the database.
	 *
	 * @param isReplied the is replied
	 */
	public void removeByIsReplied(boolean isReplied);

	/**
	 * Returns the number of support messages where isReplied = &#63;.
	 *
	 * @param isReplied the is replied
	 * @return the number of matching support messages
	 */
	public int countByIsReplied(boolean isReplied);

	/**
	 * Caches the support message in the entity cache if it is enabled.
	 *
	 * @param supportMessage the support message
	 */
	public void cacheResult(SupportMessage supportMessage);

	/**
	 * Caches the support messages in the entity cache if it is enabled.
	 *
	 * @param supportMessages the support messages
	 */
	public void cacheResult(java.util.List<SupportMessage> supportMessages);

	/**
	 * Creates a new support message with the primary key. Does not add the support message to the database.
	 *
	 * @param messageId the primary key for the new support message
	 * @return the new support message
	 */
	public SupportMessage create(long messageId);

	/**
	 * Removes the support message with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param messageId the primary key of the support message
	 * @return the support message that was removed
	 * @throws NoSuchSupportMessageException if a support message with the primary key could not be found
	 */
	public SupportMessage remove(long messageId)
		throws NoSuchSupportMessageException;

	public SupportMessage updateImpl(SupportMessage supportMessage);

	/**
	 * Returns the support message with the primary key or throws a <code>NoSuchSupportMessageException</code> if it could not be found.
	 *
	 * @param messageId the primary key of the support message
	 * @return the support message
	 * @throws NoSuchSupportMessageException if a support message with the primary key could not be found
	 */
	public SupportMessage findByPrimaryKey(long messageId)
		throws NoSuchSupportMessageException;

	/**
	 * Returns the support message with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param messageId the primary key of the support message
	 * @return the support message, or <code>null</code> if a support message with the primary key could not be found
	 */
	public SupportMessage fetchByPrimaryKey(long messageId);

	/**
	 * Returns all the support messages.
	 *
	 * @return the support messages
	 */
	public java.util.List<SupportMessage> findAll();

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
	public java.util.List<SupportMessage> findAll(int start, int end);

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
	public java.util.List<SupportMessage> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SupportMessage>
			orderByComparator);

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
	public java.util.List<SupportMessage> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SupportMessage>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the support messages from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of support messages.
	 *
	 * @return the number of support messages
	 */
	public int countAll();

}