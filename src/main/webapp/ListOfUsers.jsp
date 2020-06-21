<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*" %>
<html>
   <head>
      <link href="Back&Menu.css" rel="stylesheet">
      <link href="Table.css" rel="stylesheet">
      <link href="ModalWindow.css" rel="stylesheet">
      <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js"></script>
      <script>
         function removeRequired(form){
         $.each(form, function(key, value) {
         if ( value.hasAttribute("required")||value.hasAttribute("min")){
                  value.removeAttribute("required");
                value.removeAttribute("min");
         }
         });
         }



      </script>
   </head>
   <body>
      <c:if test="${access eq '0' || access eq '1'}">
         <form  method="post" action="/listofusers">
            <c:if test="${regime eq 'AddUser'}">
               <div class="change">
                  <div class="modal-window">
                     <div class="changeValues" align="center">
                        <table >
                           <tr>
                              <td>Персона</td>
                              <td>
                                 <select name="newPerson"  required>
                                    <option value="" selected disabled hidden>Виберіть особу</option>
                                    <c:forEach items="${staff}" var="staff">
                                       <option  >${staff.name} ${staff.surname} ${staff.patronymic}</option>
                                    </c:forEach>
                                 </select>
                              </td>
                           </tr>
                           <tr>
                              <td>Логін</td>
                              <td> <input type="text" name="newLogin"  required></td>
                           </tr>
                           <tr>
                              <td>Пароль</td>
                              <td><input type="text" name="newPassword"  required></td>
                           </tr>
                        </table>
                        <input type="submit" name="confirmUser" value="Підтвердити" >
                        <input type="submit" value="Скасувати" onClick="removeRequired(this.form)">
                     </div>
                  </div>
               </div>
            </c:if>
         </form>
      </c:if>
      <c:if test="${access eq '0' || access eq '1'}">
         <form  method="post"  onsubmit='redirect();return false;'>
            <c:if test="${regime eq 'DeleteUser'}">
               <c:choose>
                  <c:when test="${empty changeUser}">
                     <div class="deleteField">
                        <h1 >Видалення</h1>
                     </div>
                  </c:when>
                  <c:otherwise>
                     <div class="change">
                        <div class="modal-window">
                           <div class="changeValues" align="center">
                              <h1>Ви дійсно хочете видалити це з бази ?</h1>
                              <input type="submit" value="Так" name="deleteUser">
                              <input type="submit" value="Скасувати" name="cancelDeletingUser">
                           </div>
                        </div>
                     </div>
                  </c:otherwise>
               </c:choose>
            </c:if>
         </form>
      </c:if>
      <c:if test="${access eq '0' || access eq '1' }">
         <form  method="post"  onsubmit='redirect();return false;'>
            <c:if test="${regime eq 'EditUser'}">
               <c:choose>
                  <c:when test="${empty changeCheckUpId}">
                     <div class="deleteField">
                        <h1 >Редагування</h1>
                     </div>
                  </c:when>
                  <c:otherwise>
                     <div class="change">
                        <div class="modal-window">
                           <div class="changeValues" align="center">
                              <table >
                                 <tr>
                                    <td>Новий логін</td>
                                    <td> <input type="text" name="newLogin"  value="${changedSignIn.login}" required></td>
                                 </tr>
                                 <tr>
                                    <td>Новий пароль</td>
                                    <td><input type="text" name="newPassword"  value="${changedSignIn.password}"  required></td>
                                 </tr>
                              </table>
                              <input type="submit" name="confirmEdittingUser" value="Підтвердити" >
                              <input type="submit" value="Скасувати" name="cancelDeletingUser" onClick="removeRequired(this.form)">
                           </div>
                        </div>
                     </div>
                  </c:otherwise>
               </c:choose>
            </c:if>
         </form>
      </c:if>
      <div class="Bar">
         <ul id="main-ul">
            <li class="men" id="one" onclick="location.href='/staff'">Персонал</li>
            <li class="men" id="two" onclick="location.href='/buspark'">Автопарк</li>
            <li class="men" id="three" onclick="location.href='/department'">Відділення</li>
            <c:if test="${empty access}">
               <li class="men" id="four" onclick="location.href='/login'">Ввійти</li>
            </c:if>
            <c:if test="${not empty access}">
               <li class="men" id="four" onclick="location.href='/login?regime=logout'">Вийти</li>
            </c:if>
            <hr id="hr_css">
         </ul>
      </div>
      <div id="div_table">
         <table class="table" id="table">
            <tr>
            <thead>
               <th>ПІБ</th>
               <th>Відділення</th>
               <th>Логін</th>
               <th>Пароль</th>
            </thead>
            </tr>
            <c:choose>
               <c:when test="${empty regime}">
                  <c:forEach items="${listOfUsers}" var="listOfUsers">
                     <tr>
                        <td>${listOfUsers.NSP}</td>
                        <td>${listOfUsers.department}</td>
                        <td>${listOfUsers.login}</td>
                        <td>${listOfUsers.password}</td>
                     </tr>
                  </c:forEach>
               </c:when>
               <c:otherwise>
                  <c:forEach items="${listOfUsers}" var="listOfUsers">
                     <c:if test="${access eq '0' || access eq '1'}">
                        <c:if test="${regime eq 'DeleteUser'}">
                           <tr onclick="location.href='/listofusers?regime=DeleteUser&id=${listOfUsers.id}'">
                        </c:if>
                        <c:if test="${regime eq 'EditUser'}">
                        <tr onclick="location.href='/listofusers?regime=EditUser&id=${listOfUsers.id}'">
                        </c:if>
                     </c:if>
                     <c:if test="${access ne '0' && access ne '1'}">
                     <tr onclick="location.href='/listofusers'">
                     </c:if>
                     <td>${listOfUsers.NSP}</td>
                     <td>${listOfUsers.department}</td>
                     <td>${listOfUsers.login}</td>
                     <td>${listOfUsers.password}</td>
                     </tr>
                  </c:forEach>
               </c:otherwise>
            </c:choose>
         </table>
      </div>
      <c:if test="${access eq '0' || access eq '1'}">
         <div class="tooltip">
            <c:choose>
               <c:when test="${regime eq 'DeleteUser' || regime eq 'EditUser'}">
                  <img id="close" src="resources/close.png" width=35px onClick="location.href='/listofusers'"/>
               </c:when>
               <c:otherwise>
                  <img class="wrench" src="resources/feature.png" width=35px/>
                  <div class="person create">
                     <img  src="resources/edit.png" width=30px onclick="location.href='/listofusers?regime=AddUser'">
                  </div>
                  <div class="person delete">
                     <img src="resources/trash.png" width=30px onclick="location.href='/listofusers?regime=DeleteUser'">
                  </div>
                  <div class="person edit">
                     <img src="resources/fix.png" width=30px onclick="location.href='/listofusers?regime=EditUser'">
                  </div>
               </c:otherwise>
            </c:choose>
         </div>
      </c:if>
   </body>
   </head>