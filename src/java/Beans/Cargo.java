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
public class Cargo implements Serializable {
    private int idCargo;
    private String nomeCargo;
    private float salario;
    private String requisitos;
    private int cargaMinima;
    private int descontoImpostos;
    
    public Cargo() {
        
    }
    
    public int getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(int idCargo) {
        this.idCargo = idCargo;
    }

    public String getNomeCargo() {
        return nomeCargo;
    }

    public void setNomeCargo(String nomeCargo) {
        this.nomeCargo = nomeCargo;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public String getRequisitos() {
        return requisitos;
    }

    public void setRequisitos(String requisitos) {
        this.requisitos = requisitos;
    }

    public int getCargaMinima() {
        return cargaMinima;
    }

    public void setCargaMinima(int cargaMinima) {
        this.cargaMinima = cargaMinima;
    }

    public int getDescontoImpostos() {
        return descontoImpostos;
    }

    public void setDescontoImpostos(int descontoImpostos) {
        this.descontoImpostos = descontoImpostos;
    }
    
    public boolean validaCargo(Cargo cargo){
        if (cargo.getDescontoImpostos() > 100) 
            //return false;
        if (cargo.getDescontoImpostos() < 0)
            return false;
        if (cargo.getSalario() < 0)
            return false;
        if (cargo.getNomeCargo().equals("")|| cargo.getRequisitos().equals(""))
            return false;
        return cargo.getCargaMinima() >= 0;
    }
}
