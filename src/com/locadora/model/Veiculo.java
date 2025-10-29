package com.locadora.model;

public abstract class Veiculo {
    private String placa;
    private String marca;
    private String modelo;
    private int ano;
    private double valorDiaria;
    private boolean disponivel;

    public Veiculo(String placa, String marca, String modelo, int ano, double valorDiaria) {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.valorDiaria = valorDiaria;
        this.disponivel = true;
    }

    // Getters e Setters
    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public double getValorDiaria() {
        return valorDiaria;
    }

    public void setValorDiaria(double valorDiaria) {
        this.valorDiaria = valorDiaria;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    // Método abstrato que será implementado pelas classes filhas
    public abstract String getTipo();

    // Método para exibir informações do veículo
    public String exibirInformacoes() {
        return String.format(
            "Tipo: %s\nPlaca: %s\nMarca: %s\nModelo: %s\nAno: %d\nValor Diária: R$ %.2f\nDisponível: %s",
            getTipo(), placa, marca, modelo, ano, valorDiaria, disponivel ? "Sim" : "Não"
        );
    }

    @Override
    public String toString() {
        return String.format("%s - %s %s (%s) - R$ %.2f/dia %s",
            getTipo(), marca, modelo, placa, valorDiaria, disponivel ? "[DISPONÍVEL]" : "[ALUGADO]");
    }
}
