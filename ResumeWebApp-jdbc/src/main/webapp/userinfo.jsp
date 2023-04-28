<%@ page import="entity.User" %>
<%@ page import="dao.inter.NationalityDaoInter" %>
<%@ page import="main.Context" %>
<%@ page import="entity.Nationality" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>userinfo</title>
    <link rel="stylesheet" href="assets/css/users.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
<%
    User u = (User) request.getAttribute("User");
    NationalityDaoInter nationalityDao = Context.instanceNationalityDao();
    List<Nationality> list = nationalityDao.getAllCountryName();
%>

<div class="Conteiner myConteiner">
    <div>
        <form action="userinfo" method="POST">
            <div>
                <input name="id" type="hidden" value="<%=u.getId()%>">
                <label for="name"> name :</label>
                <input name="name"  class="btn btn-outline-secondary" type="text" value="<%=u.getName()%>"/>
            </div>
            <br>
            <div>
                <label for="surname"> surname :</label>
                <input name="surname" class="btn btn-outline-secondary" type="text" value="<%=u.getSurname()%>"/>
            </div>
            <br>
            <div>
                <label for="email"> E-mail :</label>
                <input name="email" class="btn btn-outline-secondary" type="email" value="<%=u.getEmail()%>">
            </div>
            <br>
            <div>
                <label for="phone"> Phone Number :</label>
                <input name="phone" class="btn btn-outline-secondary" type="tel" value="<%=u.getPhone()%>">
            </div>
            <br>
            <div>
                <label for="description"> Profile Description : </label>
                <textarea name="description" class="btn btn-outline-secondary" rows="3" cols="40"><%=u.getProfileDescription()%></textarea>
            </div>
            <br>
            <div>
                <label for="address"> Adress :</label>
                <input name="address" class="btn btn-outline-secondary" type="text" value="<%=u.getAddress()%>">
            </div>
            <br>
            <div>
                <label for="birthdate"> BirthDate :</label>
                <input name="birthdate" class="btn btn-outline-secondary" type="date" value="<%=u.getBirthdate()%>">
            </div>
            <br>
            <div class="input-group">
                <label class="input-group-text" for="birthplace"> Country :</label>
                <select class="custom-select" name="slctCountryName">
                    <option><%=u.getBirthplace().getCountryName()%>
                    </option>
                    <%
                        for (Nationality n : list) {
                    %>
                    <option><%=n.getCountryName()%>
                    </option>
                    <%
                        }
                    %>
                </select>
            </div>
            <br>
            <div class="input-group">
                <label class="input-group-text" for="nationality"> Nationality :</label>
                <select class="custom-select" name="slctNationality">
                    <option><%=u.getNationality().getNationalityName()%>
                    </option>
                    <%
                        int i = 1;
                        for (Nationality n : list) {
                    %>
                    <option value=<%=i%>><%=n.getNationalityName()%>
                    </option>
                    <%
                            i++;
                        }
                    %>
                </select>
            </div>

            <br>
            <input class="btn btn-primary" type="submit" name="save" value="Save"/>
            <input type="hidden" name="action" value="update">
        </form>
    </div>
</div>


</body>
</html>
