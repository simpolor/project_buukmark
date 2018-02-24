<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>


<tiles:insertDefinition name="user-layout">
    <tiles:putAttribute name="user-body">
		
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/member.css">
		
		<script type="text/javascript">
			$(document).ready(function() { 
				var alertMessage = '${alertMessage}';
				
				if(!isNull(alertMessage)){
					alert(alertMessage);
				}
			});
			
			
			function f_edit() {
				var url = '${pageContext.request.contextPath}/member/edit';
				$(location).attr('href', url);
			}

			function f_password() {
				var url = '${pageContext.request.contextPath}/member/password';
				$(location).attr('href', url);
			}

			function f_withdraw() {
				var url = '${pageContext.request.contextPath}/member/withdraw';
				$(location).attr('href', url);
			}
		</script>
		
		<div id="section">
			<div class="member-nav">
				<div class="member-nav-title">
					<span>회원정보</span>
				</div>
			</div>
			
			<div class="member-field">
				<div class="member-field-form">
					<div class="member-field-text">
						<span>아이디</span>${memberVO.member_id}
					</div>
					<div class="member-field-text">
						<span>성명</span>${memberVO.member_name}
					</div>
					<div class="member-field-text">
						<span>닉네임</span>${memberVO.member_nickname}
					</div>
					<div class="member-field-text">
						<span>이메일</span>${memberVO.member_email}
					</div>
					<div class="member-field-text">
						<span>등록일</span>${memberVO.reg_date}
					</div>
					<div class="member-field-text">
						<span>수정일</span>${memberVO.mod_date}
					</div>
					<div class="member-field-btn">
						<button class="member-field-btn" onclick="f_edit(); return false;">회원정보변경</button>
					</div>
					<div class="member-field-btn">
						<button class="member-field-btn" onclick="f_password(); return false;">비밀번호변경</button>
					</div>
					<div class="member-field-btn">
						<button class="member-field-btn" onclick="f_withdraw(); return false;">회원탈퇴</button>
					</div>
				</div>
			</div>
			
		</div><!-- #section -->
		
	</tiles:putAttribute>
</tiles:insertDefinition>