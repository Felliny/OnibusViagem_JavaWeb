package persistence;

import model.Onibus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OnibusDao implements ICrud<Onibus> {

    private GenericDao gDao;

    public OnibusDao(GenericDao gDao) {
        this.gDao = gDao;
    }

    @Override
    public void inserir(Onibus onibus) throws SQLException, ClassNotFoundException {
        Connection c= gDao.getConnection();

        String sql= "insert into onibus values (?, ?, ?, ?)";
        PreparedStatement ps= c.prepareStatement(sql);
        ps.setString(1, onibus.getPlaca());
        ps.setString(2, onibus.getMarca());
        ps.setInt(3, onibus.getAno());
        ps.setString(4, onibus.getDescricao());
        ps.execute();
        ps.close();
        c.close();
    }

    @Override
    public void atualizar(Onibus onibus) throws SQLException, ClassNotFoundException {
        Connection c= gDao.getConnection();

        String sql= "update onibus set marca= ?, ano= ?, descricao= ? where placa= ?";
        PreparedStatement ps= c.prepareStatement(sql);
        ps.setString(1, onibus.getMarca());
        ps.setInt(2, onibus.getAno());
        ps.setString(3, onibus.getDescricao());
        ps.setString(4, onibus.getPlaca());
        ps.execute();
        ps.close();
        c.close();
    }

    @Override
    public void excluir(Onibus onibus) throws SQLException, ClassNotFoundException {
        Connection c= gDao.getConnection();

        String sql= "delete onibus where placa= ?";
        PreparedStatement ps= c.prepareStatement(sql);
        ps.setString(1, onibus.getPlaca());
        ps.execute();
        ps.close();
        c.close();
    }

    @Override
    public Onibus consultar(Onibus onibus) throws SQLException, ClassNotFoundException {
        Connection c= gDao.getConnection();

        String sql= "select placa, marca, ano, descricao from onibus where placa = ?";
        PreparedStatement ps= c.prepareStatement(sql);
        ps.setString(1, onibus.getPlaca());
        ResultSet rs= ps.executeQuery();
        if (rs.next()) {
            onibus.setPlaca(rs.getString(1));
            onibus.setMarca(rs.getString(2));
            onibus.setAno(rs.getInt(3));
            onibus.setDescricao(rs.getString(4));
        }
        rs.close();
        ps.close();
        c.close();
        return onibus;
    }
}
