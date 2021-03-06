<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="<c:url value='/resources/css/main/member/findId.css'/>"/>
<script src="<c:url value='/resources/js/jquery-3.4.1.min.js'/>"></script>
<title>아이디 찾기</title>
<main class="main-board">  
    <!-- CONTAINER -->
    <div class="login-body">
            <div class="login-box">
                <div class="lb-header">
                    <span class="active" id="login-box-link">아이디 찾기</span>
                </div>
                <form class="email-login" method="post">
                    <div class="u-form-group">
                        <div>이메일&nbsp;</div><input type="text" id="findEmail" placeholder="Email" name="email"/>&emsp;&emsp;
                    </div>
                    <div class="u-form-group">
                        &nbsp;
                    </div>
                    <br>
                    <div class="u-form-group">
                        <button class="submit-login" id="findId">확인</button>&nbsp;&nbsp;
                        <button type="button" class="back" onclick="location.href='loginform.do'">뒤로</button>
                    </div>
                    <div class="u-form-group">
                        <a href="findIdform.do" class="forgot-id">아이디 찾기</a>
                        &nbsp;
                        <a href="findPasswordform.do" class="forgot-password">비밀번호 찾기</a>
                        &nbsp;
                        <a href="signupform.do" class="forgot-id">회원가입</a>
                    </div>
                </form>
                
            </div>

    </div>
</main>
<script>
let msg = '${msg}';
</script>
<script src="<c:url value='/resources/js/main/member/findId.js'/>"></script>