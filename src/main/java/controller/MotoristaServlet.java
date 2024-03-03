package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Motorista;
import persistence.GenericDao;
import persistence.MotoristaDao;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/motorista")
public class MotoristaServlet extends HttpServlet {
    GenericDao genericDao= new GenericDao();

    MotoristaDao motoristaDao= new MotoristaDao(genericDao);

    public MotoristaServlet(){
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Entrada
        String cmd = req.getParameter("botao");
        String codigo= req.getParameter("codigo");
        String nome = req.getParameter("nome");
        String naturalidade = req.getParameter("naturalidade");

        //Retorno
        String saida = "";
        String erro = "";
        Motorista motorista= new Motorista();


        if (cmd.contains("Cadastrar") || cmd.contains("Alterar")){
            motorista.setCodigo(Integer.parseInt(codigo));
            motorista.setNome(nome);
            motorista.setNaturalidade(naturalidade);
        }
        if (cmd.contains("Excluir") || cmd.contains("Buscar")){
            motorista.setCodigo(Integer.parseInt(codigo));
        }
        try {
            if (cmd.contains("Cadastrar")){
                cadastrarMotorista(motorista);
                saida= "Motorista cadastrado com sucesso";
                motorista= null; //limpar campos
            }
            if (cmd.contains("Alterar")){
                alterarMotorista(motorista);
                saida= "motorista alterado com sucesso";
                motorista= null;
            }
            if (cmd.contains("Excluir")){
                excluirMotorista(motorista);
                saida= "Motorista excluido com sucesso";
                motorista= null;
            }
            if (cmd.contains("Buscar")){
                motorista= buscarMotorista(motorista);
            }
        }
        catch (SQLException | ClassNotFoundException e){
            erro = e.getMessage();
        } finally {
            req.setAttribute("saida", saida);
            req.setAttribute("erro", erro);
            req.setAttribute("motorista", motorista);

            RequestDispatcher rd= req.getRequestDispatcher("motorista.jsp");
            rd.forward(req, resp);
        }


    }

    private void cadastrarMotorista(Motorista motorista) throws SQLException, ClassNotFoundException{
        motoristaDao.inserir(motorista);
    }

    private void alterarMotorista(Motorista motorista) throws SQLException, ClassNotFoundException{
        motoristaDao.atualizar(motorista);
    }

    private void excluirMotorista(Motorista motorista) throws SQLException, ClassNotFoundException{
        motoristaDao.excluir(motorista);
    }

    private Motorista buscarMotorista(Motorista motorista) throws SQLException, ClassNotFoundException{
        motorista= motoristaDao.consultar(motorista);

        return motorista;
    }
}
