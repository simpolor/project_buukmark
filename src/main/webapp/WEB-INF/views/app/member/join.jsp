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
		
			function f_submit(){
				if(isNull($("#member_id").val())){
					alert('아이디를 입력해주세요.');	
					return false;
				}
				if(isNull($("#member_pw").val())){
					alert('비밀번호를 입력해주세요.');
					return false;
				}
				if(isNull($("#member_pw2").val())){
					alert('비밀번호 재확인을 입력해주세요.');
					return false;
				}
				if(!isEqual($("#member_pw").val(), $("#member_pw2").val())){
					alert('비밀번호가 일치하지 않습니다.');
					$("#member_pw2").val("");
					return false;
				}
				if(isNull($("#member_name").val())){
					alert('이름을 입력해주세요.');
					return false;
				}
				if(isNull($("#member_nickname").val())){
					alert('닉네임을 입력해주세요.');
					return false;
				}
				if(isNull($("#member_email").val())){
					alert('이메일 입력해주세요.');
					return false;
				}
				$("#joinForm").submit();
			}
		</script>
		
		<div id="section">
			<div class="member-nav">
				<div class="member-nav-title">
					<span>회원가입</span>
				</div>
			</div>
					
			<div class="member-field">
				<form:form id="joinForm" action="${pageContext.request.contextPath}/member/join" method="post" commandName="memberVO" >
					<div class="member-field-form">
						<div class="member-field-input">
							<form:input path="member_id" placeholder="아이디" />
						</div>
						<div class="member-field-input">
							<form:password path="member_pw" placeholder="비밀번호" />
						</div>
						<div class="member-field-input">
							<form:password path="member_pw2" placeholder="비밀번호 확인" />
						</div>
						<div class="member-field-input">
							<form:input path="member_name" placeholder="이름" />
						</div>
						<div class="member-field-input">
							<form:input path="member_nickname" placeholder="닉네임" />
						</div>
						<div class="member-field-input">
							<form:input path="member_email" placeholder="이메일" />
						</div>
						<div class="member-field-btn">
							<button onclick="f_submit(); return false;">회원가입</button>
						</div>
					</div>
				</form:form>
			</div>
			
			<div class="member-link">
				<div class="member-link-group">
					<a href="${pageContext.request.contextPath}/member/login">뒤로가기</a>
				</div>
			</div>
			
		</div><!-- #section -->
		
	</tiles:putAttribute>
</tiles:insertDefinition>