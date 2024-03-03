package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Onibus;
import persistence.GenericDao;
import persistence.OnibusDao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/onibus")
public class OnibusServlet extends HttpServlet {

    GenericDao genericDao= new GenericDao();
    OnibusDao onibusDao= new OnibusDao(genericDao);

    public OnibusServlet(){
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Entrada
        String cmd = req.getParameter("botao");
        String placa= req.getParameter("placa");
        String marca = req.getParameter("marca");
        String ano = req.getParameter("ano");
        String descricao = req.getParameter("descricao");

        //Retorno
        String saida = "";
        String erro = "";
        Onibus onibus= new Onibus();


        if (cmd.contains("Cadastrar") || cmd.contains("Alterar")){
            onibus.setPlaca(placa);
            onibus.setMarca(marca);
            onibus.setAno(Integer.parseInt(ano));
            onibus.setDescricao(descricao);
        }
        if (cmd.contains("Excluir") || cmd.contains("Buscar")){
            onibus.setPlaca(placa);
        }
        try {
            if (cmd.contains("Cadastrar")){
                cadastrarOnibus(onibus);
                saida= "Ônibus cadastrado com sucesso";
                onibus= null;
            }
            if (cmd.contains("Alterar")){
                alterarOnibus(onibus);
                saida= "ônibus alterado com sucesso";
                onibus= null;
            }
            if (cmd.contains("Excluir")){
                excluirOnibus(onibus);
                saida= "Ônibus excluido com sucesso";
                onibus= null;
            }
            if (cmd.contains("Buscar")){
                onibus= buscarOnibus(onibus);
            }
        }
        catch (SQLException | ClassNotFoundException e){
            erro = e.getMessage();
        } finally {
            req.setAttribute("saida", saida);
            req.setAttribute("erro", erro);
            req.setAttribute("onibus", onibus);

            RequestDispatcher rd= req.getRequestDispatcher("onibus.jsp");
            rd.forward(req, resp);
        }


    }

    private void cadastrarOnibus(Onibus onibus) throws SQLException, ClassNotFoundException{
        onibusDao.inserir(onibus);
    }

    private void alterarOnibus(Onibus onibus) throws SQLException, ClassNotFoundException{
        onibusDao.atualizar(onibus);
    }

    private void excluirOnibus(Onibus onibus) throws SQLException, ClassNotFoundException{
        onibusDao.excluir(onibus);
    }

    private Onibus buscarOnibus(Onibus onibus) throws SQLException, ClassNotFoundException{
        onibus= onibusDao.consultar(onibus);

        return onibus;
    }
}
