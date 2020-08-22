package minesweeper;

import communication.Communication;
import domain.Game;
import domain.Level;
import domain.User;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import minesweeper.GUIListener.GUIListenerExitGame;
import minesweeper.GUIListener.GUIListenerGameAuthor;
import minesweeper.GUIListener.GUIListenerGameInfo;
import minesweeper.GUIListener.GUIListenerSetDifficulty;
import minesweeper.GUIListener.GUIListenerStartGame;
import threads.ScoreListener;
import transfer.Request;
import transfer.Response;
import transfer.util.Operation;

public class Controller {

    public boolean[][] mines;
    public int numberOfMines;
    public int numberOfFieldsLeft;
    int rows;
    int cols;
    public double difficulty;

    public FXMLDocumentController fxdc;
    ScoreListener scoreListener;

    List<ScoreListener> counterThreads;

    private User user;
    private Game game;
    private Level level;

    public Controller(FXMLDocumentController fxdc) {
        super();
        this.fxdc = fxdc;
        rows = this.fxdc.getFieldRows();
        cols = this.fxdc.getFieldColumns();

        this.user = MainMenuFX.user;

        numberOfMines = 0;
        numberOfFieldsLeft = 0;

        this.fxdc.start.setOnAction(new GUIListenerStartGame(this));
        this.fxdc.prikazInformacija.setOnAction(new GUIListenerGameInfo(this));
        this.fxdc.autorPrograma.setOnAction(new GUIListenerGameAuthor(this));
        this.fxdc.Izlaz.setOnAction(new GUIListenerExitGame(this));
        MainMenuFX.stage.setOnCloseRequest(new GUIListenerExitGame(this));
        this.fxdc.startGame.setOnAction(new GUIListenerStartGame(this));
        this.fxdc.postaviTezinu.setOnAction(new GUIListenerSetDifficulty(this));

        mines = new boolean[rows][cols];

        this.fxdc.score.setText(String.valueOf(0));

        this.fxdc.highscore.setText(String.valueOf(user.getHighscore()));
        counterThreads = new ArrayList<>();

        setNormalSmiley(this.fxdc.startGame);
        setNormalDifficulty();
    }

    public void inicijalizacijaIgre() {
        numberOfMines = 0;
        numberOfFieldsLeft = rows * cols;

        game = new Game();
        game.setDate(game.getDate(new Date()));
        game.setUserId(user.getPrimaryKey());

        setNormalSmiley(this.fxdc.startGame);

        if (!counterThreads.isEmpty()) {
            stopAllRunningThreads();
        } else {
            scoreListener = new ScoreListener(this);
            scoreListener.start();
            counterThreads.add(scoreListener);
        }

        System.out.println("Currently running: " + scoreListener.getName());

        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                Button b = null;

//                boolean hasBomb = Math.random() < 0.2; // hard
//                boolean hasBomb = Math.random() < 0.05;
//                boolean hasBomb = Math.random() < difficulty;
                boolean hasBomb = Math.random() < level.bombPercentage;
                mines[x][y] = hasBomb;

                if (hasBomb) {
                    numberOfMines++;
                    numberOfFieldsLeft--;
                }

                try {
                    b = this.fxdc.getButton(x, y);
                    resetButton(b);
                    resetButton(b);
//                    b.setText("");
//                    b.setGraphic(null);
//                    b.getStyleClass().remove("bg-white");
//                    b.getStyleClass().remove("bg-bomb");
//                    b.getStyleClass().remove("bg-white");
//                    b.getStyleClass().remove("bg-bomb");
//                    b.getStyleClass().add("bg-dark");

                } catch (NoSuchFieldException | IllegalAccessException | IllegalArgumentException ex) {
                    Logger.getLogger(Controller.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                }

//                b.setDisable(false);
            }
        }
    }

    void resetButton(Button b) {
        b.setText("");
        b.setGraphic(null);
        b.getStyleClass().remove("bg-white");
        b.getStyleClass().remove("bg-bomb");
        b.getStyleClass().remove("bg-white");
        b.getStyleClass().remove("bg-bomb");
        b.getStyleClass().add("bg-dark");
        b.setDisable(false);
    }

    void setEasyDifficulty() {
//        difficulty = 0.1;
        level = getLevel("Easy");
    }

    void setNormalDifficulty() {
//        difficulty = 0.2;        
        level = getLevel("Normal");
    }

    void setHardDifficulty() {
//        difficulty = 0.35;
        level = getLevel("Hard");
    }

    Level getLevel(String difficulty) {
        Request request = new Request();
        level = new Level();
        level.difficulty = difficulty;

        request.setLevel(level);
        request.setOperation(Operation.OPERATION_GET_LEVEL_DIFFICULTY);

        Communication.getInstance().sendRequest(request);
        Response response = Communication.getInstance().readResponse();

        return response.getLevel();
    }

    public void setDifficulty() {
        Alert difficultyAlert = new Alert(Alert.AlertType.CONFIRMATION);
        difficultyAlert.setTitle("Choose difficulty");
        difficultyAlert.setHeaderText(null);
        difficultyAlert.setContentText("Choose the difficulty of the game");

        ButtonType easy = new ButtonType("Easy");
        ButtonType normal = new ButtonType("Normal");
        ButtonType hard = new ButtonType("Hard");
        ButtonType cancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        difficultyAlert.getButtonTypes().setAll(easy, normal, hard, cancel);
        Optional<ButtonType> result = difficultyAlert.showAndWait();

        if (result.get() == easy) {
            setEasyDifficulty();
        } else if (result.get() == normal) {
            setNormalDifficulty();
        } else if (result.get() == hard) {
            setHardDifficulty();
        }

        inicijalizacijaIgre();
    }

    public void openCellStyle(Button b) {
        b.getStyleClass().remove("bg-bomb");
        b.getStyleClass().remove("bg-dark");
        b.getStyleClass().add("bg-white");
    }

    public void darkCellStyle(Button b) {
        b.getStyleClass().remove("bg-bomb");
        b.getStyleClass().remove("bg-white");
        b.getStyleClass().add("bg-dark");
    }

    public void bombCellStyle(Button b) {
        if (b.getGraphic() != null) {
            b.setGraphic(null);
        }
        b.setText("X");
        b.getStyleClass().remove("bg-white");
        b.getStyleClass().remove("bg-dark");
        b.getStyleClass().add("bg-bomb");
    }

    public void setNormalSmiley(Button b) {
        String location = "assets/icons/normal.png";
        ImageView imgView = setImageToButtonProportions(location, b);
        b.setGraphic(null);
        b.setGraphic(imgView);
    }

    public void setOnClickSmiley(Button b) {
        String location = "assets/icons/onClick.png";
        ImageView imgView = setImageToButtonProportions(location, b);
        b.setGraphic(null);
        b.setGraphic(imgView);
    }

    public void setGameOverSmiley(Button b) {
        String location = "assets/icons/gameOver.png";
        ImageView imgView = setImageToButtonProportions(location, b);
        b.setGraphic(null);
        b.setGraphic(imgView);
    }

    public void setWinSmiley(Button b) {
        String location = "assets/icons/win.png";
        ImageView imgView = setImageToButtonProportions(location, b);
        b.setGraphic(null);
        b.setGraphic(imgView);
    }

    public void open(Button b) {

        Integer row = GridPane.getRowIndex(b);
        Integer column = GridPane.getColumnIndex(b);

        int r = row == null ? 0 : row;
        int c = column == null ? 0 : column;

        if (b.isDisabled()) {
            return;
        }

        b.setGraphic(null);

        boolean mine = mines[r][c];

        if (mine) {
            bombCellStyle(b);
            System.out.println("Game Over");
            gameOver();
            setGameOverSmiley(this.fxdc.startGame);
        } else {

            setFieldNumber(b, r, c);
            openCellStyle(b);

            if (b.getText().isEmpty()) {
                try {
                    findEmptyCells(r, c);
                } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException ex) {
                    Logger.getLogger(FXMLDocumentController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                }
            } else {
                numberOfFieldsLeft--;
            }

            checkIfGameIsFinished();
        }

        b.setDisable(true);
        b.setOpacity(1);
    }

    public void setFlag(Button b) {

        if (b.getGraphic() != null) {
            b.setGraphic(null);
            return;
        }

        String location = "assets/icons/flag.png";
        ImageView imgView = setImageToButtonProportions(location, b);

        if (imgView != null) {
            b.setGraphic(imgView);
        } else {
            b.setGraphic(new ImageView());
        }
    }

    public void setFieldNumber(Button button, int row, int column) {
        int mineCount = getNumberOfSurroundingMines(row, column);

        if (mineCount > 0) {
            button.setText(Integer.toString(mineCount));
        }
    }

    public int getNumberOfSurroundingMines(int row, int column) {
        int mineCount = 0;

        // count surrounding mines
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (checkMine(mines, row + i, column + j)) {
                    mineCount++;
                }
            }
        }

        return mineCount;
    }

    public boolean checkMine(boolean[][] mines, int row, int column) {
        return row >= 0 && column >= 0 && row < mines.length && column < mines[row].length && mines[row][column];
    }

    public void findEmptyCells(int i, int j) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {

        if (i >= 0 && j >= 0 && i < rows && j < cols) {
            Button b = this.fxdc.getButton(i, j);

            int mineAroundCount = getNumberOfSurroundingMines(i, j);

            if (!b.isDisabled() && mines[i][j] == false && mineAroundCount == 0) {

                openCellStyle(b);
                b.setDisable(true);
                b.setOpacity(1);

                numberOfFieldsLeft--;

                List<Point> neighbours = getNeighbours(i, j);

                if (!neighbours.isEmpty()) {
                    neighbours.forEach(p -> {
                        try {
                            findEmptyCells(p.x, p.y);
                        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException ex) {
                            Logger.getLogger(FXMLDocumentController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                        }
                    });
                }
            } else if (!b.isDisabled() && mines[i][j] == false) {
//                b.setText(String.valueOf(mineAroundCount));
                setFieldNumber(b, i, j);
                openCellStyle(b);
                b.setDisable(true);
                b.setOpacity(1);

                numberOfFieldsLeft--;
            }
        }
    }

    private List<Point> getNeighbours(int row, int column) {
        List<Point> neighbours = new ArrayList<>();

        int[] points = new int[]{
            -1, -1,
            -1, 0,
            -1, 1,
            0, -1,
            0, 1,
            1, -1,
            1, 0,
            1, 1
        };

        for (int i = 0; i < points.length; i++) {
            int dx = points[i];
            int dy = points[++i];

            int newX = row + dx;
            int newY = column + dy;

            if (newX >= 0 && newX < rows
                    && newY >= 0 && newY < cols) {
                neighbours.add(new Point(newX, newY));
            }
        }

        return neighbours;
    }

    public void checkIfGameIsFinished() {
        int fieldsLeft = numberOfFieldsLeft;

        if (fieldsLeft == 0) {
            gameOver();
            win();
        }
//        else if (fieldsLeft > 0) {
//            numberOfFieldsLeft--;
//        }
    }

    public void gameOver() {
        
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                Button b = null;
                try {
                    b = this.fxdc.getButton(x, y);
                } catch (NoSuchFieldException | IllegalAccessException | IllegalArgumentException ex) {
                    Logger.getLogger(Controller.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                }

                if (mines[x][y]) {
                    bombCellStyle(b);
                } else if (!b.isDisabled()) {
                    darkCellStyle(b);
                }

                b.setDisable(true);
            }
        }
        
//        scoreListener.interrupt();
        stopAllRunningThreads();
    }

    private void win() {
        setWinSmiley(this.fxdc.startGame);

        Alert playAgainAlert = new Alert(Alert.AlertType.CONFIRMATION);
        playAgainAlert.setTitle("Winner!");
        playAgainAlert.setHeaderText(null);
        playAgainAlert.setContentText("You've won! Congratulations!\nYour score: " + this.fxdc.score.getText() + "\n\nWant to try again?");

        ButtonType playAgain = new ButtonType("Play again");
        ButtonType cancel = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

        playAgainAlert.getButtonTypes().setAll(playAgain, cancel);
        Optional<ButtonType> result = playAgainAlert.showAndWait();

        if (user.getHighscore() > Integer.parseInt(this.fxdc.score.getText())) {
            saveResult();
        }

        if (result.get() == playAgain) {
//            postaviTezinu();
            inicijalizacijaIgre();
        }
    }

    public void gameAuthor() {
        Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
        infoAlert.setTitle("Autor programa:");
        infoAlert.setHeaderText(null);
        infoAlert.setContentText("dipl. inz. organizacionih nauka Filip Markovski.");
        infoAlert.showAndWait();
    }

    public void gameInfo() {
        Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
        infoAlert.setTitle("Informacije o programu:");
        infoAlert.setHeaderText(null);
        infoAlert.setContentText("Minesweeper je video igra za jednog igrača. Cilj igre je da se otvore sva polja na tabli, bez detoniranja skrivenih mina. Polja sadrže broj, koji označa koliko ima mina oko njega.");
        infoAlert.showAndWait();
    }

    public void exitGame() {
//        if (scoreListener != null && scoreListener.isAlive()) {
//            scoreListener.interrupt();
//        }
        stopAllRunningThreads();
        Platform.exit();
        System.exit(0);
    }

    private ImageView setImageToButtonProportions(String location, Button button) {
        Image img = new Image(location);
        ImageView imgView = new ImageView(img);

        imgView.setPreserveRatio(true);
        imgView.fitWidthProperty().add(button.widthProperty().getValue() / 2);

        return imgView;
    }

    public void saveResult() {
        int newHighscore = Integer.parseInt(this.fxdc.score.getText());

        game.setScore(newHighscore);
        game.setLevel(level.getPrimaryKey());
        user.setHighscore(newHighscore);

        Request request = new Request();
        request.setGame(game);
        request.setLevel(level);
        request.setUser(user);
        request.setOperation(Operation.OPERATION_NEW_HIGHSCORE);

        Communication.getInstance().sendRequest(request);
        Response response = Communication.getInstance().readResponse();

        user.highscore = response.getUser().highscore;
        this.fxdc.highscore.setText(String.valueOf(user.getHighscore()));
    }

    private void stopAllRunningThreads() {
        for (ScoreListener thread : counterThreads) {
            if (!thread.isInterrupted()) {
                thread.interrupt();
                System.out.println("Removing thread: " + thread.getName());
            }
        }
        counterThreads.clear();
    }
}
