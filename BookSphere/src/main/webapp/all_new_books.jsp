<%@page import="com.entity.User"%>
<%@page import="com.entity.BookDtls"%>
<%@page import="java.util.List"%>
<%@page import="com.DB.DBConnect"%>
<%@page import="com.DAO.BookDAOImpl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>All New Book</title>
<%@include file="all_component/allCss.jsp"%>

<style type="text/css">
.crd-ho:hover {
	background-color: #fcf7f7;
}

/* toast */
#toast {
	min-width: 300px;
	position: fixed;
	bottom: 30px;
	left: 50%;
	margin-left: -125px;
	background: #333;
	padding: 10px;
	color: white;
	text-align: center;
	z-index: 1;
	font-size: 18px;
	visibility: hidden;
	box-shadow: 0px 0px 100px #000;
}

#toast.display {
	visibility: visible;
	animation: fadeIn 0.5, fadeOut 0.5s 2.5s;
}

@
keyframes fadeIn {from { bottom:0;
	opacity: 0;
}

to {
	bottom: 30px;
	opacity: 1;
}

}
@
keyframes fadeOut {form { bottom:30px;
	opacity: 1;
}

to {
	bottom: 0;
	opacity: 0;
}
}

/* toast */
</style>

</head>
<body>

	<c:if test="${not empty addCart}">
		<div id="toast">${addCart}</div>

		<script type="text/javascript">
		showToast();
		function showToast(content)
		{
		    $('#toast').addClass("display");
		    $('#toast').html(content);
		    setTimeout(()=>{
		        $("#toast").removeClass("display");
		    },2000)
		}	
</script>
		<c:remove var="addCart" scope="session" />
	</c:if>
	<%
	User user = (User) session.getAttribute("userobj");
	%>
	<%@include file="all_component/navbar.jsp"%>

	<div class="container-fluid">
		<div class="row">

			<%
			BookDAOImpl daoNew = new BookDAOImpl(DBConnect.getConn());
			List<BookDtls> listNew = daoNew.getAllNewBooks();
			for (BookDtls b : listNew) {
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
	</div>

</body>
</html>