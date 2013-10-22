/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.model.persistencia.dao;

import br.com.model.Funcao;
import java.util.List;

/**
 *
 * @author gustavo_lourenco
 */
public interface FuncaoDAO {

    int insert(Funcao f);

    List<Funcao> listAll();
}
