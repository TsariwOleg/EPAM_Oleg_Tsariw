<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*" %>
<html>
   <head>
      <link href="Back&Menu.css" rel="stylesheet">
      <link href="ModalWindow.css" rel="stylesheet">
      <link href="Person.css" rel="stylesheet">
      <link href="DepartmentTable.css" rel="stylesheet">
      <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js"></script>
      <script type="text/javascript" src="js/litezoom.js"></script>
      <script>
         function removeRequired(form){
         $.each(form, function(key, value) {
         if ( value.hasAttribute("required") || value.hasAttribute("min")){
                  value.removeAttribute("required");
                  value.removeAttribute("min");
         }
         });
         }
      </script>
   </head>
   <body>
      <c:if test="${access eq '1' || access eq '0'}">
         <form  method="post" action="/department">
            <c:if test="${regime eq 'AddWorkHours'}">
               <div class="change">
                  <div class="modal-window">
                     <div class="changeValues" align="center">
                        <table >
                           <tr>
                              <td>Початок робочого дня</td>
                              <td><input type="time" name="newStartWorkHours" required></td>
                           </tr>
                           <tr>
                              <td>Кінець робочого дня</td>
                              <td><input type="time" name="newEndWorkHours" required></td>
                           </tr>
                        </table>
                        <input type="submit" name="confirmWorkHours" value="Підтвердити" >
                        <input type="submit" value="Скасувати" onClick="removeRequired(this.form)">
                     </div>
                  </div>
               </div>
            </c:if>
         </form>
      </c:if>

      <c:if test="${access eq '1' || access eq '0'}">
         <form  method="post" action="/department">
            <c:if test="${regime eq 'AddPosition'}">
               <div class="change">
                  <div class="modal-window">
                     <div class="changeValues" align="center">
                        <table >
                           <tr>
                              <td>Посада</td>
                              <td><input type="text" name="newPosition" required></td>
                           </tr>
                           <tr>
                              <td>Зарплата</td>
                              <td><input type="number" name="newSalary" required></td>
                           </tr>
                        </table>
                        <input type="submit" name="confirmPosition" value="Підтвердити" >
                        <input type="submit" value="Скасувати" onClick="removeRequired(this.form)">
                     </div>
                  </div>
               </div>
            </c:if>
         </form>
      </c:if>

      <c:if test="${access eq '1' || access eq '0'}">
         <form  method="post">
            <c:if test="${regime eq 'EditWorkHours'}">
               <div class="change">
                  <c:choose>
                     <c:when test="${empty changeID}">
                        <div class="modal-windowDepart" style="overflow-y: scroll; height:50%;">
                           <div class="changeValues" align="center">
                              <table class="table" id="tableDep">
                                 <tr>
                                 <thead>
                                    <th>Початок робочого дня</th>
                                    <th>Кінець робочого дня</th>
                                 </thead>
                                 </tr>
                                 <c:forEach items="${workHours}" var="workHours">
                                    <tr onclick="location.href='/department?regime=EditWorkHours&id=${workHours.id}'">
                                       <td>${workHours.startWorkHour}</td>
                                       <td>${workHours.endWorkHour}</td>
                                    </tr>
                                 </c:forEach>
                              </table>
                           </div>
                        </div>
                     </c:when>
                     <c:otherwise>
                        <div class="modal-window">
                           <div class="changeValues" align="center">
                              <h1>${workHours[0].startWorkHour}-${workHours[0].endWorkHour}</h1>
                              <table >
                                 <tr>
                                    <td>Початок робочого дня</td>
                                    <td><input type="time" name="newStartWorkHours"  required></td>
                                 </tr>
                                 <tr>
                                    <td>Кінець робочого дня</td>
                                    <td><input type="time" name="newEndWorkHours" required></td>
                                 </tr>
                              </table>
                              <input type="submit" name="updateWorkHours" value="Підтвердити" >
                              <input type="submit" name="cancelEdittingWorkHours" value="Скасувати" onClick="removeRequired(this.form)">
                              <input type="submit" name="deleteWorkHours" value="Видалити" onClick="removeRequired(this.form)">
                           </div>
                        </div>
                     </c:otherwise>
                  </c:choose>
               </div>
            </c:if>
         </form>
      </c:if>

      <c:if test="${access eq '1' || access eq '0'}">
         <form  method="post">
            <c:if test="${regime eq 'EditPosition'}">
               <div class="change">
                  <c:choose>
                     <c:when test="${empty changeID}">
                        <div class="modal-windowDepart" style="overflow-y: scroll; height:50%;">
                           <div class="changeValues" align="center">
                              <table class="table" id="tableDep">
                                 <tr>
                                 <thead>
                                    <th>Посада</th>
                                    <th>Зарплата</th>
                                 </thead>
                                 </tr>
                                 <c:forEach items="${position}" var="position">
                                    <tr onclick="location.href='/department?regime=EditPosition&id=${position.id}'">
                                       <td>${position.position}</td>
                                       <td>${position.salary}</td>
                                    </tr>
                                 </c:forEach>
                              </table>
                           </div>
                        </div>
                     </c:when>
                     <c:otherwise>
                        <div class="modal-window">
                           <div class="changeValues" align="center">
                              <table >
                                 <tr>
                                    <td>Посада</td>
                                    <td><input type="text" name="newPosition" value="${position[0].position}" required></td>
                                 </tr>
                                 <tr>
                                    <td>Зарплата</td>
                                    <td><input type="number" name="newSalary" value="${position[0].salary}" required></td>
                                 </tr>
                              </table>
                              <input type="submit" name="updatePosition" value="Підтвердити" >
                              <input type="submit" name="cancelEdittingWorkHours" value="Скасувати" onClick="removeRequired(this.form)">
                              <input type="submit" name="deletePosition" value="Видалити" onClick="removeRequired(this.form)">
                           </div>
                        </div>
                     </c:otherwise>
                  </c:choose>
               </div>
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

      <form method="post" onsubmit='redirect();return false;'  enctype="multipart/form-data">
         <div id="information_about_person_div">
            <div id="information_about_person">
               <p class="spoiler-title2 closed">Водії</p>
               <table class="table">
                  <tr>
                     <td>Кількість водії:</td>
                     <td>${department.countOfDrivers}</td>
                  </tr>
                  <tr>
                     <td>Середня зарплата :</td>
                     <td>${department.avgSallaryOfDrivers}</td>
                  </tr>
                  <tr>
                                             <td>
                                                <svg height="30" width="350" ></svg>
                                             </td>
                                          </tr>
                                          <tr class="input">
                                             <td onclick="location.href='/routes'">
                                                <label>
                                                   <p class="ntr">Маршрути
                                                   <p>
                                                </label>
                                             </td>
                                          </tr>
                  <c:if test="${access eq '1' || access eq '0'}">
                     <tr>
                        <td>
                           <svg height="5" width="0" ></svg>
                        </td>
                     </tr>
                     <tr class="input">
                        <td onclick="location.href='/department?regime=AddWorkHours'">
                           <label>
                              <p class="ntr">Додати робочі години
                              </p>
                           </label>
                        </td>
                     </tr>
                     <tr>
                        <td>
                           <svg height="5"  ></svg>
                        </td>
                     </tr>
                     <tr class="input">
                        <td onclick="location.href='/department?regime=EditWorkHours'">
                           <label>
                              <p class="ntr">Редагувати робочі години
                              </p>
                           </label>
                        </td>
                     </tr>
                  </c:if>
                  <tr>
                     <td>
                        <svg height="5"  ></svg>
                     </td>
                  </tr>
               </table>
               <div class="table2">
                  <hr>
                  <p class="spoiler-title2 closed">Адміністрація</p>
                  <div class="spoiler-body2">
                     <table class="table">
                        <tr>
                           <td>Кількість персоналу:</td>
                           <td>${department.countOfAdministrations}</td>
                        </tr>
                        <tr>
                           <td>Середня зарплата :</td>
                           <td>${department.avgSallaryOfAdministrations}</td>
                        </tr>
                        <tr>
                           <td>
                              <svg height="30" width="0" ></svg>
                           </td>
                        </tr>
                        <c:if test="${access eq '1' || access eq '0'}">
                           <tr class="input">
                              <td onclick="location.href='/listofusers'">
                                 <label>
                                    <p class="ntr">Перелік користувачів сайту
                                    </p>
                                 </label>
                              </td>
                           </tr>
                           <tr>
                              <td>
                                 <svg height="5" width="350" ></svg>
                              </td>
                           </tr>
                           <tr class="input">
                              <td onclick="location.href='/department?regime=AddPosition'">
                                 <label>
                                    <p class="ntr">Додати нову посаду
                                    </p>
                                 </label>
                              </td>
                           </tr>
                           <tr>
                              <td>
                                 <svg height="5" width="350" ></svg>
                              </td>
                           </tr>
                           <tr class="input">
                              <td onclick="location.href='/department?regime=EditPosition'">
                                 <label>
                                    <p class="ntr">Редагувати посаду
                                    </p>
                                 </label>
                              </td>
                           </tr>
                           <tr>
                              <td>
                                 <svg height="5" width="350" ></svg>
                              </td>
                           </tr>
                        </c:if>
                     </table>
                  </div>
               </div>
               <div class="table3">
                  <hr>
                  <p class="spoiler-title3 closed">Автомеханіки</p>
                  <div class="spoiler-body3">
                     <table class="table">
                        <tr>
                           <td>Кількість механіків:</td>
                           <td>${department.countOfMechanics}</td>
                        </tr>
                        <tr>
                           <td>Середня зарплата :</td>
                           <td>${department.avgSallaryOfMechanics}</td>
                        </tr>
                        <tr>
                           <td>
                              <svg height="30" width="350" ></svg>
                           </td>
                        </tr>
                        <tr class="input">
                           <td onclick="location.href='/historyOfRepair'">
                              <label>
                                 <p class="ntr">Історія ремонтів автобусів
                                 <p>
                              </label>
                           </td>
                        </tr>
                        <tr>
                           <td>
                              <svg height="5" width="350" ></svg>
                           </td>
                        </tr>
                     </table>
                  </div>
               </div>
               <div class="table4">
                  <hr>
                  <p class="spoiler-title4 closed">Медперсонал</p>
                  <div class="spoiler-body4">
                     <table class="table">
                        <tr>
                           <td>Кількість медперсоналу:</td>
                           <td>${department.countOfDoctors}</td>
                        </tr>
                        <tr>
                           <td>Середня зарплата :</td>
                           <td>${department.avgSallaryOfDoctors}</td>
                        </tr>
                        <tr>
                           <td>
                              <svg height="30" width="350" ></svg>
                           </td>
                        </tr>
                        <tr class="input">
                           <td onclick="location.href='/checkUp'">
                              <label>
                                 <p class="ntr">Графік обстеження водіїв
                                 <p>
                              </label>
                           </td>
                        </tr>
                        <tr>
                           <td>
                              <svg height="5" width="350" ></svg>
                           </td>
                        </tr>
                     </table>
                  </div>
               </div>
               <div class="table4">
                  <hr>
                  <div class="spoiler-body4">
                  </div>
               </div>
            </div>
         </div>
      </form>
   </body>
</html>