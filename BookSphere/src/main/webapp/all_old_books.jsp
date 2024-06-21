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
<title>All New Book</title>
<%@include file="all_component/allCss.jsp"%>
</head>
<body>
	<%@include file="all_component/navbar.jsp"%>
	
	<div class="container-fluid">
		<div class="row">

			<%
			BookDAOImpl daoOld = new BookDAOImpl(DBConnect.getConn());
			List<BookDtls> listOld = daoOld.getAllOldBooks();
			for (BookDtls b : listOld) {
			%>
			<div class="col-md-3 mt-2">
				<div class="card crd-ho">
					<div class="card-body text-center">
						<img alt="" src="book/<%=b.getPhotoName()%>"
							style="width: 160px; height: 200px;" class="img-thumblin">
						<p><%=b.getBookName()%></p>
						<p><%=b.getAuthor()%></p>
						<p>
							Categories:<%=b.getBookCategory()%></p>

						<div class="row justify-content-center">
							<a href="view_books.jsp?bid=<%=b.getBookId()%>" class="btn btn-success btn-sm ml">View details</a> <a
								href="" class="btn btn-danger btn-sm ml-1"><%=b.getPrice()%>
								<i class="fas fa-rupee-sign"></i></a>
						</div>

					</div>
				</div>
			</div>
			<%
			}
			%>

		</div>
	</div>
	
</body>
</html>