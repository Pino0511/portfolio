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

package contacts.enterprise.manager.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import contacts.enterprise.manager.model.SupportMessage;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing SupportMessage in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class SupportMessageCacheModel
	implements CacheModel<SupportMessage>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof SupportMessageCacheModel)) {
			return false;
		}

		SupportMessageCacheModel supportMessageCacheModel =
			(SupportMessageCacheModel)object;

		if (messageId == supportMessageCacheModel.messageId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, messageId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(29);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", messageId=");
		sb.append(messageId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", senderName=");
		sb.append(senderName);
		sb.append(", senderEmail=");
		sb.append(senderEmail);
		sb.append(", subject=");
		sb.append(subject);
		sb.append(", body=");
		sb.append(body);
		sb.append(", replyText=");
		sb.append(replyText);
		sb.append(", isReplied=");
		sb.append(isReplied);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public SupportMessage toEntityModel() {
		SupportMessageImpl supportMessageImpl = new SupportMessageImpl();

		if (uuid == null) {
			supportMessageImpl.setUuid("");
		}
		else {
			supportMessageImpl.setUuid(uuid);
		}

		supportMessageImpl.setMessageId(messageId);
		supportMessageImpl.setGroupId(groupId);
		supportMessageImpl.setCompanyId(companyId);
		supportMessageImpl.setUserId(userId);

		if (userName == null) {
			supportMessageImpl.setUserName("");
		}
		else {
			supportMessageImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			supportMessageImpl.setCreateDate(null);
		}
		else {
			supportMessageImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			supportMessageImpl.setModifiedDate(null);
		}
		else {
			supportMessageImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (senderName == null) {
			supportMessageImpl.setSenderName("");
		}
		else {
			supportMessageImpl.setSenderName(senderName);
		}

		if (senderEmail == null) {
			supportMessageImpl.setSenderEmail("");
		}
		else {
			supportMessageImpl.setSenderEmail(senderEmail);
		}

		if (subject == null) {
			supportMessageImpl.setSubject("");
		}
		else {
			supportMessageImpl.setSubject(subject);
		}

		if (body == null) {
			supportMessageImpl.setBody("");
		}
		else {
			supportMessageImpl.setBody(body);
		}

		if (replyText == null) {
			supportMessageImpl.setReplyText("");
		}
		else {
			supportMessageImpl.setReplyText(replyText);
		}

		supportMessageImpl.setIsReplied(isReplied);

		supportMessageImpl.resetOriginalValues();

		return supportMessageImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		messageId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		senderName = objectInput.readUTF();
		senderEmail = objectInput.readUTF();
		subject = objectInput.readUTF();
		body = objectInput.readUTF();
		replyText = objectInput.readUTF();

		isReplied = objectInput.readBoolean();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(messageId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (senderName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(senderName);
		}

		if (senderEmail == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(senderEmail);
		}

		if (subject == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(subject);
		}

		if (body == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(body);
		}

		if (replyText == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(replyText);
		}

		objectOutput.writeBoolean(isReplied);
	}

	public String uuid;
	public long messageId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String senderName;
	public String senderEmail;
	public String subject;
	public String body;
	public String replyText;
	public boolean isReplied;

}