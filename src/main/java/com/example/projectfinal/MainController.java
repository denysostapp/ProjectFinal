package com.example.projectfinal;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class MainController {
    private ListView<League> leaguesList = new ListView<>();
    private ListView<Team> teamsList = new ListView<>();
    private TableView<Match> matchesTable;
    private TextField newLeagueName = new TextField();
    private TextField newTeamName = new TextField();
    private Button newTeamLogoButton = new Button("Load Logo");
    private String newTeamLogoPath;
    private TextField newTeamStadium = new TextField();
    private TextField newTeamCoach = new TextField();

    private Button addLeagueButton = new Button("Add League");
    private Button addTeamButton = new Button("Add Team");
    private VBox teamDetails = new VBox(10);

    public BorderPane createContent() {
        BorderPane borderPane = new BorderPane();

        HBox titleBox = new HBox();
        titleBox.setPrefSize(1200,80);
        titleBox.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

        Image logo = new Image("file:src/main/resources/0c1e07d8c596e69d24cd414c2ec6b1d9.png");
        ImageView imageView = new ImageView(logo);
        imageView.setFitWidth(80);
        imageView.setFitHeight(80);

        Label title1 = new Label("Football Score");
        title1.setStyle("-fx-font-family: 'Comic Sans MS'; -fx-font-size: 48; -fx-font-weight: bold; -fx-text-fill: #d0d0d0");
        titleBox.setAlignment(Pos.CENTER_LEFT);
        titleBox.getChildren().addAll(imageView, title1);

        // Конфигурация для верхней части
        HBox topPanel = new HBox(10, newLeagueName, addLeagueButton);
        topPanel.setStyle("-fx-padding: 10;");
        VBox topContainer = new VBox(titleBox, topPanel);
        borderPane.setTop(topContainer);
        newTeamName.setPromptText("Team Name");
        newTeamStadium.setPromptText("Stadium");
        newTeamCoach.setPromptText("Coach");
        newTeamLogoButton.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
            File selectedFile = fileChooser.showOpenDialog(null);
            if (selectedFile != null) {
                newTeamLogoPath = selectedFile.toURI().toString();
            }
        });

        // Конфигурация для нижней части
        HBox bottomPanel = new HBox(10, newTeamName,newTeamLogoButton,newTeamStadium,newTeamCoach, addTeamButton);
        bottomPanel.setStyle("-fx-padding: 10;");
        borderPane.setBottom(bottomPanel);

        initializeLeagues();

        leaguesList.setOnMouseClicked(event -> onLeagueSelected());
        VBox leftPanel = new VBox(leaguesList);
        borderPane.setLeft(leftPanel);

        // Список команд
        teamsList.setOnMouseClicked(event -> onTeamSelected());
        VBox rightPanel = new VBox(teamsList);
        borderPane.setRight(rightPanel);

        // Обработчики событий
        addLeagueButton.setOnAction(event -> addLeague());
        addTeamButton.setOnAction(event -> addTeam());
        borderPane.setCenter(teamDetails);

        return borderPane;
    }
    private void onTeamSelected() {
        Team selectedTeam = teamsList.getSelectionModel().getSelectedItem();
        if (selectedTeam != null) {
            displayTeamDetails(selectedTeam);
        }
    }
    private void displayTeamDetails(Team team) {
        teamDetails.getChildren().clear();
        Text name = new Text(team.getName());
        name.setFont(Font.font("Arial", FontWeight.BOLD, 24));

        ImageView logo = new ImageView(new Image(team.getLogoPath()));
        logo.setFitHeight(50);
        logo.setFitWidth(50);

        HBox nameAndLogo = new HBox();
        nameAndLogo.getChildren().addAll(logo, name);

        Text stadium = new Text("Stadium: " + team.getStadium());
        Text coach = new Text("Coach: " + team.getCoach());

        teamDetails.getChildren().addAll(nameAndLogo, stadium, coach);
        Button addMatchButton = new Button("Add Match");
        addMatchButton.setOnAction(e ->showAddMatchDialog(team));
        setupMatcesTable();
        matchesTable.setItems(FXCollections.observableArrayList(team.getMatches()));
        teamDetails.getChildren().addAll(addMatchButton,matchesTable);
    }
    private void initializeLeagues() {
        League premierLeague = new League("Premier League");
        premierLeague.addTeam(new Team("Manchester United", "file:src/main/resources/250px-Manchester_United_FC_crest.svg.png", "Old Trafford", "Ole Gunnar Solskjaer"));
        premierLeague.addTeam(new Team("Liverpool", "file:src/main/resources/640px-FC_Liverpool.svg.png", "Anfield", "Jurgen Klopp"));
        premierLeague.addTeam(new Team("Chelsea", "file:src/main/resources/Chelsea_FC.svg.png", "Stamford Bridge", "Frank Lampard"));
        premierLeague.addTeam(new Team("Arsenal", "file:src/main/resources/Arsenal.png", "Emirates Stadium", "Mikel Arteta"));
        premierLeague.addTeam(new Team("Manchester City", "file:src/main/resources/mancity.png", "Etihad Stadium", "Pep Guardiola"));


        League laLiga = new League("La Liga");
        laLiga.addTeam(new Team("Real Madrid", "file:src/main/resources/Real_Madrid.png", "Santiago Bernabeu", "Zinedine Zidane"));
        laLiga.addTeam(new Team("Barcelona", "file:src/main/resources/970bc06c779e44a50be963a11feadc04.png", "Camp Nou", "Ronald Koeman"));
        laLiga.addTeam(new Team("Atletico Madrid", "file:src/main/resources/Atletico_Madrid_2017_logo.svg.png", "Wanda Metropolitano", "Diego Simeone"));
        laLiga.addTeam(new Team("Valencia", "file:src/main/resources/valencia.png", "Mestalla", "Javi Gracia"));
        laLiga.addTeam(new Team("Sevilla",  "file:src/main/resources/Sevilla_FC_logo.svg.png", "Ramon Sanchez Pizjuan", "Julen Lopetegui"));


        League serieA = new League("Serie A");
        serieA.addTeam(new Team("Juventus", "file:src/main/resources/Juventus_FC_2017_logo.svg.png", "Allianz Stadium", "Andrea Pirlo"));
        serieA.addTeam(new Team("AC Milan", "file:src/main/resources/", "San Siro", "Stefano Pioli"));
        serieA.addTeam(new Team("Inter Milan", "file:src/main/resources/", "San Siro", "Antonio Conte"));
        serieA.addTeam(new Team("Roma", "file:src/main/resources/", "Stadio Olimpico", "Paulo Fonseca"));
        serieA.addTeam(new Team("Napoli", "file:src/main/resources/", "Stadio San Paolo", "Gennaro Gattuso"));
        serieA.addTeam(new Team("Lazio", "file:src/main/resources/", "Stadio Olimpico", "Simone Inzaghi"));

        League ligue1 = new League("Ligue 1");
        ligue1.addTeam(new Team("PSG", "file:src/main/resources/", "Parc des Princes", "Mauricio Pochettino"));
        ligue1.addTeam(new Team("Lyon", "file:src/main/resources/", "Groupama Stadium", "Rudi Garcia"));
        ligue1.addTeam(new Team("Marseille", "file:src/main/resources/", "Stade Velodrome", "Andre Villas-Boas"));
        ligue1.addTeam(new Team("Monaco", "file:src/main/resources/", "Stade Louis II", "Niko Kovac"));
        ligue1.addTeam(new Team("Saint-Etienne", "file:src/main/resources/", "Stade Geoffroy-Guichard", "Claude Puel"));
        ligue1.addTeam(new Team("Bordeaux", "file:src/main/resources/", "Stade Matmut-Atlantique", "Jean-Louis Gasset"));

        leaguesList.getItems().addAll(premierLeague, laLiga, serieA, ligue1);
    }

    private void onLeagueSelected() {
        League selectedLeague = leaguesList.getSelectionModel().getSelectedItem();
        if (selectedLeague != null) {
            teamsList.getItems().clear();
            for (Team team : selectedLeague.getTeams()) {
                teamsList.getItems().add(team);
            }
        }
    }

    private void addLeague() {
        String leagueName = newLeagueName.getText();
        if (!leagueName.isEmpty()) {
            League newLeague = new League(leagueName);
            leaguesList.getItems().add(newLeague);
            newLeagueName.clear();
        }
    }

    private void addTeam() {
        League selectedLeague = leaguesList.getSelectionModel().getSelectedItem();
        String teamName = newTeamName.getText();
        String stadium = newTeamStadium.getText();
        String coach = newTeamCoach.getText();
        if (selectedLeague != null && !teamName.isEmpty()) {
            Team newTeam = new Team(teamName, newTeamLogoPath, stadium, coach);
            selectedLeague.addTeam(newTeam);
            teamsList.getItems().add(newTeam);
            newTeamName.clear();
            newTeamLogoPath = null;
            newTeamStadium.clear();
            newTeamCoach.clear();
            displayTeamDetails(newTeam);
        }
    }
    public void showAddMatchDialog(Team team) {
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Add New Match");

        VBox vbox = new VBox(10);
        TextField dateField = new TextField();
        dateField.setPromptText("Date (dd/MM/yyyy)");
        TextField opponentField = new TextField();
        opponentField.setPromptText("Opponent");
        TextField scoreField = new TextField();
        scoreField.setPromptText("Score");
        TextField resultField = new TextField();
        resultField.setPromptText("Result");
        Button saveButton = new Button("Save Match");

        saveButton.setOnAction(e -> {
            Match newMatch = new Match(
                    dateField.getText(),
                    opponentField.getText(),
                    scoreField.getText(),
                    resultField.getText()
            );
            addMatchToTeam(newMatch, team);
            dialogStage.close();
        });

        vbox.getChildren().addAll(
                new Label("Date"), dateField,
                new Label("Opponent"), opponentField,
                new Label("Score"), scoreField,
                new Label("Result"), resultField,
                saveButton
        );

        Scene scene = new Scene(vbox,400,400);
        dialogStage.setScene(scene);
        dialogStage.show();
    }
    private void setupMatcesTable(){
        matchesTable = new TableView<>();
        TableColumn<Match, String> dateColumn = new TableColumn<>("Date");
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        TableColumn<Match, String> opponentColumn = new TableColumn<>("Opponent");
        opponentColumn.setCellValueFactory(new PropertyValueFactory<>("opponent"));

        TableColumn<Match, String> scoreColumn = new TableColumn<>("Score");
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));

        TableColumn<Match, String> resultColumn = new TableColumn<>("Result");
        resultColumn.setCellValueFactory(new PropertyValueFactory<>("result"));
        matchesTable.maxWidth(400);

        matchesTable.getColumns().addAll(dateColumn, opponentColumn, scoreColumn, resultColumn);
    }
    private void addMatchToTeam(Match match, Team team){
        team.addMatch(match);
        matchesTable.setItems(FXCollections.observableArrayList(team.getMatches()));
    }
}

