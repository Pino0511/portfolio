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

package contacts.enterprise.manager.service.persistence.impl;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.configuration.Configuration;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.SessionFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.BasePersistence;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;

import contacts.enterprise.manager.exception.NoSuchSupportMessageException;
import contacts.enterprise.manager.model.SupportMessage;
import contacts.enterprise.manager.model.SupportMessageTable;
import contacts.enterprise.manager.model.impl.SupportMessageImpl;
import contacts.enterprise.manager.model.impl.SupportMessageModelImpl;
import contacts.enterprise.manager.service.persistence.SupportMessagePersistence;
import contacts.enterprise.manager.service.persistence.SupportMessageUtil;
import contacts.enterprise.manager.service.persistence.impl.constants.CEMPersistenceConstants;

import java.io.Serializable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * The persistence implementation for the support message service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = {SupportMessagePersistence.class, BasePersistence.class})
public class SupportMessagePersistenceImpl
	extends BasePersistenceImpl<SupportMessage>
	implements SupportMessagePersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>SupportMessageUtil</code> to access the support message persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		SupportMessageImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByUuid;
	private FinderPath _finderPathWithoutPaginationFindByUuid;
	private FinderPath _finderPathCountByUuid;

	/**
	 * Returns all the support messages where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching support messages
	 */
	@Override
	public List<SupportMessage> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<SupportMessage> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
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
	@Override
	public List<SupportMessage> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<SupportMessage> orderByComparator) {

		return findByUuid(uuid, start, end, orderByComparator, true);
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
	@Override
	public List<SupportMessage> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<SupportMessage> orderByComparator,
		boolean useFinderCache) {

		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByUuid;
				finderArgs = new Object[] {uuid};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByUuid;
			finderArgs = new Object[] {uuid, start, end, orderByComparator};
		}

		List<SupportMessage> list = null;

		if (useFinderCache) {
			list = (List<SupportMessage>)finderCache.getResult(
				finderPath, finderArgs);

			if ((list != null) && !list.isEmpty()) {
				for (SupportMessage supportMessage : list) {
					if (!uuid.equals(supportMessage.getUuid())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					3 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(3);
			}

			sb.append(_SQL_SELECT_SUPPORTMESSAGE_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(SupportMessageModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				list = (List<SupportMessage>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first support message in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support message
	 * @throws NoSuchSupportMessageException if a matching support message could not be found
	 */
	@Override
	public SupportMessage findByUuid_First(
			String uuid, OrderByComparator<SupportMessage> orderByComparator)
		throws NoSuchSupportMessageException {

		SupportMessage supportMessage = fetchByUuid_First(
			uuid, orderByComparator);

		if (supportMessage != null) {
			return supportMessage;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchSupportMessageException(sb.toString());
	}

	/**
	 * Returns the first support message in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support message, or <code>null</code> if a matching support message could not be found
	 */
	@Override
	public SupportMessage fetchByUuid_First(
		String uuid, OrderByComparator<SupportMessage> orderByComparator) {

		List<SupportMessage> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last support message in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support message
	 * @throws NoSuchSupportMessageException if a matching support message could not be found
	 */
	@Override
	public SupportMessage findByUuid_Last(
			String uuid, OrderByComparator<SupportMessage> orderByComparator)
		throws NoSuchSupportMessageException {

		SupportMessage supportMessage = fetchByUuid_Last(
			uuid, orderByComparator);

		if (supportMessage != null) {
			return supportMessage;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchSupportMessageException(sb.toString());
	}

	/**
	 * Returns the last support message in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support message, or <code>null</code> if a matching support message could not be found
	 */
	@Override
	public SupportMessage fetchByUuid_Last(
		String uuid, OrderByComparator<SupportMessage> orderByComparator) {

		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<SupportMessage> list = findByUuid(
			uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public SupportMessage[] findByUuid_PrevAndNext(
			long messageId, String uuid,
			OrderByComparator<SupportMessage> orderByComparator)
		throws NoSuchSupportMessageException {

		uuid = Objects.toString(uuid, "");

		SupportMessage supportMessage = findByPrimaryKey(messageId);

		Session session = null;

		try {
			session = openSession();

			SupportMessage[] array = new SupportMessageImpl[3];

			array[0] = getByUuid_PrevAndNext(
				session, supportMessage, uuid, orderByComparator, true);

			array[1] = supportMessage;

			array[2] = getByUuid_PrevAndNext(
				session, supportMessage, uuid, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected SupportMessage getByUuid_PrevAndNext(
		Session session, SupportMessage supportMessage, String uuid,
		OrderByComparator<SupportMessage> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_SUPPORTMESSAGE_WHERE);

		boolean bindUuid = false;

		if (uuid.isEmpty()) {
			sb.append(_FINDER_COLUMN_UUID_UUID_3);
		}
		else {
			bindUuid = true;

			sb.append(_FINDER_COLUMN_UUID_UUID_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			sb.append(SupportMessageModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindUuid) {
			queryPos.add(uuid);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						supportMessage)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<SupportMessage> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the support messages where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (SupportMessage supportMessage :
				findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(supportMessage);
		}
	}

	/**
	 * Returns the number of support messages where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching support messages
	 */
	@Override
	public int countByUuid(String uuid) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid;

		Object[] finderArgs = new Object[] {uuid};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_SUPPORTMESSAGE_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_UUID_2 =
		"supportMessage.uuid = ?";

	private static final String _FINDER_COLUMN_UUID_UUID_3 =
		"(supportMessage.uuid IS NULL OR supportMessage.uuid = '')";

	private FinderPath _finderPathFetchByUUID_G;
	private FinderPath _finderPathCountByUUID_G;

	/**
	 * Returns the support message where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchSupportMessageException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching support message
	 * @throws NoSuchSupportMessageException if a matching support message could not be found
	 */
	@Override
	public SupportMessage findByUUID_G(String uuid, long groupId)
		throws NoSuchSupportMessageException {

		SupportMessage supportMessage = fetchByUUID_G(uuid, groupId);

		if (supportMessage == null) {
			StringBundler sb = new StringBundler(6);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("uuid=");
			sb.append(uuid);

			sb.append(", groupId=");
			sb.append(groupId);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchSupportMessageException(sb.toString());
		}

		return supportMessage;
	}

	/**
	 * Returns the support message where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching support message, or <code>null</code> if a matching support message could not be found
	 */
	@Override
	public SupportMessage fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the support message where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching support message, or <code>null</code> if a matching support message could not be found
	 */
	@Override
	public SupportMessage fetchByUUID_G(
		String uuid, long groupId, boolean useFinderCache) {

		uuid = Objects.toString(uuid, "");

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {uuid, groupId};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByUUID_G, finderArgs);
		}

		if (result instanceof SupportMessage) {
			SupportMessage supportMessage = (SupportMessage)result;

			if (!Objects.equals(uuid, supportMessage.getUuid()) ||
				(groupId != supportMessage.getGroupId())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_SQL_SELECT_SUPPORTMESSAGE_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_G_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_G_UUID_2);
			}

			sb.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				queryPos.add(groupId);

				List<SupportMessage> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByUUID_G, finderArgs, list);
					}
				}
				else {
					SupportMessage supportMessage = list.get(0);

					result = supportMessage;

					cacheResult(supportMessage);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (SupportMessage)result;
		}
	}

	/**
	 * Removes the support message where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the support message that was removed
	 */
	@Override
	public SupportMessage removeByUUID_G(String uuid, long groupId)
		throws NoSuchSupportMessageException {

		SupportMessage supportMessage = findByUUID_G(uuid, groupId);

		return remove(supportMessage);
	}

	/**
	 * Returns the number of support messages where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching support messages
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUUID_G;

		Object[] finderArgs = new Object[] {uuid, groupId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_SUPPORTMESSAGE_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_G_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_G_UUID_2);
			}

			sb.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				queryPos.add(groupId);

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_G_UUID_2 =
		"supportMessage.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_G_UUID_3 =
		"(supportMessage.uuid IS NULL OR supportMessage.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 =
		"supportMessage.groupId = ?";

	private FinderPath _finderPathWithPaginationFindByUuid_C;
	private FinderPath _finderPathWithoutPaginationFindByUuid_C;
	private FinderPath _finderPathCountByUuid_C;

	/**
	 * Returns all the support messages where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching support messages
	 */
	@Override
	public List<SupportMessage> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(
			uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<SupportMessage> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return findByUuid_C(uuid, companyId, start, end, null);
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
	@Override
	public List<SupportMessage> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<SupportMessage> orderByComparator) {

		return findByUuid_C(
			uuid, companyId, start, end, orderByComparator, true);
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
	@Override
	public List<SupportMessage> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<SupportMessage> orderByComparator,
		boolean useFinderCache) {

		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByUuid_C;
				finderArgs = new Object[] {uuid, companyId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByUuid_C;
			finderArgs = new Object[] {
				uuid, companyId, start, end, orderByComparator
			};
		}

		List<SupportMessage> list = null;

		if (useFinderCache) {
			list = (List<SupportMessage>)finderCache.getResult(
				finderPath, finderArgs);

			if ((list != null) && !list.isEmpty()) {
				for (SupportMessage supportMessage : list) {
					if (!uuid.equals(supportMessage.getUuid()) ||
						(companyId != supportMessage.getCompanyId())) {

						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					4 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(4);
			}

			sb.append(_SQL_SELECT_SUPPORTMESSAGE_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}

			sb.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(SupportMessageModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				queryPos.add(companyId);

				list = (List<SupportMessage>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
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
	@Override
	public SupportMessage findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<SupportMessage> orderByComparator)
		throws NoSuchSupportMessageException {

		SupportMessage supportMessage = fetchByUuid_C_First(
			uuid, companyId, orderByComparator);

		if (supportMessage != null) {
			return supportMessage;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append(", companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchSupportMessageException(sb.toString());
	}

	/**
	 * Returns the first support message in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support message, or <code>null</code> if a matching support message could not be found
	 */
	@Override
	public SupportMessage fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<SupportMessage> orderByComparator) {

		List<SupportMessage> list = findByUuid_C(
			uuid, companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public SupportMessage findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<SupportMessage> orderByComparator)
		throws NoSuchSupportMessageException {

		SupportMessage supportMessage = fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);

		if (supportMessage != null) {
			return supportMessage;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append(", companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchSupportMessageException(sb.toString());
	}

	/**
	 * Returns the last support message in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support message, or <code>null</code> if a matching support message could not be found
	 */
	@Override
	public SupportMessage fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<SupportMessage> orderByComparator) {

		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<SupportMessage> list = findByUuid_C(
			uuid, companyId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public SupportMessage[] findByUuid_C_PrevAndNext(
			long messageId, String uuid, long companyId,
			OrderByComparator<SupportMessage> orderByComparator)
		throws NoSuchSupportMessageException {

		uuid = Objects.toString(uuid, "");

		SupportMessage supportMessage = findByPrimaryKey(messageId);

		Session session = null;

		try {
			session = openSession();

			SupportMessage[] array = new SupportMessageImpl[3];

			array[0] = getByUuid_C_PrevAndNext(
				session, supportMessage, uuid, companyId, orderByComparator,
				true);

			array[1] = supportMessage;

			array[2] = getByUuid_C_PrevAndNext(
				session, supportMessage, uuid, companyId, orderByComparator,
				false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected SupportMessage getByUuid_C_PrevAndNext(
		Session session, SupportMessage supportMessage, String uuid,
		long companyId, OrderByComparator<SupportMessage> orderByComparator,
		boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(4);
		}

		sb.append(_SQL_SELECT_SUPPORTMESSAGE_WHERE);

		boolean bindUuid = false;

		if (uuid.isEmpty()) {
			sb.append(_FINDER_COLUMN_UUID_C_UUID_3);
		}
		else {
			bindUuid = true;

			sb.append(_FINDER_COLUMN_UUID_C_UUID_2);
		}

		sb.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			sb.append(SupportMessageModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindUuid) {
			queryPos.add(uuid);
		}

		queryPos.add(companyId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						supportMessage)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<SupportMessage> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the support messages where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (SupportMessage supportMessage :
				findByUuid_C(
					uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(supportMessage);
		}
	}

	/**
	 * Returns the number of support messages where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching support messages
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid_C;

		Object[] finderArgs = new Object[] {uuid, companyId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_SUPPORTMESSAGE_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}

			sb.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				queryPos.add(companyId);

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_C_UUID_2 =
		"supportMessage.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_C_UUID_3 =
		"(supportMessage.uuid IS NULL OR supportMessage.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 =
		"supportMessage.companyId = ?";

	private FinderPath _finderPathWithPaginationFindByUserId;
	private FinderPath _finderPathWithoutPaginationFindByUserId;
	private FinderPath _finderPathCountByUserId;

	/**
	 * Returns all the support messages where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching support messages
	 */
	@Override
	public List<SupportMessage> findByUserId(long userId) {
		return findByUserId(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<SupportMessage> findByUserId(long userId, int start, int end) {
		return findByUserId(userId, start, end, null);
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
	@Override
	public List<SupportMessage> findByUserId(
		long userId, int start, int end,
		OrderByComparator<SupportMessage> orderByComparator) {

		return findByUserId(userId, start, end, orderByComparator, true);
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
	@Override
	public List<SupportMessage> findByUserId(
		long userId, int start, int end,
		OrderByComparator<SupportMessage> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByUserId;
				finderArgs = new Object[] {userId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByUserId;
			finderArgs = new Object[] {userId, start, end, orderByComparator};
		}

		List<SupportMessage> list = null;

		if (useFinderCache) {
			list = (List<SupportMessage>)finderCache.getResult(
				finderPath, finderArgs);

			if ((list != null) && !list.isEmpty()) {
				for (SupportMessage supportMessage : list) {
					if (userId != supportMessage.getUserId()) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					3 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(3);
			}

			sb.append(_SQL_SELECT_SUPPORTMESSAGE_WHERE);

			sb.append(_FINDER_COLUMN_USERID_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(SupportMessageModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(userId);

				list = (List<SupportMessage>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first support message in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support message
	 * @throws NoSuchSupportMessageException if a matching support message could not be found
	 */
	@Override
	public SupportMessage findByUserId_First(
			long userId, OrderByComparator<SupportMessage> orderByComparator)
		throws NoSuchSupportMessageException {

		SupportMessage supportMessage = fetchByUserId_First(
			userId, orderByComparator);

		if (supportMessage != null) {
			return supportMessage;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("userId=");
		sb.append(userId);

		sb.append("}");

		throw new NoSuchSupportMessageException(sb.toString());
	}

	/**
	 * Returns the first support message in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support message, or <code>null</code> if a matching support message could not be found
	 */
	@Override
	public SupportMessage fetchByUserId_First(
		long userId, OrderByComparator<SupportMessage> orderByComparator) {

		List<SupportMessage> list = findByUserId(
			userId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last support message in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support message
	 * @throws NoSuchSupportMessageException if a matching support message could not be found
	 */
	@Override
	public SupportMessage findByUserId_Last(
			long userId, OrderByComparator<SupportMessage> orderByComparator)
		throws NoSuchSupportMessageException {

		SupportMessage supportMessage = fetchByUserId_Last(
			userId, orderByComparator);

		if (supportMessage != null) {
			return supportMessage;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("userId=");
		sb.append(userId);

		sb.append("}");

		throw new NoSuchSupportMessageException(sb.toString());
	}

	/**
	 * Returns the last support message in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support message, or <code>null</code> if a matching support message could not be found
	 */
	@Override
	public SupportMessage fetchByUserId_Last(
		long userId, OrderByComparator<SupportMessage> orderByComparator) {

		int count = countByUserId(userId);

		if (count == 0) {
			return null;
		}

		List<SupportMessage> list = findByUserId(
			userId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public SupportMessage[] findByUserId_PrevAndNext(
			long messageId, long userId,
			OrderByComparator<SupportMessage> orderByComparator)
		throws NoSuchSupportMessageException {

		SupportMessage supportMessage = findByPrimaryKey(messageId);

		Session session = null;

		try {
			session = openSession();

			SupportMessage[] array = new SupportMessageImpl[3];

			array[0] = getByUserId_PrevAndNext(
				session, supportMessage, userId, orderByComparator, true);

			array[1] = supportMessage;

			array[2] = getByUserId_PrevAndNext(
				session, supportMessage, userId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected SupportMessage getByUserId_PrevAndNext(
		Session session, SupportMessage supportMessage, long userId,
		OrderByComparator<SupportMessage> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_SUPPORTMESSAGE_WHERE);

		sb.append(_FINDER_COLUMN_USERID_USERID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			sb.append(SupportMessageModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(userId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						supportMessage)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<SupportMessage> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the support messages where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 */
	@Override
	public void removeByUserId(long userId) {
		for (SupportMessage supportMessage :
				findByUserId(
					userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(supportMessage);
		}
	}

	/**
	 * Returns the number of support messages where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching support messages
	 */
	@Override
	public int countByUserId(long userId) {
		FinderPath finderPath = _finderPathCountByUserId;

		Object[] finderArgs = new Object[] {userId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_SUPPORTMESSAGE_WHERE);

			sb.append(_FINDER_COLUMN_USERID_USERID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(userId);

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_USERID_USERID_2 =
		"supportMessage.userId = ?";

	private FinderPath _finderPathWithPaginationFindByIsReplied;
	private FinderPath _finderPathWithoutPaginationFindByIsReplied;
	private FinderPath _finderPathCountByIsReplied;

	/**
	 * Returns all the support messages where isReplied = &#63;.
	 *
	 * @param isReplied the is replied
	 * @return the matching support messages
	 */
	@Override
	public List<SupportMessage> findByIsReplied(boolean isReplied) {
		return findByIsReplied(
			isReplied, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<SupportMessage> findByIsReplied(
		boolean isReplied, int start, int end) {

		return findByIsReplied(isReplied, start, end, null);
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
	@Override
	public List<SupportMessage> findByIsReplied(
		boolean isReplied, int start, int end,
		OrderByComparator<SupportMessage> orderByComparator) {

		return findByIsReplied(isReplied, start, end, orderByComparator, true);
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
	@Override
	public List<SupportMessage> findByIsReplied(
		boolean isReplied, int start, int end,
		OrderByComparator<SupportMessage> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByIsReplied;
				finderArgs = new Object[] {isReplied};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByIsReplied;
			finderArgs = new Object[] {
				isReplied, start, end, orderByComparator
			};
		}

		List<SupportMessage> list = null;

		if (useFinderCache) {
			list = (List<SupportMessage>)finderCache.getResult(
				finderPath, finderArgs);

			if ((list != null) && !list.isEmpty()) {
				for (SupportMessage supportMessage : list) {
					if (isReplied != supportMessage.isIsReplied()) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					3 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(3);
			}

			sb.append(_SQL_SELECT_SUPPORTMESSAGE_WHERE);

			sb.append(_FINDER_COLUMN_ISREPLIED_ISREPLIED_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(SupportMessageModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(isReplied);

				list = (List<SupportMessage>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first support message in the ordered set where isReplied = &#63;.
	 *
	 * @param isReplied the is replied
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support message
	 * @throws NoSuchSupportMessageException if a matching support message could not be found
	 */
	@Override
	public SupportMessage findByIsReplied_First(
			boolean isReplied,
			OrderByComparator<SupportMessage> orderByComparator)
		throws NoSuchSupportMessageException {

		SupportMessage supportMessage = fetchByIsReplied_First(
			isReplied, orderByComparator);

		if (supportMessage != null) {
			return supportMessage;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("isReplied=");
		sb.append(isReplied);

		sb.append("}");

		throw new NoSuchSupportMessageException(sb.toString());
	}

	/**
	 * Returns the first support message in the ordered set where isReplied = &#63;.
	 *
	 * @param isReplied the is replied
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support message, or <code>null</code> if a matching support message could not be found
	 */
	@Override
	public SupportMessage fetchByIsReplied_First(
		boolean isReplied,
		OrderByComparator<SupportMessage> orderByComparator) {

		List<SupportMessage> list = findByIsReplied(
			isReplied, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last support message in the ordered set where isReplied = &#63;.
	 *
	 * @param isReplied the is replied
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support message
	 * @throws NoSuchSupportMessageException if a matching support message could not be found
	 */
	@Override
	public SupportMessage findByIsReplied_Last(
			boolean isReplied,
			OrderByComparator<SupportMessage> orderByComparator)
		throws NoSuchSupportMessageException {

		SupportMessage supportMessage = fetchByIsReplied_Last(
			isReplied, orderByComparator);

		if (supportMessage != null) {
			return supportMessage;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("isReplied=");
		sb.append(isReplied);

		sb.append("}");

		throw new NoSuchSupportMessageException(sb.toString());
	}

	/**
	 * Returns the last support message in the ordered set where isReplied = &#63;.
	 *
	 * @param isReplied the is replied
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support message, or <code>null</code> if a matching support message could not be found
	 */
	@Override
	public SupportMessage fetchByIsReplied_Last(
		boolean isReplied,
		OrderByComparator<SupportMessage> orderByComparator) {

		int count = countByIsReplied(isReplied);

		if (count == 0) {
			return null;
		}

		List<SupportMessage> list = findByIsReplied(
			isReplied, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public SupportMessage[] findByIsReplied_PrevAndNext(
			long messageId, boolean isReplied,
			OrderByComparator<SupportMessage> orderByComparator)
		throws NoSuchSupportMessageException {

		SupportMessage supportMessage = findByPrimaryKey(messageId);

		Session session = null;

		try {
			session = openSession();

			SupportMessage[] array = new SupportMessageImpl[3];

			array[0] = getByIsReplied_PrevAndNext(
				session, supportMessage, isReplied, orderByComparator, true);

			array[1] = supportMessage;

			array[2] = getByIsReplied_PrevAndNext(
				session, supportMessage, isReplied, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected SupportMessage getByIsReplied_PrevAndNext(
		Session session, SupportMessage supportMessage, boolean isReplied,
		OrderByComparator<SupportMessage> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_SUPPORTMESSAGE_WHERE);

		sb.append(_FINDER_COLUMN_ISREPLIED_ISREPLIED_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			sb.append(SupportMessageModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(isReplied);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						supportMessage)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<SupportMessage> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the support messages where isReplied = &#63; from the database.
	 *
	 * @param isReplied the is replied
	 */
	@Override
	public void removeByIsReplied(boolean isReplied) {
		for (SupportMessage supportMessage :
				findByIsReplied(
					isReplied, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(supportMessage);
		}
	}

	/**
	 * Returns the number of support messages where isReplied = &#63;.
	 *
	 * @param isReplied the is replied
	 * @return the number of matching support messages
	 */
	@Override
	public int countByIsReplied(boolean isReplied) {
		FinderPath finderPath = _finderPathCountByIsReplied;

		Object[] finderArgs = new Object[] {isReplied};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_SUPPORTMESSAGE_WHERE);

			sb.append(_FINDER_COLUMN_ISREPLIED_ISREPLIED_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(isReplied);

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_ISREPLIED_ISREPLIED_2 =
		"supportMessage.isReplied = ?";

	public SupportMessagePersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("uuid", "uuid_");

		setDBColumnNames(dbColumnNames);

		setModelClass(SupportMessage.class);

		setModelImplClass(SupportMessageImpl.class);
		setModelPKClass(long.class);

		setTable(SupportMessageTable.INSTANCE);
	}

	/**
	 * Caches the support message in the entity cache if it is enabled.
	 *
	 * @param supportMessage the support message
	 */
	@Override
	public void cacheResult(SupportMessage supportMessage) {
		entityCache.putResult(
			SupportMessageImpl.class, supportMessage.getPrimaryKey(),
			supportMessage);

		finderCache.putResult(
			_finderPathFetchByUUID_G,
			new Object[] {
				supportMessage.getUuid(), supportMessage.getGroupId()
			},
			supportMessage);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the support messages in the entity cache if it is enabled.
	 *
	 * @param supportMessages the support messages
	 */
	@Override
	public void cacheResult(List<SupportMessage> supportMessages) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (supportMessages.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (SupportMessage supportMessage : supportMessages) {
			if (entityCache.getResult(
					SupportMessageImpl.class, supportMessage.getPrimaryKey()) ==
						null) {

				cacheResult(supportMessage);
			}
		}
	}

	/**
	 * Clears the cache for all support messages.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(SupportMessageImpl.class);

		finderCache.clearCache(SupportMessageImpl.class);
	}

	/**
	 * Clears the cache for the support message.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(SupportMessage supportMessage) {
		entityCache.removeResult(SupportMessageImpl.class, supportMessage);
	}

	@Override
	public void clearCache(List<SupportMessage> supportMessages) {
		for (SupportMessage supportMessage : supportMessages) {
			entityCache.removeResult(SupportMessageImpl.class, supportMessage);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(SupportMessageImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(SupportMessageImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		SupportMessageModelImpl supportMessageModelImpl) {

		Object[] args = new Object[] {
			supportMessageModelImpl.getUuid(),
			supportMessageModelImpl.getGroupId()
		};

		finderCache.putResult(_finderPathCountByUUID_G, args, Long.valueOf(1));
		finderCache.putResult(
			_finderPathFetchByUUID_G, args, supportMessageModelImpl);
	}

	/**
	 * Creates a new support message with the primary key. Does not add the support message to the database.
	 *
	 * @param messageId the primary key for the new support message
	 * @return the new support message
	 */
	@Override
	public SupportMessage create(long messageId) {
		SupportMessage supportMessage = new SupportMessageImpl();

		supportMessage.setNew(true);
		supportMessage.setPrimaryKey(messageId);

		String uuid = PortalUUIDUtil.generate();

		supportMessage.setUuid(uuid);

		supportMessage.setCompanyId(CompanyThreadLocal.getCompanyId());

		return supportMessage;
	}

	/**
	 * Removes the support message with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param messageId the primary key of the support message
	 * @return the support message that was removed
	 * @throws NoSuchSupportMessageException if a support message with the primary key could not be found
	 */
	@Override
	public SupportMessage remove(long messageId)
		throws NoSuchSupportMessageException {

		return remove((Serializable)messageId);
	}

	/**
	 * Removes the support message with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the support message
	 * @return the support message that was removed
	 * @throws NoSuchSupportMessageException if a support message with the primary key could not be found
	 */
	@Override
	public SupportMessage remove(Serializable primaryKey)
		throws NoSuchSupportMessageException {

		Session session = null;

		try {
			session = openSession();

			SupportMessage supportMessage = (SupportMessage)session.get(
				SupportMessageImpl.class, primaryKey);

			if (supportMessage == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchSupportMessageException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(supportMessage);
		}
		catch (NoSuchSupportMessageException noSuchEntityException) {
			throw noSuchEntityException;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected SupportMessage removeImpl(SupportMessage supportMessage) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(supportMessage)) {
				supportMessage = (SupportMessage)session.get(
					SupportMessageImpl.class,
					supportMessage.getPrimaryKeyObj());
			}

			if (supportMessage != null) {
				session.delete(supportMessage);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (supportMessage != null) {
			clearCache(supportMessage);
		}

		return supportMessage;
	}

	@Override
	public SupportMessage updateImpl(SupportMessage supportMessage) {
		boolean isNew = supportMessage.isNew();

		if (!(supportMessage instanceof SupportMessageModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(supportMessage.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					supportMessage);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in supportMessage proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom SupportMessage implementation " +
					supportMessage.getClass());
		}

		SupportMessageModelImpl supportMessageModelImpl =
			(SupportMessageModelImpl)supportMessage;

		if (Validator.isNull(supportMessage.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			supportMessage.setUuid(uuid);
		}

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date date = new Date();

		if (isNew && (supportMessage.getCreateDate() == null)) {
			if (serviceContext == null) {
				supportMessage.setCreateDate(date);
			}
			else {
				supportMessage.setCreateDate(
					serviceContext.getCreateDate(date));
			}
		}

		if (!supportMessageModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				supportMessage.setModifiedDate(date);
			}
			else {
				supportMessage.setModifiedDate(
					serviceContext.getModifiedDate(date));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(supportMessage);
			}
			else {
				supportMessage = (SupportMessage)session.merge(supportMessage);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			SupportMessageImpl.class, supportMessageModelImpl, false, true);

		cacheUniqueFindersCache(supportMessageModelImpl);

		if (isNew) {
			supportMessage.setNew(false);
		}

		supportMessage.resetOriginalValues();

		return supportMessage;
	}

	/**
	 * Returns the support message with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the support message
	 * @return the support message
	 * @throws NoSuchSupportMessageException if a support message with the primary key could not be found
	 */
	@Override
	public SupportMessage findByPrimaryKey(Serializable primaryKey)
		throws NoSuchSupportMessageException {

		SupportMessage supportMessage = fetchByPrimaryKey(primaryKey);

		if (supportMessage == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchSupportMessageException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return supportMessage;
	}

	/**
	 * Returns the support message with the primary key or throws a <code>NoSuchSupportMessageException</code> if it could not be found.
	 *
	 * @param messageId the primary key of the support message
	 * @return the support message
	 * @throws NoSuchSupportMessageException if a support message with the primary key could not be found
	 */
	@Override
	public SupportMessage findByPrimaryKey(long messageId)
		throws NoSuchSupportMessageException {

		return findByPrimaryKey((Serializable)messageId);
	}

	/**
	 * Returns the support message with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param messageId the primary key of the support message
	 * @return the support message, or <code>null</code> if a support message with the primary key could not be found
	 */
	@Override
	public SupportMessage fetchByPrimaryKey(long messageId) {
		return fetchByPrimaryKey((Serializable)messageId);
	}

	/**
	 * Returns all the support messages.
	 *
	 * @return the support messages
	 */
	@Override
	public List<SupportMessage> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<SupportMessage> findAll(int start, int end) {
		return findAll(start, end, null);
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
	@Override
	public List<SupportMessage> findAll(
		int start, int end,
		OrderByComparator<SupportMessage> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
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
	@Override
	public List<SupportMessage> findAll(
		int start, int end, OrderByComparator<SupportMessage> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindAll;
				finderArgs = FINDER_ARGS_EMPTY;
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindAll;
			finderArgs = new Object[] {start, end, orderByComparator};
		}

		List<SupportMessage> list = null;

		if (useFinderCache) {
			list = (List<SupportMessage>)finderCache.getResult(
				finderPath, finderArgs);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_SUPPORTMESSAGE);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_SUPPORTMESSAGE;

				sql = sql.concat(SupportMessageModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<SupportMessage>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the support messages from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (SupportMessage supportMessage : findAll()) {
			remove(supportMessage);
		}
	}

	/**
	 * Returns the number of support messages.
	 *
	 * @return the number of support messages
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_SUPPORTMESSAGE);

				count = (Long)query.uniqueResult();

				finderCache.putResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	public Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	@Override
	protected EntityCache getEntityCache() {
		return entityCache;
	}

	@Override
	protected String getPKDBName() {
		return "messageId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_SUPPORTMESSAGE;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return SupportMessageModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the support message persistence.
	 */
	@Activate
	public void activate() {
		_valueObjectFinderCacheListThreshold = GetterUtil.getInteger(
			PropsUtil.get(PropsKeys.VALUE_OBJECT_FINDER_CACHE_LIST_THRESHOLD));

		_finderPathWithPaginationFindAll = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0],
			new String[0], true);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0],
			new String[0], true);

		_finderPathCountAll = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0], new String[0], false);

		_finderPathWithPaginationFindByUuid = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"uuid_"}, true);

		_finderPathWithoutPaginationFindByUuid = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] {String.class.getName()}, new String[] {"uuid_"},
			true);

		_finderPathCountByUuid = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] {String.class.getName()}, new String[] {"uuid_"},
			false);

		_finderPathFetchByUUID_G = new FinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] {String.class.getName(), Long.class.getName()},
			new String[] {"uuid_", "groupId"}, true);

		_finderPathCountByUUID_G = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] {String.class.getName(), Long.class.getName()},
			new String[] {"uuid_", "groupId"}, false);

		_finderPathWithPaginationFindByUuid_C = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			},
			new String[] {"uuid_", "companyId"}, true);

		_finderPathWithoutPaginationFindByUuid_C = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()},
			new String[] {"uuid_", "companyId"}, true);

		_finderPathCountByUuid_C = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()},
			new String[] {"uuid_", "companyId"}, false);

		_finderPathWithPaginationFindByUserId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUserId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"userId"}, true);

		_finderPathWithoutPaginationFindByUserId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUserId",
			new String[] {Long.class.getName()}, new String[] {"userId"}, true);

		_finderPathCountByUserId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUserId",
			new String[] {Long.class.getName()}, new String[] {"userId"},
			false);

		_finderPathWithPaginationFindByIsReplied = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByIsReplied",
			new String[] {
				Boolean.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"isReplied"}, true);

		_finderPathWithoutPaginationFindByIsReplied = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByIsReplied",
			new String[] {Boolean.class.getName()}, new String[] {"isReplied"},
			true);

		_finderPathCountByIsReplied = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByIsReplied",
			new String[] {Boolean.class.getName()}, new String[] {"isReplied"},
			false);

		_setSupportMessageUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setSupportMessageUtilPersistence(null);

		entityCache.removeCache(SupportMessageImpl.class.getName());
	}

	private void _setSupportMessageUtilPersistence(
		SupportMessagePersistence supportMessagePersistence) {

		try {
			Field field = SupportMessageUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, supportMessagePersistence);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@Override
	@Reference(
		target = CEMPersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
	}

	@Override
	@Reference(
		target = CEMPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = CEMPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_SUPPORTMESSAGE =
		"SELECT supportMessage FROM SupportMessage supportMessage";

	private static final String _SQL_SELECT_SUPPORTMESSAGE_WHERE =
		"SELECT supportMessage FROM SupportMessage supportMessage WHERE ";

	private static final String _SQL_COUNT_SUPPORTMESSAGE =
		"SELECT COUNT(supportMessage) FROM SupportMessage supportMessage";

	private static final String _SQL_COUNT_SUPPORTMESSAGE_WHERE =
		"SELECT COUNT(supportMessage) FROM SupportMessage supportMessage WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "supportMessage.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No SupportMessage exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No SupportMessage exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		SupportMessagePersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"uuid"});

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

	@Reference
	private SupportMessageModelArgumentsResolver
		_supportMessageModelArgumentsResolver;

}