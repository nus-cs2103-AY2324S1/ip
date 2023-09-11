package cyrus.ui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.VBox;

/**
 * Popup to view statistics of the use of the task list.
 */
public class StatisticsDashboard extends VBox implements Initializable {
    @FXML
    private PieChart taskDistribution;

    private final ObservableList<PieChart.Data> pieChartData;

    /**
     * Empty constructor.
     */
    public StatisticsDashboard() {
        this(null);
    }

    /**
     * Constructor for creating a StatisticsDashboard controller.
     */
    public StatisticsDashboard(ObservableList<PieChart.Data> pieChartData) {
        this.pieChartData = pieChartData;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        taskDistribution.setData(pieChartData);
    }
}
