

    <%
    String sqlStatement =
       (String) session.getAttribute("sqlStatement");
    if (sqlStatement == null)
       sqlStatement = "";
    String message =
       (String) session.getAttribute("message");
    if (message == null)
       message = "";
 %>

  

 
</body>
</html>


<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container">
  <h2>Sql editor :</h2>
  <form role="form" action="/MySqlEditor/Lazim" method="post" >
    <div class="form-group">
      <label for="comment">Query:</label>
      <textarea class="form-control" rows="5" id="sql" name="sqlStatement"></textarea>
    </div>
    
    
      <button type="submit" class="btn btn-default" name="submit" value="Execute">Çaliştir:</button>
  </form>
</div>
<div class="container table-responsive">
     <%= message %>
</div>

</body>
</html>


