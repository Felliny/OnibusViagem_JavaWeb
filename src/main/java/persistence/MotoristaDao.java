package persistence;

import model.Motorista;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MotoristaDao implements ICrud<Motorista> {

    private GenericDao gDao;

    public MotoristaDao(GenericDao gDao) {
        this.gDao = gDao;
    }

    @Override
    public void inserir(Motorista motorista) throws SQLException, ClassNotFoundException {
        Connection c = gDao.getConnection();

        String sql= "insert into motorista values (?, ?, ?)";
        PreparedStatement ps= c.prepareStatement(sql);
        ps.setInt(1, motorista.getCodigo());
        ps.setString(2, motorista.getNome());
        ps.setString(3, motorista.getNaturalidade());
        ps.execute();
        ps.close();
        c.close();
    }

    @Override
    public void atualizar(Motorista motorista) throws SQLException, ClassNotFoundException {
        Connection c = gDao.getConnection();

        String sql= "update motorista set nome= ?, naturalidade= ? where codigo= ?";
        PreparedStatement ps= c.prepareStatement(sql);
        ps.setString(1, motorista.getNome());
        ps.setString(2, motorista.getNaturalidade());
        ps.setInt(3, motorista.getCodigo());
        ps.execute();
        ps.close();
        c.close();
    }

    @Override
    public void excluir(Motorista motorista) throws SQLException, ClassNotFoundException {
        Connection c = gDao.getConnection();

        String sql= "delete motorista where codigo= ?";
        PreparedStatement ps= c.prepareStatement(sql);
        ps.setInt(1, motorista.getCodigo());
        ps.execute();
        ps.close();
        c.close();
    }

    @Override
    public Motorista consultar(Motorista motorista) throws SQLException, ClassNotFoundException {
        Connection c = gDao.getConnection();

        String sql= "select codigo, nome, naturalidade from motorista where codigo= ?";
        PreparedStatement ps= c.prepareStatement(sql);
        ps.setInt(1, motorista.getCodigo());
        ResultSet rs=ps.executeQuery();
        if (rs.next()){
            motorista.setCodigo(rs.getInt(1));
            motorista.setNome(rs.getString(2));
            motorista.setNaturalidade(rs.getString(3));
        }
        rs.close();
        ps.close();
        c.close();
        return motorista;
    }
}
