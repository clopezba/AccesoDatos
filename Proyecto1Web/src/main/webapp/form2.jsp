<html>
<body>

 <% response.setContentType("text/html");
    out.println("<title>Ejemplo</title>" + "<body bgcolor=FFFFFF>");
    String nombre = request.getParameter("nombre");
    String correo = request.getParameter("email");
    if(nombre== null && correo ==null)
    	 out.println("No he recibido nada!!!!!");
    if(nombre!= null)
    	out.println("Tu nombre es " + nombre);
    if(correo!= null)
    	out.println("<br>Tu correo es " + correo);
  
%>
</body>
</html>
