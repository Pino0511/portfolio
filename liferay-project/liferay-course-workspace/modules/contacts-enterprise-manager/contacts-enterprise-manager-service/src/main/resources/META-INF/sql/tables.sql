create table CEM_Contact (
	contactId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	firstName VARCHAR(75) null,
	lastName VARCHAR(75) null,
	email VARCHAR(75) null,
	phoneNumber VARCHAR(75) null,
	company VARCHAR(75) null,
	jobTitle VARCHAR(75) null,
	notes VARCHAR(75) null,
	active_ BOOLEAN
);

create table CEM_SupportMessage (
	uuid_ VARCHAR(75) null,
	messageId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	senderName VARCHAR(75) null,
	senderEmail VARCHAR(75) null,
	subject VARCHAR(75) null,
	body VARCHAR(75) null,
	replyText VARCHAR(75) null,
	isReplied BOOLEAN
);