package com.vrtkarim.courierflow.services;


import com.graphhopper.GHRequest;
import com.graphhopper.GHResponse;
import com.graphhopper.GraphHopper;
import com.graphhopper.ResponsePath;
import com.graphhopper.util.*;
import com.graphhopper.util.shapes.GHPoint;
import com.vrtkarim.courierflow.dto.Location;
import com.vrtkarim.courierflow.dto.LocationRequest;
import com.vrtkarim.courierflow.utils.GpxConversion;
import org.springframework.stereotype.Service;

import javax.xml.crypto.dsig.keyinfo.PGPData;
import java.util.Locale;

@Service
public class RoutingService {

    GraphHopper graphHopper;

    public RoutingService(GraphHopper graphHopper) {
        this.graphHopper = graphHopper;
    }
    public String routing( LocationRequest locationRequest) {
        GHRequest req = new  GHRequest().setProfile("car").
                setAlgorithm(Parameters.Algorithms.ASTAR_BI);
        req.addPoint(new GHPoint(locationRequest.getSource().getLatitude(), locationRequest.getSource().getLongitude()));
        for(Location l : locationRequest.getDestinations()){
            req.addPoint(new GHPoint(l.getLatitude(), l.getLongitude()));
        }


        GHResponse res = graphHopper.route(req);
        // handle errors
        if (res.hasErrors())
            throw new RuntimeException(res.getErrors().toString());

        // use the best path, see the GHResponse class for more possibilities.
        ResponsePath path = res.getBest();

        // points, distance in meters and time in millis of the full path
        PointList pointList = path.getPoints();
        double distance = path.getDistance();
        long timeInMs = path.getTime();
        System.out.println(distance + " m, " + timeInMs + " ms, " + pointList.size() + " points");

        Translation tr = graphHopper.getTranslationMap().getWithFallBack(Locale.ENGLISH);
        InstructionList il = path.getInstructions();

        // iterate over all turn instructions
//        for (Instruction instruction : il) {
//            System.out.println(instruction.getPoints());
//            System.out.println("distance " + instruction.getDistance() + " for instruction: " + instruction.getTurnDescription(tr));
//            System.out.println("_____________________________________");
//        }
        String gpx = GpxConversion.createGPX(il, "test", 9L, false, true, true, true, "latest", tr);
        System.out.println(gpx);

        assert il.size() == 6;
        assert Helper.round(path.getDistance(), -2) == 600;
        if (res.hasErrors())
            throw new RuntimeException(res.getErrors().toString());
        assert Helper.round(res.getBest().getDistance(), -2) == 600;
        return gpx;
    }
}
