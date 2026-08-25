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

package contacts.enterprise.manager.service.persistence.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.test.ReflectionTestUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.util.IntegerWrapper;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PersistenceTestRule;
import com.liferay.portal.test.rule.TransactionalTestRule;

import contacts.enterprise.manager.exception.NoSuchSupportMessageException;
import contacts.enterprise.manager.model.SupportMessage;
import contacts.enterprise.manager.service.SupportMessageLocalServiceUtil;
import contacts.enterprise.manager.service.persistence.SupportMessagePersistence;
import contacts.enterprise.manager.service.persistence.SupportMessageUtil;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @generated
 */
@RunWith(Arquillian.class)
public class SupportMessagePersistenceTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(), PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(
				Propagation.REQUIRED, "contacts.enterprise.manager.service"));

	@Before
	public void setUp() {
		_persistence = SupportMessageUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<SupportMessage> iterator = _supportMessages.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		SupportMessage supportMessage = _persistence.create(pk);

		Assert.assertNotNull(supportMessage);

		Assert.assertEquals(supportMessage.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		SupportMessage newSupportMessage = addSupportMessage();

		_persistence.remove(newSupportMessage);

		SupportMessage existingSupportMessage = _persistence.fetchByPrimaryKey(
			newSupportMessage.getPrimaryKey());

		Assert.assertNull(existingSupportMessage);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addSupportMessage();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		SupportMessage newSupportMessage = _persistence.create(pk);

		newSupportMessage.setUuid(RandomTestUtil.randomString());

		newSupportMessage.setGroupId(RandomTestUtil.nextLong());

		newSupportMessage.setCompanyId(RandomTestUtil.nextLong());

		newSupportMessage.setUserId(RandomTestUtil.nextLong());

		newSupportMessage.setUserName(RandomTestUtil.randomString());

		newSupportMessage.setCreateDate(RandomTestUtil.nextDate());

		newSupportMessage.setModifiedDate(RandomTestUtil.nextDate());

		newSupportMessage.setSenderName(RandomTestUtil.randomString());

		newSupportMessage.setSenderEmail(RandomTestUtil.randomString());

		newSupportMessage.setSubject(RandomTestUtil.randomString());

		newSupportMessage.setBody(RandomTestUtil.randomString());

		newSupportMessage.setReplyText(RandomTestUtil.randomString());

		newSupportMessage.setIsReplied(RandomTestUtil.randomBoolean());

		_supportMessages.add(_persistence.update(newSupportMessage));

		SupportMessage existingSupportMessage = _persistence.findByPrimaryKey(
			newSupportMessage.getPrimaryKey());

		Assert.assertEquals(
			existingSupportMessage.getUuid(), newSupportMessage.getUuid());
		Assert.assertEquals(
			existingSupportMessage.getMessageId(),
			newSupportMessage.getMessageId());
		Assert.assertEquals(
			existingSupportMessage.getGroupId(),
			newSupportMessage.getGroupId());
		Assert.assertEquals(
			existingSupportMessage.getCompanyId(),
			newSupportMessage.getCompanyId());
		Assert.assertEquals(
			existingSupportMessage.getUserId(), newSupportMessage.getUserId());
		Assert.assertEquals(
			existingSupportMessage.getUserName(),
			newSupportMessage.getUserName());
		Assert.assertEquals(
			Time.getShortTimestamp(existingSupportMessage.getCreateDate()),
			Time.getShortTimestamp(newSupportMessage.getCreateDate()));
		Assert.assertEquals(
			Time.getShortTimestamp(existingSupportMessage.getModifiedDate()),
			Time.getShortTimestamp(newSupportMessage.getModifiedDate()));
		Assert.assertEquals(
			existingSupportMessage.getSenderName(),
			newSupportMessage.getSenderName());
		Assert.assertEquals(
			existingSupportMessage.getSenderEmail(),
			newSupportMessage.getSenderEmail());
		Assert.assertEquals(
			existingSupportMessage.getSubject(),
			newSupportMessage.getSubject());
		Assert.assertEquals(
			existingSupportMessage.getBody(), newSupportMessage.getBody());
		Assert.assertEquals(
			existingSupportMessage.getReplyText(),
			newSupportMessage.getReplyText());
		Assert.assertEquals(
			existingSupportMessage.isIsReplied(),
			newSupportMessage.isIsReplied());
	}

	@Test
	public void testCountByUuid() throws Exception {
		_persistence.countByUuid("");

		_persistence.countByUuid("null");

		_persistence.countByUuid((String)null);
	}

	@Test
	public void testCountByUUID_G() throws Exception {
		_persistence.countByUUID_G("", RandomTestUtil.nextLong());

		_persistence.countByUUID_G("null", 0L);

		_persistence.countByUUID_G((String)null, 0L);
	}

	@Test
	public void testCountByUuid_C() throws Exception {
		_persistence.countByUuid_C("", RandomTestUtil.nextLong());

		_persistence.countByUuid_C("null", 0L);

		_persistence.countByUuid_C((String)null, 0L);
	}

	@Test
	public void testCountByUserId() throws Exception {
		_persistence.countByUserId(RandomTestUtil.nextLong());

		_persistence.countByUserId(0L);
	}

	@Test
	public void testCountByIsReplied() throws Exception {
		_persistence.countByIsReplied(RandomTestUtil.randomBoolean());

		_persistence.countByIsReplied(RandomTestUtil.randomBoolean());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		SupportMessage newSupportMessage = addSupportMessage();

		SupportMessage existingSupportMessage = _persistence.findByPrimaryKey(
			newSupportMessage.getPrimaryKey());

		Assert.assertEquals(existingSupportMessage, newSupportMessage);
	}

	@Test(expected = NoSuchSupportMessageException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, getOrderByComparator());
	}

	protected OrderByComparator<SupportMessage> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create(
			"CEM_SupportMessage", "uuid", true, "messageId", true, "groupId",
			true, "companyId", true, "userId", true, "userName", true,
			"createDate", true, "modifiedDate", true, "senderName", true,
			"senderEmail", true, "subject", true, "body", true, "replyText",
			true, "isReplied", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		SupportMessage newSupportMessage = addSupportMessage();

		SupportMessage existingSupportMessage = _persistence.fetchByPrimaryKey(
			newSupportMessage.getPrimaryKey());

		Assert.assertEquals(existingSupportMessage, newSupportMessage);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		SupportMessage missingSupportMessage = _persistence.fetchByPrimaryKey(
			pk);

		Assert.assertNull(missingSupportMessage);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {

		SupportMessage newSupportMessage1 = addSupportMessage();
		SupportMessage newSupportMessage2 = addSupportMessage();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newSupportMessage1.getPrimaryKey());
		primaryKeys.add(newSupportMessage2.getPrimaryKey());

		Map<Serializable, SupportMessage> supportMessages =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, supportMessages.size());
		Assert.assertEquals(
			newSupportMessage1,
			supportMessages.get(newSupportMessage1.getPrimaryKey()));
		Assert.assertEquals(
			newSupportMessage2,
			supportMessages.get(newSupportMessage2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {

		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, SupportMessage> supportMessages =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(supportMessages.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {

		SupportMessage newSupportMessage = addSupportMessage();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newSupportMessage.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, SupportMessage> supportMessages =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, supportMessages.size());
		Assert.assertEquals(
			newSupportMessage,
			supportMessages.get(newSupportMessage.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys() throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, SupportMessage> supportMessages =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(supportMessages.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey() throws Exception {
		SupportMessage newSupportMessage = addSupportMessage();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newSupportMessage.getPrimaryKey());

		Map<Serializable, SupportMessage> supportMessages =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, supportMessages.size());
		Assert.assertEquals(
			newSupportMessage,
			supportMessages.get(newSupportMessage.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery =
			SupportMessageLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod<SupportMessage>() {

				@Override
				public void performAction(SupportMessage supportMessage) {
					Assert.assertNotNull(supportMessage);

					count.increment();
				}

			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting() throws Exception {
		SupportMessage newSupportMessage = addSupportMessage();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			SupportMessage.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"messageId", newSupportMessage.getMessageId()));

		List<SupportMessage> result = _persistence.findWithDynamicQuery(
			dynamicQuery);

		Assert.assertEquals(1, result.size());

		SupportMessage existingSupportMessage = result.get(0);

		Assert.assertEquals(existingSupportMessage, newSupportMessage);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			SupportMessage.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq("messageId", RandomTestUtil.nextLong()));

		List<SupportMessage> result = _persistence.findWithDynamicQuery(
			dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting() throws Exception {
		SupportMessage newSupportMessage = addSupportMessage();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			SupportMessage.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("messageId"));

		Object newMessageId = newSupportMessage.getMessageId();

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"messageId", new Object[] {newMessageId}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingMessageId = result.get(0);

		Assert.assertEquals(existingMessageId, newMessageId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			SupportMessage.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("messageId"));

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"messageId", new Object[] {RandomTestUtil.nextLong()}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		SupportMessage newSupportMessage = addSupportMessage();

		_persistence.clearCache();

		_assertOriginalValues(
			_persistence.findByPrimaryKey(newSupportMessage.getPrimaryKey()));
	}

	@Test
	public void testResetOriginalValuesWithDynamicQueryLoadFromDatabase()
		throws Exception {

		_testResetOriginalValuesWithDynamicQuery(true);
	}

	@Test
	public void testResetOriginalValuesWithDynamicQueryLoadFromSession()
		throws Exception {

		_testResetOriginalValuesWithDynamicQuery(false);
	}

	private void _testResetOriginalValuesWithDynamicQuery(boolean clearSession)
		throws Exception {

		SupportMessage newSupportMessage = addSupportMessage();

		if (clearSession) {
			Session session = _persistence.openSession();

			session.flush();

			session.clear();
		}

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			SupportMessage.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"messageId", newSupportMessage.getMessageId()));

		List<SupportMessage> result = _persistence.findWithDynamicQuery(
			dynamicQuery);

		_assertOriginalValues(result.get(0));
	}

	private void _assertOriginalValues(SupportMessage supportMessage) {
		Assert.assertEquals(
			supportMessage.getUuid(),
			ReflectionTestUtil.invoke(
				supportMessage, "getColumnOriginalValue",
				new Class<?>[] {String.class}, "uuid_"));
		Assert.assertEquals(
			Long.valueOf(supportMessage.getGroupId()),
			ReflectionTestUtil.<Long>invoke(
				supportMessage, "getColumnOriginalValue",
				new Class<?>[] {String.class}, "groupId"));
	}

	protected SupportMessage addSupportMessage() throws Exception {
		long pk = RandomTestUtil.nextLong();

		SupportMessage supportMessage = _persistence.create(pk);

		supportMessage.setUuid(RandomTestUtil.randomString());

		supportMessage.setGroupId(RandomTestUtil.nextLong());

		supportMessage.setCompanyId(RandomTestUtil.nextLong());

		supportMessage.setUserId(RandomTestUtil.nextLong());

		supportMessage.setUserName(RandomTestUtil.randomString());

		supportMessage.setCreateDate(RandomTestUtil.nextDate());

		supportMessage.setModifiedDate(RandomTestUtil.nextDate());

		supportMessage.setSenderName(RandomTestUtil.randomString());

		supportMessage.setSenderEmail(RandomTestUtil.randomString());

		supportMessage.setSubject(RandomTestUtil.randomString());

		supportMessage.setBody(RandomTestUtil.randomString());

		supportMessage.setReplyText(RandomTestUtil.randomString());

		supportMessage.setIsReplied(RandomTestUtil.randomBoolean());

		_supportMessages.add(_persistence.update(supportMessage));

		return supportMessage;
	}

	private List<SupportMessage> _supportMessages =
		new ArrayList<SupportMessage>();
	private SupportMessagePersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;

}