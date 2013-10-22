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
}
