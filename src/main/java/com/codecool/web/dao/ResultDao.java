package com.codecool.web.dao;

import com.codecool.web.model.Result;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public final class ResultDao extends AbstractDao {

    private DatabaseUserDao userDao = new DatabaseUserDao(connection);

    public ResultDao(Connection connection) {
        super(connection);
    }

    public List<Result> findAllResults() throws SQLException {
        List<Result> allResults = new ArrayList<>();
        String sql = "SELECT * FROM results";
        try(Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql)){
            while(resultSet.next()){
                allResults.add(fetchResult(resultSet));
            }
        }

        return allResults;
    }

    public List<Result> findResultsByUserId(int userId) throws SQLException {
        List<Result> results = new ArrayList<>();
        String sql = "SELECT * FROM results WHERE userid = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(2, userId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    results.add(fetchResult(resultSet));
                }
            }
        }
        return results;
    }

    public List<Result> findResultsByPageId(int pageId) throws SQLException {
        List<Result> results = new ArrayList<>();
        String sql = "SELECT * FROM results WHERE pageid = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, pageId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    results.add(fetchResult(resultSet));
                }
            }
        }
        return results;
    }

    public void addResult(Result result) throws SQLException {
        boolean autoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);
        String sql = "INSERT INTO results (pageid, userid, timestamp, score) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            statement.setInt(1, result.getAssignmentPageId());
            statement.setInt(2, result.getUser().getUniqueId());
            statement.setString(3,result.getSubmissionDate().toString());
            statement.setInt(4,result.getScore());
            executeInsert(statement);
            int id = fetchGeneratedId(statement);
            connection.commit();
        } catch (SQLException ex) {
            connection.rollback();
            throw ex;
        } finally {
            connection.setAutoCommit(autoCommit);
        }
    }

    private Result fetchResult(ResultSet resultSet) throws SQLException {
        int assignmentPageId = resultSet.getInt("pageid");
        int userId = resultSet.getInt("userid");
        String timeStamp = resultSet.getString("timestamp");
        int score = resultSet.getInt("score");
        Result result = new Result(assignmentPageId, userDao.getUserById(userId), Timestamp.valueOf(timeStamp), connection);
        result.setScore(score);
        return result;
    }
}
