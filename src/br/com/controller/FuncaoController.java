/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controller;

import br.com.model.Funcao;
import br.com.model.persistencia.FuncaoDAOJDBC;
import br.com.model.persistencia.dao.FuncaoDAO;
import java.util.List;

/**
 *
 * @author gustavo_lourenco
 */
public class FuncaoController {

    public int insert(Funcao f) {

        FuncaoDAO dao = new FuncaoDAOJDBC();
        return dao.insert(f);

    }

    public List<Funcao> ListarTodos() {
        FuncaoDAO dao = new FuncaoDAOJDBC();
        return dao.listAll();
    }

    public boolean remove(int id) {
        FuncaoDAO dao = new FuncaoDAOJDBC();
        return dao.remove(id);
    }

    public int update(Funcao f) {
        FuncaoDAO dao = new FuncaoDAOJDBC();
        return dao.update(f);
    }

    public Funcao listById(int id) {
        FuncaoDAO dao = new FuncaoDAOJDBC();
        return dao.listById(id);
    }
}
