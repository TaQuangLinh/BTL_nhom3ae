package com.example.demo7;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HelloController {
    @FXML
    public ListView<String> suggestionList;

    @FXML
    private Button btn_add, btn_sua;

    @FXML
    private Button btn_search;

    @FXML
    private Button btn_API;

    @FXML
    private Button btn_game;


    @FXML
    private AnchorPane panel_add;

    @FXML
    private AnchorPane panel_search;

    @FXML
    private AnchorPane panel_API;

    @FXML
    private AnchorPane panel_game;

    private Dictionary dictionary = null;
    private final Trie trie = new Trie();

    private Sound clickSound = null;
    private final Translator translator = new Translator();

    private OnlineDictionary onlineDictionary;

    private History history;

    @FXML
    private AnchorPane Xinchao;

    @FXML
    private Pane batDau;
    @FXML
    private Label ngonNguDich;
    @FXML
    private Label ngonNguGoc;
    @FXML
    private Button nutDoiNgonNgu;
    @FXML
    private Button dichVanBan;
    @FXML
    private Label vanBanDich;
    @FXML
    private TextArea vanBanGoc;
    @FXML
    private TextField oTimKiem;
    //private final Translator translator = new Translator();
    @FXML
    private Label nghiaCuaTu, choGhiPhienAm;
    @FXML
    private TextField tiengAnh;
    @FXML
    private TextArea tiengViet2;
    @FXML
    private TextField phienAm2;
    @FXML
    private Pane panel_suaTu;
    @FXML
    private TextField themPhienAm;
    @FXML
    private TextField themTiengAnh;
    @FXML
    private TextField themTiengViet;
    @FXML
    private AnchorPane panel_game1;
    @FXML
    private Label game1;
    @FXML
    private Button ButtonA;
    @FXML
    private Button ButtonB;
    @FXML
    private Button ButtonC;
    @FXML
    private Button ButtonD;
    @FXML
    private Label answerLabelA;
    @FXML
    private Label answerLabelB;
    @FXML
    private Label answerLabelC;
    @FXML
    private Label answerLabelD;
    @FXML
    private Label highscoreLabel;
    @FXML
    private Label questionLabel;
    @FXML
    private TextField questionTextField;
    @FXML
    private Label score_now;
    @FXML
    private Label seconds_left;
    @FXML
    private Button confirmButton;
    @FXML
    private AnchorPane Reslut;
    @FXML
    private Label correct_answer;
    private int highscore = 0;
    private char selectedAnswer = ' ';
    private final ArrayList<String> questions = new ArrayList<>();
    private final ArrayList<String[]> options = new ArrayList<>();
    private final ArrayList<Character> answers = new ArrayList<>();
    private int index = 0;
    private int correctGuesses = 0;
    private final int totalQuestions = 6;
    private int seconds = 10;
    private boolean answerSubmitted = false;
    private Timeline timeline;
    private boolean timerStarted = false; // Biến để kiểm tra xem thời gian đã được bắt đầu chưa
    @FXML
    private Label percentage;
    @FXML
    private Button exitButton;
    @FXML
    private Pane thanhChucNang;
    private final ObservableList<String> suggestions = FXCollections.observableArrayList();
    @FXML
    private Label score_game2;
    @FXML
    private AnchorPane Game2;
    @FXML
    private Label Labelclicked;
    @FXML
    private Button nameA;
    @FXML
    private Button nameB;
    @FXML
    private Button nameBACKSPACE;
    @FXML
    private Button nameC;
    @FXML
    private Button nameD;
    @FXML
    private Button nameE;
    @FXML
    private Button nameENTER;
    @FXML
    private Button nameF;
    @FXML
    private Button nameG;
    @FXML
    private Button nameH;
    @FXML
    private Button nameI;
    @FXML
    private Button nameJ;
    @FXML
    private Button nameK;
    @FXML
    private Button nameL;
    @FXML
    private Button nameM;
    @FXML
    private Button nameN;
    @FXML
    private Button nameO;
    @FXML
    private Button nameP;
    @FXML
    private Button nameQ;
    @FXML
    private Button nameR;
    @FXML
    private Button nameS;
    @FXML
    private Button nameT;
    @FXML
    private Button nameU;
    @FXML
    private Button nameV;
    @FXML
    private Button nameW;
    @FXML
    private Button nameX;
    @FXML
    private Button nameY;
    private int enterCount = 0;
    private int scoregame2 = 0;
    @FXML
    private Button nameZ;
    private final List<String> imagePaths = new ArrayList<>();
    private final List<String> answersgame2 = new ArrayList<>();
    private final Map<String, String> imageToAnswerMap = new HashMap<>();
    @FXML
    private ImageView imageGame;
    private String currentQuestionImagePath; // Biến để lưu đường dẫn của ảnh câu hỏi hiện tại Biến Rất Quan trong


    @FXML
    void StartApp(ActionEvent event) {

        clickSound = new Sound("C:\\Users\\LINH\\Desktop\\BTL_DictionaryApp_Final\\BTL_0405\\BTL_0405\\BTL - Da_co_game\\Test13\\BTL - TaQuangLinh\\file_du_lieu\\tieng-click-con-tro-hoat-hinh-www_tiengdong_com.au");
        clickSound.play();
        dictionary = new Dictionary();
        dictionary.loadFromFile("C:\\Users\\LINH\\Desktop\\BTL_DictionaryApp_Final\\BTL_0405\\BTL_0405\\BTL - Da_co_game\\Test13\\BTL - TaQuangLinh\\file_du_lieu\\tu_dien_2");//
        onlineDictionary = new OnlineDictionary("");
        history = new History();
        panel_search.toFront();
        batDau.toBack();
    }

    @FXML
    private void chon_Chuc_nang(Event event) {
        clickSound.play();
        if (event.getSource() == btn_add) {
            panel_add.toFront();
        } else if (event.getSource() == btn_search) {
            panel_search.toFront();
            nghiaCuaTu.setWrapText(true);
        } else if (event.getSource() == btn_API) {
            panel_API.toFront();
            vanBanGoc.setWrapText(true);
        } else if (event.getSource() == btn_game) {
            panel_game.toFront();
        }

        nghiaCuaTu.setWrapText(true);
    }

    @FXML
    void doi_Ngon_Ngu(ActionEvent event) {

        clickSound.play();
        if (ngonNguGoc.getText().equals("Tiếng Anh")) {
            ngonNguGoc.setText("Tiếng Việt");
            ngonNguDich.setText("Tiếng Anh");
        } else {
            ngonNguGoc.setText("Tiếng Anh");
            ngonNguDich.setText("Tiếng Việt");
        }
    }

    @FXML
    void dich(ActionEvent event) {
        clickSound.play();
        if (ngonNguGoc.getText().equals("Tiếng Anh")) {
            String text = vanBanGoc.getText();
            try {
                String translatedText = translator.translateText1(text); // gán translateText thành văn bản đã dịch.
                vanBanDich.setText(translatedText);
                vanBanDich.setWrapText(true);
            } catch (IOException ex) {
                vanBanDich.setText("Error translating text.");

            }
        } else {
            String text = vanBanGoc.getText();
            try {
                String translatedText = translator.translateText2(text); // gán translateText thành văn bản đã dịch.
                vanBanDich.setText(translatedText);
                vanBanDich.setWrapText(true);
            } catch (IOException ex) {
                vanBanDich.setText("Error translating text.");
            }
        }

    }

    @FXML
    void phatAm(ActionEvent event) {

        TextToSpeech speech = new TextToSpeech();
        String text = oTimKiem.getText();
        speech.speak(text);

    }

    @FXML
    void timKiem() {
        clickSound.play();
        String word = oTimKiem.getText();
        word = word.toLowerCase();
        history.addWord(word);



        String definition = dictionary.getDefinition(word), phienAm = "";


        if (definition == null) {
            clickSound.play();
            nghiaCuaTu.setText("Không tìm thấy từ " + word + " trong từ điển");
        } else {
            Pattern pattern = Pattern.compile("/(.*?)/");
            Matcher matcher = pattern.matcher(definition);

            while (matcher.find()) {
                phienAm = matcher.group(1);
            }
            definition = definition.substring(definition.indexOf("\n") + 1);
            nghiaCuaTu.setText(definition);
            if (word.equals("name"))
                phienAm = "neim";
            choGhiPhienAm.setText("/" + phienAm + "/");
        }
    }

    @FXML
    void xoaTu(ActionEvent event) {
        clickSound.play();
        String word = oTimKiem.getText();
        dictionary.removeWord(word);
        trie.delete(word);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText(null);
        alert.setContentText("đã xóa");

        alert.showAndWait();
        choGhiPhienAm.setText("");
        nghiaCuaTu.setText("");
    }

    @FXML
    void suaTu(ActionEvent event) {
        clickSound.play();
        panel_suaTu.toFront();
        String TA = oTimKiem.getText();
        String TV = nghiaCuaTu.getText();
        String PA = choGhiPhienAm.getText();
        tiengAnh.setText(TA);
        tiengViet2.setText(TV);
        phienAm2.setText(PA);
    }

    @FXML
    void luuSuaTu(ActionEvent event) {
        clickSound.play();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText(null);
        alert.setContentText("đã lưu");
        alert.showAndWait();
        String tuMoi = tiengAnh.getText();
        String nghiaCuaTuMoi = tiengViet2.getText();
        String phienAmMoi = "/" + phienAm2.getText() + "/";
        nghiaCuaTuMoi = phienAmMoi + "\n" + nghiaCuaTuMoi;
        dictionary.addWord(tuMoi, nghiaCuaTuMoi);
        trie.insert(tuMoi);
    }

    @FXML
    void luuThemTu(ActionEvent event) {
        clickSound.play();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText(null);
        alert.setContentText("đã lưu");
        alert.showAndWait();
        String tuMoi = themTiengAnh.getText();
        String nghiaCuaTuMoi = themTiengViet.getText();
        String phienAmMoi = "/" + themPhienAm.getText() + "/";
        nghiaCuaTuMoi = phienAmMoi + "\n" + nghiaCuaTuMoi;
        dictionary.addWord(tuMoi, nghiaCuaTuMoi);
        trie.insert(tuMoi);
    }

    @FXML
    void back(ActionEvent event) {
        clickSound.play();
        panel_suaTu.toBack();
    }

    @FXML
    void chonGame1(ActionEvent event) {
        panel_game1.toFront();
        startNewGame();
        clickSound.play();
    }

    @FXML
    void backGame(ActionEvent event) {
        if (timeline != null) {
            timeline.stop(); // Dừng Timeline nếu đang chạy

        }
        clickSound.play();
        panel_game.toFront();


    }

    public void initialize() {

        readQuestionsFromFile("C:\\Users\\LINH\\Desktop\\BTL_DictionaryApp_Final\\BTL_0405\\BTL_0405\\BTL - Da_co_game\\Test13\\BTL - TaQuangLinh\\file_du_lieu\\Question&key.txt");
        //    startNewGame();


        // thêm cho phần Trie
        trie.loadFromFile("C:\\Users\\LINH\\Desktop\\BTL_DictionaryApp_Final\\BTL_0405\\BTL_0405\\BTL - Da_co_game\\Test13\\BTL - TaQuangLinh\\file_du_lieu\\tu_dien_2");
        suggestionList.setItems(suggestions);

        oTimKiem.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                nhapTu();
                timKiem();

            } else if (event.getCode() == KeyCode.DOWN && !suggestions.isEmpty()) {
                suggestionList.requestFocus();
                suggestionList.getSelectionModel().select(0);
            }
        });

        suggestionList.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                String selectedSuggestion = suggestionList.getSelectionModel().getSelectedItem();
                oTimKiem.setText(selectedSuggestion);
                timKiem();
                suggestionList.setVisible(true);
            }
        });

        loadImagesFromDirectory("C:\\Users\\LINH\\Desktop\\BTL_DictionaryApp_Final\\BTL_0405\\BTL_0405\\BTL - Da_co_game\\Test13\\BTL - TaQuangLinh\\src\\main\\resources\\com\\example\\demo7\\Image_Game2");
        loadAnswers("C:\\Users\\LINH\\Desktop\\BTL_DictionaryApp_Final\\BTL_0405\\BTL_0405\\BTL - Da_co_game\\Test13\\BTL - TaQuangLinh\\src\\main\\resources\\com\\example\\demo7\\answers.txt");

    }

    private void startNewGame() {
        index = 0; // Reset lại chỉ số câu hỏi
        correctGuesses = 0; // Reset lại số câu trả lời đúng
        score_now.setText("Score: 0"); // Reset điểm
        highscoreLabel.setText("       High Score: " + readScore("C:\\Users\\LINH\\Desktop\\BTL_DictionaryApp_Final\\BTL_0405\\BTL_0405\\BTL - Da_co_game\\Test13\\BTL - TaQuangLinh\\file_du_lieu\\highscore.txt")); // Hiển thị điểm cao nhất
        timerStarted = false; // Đặt lại trạng thái của biến timerStarted
        nextQuestion(); // Bắt đầu hiển thị câu hỏi đầu tiên
        confirmButton.setDisable(false);
        ButtonA.setDisable(false);
        ButtonB.setDisable(false);
        ButtonC.setDisable(false);
        ButtonD.setDisable(false);
        answerLabelA.setDisable(false);
        answerLabelB.setDisable(false);
        answerLabelC.setDisable(false);
        answerLabelD.setDisable(false);
        seconds_left.setDisable(false);

    }

    private void readQuestionsFromFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            ArrayList<String> lines = readLinesFromFile(filename);
            ArrayList<String> arrayList = new ArrayList<>(lines);
            Collections.shuffle(arrayList);
            for (String line : arrayList) {
                String[] parts = line.split(";");
                if (parts.length >= 3) {
                    questions.add(parts[0]);
                    options.add(parts[1].split(","));
                    answers.add(parts[2].charAt(0));
                } else {
                    System.err.println("Invalid question format: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<String> readLinesFromFile(String filename) throws IOException {
        ArrayList<String> lines = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }
        reader.close();
        return lines;
    }

    private void nextQuestion() {
        if (index >= totalQuestions) {
            showResult(); // Nếu đã hết câu hỏi, hiển thị kết quả
        } else {
            questionLabel.setWrapText(true);
            questionTextField.setText("Question " + (index + 1));
            questionLabel.setText(questions.get(index));
            String[] currentOptions = options.get(index);
            if (currentOptions.length >= 4) {
                answerLabelA.setText(options.get(index)[0]);
                answerLabelB.setText(options.get(index)[1]);
                answerLabelC.setText(options.get(index)[2]);
                answerLabelD.setText(options.get(index)[3]);
            }
            if (timeline != null) {
                timeline.stop();
            }
            selectedAnswer = ' ';
            answerSubmitted = false;
            seconds = 10;
            seconds_left.setText("     " + seconds);
            answerLabelA.setStyle("-fx-text-fill: black;");
            answerLabelB.setStyle("-fx-text-fill: black;");
            answerLabelC.setStyle("-fx-text-fill: black;");
            answerLabelD.setStyle("-fx-text-fill: black;");
            ButtonA.setDisable(false);
            ButtonB.setDisable(false);
            ButtonC.setDisable(false);
            ButtonD.setDisable(false);

            if (!timerStarted) { // Nếu thời gian chưa được bắt đầu, hãy bắt đầu nó
                startTimer();
                timerStarted = true;
            }
        }
    }

    private void startTimer() {
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            seconds--;
            seconds_left.setText("       " + seconds);
            if (seconds <= 0) {
                displayAnswer();
                timeline.stop();
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void displayAnswer() {
        answerSubmitted = true; // Set answerSubmitted to true to prevent further selection
        timeline.stop(); // Stop the timer

        ButtonA.setDisable(true);
        ButtonB.setDisable(true);
        ButtonC.setDisable(true);
        ButtonD.setDisable(true);

        // Highlight the selected answer if incorrect
        if (selectedAnswer != answers.get(index)) {
            switch (selectedAnswer) {
                case 'A':
                    answerLabelA.setStyle("-fx-text-fill: #ff0000;");
                    break;
                case 'B':
                    answerLabelB.setStyle("-fx-text-fill: #ff0000;");
                    break;
                case 'C':
                    answerLabelC.setStyle("-fx-text-fill: #ff0000;");
                    break;
                case 'D':
                    answerLabelD.setStyle("-fx-text-fill: #ff0000;");
                    break;
                default:
                    break;
            }
        }

        // Highlight the correct answer
        switch (answers.get(index)) {
            case 'A':
                answerLabelA.setStyle("-fx-text-fill: green;");
                break;
            case 'B':
                answerLabelB.setStyle("-fx-text-fill: green;");
                break;
            case 'C':
                answerLabelC.setStyle("-fx-text-fill: green;");
                break;
            case 'D':
                answerLabelD.setStyle("-fx-text-fill: green;");
                break;
        }

        //  Tao đỗ trễ giữa các câu hỏi
        PauseTransition pauseTransition = new PauseTransition(Duration.seconds(1.5));
        pauseTransition.setOnFinished(event -> {
            if (answers.size() > index) {
                index++;
                if (index < totalQuestions) {
                    nextQuestion();
                    startTimer();
                } else {
                    showResult();
                }
            }
        });
        pauseTransition.play();
    }

    private void showResult() {
        Reslut.toFront();
        Reslut.setVisible(true); // Hiển thị AnchorPane "Reslut"
        int percentage1 = (correctGuesses * 100) / totalQuestions; // Tính tỷ lệ phần trăm câu trả lời đúng
        correct_answer.setText("You answered " + correctGuesses + "/" + totalQuestions);
        percentage.setText("Percentage: " + percentage1 + "%");
        confirmButton.setDisable(true);
        ButtonA.setDisable(true);
        ButtonB.setDisable(true);
        ButtonC.setDisable(true);
        ButtonD.setDisable(true);
        answerLabelA.setDisable(true);
        answerLabelB.setDisable(true);
        answerLabelC.setDisable(true);
        answerLabelD.setDisable(true);
        seconds_left.setDisable(true);


        if (timeline != null) {
            timeline.stop(); // Dừng Timeline nếu đang chạy
        }
    }

    @FXML
    void handleButtonAction(ActionEvent event) {
        if (!answerSubmitted) {
            Button selectedButton = (Button) event.getSource();
            selectedAnswer = selectedButton.getText().charAt(0);
        }
    }

    @FXML
    void handleConfirmButtonAction(ActionEvent event) {
        if (!answerSubmitted) {
            answerSubmitted = true;
            if (selectedAnswer != ' ') {
                if (selectedAnswer == answers.get(index)) {
                    correctGuesses++;
                    score_now.setText("Score: " + correctGuesses * 10);
                    updateHighscore();
                }
                displayAnswer();
                selectedAnswer = ' ';
                timeline.stop();
            }
        }
    }

    private void updateHighscore() {
        int currentHighscore = readScore("C:\\Users\\LINH\\Desktop\\BTL_DictionaryApp_Final\\BTL_0405\\BTL_0405\\BTL - Da_co_game\\Test13\\BTL - TaQuangLinh\\file_du_lieu\\highscore.txt");
        int highscoreInPoints = correctGuesses * 10; // Điểm cao nhất tính bằng số câu đúng nhân 10
        if (highscoreInPoints > currentHighscore) {
            highscore = highscoreInPoints;
            highscoreLabel.setText("       High Score: " + highscore); // Hiển thị điểm cao nhất với đơn vị là 10
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\LINH\\Desktop\\BTL_DictionaryApp_Final\\BTL_0405\\BTL_0405\\BTL - Da_co_game\\Test13\\BTL - TaQuangLinh\\file_du_lieu\\highscore.txt"))) {
                writer.write(Integer.toString(highscore));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private int readScore(String filename) {
        int currentHighscore = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line = br.readLine();
            if (line != null) {
                currentHighscore = Integer.parseInt(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return currentHighscore;
    }

    @FXML
    void exitButtonAction(ActionEvent event) {
        panel_game.toFront();
        if (timeline != null) {
            timeline.stop(); // Dừng Timeline nếu đang chạy
            timerStarted = false;
        }
        clickSound.play();
    }

    @FXML
    public void nhapTu() {
        String text = oTimKiem.getText();
        suggestions.clear();
        List<String> trieSuggestions = trie.search(text);
        if (!trieSuggestions.isEmpty()) {
            suggestions.addAll(trieSuggestions);
            if (!suggestionList.isVisible()) {
                suggestionList.setVisible(true);
            }
        } else {
            suggestionList.setVisible(false);
        }
    }

    private void loadImagesFromDirectory(String directoryPath) {
        File directory = new File(directoryPath);
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.getName().endsWith(".png")) {
                    String imagePath = file.toURI().toString();
                    // System.out.println(imagePath);
                    imagePaths.add(imagePath);
                }
            }
        }
    }

    private void loadAnswers(String filePath) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("#");
                if (parts.length >= 2) {
                    String imagePath1 = parts[0];
                    //  System.out.println(imagePath1);
                    String answer = parts[1].replace("#", "").trim();
                    // System.out.println(answer);
                    imageToAnswerMap.put(imagePath1, answer);
                } else {
                    System.err.println("Invalid data format: " + line);
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getRandomImagePath() {
        if (imagePaths.isEmpty()) {
            return null;
        }
        Random random = new Random();
        return imagePaths.get(random.nextInt(imagePaths.size()));
    }

    @FXML
    void chonGame2(ActionEvent event) {
        Game2.toFront();
        clickSound.play();
        String imagePath2 = getRandomImagePath();
        currentQuestionImagePath = getRandomImagePath(); // Lưu đường dẫn của ảnh hiện tại
        Image image = new Image(currentQuestionImagePath);
        imageGame.setImage(image);
        //clickSound.play();


    }

    @FXML
    void ClickButton(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        String character = clickedButton.getText();
        String currentText = Labelclicked.getText();

        if (clickedButton.getId().equals("nameBACKSPACE")) {
            if (!currentText.isEmpty() && !currentText.equals("Label:")) {
                Labelclicked.setText(currentText.substring(0, currentText.length() - 1));
            }
        } else if (clickedButton.getId().equals("nameENTER")) {
            enterCount++;
            if (enterCount <= 10) {
                String enteredText = Labelclicked.getText().trim();

                String correctAnswer = imageToAnswerMap.get(currentQuestionImagePath);
                //System.out.println(currentQuestionImagePath);
                System.out.println(correctAnswer);
                if (enteredText.equalsIgnoreCase(correctAnswer)) {
                    scoregame2++;
                    score_game2.setText("" + scoregame2 * 10);
                }
                Labelclicked.setText("");
                currentQuestionImagePath = getRandomImagePath(); // Lưu đường dẫn của ảnh hiện tại
                Image image = new Image(currentQuestionImagePath);
                imageGame.setImage(image);
            } else {
                // Kết thúc trò chơi
                Labelclicked.setText("Game Over");
                // Vô hiệu hóa tất cả các nút
                disableAllButtons();
            }
        } else {
            if (currentText.isEmpty() || currentText.equals("Label:")) {
                Labelclicked.setText(character);
            } else {
                Labelclicked.setText(currentText + character);
            }
        }
    }

    @FXML
    void RestartGame2(ActionEvent event) {
        clickSound.play();
        enterCount = 0;
        scoregame2 = 0;
        score_game2.setText("0");
        Labelclicked.setText("");
        String newImagePath = getRandomImagePath();
        if (newImagePath != null) {
            currentQuestionImagePath = newImagePath;
            Image newImage = new Image(currentQuestionImagePath);
            imageGame.setImage(newImage);

        }
        enableAllButtons();
    }

    @FXML
    void backGame2(ActionEvent event) {
        Labelclicked.setText("");
        panel_game.toFront();
        clickSound.play();
    }

    private void disableAllButtons() {
        nameA.setDisable(true);
        nameB.setDisable(true);
        nameC.setDisable(true);
        nameD.setDisable(true);
        nameE.setDisable(true);
        nameF.setDisable(true);
        nameG.setDisable(true);
        nameH.setDisable(true);
        nameI.setDisable(true);
        nameJ.setDisable(true);
        nameK.setDisable(true);
        nameL.setDisable(true);
        nameM.setDisable(true);
        nameN.setDisable(true);
        nameO.setDisable(true);
        nameP.setDisable(true);
        nameQ.setDisable(true);
        nameR.setDisable(true);
        nameS.setDisable(true);
        nameT.setDisable(true);
        nameU.setDisable(true);
        nameV.setDisable(true);
        nameW.setDisable(true);
        nameX.setDisable(true);
        nameY.setDisable(true);
        nameZ.setDisable(true);
        nameBACKSPACE.setDisable(true);
        nameENTER.setDisable(true);
    }

    private void enableAllButtons() {
        nameA.setDisable(false);
        nameB.setDisable(false);
        nameC.setDisable(false);
        nameD.setDisable(false);
        nameE.setDisable(false);
        nameF.setDisable(false);
        nameG.setDisable(false);
        nameH.setDisable(false);
        nameI.setDisable(false);
        nameJ.setDisable(false);
        nameK.setDisable(false);
        nameL.setDisable(false);
        nameM.setDisable(false);
        nameN.setDisable(false);
        nameO.setDisable(false);
        nameP.setDisable(false);
        nameQ.setDisable(false);
        nameR.setDisable(false);
        nameS.setDisable(false);
        nameT.setDisable(false);
        nameU.setDisable(false);
        nameV.setDisable(false);
        nameW.setDisable(false);
        nameX.setDisable(false);
        nameY.setDisable(false);
        nameZ.setDisable(false);
        nameBACKSPACE.setDisable(false);
        nameENTER.setDisable(false);
    }
}


















