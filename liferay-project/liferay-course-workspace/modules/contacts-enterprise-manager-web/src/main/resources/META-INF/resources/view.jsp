<%@ include file="/init.jsp" %>
<%@ page import="java.util.List" %>
<%@ page import="contacts.enterprise.manager.model.Contact" %>
<%@ page import="javax.portlet.PortletURL" %>
<%@ page import="com.liferay.portal.kernel.util.Validator" %>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@ page import="com.liferay.portal.kernel.util.OrderByComparator" %>
<%@ page import="com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil" %>
<%@ page import="contacts.enterprise.manager.service.ContactLocalServiceUtil" %>

<%
// Recupero parametri di ricerca
String searchFirstName = ParamUtil.getString(request, "searchFirstName");
String searchLastName = ParamUtil.getString(request, "searchLastName");
String searchCompany = ParamUtil.getString(request, "searchCompany");
String searchJobTitle = ParamUtil.getString(request, "searchJobTitle");

// Gestione Ordinamento
String orderByCol = ParamUtil.getString(request, "orderByCol", "lastName"); // Default: ordina per cognome
String orderByType = ParamUtil.getString(request, "orderByType", "asc");
boolean orderByAsc = orderByType.equals("asc");
OrderByComparator<Contact> orderByComparator = OrderByComparatorFactoryUtil.create("Contact", orderByCol, orderByAsc);

// Creazione URL per la paginazione e la ricerca
PortletURL searchURL = renderResponse.createRenderURL();
searchURL.setParameter("mvcRenderCommandName", "/");
searchURL.setParameter("searchFirstName", searchFirstName);
searchURL.setParameter("searchLastName", searchLastName);
searchURL.setParameter("searchCompany", searchCompany);
searchURL.setParameter("searchJobTitle", searchJobTitle);

// SICUREZZA: Controlla se l'utente è Amministratore (Punto 4)
boolean hasAdminRights = themeDisplay.getPermissionChecker().isGroupAdmin(themeDisplay.getScopeGroupId()) || 
                         themeDisplay.getPermissionChecker().isCompanyAdmin(themeDisplay.getCompanyId());
%>

<% if (!themeDisplay.isSignedIn()) { %>
    
    <div class="alert alert-warning mt-4">
        <h4>Accesso Negato</h4>
        <p>La Lista Contatti è disponibile solo per gli utenti loggati. Effettua il login per continuare.</p>
    </div>

<% } else { %>
    
    <div class="container-fluid-1280">
        <h1>Rubrica Aziendale</h1>
        
        <liferay-ui:success key="contactDeleted" message="Contatto eliminato con successo!" />
        <liferay-ui:success key="contactAdded" message="Contatto salvato con successo!" />
        <liferay-ui:success key="contactUpdated" message="Contatto aggiornato con successo!" />
        <liferay-ui:error key="errorDeletingContact" message="Errore durante l'eliminazione." />
        <liferay-ui:error key="errorSavingContact" message="Errore durante il salvataggio." />

        <%-- Bottoni in alto (Aggiungi e CSV) --%>
        <div class="mb-4 mt-4">
            <%-- Nascondiamo il bottone Aggiungi a chi NON è admin --%>
            <% if (hasAdminRights) { %>
                <portlet:renderURL var="addContactURL">
                    <portlet:param name="mvcRenderCommandName" value="/contact/edit" />
                </portlet:renderURL>
                <a href="<%= addContactURL %>" class="btn btn-primary">Aggiungi Nuovo Contatto</a>
            <% } %>
            
            <%-- L'esportazione CSV la lasciamo visibile a tutti gli utenti loggati --%>
            <portlet:resourceURL var="exportCSVURL" id="/contact/export" />
            <a href="<%= exportCSVURL %>" class="btn btn-success <%= hasAdminRights ? "ml-2" : "" %>">
                <i class="icon-download"></i> Esporta in CSV
            </a>
        </div>
        
        <hr/>

        <h2>Lista Contatti</h2>

        <%-- Form di Ricerca Avanzata --%>
        <div class="container-fluid mt-3 mb-4">
            <aui:form action="<%= searchURL %>" method="post" name="searchForm">
                <div class="row">
                    <div class="col-md-3">
                        <aui:input name="searchFirstName" label="Nome" value="<%= searchFirstName %>" />
                    </div>
                    <div class="col-md-3">
                        <aui:input name="searchLastName" label="Cognome" value="<%= searchLastName %>" />
                    </div>
                    <div class="col-md-3">
                        <aui:input name="searchCompany" label="Azienda" value="<%= searchCompany %>" />
                    </div>
                    <div class="col-md-3">
                        <aui:input name="searchJobTitle" label="Ruolo" value="<%= searchJobTitle %>" />
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12 text-right">
                        <aui:button type="submit" value="Cerca" cssClass="btn btn-primary" />
                        <portlet:renderURL var="resetURL">
                            <portlet:param name="mvcRenderCommandName" value="/" />
                        </portlet:renderURL>
                        <a href="<%= resetURL %>" class="btn btn-secondary">Reset</a>
                    </div>
                </div>
            </aui:form>
        </div>

        <div class="row">
            <div class="col-md-12">
                
                <%-- Search Container con paginazione e ordinamento --%>
                <liferay-ui:search-container 
                    total="<%= ContactLocalServiceUtil.searchAdvancedCount(themeDisplay.getScopeGroupId(), searchFirstName, searchLastName, searchCompany, searchJobTitle) %>"
                    iteratorURL="<%= searchURL %>"
                    orderByCol="<%= orderByCol %>"
                    orderByType="<%= orderByType %>"
                    emptyResultsMessage="Nessun contatto trovato con questi criteri.">

                    <liferay-ui:search-container-results 
                        results="<%= ContactLocalServiceUtil.searchAdvanced(themeDisplay.getScopeGroupId(), searchFirstName, searchLastName, searchCompany, searchJobTitle, searchContainer.getStart(), searchContainer.getEnd(), orderByComparator) %>" 
                    />

                    <liferay-ui:search-container-row className="contacts.enterprise.manager.model.Contact" modelVar="currentContact">
                        
                        <%-- Colonne esplose e separate --%>
                        <liferay-ui:search-container-column-text name="Nome" property="firstName" orderable="true" orderableProperty="firstName" />
                        <liferay-ui:search-container-column-text name="Cognome" property="lastName" orderable="true" orderableProperty="lastName" />
                        <liferay-ui:search-container-column-text name="Azienda" property="company" orderable="true" orderableProperty="company" />
                        <liferay-ui:search-container-column-text name="Ruolo" property="jobTitle" orderable="true" orderableProperty="jobTitle" />
                        <liferay-ui:search-container-column-text name="Email" property="email" orderable="true" orderableProperty="email" />
                        <liferay-ui:search-container-column-text name="Telefono" property="phoneNumber" />
                        <liferay-ui:search-container-column-date property="createDate" name="Data Invio" />
                        
                        <%-- Stato --%>
                        <liferay-ui:search-container-column-text name="Stato" orderable="true" orderableProperty="active">
                            <span class="badge badge-<%= currentContact.getActive() ? "success" : "danger" %>" 
                                  style="font-size: 12px; padding: 4px 7px; display: inline-block; text-align: center;">
                                <%= currentContact.getActive() ? "Attivo" : "Inattivo" %>
                            </span>
                        </liferay-ui:search-container-column-text>
                        
                        <%-- Azioni --%>
                        <liferay-ui:search-container-column-text name="Azioni">
                            <portlet:renderURL var="editURL">
                                <portlet:param name="mvcRenderCommandName" value="/contact/edit" />
                                <portlet:param name="contactId" value="<%= String.valueOf(currentContact.getContactId()) %>" />
                            </portlet:renderURL>
                            
                            <portlet:actionURL name="deleteContact" var="deleteURL">
                                <portlet:param name="contactId" value="<%= String.valueOf(currentContact.getContactId()) %>" />
                            </portlet:actionURL>
                    
                            <portlet:actionURL name="toggleStatus" var="toggleStatusURL">
                                <portlet:param name="contactId" value="<%= String.valueOf(currentContact.getContactId()) %>" />
                            </portlet:actionURL>
                    
                            <portlet:renderURL var="viewContactURL">
                                 <portlet:param name="mvcRenderCommandName" value="/contact/view" />
                                 <portlet:param name="contactId" value="<%= String.valueOf(currentContact.getContactId()) %>" />
                            </portlet:renderURL>
                            
                            <liferay-ui:icon-menu markupView="lexicon">
                                <liferay-ui:icon icon="view" message="Dettagli" url="<%= viewContactURL %>" />
                                
                                <%-- Le icone di Modifica/Stato/Elimina le vedono SOLO gli Admin --%>
                                <% if (hasAdminRights) { %>
                                    <liferay-ui:icon icon="refresh" message="Cambia Stato" url="<%= toggleStatusURL %>" />
                                    <liferay-ui:icon icon="pencil" message="Modifica" url="<%= editURL %>" />
                                    <liferay-ui:icon-delete url="<%= deleteURL %>" message="Elimina" confirmation="Sei sicuro?" />
                                <% } %>
                            </liferay-ui:icon-menu>
                        </liferay-ui:search-container-column-text>

                    </liferay-ui:search-container-row>

                    <liferay-ui:search-iterator />
                </liferay-ui:search-container>
            </div>
        </div>
    </div>
<% } %>