<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*" %>
<html>
   <head>
      <link href="Back&Menu.css" rel="stylesheet">
      <link href="Person.css" rel="stylesheet">
      <link href="ModalWindow.css" rel="stylesheet">
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


      <div class="Bar">
         <ul id="main-ul">
            <li class="men" id="one" onclick="location.href='/staff'">Персонал</li>
            <li class="men" id="two" onclick="location.href='/buspark'">Автопарк</li>
            <li class="men" id="three">
               Відділення</a>
               <ul>
                  <li id="adm">Адміністрація</li>
                  <li id="Dri">Водії</li>
                  <li id="Mech">Автомеханіки</li>
                  <li id="Doc">Медперсонал</li>
               </ul>
            </li>
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
                              <svg height="5" width="350" ></svg>
                           </td>
                        </tr>



               </table>


               <div class="table2">

                  <hr>
                  <p class="spoiler-title2 closed">Адміністрація</p>
                  <div class="spoiler-body2">
                     <table class="table">
                        <tr>
                                             <td>Кількість персоналу в адміністрації:</td>
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