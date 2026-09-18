# Teste Prático - Iniflex

Projeto Java resolvendo o teste prático de programação da indústria Iniflex.

## Requisitos atendidos

- [x] 1 - Classe `Pessoa` (`nome`, `dataNascimento`)
- [x] 2 - Classe `Funcionario` extends `Pessoa` (`salario`, `funcao`)
- [x] 3.1 - Inserir 10 funcionários na ordem da tabela
- [x] 3.2 - Remover `João`
- [x] 3.3 - Imprimir com data `dd/MM/yyyy` e salário `pt-BR` (ponto milhar, vírgula decimal)
- [x] 3.4 - Aumento 10% (BigDecimal com HALF_UP)
- [x] 3.5 - Agrupar por função em `Map<String, List<Funcionario>>`
- [x] 3.6 - Imprimir agrupados
- [x] 3.8 - Aniversariantes meses 10 e 12
- [x] 3.9 - Maior idade (nome + idade via `Period`)
- [x] 3.10 - Ordem alfabética
- [x] 3.11 - Total salários
- [x] 3.12 - Salários mínimos (R$ 1212,00)

## Stack

- Java 17
- Maven 3.9+
- Sem dependências externas (JDK puro)

## Como executar

```bash
# compilar
mvn compile

# executar
mvn exec:java

# ou com javac direto (sem Maven)
javac -d out src/main/java/com/iniflex/*.java
java -cp out com.iniflex.Principal

# gerar jar executável
mvn package
java -jar target/teste-pratico-iniflex-1.0.0.jar
```

## Estrutura

```
src/main/java/com/iniflex/
├── Pessoa.java
├── Funcionario.java
└── Principal.java
```

## Tabela de dados (3.1)

| Nome    | Nascimento | Salário   | Função       |
|---------|------------|-----------|--------------|
| Maria   | 18/10/2000 | 2009.44   | Operador     |
| João    | 12/05/1990 | 2284.38   | Operador     |
| Caio    | 02/05/1961 | 9836.14   | Coordenador  |
| Miguel  | 14/10/1988 | 19119.88  | Diretor      |
| Alice   | 05/01/1995 | 2234.68   | Recepcionista|
| Heitor  | 19/11/1999 | 1582.72   | Operador     |
| Arthur  | 31/03/1993 | 4071.84   | Contador     |
| Laura   | 08/07/1994 | 3017.45   | Gerente      |
| Heloísa | 24/05/2003 | 1606.85   | Eletricista  |
| Helena  | 02/09/1996 | 2799.93   | Gerente      |
```

