/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controller;

import br.com.model.Funcionario;
import br.com.model.persistencia.FuncionarioDAOJDBC;
import br.com.model.persistencia.dao.FuncionarioDAO;

/**
 *
 * @author gustavo_lourenco
 */
public class FuncionarioController {

    public int insert(Funcionario f) {

        FuncionarioDAO dao = new FuncionarioDAOJDBC();
        return dao.insert(f);

    }
}
