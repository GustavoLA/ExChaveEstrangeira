/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.model.persistencia;

import br.com.model.Funcao;
import br.com.model.persistencia.dao.FuncaoDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author gustavo_lourenco
 */
public class FuncaoDAOJDBC implements FuncaoDAO {

    private static final String INSERT = "insert into funcao(nome) values (?)";
    private static final String LIST = "select * from funcao";
    private static final String REMOVE = "delete from funcao where codigo = ?";
    private static final String UPDATE = "update funcao set nome = ?, where codigo = ?";
    private static final String LISTBYID = "select * from funcao where codigo = ?";

    public int insert(Funcao f) {

        Connection conn = null;
        PreparedStatement pstm = null;
        int retorno = -1;

        try {
            conn = ConnectionFactory.getConnection();
            pstm = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1, f.getNome());
            pstm.execute();
            try (ResultSet rs = pstm.getGeneratedKeys()) {

                if (rs.next()) {
                    retorno = (rs.getInt(1));
                }
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Não foi possível efetuar a transação" + ex.getMessage());
        } finally {
            try {
                ConnectionFactory.closeConnection(conn, pstm);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar a conexão" + ex.getMessage());
            }
        }
        return retorno;
    }

    @Override
    public List<Funcao> listAll() {

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<Funcao> funcoes = new ArrayList<>();

        try {
            conn = ConnectionFactory.getConnection();
            pstm = conn.prepareStatement(LIST);
            rs = pstm.executeQuery();

            while (rs.next()) {
                Funcao f = new Funcao();
                f.setCodigo(rs.getInt("codigo"));
                f.setNome(rs.getString("nome"));
                funcoes.add(f);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao listar" + ex.getMessage());

        } finally {

            try {
                ConnectionFactory.closeConnection(conn, pstm, rs);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar conexão" + ex.getMessage());
            }
        }
        return funcoes;
    }

    @Override
    public boolean remove(int id) {
        boolean retorno = false;
        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = ConnectionFactory.getConnection();
            pstm = conn.prepareStatement(REMOVE);
            pstm.setInt(1, id);
            pstm.execute();
            retorno = true;

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao remover informações " + ex.getMessage());
        } finally {
            try {
                ConnectionFactory.closeConnection(conn, pstm);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar conexão " + ex.getMessage());
            }
        }
        return retorno;
    }

    @Override
    public int update(Funcao f) {

        int retorno = -1;
        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = ConnectionFactory.getConnection();
            pstm = conn.prepareStatement(UPDATE);
            pstm.setString(1, f.getNome());
            pstm.setInt(2, f.getCodigo());
            pstm.execute();
            retorno = f.getCodigo();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao editar informações " + ex.getMessage());
        } finally {
            try {
                ConnectionFactory.closeConnection(conn, pstm);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar conexão " + ex.getMessage());
            }
        }
        return retorno;

    }

    @Override
    public Funcao listById(int id) {

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        Funcao f = new Funcao();

        try {
            conn = ConnectionFactory.getConnection();
            pstm = conn.prepareStatement(LISTBYID);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();

            while (rs.next()) {

                f.setCodigo(rs.getInt("codigo"));
                f.setNome(rs.getString("nome"));

            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao listar" + ex.getMessage());

        } finally {

            try {
                ConnectionFactory.closeConnection(conn, pstm, rs);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar conexão" + ex.getMessage());
            }
        }
        return f;

    }
}
