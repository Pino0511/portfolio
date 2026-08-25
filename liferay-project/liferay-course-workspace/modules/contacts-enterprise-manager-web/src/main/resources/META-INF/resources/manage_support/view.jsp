<%@ include file="/init.jsp" %>
<%@ page import="contacts.enterprise.manager.service.SupportMessageLocalServiceUtil" %>
<%@ page import="contacts.enterprise.manager.model.SupportMessage" %>

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

    <%-- TABELLA DENTRO L'ELSE --%>
    <div class="container mt-4">
        <div class="card shadow-sm">
            <div class="card-body">
                <liferay-ui:success key="replySaved" message="Risposta salvata con successo!" />
                <h2 class="card-title text-primary"><i class="icon-briefcase"></i> Gestione Messaggi di Supporto</h2>
                <p class="text-muted">Lista delle richieste inviate dagli utenti.</p>

                <liferay-ui:search-container 
                    total="<%= SupportMessageLocalServiceUtil.getSupportMessagesCount() %>" 
                    emptyResultsMessage="Nessun messaggio ricevuto finora. Ottimo lavoro!">
                    
                    <liferay-ui:search-container-results 
                        results="<%= SupportMessageLocalServiceUtil.getSupportMessages(searchContainer.getStart(), searchContainer.getEnd()) %>" />

                    <liferay-ui:search-container-row className="contacts.enterprise.manager.model.SupportMessage" modelVar="msg">
                        <liferay-ui:search-container-column-text property="createDate" name="Data Ricezione" />
                        <liferay-ui:search-container-column-text property="senderName" name="Mittente" />
                        <liferay-ui:search-container-column-text property="subject" name="Oggetto" />
                        
                        <liferay-ui:search-container-column-text name="Stato">
                            <% if(msg.getIsReplied()) { %>
                                <span class="badge badge-success">Risposto</span>
                            <% } else { %>
                                <span class="badge badge-warning">Da leggere</span>
                            <% } %>
                        </liferay-ui:search-container-column-text>
                        
                        <liferay-ui:search-container-column-text name="Azioni">
                            <portlet:renderURL var="replyURL">
                                <portlet:param name="mvcPath" value="/manage_support/reply.jsp" />
                                <portlet:param name="messageId" value="<%= String.valueOf(msg.getMessageId()) %>" />
                            </portlet:renderURL>
                            <% if(msg.getIsReplied()) { %>
                            	<a href="<%= replyURL %>" class="btn btn-sm btn-secondary">Modifica Risposta</a>
                            <% } else { %>
                            	<a href="<%= replyURL %>" class="btn btn-sm btn-primary">Leggi e Rispondi</a>
                            <% } %>
                        </liferay-ui:search-container-column-text>
                    </liferay-ui:search-container-row>

                    <liferay-ui:search-iterator />
                </liferay-ui:search-container>
            </div>
        </div>
    </div>

<% } %><%-- CHIUSURA DELL'ELSE ALLA FINE DELLA TABELLA --%>