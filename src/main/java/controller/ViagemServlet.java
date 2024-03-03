package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Motorista;
import model.Onibus;
import model.Viagem;
import persistence.GenericDao;
import persistence.MotoristaDao;
import persistence.OnibusDao;
import persistence.ViagemDao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/viagem")
public class ViagemServlet extends HttpServlet {


    GenericDao genericDao= new GenericDao();

    OnibusDao onibusDao= new OnibusDao(genericDao);

    MotoristaDao motoristaDao= new MotoristaDao(genericDao);

    ViagemDao viagemDao= new ViagemDao(genericDao);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cmd= req.getParameter("botao");
        String codigo= req.getParameter("codigo");
        String placa= req.getParameter("placa");
        String motorista_codigo= req.getParameter("motorista_codigo");
        String hora_saida= req.getParameter("hora_saida");
        String hora_chegada= req.getParameter("hora_chegada");
        String partida= req.getParameter("partida");
        String destino= req.getParameter("destino");

        String saida= "";
        String erro= "";
        Viagem viagem= new Viagem();
        Onibus onibus= new Onibus();
        Motorista motorista= new Motorista();
        List<Viagem> listaOnibus = new ArrayList<>();
        List<Viagem> listaViagens = new ArrayList<>();

        try {
            if (cmd.contains("Cadastrar") || cmd.contains("Alterar")){
                viagem.setCodigo(Integer.parseInt(codigo));
                onibus.setPlaca(placa);
                viagem.setOnibus(carregarOnibus(onibus));
                motorista.setCodigo(Integer.parseInt(motorista_codigo));
                viagem.setMotorista(carregarMotorista(motorista));
                viagem.setHora_saida(hora_saida);
                viagem.setHora_chegada(hora_chegada);
                viagem.setPartida(partida);
                viagem.setDestino(destino);
            }
            if (cmd.contains("Buscar") || cmd.contains("Excluir")){
                viagem.setCodigo(Integer.parseInt(codigo));
            }
            if (cmd.contains("Cadastrar")){
                cadastrarViagem(viagem);
                saida= "Viagem cadastrada com sucesso";
                viagem= null;
            }
            if (cmd.contains("Alterar")){
                atualizarViagem(viagem);
                saida= "Viagem alterada com sucesso";
                viagem= null;
            }
            if (cmd.contains("Excluir")){
                excluirViagem(viagem);
                saida= "Viagem excluida com sucesso";
                viagem= null;
            }
            if (cmd.contains("Buscar")){
                viagem= consultarViagem(viagem);
            }
            if (cmd.contains("Listar Ônibus")){
                listaOnibus = listarOnibus();
            }
            if (cmd.contains("Listar Viagens")){
                listaViagens = listarViagens();
            }
        } catch (SQLException | ClassNotFoundException e){
            erro = e.getMessage();
        } finally {
            req.setAttribute("saida", saida);
            req.setAttribute("erro", erro);
            req.setAttribute("viagem", viagem);
            req.setAttribute("listaOnibus", listaOnibus);
            req.setAttribute("listaViagens", listaViagens);

            RequestDispatcher rd= req.getRequestDispatcher("viagem.jsp");
            rd.forward(req, resp);
        }
    }

    private void cadastrarViagem(Viagem viagem) throws SQLException, ClassNotFoundException {
        viagemDao.inserir(viagem);
    }

    private void atualizarViagem(Viagem viagem) throws SQLException, ClassNotFoundException {
        viagemDao.atualizar(viagem);
    }

    private void excluirViagem(Viagem viagem) throws SQLException, ClassNotFoundException {
        viagemDao.excluir(viagem);
    }

    private Viagem consultarViagem(Viagem viagem) throws SQLException, ClassNotFoundException {
        viagem= viagemDao.consultar(viagem);
        return viagem;
    }

    private List<Viagem> listarOnibus() throws SQLException, ClassNotFoundException {
        return viagemDao.listarOnibus();
    }

    private List<Viagem> listarViagens() throws SQLException, ClassNotFoundException {
        return viagemDao.listarViagens();
    }

    private Motorista carregarMotorista(Motorista motorista) throws SQLException, ClassNotFoundException {
        motorista= motoristaDao.consultar(motorista);

        return motorista;
    }

    private Onibus carregarOnibus(Onibus onibus) throws SQLException, ClassNotFoundException {
        onibus= onibusDao.consultar(onibus);

        return onibus;
    }
}
