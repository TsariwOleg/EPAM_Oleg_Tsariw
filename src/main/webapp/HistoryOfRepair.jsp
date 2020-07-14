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
         if ( value.hasAttribute("required")){
                  value.removeAttribute("required");
         }
         });
         }
      </script>
   </head>
   <body>
      <c:if test="${access eq '1' || access eq '0'}">
         <c:choose>
            <c:when test="${empty target}">
               <form  method="post" action="/historyOfRepair">
            </c:when>
            <c:otherwise>
            <form  method="post" action="/historyOfRepair?target=${target}&id=${id}">
            </c:otherwise>
         </c:choose>

         <c:if test="${regime eq 'AddNewHistory'}">
         <div class="change">
         <div class="modal-window">
         <div class="changeValues" align="center">
         <c:if test="${target eq 'bus'}">
         <h1>Створити історію ремонту для цього автобуса</h1>
         </c:if>
         <c:if test="${target eq 'carMechanic'}">
         <h1>Створити історію ремонту для цієї особи</h1>
         </c:if>
         <table >
         <tr>
         <td>Поломка</td>
         <td><input type="text" name="newMalfunction" required></td>
         </tr>
         <tr>
         <td>Ціна ремонту</td>
         <td><input type="number" name="newCostOfRepair" required></td>
         </tr>
         <tr>
         <td>Нотатки</td>
         <td> <textarea class="textarea" rows="4" name="newNote"  required></textarea></td>
         </tr>
         <c:if test="${target ne 'carMechanic'}">
         <tr>
         <td>Механік</td>
         <td><select name="newCarMechanics"  required>
         <option value="" selected disabled hidden>Виберіть механіка</option>
         <c:forEach items="${carmechanics}" var="carmechanics">
         <option  >${carmechanics.name} ${carmechanics.surname} ${carmechanics.patronymic}</option>
         </c:forEach>
         </select></td>
         </tr>
         </c:if>
         <c:if test="${target ne 'bus'}">
         <tr>
         <td>Автобус</td>
         <td><select name="newBus"  required>
         <option value="" selected disabled hidden>Виберіть автобус</option>
         <c:forEach items="${bus}" var="bus">
         <option  >${bus.busNo}</option>
         </c:forEach>
         </select></td>
         </tr>
         </c:if>
         </table>
         <input type="submit" name="confirmHistory" value="Підтвердити" >
         <input type="submit" value="Скасувати" onClick="removeRequired(this.form)">
         </div>
         </div>
         </div>
         </c:if>
         </form>


         <form  method="post"  onsubmit='redirect();return false;'>
            <c:if test="${regime eq 'DeleteHistory'}">
               <c:choose>
                  <c:when test="${empty deleteId}">
                     <div class="deleteField">
                        <h1 >Видалення</h1>
                     </div>
                  </c:when>
                  <c:otherwise>
                     <div class="change">
                        <div class="modal-window">
                           <div class="changeValues" align="center">
                              <h1>Ви дійсно хочете видалити це з бази?</h1>
                              <input type="submit" value="Так" name="deleteHistory">
                              <input type="submit" value="Скасувати" name="cancelDeletinfHistory">
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
               <th>Номер ремонту</th>
               <th>Поломка</th>
               <th>Вартість ремонту</th>
               <th>Нотатки</th>
               <th>ПІБ</th>
               <th>Автобус</th>
            </thead>
            </tr>
            <c:choose>
               <c:when test="${empty history}">
                  <td colspan=6 class="emptyField">
                     <h2>ПУСТО</h2>
                  </td>
               </c:when>
               <c:otherwise>
                  <c:choose>
                     <c:when test="${empty regime}">
                        <c:forEach items="${history}" var="history">
                           <tr>
                              <td>${history.repairedNo}</td>
                              <td>${history.malfunction}</td>
                              <td>${history.costOfRepair}</td>
                              <td>${history.note}</td>
                              <td>${history.NSP}</td>
                              <td>${history.bus}</td>
                           </tr>
                        </c:forEach>
                     </c:when>
                     <c:otherwise>
                        <c:forEach items="${history}" var="history">
                           <c:if test="${access eq '1' || access eq '0'}">
                              <c:choose>
                                 <c:when test="${empty target}">
                                    <tr onclick="location.href='/historyOfRepair?regime=DeleteHistory&deleteId=${history.repairedNo}'">
                                 </c:when>
                                 <c:otherwise>
                                 <tr onclick="location.href='/historyOfRepair?target=${target}&id=${id}&regime=DeleteHistory&deleteId=${history.repairedNo}'">
                                 </c:otherwise>
                              </c:choose>
                           </c:if>
                           <c:if test="${access ne '1' && access ne '0'}">
                           <tr>
                           </c:if>
                           <td>${history.repairedNo}</td>
                           <td>${history.malfunction}</td>
                           <td>${history.costOfRepair}</td>
                           <td>${history.note}</td>
                           <td>${history.NSP}</td>
                           <td>${history.bus}</td>
                           </tr>
                        </c:forEach>
                     </c:otherwise>
                  </c:choose>
               </c:otherwise>
            </c:choose>
         </table>
      </div>
      <c:if test="${access eq '1' || access eq '0'}">
         <div class="tooltip">
            <c:choose>
               <c:when test="${regime eq 'DeleteHistory'}">
                  <c:choose>
                     <c:when test="${empty target}">
                        <img id="close" src="resources/close.png" width=35px onClick="location.href='/historyOfRepair'"/>
                     </c:when>
                     <c:otherwise>
                        <img id="close" src="resources/close.png" width=35px onClick="location.href='/historyOfRepair?target=${target}&id=${id}'"/>
                     </c:otherwise>
                  </c:choose>
               </c:when>
               <c:otherwise>
                  <c:choose>
                     <c:when test="${empty target}">
                        <img class="wrench" src="resources/feature.png" width=35px/>
                        <div class="person create">
                           <img  src="resources/edit.png" width=30px onclick="location.href='/historyOfRepair?regime=AddNewHistory'">
                        </div>
                        <div class="person delete">
                           <img src="resources/trash.png" width=30px onclick="location.href='/historyOfRepair?regime=DeleteHistory'">
                        </div>
                     </c:when>
                     <c:otherwise>
                        <img class="wrench" src="resources/feature.png" width=35px/>
                        <div class="person create">
                           <img  src="resources/edit.png" width=30px onclick="location.href='/historyOfRepair?target=${target}&id=${id}&regime=AddNewHistory'">
                        </div>
                        <div class="person delete">
                           <img src="resources/trash.png" width=30px onclick="location.href='/historyOfRepair?target=${target}&id=${id}&regime=DeleteHistory'">
                        </div>
                     </c:otherwise>
                  </c:choose>
               </c:otherwise>
            </c:choose>
         </div>
      </c:if>
   </body>
</html>