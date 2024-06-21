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
<title>BookSphere: index</title>
<%@include file="all_component/allCss.jsp"%>
<style type="text/css">
.back-img {
	background: url("img/main_book_img_2.jpg");
	height: 50vh;
	width: 100%;
	background-size: cover;
	background-repeat: no-repeat;
}

.crd-ho:hover {
	background-color: #fcf7f7;
}
</style>
</head>
<body style="background-color: #f7f7f7">

	<%
	User user = (User) session.getAttribute("userobj");
	%>

	<%@include file="all_component/navbar.jsp"%>

	<!-- have to design later... -->
	<div class="container-fluid back-img">
		<h2 class="text-center" style="color: white">Booksphare: Your one-stop shop for books.</h2>
	</div>


	<!-- Start Recent Book -->
	<div class="container">
		<h3 class="text-center p-2">Recent Book</h3>
		<div class="row">
			<%
			BookDAOImpl daoRecent = new BookDAOImpl(DBConnect.getConn());
			List<BookDtls> listRecent = daoRecent.getRecentBooks();
			for (BookDtls b : listRecent) {
			%>
			<div class="col-md-3">
				<div class="card crd-ho">
					<div class="card-body text-center">
						<img alt="" src="book/<%=b.getPhotoName()%>"
							style="width: 160px; height: 200px;" class="img-thumblin">
						<p><%=b.getBookName()%></p>
						<p><%=b.getAuthor()%></p>
						<p>
							Categories:<%=b.getBookCategory()%></p>

						<%
						if (b.getBookCategory().equals("Old")) {
						%>
						<div class="row justify-content-center">
							<a href="view_books.jsp?bid=<%=b.getBookId()%>"
								class="btn btn-success btn-sm ml">View details</a> <a href=""
								class="btn btn-danger btn-sm ml-1"><%=b.getPrice()%> <i
								class="fas fa-rupee-sign"></i></a>
						</div>
						<%
						} else {
						%>
						<div class="row justify-content-center">
							<%
							if (user == null) {
							%>
							<a href="login.jsp" class="btn btn-danger btn-sm ml"><i
								class="fas fa-cart-plus"></i> Add cart</a>
							<%
							} else {
							%>
							<a href="cart?bid=<%=b.getBookId()%>&&uid=<%=user.getId()%>"
								class="btn btn-danger btn-sm ml"><i class="fas fa-cart-plus"></i>
								Add cart</a>
							<%
							}
							%>

							<a href="view_books.jsp?bid=<%=b.getBookId()%>"
								class="btn btn-success btn-sm ml-1">View details</a> <a href=""
								class="btn btn-danger btn-sm ml-1"> <%=b.getPrice()%><i
								class="fas fa-rupee-sign"></i></a>
						</div>
						<%
						}
						%>

					</div>
				</div>
			</div>
			<%
			}
			%>

		</div>
		<div class="text-center mt-2">
			<a href="all_recent_books.jsp"
				class="btn btn-danger btn-sm text-white">View all</a>
		</div>
	</div>

	<!-- End Recent Book -->

	<hr>

	<!-- Start New Book -->
	<div class="container">
		<h3 class="text-center">New Book</h3>
		<div class="row">

			<%
			BookDAOImpl daoNew = new BookDAOImpl(DBConnect.getConn());
			List<BookDtls> listNew = daoNew.getNewBooks();
			for (BookDtls b : listNew) {
			%>
			<div class="col-md-3">
				<div class="card crd-ho">
					<div class="card-body text-center">
						<img alt="" src="book/<%=b.getPhotoName()%>"
							style="width: 160px; height: 200px;" class="img-thumblin">
						<p>
							<%=b.getBookName()%>
						</p>
						<p>
							<%=b.getAuthor()%>
						</p>
						<p>
							Categories:<%=b.getBookCategory()%>
						</p>
						<div class="row justify-content-center">
							<%
							if (user == null) {
							%>
							<a href="login.jsp" class="btn btn-danger btn-sm ml"><i
								class="fas fa-cart-plus"></i> Add cart</a>
							<%
							} else {
							%>
							<a href="cart?bid=<%=b.getBookId()%>&&uid=<%=user.getId()%>"
								class="btn btn-danger btn-sm ml"><i class="fas fa-cart-plus"></i>
								Add cart</a>
							<%
							}
							%>

							<a href="view_books.jsp?bid=<%=b.getBookId()%>"
								class="btn btn-success btn-sm ml-1">View details</a> <a href=""
								class="btn btn-danger btn-sm ml-1"> <%=b.getPrice()%><i
								class="fas fa-rupee-sign"></i></a>
						</div>
					</div>
				</div>
			</div>
			<%
			}
			%>

		</div>
		<div class="text-center mt-2">
			<a href="all_new_books.jsp" class="btn btn-danger btn-sm text-white">View
				all</a>
		</div>
	</div>

	<!-- End New Book -->

	<hr>

	<!-- Start Old Book -->
	<div class="container">
		<h3 class="text-center">Old Book</h3>
		<div class="row">
			<%
			BookDAOImpl daoOld = new BookDAOImpl(DBConnect.getConn());
			List<BookDtls> listOld = daoOld.getOldBooks();
			for (BookDtls b : listOld) {
			%>
			<div class="col-md-3">
				<div class="card crd-ho">
					<div class="card-body text-center">
						<img alt="" src="book/<%=b.getPhotoName()%>"
							style="width: 160px; height: 200px;" class="img-thumblin">
						<p>
							<%=b.getBookName()%>
						</p>
						<p>
							<%=b.getAuthor()%>
						</p>
						<p>
							Categories:<%=b.getBookCategory()%>
						</p>
						<div class="row justify-content-center">
							<a href="view_books.jsp?bid=<%=b.getBookId()%>"
								class="btn btn-success btn-sm ml">View details</a> <a href=""
								class="btn btn-danger btn-sm ml-1"><%=b.getPrice()%> <i
								class="fas fa-rupee-sign"></i></a>
						</div>
					</div>
				</div>
			</div>
			<%
			}
			%>

		</div>
		<div class="row justify-content-center p-2">
			<a href="all_old_books.jsp" class="btn btn-danger btn-sm text-white">View
				all</a>
		</div>
	</div>

	<!-- End Old Book -->

	<%@include file="all_component/footer.jsp"%>

</body>
</html>