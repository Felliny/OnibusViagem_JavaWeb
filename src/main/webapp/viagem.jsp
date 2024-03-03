<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="./css/styles.css">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Viagem</title>
</head>
<body>
<div>
    <jsp:include page="menu.jsp" />
</div>
<br/>
<div align="center" class="container">
    <form action="viagem" method="post">
        <p class="tittle">
            <b>Viagem</b>
        </p>
        <table>
            <tr>
                <td colspan="2">
                    <input class="input_data_id" type="number" min="0" step="1"
                        id="codigo" name="codigo" placeholder="Codigo"
                        value="<c:out value="${viagem.codigo}"/>">
                </td>
                <td>
                    <input type="submit" id="botao" name="botao" value="Buscar">
                </td>
            </tr>
            <tr>
                <td colspan="3">
                    <input class="input_data" type="text" id="placa" name="placa"
                        placeholder="Placa do Ônibus"
                        value="<c:out value="${viagem.onibus.placa}"/>">
                </td>
            </tr>
            <tr>
                <td colspan="3">
                    <input class="input_data" type="number" id="motorista_codigo"
                           name="motorista_codigo"
                           min="0"
                           step="1"
                           placeholder="Codigo do Motorista"
                           value="<c:out value="${viagem.motorista.codigo}"/>">
                </td>
            </tr>
            <tr>
                <td colspan="3">
                    <input class="input_data" type="number" min="0" id="hora_saida"
                           name="hora_saida"
                           placeholder="Hora de Saída"
                           value="<c:out value="${viagem.hora_saida}"/>">
                </td>
            </tr>
            <tr>
                <td colspan="3">
                    <input class="input_data" type="number" min="0" id="hora_chegada"
                           name="hora_chegada"
                           placeholder="Hora de Chegada"
                           value="<c:out value="${viagem.hora_chegada}"/>">
                </td>
            </tr>
            <tr>
                <td colspan="3">
                    <input class="input_data" type="text" id="partida"
                           name="partida"
                           placeholder="Partida"
                           value="<c:out value="${viagem.partida}"/>">
                </td>
            </tr>
            <tr>
                <td colspan="3">
                    <input class="input_data" type="text" id="destino"
                           name="destino"
                           placeholder="Destino"
                           value="<c:out value="${viagem.destino}"/>">
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
                <td>
                    <input type="submit" id="botao" name="botao"
                           value="Listar Ônibus">
                </td>
                <td>
                    <input type="submit" id="botao" name="botao"
                           value="Listar Viagens">
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
<br/>
<div align="center">
    <table>
        <tr>
            <td>
                <c:if test="${not empty listaOnibus}">
                    <table class="table_round">
                        <thead>
                        <tr>
                            <th>Codigo</th>
                            <th>Motorista</th>
                            <th>Placa do Ônibus</th>
                            <th>Marca</th>
                            <th>Ano</th>
                            <th>Descrição</th>
                        </tr>
                        </thead>
                        <tbody s>
                        <c:forEach var="v" items="${listaOnibus}">
                            <tr>
                                <td><c:out value="${v.codigo}" /></td>
                                <td><c:out value="${v.motorista.nome}" /></td>
                                <td><c:out value="${v.onibus.placa}" /></td>
                                <td><c:out value="${v.onibus.marca}" /></td>
                                <td><c:out value="${v.onibus.ano}" /></td>
                                <td><c:out value="${v.onibus.descricao}" /></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </c:if>
            </td>
            <td>
                <c:if test="${not empty listaViagens}">
                    <table class="table_round">
                        <thead>
                        <tr>
                            <th>Codigo</th>
                            <th>Placa do Ônibus</th>
                            <th>Hora de Saida</th>
                            <th>Hora de Chegada</th>
                            <th>Partida</th>
                            <th>Destino</th>
                        </tr>
                        </thead>
                        <tbody s>
                        <c:forEach var="v" items="${listaViagens}">
                            <tr>
                                <td><c:out value="${v.codigo}" /></td>
                                <td><c:out value="${v.onibus.placa}" /></td>
                                <td><c:out value="${v.hora_saida}" /></td>
                                <td><c:out value="${v.hora_chegada}" /></td>
                                <td><c:out value="${v.partida}" /></td>
                                <td><c:out value="${v.destino}" /></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </c:if>
            </td>
        </tr>
    </table>
</div>
</body>
</html>
