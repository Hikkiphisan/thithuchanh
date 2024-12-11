<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Mượn Sách</title>
    <script type="text/javascript">
        function confirmReturn() {
            return confirm('Bạn có chắc chắn muốn trở về danh sách không?');
        }
    </script>
</head>
<body>
<h2>Mượn Sách</h2>
<form action="borrorBooks_temporary" method="get" onsubmit="return validateForm()">
    <!-- Mã mượn sách -->
    <label for="maSach">Mã mượn sách:</label>
    <input type="text" id="maSach" name="maSach" pattern="^MS-\d{4}$" required>
    <small>Mã mượn sách phải theo định dạng MS-XXXX (X là các số nguyên dương).</small><br><br>

    <!-- Tên sách (Không sửa đổi, chọn từ màn hình 1) -->
    <label for="tenSach">Tên sách:</label>
    <input type="text" id="tenSach" name="tenSach" value="${tenSach}" readonly><br><br>

    <!-- Tên học sinh (Dropdown) chua truyen tham so -->  c
    <label for="studentName">Tên học sinh:</label>
    <select id="studentName" name="studentName" required>
        <c:forEach var="student" items="${students}">
            <option value="${student.id}">${student.name}</option>
        </c:forEach>
    </select><br><br>

    <!-- Ngày mượn sách (Không sửa đổi, lấy từ hệ thống) -->
    <label for="ngayMuon">Ngày mượn sách:</label>
    <input type="text" id="ngayMuon" name="ngayMuon" value="<fmt:formatDate value='${currentDate}' pattern='dd/MM/yyyy' />" readonly><br><br>

    <!-- Ngày trả sách (Không trước ngày mượn, đúng định dạng dd/MM/yyyy) -->
    <label for="ngayTra">Ngày trả sách:</label>
    <input type="date" id="ngayTra" name="ngayTra" min="<%= new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()) %>" required><br><br>

    <!-- Trạng thái -->
    <input type="hidden" id="status" name="status" value="đang mượn sách">

    <!-- Nút mượn sách -->
    <button type="submit">Mượn sách</button>
    <button type="button" onclick="window.location.href='listBooks.jsp'">Trở về danh sách</button>
</form>

<script type="text/javascript">
    function validateForm() {
        const maSach = document.getElementById('maSach').value;
        const ngayTra = document.getElementById('ngayTra').value;
        const ngayMuon = document.getElementById('ngayMuon').value;

        // Kiểm tra ngày trả sách
        const borrowDateObj = new Date(ngayMuon.split('/').reverse().join('-'));
        const returnDateObj = new Date(ngayTra);

        if (returnDateObj < borrowDateObj) {
            alert('Ngày trả sách không được phép trước ngày mượn sách.');
            return false;
        }
        return true;
    }
</script>
</body>
</html>
