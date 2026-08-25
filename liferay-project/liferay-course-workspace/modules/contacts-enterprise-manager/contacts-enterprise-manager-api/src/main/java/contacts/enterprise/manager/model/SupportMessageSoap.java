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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class SupportMessageSoap implements Serializable {

	public static SupportMessageSoap toSoapModel(SupportMessage model) {
		SupportMessageSoap soapModel = new SupportMessageSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setMessageId(model.getMessageId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setSenderName(model.getSenderName());
		soapModel.setSenderEmail(model.getSenderEmail());
		soapModel.setSubject(model.getSubject());
		soapModel.setBody(model.getBody());
		soapModel.setReplyText(model.getReplyText());
		soapModel.setIsReplied(model.isIsReplied());

		return soapModel;
	}

	public static SupportMessageSoap[] toSoapModels(SupportMessage[] models) {
		SupportMessageSoap[] soapModels = new SupportMessageSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static SupportMessageSoap[][] toSoapModels(
		SupportMessage[][] models) {

		SupportMessageSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new SupportMessageSoap[models.length][models[0].length];
		}
		else {
			soapModels = new SupportMessageSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static SupportMessageSoap[] toSoapModels(
		List<SupportMessage> models) {

		List<SupportMessageSoap> soapModels = new ArrayList<SupportMessageSoap>(
			models.size());

		for (SupportMessage model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new SupportMessageSoap[soapModels.size()]);
	}

	public SupportMessageSoap() {
	}

	public long getPrimaryKey() {
		return _messageId;
	}

	public void setPrimaryKey(long pk) {
		setMessageId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getMessageId() {
		return _messageId;
	}

	public void setMessageId(long messageId) {
		_messageId = messageId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getUserName() {
		return _userName;
	}

	public void setUserName(String userName) {
		_userName = userName;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public String getSenderName() {
		return _senderName;
	}

	public void setSenderName(String senderName) {
		_senderName = senderName;
	}

	public String getSenderEmail() {
		return _senderEmail;
	}

	public void setSenderEmail(String senderEmail) {
		_senderEmail = senderEmail;
	}

	public String getSubject() {
		return _subject;
	}

	public void setSubject(String subject) {
		_subject = subject;
	}

	public String getBody() {
		return _body;
	}

	public void setBody(String body) {
		_body = body;
	}

	public String getReplyText() {
		return _replyText;
	}

	public void setReplyText(String replyText) {
		_replyText = replyText;
	}

	public boolean getIsReplied() {
		return _isReplied;
	}

	public boolean isIsReplied() {
		return _isReplied;
	}

	public void setIsReplied(boolean isReplied) {
		_isReplied = isReplied;
	}

	private String _uuid;
	private long _messageId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _senderName;
	private String _senderEmail;
	private String _subject;
	private String _body;
	private String _replyText;
	private boolean _isReplied;

}