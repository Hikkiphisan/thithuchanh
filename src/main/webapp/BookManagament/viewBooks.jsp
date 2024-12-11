<%--
  Created by IntelliJ IDEA.
  User: Mr Loc
  Date: 12/11/2024
  Time: 9:24 AM
  To change this template use File | Settings | File Templates.
--%>
<!-- viewBooks.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Danh Sách Sách</title>
    <style>
        table {
            width: 80%;
            margin: 20px auto;
            border-collapse: collapse;
        }
        th, td {
            border: 1px solid black;
            padding: 10px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
        button {
            padding: 5px 10px;
            background-color: #007BFF;
            color: white;
            border: none;
            cursor: pointer;
        }
        button:hover {
            background-color: #0056b3;
        }



        .error {
            color: red;
            font-weight: bold;
        }
    </style>


    <script>
        function checkAvailability(soLuong) {
            if (soLuong <= 0) {
                alert("Sách này hiện không có sẵn để mượn.");
                return false;
            }
            return true;
        }
    </script>


</head>
<body>
<h1 style="text-align: center;">Danh Sách Sách</h1>
<table>
    <thead>
    <tr>
        <th>Mã Sách</th>
        <th>Tên Sách</th>
        <th>Tác Giả</th>
        <th>Số Lượng</th>
        <th>Hành Động</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="sach" items="${sachList}">
        <tr>
            <td>${sach.maSach}</td>
            <td>${sach.tenSach}</td>
            <td>${sach.tacGia}</td>
            <td>${sach.soLuong}</td>
            <td>
                <form action="borrowBook.jsp" method="get" onsubmit="return checkAvailability(${sach.soLuong})">
                    <input type="hidden" name="maSach" value="${sach.maSach}" />
                    <input type="hidden" name="tenSach" value="${sach.tenSach}" />
                    <button type="submit" ${sach.soLuong <= 0 ? 'disabled' : ''}>Mượn</button>
                </form>

                <c:if test="${sach.soLuong <= 0}">
                    <span class="error">Hết sách</span>
                </c:if>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
