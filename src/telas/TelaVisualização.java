package telas;

import Brincadeiras.Brincadeiras;
import Brincadeiras.BrincadeiraDao;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class TelaVisualização extends JFrame {
    private JList<String> listaBrincadeira;
    private JTextArea areaDescricao;
    private BrincadeiraDao dao;
    private List<Brincadeiras> todasBrincadeiras;
    private JFrame telaPai;
    private DefaultListModel<String> listModel; // <--- Variável movida para o nível da classe

    public TelaVisualização(JFrame telaPai) {
        this.telaPai = telaPai;

        Color laranjaClaro = new Color(255, 204, 153);
        Color verdeClaro = new Color(153, 204, 153);

        setTitle("Brincadeiras Cadastradas");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        this.dao = new BrincadeiraDao();
        this.todasBrincadeiras = dao.carregarTodos();

        setLayout(new BorderLayout(20, 20));
        getContentPane().setBackground(verdeClaro);
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel tituloLabel = new JLabel("Selecione uma Brincadeira:");
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 18));
        tituloLabel.setForeground(Color.BLACK);
        tituloLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(tituloLabel, BorderLayout.NORTH);

        // Inicializa o listModel aqui
        listModel = new DefaultListModel<>();
        carregarBrincadeirasNaLista();

        listaBrincadeira = new JList<>(listModel);
        listaBrincadeira.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaBrincadeira.setFont(new Font("Arial", Font.PLAIN, 16));
        listaBrincadeira.setBackground(laranjaClaro);
        listaBrincadeira.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        
        JScrollPane scrollLista = new JScrollPane(listaBrincadeira);
        scrollLista.setPreferredSize(new Dimension(250, 0));
        add(scrollLista, BorderLayout.WEST);

        areaDescricao = new JTextArea();
        areaDescricao.setEditable(false);
        areaDescricao.setLineWrap(true);
        areaDescricao.setWrapStyleWord(true);
        areaDescricao.setFont(new Font("Arial", Font.PLAIN, 16));
        areaDescricao.setBackground(new Color(240, 240, 240));
        areaDescricao.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JScrollPane scrollDescricao = new JScrollPane(areaDescricao);
        add(scrollDescricao, BorderLayout.CENTER);

        JPanel painelBotoesAcao = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        painelBotoesAcao.setBackground(verdeClaro);

        JButton botaoExcluir = new JButton("Excluir Brincadeira");
        botaoExcluir.setBackground(new Color(244, 67, 54));
        botaoExcluir.setForeground(Color.WHITE);
        botaoExcluir.setFont(new Font("Arial", Font.BOLD, 14));
        painelBotoesAcao.add(botaoExcluir);

        JButton botaoVoltar = new JButton("Voltar");
        botaoVoltar.setBackground(new Color(76, 175, 80));
        botaoVoltar.setForeground(Color.WHITE);
        botaoVoltar.setFont(new Font("Arial", Font.BOLD, 14));
        painelBotoesAcao.add(botaoVoltar);
        
        add(painelBotoesAcao, BorderLayout.SOUTH);

        botaoExcluir.addActionListener(e -> {
            int indiceSelecionado = listaBrincadeira.getSelectedIndex();
            if (indiceSelecionado == -1) {
                JOptionPane.showMessageDialog(this, "Por favor, selecione uma brincadeira para excluir.",
                        "Nenhuma Brincadeira Selecionada", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int confirmacao = JOptionPane.showConfirmDialog(this,
                    "Tem certeza que deseja excluir esta brincadeira?", "Confirmar Exclusão",
                    JOptionPane.YES_NO_OPTION);

            if (confirmacao == JOptionPane.YES_OPTION) {
                Brincadeiras brincadeiraParaExcluir = todasBrincadeiras.get(indiceSelecionado);
                dao.excluir(brincadeiraParaExcluir);
                
                // Recarrega a lista
                atualizarListaBrincadeiras();
                
                areaDescricao.setText("");
                JOptionPane.showMessageDialog(this, "Brincadeira excluída com sucesso!", "Exclusão Concluída", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        botaoVoltar.addActionListener(e -> {
            this.telaPai.setVisible(true);
            this.dispose();
        });

        listaBrincadeira.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int indiceSelecionado = listaBrincadeira.getSelectedIndex();
                if (indiceSelecionado != -1) {
                    Brincadeiras brincadeirasSelecionada = todasBrincadeiras.get(indiceSelecionado);
                    areaDescricao.setText(brincadeirasSelecionada.getDescricao());
                    areaDescricao.setCaretPosition(0);
                } else {
                    areaDescricao.setText("");
                }
            }
        });
    }

    // Método privado para carregar as brincadeiras no modelo
    private void carregarBrincadeirasNaLista() {
        for (Brincadeiras brincadeira : todasBrincadeiras) {
            listModel.addElement(brincadeira.getNome());
        }
    }
    
    // Método para atualizar a lista após uma exclusão
    private void atualizarListaBrincadeiras() {
        // Recarrega a lista de dados do arquivo
        this.todasBrincadeiras = dao.carregarTodos();
        
        // Limpa o modelo da lista na interface
        listModel.clear();
        
        // Adiciona os dados atualizados
        carregarBrincadeirasNaLista();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TelaVisualização(null).setVisible(true);
        });
    }
}