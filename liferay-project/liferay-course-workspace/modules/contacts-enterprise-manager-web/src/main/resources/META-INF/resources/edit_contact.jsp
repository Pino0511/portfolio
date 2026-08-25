<%@ include file="/init.jsp" %>
<%@ page import="contacts.enterprise.manager.model.Contact" %>

<%
Contact editContact = (Contact) request.getAttribute("editContact");
%>

<portlet:actionURL name="saveContact" var="saveContactURL" />

<portlet:renderURL var="backURL">
    <portlet:param name="mvcRenderCommandName" value="/" />
</portlet:renderURL>

<div class="container-fluid-1280">
    <h1><%= editContact != null ? "Modifica Contatto" : "Aggiungi Nuovo Contatto" %></h1>

    <div class="row">
        <div class="col-md-12">
            <aui:form action="<%= saveContactURL %>" method="post" name="fm">
                <aui:input name="contactId" type="hidden" value="<%= editContact != null ? editContact.getContactId() : 0 %>" />
                
                <aui:fieldset>
	                <aui:input name="firstName" label="Nome" required="true" value="<%= editContact != null ? editContact.getFirstName() : "" %>">
				    	<aui:validator name="required" />
					</aui:input>
					
					<aui:input name="lastName" label="Cognome" required="true" value="<%= editContact != null ? editContact.getLastName() : "" %>">
					    <aui:validator name="required" />
					</aui:input>
					
					<aui:input name="email" label="Email" required="true" type="email" value="<%= editContact != null ? editContact.getEmail() : "" %>">
					    <aui:validator name="required" />
					    <aui:validator name="email" />
					</aui:input>
                    <aui:input name="phoneNumber" label="Telefono" value="<%= editContact != null ? editContact.getPhoneNumber() : "" %>">
					    <aui:validator name="custom" errorMessage="Inserisci un formato valido (solo numeri, spazi o +)">
					        function(val, fieldNode, ruleValue) {
					            return /^[0-9+\s]*$/.test(val);
					        }
					    </aui:validator>
					</aui:input>
                    <aui:input name="company" label="Azienda" value="<%= editContact != null ? editContact.getCompany() : "" %>" />
                    <aui:input name="jobTitle" label="Qualifica" value="<%= editContact != null ? editContact.getJobTitle() : "" %>" />
                    <aui:input name="notes" label="Note" type="textarea" value="<%= editContact != null ? editContact.getNotes() : "" %>" />
                    <aui:input name="active" label="Attivo" type="checkbox" value="true" checked="<%= editContact != null ? editContact.getActive() : true %>" />
                </aui:fieldset>

                <aui:button-row>
                    <aui:button type="submit" value="Salva" cssClass="btn btn-primary" />
                    <aui:button type="cancel" value="Annulla" href="<%= backURL %>" cssClass="btn btn-secondary" />
                </aui:button-row>
            </aui:form>
        </div>
    </div>
</div>