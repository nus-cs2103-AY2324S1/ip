package cyrus.ui.components;

import java.net.URL;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import cyrus.utility.DateUtility;
import javafx.collections.FXCollections;
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
        this(new HashMap<>(), new HashMap<>());
    }

    /**
     * Constructor for creating a StatisticsDashboard controller.
     */
    public StatisticsDashboard(
            HashMap<String, Long> taskDistribution,
            HashMap<LocalDate, Long> weeklyTaskCompletionRate
    ) {
        taskDistributionData = loadTaskDistributionData(taskDistribution);
        weeklyTaskCompletionRateData = loadWeeklyTaskCompletionRateData(weeklyTaskCompletionRate);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        taskDistributionChart.setData(taskDistributionData);
        weeklyTaskCompletionRateChart.getData().add(weeklyTaskCompletionRateData);
    }

    /**
     * Loads task distribution data into pie chart data form.
     *
     * @param tasksDistribution {@code String <> Long} where {@code String} is the type of task and {@code Long} is
     *                          the number of tasks that have that type.
     * @return {@code PieChart.Data} formatted data.
     */
    private ObservableList<PieChart.Data> loadTaskDistributionData(HashMap<String, Long> tasksDistribution) {
        var data = FXCollections.<PieChart.Data>observableArrayList();
        for (var entry : tasksDistribution.entrySet()) {
            data.add(new PieChart.Data(entry.getKey(), entry.getValue()));
        }

        return data;
    }

    private XYChart.Series<String, Long> loadWeeklyTaskCompletionRateData(
            HashMap<LocalDate, Long> weeklyTasksCompletionRate
    ) {
        var data = new XYChart.Series<String, Long>();
        for (var entry : weeklyTasksCompletionRate.entrySet()) {
            data.getData().add(
                    new XYChart.Data<>(DateUtility.toInputFormat(entry.getKey()), entry.getValue())
            );
        }

        if (weeklyTasksCompletionRate.size() > 0) {
            var sortedEntries = data
                    .getData()
                    .stream()
                    .sorted(Comparator.comparing(XYChart.Data::getXValue))
                    .collect(Collectors.toList());
            data.setData(FXCollections.observableArrayList(sortedEntries));
        }

        return data;
    }
}
