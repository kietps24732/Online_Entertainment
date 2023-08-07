<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<style>
body {
	height: 180vh;
	text-align: center;
	/* background: linear-gradient(#CAE1FF, #FFF0F5, #8DEEEE); */
	background: linear-gradient(#97FFFF, #FFDAB9);
	font-family: "Sansita Swashed", cursive;
	/* font-family: "Courier New", Courier, monospace; */
}

button {
	border-radius: 50px;
	width: 80px;
	height: 30px;
	background-color: #00CED1;
	color: black;
	font-family: "Sansita Swashed", cursive;
}

button:hover {
	background: #03e9f4;
	color: #fff;
	box-shadow: 0 0 5px #03e9f4, 0 0 25px #03e9f4, 0 0 50px #03e9f4, 0 0
		100px #03e9f4;
}

input {
	border-radius: 10px;
	height: 25px;
	width: 300px;
	font-family: "Sansita Swashed", cursive;
	background-color: #EEE0E5;
}

.a {
	background-color: white;
	position: absolute;
	top: 50%;
	left: 50%;
	width: 800px;
	padding: 40px;
	transform: translate(-50%, -50%);
	background: #B9D3EE;
	box-sizing: border-box;
	box-shadow: 0 15px 25px rgba(0, 0, 0, .6);
	border-radius: 10px;
}

th {
	width: 200px;
}

.b {
	background-color: white;
	position: absolute;
	top: 800px;
	left: 50%;
	width: 800px;
	padding: 40px;
	transform: translate(-50%, -50%);
	background: #B9D3EE;
	box-sizing: border-box;
	box-shadow: 0 15px 25px rgba(0, 0, 0, .6);
	border-radius: 10px;
}

</style>

</head>
<body>
	<div class="a">
		<form>
			<div class="center">
				<h1>Favorites Statistic</h1>
				<form>
					<table border="1" style="width: 100%">
						<thead>
							<tr>
								<th>Title</th>
								<th>Link</th>
								<th>Total Like</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${videos}" var="item">
								<tr>
									<td>${item.title}</td>
									<td><a href="video?action=watch&id=${item.href}">${item.href}</a></td>
									<td>${item.totalLike}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</form>
			</div>
		</form>
	</div>


	<div class="b">
		<div class="form-group">
			<label>List Videos</label> <select id="selectVideo"
				class="form-control">
				<option selected disabled>Select one</option>
				<c:forEach items="${videos}" var="item">
					<option value="${item.href}">${item.title}</option>
				</c:forEach>
			</select>
		</div>
		<form>
			<table border="1" style="width: 100%">
				<thead>
					<tr>
						<th>Username</th>
						<th>Email</th>
					</tr>
				</thead>
				<tbody>

				</tbody>
			</table>
		</form>
	</div>

	<script>
		$('#selectVideo').change(function() {
			var videoHref = $(this).val();
			$.ajax({
				url : 'admin/favorites?href=' + videoHref,
				type : 'GET',
				contentType : 'application/json'
			}).done(function(data) {
				$('#example2').DataTable({
					destroy : true,
					"paging" : true,
					"lengthChang" : false,
					"searching" : false,
					"ordering" : true,
					"info" : true,
					"autoWidth" : false,
					"responsive" : true,
					"aaData" : data,
					"columns" : [ {
						"data" : "username"
					}, {
						"data" : "email"
					} ]
				});
			});
		});
	</script>

</body>
</html>

