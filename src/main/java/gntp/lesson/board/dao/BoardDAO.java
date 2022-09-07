package gntp.lesson.board.dao;

import gntp.lesson.board.vo.BoardVO;
import gntp.lesson.board.utils.ConnectionManagerV2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BoardDAO {
    public boolean insertItem(BoardVO board) throws SQLException {
        boolean flag = false;
        String sql = "insert into board(current_seq,title,content,next_seq,writer,user_id) values (?,?,?,?,?,?)";
        Connection con = ConnectionManagerV2.getConnection();
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, board.getCurrent_seq());
        pstmt.setString(2, board.getTitle());
        pstmt.setString(3, board.getContent());
        pstmt.setString(4, board.getNext_seq());
        pstmt.setString(5, board.getWriter());
        pstmt.setString(6, board.getUser_id());
        int affectedCount = pstmt.executeUpdate();
        if(affectedCount>0) {
            flag = true;
        }
        ResultSet rs = null;
        if(flag) {
            sql = "select max(seq) from board";
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            int max = 0;
            if(rs.next()) {
                max = rs.getInt(1);
            }
            sql = "update board set write_seq = ? where seq = ? ";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, max);
            pstmt.setInt(2, max);
            pstmt.executeUpdate();
            //write_seq 생성
            System.out.println("write_seq 생성");
        }
        ConnectionManagerV2.closeConnection(rs, pstmt, con);
        return flag;
    }
    public ArrayList<BoardVO> selectAll() throws SQLException{
        ArrayList<BoardVO> list = null;
        String sql = "select * from board order by write_seq desc , current_seq asc";
        Connection con = ConnectionManagerV2.getConnection();
        PreparedStatement pstmt = con.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        list = new ArrayList<BoardVO>();
        BoardVO board = null;
        while(rs.next()) {
            board = new BoardVO(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),
                    rs.getString(5),rs.getInt(6),rs.getString(7),rs.getTimestamp(8),"user","user");
            list.add(board);
        }
        ConnectionManagerV2.closeConnection(rs, pstmt, con);
        return list;
    }

//    public ArrayList<BoardVO> getBoardList() throws SQLException {
//        ArrayList<BoardVO> list = new ArrayList<>();
//
//        // 컨낵션 정보
//        Connection con = ConnectionManagerV2.getConnetion();
//
//        // 쿼리부분     아이디, 비밀번호, 이름
//        String sql = "SELECT * FROM board";
//
//        // 특정한 쿼리만 통과 하는 전용 통로 작성.
//        PreparedStatement pstmt = con.prepareStatement(sql);
//
//        // 쿼리 ? 부분에 값을 넣어 비교 해준다.
//        //pstmt.setInt(1,Integer.parseInt(id));
//        //pstmt.setInt(2,Integer.parseInt(pwd));
//        ResultSet rs = pstmt.executeQuery();
//
//        // 쿼리 처리
//        BoardVO vo = null;
//        while(rs.next()) {
//            vo = new BoardVO(rs.getInt(1),
//                    rs.getInt(2),
//                    rs.getString(3),
//                    rs.getString(4),
//                    rs.getString(5),
//                    rs.getInt(6),
//                    rs.getString(7),
//                    rs.getDate(8),
//                    rs.getString(9),
//                    rs.getString(10));
//            list.add(vo);
//        }
//
//        // 닫기
//        ConnectionManagerV2.closeConnection(rs,pstmt,con);
//
//        return list;
//    }
//    public void WriteBoard(BoardVO vo) throws SQLException {
//        //ArrayList<memberVO> list = new ArrayList<>();
//        boolean flag = false;
//
//        // 컨낵션 정보
//        Connection con = ConnectionManagerV2.getConnetion();
//
//        // 쿼리부분     아이디, 비밀번호, 이름
//        String sql = "Insert into board (current_seq,title,content,next_seq,writer,user_id) values(?,?,?,?,?,?)";
//
//        // 특정한 쿼리만 통과 하는 전용 통로 작성.
//        PreparedStatement pstmt = con.prepareStatement(sql);
//
//        // 쿼리 ? 부분에 값을 넣어 비교 해준다.
//        pstmt.setString(1,vo.getCurrent_seq());  //current_seq
//        pstmt.setString(2,vo.getTitle());   //title
//        pstmt.setString(3,vo.getContent()); //content
//        pstmt.setString(4,vo.getNext_seq());   //next_seq
//        pstmt.setString(5,vo.getWriter());   //writer
//        pstmt.setString(6,vo.getUser_id());   //user_id
//        //pstmt.setInt(2,Integer.parseInt(pwd));
//        int count = pstmt.executeUpdate();
//
//        // 쿼리 처리
//        if(count > 0){
//            flag = true;
//        }
//        ResultSet rs = null;
//        if(flag){
//            sql = "select max(seq) from board";
//                    sql = "update board set write_seq = ? where seq = ?";
//            pstmt = con.prepareStatement(sql);
//            pstmt.setInt(1,max);
//            pstmt.setInt(2,max);
//        }
//        while (rs.next()) {
//            board = new BoardBO(rs.getInt(1),rs.getInt(2),rs.getInt(3),
//                    rs.getString(3),rs.getInt(4),rs.getInt(5),
//                    rs.getString(6),rs.getDate(7),"user","user",rs.getString(10));
//            list.add(board);
//        }
//        ConnectionManagerV2 =
//
//        // 닫기
//        pstmt.close();
//        con.close();
//    }

    public BoardVO selectBoard(String seq) {

        return null;
    }
}
