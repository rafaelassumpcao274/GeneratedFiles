# GeneratedFiles (Kotlin + Apache POI)

Projeto em Kotlin para gerar planilhas Excel (usando Apache POI). Atualmente contém apenas o módulo de Excel e adota uma API inspirada no estilo de criação de widgets do Flutter (criação de células, tabelas e relatórios de forma declarativa).

## Principais funcionalidades
- Criação de células básicas (text, number, date, double)
- Estilos (moeda, data, formato customizável)
- Fórmulas
- Mesclagem de células
- Geração de tabelas a partir de listas de objetos (mapeamento por nome de coluna -> caminho de propriedade)
- Composição de um ReportXLSX com múltiplas células/tabelas

## Exemplo de uso
```kotlin
fun main(args: Array<String>) {
    val lista: MutableList<ICell<*>> = mutableListOf(
        BasicCell<String>("A", null).content("Teste"),
        BasicCell<Int>("A", 2).content(1),
        BasicCell<String>("AA", null).content("teste Proxima linha"),
        BasicCell<Double>("B", 1).content(12.30).style(dataStyle()),
        BasicCell<Date>("E", null).content(Date()).style(Styles().dateFormat(DateFormat.NBR)),
        BasicCell<String>("A",4).mergeCell("C",4).content("Teste Mesclagem"),
        BasicCell<String>("F",4).content("13 item"),
        FormulaCell("G",4).content("A2+B1").style(Styles().currencyFormat(CurrencyFormat.BRL))
    )

    val listaTeste: List<String> = listOf("John Doe","Joao")
    var namePath: Map<String,String?> = mapOf("Nome" to null)
    var table: Table<String> = Table("A",6, listaTeste).nameColumnAndPathValue(namePath)
    lista += TableXLSX<String>().createTable(table)

    val listaObjeto: List<Pessoa> = listOf(
        Pessoa(1,"John Doe",29,"São Paulo"),
        Pessoa(2,"Joao",23,"Parana")
    )
    namePath = mapOf("Uf" to "uf")
    var cliente: Table<Pessoa> = Table("F",13, listaObjeto).nameColumnAndPathValue(namePath)
    lista += TableXLSX<Pessoa>().createTable(cliente)

    // tabela a partir de objeto com caminhos compostos
    namePath = mapOf("Nome" to "idPessoa.nome","Empresa" to "idEmpresa.razaoSocial","CPF" to "idPessoa.cpf")
    var empresa: Table<Empregados> = Table("F",1, listOf(empregados)).nameColumnAndPathValue(namePath)
    lista.addAll(TableXLSX<Empregados>().createTable(empresa))

    val reportXLSX = ReportXLSX()
    reportXLSX.begin("teste")
    reportXLSX.sheet(lista)
    reportXLSX.end()
}

fun dataStyle(): Styles {
    return Styles().currencyFormat(CurrencyFormat.BRL)
}
```

## Como usar (rápido)
- Adicione dependência do Apache POI no build.gradle (se ainda não)
- Importe o módulo Excel do projeto
- Construa uma lista de ICell, crie Tables via TableXLSX e gere o relatório com ReportXLSX

Exemplo Gradle (adapte conforme seu build):
```groovy
dependencies {
    implementation "org.apache.poi:poi:5.2.3"
    implementation "org.apache.poi:poi-ooxml:5.2.3"
    // adicionar o módulo local do projeto se necessário
}
```

## Estrutura esperada
- ICell, BasicCell, FormulaCell — elementos básicos de célula
- Styles, DateFormat, CurrencyFormat — helpers de estilo
- Table, TableXLSX — criação de tabelas a partir de coleções
- ReportXLSX — API para compor e escrever o arquivo final

## Contribuição
Pull requests são bem-vindos. Abra uma issue para discutir mudanças ou reportar bugs.

## Licença
MIT (ou escolha a licença que preferir)
