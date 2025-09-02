package telas;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import Brincadeiras.Brincadeiras;
import Brincadeiras.BrincadeiraDao;

public class Tela_Brincadeiras extends JFrame {
    private JTextField campoNome;
    private JTextArea campoDescricao;
    private BrincadeiraDao dao;
    private JFrame telaPai; // Guarda a referência da tela anterior

    // Construtor que recebe a tela principal
    public Tela_Brincadeiras(JFrame telaPai) {
        this.telaPai = telaPai;
        
        Color laranjaClaro = new Color(255, 204, 153);
        Color verdeClaro = new Color(153, 204, 153);

        setTitle("Cadastro de Brincadeiras");
        setSize(600, 450); 
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        setLocationRelativeTo(null); 

        this.dao = new BrincadeiraDao();

        // O restante do seu código de layout...
        JPanel painel = new JPanel(new GridBagLayout());
        painel.setBackground(verdeClaro); 
        painel.setBorder(new EmptyBorder(30, 30, 30, 30)); 
        add(painel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); 
        gbc.fill = GridBagConstraints.BOTH; 

        JLabel labelNome = new JLabel("Nome da Brincadeira:");
        labelNome.setFont(new Font("Arial", Font.BOLD, 16)); 
        labelNome.setForeground(Color.BLACK);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.3; 
        painel.add(labelNome, gbc);

        campoNome = new JTextField(30); 
        campoNome.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.7; 
        painel.add(campoNome, gbc);

        JLabel labelDescricao = new JLabel("Descrição da Brincadeira:");
        labelDescricao.setFont(new Font("Arial", Font.BOLD, 16));
        labelDescricao.setForeground(Color.BLACK);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.NORTHWEST; 
        painel.add(labelDescricao, gbc);

        campoDescricao = new JTextArea(8, 30); 
        campoDescricao.setFont(new Font("Arial", Font.PLAIN, 14));
        campoDescricao.setLineWrap(true);
        campoDescricao.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(campoDescricao);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weighty = 1.0; 
        painel.add(scrollPane, gbc);

        JButton botaoSalvar = new JButton("Salvar Brincadeira");
        botaoSalvar.setBackground(laranjaClaro); 
        botaoSalvar.setForeground(Color.BLACK);
        botaoSalvar.setFont(new Font("Arial", Font.BOLD, 16));
        
        JPanel painelBotoesInferior = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 0));
        painelBotoesInferior.setBackground(verdeClaro);

        JButton botaoVisualizar = new JButton("Visualizar Brincadeiras");
        botaoVisualizar.setBackground(laranjaClaro);
        botaoVisualizar.setForeground(Color.BLACK);
        botaoVisualizar.setFont(new Font("Arial", Font.BOLD, 14));
        painelBotoesInferior.add(botaoVisualizar);

        JButton botaoVoltar = new JButton("Voltar");
        botaoVoltar.setBackground(laranjaClaro);
        botaoVoltar.setForeground(Color.BLACK);
        botaoVoltar.setFont(new Font("Arial", Font.BOLD, 14));
        painelBotoesInferior.add(botaoVoltar);
        
        // Ações de navegação
        botaoVisualizar.addActionListener(e -> {
            new TelaVisualização(this.telaPai).setVisible(true); // Abre a tela de visualização
            this.dispose(); // Fecha esta tela
        });
        
        botaoVoltar.addActionListener(e -> {
            this.telaPai.setVisible(true); // Mostra a tela principal
            this.dispose(); // Fecha esta tela
        });
        
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.EAST;
        painel.add(botaoSalvar, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 3; 
        painel.add(painelBotoesInferior, gbc);

        botaoSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = campoNome.getText();
                String descricao = campoDescricao.getText();
                if (nome.trim().isEmpty() || descricao.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(Tela_Brincadeiras.this, "Por favor, preencha todos os campos.",
                            "Erro de Validação", JOptionPane.ERROR_MESSAGE);
                } else {
                    Brincadeiras novaBrincadeira = new Brincadeiras(nome, descricao);
                    dao.salvar(novaBrincadeira);
                    JOptionPane.showMessageDialog(Tela_Brincadeiras.this,
                            "Brincadeira '" + novaBrincadeira.getNome() + "' salva com sucesso!");
                    campoNome.setText("");
                    campoDescricao.setText("");
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Tela_Brincadeiras(null).setVisible(true);
        });
    }
}