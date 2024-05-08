package com.example.demo7;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.List;

// file phụ để chạy app, có các đoạn code để vẽ giao diện, dùng để test các chức năng.
// file chạy để demo cho anh là HelloApplication.

public class DictionaryApp {
    private final Dictionary dictionary;
    private final Trie trie;
    private final JComboBox<String> searchField;
    private final DefaultComboBoxModel<String> model;
    private final Sound clickSound;
    private final Translator translator = new Translator();
    private JTextArea resultArea;
    private JButton searchButton;
    private JButton addButton;
    private JTextField addWordField;
    private JTextField addDefinitionField;
    private JFrame frame;
    private OnlineDictionary onlineDictionary;
    private JTextArea translateArea;
    private JTextField englishField;
    private final History history;

    public DictionaryApp() {

    // setUp để các class chức năng hoạt động.
        clickSound = new Sound("file_du_lieu/tieng-click-con-tro-hoat-hinh-www_tiengdong_com.au");
        trie = new Trie();
        trie.loadFromFile("file_du_lieu/tu_dien_2");
        model = new DefaultComboBoxModel<>();
        dictionary = new Dictionary();
        dictionary.loadFromFile("file_du_lieu/tu_dien_2");//
        onlineDictionary = new OnlineDictionary("");
        history = new History();

        // trường tìm kiếm có gợi ý
        searchField = new JComboBox<>(model);
        searchField.setEditable(true);
        searchField.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                    case KeyEvent.VK_DOWN:
                    case KeyEvent.VK_ENTER:
                        return;
                }
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        // cách lấy nội dung "text" trong trường tìm từ (có gợi ý).
                        String text = (String) searchField.getEditor().getItem();
                        List<String> suggestions = trie.search(text);
                        model.removeAllElements();
                        for (String suggestion : suggestions) {
                            model.addElement(suggestion);
                        }
                        ((JTextField) searchField.getEditor().getEditorComponent()).setText(text);
                        if (!suggestions.isEmpty()) {
                            searchField.showPopup();
                        }
                    }
                });
            }
        });


    //khung ứng dụng
        JFrame frame = new JFrame("Từ điển Anh-Việt");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);

        JPanel panel = new JPanel();
        panel.setBackground(Color.LIGHT_GRAY);// cài màu nền
        frame.add(panel);
        placeComponents(panel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new DictionaryApp();
    }

    // hàm tạo tiếng động khi click chuột
    private void placeComponents(JPanel panel) {


        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                clickSound.play();
            }
        });

    // tạo 1 font chữ.
        Font font = new Font("Arial", Font.PLAIN, 20);

        // thêm ô "Tìm từ".
        panel.setLayout(null);
        JLabel searchLabel = new JLabel("Tìm từ:");
        searchLabel.setBounds(10, 10, 160, 50);
        panel.add(searchLabel);


        // chèn phần để ta nhập phần tìm từ vô.
        searchField.setBounds(200, 10, 320, 50);
        panel.add(searchField);


        searchButton = new JButton("Tìm");
        searchButton.setBounds(540, 10, 80, 50);
        searchButton.setBackground(Color.CYAN);
        panel.add(searchButton);

        // ô phát âm từ ở chỗ tìm kiếm từ vựng.
        JButton speechButton2 = new JButton("Phát âm");
        speechButton2.setBounds(640, 10, 80, 50);
        speechButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TextToSpeech speech = new TextToSpeech();
                //clickSound.play();
                String text = (String) searchField.getEditor().getItem();
                speech.speak(text);
                //speech.close();
            }
        });


        panel.add(speechButton2);


        // tạo sự kiện khi nhấn nút tìm: tìm từ trong từ điển off, kh thấy thì tìm onl.
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clickSound.play();
                String word = (String) searchField.getEditor().getItem();
                history.addWord(word);
                onlineDictionary = new OnlineDictionary("https://api.dictionaryapi.dev/api/v2/entries/en/" + word);

                String definition = dictionary.getDefinition(word);
                if (definition == null) {
                    definition = onlineDictionary.getDefinition(word);
                    if (definition != null) {

                        resultArea.setText("Từ " + word + " được tìm thấy trong từ điển trực tuyến: " + definition);
                    } else {
                        resultArea.setText("Không tìm thấy từ: " + word);
                    }
                } else {
                    resultArea.setText(definition);
                }
            }
        });


        // nơi hiển thị nội dung tra được ở chỗ "Tìm từ".
        resultArea = new JTextArea();
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setBounds(10, 70, 760, 180);
        panel.add(scrollPane); // tạo cái cuộn xuống.


        // tạo ô thêm từ
        JLabel addLabel = new JLabel("Thêm từ:");
        addLabel.setBounds(10, 280, 160, 50);
        panel.add(addLabel);

        // vùng ta viết từ muốn thêm (từ tiếng anh).
        addWordField = new JTextField(40);
        addWordField.setBounds(200, 280, 320, 50);
        panel.add(addWordField);

        // tạo ô ghi nghĩa của từ mới thêm.
        JLabel definitionLabel = new JLabel("Nghĩa:");
        definitionLabel.setBounds(10, 340, 160, 50);
        panel.add(definitionLabel);

        // vùng ta ghi nghĩa của từ mới thêm.
        addDefinitionField = new JTextField(40);
        addDefinitionField.setBounds(200, 340, 320, 50);
        panel.add(addDefinitionField);

        // tạo nút thêm.
        addButton = new JButton("Thêm");
        addButton.setBounds(540, 280, 160, 50);
        addButton.setBackground(Color.GREEN); // Màu nút thêm
        panel.add(addButton);

// tạo sự kiện khi nhấn nút "Thêm".
        // mỗi lần thêm từ mới sẽ hiện ra thông báo "Đã thêm"
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clickSound.play();
                String word = addWordField.getText(); // lấy nội dung ở ô ghi tiếng anh.
                String definition = addDefinitionField.getText(); // lấy nội dung nghĩa ngươif dùng ghi.
                dictionary.addWord(word, definition); // thêm vào từ điển off.
                JOptionPane.showMessageDialog(frame, "Đã thêm từ: " + word); // hiện thông báo.
            }
        });


        panel.setLayout(null);

        JFrame frame = new JFrame("Từ điển Anh-Việt");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);

// ô ghi tiếng anh đầu vào.
        JTextArea inputArea = new JTextArea();
        inputArea.setBounds(200, 430, 320, 130);
        inputArea.setLineWrap(true); //giúp cho văn bản khi hết dòng sẽ tự động xuống dòng
        panel.add(inputArea);

        // giúp ô nhập tiếng việt: inputArea có thanh cuộn xuống
        JScrollPane scrollPane3 = new JScrollPane(inputArea);
        scrollPane3.setBounds(200, 430, 320, 130);
        panel.add(scrollPane3);

        // ô ghi tiếng việt đầu ra.
        JTextArea translateArea = new JTextArea();
        translateArea.setBounds(200, 600, 320, 130);
        translateArea.setEditable(false);
        translateArea.setLineWrap(true);// giúp cho văn bản khi hết dòng sẽ tự động xuống dòng
        //panel.add(translateArea);

        // Tạo JScrollPane và thêm translateArea vào đó để có thanh cuộn
        JScrollPane scrollPane2 = new JScrollPane(translateArea);
        scrollPane2.setBounds(200, 600, 320, 130);
        panel.add(scrollPane2);


        // khi ấn dịch văn bản thì sẽ dịch.
        JButton translateButton = new JButton("Dịch văn bản");
        translateButton.setBounds(540, 430, 160, 50);
        translateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clickSound.play();
                String text = inputArea.getText();
                try {
                    String translatedText = translator.translateText1(text); // gán translateText thành văn bản đã dịch.
                    translateArea.setText(translatedText);
                } catch (IOException ex) {
                    translateArea.setText("Error translating text.");
                }
            }
        });
        panel.add(translateButton);


        // thêm ô speech để đọc văn bản khi ấn nút.
        JButton speechButton = new JButton("Đọc văn bản");
        speechButton.setBounds(540, 500, 160, 50);
        speechButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TextToSpeech speech = new TextToSpeech();
                clickSound.play();
                String text = inputArea.getText();
                speech.speak(text);
                //speech.close();
            }
        });


        panel.add(speechButton);


        JLabel addLabel2 = new JLabel("Tiếng Anh:");
        addLabel2.setBounds(10, 430, 160, 50);
        panel.add(addLabel2);

        JLabel addLabel3 = new JLabel("Tiếng Việt:");
        addLabel3.setBounds(10, 600, 160, 50);
        panel.add(addLabel3);


        // thêm ô lịch sử tra từ.

        JButton historyButton = new JButton("Lịch sử tra từ");
        historyButton.setBounds(628, 710, 150, 50);
        panel.add(historyButton);

        historyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame historyFrame = new JFrame("Lịch sử tra từ");
                historyFrame.setSize(800, 800);
                JTextArea textArea = new JTextArea();
                textArea.setFont(font); // cài đặt để các chữ xuất hiện ở lịch sự sẽ theo font.
                JScrollPane scrollPane = new JScrollPane(textArea);
                historyFrame.add(scrollPane);

                for (String word : history.getWordHistory()) {

                    textArea.append("\t- " + word + "\n \n");
                }

                historyFrame.setVisible(true);
            }
        });

    }
}
