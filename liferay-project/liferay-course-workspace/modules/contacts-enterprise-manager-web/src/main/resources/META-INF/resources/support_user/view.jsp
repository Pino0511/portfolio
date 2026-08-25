<%@ include file="/init.jsp" %>
<%@ page import="contacts.enterprise.manager.service.SupportMessageLocalServiceUtil" %>
<%@ page import="contacts.enterprise.manager.model.SupportMessage" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>

<%-- 1. URL per andare alla nuova pagina di creazione ticket --%>
<portlet:renderURL var="createTicketURL">
    <portlet:param name="mvcPath" value="/support_user/create_ticket.jsp" />
</portlet:renderURL>

<div class="container mt-4">
    <%-- Messaggio di successo che apparirà quando si torna qui dopo aver inviato --%>
    <liferay-ui:success key="messageAdded" message="Il tuo messaggio è stato inviato con successo! Ti risponderemo via email." />

    <%-- INTESTAZIONE E BOTTONE --%>
    <div class="row mb-4">
        <div class="col-md-12 d-flex justify-content-between align-items-center">
            <h2 class="text-primary"><i class="icon-headphones"></i> Supporto Clienti</h2>
            <aui:button href="<%= createTicketURL %>" value="Crea Nuovo Ticket" cssClass="btn btn-primary" />
        </div>
    </div>

    <%-- TABELLA I MIEI TICKET (Visibile SOLO ai loggati) --%>
    <% if (themeDisplay.isSignedIn()) { 
        
        // RECUPERO E FILTRO MANUALE DEI TICKET
        List<SupportMessage> allMessages = SupportMessageLocalServiceUtil.getSupportMessages(-1, -1);
        List<SupportMessage> userMessages = new ArrayList<SupportMessage>();
        
        for(SupportMessage m : allMessages) {
            if(m.getUserId() == themeDisplay.getUserId()) {
                userMessages.add(m);
            }
        }
    %>
        <div class="card shadow-sm border-info">
            <div class="card-body">
                <h4 class="card-title text-info"><i class="icon-list"></i> I Miei Ticket</h4>
                <p class="text-muted">Storico delle richieste inviate dal tuo account.</p>

                <liferay-ui:search-container 
                    total="<%= userMessages.size() %>" 
                    emptyResultsMessage="Non hai ancora inviato nessun ticket. Clicca su 'Crea Nuovo Ticket' per contattarci.">
                    
                    <liferay-ui:search-container-results 
                        results="<%= com.liferay.portal.kernel.util.ListUtil.subList(userMessages, searchContainer.getStart(), searchContainer.getEnd()) %>" 
                    />
                    
                    <liferay-ui:search-container-row className="contacts.enterprise.manager.model.SupportMessage" modelVar="msg">
                        
                        <%-- Usiamo la chicca dello shorten per l'oggetto! --%>
                        <liferay-ui:search-container-column-text name="Oggetto">
                            <%= com.liferay.portal.kernel.util.StringUtil.shorten(msg.getSubject(), 40) %>
                        </liferay-ui:search-container-column-text>
                        
                        <liferay-ui:search-container-column-text name="Stato">
                            <% if(msg.getIsReplied()) { %>
                                <span class="badge badge-success">Risposto</span>
                            <% } else { %>
                                <span class="badge badge-warning">In attesa</span>
                            <% } %>
                        </liferay-ui:search-container-column-text>
                        
                    </liferay-ui:search-container-row>

                    <liferay-ui:search-iterator markupView="lexicon" />
                </liferay-ui:search-container>
            </div>
        </div>
    <% } else { %>
        <%-- Messaggio per gli utenti Guest --%>
        <div class="alert alert-info">
            Effettua il login per vedere lo storico dei tuoi ticket, oppure clicca su <strong>"Crea Nuovo Ticket"</strong> per inviare una richiesta come ospite.
        </div>
    <% } %>
</div>