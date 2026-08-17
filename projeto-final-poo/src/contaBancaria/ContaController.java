package contaBancaria;

public class ContaController {

    private ContaBancaria[] contas = new ContaBancaria[10];
    private int total = 0;
    private ContaView view = new ContaView();

    public void iniciar() {

        int opcao;
        do {
            opcao = view.exibirMenu();
            switch (opcao) {
                case 1 -> criarConta();
                case 2 -> depositar();
                case 3 -> sacar();
                case 4 -> emitirExtrato();
                case 5 -> view.listarContas(contas, total);
                case 0 -> view.exibirMensagem("Encerrando...");
                default -> view.exibirMensagem("Opção inválida");
            }
        } while (opcao != 0);
    }

    private void criarConta() {
        if (total == contas.length) {
            view.exibirMensagem("Limite de contas atingido.");
            return;
        }

        int tipo = view.escolherTipoConta();
        String titular = view.lerTexto("Titular");
        int agencia = view.lerInteiro("Agência");
        double saldoInicial = view.lerValor("Saldo inicial: R$");
        int numeroConta = 1000 + total;

        switch (tipo) {
            case 1 -> {
                double limite = view.lerValor("Limite do cheque especial: R$");
                contas[total] = new ContaCorrente(titular, agencia, numeroConta, saldoInicial, limite);
            }
            case 2 -> {
                double taxaJuros = view.lerValor("Taxa de Juros (ex.: 0.005 para 0.5% ) ");
                contas[total] = new ContaPoupanca(titular, agencia, numeroConta, saldoInicial, taxaJuros);
            }
            default -> {
                view.exibirMensagem("Tipo de conta inválido.");
                return;
            }
        }
        total++;
        view.exibirMensagem("Conta criada com sucesso. Número: " + numeroConta);
    }

    private void depositar() {
        ContaBancaria conta = selecionarConta();
        if (conta == null) {
            return;
        }

        double valor = view.lerValor("Valor para depósito: ");
        conta.depositar(valor);
    }

    private void sacar() {
        ContaBancaria conta = selecionarConta();
        if (conta == null) {
            return;
        }
        double valor = view.lerValor("Valor para saque: ");
        conta.sacar(valor);
    }

    private void emitirExtrato() {
        ContaBancaria conta = selecionarConta();
        if (conta == null) {
            return;
        }
        conta.emitirExtrato();
    }

    private ContaBancaria selecionarConta() {
        if (total == 0) {
            view.exibirMensagem("Nenhuma conta cadastrada.");
            return null;
        }
        int numeroConta = view.lerInteiro("Número da conta: ");
        for (int i = 0; i < total; i++) {
            if (contas[i].getNumeroConta() == numeroConta) {
                return contas[i];
            }
        }
        view.exibirMensagem("Conta não cadastrada.");
        return null;

    }

}
