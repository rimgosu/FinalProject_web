<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>test������</h1>
  <jsp:include page="header.jsp"></jsp:include>
  
	 <form action ="${cpath}/info"  method="post" enctype ="multipart/form-data">
	 	 <label for="uploadInput" id="uploadButton">�̹��� ����</label>
		 <input type="file" id="file" name="file" accept="image/*">
		 <button type="submit"  id="uploadButton">���ε�</button>
 	 </form> 
 	 
<input type="submit" >

</body>
</html>