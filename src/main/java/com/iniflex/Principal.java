package com.iniflex;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

public class Principal {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final NumberFormat SALARY_FORMAT = NumberFormat.getNumberInstance(new Locale("pt", "BR"));
    private static final BigDecimal SALARIO_MINIMO = new BigDecimal("1212.00");

    static {
        SALARY_FORMAT.setMinimumFractionDigits(2);
        SALARY_FORMAT.setMaximumFractionDigits(2);
    }

    public static void main(String[] args) {
        // 3.1 - Inserir todos os funcionarios na mesma ordem da tabela
        List<Funcionario> funcionarios = new ArrayList<>(Arrays.asList(
                new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"),
                new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"),
                new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"),
                new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"),
                new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"),
                new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"),
                new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"),
                new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"),
                new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"),
                new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente")
        ));

        // 3.2 - Remover funcionario Joao
        funcionarios.removeIf(f -> f.getNome().equals("João"));
        System.out.println("=== 3.2 - Joao removido. Total: " + funcionarios.size() + " funcionarios ===\n");

        // 3.3 - Imprimir todos os funcionarios com todas as informacoes
        System.out.println("=== 3.3 - Todos os funcionarios ===");
        imprimirFuncionarios(funcionarios);

        // 3.4 - Aumento de 10%
        System.out.println("\n=== 3.4 - Aumento de 10% ===");
        funcionarios.forEach(f -> f.setSalario(
                f.getSalario().multiply(new BigDecimal("1.10")).setScale(2, RoundingMode.HALF_UP)
        ));
        imprimirFuncionarios(funcionarios);

        // 3.5 - Agrupar por funcao em MAP
        Map<String, List<Funcionario>> porFuncao = funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));

        // 3.6 - Imprimir agrupados por funcao
        System.out.println("\n=== 3.6 - Funcionarios agrupados por funcao ===");
        porFuncao.forEach((funcao, lista) -> {
            System.out.println("Funcao: " + funcao);
            lista.forEach(f -> System.out.println("  - " + formatarFuncionario(f)));
        });

        // 3.8 - Aniversariantes mes 10 e 12
        System.out.println("\n=== 3.8 - Aniversariantes meses 10 e 12 ===");
        funcionarios.stream()
                .filter(f -> f.getDataNascimento().getMonthValue() == 10
                        || f.getDataNascimento().getMonthValue() == 12)
                .forEach(f -> System.out.println(formatarFuncionario(f)));

        // 3.9 - Funcionario com maior idade
        System.out.println("\n=== 3.9 - Funcionario com maior idade ===");
        Funcionario maisVelho = funcionarios.stream()
                .min(Comparator.comparing(Funcionario::getDataNascimento))
                .orElse(null);
        if (maisVelho != null) {
            int idade = Period.between(maisVelho.getDataNascimento(), LocalDate.now()).getYears();
            System.out.println("Nome: " + maisVelho.getNome() + " | Idade: " + idade + " anos");
        }

        // 3.10 - Lista por ordem alfabetica
        System.out.println("\n=== 3.10 - Ordem alfabetica ===");
        funcionarios.stream()
                .sorted(Comparator.comparing(Funcionario::getNome))
                .forEach(f -> System.out.println(formatarFuncionario(f)));

        // 3.11 - Total dos salarios
        System.out.println("\n=== 3.11 - Total dos salarios ===");
        BigDecimal total = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("Total: R$ " + SALARY_FORMAT.format(total));

        // 3.12 - Salarios minimos por funcionario
        System.out.println("\n=== 3.12 - Salarios minimos por funcionario (SM = R$ " + SALARY_FORMAT.format(SALARIO_MINIMO) + ") ===");
        funcionarios.forEach(f -> {
            BigDecimal qtd = f.getSalario().divide(SALARIO_MINIMO, 2, RoundingMode.HALF_UP);
            System.out.println(f.getNome() + " - " + SALARY_FORMAT.format(qtd) + " salarios minimos");
        });
    }

    private static void imprimirFuncionarios(List<Funcionario> funcionarios) {
        funcionarios.forEach(f -> System.out.println(formatarFuncionario(f)));
    }

    private static String formatarFuncionario(Funcionario f) {
        return "Nome: " + f.getNome()
                + " | Data Nasc: " + f.getDataNascimento().format(DATE_FORMATTER)
                + " | Salario: R$ " + SALARY_FORMAT.format(f.getSalario())
                + " | Funcao: " + f.getFuncao();
    }
}
