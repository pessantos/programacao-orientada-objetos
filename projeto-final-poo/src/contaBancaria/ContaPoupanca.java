package contaBancaria;

public class ContaPoupanca extends ContaBancaria {

    private double taxaJuros;

    public ContaPoupanca(String titular, int agencia, int numeroConta, double saldo, double taxaJuros) {
        super(titular, agencia, numeroConta, saldo);
        this.taxaJuros = taxaJuros;
    }

    public double getTaxaJuros() {
        return taxaJuros;
    }

    public void setTaxaJuros(double taxaJuros) {
        this.taxaJuros = taxaJuros;
    }

    public void renderJuros() {
        double jurosGanho = saldo * taxaJuros;
        saldo += jurosGanho;
        System.out.println("Juros renderizados no valor de R$" + jurosGanho + ".");
    }

    @Override
    public double calcularTaxaManutencao() {        
        System.out.println("Conta poupança não possui taxa de manutenção.");
        return 0.0;
    }

    @Override
    public void emitirExtrato(){
        System.out.println("=== Extrato Conta Poupança ===");
        System.out.println("Titular: " + getTitular());
        System.out.println("Saldo: " + getSaldo());
        System.out.println("Taxa de juros: " + (taxaJuros * 100) + "% ao mês");

    }

}
