package gntp.lesson.board.controller;

import gntp.lesson.board.dao.BoardDAO;
import gntp.lesson.board.utils.ConnectionManagerV2;
import gntp.lesson.board.vo.BoardVO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("*.do")
public class BoardController extends HttpServlet{

    public void doHandler(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("hi");
        //doHandler(request, response);
        // DB 연결 테스트
//        Connection con = ConnectionManagerV2.getConnetion();
//        if(con!=null)   {
//            try {
//                con.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
    }

    @Override
    public void init() throws ServletException {
        System.out.println("init");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doGet");
//        try {
//            doHandler(req, resp);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doPost");

        resp.setContentType("text/html; charset=UTF-8");

        //String command = req.getParameter("command");
        String uri = req.getRequestURI();
        String[] temp = uri.split("/");
        String command = temp[temp.length - 1];

        req.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        BoardDAO dao = new BoardDAO();

        String url = "board/Board.jsp";

        if(command.equals("list.do")) {

            // 리스트 처리
            ArrayList<BoardVO> list = new ArrayList<BoardVO>();
            try {
                list = dao.selectAll();
                HttpSession session = req.getSession();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            req.setAttribute("list", list);

            url = "board/Board.jsp";
        }

        else if(command.equals("writeBoard.do")) {

            url = "board/writeBoard.jsp";
        }
        else if(command.equals("write.do")) {
            // 추가 회원 내용
            String current_seq = req.getParameter("current_seq");
            String writer = req.getParameter("writer");
            String title = req.getParameter("title");
            String next_seq = req.getParameter("next_seq");
            String content = req.getParameter("content");
            String userId = req.getParameter("userId");

            BoardVO vo = new BoardVO();
            vo.setTitle(title);
            vo.setContent(content);
            vo.setCurrent_seq(current_seq);
            vo.setWriter(writer);
            vo.setNext_seq(next_seq);
            vo.setUser_id(userId);

            // 로직 처리
            try {
                dao.insertItem(vo);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            url = "list.do";
        } else if (command.equals("read.do")) {
            String seq = req.getParameter("seq");
            BoardVO board = dao.selectBoard(seq);
            req.setAttribute("board",board);
            url = "board/read.jsp";

        } else if (command.equals("replay.do")) {
            String seq = req.getParameter("seq");
            BoardVO board = dao.selectBoard(seq);
            req.setAttribute("board",board);
            url = "board/read.jsp";
        }

        RequestDispatcher rd = req.getRequestDispatcher(url);
        rd.forward(req, resp);
    }
    @Override
    public void destroy() {
        System.out.println("destory");
    }
}
