package org.example.thithuchanh.controller;

import org.example.thithuchanh.model.Sach;
import org.example.thithuchanh.service.SachService;
import org.example.thithuchanh.service.SachServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;
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
        try {
            showListBooks(request, response);
        } catch (ServletException er) {
            er.printStackTrace();
        } catch (IOException io) {
            io.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
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
}