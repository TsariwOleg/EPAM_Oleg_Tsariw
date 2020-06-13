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
         <form  method="post" action="/routes">
            <c:if test="${regime eq 'AddRoute'}">
               <div class="change">
                  <div class="modal-window">
                     <div class="changeValues" align="center">
                        <table >
                           <tr>
                              <td>Маршрут</td>
                              <td><input type="text" name="newRoute" required></td>
                           </tr>
                           <tr>
                              <td>Середня виручка(день)</td>
                              <td><input type="number" name="newProfit" required></td>
                           </tr>
                           <tr>
                              <td>Середній розхід палива(день)</td>
                              <td> <input type="number" name="newFuelConsuption"  required></td>
                           </tr>


                        </table>
                        <input type="submit" name="confirmRoute" value="Підтвердити" >
                        <input type="submit" value="Скасувати" onClick="removeRequired(this.form)">
                     </div>
                  </div>
               </div>
            </c:if>
         </form>
         </c:if>

   <c:if test="${access eq '1' || access eq '0'}">
           <form  method="post"  onsubmit='redirect();return false;'>
                  <c:if test="${regime eq 'DeleteRoute'}">
                     <c:choose>
                        <c:when test="${empty deleteRouteId}">
                           <div class="deleteField">
                              <h1 >Видалення</h1>
                           </div>
                        </c:when>
                        <c:otherwise>
                           <div class="change">
                              <div class="modal-window">
                                 <div class="changeValues" align="center">
                                    <h1>Ви дійсно хочете видалити цю особу з бази?</h1>
                                    <input type="submit" value="Так" name="deleteRoute">
                                    <input type="submit" value="Скасувати" name="cancelDeletingRoute">
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
               <th>Маршрут</th>
               <th>Середня виручка(день)</th>
               <th>Середній розхід палива(день)</th>
               <th>Кількість автобусів прив'язаних до маршруту</th>

            </thead>
            </tr>
            <c:choose>
               <c:when test="${empty regime}">
                  <c:forEach items="${route}" var="route">
                     <tr onclick="location.href='/route?id=${route.id}'">
                        <td>${route.route}</td>
                        <td>${route.avarageProfit}</td>
                        <td>${route.avarageFuelConsuption}</td>
                        <td>${route.countOfBuses}</td>

                     </tr>
                  </c:forEach>
               </c:when>
               <c:otherwise>
                  <c:forEach items="${route}" var="route">
                     <c:if test="${access eq '1' || access eq '0'}">
                                          <tr onclick="location.href='/routes?regime=DeleteRoute&id=${route.id}'">


                     </c:if>

                        <c:if test="${access ne '1' || access eq '0'}">
                                            <tr onclick="location.href='/routes?id=${route.id}'">

</c:if>
                        <td>${route.route}</td>
                                                <td>${route.avarageProfit}</td>
                                                <td>${route.avarageFuelConsuption}</td>
                                                <td>${route.countOfBuses}</td>
                     </tr>
                  </c:forEach>
               </c:otherwise>
            </c:choose>
         </table>
      </div>




<c:if test="${access eq '1' || access eq '0'}">
      <div class="tooltip">
         <c:choose>
            <c:when test="${regime eq 'DeleteRoute'}">
               <img id="close" src="resources/close.png" width=35px onClick="location.href='/routes'"/>
            </c:when>
            <c:otherwise>
               <img class="wrench" src="resources/feature.png" width=35px/>
               <div class="person create">
                  <img  src="resources/edit.png" width=30px onclick="location.href='/routes?regime=AddRoute'">
               </div>
               <div class="person delete">
                  <img src="resources/trash.png" width=30px onclick="location.href='/routes?regime=DeleteRoute'">
               </div>
            </c:otherwise>
         </c:choose>
      </div>
      </c:if>

   </body>
   </html>