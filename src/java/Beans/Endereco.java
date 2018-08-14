/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author guilh
 */
public class Endereco implements Serializable {
    private int idUf;
    private int idEndereco;
    private String uf;
    private String rua;
    private int numero;
    private String bairro;
    private String cep;
    private String cidade;
    private String cepFormatado;
    
    public Endereco() {
        
    }

    public int getIdUf() {
        return idUf;
    }

    public void setIdUf(int idUf) {
        this.idUf = idUf;
    }

    public int getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(int idEndereco) {
        this.idEndereco = idEndereco;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getCepFormatado() {
        return cepFormatado;
    }

    public void setCepFormatado(String cepFormatado) {
        Pattern pattern = Pattern.compile("(\\d{5})(\\d{3})");
        Matcher matcher = pattern.matcher(cepFormatado);
        if (matcher.matches()) 
            this.cepFormatado = matcher.replaceAll("$1-$2");
    }
    
    public boolean validaEndereco (Endereco endereco) {
        if (endereco.getCidade().equals("") || endereco.getCidade() == null)
            return false;
        if (endereco.getBairro().equals("") || endereco.getBairro() == null)
            return false;
        return true;
    }
}
