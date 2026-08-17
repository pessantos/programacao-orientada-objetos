package contaBancaria;

public abstract class ContaBancaria implements Extratavel {

    // Atributos
    private String titular;
    private int agencia;
    private int numeroConta;
    protected double saldo;
    private StatusConta status; 

    // Construtor
    public ContaBancaria(String titular, int agencia, int numeroConta) {
        this.titular = titular;
        this.agencia = agencia;
        this.numeroConta = numeroConta;
        this.saldo = 0.0;
        this.status = StatusConta.ATIVA;
    }

    public ContaBancaria(String titular, int agencia, int numeroConta, double saldo) {
        this.titular = titular;
        this.agencia = agencia;
        this.numeroConta = numeroConta;
        this.saldo = saldo;
         this.status = StatusConta.ATIVA;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public int getAgencia() {
        return agencia;
    }

    public void setAgencia(int agencia) {
        this.agencia = agencia;
    }

    public int getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(int numeroConta) {
        this.numeroConta = numeroConta;
    }

    public double getSaldo() {
        return saldo;
    }

    public StatusConta getStatus(){
        return status;
    }

    public void depositar(double valor) {
        if(status != StatusConta.ATIVA){
            System.out.println("Operação não permitida: conta " + status + ".");
        }        
        else if (valor <= 0) {
            System.out.println("Valor inválido para depósito.");
        } else {
            this.saldo += valor;
            System.out.println("Depósito de R$" + valor + " realizado com sucesso.");
        }

    }

    public void sacar(double valor) {
        if(status != StatusConta.ATIVA){
            System.out.println("Operação não permitida: conta " + status + ".");
        }
        else if (valor <= 0) {
            System.out.println("Valor inválido para saque.");
        } else if (valor > this.saldo) {
            System.out.println("Saldo insuficiente para saque.");
        } else {
            this.saldo -= valor;
            System.out.println("Saque de R$" + valor + " realizado com sucesso.");
        }
    }

    public void bloquear(){
        this.status =  StatusConta.BLOQUEADA;
        System.out.println("A conta de " + titular + " está bloqueada.");

    }

    public void reativar(){
        this.status =  StatusConta.ATIVA;
        System.out.println("A conta de " + titular + " foi reativada.");

    }

    public void encerrar(){
        this.status =  StatusConta.ENCERRADA;
        System.out.println("A conta de " + titular + " foi encerrada.");

    }

    //Metodo abstrato para ser implementado nas subclasses
    public abstract double calcularTaxaManutencao();

}