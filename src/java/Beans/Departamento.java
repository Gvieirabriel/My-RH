/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.io.Serializable;

/**
 *
 * @author guilh
 */
public class Departamento implements Serializable {
    private int idDepartamento;
    private String nomeDepartamento;
    private String localizacao;
    
    public Departamento() {
        
    }
    
    public int getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(int idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public String getNomeDepartamento() {
        return nomeDepartamento;
    }

    public void setNomeDepartamento(String nomeDepartamento) {
        this.nomeDepartamento = nomeDepartamento;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }
    
    public boolean validaDepartamento(Departamento departamento) {
        if (departamento.getLocalizacao().equals("")|| departamento.getNomeDepartamento().equals(""))
            return false;
        return true;
    }
}
