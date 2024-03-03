<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="./css/styles.css">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Motorista</title>
</head>
<body>
<div>
    <jsp:include page="menu.jsp" />
</div>
<br />
<div align="center" class="container">
    <form action="motorista" method="post">
        <p class="tittle">
            <b>Motorista</b>
        </p>
        <table>
            <tr>
                <td colspan="2">
                    <input class="input_data_id" type="number" min="0" step="1" id="codigo" name="codigo"
                           placeholder="Codigo" value="<c:out value="${motorista.codigo}"/>">
                </td>
                <td>
                    <input type="submit" id="botao" name="botao" value="Buscar">
                </td>
            </tr>
            <tr>
                <td colspan="3">
                    <input class="input_data" type="text" id="nome" name="nome" placeholder="Nome"
                           value="<c:out value="${motorista.nome}"/>">
                </td>
            </tr>
            <tr>
                <td colspan="3">
                    <input class="input_data" type="text" id="naturalidade" name="naturalidade" placeholder="Naturalidade"
                           value="<c:out value="${motorista.naturalidade}"/>">
                </td>
            </tr>
            <tr>
                <td>
                    <input type="submit" id="botao" name="botao" value="Cadastrar">
                </td>
                <td>
                    <input type="submit" id="botao" name="botao" value="Alterar">
                </td>
                <td>
                    <input type="submit" id="botao" name="botao" value="Excluir">
                </td>
            </tr>
        </table>
    </form>
</div>
<br />
<div align="center">
    <c:if test="${not empty erro}">
        <h2><b><c:out value="${erro}"/></b></h2>
    </c:if>
</div>
<div align="center">
    <c:if test="${not empty saida}">
        <h3><b><c:out value="${saida}"/></b></h3>
    </c:if>
</div>
</body>
</html>
