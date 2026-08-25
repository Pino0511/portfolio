<%@ include file="/init.jsp" %>
<%@ page import="contacts.enterprise.manager.model.Contact" %>

<%
    // Recuperiamo il contatto con il nuovo nome per evitare conflitti con Liferay
    Contact currentContact = (Contact) request.getAttribute("currentContact");
%>

<portlet:renderURL var="backURL">
    <portlet:param name="mvcRenderCommandName" value="/" />
</portlet:renderURL>

<div class="container mt-4">
    <a href="<%= backURL %>" class="btn btn-secondary mb-3"><i class="icon-arrow-left"></i> Torna alla Rubrica</a>

    <% if (currentContact != null) { %>
        <div class="card shadow-sm">
            <div class="card-body text-center">
                <i class="icon-user" style="font-size: 80px; color: #0052cc;"></i>
                <h2 class="mt-3"><%= currentContact.getFirstName() %> <%= currentContact.getLastName() %></h2>
                <h4 class="text-muted"><%= currentContact.getJobTitle() %> presso <%= currentContact.getCompany() %></h4>
                
                <hr/>
                
                <div class="row text-left mt-4">
                    <div class="col-md-6">
                        <p><strong><i class="icon-envelope"></i> Email:</strong> <a href="mailto:<%= currentContact.getEmail() %>"><%= currentContact.getEmail() %></a></p>
                        <p><strong><i class="icon-phone"></i> Telefono:</strong> <%= currentContact.getPhoneNumber() != null ? currentContact.getPhoneNumber() : "N/D" %></p>
                    </div>
                    <div class="col-md-6">
                        <p><strong><i class="icon-calendar"></i> Aggiunto il:</strong> <%= currentContact.getCreateDate() %></p>
                        <p><strong><i class="icon-star"></i> Stato:</strong> <%= currentContact.getActive() ? "Attivo" : "Inattivo" %></p>
                    </div>
                </div>

                <div class="text-left mt-3">
                    <strong>Note:</strong>
                    <div class="alert alert-info mt-2">
                        <%= currentContact.getNotes() != null && !currentContact.getNotes().isEmpty() ? currentContact.getNotes() : "Nessuna nota aggiuntiva." %>
                    </div>
                </div>
            </div>
        </div>
    <% } else { %>
        <div class="alert alert-danger">Contatto non trovato.</div>
    <% } %>
</div>