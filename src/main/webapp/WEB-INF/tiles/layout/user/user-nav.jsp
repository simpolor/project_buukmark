<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>  

<div id="nav">
	<div class="nav-menus">
		<c:choose>
			<c:when test="${! empty sessionScope.SESSION_MEMBER_ID}">
				<a href="${pageContext.request.contextPath}/bookmark/list" class="pure-link">즐겨찾기</a>
				<a href="${pageContext.request.contextPath}/bookmark/myList" class="pure-link">나의 즐겨찾기</a>
				<a href="${pageContext.request.contextPath}/notice/list" class="pure-link">공지사항</a>
				<a href="${pageContext.request.contextPath}/board/list" class="pure-link">자유게시판</a>
				<a href="${pageContext.request.contextPath}/ask/list" class="pure-link">문의사항</a>
			</c:when>
			<c:otherwise>
				<a href="${pageContext.request.contextPath}/bookmark/list" class="pure-link">즐겨찾기</a>
				<a href="${pageContext.request.contextPath}/notice/list" class="pure-link">공지사항</a>
				<a href="${pageContext.request.contextPath}/board/list" class="pure-link">자유게시판</a>
				<a href="${pageContext.request.contextPath}/ask/list" class="pure-link">문의사항</a>
			</c:otherwise>
		</c:choose>
	</div>
</div><!-- #nav -->