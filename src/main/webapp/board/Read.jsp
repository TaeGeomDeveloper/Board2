<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 2022-09-07
  Time: 오후 4:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Read Board</title>
</head>
<body>
<form method="post" action="reply.do">
    <table>
        <tr>
            <td>작성자 : <input type="text" name="writer">
            </td>
            <td>
                아이디 : <input type="text" name="user_id">
            </td>
            <td>제목 : <input type="text" name="title"></td>
        </tr>
        <tr>
            <td></td>
            <td>
                <textarea rows="5" cols="100" name="content"> </textarea>
            </td>
            <td></td>
        </tr>
        <tr>
            <td></td>
            <td>
                <input type="hidden" name="current_seq" value=" ">
            </td>
            <td></td>
        </tr>
        <tr>
            <td></td>
            <td>
                <input type="hidden" name="next_seq" value=" ">
            </td>
            <td></td>
        </tr>
        <tr>
            <td></td>
            <td>
                <input type="hidden" name="write_seq" value=" ">
            </td>
            <td></td>
        </tr>
        <tr>
            <td colspan="3">
                <input type="submit" value="댓글 작성">
            </td>
        </tr>
    </table>
</form>
</body>
</html>
