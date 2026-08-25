<%@ include file="/init.jsp" %>
<%@ page import="contacts.enterprise.manager.service.SupportMessageLocalServiceUtil" %>
<%@ page import="contacts.enterprise.manager.model.SupportMessage" %>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>

<%
// SICUREZZA
boolean hasAdminRights = themeDisplay.getPermissionChecker().isGroupAdmin(themeDisplay.getScopeGroupId()) || 
                         themeDisplay.getPermissionChecker().isCompanyAdmin(themeDisplay.getCompanyId());
%>

<% if (!hasAdminRights) { %>
    <div class="alert alert-danger mt-4">
        <h4>Accesso Negato</h4>
        <p>Questa è un'area riservata ai dipendenti dell'azienda per la gestione dei ticket.</p>
    </div>
<% } else { %>
    
    <%-- TUTTO IL RESTO DEVE STARE QUI DENTRO L'ELSE --%>
    <%
    long messageId = ParamUtil.getLong(request, "messageId");
    SupportMessage msg = SupportMessageLocalServiceUtil.getSupportMessage(messageId);
    %>

    <portlet:renderURL var="backURL">
        <portlet:param name="mvcPath" value="/manage_support/view.jsp" />
    </portlet:renderURL>

    <portlet:actionURL name="/support/reply_message" var="replyMessageURL" />

    <liferay-ui:header backURL="<%= backURL %>" title="Rispondi alla Richiesta" />

    <div class="container mt-4">
        <div class="card shadow-sm mb-4 border-primary">
            <div class="card-body">
                <h5 class="card-title text-primary"><i class="icon-user"></i> Dettagli Richiesta</h5>
                <p class="mb-1"><strong>Mittente:</strong> <%= msg.getSenderName() %> (<a href="mailto:<%= msg.getSenderEmail() %>"><%= msg.getSenderEmail() %></a>)</p>
                <p class="mb-1"><strong>Oggetto:</strong> <%= msg.getSubject() %></p>
                <hr>
                <p class="mb-0"><strong>Testo del messaggio:</strong><br> <%= msg.getBody() %></p>
            </div>
        </div>

        <div class="card shadow-sm">
            <div class="card-body">
                <aui:form action="<%= replyMessageURL %>" method="post" onSubmit="return confirm('Sei sicuro di voler inviare la risposta? Il messaggio partira immediatamente al cliente.');">
                    <aui:input name="messageId" type="hidden" value="<%= msg.getMessageId() %>" />
                    <aui:input name="replyText" label="Scrivi la tua risposta aziendale:" type="textarea" required="true" rows="6" value="<%= msg.getReplyText() %>" />
                    <div class="mt-3">
                        <aui:button type="submit" value="Salva Risposta" cssClass="btn btn-success" />
                        <aui:button href="<%= backURL %>" type="cancel" value="Annulla" cssClass="btn btn-secondary" />
                    </div>
                </aui:form>
            </div>
        </div>
    </div>

<% } %><%-- CHIUSURA DELL'ELSE ALLA FINE DEL FILE --%>