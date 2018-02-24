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
			
			function f_search(){
				if(isNull($("#member_name").val())){
					alert('이름을 입력해주세요.');	
					return false;
				}
				if(isNull($("#email").val())){
					alert('이메일을 입력해주세요.');
					return false;
				}
				$("#searchForm").submit();
			}
			
		</script>
		
		<div id="section">
			<div class="member-nav">
				<div class="member-nav-title">
					<span>회원찾기</span>
				</div>
			</div>
			
			<div class="member-field">
				<form:form id="searchForm" action="${pageContext.request.contextPath}/member/search" method="post" commandName="memberVO">
					<div class="member-field-form">
						<div class="member-field-input">
							<form:input path="member_name" placeholder="이름" />
						</div>
						<div class="member-field-input">
							<form:input path="member_email" placeholder="이메일" />
						</div>
						<div class="member-field-btn">
							<button onclick="f_search(); return false;">회원찾기</button>
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