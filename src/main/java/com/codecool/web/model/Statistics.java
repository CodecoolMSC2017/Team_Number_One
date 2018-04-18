package com.codecool.web.model;

import com.codecool.web.service.DataStorage;
import com.codecool.web.service.ResultService;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Statistics {
    ResultService resultService;

    public Statistics(ResultService resultService) {
        this.resultService = resultService;
    }

    public HttpServletRequest createChart(HttpServletRequest req) throws SQLException {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        HttpSession session = req.getSession(false);
        List<Result> results = new ArrayList<>();
        User user = (User)session.getAttribute("user");

        for (Result result : resultService.getAllResults()) {
            if (result.getUser().getUniqueId() == (user.getUniqueId())) {
                results.add(result);
            }
        }

        for (Result res : results) {
            double percent = (double)res.getScore() / (double)res.getAp().getMaxScore() * 100;
            System.out.println(percent);
            System.out.println(res.getScore());
            System.out.println(res.getAp().getMaxScore());
            dataset.addValue(percent, res.getUser().getName(), res.getSubmissionDate());
        }

        JFreeChart chart = ChartFactory.createBarChart("Your scores", "Category", "Score", dataset, PlotOrientation.VERTICAL, true, false, false);

        chart.getCategoryPlot().getRangeAxis().setLowerBound(0.0);
        chart.getCategoryPlot().getRangeAxis().setUpperBound(120.0);

        try {

            final ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());
            final String path = "protected/resources/chart_" + user.getUniqueId() + ".png";
            final File file1 = new File(req.getServletContext().getRealPath(".") + "/" + path);

            ChartUtilities.saveChartAsPNG(file1, chart, 600, 400, info);

            req.setAttribute("path", path);
        } catch (Exception e) {
            System.out.println(e);
        }

        return req;
    }
}
