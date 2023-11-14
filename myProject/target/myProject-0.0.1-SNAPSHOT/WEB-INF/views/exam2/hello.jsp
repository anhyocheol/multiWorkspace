<%@ page contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>hello.jsp</title>
</head>
<body>

	<h2>Spring WebMVC Exam2</h2>
	<hr />
	
	<h4>인사말: ${msg}</h4>
	<h4>인사말: <%= request.getAttribute("msg") %>  </h4>	

</body>
</html>