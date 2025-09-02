# Sistema de Brincadeiras - ONG Semear

## 📝 Sobre o Projeto

O **Sistema de Brincadeiras** é uma ferramenta de software desenvolvida em Java para a **ONG Semear**. Seu principal objetivo é ajudar as educadoras a gerenciar e consultar um banco de dados de brincadeiras de forma fácil e intuitiva.

O sistema permite:
- **Cadastrar** novas brincadeiras com nome e descrição detalhada.
- **Visualizar** a lista de brincadeiras cadastradas.
- **Consultar** a descrição de cada brincadeira com um único clique.
- **Excluir** brincadeiras que não são mais necessárias.

O design da aplicação foi pensado para ser claro e amigável, utilizando uma paleta de cores agradável e uma navegação simples.

## 🛠️ Tecnologias Utilizadas

- **Linguagem:** Java
- **Interface Gráfica:** Java Swing
- **Persistência de Dados:** Arquivos CSV (para armazenar as brincadeiras)

## 🚀 Como Executar o Projeto

Para rodar este projeto na sua máquina, siga os passos abaixo:

1.  **Clone o repositório:**
    ```bash
    git clone [https://github.com/SEU-USUARIO/SEU-REPOSITORIO.git](https://github.com/SEU-USUARIO/SEU-REPOSITORIO.git)
    cd SEU-REPOSITORIO
    ```
    *Obs: Lembre-se de substituir `SEU-USUARIO` e `SEU-REPOSITORIO` pelos dados corretos do seu projeto.*

2.  **Abra o projeto em sua IDE (IntelliJ, Eclipse, VS Code, etc.).**

3.  **Certifique-se de que todas as classes estão no lugar correto:**
    - `telas/Tela_Principal.java`
    - `telas/Tela_Brincadeiras.java`
    - `telas/TelaVisualizacao.java`
    - `Brincadeiras/Brincadeiras.java`
    - `Brincadeiras/BrincadeiraDao.java`

4.  **Execute a classe `Tela_Principal.java`.**
    - Clique com o botão direito no arquivo `Tela_Principal.java` e selecione "Run" ou "Executar".
    - A janela principal do sistema será exibida, e você poderá começar a usar o software.

## 📄 Estrutura de Arquivos
    .
    ├── src/
    │   ├── Brincadeiras/
    │   │   ├── Brincadeiras.java
    │   │   └── BrincadeiraDao.java
    │   └── telas/
    │       ├── Tela_Principal.java
    │       ├── Tela_Brincadeiras.java
    │       └── TelaVisualizacao.java
    └── README.md
