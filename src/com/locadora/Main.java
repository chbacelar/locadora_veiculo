package com.locadora;

import com.locadora.gestor.LocadoraGestor;
import com.locadora.model.Carro;
import com.locadora.model.Moto;
import com.locadora.model.Veiculo;
import javax.swing.JOptionPane;
import java.util.List;

public class Main {
    private static LocadoraGestor gestor = new LocadoraGestor();

    public static void main(String[] args) {
        boolean continuar = true;

        while (continuar) {
            String menu = "=== LOCADORA DE VEÍCULOS ===\n\n"
                    + "1. Cadastrar Veículo\n"
                    + "2. Listar Todos os Veículos\n"
                    + "3. Buscar Veículo por Placa\n"
                    + "4. Atualizar Veículo\n"
                    + "5. Remover Veículo\n"
                    + "6. Alugar Veículo\n"
                    + "7. Devolver Veículo\n"
                    + "8. Listar Veículos Disponíveis\n"
                    + "9. Sair\n\n"
                    + "Escolha uma opção:";

            String opcao = JOptionPane.showInputDialog(null, menu, "Sistema de Locadora",
                    JOptionPane.QUESTION_MESSAGE);

            if (opcao == null) {
                continuar = false;
                continue;
            }

            switch (opcao) {
                case "1":
                    cadastrarVeiculo();
                    break;
                case "2":
                    listarTodosVeiculos();
                    break;
                case "3":
                    buscarVeiculo();
                    break;
                case "4":
                    atualizarVeiculo();
                    break;
                case "5":
                    removerVeiculo();
                    break;
                case "6":
                    alugarVeiculo();
                    break;
                case "7":
                    devolverVeiculo();
                    break;
                case "8":
                    listarVeiculosDisponiveis();
                    break;
                case "9":
                    continuar = false;
                    JOptionPane.showMessageDialog(null, "Obrigado por usar o sistema!",
                            "Até logo", JOptionPane.INFORMATION_MESSAGE);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida!",
                            "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private static void cadastrarVeiculo() {
        String[] tipos = {"Carro", "Moto"};
        String tipo = (String) JOptionPane.showInputDialog(null,
                "Selecione o tipo de veículo:",
                "Tipo de Ve \"Selecione o tipo de veículículo",
                JOptionPane.QUESTION_MESSAGE,
                null,
                tipos,
                tipos[0]);

        if (tipo == null) return;

        try {
            String placa = JOptionPane.showInputDialog("Digite a placa:");
            if (placa == null || placa.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Placa é obrigatória!",
                        "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String marca = JOptionPane.showInputDialog("Digite a marca:");
            if (marca == null || marca.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Marca é obrigatória!",
                        "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String modelo = JOptionPane.showInputDialog("Digite o modelo:");
            if (modelo == null || modelo.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Modelo é obrigatório!",
                        "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String anoStr = JOptionPane.showInputDialog("Digite o ano:");
            if (anoStr == null || anoStr.trim().isEmpty()) return;
            int ano = Integer.parseInt(anoStr);

            String valorStr = JOptionPane.showInputDialog("Digite o valor da diária:");
            if (valorStr == null || valorStr.trim().isEmpty()) return;
            double valorDiaria = Double.parseDouble(valorStr);

            Veiculo veiculo;

            if (tipo.equals("Carro")) {
                String portasStr = JOptionPane.showInputDialog("Digite o número de portas:");
                if (portasStr == null || portasStr.trim().isEmpty()) return;
                int numeroPortas = Integer.parseInt(portasStr);

                String tipoCombustivel = JOptionPane.showInputDialog("Digite o tipo de combustível:");
                if (tipoCombustivel == null || tipoCombustivel.trim().isEmpty()) return;

                veiculo = new Carro(placa, marca, modelo, ano, valorDiaria,
                        numeroPortas, tipoCombustivel);
            } else {
                String cilindradasStr = JOptionPane.showInputDialog("Digite as cilindradas:");
                if (cilindradasStr == null || cilindradasStr.trim().isEmpty()) return;
                int cilindradas = Integer.parseInt(cilindradasStr);

                String tipoPartida = JOptionPane.showInputDialog("Digite o tipo de partida:");
                if (tipoPartida == null || tipoPartida.trim().isEmpty()) return;

                veiculo = new Moto(placa, marca, modelo, ano, valorDiaria,
                        cilindradas, tipoPartida);
            }

            gestor.adicionarVeiculo(veiculo);
            JOptionPane.showMessageDialog(null, "Veículo cadastrado com sucesso!",
                    "Sucesso", JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Valor numérico inválido!",
                    "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void listarTodosVeiculos() {
        List<Veiculo> veiculos = gestor.listarTodos();

        if (veiculos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum veículo cadastrado!",
                    "Informação", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        StringBuilder sb = new StringBuilder("=== LISTA DE VEÍCULOS ===\n\n");
        for (int i = 0; i < veiculos.size(); i++) {
            sb.append((i + 1)).append(". ").append(veiculos.get(i).toString()).append("\n");
        }

        JOptionPane.showMessageDialog(null, sb.toString(),
                "Veículos Cadastrados", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void buscarVeiculo() {
        String placa = JOptionPane.showInputDialog("Digite a placa do veículo:");
        if (placa == null || placa.trim().isEmpty()) return;

        Veiculo veiculo = gestor.buscarPorPlaca(placa);

        if (veiculo == null) {
            JOptionPane.showMessageDialog(null, "Veículo não encontrado!",
                    "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, veiculo.exibirInformacoes(),
                    "Informações do Veículo", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private static void atualizarVeiculo() {
        String placa = JOptionPane.showInputDialog("Digite a placa do veículo a atualizar:");
        if (placa == null || placa.trim().isEmpty()) return;

        Veiculo veiculoExistente = gestor.buscarPorPlaca(placa);

        if (veiculoExistente == null) {
            JOptionPane.showMessageDialog(null, "Veículo não encontrado!",
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            String marca = JOptionPane.showInputDialog("Nova marca:", veiculoExistente.getMarca());
            if (marca == null || marca.trim().isEmpty()) return;

            String modelo = JOptionPane.showInputDialog("Novo modelo:", veiculoExistente.getModelo());
            if (modelo == null || modelo.trim().isEmpty()) return;

            String anoStr = JOptionPane.showInputDialog("Novo ano:", veiculoExistente.getAno());
            if (anoStr == null || anoStr.trim().isEmpty()) return;
            int ano = Integer.parseInt(anoStr);

            String valorStr = JOptionPane.showInputDialog("Novo valor da diária:",
                    veiculoExistente.getValorDiaria());
            if (valorStr == null || valorStr.trim().isEmpty()) return;
            double valorDiaria = Double.parseDouble(valorStr);

            veiculoExistente.setMarca(marca);
            veiculoExistente.setModelo(modelo);
            veiculoExistente.setAno(ano);
            veiculoExistente.setValorDiaria(valorDiaria);

            JOptionPane.showMessageDialog(null, "Veículo atualizado com sucesso!",
                    "Sucesso", JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Valor numérico inválido!",
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void removerVeiculo() {
        String placa = JOptionPane.showInputDialog("Digite a placa do veículo a remover:");
        if (placa == null || placa.trim().isEmpty()) return;

        int confirmacao = JOptionPane.showConfirmDialog(null,
                "Tem certeza que deseja remover o veículo?",
                "Confirmação",
                JOptionPane.YES_NO_OPTION);

        if (confirmacao == JOptionPane.YES_OPTION) {
            if (gestor.removerVeiculo(placa)) {
                JOptionPane.showMessageDialog(null, "Veículo removido com sucesso!",
                        "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Veículo não encontrado!",
                        "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private static void alugarVeiculo() {
        String placa = JOptionPane.showInputDialog("Digite a placa do veículo a alugar:");
        if (placa == null || placa.trim().isEmpty()) return;

        if (gestor.alugarVeiculo(placa)) {
            JOptionPane.showMessageDialog(null, "Veículo alugado com sucesso!",
                    "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null,
                    "Veículo não encontrado ou não está disponível!",
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void devolverVeiculo() {
        String placa = JOptionPane.showInputDialog("Digite a placa do veículo a devolver:");
        if (placa == null || placa.trim().isEmpty()) return;

        if (gestor.devolverVeiculo(placa)) {
            JOptionPane.showMessageDialog(null, "Veículo devolvido com sucesso!",
                    "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null,
                    "Veículo não encontrado ou não está alugado!",
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void listarVeiculosDisponiveis() {
        List<Veiculo> veiculos = gestor.listarDisponiveis();

        if (veiculos.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Nenhum veículo disponível no momento!",
                    "Informação", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        StringBuilder sb = new StringBuilder("=== VEÍCULOS DISPONÍVEIS ===\n\n");
        for (int i = 0; i < veiculos.size(); i++) {
            sb.append((i + 1)).append(". ").append(veiculos.get(i).toString()).append("\n");
        }

        JOptionPane.showMessageDialog(null, sb.toString(),
                "Veículos Disponíveis", JOptionPane.INFORMATION_MESSAGE);
    }
}
