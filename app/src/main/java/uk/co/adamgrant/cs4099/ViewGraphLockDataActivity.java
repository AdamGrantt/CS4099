package uk.co.adamgrant.cs4099;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.*;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;


import java.util.Calendar;
import java.util.Date;

public class ViewGraphLockDataActivity extends AppCompatActivity {
    GraphView graph;
    BarGraphSeries<DataPoint> series;
    private LockData data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_lock_data);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        data = new LockData();
        if(data.getEntries().size() != 0){
            Log.v("$$$$$$", "Initialising Lock Graph");

            initLockGraph();
        } else {
            Log.v("$$$$$$", "No Data Found");
        }
    }

    public void initLockGraph(){
        GraphView graph = (GraphView) this.findViewById(R.id.lock_graph);

        DataPoint points[] = new DataPoint[data.getNoEntries()*2];
        int count = 0;
        for(LockDataEntry entry: data.getEntries()) {
            Date date = new Date(entry.getEpoch());
            points[count] = new DataPoint(date, (entry.getLocked() + 1) % 2);
            count++;
            points[count] = new DataPoint(date, entry.getLocked());
            count++;
        }

        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(points);

        // you can directly pass Date objects to DataPoint-Constructor
        // this will convert the Date to double via Date#getTime()
        graph.addSeries(series);

        // set date label formatter
        graph.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(this));
        graph.getGridLabelRenderer().setNumHorizontalLabels(3); // only 4 because of the space
        graph.getGridLabelRenderer().setNumVerticalLabels(2); // only 4 because of the space

        // set manual x bounds to have nice steps
        graph.getViewport().setMinX(new Date(data.getEntries().get(0).getEpoch()).getTime());
        graph.getViewport().setMaxX(new Date(data.getEntries().get(data.getNoEntries() - 1).getEpoch()).getTime());
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinY(0);
        graph.getViewport().setMaxY(1);
        graph.getViewport().setYAxisBoundsManual(true);
    }

//    private DataPoint[] populateSeries(LineGraphSeries series) {
//        DataPoint points[] = new DataPoint[data.getNoEntries()];
//        int count = 0;
//        for(LockDataEntry entry : data.getData()) {
//            points[count] = new DataPoint(entry.getEpoch(), entry.getLocked());
//        }
//        return points;
//    }

    //
}
