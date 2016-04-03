package uk.co.adamgrant.cs4099;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
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
            // NO DATA FOUND MESSAGE
        }
    }

    public void initLockGraph(){
//        graph = (GraphView) findViewById(R.id.lock_graph);
////        series = new LineGraphSeries<>(populateSeries(series));
//        series = new BarGraphSeries<DataPoint>(new DataPoint[] {
//                new DataPoint(0, 0),
//                new DataPoint(1, 1),
//                new DataPoint(3, 0),
//                new DataPoint(5, 1)
//        });
//        graph.addSeries(series);

        // generate Dates
        Calendar calendar = Calendar.getInstance();
        Date d1 = calendar.getTime();
        calendar.add(Calendar.DATE, 1);
        Date d2 = calendar.getTime();
        calendar.add(Calendar.DATE, 1);
        Date d3 = calendar.getTime();

        GraphView graph = (GraphView) this.findViewById(R.id.lock_graph);

        DataPoint points[] = new DataPoint[data.getNoEntries()];
        int count = 0;
        for(LockDataEntry entry: data.getEntries()) {

            points[count] = new DataPoint(entry.getEpoch(), entry.getLocked());
            count++;
        }

        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(points);

        // you can directly pass Date objects to DataPoint-Constructor
        // this will convert the Date to double via Date#getTime()
//        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[] {
//                new DataPoint(d1, 1),
//                new DataPoint(d2, 5),
//                new DataPoint(d3, 3)
//        });
        graph.addSeries(series);

        // set date label formatter
        graph.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(this));
        graph.getGridLabelRenderer().setNumHorizontalLabels(3); // only 4 because of the space

        // set manual x bounds to have nice steps
//        graph.getViewport().setMinX(d1.getTime());
//        graph.getViewport().setMaxX(d3.getTime());
//        graph.getViewport().setXAxisBoundsManual(true);
    }

    private DataPoint[] populateSeries(LineGraphSeries series) {
        DataPoint points[] = new DataPoint[data.getNoEntries()];
//        int count = 0;
//        for(LockDataEntry entry : data.getData()) {
//            points[count] = new DataPoint(entry.getEpoch(), entry.getLocked());
//        }
        return points;
    }

    //
}
