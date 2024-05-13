<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<html lang="en-US">
<head>
    <meta charset="UTF-8"/>
    <title>Hello</title>
    <sj:head jqueryui="true" jquerytheme="cupertino"/>
    <script src="uStates.js"></script>
    <script src="http://d3js.org/d3.v3.min.js"></script>
</head>
<body>
<sj:tabbedpanel id="localtabs">
  <sj:tab id="tab1" target="location" label="Acceuil"/>
  <div id="location">
    <p>Bonjour! 👋 Bievenue sur STRUT and SPRING BOOT </p>


    <p>Démmarer, veuillez cliquer ici : <s:a href="%{name}"><s:property value="name"/></s:a></p>


  </div>

</sj:tabbedpanel>
</body>
</html>