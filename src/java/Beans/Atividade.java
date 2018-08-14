/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.io.Serializable;
import java.sql.Date;
import java.util.Calendar;

/**
 *
 * @author guilh
 */
public class Atividade implements Serializable {
    private int idAtividade;
    private String estado;
    private int idEstado;
    private TipoAtividade tipoAtividade;
    private Funcionario funcionario;
    private Departamento departamento;
    private String descricao;
    private Date inicio;
    private Date fim;
    
    public Atividade() {
        
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public int getIdAtividade() {
        return idAtividade;
    }

    public void setIdAtividade(int idAtividade) {
        this.idAtividade = idAtividade;
    }

    public TipoAtividade getTipoAtividade() {
        return tipoAtividade;
    }

    public void setTipoAtividade(TipoAtividade tipoAtividade) {
        this.tipoAtividade = tipoAtividade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getFim() {
        return fim;
    }

    public void setFim(Date fim) {
        this.fim = fim;
    }
    
    public boolean validaAtividade(Atividade atividade) {
        java.sql.Date data = new Date(Calendar.getInstance().getTimeInMillis());
        if (atividade.getInicio().after(data)) {
            return false;
        }
        if (atividade.getFim() != null) {
            if (atividade.getInicio().after(atividade.getFim())) {
                return false;
            }
            if (atividade.getFim().after(data)) {
                return false;
            }
        }
        return true;
    }
    
    public boolean validaData(Date inicio, Date fim) {
        
        return true;
    }
}
