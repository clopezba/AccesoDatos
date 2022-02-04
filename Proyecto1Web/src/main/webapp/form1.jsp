<html>
<body>

 <% response.setContentType("text/html");
    out.println("<title>Ejemplo</title>" + "<body bgcolor=FFFFFF>");
    String nombre = request.getParameter("nombre");
    String correo = request.getParameter("email");
    if(nombre== "" && correo =="")
    	 out.println("No he recibido nada!!!!!");
    if(nombre!= "")
    	out.println("Tu nombre es " + nombre);
    if(correo!= "")
    	out.println("<br>Tu correo es " + correo);
  
%>
</body>
</html>
