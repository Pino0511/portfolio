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

package contacts.enterprise.manager.model;

import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link SupportMessage}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SupportMessage
 * @generated
 */
public class SupportMessageWrapper
	extends BaseModelWrapper<SupportMessage>
	implements ModelWrapper<SupportMessage>, SupportMessage {

	public SupportMessageWrapper(SupportMessage supportMessage) {
		super(supportMessage);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("messageId", getMessageId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("senderName", getSenderName());
		attributes.put("senderEmail", getSenderEmail());
		attributes.put("subject", getSubject());
		attributes.put("body", getBody());
		attributes.put("replyText", getReplyText());
		attributes.put("isReplied", isIsReplied());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long messageId = (Long)attributes.get("messageId");

		if (messageId != null) {
			setMessageId(messageId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String senderName = (String)attributes.get("senderName");

		if (senderName != null) {
			setSenderName(senderName);
		}

		String senderEmail = (String)attributes.get("senderEmail");

		if (senderEmail != null) {
			setSenderEmail(senderEmail);
		}

		String subject = (String)attributes.get("subject");

		if (subject != null) {
			setSubject(subject);
		}

		String body = (String)attributes.get("body");

		if (body != null) {
			setBody(body);
		}

		String replyText = (String)attributes.get("replyText");

		if (replyText != null) {
			setReplyText(replyText);
		}

		Boolean isReplied = (Boolean)attributes.get("isReplied");

		if (isReplied != null) {
			setIsReplied(isReplied);
		}
	}

	@Override
	public SupportMessage cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the body of this support message.
	 *
	 * @return the body of this support message
	 */
	@Override
	public String getBody() {
		return model.getBody();
	}

	/**
	 * Returns the company ID of this support message.
	 *
	 * @return the company ID of this support message
	 */
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	/**
	 * Returns the create date of this support message.
	 *
	 * @return the create date of this support message
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the group ID of this support message.
	 *
	 * @return the group ID of this support message
	 */
	@Override
	public long getGroupId() {
		return model.getGroupId();
	}

	/**
	 * Returns the is replied of this support message.
	 *
	 * @return the is replied of this support message
	 */
	@Override
	public boolean getIsReplied() {
		return model.getIsReplied();
	}

	/**
	 * Returns the message ID of this support message.
	 *
	 * @return the message ID of this support message
	 */
	@Override
	public long getMessageId() {
		return model.getMessageId();
	}

	/**
	 * Returns the modified date of this support message.
	 *
	 * @return the modified date of this support message
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the primary key of this support message.
	 *
	 * @return the primary key of this support message
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the reply text of this support message.
	 *
	 * @return the reply text of this support message
	 */
	@Override
	public String getReplyText() {
		return model.getReplyText();
	}

	/**
	 * Returns the sender email of this support message.
	 *
	 * @return the sender email of this support message
	 */
	@Override
	public String getSenderEmail() {
		return model.getSenderEmail();
	}

	/**
	 * Returns the sender name of this support message.
	 *
	 * @return the sender name of this support message
	 */
	@Override
	public String getSenderName() {
		return model.getSenderName();
	}

	/**
	 * Returns the subject of this support message.
	 *
	 * @return the subject of this support message
	 */
	@Override
	public String getSubject() {
		return model.getSubject();
	}

	/**
	 * Returns the user ID of this support message.
	 *
	 * @return the user ID of this support message
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user name of this support message.
	 *
	 * @return the user name of this support message
	 */
	@Override
	public String getUserName() {
		return model.getUserName();
	}

	/**
	 * Returns the user uuid of this support message.
	 *
	 * @return the user uuid of this support message
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	/**
	 * Returns the uuid of this support message.
	 *
	 * @return the uuid of this support message
	 */
	@Override
	public String getUuid() {
		return model.getUuid();
	}

	/**
	 * Returns <code>true</code> if this support message is is replied.
	 *
	 * @return <code>true</code> if this support message is is replied; <code>false</code> otherwise
	 */
	@Override
	public boolean isIsReplied() {
		return model.isIsReplied();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the body of this support message.
	 *
	 * @param body the body of this support message
	 */
	@Override
	public void setBody(String body) {
		model.setBody(body);
	}

	/**
	 * Sets the company ID of this support message.
	 *
	 * @param companyId the company ID of this support message
	 */
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	 * Sets the create date of this support message.
	 *
	 * @param createDate the create date of this support message
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the group ID of this support message.
	 *
	 * @param groupId the group ID of this support message
	 */
	@Override
	public void setGroupId(long groupId) {
		model.setGroupId(groupId);
	}

	/**
	 * Sets whether this support message is is replied.
	 *
	 * @param isReplied the is replied of this support message
	 */
	@Override
	public void setIsReplied(boolean isReplied) {
		model.setIsReplied(isReplied);
	}

	/**
	 * Sets the message ID of this support message.
	 *
	 * @param messageId the message ID of this support message
	 */
	@Override
	public void setMessageId(long messageId) {
		model.setMessageId(messageId);
	}

	/**
	 * Sets the modified date of this support message.
	 *
	 * @param modifiedDate the modified date of this support message
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the primary key of this support message.
	 *
	 * @param primaryKey the primary key of this support message
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the reply text of this support message.
	 *
	 * @param replyText the reply text of this support message
	 */
	@Override
	public void setReplyText(String replyText) {
		model.setReplyText(replyText);
	}

	/**
	 * Sets the sender email of this support message.
	 *
	 * @param senderEmail the sender email of this support message
	 */
	@Override
	public void setSenderEmail(String senderEmail) {
		model.setSenderEmail(senderEmail);
	}

	/**
	 * Sets the sender name of this support message.
	 *
	 * @param senderName the sender name of this support message
	 */
	@Override
	public void setSenderName(String senderName) {
		model.setSenderName(senderName);
	}

	/**
	 * Sets the subject of this support message.
	 *
	 * @param subject the subject of this support message
	 */
	@Override
	public void setSubject(String subject) {
		model.setSubject(subject);
	}

	/**
	 * Sets the user ID of this support message.
	 *
	 * @param userId the user ID of this support message
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user name of this support message.
	 *
	 * @param userName the user name of this support message
	 */
	@Override
	public void setUserName(String userName) {
		model.setUserName(userName);
	}

	/**
	 * Sets the user uuid of this support message.
	 *
	 * @param userUuid the user uuid of this support message
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	/**
	 * Sets the uuid of this support message.
	 *
	 * @param uuid the uuid of this support message
	 */
	@Override
	public void setUuid(String uuid) {
		model.setUuid(uuid);
	}

	@Override
	public StagedModelType getStagedModelType() {
		return model.getStagedModelType();
	}

	@Override
	protected SupportMessageWrapper wrap(SupportMessage supportMessage) {
		return new SupportMessageWrapper(supportMessage);
	}

}