package Brincadeiras;



import java.io.*;

import java.util.ArrayList;

import java.util.List;



public class BrincadeiraDao {



    private static final String NOME_ARQUIVO = "brincadeiras.csv";

    private static final String SEPARADOR = ";"; // Escolhemos o ponto e vírgula para evitar conflito com vírgulas na descrição



    

 // O método salvar deve ter essa estrutura:

    public void salvar(Brincadeiras brincadeira) {

        // Note o 'true' no final, que diz para o programa ADICIONAR ao arquivo

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(NOME_ARQUIVO, true))) {

            // Formata a linha como "nome;descricao"

            String linha = String.format("%s%s%s\n",

                    escaparCSV(brincadeira.getNome()),

                    SEPARADOR,

                    escaparCSV(brincadeira.getDescricao()));

            writer.write(linha);

            System.out.println("Brincadeira salva com sucesso no arquivo.");

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

    public void excluir(Brincadeiras brincadeiraExcluir) {

        // Carrega todas as brincadeiras do arquivo

        List<Brincadeiras> todasBrincadeiras = carregarTodos();

        

        // Remove a brincadeira da lista

        todasBrincadeiras.removeIf(b -> b.getNome().equals(brincadeiraExcluir.getNome()));

        

        // Salva a lista atualizada de volta no arquivo

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(NOME_ARQUIVO))) {

            for (Brincadeiras b : todasBrincadeiras) {

                writer.write(String.format("%s%s%s\n",

                    escaparCSV(b.getNome()),

                    SEPARADOR,

                    escaparCSV(b.getDescricao())));

            }

            System.out.println("Brincadeira excluída com sucesso do arquivo.");

        } catch (IOException e) {

            e.printStackTrace();

            // Aqui você pode adicionar uma mensagem de erro para o usuário

        }

    }



    

    public List<Brincadeiras> carregarTodos() {

        List<Brincadeiras> brincadeiras = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(NOME_ARQUIVO))) {

            String linha;

            while ((linha = reader.readLine()) != null) {

                String[] partes = linha.split(SEPARADOR, 2); // Limita a 2 para pegar a descrição inteira

                if (partes.length == 2) {

                    String nome = desescaparCSV(partes[0]);

                    String descricao = desescaparCSV(partes[1]);

                    brincadeiras.add(new Brincadeiras(nome, descricao));

                }

            }

        } catch (IOException e) {

            System.err.println("Arquivo de brincadeiras não encontrado ou erro de leitura. Iniciando com uma lista vazia.");

        }

        return brincadeiras;

    }



    /**

     * Escapa caracteres especiais para evitar problemas no CSV.

     */

    private String escaparCSV(String texto) {

        if (texto.contains(SEPARADOR) || texto.contains("\n") || texto.contains("\"")) {

            return "\"" + texto.replace("\"", "\"\"") + "\"";

        }

        return texto;

    }



    /**

     * Remove o escape de caracteres especiais.

     */

    private String desescaparCSV(String texto) {

        if (texto.startsWith("\"") && texto.endsWith("\"")) {

            texto = texto.substring(1, texto.length() - 1);

            return texto.replace("\"\"", "\"");

        }

        return texto;

    }

}

