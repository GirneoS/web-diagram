package com.ozhegov.laba4_backend.bean;

import com.ozhegov.laba4_backend.dao.PointDAO;
import com.ozhegov.laba4_backend.dao.UserDAO;
import com.ozhegov.laba4_backend.model.Point;
import com.ozhegov.laba4_backend.model.User;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Stateless
public class PointsBean {
    @Inject
    private PointDAO pointDAO;
    @Inject
    private UserDAO userDAO;

    public List<Point> getPoints(long userId){
        User user = userDAO.get(userId);
        return pointDAO.getAll(user);
    }

    public List<Point> storePoint(String strX, String strY, String strR, long startTime, long userId){
        double[] xs = Arrays.stream(strX.split(",")).mapToDouble(Double::parseDouble).toArray();
        double y = Double.parseDouble(strY);
        double r = Double.parseDouble(strR);

        List<Point> points = new ArrayList<>();
        for(double x: xs) {
            User user = userDAO.get(userId);
            Point point = new Point(x, y, r, user);
            if (isIntoArea(x, y, r))
                point.setResult("попал");
            else
                point.setResult("не попал");
            point.setExecutionTime(System.nanoTime() - startTime);

            pointDAO.create(point);
            points.add(point);
        }
        return points;
    }

    public boolean isIntoArea(double x, double y, double r){
        double line = y + 2*x - r;
        double circle = Math.pow(x, 2) + Math.pow(y, 2) - Math.pow(r, 2);

        return ((x <= 0 && y >= 0 && x >= -r/2 && y <= r) || (x >= 0 && y >= 0 && line <= 0) || (x <= 0 && y <=0 && circle <= 0));
    }
}
