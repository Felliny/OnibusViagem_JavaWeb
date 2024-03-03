package persistence;

import model.Motorista;
import model.Onibus;
import model.Viagem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ViagemDao {

    private GenericDao gDao;

    public ViagemDao(GenericDao gDao) {
        this.gDao = gDao;
    }

    public void inserir(Viagem viagem) throws SQLException, ClassNotFoundException {
        Connection c= gDao.getConnection();

        String sql= "insert into viagem values (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps= c.prepareStatement(sql);
        ps.setInt(1, viagem.getCodigo());
        ps.setString(2, viagem.getOnibus().getPlaca());
        ps.setInt(3, viagem.getMotorista().getCodigo());
        ps.setInt(4, Integer.parseInt(viagem.getHora_saida()));
        ps.setInt(5, Integer.parseInt(viagem.getHora_chegada()));
        ps.setString(6, viagem.getPartida());
        ps.setString(7, viagem.getDestino());
        ps.execute();
        ps.close();
        c.close();
    }

    public void atualizar(Viagem viagem) throws SQLException, ClassNotFoundException {
        Connection c= gDao.getConnection();

        String sql= "update viagem set onibus= ?, motorista= ?, hora_saida= ?, hora_chegada= ?, partida= ?, destino= ?" +
                "where codigo= ?";
        PreparedStatement ps= c.prepareStatement(sql);
        ps.setString(1, viagem.getOnibus().getPlaca());
        ps.setInt(2, viagem.getMotorista().getCodigo());
        ps.setInt(3, Integer.parseInt(viagem.getHora_saida()));
        ps.setInt(4, Integer.parseInt(viagem.getHora_chegada()));
        ps.setString(5, viagem.getPartida());
        ps.setString(6, viagem.getDestino());
        ps.setInt(7, viagem.getCodigo());
        ps.execute();
        ps.close();
        c.close();
    }

    public void excluir(Viagem viagem) throws SQLException, ClassNotFoundException {
        Connection c= gDao.getConnection();

        String sql= "delete viagem where codigo= ?";
        PreparedStatement ps= c.prepareStatement(sql);
        ps.setInt(1, viagem.getCodigo());
        ps.execute();
        ps.close();
        c.close();
    }

    public Viagem consultar(Viagem viagem) throws SQLException, ClassNotFoundException {
        Connection c= gDao.getConnection();

        String sql= "select codigo, onibus, motorista, hora_saida, hora_chegada, partida, destino from viagem " +
                "where codigo= ?";
        PreparedStatement ps= c.prepareStatement(sql);
        ps.setInt(1, viagem.getCodigo());
        ResultSet rs= ps.executeQuery();
        if (rs.next()){
            Onibus onibus= new Onibus();
            Motorista motorista= new Motorista();

            viagem.setCodigo(rs.getInt(1));

            onibus.setPlaca(rs.getString(2));
            motorista.setCodigo(rs.getInt(3));

            viagem.setOnibus(onibus);
            viagem.setMotorista(motorista);
            viagem.setHora_saida(rs.getString(4));
            viagem.setHora_chegada(rs.getString(5));
            viagem.setPartida(rs.getString(6));
            viagem.setDestino(rs.getString(7));
        }
        rs.close();
        ps.close();
        c.close();

        return viagem;
    }

    public List<Viagem> listarOnibus() throws SQLException, ClassNotFoundException {
        Connection c= gDao.getConnection();

        String sql= "select * from v_descricao_onibus";
        PreparedStatement ps= c.prepareStatement(sql);
        ResultSet rs= ps.executeQuery();
        List<Viagem> lista= new ArrayList<>();
        while (rs.next()){
            Viagem viagem= new Viagem();
            Onibus onibus= new Onibus();
            Motorista motorista= new Motorista();

            viagem.setCodigo(rs.getInt(1));

            motorista.setNome(rs.getString(2));

            onibus.setPlaca(rs.getString(3));
            onibus.setMarca(rs.getString(4));
            onibus.setAno(rs.getInt(5));
            onibus.setDescricao(rs.getString(6));

            viagem.setOnibus(onibus);
            viagem.setMotorista(motorista);

            lista.add(viagem);
        }
        rs.close();
        ps.close();
        c.close();

        return lista;
    }

    public List<Viagem> listarViagens() throws SQLException, ClassNotFoundException {
        Connection c= gDao.getConnection();

        String sql= "select  * from v_descricao_viagem";
        PreparedStatement ps= c.prepareStatement(sql);
        ResultSet rs= ps.executeQuery();
        List<Viagem> lista= new ArrayList<>();

        while (rs.next()){
            Viagem viagem= new Viagem();
            Onibus onibus= new Onibus();


            viagem.setCodigo(rs.getInt(1));

            onibus.setPlaca(rs.getString(2));

            viagem.setHora_saida(rs.getString(3));
            viagem.setHora_chegada(rs.getString(4));
            viagem.setPartida(rs.getString(5));
            viagem.setDestino(rs.getString(6));

            viagem.setOnibus(onibus);

            lista.add(viagem);
        }
        ps.close();
        rs.close();
        c.close();

        return lista;
    }
}


