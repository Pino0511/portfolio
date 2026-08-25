<%@ include file="/init.jsp" %>

<%-- URL per inviare i dati al Java (il tuo Controller) --%>
<portlet:actionURL name="/support/add_message" var="addMessageURL" />

<%-- URL per il bottone Annulla: riporta alla lista principale --%>
<portlet:renderURL var="backURL">
    <portlet:param name="mvcPath" value="/support_user/view.jsp" />
</portlet:renderURL>

<%-- Aggiunto il .toString() per evitare crash di conversione --%>
<liferay-ui:header backURL="<%= backURL.toString() %>" title="Invia una nuova richiesta" />

<div class="container mt-4">
    <div class="card shadow-sm mb-4 border-primary">
        <div class="card-body">
            <h2 class="card-title text-primary"><i class="icon-envelope"></i> Compila il Ticket</h2>
            <p class="text-muted">Inserisci i tuoi dati e il motivo della richiesta.</p>

            <aui:form action="<%= addMessageURL.toString() %>" method="post" name="supportForm">
                
                <%-- Ripristiniamo la logica: chiediamo Nome/Email solo se NON è loggato --%>
                <% if (!themeDisplay.isSignedIn()) { %>
                    <div class="row">
                        <div class="col-md-6">
                            <aui:input name="senderName" label="Il tuo Nome" required="true" />
                        </div>
                        <div class="col-md-6">
                            <aui:input name="senderEmail" label="La tua Email" required="true" type="email" />
                        </div>
                    </div>
                <% } else { %>
                    <div class="alert alert-secondary">
                        Stai inviando questo ticket dall'account: <strong><%= themeDisplay.getUser().getFullName() %></strong>
                    </div>
                <% } %>

                <aui:input name="subject" label="Oggetto della richiesta" required="true" />
                <aui:input name="body" label="Testo del messaggio" type="textarea" required="true" rows="5" />

                <div class="mt-4">
                    <aui:button type="submit" value="Invia Messaggio" cssClass="btn btn-primary" />
                    <%-- Aggiunto il .toString() anche qui --%>
                    <aui:button href="<%= backURL.toString() %>" type="cancel" value="Annulla" cssClass="btn btn-secondary" />
                </div>
            </aui:form>
        </div>
    </div>
</div>