# Teste Prático - Iniflex

Projeto Java 17 + Maven que resolve o teste prático de programação da indústria Iniflex – gestão de funcionários.

> Repositório público para avaliação na etapa **Mão na Massa**: https://github.com/MatheusFerraz320/teste-pratico-iniflex

## Requisitos atendidos

- [x] **1** – Classe `Pessoa` (`src/main/java/com/iniflex/Pessoa.java:1`) com `nome: String` e `dataNascimento: LocalDate`
- [x] **2** – Classe `Funcionario extends Pessoa` (`Funcionario.java:1`) com `salario: BigDecimal` e `funcao: String`
- [x] **3.1** – Inserir 10 funcionários na ordem da tabela (ver abaixo)
- [x] **3.2** – Remover `João` (`Principal.java:50` – `removeIf`)
- [x] **3.3** – Imprimir todos com data `dd/MM/yyyy` (`DateTimeFormatter`) e salário pt-BR ponto milhar / vírgula decimal (`NumberFormat pt-BR`)
- [x] **3.4** – Aumento 10% (`multiply(new BigDecimal("1.10")).setScale(2, HALF_UP)`)
- [x] **3.5** – Agrupar por função em `Map<String, List<Funcionario>>` (`Collectors.groupingBy`)
- [x] **3.6** – Imprimir agrupados por função
- [x] **3.8** – Aniversariantes meses 10 e 12 (`getMonthValue() == 10 || == 12`)
- [x] **3.9** – Maior idade – nome + idade (`Period.between` + `min(comparing(Pessoa::getDataNascimento))`)
- [x] **3.10** – Ordem alfabética (`sorted(comparing(Pessoa::getNome))`)
- [x] **3.11** – Total dos salários (`reduce(BigDecimal.ZERO, BigDecimal::add)`)
- [x] **3.12** – Salários mínimos por funcionário (`SM = R$1212.00`, `divide(..., 2, HALF_UP)`)

## Stack

- **Java 17** (Temurin/Adoptium)
- **Maven 3.9+**
- **Sem dependências externas** – apenas `java.time`, `java.math`, `java.text`, `java.util.stream`

## Como executar

### Pré-requisitos

```bash
java -version   # 17+
mvn -version    # 3.9+
```

No WSL/Ubuntu sem JDK:

```bash
# JDK via Temurin (sem sudo, como usado na validação)
curl -sL https://api.adoptium.net/v3/binary/latest/17/ga/linux/x64/jdk/hotspot/normal/eclipse -o /tmp/jdk.tar.gz
mkdir -p /tmp/jdk && tar -xzf /tmp/jdk.tar.gz -C /tmp/jdk --strip-components=1
export JAVA_HOME=/tmp/jdk
export PATH=$JAVA_HOME/bin:$PATH
```

### Opção 1 – Maven (recomendado)

```bash
# clonar
git clone https://github.com/MatheusFerraz320/teste-pratico-iniflex.git
cd teste-pratico-iniflex

# compilar
mvn compile

# executar (roda Principal.main)
mvn exec:java

# gerar jar executável e rodar
mvn package
java -jar target/teste-pratico-iniflex-1.0.0.jar
```

### Opção 2 – javac direto (sem Maven)

```bash
javac -d out src/main/java/com/iniflex/*.java
java -cp out com.iniflex.Principal
```

### Opção 3 – IDE (Eclipse / IntelliJ / VS Code)

1. `File → Open → teste-pratico-iniflex` (ou Import Maven Project)
2. JDK 17
3. Run `com.iniflex.Principal`

## Exemplo de saída (validada `mvn exec:java`)

```
=== 3.3 - Todos os funcionarios ===
Nome: Maria | Data Nasc: 18/10/2000 | Salario: R$ 2.009,44 | Funcao: Operador
...
=== 3.6 - Funcionarios agrupados por funcao ===
Funcao: Operador
  - Nome: Maria ...
Funcao: Gerente
  - Nome: Laura ...
=== 3.8 - Aniversariantes meses 10 e 12 ===
Nome: Maria | Data Nasc: 18/10/2000 | Salario: R$ 2.210,38 | Funcao: Operador
Nome: Miguel | Data Nasc: 14/10/1988 | Salario: R$ 21.031,87 | Funcao: Diretor
=== 3.9 - Funcionario com maior idade ===
Nome: Caio | Idade: 65 anos
=== 3.11 - Total dos salarios ===
Total: R$ 50.906,82
=== 3.12 - Salarios minimos por funcionario (SM = R$ 1.212,00) ===
Maria - 1,82 salarios minimos
Miguel - 17,35 salarios minimos
...
```

## Estrutura

```
teste-pratico-iniflex/
├── pom.xml                           # Java 17, exec-maven-plugin, jar com Main-Class
├── README.md
├── .gitignore
└── src/main/java/com/iniflex/
    ├── Pessoa.java                   # 1
    ├── Funcionario.java              # 2
    └── Principal.java                # 3.1 a 3.12
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

## Entrega

- Repositório: https://github.com/MatheusFerraz320/teste-pratico-iniflex
- Para exportar sem GitHub: `tar -czf teste-pratico-iniflex.tar.gz --exclude=target --exclude=.git teste-pratico-iniflex` ou `mvn package` e anexar o `target/*.jar`
