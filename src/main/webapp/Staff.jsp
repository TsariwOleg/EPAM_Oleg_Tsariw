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
      <c:if test="${access eq '1' || access eq '0'}">
         <form  method="post" action="/staff">
            <c:if test="${regime eq 'AddPerson'}">
               <div class="change">
                  <div class="modal-window">
                     <div class="changeValues" align="center">
                        <table >
                           <tr>
                              <td>Ім`я</td>
                              <td><input type="text" name="newName" required></td>
                           </tr>
                           <tr>
                              <td>Прізвище</td>
                              <td><input type="text" name="newSurname" required></td>
                           </tr>
                           <tr>
                              <td>По-батькові</td>
                              <td> <input type="text" name="newPatronymic"  required></td>
                           </tr>
                           <tr>
                              <td>Вік</td>
                              <td><input min="16" max="70" type="number" name="newAge"  required></td>
                           </tr>
                           <tr>
                              <td>Відділення</td>
                              <td>
                                 <select name="newDepartment"  required>
                                    <option value="" selected disabled hidden>Виберіть відділення</option>
                                    <c:forEach items="${departments}" var="department">
                                       <option  >${department.department}</option>
                                    </c:forEach>
                                 </select>
                              </td>
                           </tr>
                        </table>
                        <input type="submit" name="confirmPerson" value="Підтвердити" >
                        <input type="submit" value="Скасувати" onClick="removeRequired(this.form)">
                     </div>
                  </div>
               </div>
            </c:if>
         </form>
      </c:if>
      <c:if test="${access eq '1' || access eq '0'}">
         <form  method="post"  onsubmit='redirect();return false;'>
            <c:if test="${regime eq 'DeletePerson'}">
               <c:choose>
                  <c:when test="${empty deletePersonId}">
                     <div class="deleteField">
                        <h1 >Видалення</h1>
                     </div>
                  </c:when>
                  <c:otherwise>
                     <div class="change">
                        <div class="modal-window">
                           <div class="changeValues" align="center">
                              <h1>Ви дійсно хочете видалити цю особу з бази?</h1>
                              <input type="submit" value="Так" name="deletePerson">
                              <input type="submit" value="Скасувати" name="cancelDeletingPerson">
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
               <th>Ім`я</th>
               <th>Прізвище</th>
               <th>По-батькові</th>
               <th>Вік</th>
               <th>Відділення</th>
            </thead>
            </tr>
            <c:choose>
               <c:when test="${empty regime}">
                  <c:forEach items="${staff}" var="staffjsp">
                     <tr onclick="location.href='/person?id=${staffjsp.id}'">
                        <td>${staffjsp.name}</td>
                        <td>${staffjsp.surname}</td>
                        <td>${staffjsp.patronymic}</td>
                        <td>${staffjsp.age}</td>
                        <td>${staffjsp.department}</td>
                     </tr>
                  </c:forEach>
               </c:when>
               <c:otherwise>
                  <c:forEach items="${staff}" var="staffjsp">
                     <c:if test="${access eq '1' || access eq '0'}">
                        <tr onclick="location.href='/staff?regime=DeletePerson&id=${staffjsp.id}'">
                     </c:if>
                     <c:if test="${access ne '1' && access ne '0'}">
                     <tr onclick="location.href='/person?id=${staffjsp.id}'">
                     </c:if>
                     <td>${staffjsp.name}</td>
                     <td>${staffjsp.surname}</td>
                     <td>${staffjsp.patronymic}</td>
                     <td>${staffjsp.age}</td>
                     <td>${staffjsp.department}</td>
                     </tr>
                  </c:forEach>
               </c:otherwise>
            </c:choose>
         </table>
      </div>
      <c:if test="${access eq '1' || access eq '0'}">
         <div class="tooltip">
            <c:choose>
               <c:when test="${regime eq 'DeletePerson'}">
                  <img id="close" src="resources/close.png" width=35px onClick="location.href='/staff'"/>
               </c:when>
               <c:otherwise>
                  <img class="wrench" src="resources/feature.png" width=35px/>
                  <div class="person create">
                     <img  src="resources/edit.png" width=30px onclick="location.href='/staff?regime=AddPerson'">
                  </div>
                  <div class="person delete">
                     <img src="resources/trash.png" width=30px onclick="location.href='/staff?regime=DeletePerson'">
                  </div>
               </c:otherwise>
            </c:choose>
         </div>
      </c:if>
   </body>
</html>