create index IX_1C5F6414 on CEM_Contact (groupId, email[$COLUMN_LENGTH:75$]);

create index IX_350A86D on CEM_SupportMessage (isReplied);
create index IX_17C0BAEC on CEM_SupportMessage (userId);
create index IX_9A2FAE26 on CEM_SupportMessage (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_EBF98628 on CEM_SupportMessage (uuid_[$COLUMN_LENGTH:75$], groupId);