<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>file</title>
</head>
<body>
	<form action="multiFileUpload.do" method="post" enctype="multipart/form-data">
		<input type="file" name="afile" /><br/>
		<input type="file" name="bfile" /><br/>
		<input type="file" name="cfile" /><br/>
		<input type="file" name="dfile" /><br/>
		<input type="file" name="efile" /><br/>
		<input type="submit" value="upload" />
	</form>
</body>
</html>