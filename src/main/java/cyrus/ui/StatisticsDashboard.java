package cyrus.ui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;

/**
 * Popup to view statistics of the use of the task list.
 */
public class StatisticsDashboard extends VBox implements Initializable {
    @FXML
    private PieChart taskDistributionChart;
    @FXML
    private LineChart<String, Long> weeklyTaskCompletionRateChart;

    private final ObservableList<PieChart.Data> taskDistributionData;
    private final XYChart.Series<String, Long> weeklyTaskCompletionRateData;

    /**
     * Empty constructor.
     */
    public StatisticsDashboard() {
        this(null, null);
    }

    /**
     * Constructor for creating a StatisticsDashboard controller.
     */
    public StatisticsDashboard(
            ObservableList<PieChart.Data> taskDistributionData,
            XYChart.Series<String, Long> weeklyTaskCompletionRateData
    ) {
        this.taskDistributionData = taskDistributionData;
        this.weeklyTaskCompletionRateData = weeklyTaskCompletionRateData;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        taskDistributionChart.setData(taskDistributionData);
        weeklyTaskCompletionRateChart.getData().add(weeklyTaskCompletionRateData);
    }
}
