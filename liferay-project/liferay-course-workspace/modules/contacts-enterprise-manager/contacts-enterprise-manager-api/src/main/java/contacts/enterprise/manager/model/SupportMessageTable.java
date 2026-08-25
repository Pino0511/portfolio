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

import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.base.BaseTable;

import java.sql.Types;

import java.util.Date;

/**
 * The table class for the &quot;CEM_SupportMessage&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see SupportMessage
 * @generated
 */
public class SupportMessageTable extends BaseTable<SupportMessageTable> {

	public static final SupportMessageTable INSTANCE =
		new SupportMessageTable();

	public final Column<SupportMessageTable, String> uuid = createColumn(
		"uuid_", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<SupportMessageTable, Long> messageId = createColumn(
		"messageId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<SupportMessageTable, Long> groupId = createColumn(
		"groupId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<SupportMessageTable, Long> companyId = createColumn(
		"companyId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<SupportMessageTable, Long> userId = createColumn(
		"userId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<SupportMessageTable, String> userName = createColumn(
		"userName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<SupportMessageTable, Date> createDate = createColumn(
		"createDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<SupportMessageTable, Date> modifiedDate = createColumn(
		"modifiedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<SupportMessageTable, String> senderName = createColumn(
		"senderName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<SupportMessageTable, String> senderEmail = createColumn(
		"senderEmail", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<SupportMessageTable, String> subject = createColumn(
		"subject", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<SupportMessageTable, String> body = createColumn(
		"body", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<SupportMessageTable, String> replyText = createColumn(
		"replyText", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<SupportMessageTable, Boolean> isReplied = createColumn(
		"isReplied", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);

	private SupportMessageTable() {
		super("CEM_SupportMessage", SupportMessageTable::new);
	}

}