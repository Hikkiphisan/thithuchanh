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

        /* Modal styles */
        .modal {
            display: none;
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0,0,0,0.5);
            z-index: 1000;
        }

        .modal-content {
            background-color: white;
            margin: 15% auto;
            padding: 20px;
            border: 1px solid #888;
            width: 80%;
            max-width: 500px;
            border-radius: 5px;
            text-align: center;
            position: relative;
        }

        .modal button {
            margin-top: 15px;
            padding: 8px 20px;
        }
    </style>

    <script>
        function handleBorrow(soLuong) {
            if (soLuong <= 0) {
                document.getElementById("errorModal").style.display = "block";
                return false;
            }
            // Nếu số lượng > 0, cho phép chuyển đến trang mượn sách
            return true;
        }

        function closeModal() {
            document.getElementById("errorModal").style.display = "none";
        }
    </script>
</head>
<body>
<h1 style="text-align: center;">Danh Sách Sách</h1>

<div id="errorModal" class="modal">
    <div class="modal-content">
        <p>Quyển sách này tạm thời đã cho mượn hết, vui lòng chọn sách khác.</p>
        <button onclick="closeModal()">OK</button>
    </div>
</div>

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
                <form action="borrorBooks_temporary,jsp" method="get" onsubmit="return handleBorrow(${sach.soLuong})">
                    <input type="hidden" name="maSach" value="${sach.maSach}" />
                    <input type="hidden" name="tenSach" value="${sach.tenSach}" />
                    <button type="submit">Mượn</button>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>