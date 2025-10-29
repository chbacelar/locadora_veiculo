package com.locadora.model;

public class Moto extends Veiculo {
    private int cilindradas;
    private String tipoPartida;

    public Moto(String placa, String marca, String modelo, int ano, double valorDiaria,
                int cilindradas, String tipoPartida) {
        super(placa, marca, modelo, ano, valorDiaria);
        this.cilindradas = cilindradas;
        this.tipoPartida = tipoPartida;
    }

    public int getCilindradas() {
        return cilindradas;
    }

    public void setCilindradas(int cilindradas) {
        this.cilindradas = cilindradas;
    }

    public String getTipoPartida() {
        return tipoPartida;
    }

    public void setTipoPartida(String tipoPartida) {
        this.tipoPartida = tipoPartida;
    }

    @Override
    public String getTipo() {
        return "Moto";
    }

    @Override
    public String exibirInformacoes() {
        return super.exibirInformacoes() +
               String.format("\nCilindradas: %d cc\nTipo de Partida: %s",
                           cilindradas, tipoPartida);
    }
}
