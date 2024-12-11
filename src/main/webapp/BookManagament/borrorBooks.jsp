<%--
  Created by IntelliJ IDEA.
  User: Mr Loc
  Date: 12/11/2024
  Time: 9:39 AM
  To change this template use File | Settings | File Templates.
--%>

<!-- borrowBook.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Mượn Sách</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 50px;
        }
        form {
            max-width: 400px;
            margin: 0 auto;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 10px;
            background-color: #f9f9f9;
        }
        label {
            display: block;
            margin-bottom: 8px;
            font-weight: bold;
        }
        input, button {
            width: 100%;
            padding: 8px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        button {
            background-color: #28a745;
            color: white;
            font-size: 16px;
            cursor: pointer;
        }
        button:hover {
            background-color: #218838;
        }
    </style>
</head>
<body>
<h1 style="text-align: center;">Mượn Sách</h1>
<form action="borrowBookServlet" method="post">
    <label for="maSach">Mã Sách:</label>
    <input type="text" id="maSach" name="maSach" value="${param.maSach}" readonly />

    <label for="tenSach">Tên Sách:</label>
    <input type="text" id="tenSach" name="tenSach" value="${param.tenSach}" readonly />

    <label for="maHocSinh">Mã Học Sinh:</label>
    <input type="text" id="maHocSinh" name="maHocSinh" required placeholder="Nhập mã học sinh" />

    <label for="ngayMuon">Ngày Mượn:</label>
    <input type="date" id="ngayMuon" name="ngayMuon" required />

    <button type="submit">Xác Nhận Mượn</button>
</form>
</body>
</html>
