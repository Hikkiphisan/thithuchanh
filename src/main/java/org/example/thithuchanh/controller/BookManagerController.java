package org.example.thithuchanh.controller;

import org.example.thithuchanh.model.Sach;
import org.example.thithuchanh.model.TheMuonSach;
import org.example.thithuchanh.service.SachService;
import org.example.thithuchanh.service.SachServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Controller", urlPatterns = "/main_bookmanager")
public class BookManagerController extends HttpServlet {

    private SachService sachService;

    @Override
    public void init() throws ServletException {
        sachService = new SachServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            action = "list";
        }
        try {
            switch (action) {
                case "borrowForm":
                    showBorrowForm(request, response);
                    break;
                case "list":
                default:
                    showListBooks(request, response);
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void showListBooks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Sach> sachList = sachService.getSachList();
            request.setAttribute("sachList", sachList);
            request.getRequestDispatcher("BookManagament/viewBooks.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Không thể lấy dữ liệu sách.");
            request.getRequestDispatcher("BookManagament/viewBooks.jsp").forward(request, response);
        }


    }




    private void showBorrowForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String maSach = request.getParameter("maSach");
            String tenSach = request.getParameter("tenSach");

            if (maSach == null || tenSach == null) {
                response.sendRedirect("bookManager?action=list");
                return;
            }

            request.setAttribute("maSach", maSach);
            request.setAttribute("tenSach", tenSach);

            // Load danh sách học sinh từ service
            List<TheMuonSach> theMuonSach = sachService.getTableTheMuonSach();
            request.setAttribute("theMuonSach", theMuonSach);

            // Chuyển đến form mượn sách
            request.getRequestDispatcher("BookManagament/borrowBook.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Có lỗi xảy ra khi chuẩn bị form mượn sách.");
            request.getRequestDispatcher("BookManagament/bookList.jsp").forward(request, response);
        }
    }




        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            // Lấy các tham số từ form
            String maSach = request.getParameter("maSach");
            String tenSach = request.getParameter("tenSach");
            String studentName = request.getParameter("studentName");
            String mamuonsach = request.getParameter("mamuonsach");
            String ngayMuon = request.getParameter("ngayMuon");
            String ngayTra = request.getParameter("ngayTra");
            String trangthai = request.getParameter("trangthai");

            // Kiểm tra mã mượn sách
            if (!maSach.matches("^MS-\\d{4}$")) {
                request.setAttribute("error", "Mã mượn sách không hợp lệ.");
                request.getRequestDispatcher("borrowBook.jsp").forward(request, response);
                return;
            }

            // Kiểm tra ngày trả sách không trước ngày mượn
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Date borrowDate = sdf.parse(ngayMuon);
                Date returnDate = sdf.parse(ngayTra);

                if (returnDate.before(borrowDate)) {
                    request.setAttribute("error", "Ngày trả sách không được phép trước ngày mượn.");
                    request.getRequestDispatcher("borrowBook.jsp").forward(request, response);
                    return;
                }
            } catch (ParseException e) {
                e.printStackTrace();
                request.setAttribute("error", "Lỗi khi phân tích ngày tháng.");
                request.getRequestDispatcher("borrowBook.jsp").forward(request, response);
                return;
            }

            // Cập nhật thông tin mượn sách vào cơ sở dữ liệu
            // Giả sử bạn có một dịch vụ để xử lý việc mượn sách
            SachServiceImpl borrowBookService = new SachServiceImpl();
            boolean isBookBorrowed = borrowBookService.addtoTableTheMuonSachString(mamuonsach, studentName, maSach, ngayMuon,ngayTra, trangthai);
            //phuong thuc sẽ chuyen huong vao trang thong ke sach cho muon

            if (isBookBorrowed) {
                // Giảm số lượng sách trong kho
                borrowBookService.decreaseBookStock_truMot(maSach);
                //xoa sach trong kho

                // Chuyển hướng về trang thongkesach dang cho muon
                response.sendRedirect("thongkesachdangchomuon.jsp");
            } else {
                request.setAttribute("error", "Có lỗi khi mượn sách, vui lòng thử lại.");
                request.getRequestDispatcher("borrowBook.jsp").forward(request, response);
            }
        }
    }
