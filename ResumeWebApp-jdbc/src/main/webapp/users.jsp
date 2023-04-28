<%@page import="entity.User" %>
<%@ page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
    <link rel="stylesheet" href="assets/css/users.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script type="text/javascript" src="assets/js/users.js"></script>

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
>
<body>
<%
    List<User> list = (List<User>) request.getAttribute("userList");
%>

<div class="col-12">
    <div class="Conteiner myConteiner">
        <div class="col-4">
            <form action="users" method="GET">
                <div class="form-group">
                    <input type="hidden" name="id" value="">
                    <label for="name">name:</label>
                    <input class="form-control" type="text" name="name" value=""/>
                    <span id="typing"></span>
                </div>
                <br>
                <div class="form-group">
                    <label for="surname">surname:</label>
                    <input class="form-control" type="text" name="surname" value=""/>
                    <br>
                    <input class="btn btn-primary" type="submit" name="search" value="Search">
                </div>
            </form>
        </div>
        <hr/>
        <div>
            <table class="table">
                <thead>
                <tr>
                    <th>Name</th>
                    <th>Surname</th>
                    <th>Country</th>
                    <th>Nationality</th>
                    <th></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <%for (User u : list) {%>
                <tr>
                    <td><%=u.getName()%>
                    </td>
                    <td><%=u.getSurname()%>
                    </td>
                    <td><%=u.getBirthplace().getCountryName()%>
                    </td>
                    <td><%=u.getNationality().getNationalityName()%>
                    </td>
                    <td>
                        <button type="submit" value="delete" class="btn btn-danger"
                                data-toggle="modal" data-target="#exampleModal"
                        onclick="setIdForDelete(<%=u.getId()%>)">
                            <i class="fa-solid fa-trash"></i>
                        </button>
                    </td>
                    <td>
                        <form action="userinfo" method="get">
                            <input type="hidden" name="id" value="<%=u.getId()%>"/>
                            <button type="submit" value="update" class="btn btn-Secondary ">
                                <i class="fa-regular fa-pen-to-square"></i>
                            </button>
                        </form>
                    </td>
                </tr>
                <%}%>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>

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
                <form action="userinfo" method="POST">
                    <input type="hidden" name="id" value="" id="idForDelete" />
                    <input type="hidden" name="action" value="delete"/>
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-danger">Delete</button>
                </form>
            </div>
        </div>
    </div>
</div>
</html>
