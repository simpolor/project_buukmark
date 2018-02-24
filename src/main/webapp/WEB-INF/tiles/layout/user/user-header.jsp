<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>  

<div id="header">
	<div class="header-logo">
		<a href="${pageContext.request.contextPath}/bookmark/list">BUUKMARK</a>
	</div>
	
	<div class="header-welcome">
		<c:if test="${! empty sessionScope.SESSION_MEMBER_ID}">
			<span>${sessionScope.SESSION_MEMBER_NAME} ( ${sessionScope.SESSION_MEMBER_ID} ) 님 환영합니다.</span>
		</c:if>
	</div>
	
	<div class="header-member">
		<c:choose>
			<c:when test="${! empty sessionScope.SESSION_MEMBER_ID}">
				<a href="${pageContext.request.contextPath}/member/view" >마이페이지</a>
				<a href="${pageContext.request.contextPath}/member/logout" >로그아웃</a>
			</c:when>
			<c:otherwise>
				<a href="${pageContext.request.contextPath}/member/login" class="pure-link">로그인</a>
				<a href="${pageContext.request.contextPath}/member/join" class="pure-link">회원가입</a>
			</c:otherwise>
		</c:choose>
	</div>
	
</div><!-- #header -->
