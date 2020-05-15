<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*" %>
<html>
	<head>
		<link href="Back&Menu.css" rel="stylesheet">
		<link href="Person.css" rel="stylesheet">


		<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js"></script>
		<script type="text/javascript" src="litezoom/litezoom.js"></script>
		<script type="text/javascript" src="pop-upMenu.js"></script>


	</head>
	<body>

		<div class="Bar">

			<ul id="main-ul">

				<li class="men" id="one">Персонал</li>
				<li class="men" id="two">Автопарк</li>
				<li class="men" id="three">Відділення</a>

				<ul>
					<li id="adm">Адміністрація</li>
					<li id="Dri">Водії</li>
					<li id="Mech">Автомеханіки</li>
					<li id="Doc">Медперсонал</li>
				</ul>

			</li>


			<li class="men" id="four">Ввійти</li>
			<hr id="hr_css">
		</ul>
	</div>


    <form method="post" onsubmit='redirect();return false;'  enctype="multipart/form-data">


		<div id="information_about_person_div">
		    <div id="information_about_person">

				<div class="image">

				<c:choose>
                		<c:when test="${empty staff.photo}">
                			<img class="img" src="empty.jpg"/>
                		</c:when>
                			<c:otherwise>
                				<img class="img" src="data:image/jpg;base64,${staff.photo}"/>
                			</c:otherwise>
                </c:choose>


					<ul class="input-file">
						<li>  <input type="file" name="staff-img" accept="image/*">  </li>
						<li>  <input type="submit" name="staff-button" >  </li>
					</ul>

				</div>

				<table class="table">
					<tr>
						<td>Ім`я:</td>
						<td>${staff.name}</td>
					</tr>

					<tr>
						<td>Прізвище:</td>
						<td>${staff.surname}</td>
					</tr>

					<tr>
						<td>По-батькові:</td>
						<td>${staff.patronymic}</td>
					</tr>



					<tr>
						<td>Вік:</td>
						<td>${staff.age}</td>
					</tr>


					<tr>
						<td>Відділення:</td>
						<td>${staff.department}</td>
					</tr>

					<tr>
						<td>Посада:</td>
						<td>${department.position}</td>
					</tr>

					<tr>
						<td>Зарплата:</td>
						<td>${department.salary}</td>
					</tr>
				</table>



				<div class="table2">
					<script type="text/javascript"></script>
                    <hr>
                    <p class="spoiler-title2 closed">Паспорт</p>

					<div class="spoiler-body2">

						<div class="image">
							<c:choose>
								<c:when test="${empty passport.scannedPassport}">
									<img class="img" src="empty.jpg"/>
								</c:when>
								<c:otherwise>
									<img class="img" src="data:image/jpg;base64,${passport.scannedPassport}" />
								</c:otherwise>
							</c:choose>


							<ul class="input-file">

								<li>  <input type="file" name="passport-img"  accept="image/*" >  </li>
								<li>  <input type="submit" name="passport-button">  </li>

							</ul>

						</div>

						<table class="table">
							<tr>
								<td>Дата народження:</td>
								<td>${passport.dateOfBirth}</td>
							</tr>

							<tr>
								<td>Країна народження:</td>
								<td>${passport.countryOfBirth}</td>
							</tr>

							<tr>
								<td>Область народження:</td>
								<td>${passport.regionOfBirth}</td>
							</tr>

							<tr>
								<td>Місто народження:</td>
								<td>${passport.cityOfBirth}</td>
							</tr>

							<tr>
								<td>Номер документа:</td>
								<td>${passport.documentNo}</td>
							</tr>

						</table>
					</div>

				</div>






				<div class="table3">
					<script type="text/javascript"></script>
                    <hr>
                    <p class="spoiler-title3 closed">Картка платника податків</p>


					<div class="spoiler-body3">

						<div class="image">
							<c:choose>
								<c:when test="${empty taxpayerCard.scannedTaxpayerCard}">
									<img class="img" src="empty.jpg" />
								</c:when>
								<c:otherwise>
									<img class="img" src="data:image/jpg;base64,${taxpayerCard.scannedTaxpayerCard}"/>
								</c:otherwise>
							</c:choose>

							<ul class="input-file">
								<li>  <input type="file" name="taxpayerCard-img" accept="image/*">  </li>
								<li>  <input type="submit" name="taxpayerCard-button">  </li>
							</ul>
						</div>

						<table class="table">
							<tr>
								<td>Номер карти:</td>
								<td>${taxpayerCard.taxpayerNumber}</td>
							</tr>
						</table>
					</div>

				</div>




				<div class="table4">

					<script type="text/javascript"></script>


                    <hr>
                    <p class="spoiler-title4 closed">Медична книжка</p>


					<div class="spoiler-body4">

						<table class="table">
							<tr>
								<td>Дата проходження медогляду:</td>
								<td>${medicalBook.dateOfMedicalExam}</td>
							</tr>


							<tr>
								<td>Дата наступного проходження медогляду:</td>
								<td>${medicalBook.dateOfNextMedicalExam}</td>
							</tr>
						</table>
					</div>

				</div>



				<hr id="ew">
			</div>

		</div>

	</form>



	<script type="text/javascript">$('.img').litezoom({speed:400, viewTitle:true});</script>


</body>
</html>
