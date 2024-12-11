<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thống kê sách đang cho mượn</title>
    <link rel="stylesheet" href="styles.css"> <!-- Thêm CSS nếu cần -->
</head>
<body>
<div class="container">
    <h1>Thống kê sách đang cho mượn</h1>

    <!-- Hiển thị thông báo lỗi nếu có -->
    <c:if test="${not empty error}">
        <div class="error">
                ${error}
        </div>
    </c:if>

    <!-- Bảng thống kê sách -->
    <table border="1" cellpadding="10" cellspacing="0">
        <thead>
        <tr>
            <th>Mã mượn sách</th>
            <th>Tên sách</th>
            <th>Tên học sinh</th>
            <th>Ngày mượn</th>
            <th>Ngày trả</th>
            <th>Trạng thái</th>
        </tr>
        </thead>
        <tbody>
        <!-- Lặp qua danh sách sách đang mượn -->
        <c:forEach var="book" items="${borrowedBooks}">
            <tr>
                <td>${book.mamuonsach}</td>
                <td>${book.tenSach}</td>
                <td>${book.studentName}</td>
                <td>${book.ngayMuon}</td>
                <td>${book.ngayTra}</td>
                <td>${book.trangthai}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <!-- Nút quay về trang chính -->
    <div class="actions">
        <a href="main_bookmanager" class="btn">Quay về trang chính</a>
    </div>
</div>
</body>
</html>