package com.locadora.gestor;

import com.locadora.model.Veiculo;
import java.util.ArrayList;
import java.util.List;

public class LocadoraGestor {
    private List<Veiculo> veiculos;

    public LocadoraGestor() {
        this.veiculos = new ArrayList<>();
    }

    // Adicionar veículo
    public void adicionarVeiculo(Veiculo veiculo) {
        if (buscarPorPlaca(veiculo.getPlaca()) != null) {
            throw new IllegalArgumentException("Já existe um veículo com esta placa!");
        }
        veiculos.add(veiculo);
    }

    // Remover veículo
    public boolean removerVeiculo(String placa) {
        Veiculo veiculo = buscarPorPlaca(placa);
        if (veiculo != null) {
            veiculos.remove(veiculo);
            return true;
        }
        return false;
    }

    // Buscar veículo por placa
    public Veiculo buscarPorPlaca(String placa) {
        for (Veiculo veiculo : veiculos) {
            if (veiculo.getPlaca().equalsIgnoreCase(placa)) {
                return veiculo;
            }
        }
        return null;
    }

    // Listar todos os veículos
    public List<Veiculo> listarTodos() {
        return new ArrayList<>(veiculos);
    }

    // Listar veículos disponíveis
    public List<Veiculo> listarDisponiveis() {
        List<Veiculo> disponiveis = new ArrayList<>();
        for (Veiculo veiculo : veiculos) {
            if (veiculo.isDisponivel()) {
                disponiveis.add(veiculo);
            }
        }
        return disponiveis;
    }

    // Alugar veículo
    public boolean alugarVeiculo(String placa) {
        Veiculo veiculo = buscarPorPlaca(placa);
        if (veiculo != null && veiculo.isDisponivel()) {
            veiculo.setDisponivel(false);
            return true;
        }
        return false;
    }

    // Devolver veículo
    public boolean devolverVeiculo(String placa) {
        Veiculo veiculo = buscarPorPlaca(placa);
        if (veiculo != null && !veiculo.isDisponivel()) {
            veiculo.setDisponivel(true);
            return true;
        }
        return false;
    }

    // Atualizar veículo
    public boolean atualizarVeiculo(String placa, Veiculo veiculoAtualizado) {
        Veiculo veiculo = buscarPorPlaca(placa);
        if (veiculo != null) {
            veiculo.setMarca(veiculoAtualizado.getMarca());
            veiculo.setModelo(veiculoAtualizado.getModelo());
            veiculo.setAno(veiculoAtualizado.getAno());
            veiculo.setValorDiaria(veiculoAtualizado.getValorDiaria());
            return true;
        }
        return false;
    }

    // Verificar se há veículos cadastrados
    public boolean temVeiculos() {
        return !veiculos.isEmpty();
    }

    // Obter quantidade de veículos
    public int getQuantidadeVeiculos() {
        return veiculos.size();
    }
}
