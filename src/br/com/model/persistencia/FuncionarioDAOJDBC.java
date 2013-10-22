/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.model.persistencia;

import br.com.model.Funcionario;
import br.com.model.persistencia.dao.FuncionarioDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author gustavo_lourenco
 */
public class FuncionarioDAOJDBC implements FuncionarioDAO {

    private static final String INSERT = "insert into funcionario(nome, codigo_funcao) values (?, ?)";

    public int insert(Funcionario f) {

        Connection conn = null;
        PreparedStatement pstm = null;
        int retorno = -1;

        try {
            conn = ConnectionFactory.getConnection();
            pstm = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1, f.getNome());
            pstm.setInt(2, f.getFuncao().getCodigo());
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
}
