<%@ page import="gntp.lesson.board.vo.BoardVO" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 2022-09-07
  Time: 오후 5:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Board</title>
</head>
<body>
<%
  // 보낸 값 받기 getAttribute  // 오브젝트를 ArrayList 로 변경
  ArrayList<BoardVO> list = (ArrayList<BoardVO>) request.getAttribute("list");
%>

<a href="writeBoard.do">
  <input type="button" value="작성하기">
</a>

<div id="Main_Box">
  <h1> 방명록 </h1>
  <table id="list_table">
    <tr>
      <th>작성자</th>
      <th id="title">제목</th>
      <th id="content">내용</th>
      <th>작성일</th>
      <th>조회수</th>
      <th colspan="2"> 버튼</th>
    </tr>
    <%
      for (BoardVO vo : list) {
    %>
    <tr>
      <td>
        <%=vo.getWriter()%>
      </td>
      <td>
        <a href="read.do?seq=<%=vo.getSeq()%>">
          <%=vo.getTitle()%>
        </a>
      </td>
      <td>
        <%=vo.getContent()%>
      </td>
      <td>
        <%=vo.getWrite_date()%>
      </td>
      <td>
        <%=vo.getRead_count()%>
      </td>
      <td>
        <button>변경하기</button>
      </td>
      <td>
        <button>삭제하기</button>
      </td>
    </tr>
    <%
      }
    %>
  </table>
</div>
</body>
</html>
