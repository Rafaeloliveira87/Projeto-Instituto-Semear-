package telas;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;

public class Tela_Principal extends JFrame {

    public Tela_Principal() {
        // Cores da nova paleta
        Color fundoTela = new Color(240, 230, 200); // Bege claro
        Color verdeBotoes = new Color(76, 175, 80); // Verde escuro para botões
        Color vermelhoSair = new Color(244, 67, 54); // Vermelho para o botão de sair
        
        // Configurações da janela principal
        setTitle("Sistema de Brincadeiras - ONG Semear");
        setSize(500, 450); // Aumenta o tamanho da tela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Usa BorderLayout para a janela principal
        setLayout(new BorderLayout());
        getContentPane().setBackground(fundoTela);

        // Título no topo da tela
        JLabel titulo = new JLabel("Bem-vindo(a) ao Sistema de Brincadeiras!");
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setBorder(new EmptyBorder(20, 0, 20, 0)); // Espaçamento superior e inferior
        add(titulo, BorderLayout.NORTH);

        // Painel para os botões no centro
        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new GridLayout(3, 1, 0, 20)); // 3 linhas, 1 coluna, 20px de espaçamento vertical
        painelBotoes.setBorder(new EmptyBorder(0, 50, 0, 50)); // Espaçamento nas laterais
        painelBotoes.setBackground(fundoTela);

        // Botão para Cadastrar Brincadeira
        JButton botaoCadastro = new JButton("Cadastrar Brincadeira");
        botaoCadastro.setFont(new Font("Arial", Font.BOLD, 18));
        botaoCadastro.setBackground(verdeBotoes);
        botaoCadastro.setForeground(Color.WHITE);
        painelBotoes.add(botaoCadastro);

        // Botão para Visualizar Brincadeiras
        JButton botaoVisualizacao = new JButton("Visualizar Brincadeiras");
        botaoVisualizacao.setFont(new Font("Arial", Font.BOLD, 18));
        botaoVisualizacao.setBackground(verdeBotoes);
        botaoVisualizacao.setForeground(Color.WHITE);
        painelBotoes.add(botaoVisualizacao);
        
        // Botão Sair
        JButton botaoSair = new JButton("Sair");
        botaoSair.setFont(new Font("Arial", Font.BOLD, 18));
        botaoSair.setBackground(vermelhoSair);
        botaoSair.setForeground(Color.WHITE);
        painelBotoes.add(botaoSair);

        // Adiciona o painel de botões ao centro da janela
        add(painelBotoes, BorderLayout.CENTER);

        // Rodapé com o texto de copyright
        JLabel rodape = new JLabel("© ONG Semear 2025 - Todos os direitos reservados");
        rodape.setFont(new Font("Arial", Font.PLAIN, 12));
        rodape.setHorizontalAlignment(SwingConstants.CENTER);
        rodape.setBorder(new EmptyBorder(20, 0, 10, 0));
        add(rodape, BorderLayout.SOUTH);

        // Ações dos botões
        botaoCadastro.addActionListener(e -> {
            new Tela_Brincadeiras(this).setVisible(true);
            this.setVisible(false);
        });

        botaoVisualizacao.addActionListener(e -> {
            new TelaVisualização(this).setVisible(true);
            this.setVisible(false);
        });
        
        botaoSair.addActionListener(e -> {
            System.exit(0); // Encerra o programa
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Tela_Principal().setVisible(true);
        });
    }
}