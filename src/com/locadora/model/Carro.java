package com.locadora.model;

public class Carro extends Veiculo {
    private int numeroPortas;
    private String tipoCombustivel;

    public Carro(String placa, String marca, String modelo, int ano, double valorDiaria,
                 int numeroPortas, String tipoCombustivel) {
        super(placa, marca, modelo, ano, valorDiaria);
        this.numeroPortas = numeroPortas;
        this.tipoCombustivel = tipoCombustivel;
    }

    public int getNumeroPortas() {
        return numeroPortas;
    }

    public void setNumeroPortas(int numeroPortas) {
        this.numeroPortas = numeroPortas;
    }

    public String getTipoCombustivel() {
        return tipoCombustivel;
    }

    public void setTipoCombustivel(String tipoCombustivel) {
        this.tipoCombustivel = tipoCombustivel;
    }

    @Override
    public String getTipo() {
        return "Carro";
    }

    @Override
    public String exibirInformacoes() {
        return super.exibirInformacoes() +
               String.format("\nNúmero de Portas: %d\nTipo de Combustível: %s",
                           numeroPortas, tipoCombustivel);
    }
}
