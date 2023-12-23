<%--
    Document   : user
    Created on : Jul 28, 2023, 11:21:56 AM
    Author     : Dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
    <link rel="stylesheet" href="assets/css/users.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="assets/js/users.js"></script>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>
</head>
<body>

<style>.error {
    color: red;
}</style>

<div class="container">
    <div class="row">
        <div class="col-4">
            <f:form action="usersm" method="GET" modelAttribute="user">
                <div class="form-group">
                    <label for="name">name:</label>
                    <f:input path="name"
                             placeholder="Enter name"
                             class="form-control"/>
                    <f:errors path="name" cssClass="error"/>
                </div>
                <div class="form-group">
                    <label for="surname">surname:</label>
                    <f:input path="surname"
                             placeholder="Enter surname"
                             class="form-control"/>
                    <f:errors path="surname" cssClass="error"/>
                </div>
                <f:button type="submit"
                          class="btn btn-primary"
                          id="btnsearch">Search
                </f:button>
            </f:form>
        </div>
    </div>
    <div>
        <table class="table">
            <thead>
            <tr>
                <th>name</th>
                <th>surname</th>
                <th>nationality</th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${users}" var="u">
                <tr>
                    <td>${u.name}
                    </td>
                    <td>${u.surname}
                    </td>
                    <td>${u.nationality.name}
                    </td>
                    <td style="width: 5px">
                        <input type="hidden" name="id" value="$(u.id)"/>
                        <input type="hidden" name="action" value="delete"/>
                        <button type="submit" value="delete" class="btn btn-danger" data-toggle="modal"
                                data-target="#exampleModal" onclick="setIdForDelete(${u.id})">
                            <i class="fa-solid fa-trash-can"></i>
                        </button>
                    </td>
                    <td style="width: 5px">
                        <form action="userdetail" method="GET">
                            <input type="hidden" name="id" value="$(u.id)"/>
                            <input type="hidden" name="action" value="update"/>
                            <button type="submit" value="update" class="btn btn-secondary">
                                <i class="fa-solid fa-square-pen"></i>
                            </button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

</div>

<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Delete</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                Are you sure?
            </div>
            <div class="modal-footer">
                <form action="userdetail" method="POST">
                    <input type="hidden" name="id" value="" id="idForDelete"/>
                    <input type="hidden" name="action" value="delete"/>
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <input type="submit" class="btn btn-danger" value="Delete"/>
                </form>
            </div>
        </div>
    </div>
</div>

</body>
</html>
