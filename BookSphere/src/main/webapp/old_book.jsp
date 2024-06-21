<%@page import="com.entity.User"%>
<%@page import="com.entity.BookDtls"%>
<%@page import="java.util.List"%>
<%@page import="com.DB.DBConnect"%>
<%@page import="com.DAO.BookDAOImpl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Old Books</title>
<%@include file="all_component/allCss.jsp"%>
</head>
<body style="background-color: #f0f1f2;">
	<%@include file="all_component/navbar.jsp"%>

	<c:if test="${empty userobj}">
		<c:redirect url="login.jsp"></c:redirect>
	</c:if>

	<c:if test="${not empty successMsg}">
		<div class="alert alert-success" role="alert">${successMsg}</div>
		<c:remove var="successMsg" scope="session" />
	</c:if>

	<c:if test="${not empty failed}">
		<div class="alert alert-danger" role="alert">${failedMsg}</div>
		<c:remove var="failedMsg" scope="session" />
	</c:if>

	<div class="container p-5">
		<table class="table table-striped">
			<thead class="bg-primary text-white">
				<tr>
					<th scope="col">#</th>
					<th scope="col">Book Name</th>
					<th scope="col">Author</th>
					<th scope="col">Price</th>
					<th scope="col">Category</th>
					<th scope="col">Action</th>
				</tr>
			</thead>

			<tbody>

				<%
				User user = (User) session.getAttribute("userobj");
				String email = user.getEmail();
				BookDAOImpl bookDao = new BookDAOImpl(DBConnect.getConn());
				List<BookDtls> list = bookDao.getBookByUserSell(email, "Old");
				int i = 1;
				for (BookDtls b : list) {
				%>
				<tr>
					<th scope="row"><%=i++%></th>
					<td><%=b.getBookName()%></td>
					<td><%=b.getAuthor()%></td>
					<td><%=b.getPrice()%></td>
					<td><%=b.getBookCategory()%></td>
					<td><a href="delete_old_book?email=<%=email%>&&id=<%=b.getBookId()%>"
						class="btn btn-sm btn-danger">Delete</a></td>
				</tr>
				<%
				}
				%>


			</tbody>

		</table>
	</div>

</body>
</html>