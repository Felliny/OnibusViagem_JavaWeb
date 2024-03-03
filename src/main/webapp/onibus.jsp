<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="./css/styles.css">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Onibus</title>
</head>
<body>
    <div>
        <jsp:include page="menu.jsp" />
    </div>
    <br />
    <div align="center" class="container">
        <form action="onibus" method="post">
            <p class="tittle">
                <b>Ônibus</b>
            </p>
            <table>
                <tr>
                    <td colspan="2">
                        <input class="input_data_id" type="text" id="placa" name="placa"
                            placeholder="Placa" value="<c:out value="${onibus.placa}"/>">
                    </td>
                    <td>
                        <input type="submit" id="botao" name="botao" value="Buscar">
                    </td>
                </tr>
                <tr>
                    <td colspan="3">
                        <input class="input_data" type="text" id="marca" name="marca" placeholder="Marca"
                            value="<c:out value="${onibus.marca}"/>">
                    </td>
                </tr>
                <tr>
                    <td colspan="3">
                        <input class="input_data" type="number" id="ano" name="ano" placeholder="Ano"
                            value="<c:out value="${onibus.ano}"/>">
                    </td>
                </tr>
                <tr>
                    <td colspan="3">
                        <input class="input_data" type="text" id="descricao" name="descricao" placeholder="Descrição"
                            value="<c:out value="${onibus.descricao}"/>">
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
