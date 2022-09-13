package entities;

import entities.enums.WorkerLevel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Worker {
    private String name;
    private WorkerLevel level;
    private Double baseSalary;

    /*
    Associação entre classes do tipo Composição.
     */
    private Department department;
    private List<HourContract> contracts = new ArrayList<>();//Não incluir no construtor

    public Worker() {
    }

    public Worker(String name, WorkerLevel level, Double baseSalary, Department department) {
        this.name = name;
        this.level = level;
        this.baseSalary = baseSalary;
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public WorkerLevel getLevel() {
        return level;
    }

    public void setLevel(WorkerLevel level) {
        this.level = level;
    }

    public Double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(Double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<HourContract> getContracts() {
        return contracts;
    }

    /**
     * Método para adicionar o contrato recebido como argumento na lista de contratos.
     * Faz a associação entre um trabalhador e um contrato.
     * @param contract
     */
    public void addContract(HourContract contract) {
        contracts.add(contract);
    }

    /**
     * Método que remove o contrato da lista de contratos associados ao trabalhador.
     * Desfaz a associação entre um trabalhador e um contrato.
     * @param contract
     */
    public void removeContract(HourContract contract) {
        contracts.remove(contract);
    }

    /**
     * Método que calcula quanto a pessoa ganhou considerando os parâmetros recebidos (ano e mês)
     * @param year
     * @param month
     * @return
     */
    public double income(int year, int month) {
        double sum = baseSalary;
        Calendar cal = Calendar.getInstance();
        for (HourContract c : contracts) {
            cal.setTime(c.getDate());
            int c_year = cal.get(Calendar.YEAR);
            int c_month = 1 + cal.get(Calendar.MONTH);//O mês no Calendar começa com 0.
            if (year == c_year && month == c_month) {
                sum += c.totalValue();
            }
        }
        return sum;
    }

}
