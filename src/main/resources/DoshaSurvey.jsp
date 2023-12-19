<!DOCTYPE html>
<%@page import="com.atm.pojo.AnswersPojo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.atm.pojo.DoshaSurveyQuestionPojo"%>
<%@page import="com.atm.dao.DoshaDao"%>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible"
		content="IE=edge">
	<meta name="viewport"
		content="width=device-width, initial-scale=1.0">
	<title>
		SurveyForm
	</title>

		<link rel="stylesheet" href="Resorces/CSS/DoshaSurvey.css">
	
</head>
	
<body>
	<h1>PLease Fill The Form</h1>
	<form action="userData" method="post">
<%
	DoshaDao dd = new DoshaDao();
	ArrayList<DoshaSurveyQuestionPojo> dss = dd.getAllQuestion();
	int sizeOfQuestion = dss.size();
	int perPage = 8;
	for(DoshaSurveyQuestionPojo dsp : dss){
		ArrayList<AnswersPojo> anss = dsp.getOption();
		%>
		<h3><%=dsp.getQuestionid() %>: <%=dsp.getQuestion() %></h3>
		<%
		for(AnswersPojo a : anss){
			if(a.getQuestionId()== dsp.getQuestionid()){
	%>
		<input type="checkbox" id="<%=dsp.getQuestionid() %>" name= "<%=dsp.getQuestionid()%>" value="<%=a.getId()%>" >
		<label for="<%=dsp.getQuestionid()%> " > <%=a.getAnswerId() %></label><br>
	
	<%
				}
			} 
	}
		%>
		<input type="submit" value="submit">
		</form>
</body>
</html>
