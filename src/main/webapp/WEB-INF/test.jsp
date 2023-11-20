<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Image Display</title>
</head>
<body>
    <h2>Image from S3:</h2>
    <img src="data:image/jpeg;base64,${imageData}" alt="S3 Image" />
</body>
</html>