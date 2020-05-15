<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*" %>
<html>
	<head>

		<link href="Person.css" rel="stylesheet">
		<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js"></script>

	</head>
<body>

  <img src="data:image/jpg;base64,${w}" width="240" height="300"/>

		<h1>File Upload to Database Demo</h1>
		<form method="post"  onsubmit='redirect();return false;' enctype="multipart/form-data">

					<input type="file" name="photo" />
                    <input type="submit" value="Save">

 <h3>Способ 3</h3>


		</form>


                <script type="text/javascript">
            				$(document).ready(function(){
            					$('.spoiler-body3').hide();
            					$('.spoiler-title3').click(function(){
            						$(this).toggleClass('opened').toggleClass('closed').next().slideToggle();
            						if($(this).hasClass('opened')) {
            							$(this).html('Скрыть текст');
            						}
            						else {
            							$(this).html('Показать текст');
            						}
            					});
            				});
            			</script>


            			Спойлер <b class="spoiler-title3 closed">Показать текст</b>
            			<hr>
            			<div class="spoiler-body3">
            				Нельзя добавлять комментарии, которые:
            				<ul>
            					<li>Не относятся к тематике сайта и самой записи</li>
            					<li>Содержат в тексте исключительно заглавные буквы</li>
            				</ul>
            			</div>
</body>
</html>